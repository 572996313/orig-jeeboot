package org.jeecg.common.oss.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.constant.SymbolConstant;
import org.jeecg.common.oss.OssService;
import org.jeecg.common.util.CommonUtils;
import org.jeecg.common.util.filter.SsrfFileTypeFilter;
import org.jeecg.common.util.filter.StrAttackFilter;
import org.jeecg.config.oss.JeecgOssProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * 本地文件存储服务实现
 *
 * @author jeecg-boot
 * @since 4.0.0
 */
@Slf4j
public class LocalOssServiceImpl implements OssService {

    private final JeecgOssProperties ossProperties;
    private final String basePath;

    public LocalOssServiceImpl(JeecgOssProperties ossProperties) {
        this.ossProperties = ossProperties;
        this.basePath = ossProperties.getLocalPath();
        
        // 确保基础目录存在
        FileUtil.mkdir(basePath);
        log.info("本地存储初始化，路径: {}", basePath);
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

            // 获取文件名
            String orgName = file.getOriginalFilename();
            if (StrUtil.isEmpty(orgName)) {
                orgName = file.getName();
            }
            orgName = CommonUtils.getFileName(orgName);

            // 生成文件路径
            String fileName = generateFileName(orgName);
            String relativePath = bucketName + "/" + customPath + "/" + fileName;
            String fullPath = basePath + "/" + relativePath;

            // 确保目录存在
            File targetFile = new File(fullPath);
            FileUtil.mkParentDirs(targetFile);

            // 保存文件
            file.transferTo(targetFile);
            
            log.info("本地文件上传成功: {}", fullPath);

            // 返回相对路径
            return "/" + relativePath;
            
        } catch (Exception e) {
            log.error("本地文件上传失败: {}", e.getMessage(), e);
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
            String relativePath = bucketName + "/" + fileName;
            String fullPath = basePath + "/" + relativePath;

            // 确保目录存在
            File targetFile = new File(fullPath);
            FileUtil.mkParentDirs(targetFile);

            // 保存文件
            FileUtil.writeFromStream(inputStream, targetFile);
            
            log.info("本地文件流上传成功: {}", fullPath);

            // 返回相对路径
            return "/" + relativePath;
            
        } catch (Exception e) {
            log.error("本地文件流上传失败: {}", e.getMessage(), e);
            throw new RuntimeException("文件流上传失败: " + e.getMessage(), e);
        }
    }

    @Override
    public InputStream getFile(String bucketName, String objectName) {
        try {
            String fullPath = basePath + "/" + bucketName + "/" + objectName;
            File file = new File(fullPath);
            
            if (!file.exists()) {
                throw new RuntimeException("文件不存在: " + fullPath);
            }
            
            return new FileInputStream(file);
        } catch (Exception e) {
            log.error("本地文件获取失败: {}", e.getMessage(), e);
            throw new RuntimeException("文件获取失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(String bucketName, String objectName) {
        try {
            String fullPath = basePath + "/" + bucketName + "/" + objectName;
            File file = new File(fullPath);
            
            if (file.exists()) {
                FileUtil.del(file);
                log.info("本地文件删除成功: {}", fullPath);
            } else {
                log.warn("文件不存在，无需删除: {}", fullPath);
            }
        } catch (Exception e) {
            log.error("本地文件删除失败: {}", e.getMessage(), e);
            throw new RuntimeException("文件删除失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteByUrl(String url) {
        try {
            // 从URL中提取相对路径
            String relativePath = url;
            if (relativePath.startsWith("/")) {
                relativePath = relativePath.substring(1);
            }
            
            String fullPath = basePath + "/" + relativePath;
            File file = new File(fullPath);
            
            if (file.exists()) {
                FileUtil.del(file);
                log.info("根据URL删除文件成功: {}", fullPath);
            }
        } catch (Exception e) {
            log.error("根据URL删除文件失败: {}", e.getMessage(), e);
            throw new RuntimeException("删除文件失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName, Integer expires) {
        // 本地存储不支持临时URL，返回永久URL
        return getPublicObjectUrl(bucketName, objectName);
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName, Date expiration) {
        // 本地存储不支持临时URL，返回永久URL
        return getPublicObjectUrl(bucketName, objectName);
    }

    @Override
    public String getPublicObjectUrl(String bucketName, String objectName) {
        // 返回相对路径，由Web服务器提供访问
        return "/" + bucketName + "/" + objectName;
    }

    @Override
    public boolean bucketExists(String bucketName) {
        String bucketPath = basePath + "/" + bucketName;
        File bucketDir = new File(bucketPath);
        return bucketDir.exists() && bucketDir.isDirectory();
    }

    @Override
    public void createBucket(String bucketName) {
        try {
            String bucketPath = basePath + "/" + bucketName;
            File bucketDir = new File(bucketPath);
            
            if (!bucketDir.exists()) {
                FileUtil.mkdir(bucketDir);
                log.info("本地存储桶创建成功: {}", bucketPath);
            }
        } catch (Exception e) {
            log.error("本地存储桶创建失败: {}", e.getMessage(), e);
            throw new RuntimeException("创建桶失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteBucket(String bucketName) {
        try {
            String bucketPath = basePath + "/" + bucketName;
            File bucketDir = new File(bucketPath);
            
            if (bucketDir.exists()) {
                FileUtil.del(bucketDir);
                log.info("本地存储桶删除成功: {}", bucketPath);
            }
        } catch (Exception e) {
            log.error("本地存储桶删除失败: {}", e.getMessage(), e);
            throw new RuntimeException("删除桶失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String getStorageType() {
        return "local";
    }

    /**
     * 生成文件名（带时间戳）
     */
    private String generateFileName(String orgName) {
        if (orgName.indexOf(".") == -1) {
            return orgName + "_" + System.currentTimeMillis();
        } else {
            return orgName.substring(0, orgName.lastIndexOf(".")) 
                    + "_" + System.currentTimeMillis() 
                    + orgName.substring(orgName.lastIndexOf("."));
        }
    }
}