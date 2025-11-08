package org.jeecg.config.oss;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.oss.OssService;
import org.jeecg.common.oss.impl.AliyunOssServiceImpl;
import org.jeecg.common.oss.impl.LocalOssServiceImpl;
import org.jeecg.common.oss.impl.MinioOssServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OSS自动配置类
 *
 * @author jeecg-boot
 * @since 4.0.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(JeecgOssProperties.class)
@ConditionalOnProperty(prefix = "jeecg.oss", name = "enabled", havingValue = "true", matchIfMissing = true)
public class JeecgOssAutoConfiguration {

    /**
     * MinIO客户端配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "jeecg.oss", name = "type", havingValue = "minio")
    @ConditionalOnMissingBean
    public MinioClient minioClient(JeecgOssProperties ossProperties) {
        try {
            String endpoint = ossProperties.getActualEndpoint();
            String accessKey = ossProperties.getActualAccessKey();
            String secretKey = ossProperties.getActualSecretKey();

            if (endpoint == null || accessKey == null || secretKey == null) {
                throw new IllegalArgumentException("MinIO配置不完整，请检查 jeecg.oss.endpoint、accessKey、secretKey");
            }

            MinioClient client = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(accessKey, secretKey)
                    .build();

            log.info("MinIO客户端初始化成功: {}", endpoint);
            return client;
        } catch (Exception e) {
            log.error("MinIO客户端初始化失败: {}", e.getMessage(), e);
            throw new RuntimeException("MinIO客户端初始化失败", e);
        }
    }

    /**
     * MinIO服务配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "jeecg.oss", name = "type", havingValue = "minio")
    @ConditionalOnMissingBean
    public OssService minioOssService(MinioClient minioClient, JeecgOssProperties ossProperties) {
        log.info("启用MinIO对象存储服务");
        return new MinioOssServiceImpl(minioClient, ossProperties);
    }

    /**
     * 阿里云OSS客户端配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "jeecg.oss", name = "type", havingValue = "aliyun")
    @ConditionalOnMissingBean
    public OSSClient ossClient(JeecgOssProperties ossProperties) {
        try {
            String endpoint = ossProperties.getActualEndpoint();
            String accessKeyId = ossProperties.getActualAccessKey();
            String accessKeySecret = ossProperties.getActualSecretKey();

            if (endpoint == null || accessKeyId == null || accessKeySecret == null) {
                throw new IllegalArgumentException("阿里云OSS配置不完整，请检查 jeecg.oss.endpoint、accessKey、secretKey");
            }

            OSSClient client = new OSSClient(
                    endpoint,
                    new DefaultCredentialProvider(accessKeyId, accessKeySecret),
                    new ClientConfiguration()
            );

            log.info("阿里云OSS客户端初始化成功: {}", endpoint);
            return client;
        } catch (Exception e) {
            log.error("阿里云OSS客户端初始化失败: {}", e.getMessage(), e);
            throw new RuntimeException("阿里云OSS客户端初始化失败", e);
        }
    }

    /**
     * 阿里云OSS服务配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "jeecg.oss", name = "type", havingValue = "aliyun")
    @ConditionalOnMissingBean
    public OssService aliyunOssService(OSSClient ossClient, JeecgOssProperties ossProperties) {
        log.info("启用阿里云OSS对象存储服务");
        return new AliyunOssServiceImpl(ossClient, ossProperties);
    }

    /**
     * 本地存储服务配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "jeecg.oss", name = "type", havingValue = "local", matchIfMissing = false)
    @ConditionalOnMissingBean
    public OssService localOssService(JeecgOssProperties ossProperties) {
        log.info("启用本地文件存储服务，路径: {}", ossProperties.getLocalPath());
        return new LocalOssServiceImpl(ossProperties);
    }

    /**
     * 向后兼容：MinIO配置类
     */
    @Bean
    @ConditionalOnProperty(prefix = "jeecg.oss", name = "type", havingValue = "minio")
    @ConditionalOnMissingBean(MinioConfig.class)
    public MinioConfig minioConfig(JeecgOssProperties ossProperties) {
        MinioConfig config = new MinioConfig();
        config.setMinioUrl(ossProperties.getActualEndpoint());
        config.setMinioName(ossProperties.getActualAccessKey());
        config.setMinioPass(ossProperties.getActualSecretKey());
        config.setBucketName(ossProperties.getActualBucketName());
        log.info("向后兼容：MinioConfig初始化完成");
        return config;
    }

    /**
     * 向后兼容：阿里云OSS配置类
     */
    @Bean
    @ConditionalOnProperty(prefix = "jeecg.oss", name = "type", havingValue = "aliyun")
    @ConditionalOnMissingBean(OssConfiguration.class)
    public OssConfiguration ossConfiguration(JeecgOssProperties ossProperties) {
        OssConfiguration config = new OssConfiguration();
        config.setEndPoint(ossProperties.getActualEndpoint());
        config.setAccessKeyId(ossProperties.getActualAccessKey());
        config.setAccessKeySecret(ossProperties.getActualSecretKey());
        config.setBucketName(ossProperties.getActualBucketName());
        config.setStaticDomain(ossProperties.getStaticDomain());
        log.info("向后兼容：OssConfiguration初始化完成");
        return config;
    }
}