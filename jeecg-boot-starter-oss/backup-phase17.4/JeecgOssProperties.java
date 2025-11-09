package org.jeecg.config.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OSS配置属性
 *
 * @author jeecg-boot
 * @since 4.0.0
 */
@Data
@ConfigurationProperties(prefix = "jeecg.oss")
public class JeecgOssProperties {

    /**
     * 是否启用OSS
     */
    private Boolean enabled = true;

    /**
     * 存储类型: minio, aliyun, local
     */
    private String type = "minio";

    /**
     * 服务端点
     * MinIO示例: http://localhost:9000
     * 阿里云示例: https://oss-cn-beijing.aliyuncs.com
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
     * 桶名称（默认桶）
     */
    private String bucketName = "jeecg-bucket";

    /**
     * 静态访问域名（用于替换endpoint）
     * 例如: https://cdn.example.com
     */
    private String staticDomain;

    /**
     * 本地存储路径（type=local时使用）
     */
    private String localPath = "./upload";

    /**
     * 是否自动创建桶
     */
    private Boolean autoCreateBucket = true;

    /**
     * MinIO专用配置
     */
    private MinioProperties minio = new MinioProperties();

    /**
     * 阿里云OSS专用配置
     */
    private AliyunProperties aliyun = new AliyunProperties();

    /**
     * MinIO配置
     */
    @Data
    public static class MinioProperties {
        /**
         * MinIO URL (兼容旧配置)
         */
        private String minioUrl;

        /**
         * MinIO用户名 (兼容旧配置)
         */
        private String minioName;

        /**
         * MinIO密码 (兼容旧配置)
         */
        private String minioPass;

        /**
         * 桶名称 (兼容旧配置)
         */
        private String bucketName;
    }

    /**
     * 阿里云OSS配置
     */
    @Data
    public static class AliyunProperties {
        /**
         * 地域节点
         */
        private String region;

        /**
         * 是否使用内网访问
         */
        private Boolean internal = false;

        /**
         * 是否使用HTTPS
         */
        private Boolean secure = true;
    }

    /**
     * 获取实际的endpoint（优先使用统一配置）
     */
    public String getActualEndpoint() {
        if (endpoint != null && !endpoint.isEmpty()) {
            return endpoint;
        }
        // 兼容旧配置
        if (minio.getMinioUrl() != null && !minio.getMinioUrl().isEmpty()) {
            return minio.getMinioUrl();
        }
        return null;
    }

    /**
     * 获取实际的accessKey（优先使用统一配置）
     */
    public String getActualAccessKey() {
        if (accessKey != null && !accessKey.isEmpty()) {
            return accessKey;
        }
        // 兼容旧配置
        if (minio.getMinioName() != null && !minio.getMinioName().isEmpty()) {
            return minio.getMinioName();
        }
        return null;
    }

    /**
     * 获取实际的secretKey（优先使用统一配置）
     */
    public String getActualSecretKey() {
        if (secretKey != null && !secretKey.isEmpty()) {
            return secretKey;
        }
        // 兼容旧配置
        if (minio.getMinioPass() != null && !minio.getMinioPass().isEmpty()) {
            return minio.getMinioPass();
        }
        return null;
    }

    /**
     * 获取实际的桶名称（优先使用统一配置）
     */
    public String getActualBucketName() {
        if (bucketName != null && !bucketName.isEmpty()) {
            return bucketName;
        }
        // 兼容旧配置
        if (minio.getBucketName() != null && !minio.getBucketName().isEmpty()) {
            return minio.getBucketName();
        }
        return "jeecg-bucket";
    }
}