package org.jeecg.common.oss.impl;

import cn.hutool.core.util.StrUtil;
import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.constant.SymbolConstant;
import org.jeecg.common.oss.OssService;
import org.jeecg.common.util.CommonUtils;
import org.jeecg.common.util.filter.SsrfFileTypeFilter;
import org.jeecg.common.util.filter.StrAttackFilter;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.config.oss.JeecgOssProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Date;

/**
 * MinIO对象存储服务实现
 *
 * @author jeecg-boot
 * @since 4.0.0
 */
@Slf4j
public class MinioOssServiceImpl implements OssService {

    private final MinioClient minioClient;
    private final JeecgOssProperties ossProperties;

    public MinioOssServiceImpl(MinioClient minioClient, JeecgOssProperties ossProperties) {
        this.minioClient = minioClient;
        this.ossProperties = ossProperties;
    }

    @Override
    public String upload(MultipartFile file, String customPath) {
        return upload(file, ossProperties.getActualBucketName(), customPath);
    }

    @Override
    public String upload(MultipartFile file, String bucketName, String customPath) {
        try {
            // 业务路径过滤，防止攻击
            customPath = StrAttackFilter.filter(customPath);
            
            // 文件安全校验，防止上传漏洞文件
            SsrfFileTypeFilter.checkUploadFileType(file, customPath);

            // 确保桶存在
            ensureBucketExists(bucketName);

            // 获取文件名
            String orgName = file.getOriginalFilename();
            if (StrUtil.isEmpty(orgName)) {
                orgName = file.getName();
            }
            orgName = CommonUtils.getFileName(orgName);

            // 生成对象名称
            String objectName = generateObjectName(customPath, orgName);

            // 上传文件
            InputStream stream = file.getInputStream();
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .contentType("application/octet-stream")
                    .stream(stream, stream.available(), -1)
                    .build();
            
            minioClient.putObject(objectArgs);
            stream.close();

            // 返回文件URL
            return buildFileUrl(bucketName, objectName);
            
        } catch (Exception e) {
            log.error("MinIO文件上传失败: {}", e.getMessage(), e);
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

            // 移除开头的斜杠
            if (fileName.startsWith(SymbolConstant.SINGLE_SLASH)) {
                fileName = fileName.substring(1);
            }

            // 上传文件
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .contentType("application/octet-stream")
                    .stream(inputStream, inputStream.available(), -1)
                    .build();
            
            minioClient.putObject(objectArgs);
            inputStream.close();

            // 返回文件URL
            return buildFileUrl(bucketName, fileName);
            
        } catch (Exception e) {
            log.error("MinIO文件流上传失败: {}", e.getMessage(), e);
            throw new RuntimeException("文件流上传失败: " + e.getMessage(), e);
        }
    }

    @Override
    public InputStream getFile(String bucketName, String objectName) {
        try {
            GetObjectArgs objectArgs = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();
            return minioClient.getObject(objectArgs);
        } catch (Exception e) {
            log.error("MinIO文件获取失败: {}", e.getMessage(), e);
            throw new RuntimeException("文件获取失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(String bucketName, String objectName) {
        try {
            RemoveObjectArgs objectArgs = RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();
            minioClient.removeObject(objectArgs);
            log.info("MinIO文件删除成功: {}/{}", bucketName, objectName);
        } catch (Exception e) {
            log.error("MinIO文件删除失败: {}", e.getMessage(), e);
            throw new RuntimeException("文件删除失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteByUrl(String url) {
        try {
            // 从URL中提取桶名和对象名
            String minioUrl = ossProperties.getActualEndpoint();
            if (url.startsWith(minioUrl)) {
                String path = url.substring(minioUrl.length());
                String[] parts = path.split("/", 2);
                if (parts.length == 2) {
                    delete(parts[0], parts[1]);
                    return;
                }
            }
            log.warn("无法从URL解析文件路径: {}", url);
        } catch (Exception e) {
            log.error("根据URL删除文件失败: {}", e.getMessage(), e);
            throw new RuntimeException("删除文件失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName, Integer expires) {
        try {
            GetPresignedObjectUrlArgs objectArgs = GetPresignedObjectUrlArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .expiry(expires)
                    .method(Method.GET)
                    .build();
            
            String url = minioClient.getPresignedObjectUrl(objectArgs);
            return URLDecoder.decode(url, "UTF-8");
        } catch (Exception e) {
            log.error("MinIO获取文件外链失败: {}", e.getMessage(), e);
            throw new RuntimeException("获取文件外链失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName, Date expiration) {
        // 计算距离过期时间的秒数
        long expires = (expiration.getTime() - System.currentTimeMillis()) / 1000;
        return getObjectUrl(bucketName, objectName, (int) expires);
    }

    @Override
    public String getPublicObjectUrl(String bucketName, String objectName) {
        return buildFileUrl(bucketName, objectName);
    }

    @Override
    public boolean bucketExists(String bucketName) {
        try {
            return minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucketName).build()
            );
        } catch (Exception e) {
            log.error("检查桶是否存在失败: {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void createBucket(String bucketName) {
        try {
            if (!bucketExists(bucketName)) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(bucketName).build()
                );
                log.info("MinIO桶创建成功: {}", bucketName);
            }
        } catch (Exception e) {
            log.error("MinIO创建桶失败: {}", e.getMessage(), e);
            throw new RuntimeException("创建桶失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteBucket(String bucketName) {
        try {
            minioClient.removeBucket(
                    RemoveBucketArgs.builder().bucket(bucketName).build()
            );
            log.info("MinIO桶删除成功: {}", bucketName);
        } catch (Exception e) {
            log.error("MinIO删除桶失败: {}", e.getMessage(), e);
            throw new RuntimeException("删除桶失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String getStorageType() {
        return "minio";
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
     * 生成对象名称（带时间戳）
     */
    private String generateObjectName(String bizPath, String orgName) {
        String fileName;
        if (orgName.indexOf(".") == -1) {
            fileName = orgName + "_" + System.currentTimeMillis();
        } else {
            fileName = orgName.substring(0, orgName.lastIndexOf(".")) 
                    + "_" + System.currentTimeMillis() 
                    + orgName.substring(orgName.lastIndexOf("."));
        }

        String objectName = bizPath + "/" + fileName;
        
        // 移除开头的斜杠
        if (objectName.startsWith(SymbolConstant.SINGLE_SLASH)) {
            objectName = objectName.substring(1);
        }
        
        return objectName;
    }

    /**
     * 构建文件访问URL
     */
    private String buildFileUrl(String bucketName, String objectName) {
        String minioUrl = ossProperties.getActualEndpoint();
        if (!minioUrl.endsWith("/")) {
            minioUrl += "/";
        }
        return minioUrl + bucketName + "/" + objectName;
    }
}