package org.jeecg.config.oss;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.MinioUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * MinIO配置类（向后兼容）
 * 
 * @deprecated 请使用新的 {@link JeecgOssProperties} 和 {@link org.jeecg.common.oss.OssService}
 * @author jeecg-boot
 */
@Slf4j
@Data
@Deprecated
@Configuration
@ConditionalOnProperty(prefix = "jeecg.minio", name = "minio_url")
@ConfigurationProperties(prefix = "jeecg.minio")
public class MinioConfig {

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
     * 桶名称
     */
    private String bucketName;

    /**
     * 初始化MinioUtil静态字段（向后兼容）
     */
    @PostConstruct
    public void initMinio() {
        MinioUtil.setMinioUrl(minioUrl);
        MinioUtil.setMinioName(minioName);
        MinioUtil.setMinioPass(minioPass);
        MinioUtil.setBucketName(bucketName);
        
        log.warn("【向后兼容】MinioConfig 已被标记为过时，建议迁移到新的配置方式");
        log.info("MinIO配置初始化完成 - URL: {}, Bucket: {}", minioUrl, bucketName);
    }
}