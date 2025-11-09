package org.jeecg.config.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OSS配置属性（简化版）
 * 
 * @author jeecg-boot
 * @since 4.0.0
 */
@Data
@ConfigurationProperties(prefix = "jeecg.oss")
public class JeecgOssProperties {
    
    /**
     * 是否启用OSS功能
     */
    private Boolean enabled = true;
    
    /**
     * OSS类型：minio, aliyun, local
     */
    private String type = "local";
    
    /**
     * OSS服务端点
     */
    private String endpoint;
    
    /**
     * 访问密钥ID
     */
    private String accessKey;
    
    /**
     * 访问密钥Secret
     */
    private String secretKey;
    
    /**
     * 默认存储桶名称
     */
    private String bucketName;
    
    /**
     * 静态资源域名（CDN域名）
     */
    private String staticDomain;
    
    /**
     * 本地存储路径
     */
    private String localPath = "./upload";
    
    /**
     * 是否自动创建存储桶
     */
    private Boolean autoCreateBucket = true;
    
    /**
     * MinIO配置
     */
    private MinioProperties minio = new MinioProperties();
    
    /**
     * 阿里云OSS配置
     */
    private AliyunProperties aliyun = new AliyunProperties();
    
    /**
     * MinIO配置属性
     */
    @Data
    public static class MinioProperties {
        private String endpoint;
        private String accessKey;
        private String secretKey;
        private String bucketName;
    }
    
    /**
     * 阿里云OSS配置属性
     */
    @Data
    public static class AliyunProperties {
        private String endpoint;
        private String accessKeyId;
        private String accessKeySecret;
        private String bucketName;
    }
    
    /**
     * 获取实际端点（优先使用具体配置，其次使用通用配置）
     */
    public String getActualEndpoint() {
        if ("minio".equals(type) && minio.getEndpoint() != null) {
            return minio.getEndpoint();
        }
        if ("aliyun".equals(type) && aliyun.getEndpoint() != null) {
            return aliyun.getEndpoint();
        }
        return endpoint;
    }
    
    /**
     * 获取实际访问密钥
     */
    public String getActualAccessKey() {
        if ("minio".equals(type) && minio.getAccessKey() != null) {
            return minio.getAccessKey();
        }
        if ("aliyun".equals(type) && aliyun.getAccessKeyId() != null) {
            return aliyun.getAccessKeyId();
        }
        return accessKey;
    }
    
    /**
     * 获取实际访问密钥Secret
     */
    public String getActualSecretKey() {
        if ("minio".equals(type) && minio.getSecretKey() != null) {
            return minio.getSecretKey();
        }
        if ("aliyun".equals(type) && aliyun.getAccessKeySecret() != null) {
            return aliyun.getAccessKeySecret();
        }
        return secretKey;
    }
    
    /**
     * 获取实际存储桶名称
     */
    public String getActualBucketName() {
        if ("minio".equals(type) && minio.getBucketName() != null) {
            return minio.getBucketName();
        }
        if ("aliyun".equals(type) && aliyun.getBucketName() != null) {
            return aliyun.getBucketName();
        }
        return bucketName;
    }
}