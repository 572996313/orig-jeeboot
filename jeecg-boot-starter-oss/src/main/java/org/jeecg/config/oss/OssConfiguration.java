package org.jeecg.config.oss;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.oss.OssBootUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 阿里云OSS配置类（向后兼容）
 * 
 * @deprecated 请使用新的 {@link JeecgOssProperties} 和 {@link org.jeecg.common.oss.OssService}
 * @author jeecg-boot
 */
@Slf4j
@Data
@Deprecated
@Configuration
@ConditionalOnProperty(prefix = "jeecg.oss", name = "endpoint")
@ConfigurationProperties(prefix = "jeecg.oss")
public class OssConfiguration {

    /**
     * OSS服务端点
     */
    private String endPoint;

    /**
     * 访问密钥ID
     */
    private String accessKeyId;

    /**
     * 访问密钥Secret
     */
    private String accessKeySecret;

    /**
     * 桶名称
     */
    private String bucketName;

    /**
     * 静态访问域名
     */
    private String staticDomain;

    /**
     * 初始化OssBootUtil静态字段（向后兼容）
     */
    @PostConstruct
    public void initOssBootConfiguration() {
        OssBootUtil.setEndPoint(endPoint);
        OssBootUtil.setAccessKeyId(accessKeyId);
        OssBootUtil.setAccessKeySecret(accessKeySecret);
        OssBootUtil.setBucketName(bucketName);
        OssBootUtil.setStaticDomain(staticDomain);
        
        log.warn("【向后兼容】OssConfiguration 已被标记为过时，建议迁移到新的配置方式");
        log.info("阿里云OSS配置初始化完成 - EndPoint: {}, Bucket: {}", endPoint, bucketName);
    }
}