package org.jeecg.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OSS 配置属性
 * @author: jeecg-boot
 */
@Data
@ConfigurationProperties(prefix = "jeecg.oss")
public class OssProperties {

    /**
     * 对象存储服务的URL
     */
    private String endpoint;

    /**
     * Access Key ID
     */
    private String accessKey;

    /**
     * Secret Access Key
     */
    private String secretKey;

    /**
     * 默认的存储桶名称
     */
    private String bucketName;

    /**
     * 自定义域名(用于文件访问路径)
     */
    private String staticDomain;

    /**
     * MinIO配置
     */
    private MinioProperties minio = new MinioProperties();

    /**
     * MinIO 配置属性
     */
    @Data
    public static class MinioProperties {
        /**
         * MinIO服务地址
         */
        private String minioUrl;

        /**
         * MinIO用户名
         */
        private String minioName;

        /**
         * MinIO密码
         */
        private String minioPass;

        /**
         * MinIO存储桶名称
         */
        private String bucketName;
    }
}