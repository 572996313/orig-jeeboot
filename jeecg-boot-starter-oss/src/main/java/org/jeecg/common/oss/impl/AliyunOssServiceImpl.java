package org.jeecg.common.oss.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItemStream;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.constant.SymbolConstant;
import org.jeecg.common.oss.OssService;
import org.jeecg.common.util.CommonUtils;
import org.jeecg.common.util.filter.SsrfFileTypeFilter;
import org.jeecg.common.util.filter.StrAttackFilter;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.config.oss.JeecgOssProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * 阿里云OSS对象存储服务实现
 *
 * @author jeecg-boot
 * @since 4.0.0
 */
@Slf4j
public class AliyunOssServiceImpl implements OssService {

    private final OSSClient ossClient;
    private final JeecgOssProperties ossProperties;

    public AliyunOssServiceImpl(OSSClient ossClient, JeecgOssProperties ossProperties) {
        this.ossClient = ossClient;
        this.ossProperties = ossProperties;
    }

    @Override
    public String upload(MultipartFile file, String customPath) {
        return upload(file, ossProperties.getActualBucketName(), customPath);
    }

    @Override
    public String upload(MultipartFile file, String bucketName, String customPath) {
        try {
            // 文件安全校验，防止上传漏洞文件
            SsrfFileTypeFilter.checkUploadFileType(file);

            // 业务路径过滤，防止攻击
            customPath = StrAttackFilter.filter(customPath);

            // 确保桶存在
            ensureBucketExists(bucketName);

            // 获取文件名
            String orgName = file.getOriginalFilename();
            if (StrUtil.isEmpty(orgName)) {
                orgName = file.getName();
            }
            orgName = CommonUtils.getFileName(orgName);

            // 生成文件路径
            String filePath = generateFilePath(customPath, orgName);

            // 上传文件
            PutObjectResult result = ossClient.putObject(bucketName, filePath, file.getInputStream());
            
            if (result != null) {
                log.info("阿里云OSS文件上传成功: {}", filePath);
            }

            // 返回文件URL
            return buildFileUrl(bucketName, filePath);
            
        } catch (Exception e) {
            log.error("阿里云OSS文件上传失败: {}", e.getMessage(), e);
            throw new RuntimeException("文件上传失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String upload(InputStream inputStream, String fileName) {
        return upload(inputStream, ossProperties.getActualBucketName(), fileName);
    }

    @Override
    public String upload(InputStream inputStream, String bucketName, String fileName) {
        try {
            // 确保桶存在
            ensureBucketExists(bucketName);

            // 上传文件
            PutObjectResult result = ossClient.putObject(bucketName, fileName, inputStream);
            
            // 设置权限(公开读)
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            
            if (result != null) {
                log.info("阿里云OSS文件流上传成功: {}", fileName);
            }

            // 返回文件URL
            return buildFileUrl(bucketName, fileName);
            
        } catch (Exception e) {
            log.error("阿里云OSS文件流上传失败: {}", e.getMessage(), e);
            throw new RuntimeException("文件流上传失败: " + e.getMessage(), e);
        }
    }

    /**
     * 上传文件（兼容FileItemStream）
     */
    public String upload(FileItemStream file, String fileDir) {
        try {
            String bucketName = ossProperties.getActualBucketName();
            
            // 业务路径过滤
            fileDir = StrAttackFilter.filter(fileDir);
            
            // 生成文件名
            String suffix = file.getName().substring(file.getName().lastIndexOf('.'));
            String fileName = java.util.UUID.randomUUID().toString().replace("-", "") + suffix;
            
            if (!fileDir.endsWith(SymbolConstant.SINGLE_SLASH)) {
                fileDir = fileDir.concat(SymbolConstant.SINGLE_SLASH);
            }
            
            String filePath = fileDir + fileName;
            
            // 上传文件
            PutObjectResult result = ossClient.putObject(bucketName, filePath, file.openStream());
            
            // 设置权限(公开读)
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            
            if (result != null) {
                log.info("阿里云OSS文件上传成功: {}", filePath);
            }

            return buildFileUrl(bucketName, filePath);
            
        } catch (Exception e) {
            log.error("阿里云OSS FileItemStream上传失败: {}", e.getMessage(), e);
            throw new RuntimeException("文件上传失败: " + e.getMessage(), e);
        }
    }

    @Override
    public InputStream getFile(String bucketName, String objectName) {
        try {
            // 替换前缀，防止key不一致
            objectName = replacePrefix(objectName, bucketName);
            
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);
            return new BufferedInputStream(ossObject.getObjectContent());
        } catch (Exception e) {
            log.error("阿里云OSS文件获取失败: {}", e.getMessage(), e);
            throw new RuntimeException("文件获取失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(String bucketName, String objectName) {
        try {
            ossClient.deleteObject(bucketName, objectName);
            log.info("阿里云OSS文件删除成功: {}/{}", bucketName, objectName);
        } catch (Exception e) {
            log.error("阿里云OSS文件删除失败: {}", e.getMessage(), e);
            throw new RuntimeException("文件删除失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteByUrl(String url) {
        try {
            String bucketName = ossProperties.getActualBucketName();
            String bucketUrl = buildBucketUrl(bucketName);
            
            // 从URL中提取对象名
            String objectName = url.replace(bucketUrl, "");
            
            delete(bucketName, objectName);
        } catch (Exception e) {
            log.error("根据URL删除文件失败: {}", e.getMessage(), e);
            throw new RuntimeException("删除文件失败: " + e.getMessage(), e);
        }
    }

    /**
     * 删除文件（指定桶）
     */
    public void deleteUrl(String url, String bucket) {
        try {
            String bucketName = oConvertUtils.isNotEmpty(bucket) 
                    ? bucket : ossProperties.getActualBucketName();
            
            String bucketUrl = buildBucketUrl(bucketName);
            String objectName = url.replace(bucketUrl, "");
            
            ossClient.deleteObject(bucketName, objectName);
            log.info("阿里云OSS文件删除成功: {}", url);
        } catch (Exception e) {
            log.error("阿里云OSS删除文件失败: {}", e.getMessage(), e);
        }
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName, Integer expires) {
        Date expiration = new Date(System.currentTimeMillis() + expires * 1000L);
        return getObjectUrl(bucketName, objectName, expiration);
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName, Date expiration) {
        try {
            // 替换前缀，防止key不一致
            objectName = replacePrefix(objectName, bucketName);
            
            // 检查对象是否存在
            if (ossClient.doesObjectExist(bucketName, objectName)) {
                URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
                return url.toString();
            }
            
            log.warn("对象不存在: {}/{}", bucketName, objectName);
            return null;
        } catch (Exception e) {
            log.error("阿里云OSS获取文件外链失败: {}", e.getMessage(), e);
            throw new RuntimeException("获取文件外链失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String getPublicObjectUrl(String bucketName, String objectName) {
        return buildFileUrl(bucketName, objectName);
    }

    @Override
    public boolean bucketExists(String bucketName) {
        try {
            return ossClient.doesBucketExist(bucketName);
        } catch (Exception e) {
            log.error("检查桶是否存在失败: {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void createBucket(String bucketName) {
        try {
            if (!bucketExists(bucketName)) {
                ossClient.createBucket(bucketName);
                log.info("阿里云OSS桶创建成功: {}", bucketName);
            }
        } catch (Exception e) {
            log.error("阿里云OSS创建桶失败: {}", e.getMessage(), e);
            throw new RuntimeException("创建桶失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteBucket(String bucketName) {
        try {
            ossClient.deleteBucket(bucketName);
            log.info("阿里云OSS桶删除成功: {}", bucketName);
        } catch (Exception e) {
            log.error("阿里云OSS删除桶失败: {}", e.getMessage(), e);
            throw new RuntimeException("删除桶失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String getStorageType() {
        return "aliyun";
    }

    /**
     * 获取原始URL（将静态域名替换为OSS域名）
     */
    public String getOriginalUrl(String url) {
        String bucketName = ossProperties.getActualBucketName();
        String originalDomain = "https://" + bucketName + "." + ossProperties.getActualEndpoint();
        String staticDomain = ossProperties.getStaticDomain();
        
        if (oConvertUtils.isNotEmpty(staticDomain) && url.indexOf(staticDomain) != -1) {
            url = url.replace(staticDomain, originalDomain);
        }
        return url;
    }

    /**
     * 确保桶存在，不存在则创建
     */
    private void ensureBucketExists(String bucketName) {
        if (ossProperties.getAutoCreateBucket() && !bucketExists(bucketName)) {
            createBucket(bucketName);
        }
    }

    /**
     * 生成文件路径
     */
    private String generateFilePath(String fileDir, String orgName) {
        String fileName;
        if (orgName.indexOf(".") == -1) {
            fileName = orgName + "_" + System.currentTimeMillis();
        } else {
            fileName = orgName.substring(0, orgName.lastIndexOf(".")) 
                    + "_" + System.currentTimeMillis() 
                    + orgName.substring(orgName.lastIndexOf("."));
        }

        if (!fileDir.endsWith(SymbolConstant.SINGLE_SLASH)) {
            fileDir = fileDir.concat(SymbolConstant.SINGLE_SLASH);
        }

        return fileDir + fileName;
    }

    /**
     * 构建文件访问URL
     */
    private String buildFileUrl(String bucketName, String filePath) {
        String staticDomain = ossProperties.getStaticDomain();
        
        if (oConvertUtils.isNotEmpty(staticDomain) 
                && staticDomain.toLowerCase().startsWith(CommonConstant.STR_HTTP)) {
            return staticDomain + SymbolConstant.SINGLE_SLASH + filePath;
        } else {
            return "https://" + bucketName + "." + ossProperties.getActualEndpoint() 
                    + SymbolConstant.SINGLE_SLASH + filePath;
        }
    }

    /**
     * 构建桶URL前缀
     */
    private String buildBucketUrl(String bucketName) {
        String staticDomain = ossProperties.getStaticDomain();
        
        if (oConvertUtils.isNotEmpty(staticDomain) 
                && staticDomain.toLowerCase().startsWith(CommonConstant.STR_HTTP)) {
            return staticDomain + SymbolConstant.SINGLE_SLASH;
        } else {
            return "https://" + bucketName + "." + ossProperties.getActualEndpoint() 
                    + SymbolConstant.SINGLE_SLASH;
        }
    }

    /**
     * 替换前缀，防止key不一致导致获取不到文件
     */
    private String replacePrefix(String objectName, String customBucket) {
        log.debug("替换前 objectName: {}", objectName);
        
        String staticDomain = ossProperties.getStaticDomain();
        if (oConvertUtils.isNotEmpty(staticDomain)) {
            objectName = objectName.replace(staticDomain + SymbolConstant.SINGLE_SLASH, "");
        } else {
            String bucketName = oConvertUtils.isNotEmpty(customBucket) 
                    ? customBucket : ossProperties.getActualBucketName();
            String path = "https://" + bucketName + "." + ossProperties.getActualEndpoint() 
                    + SymbolConstant.SINGLE_SLASH;
            objectName = objectName.replace(path, "");
        }
        
        log.debug("替换后 objectName: {}", objectName);
        return objectName;
    }
}