package org.jeecg.config.oss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * OSS自动配置类（简化版）
 * 
 * 完整功能将在 Phase 20 恢复
 * 
 * @author jeecg-boot
 * @since 4.0.0
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(JeecgOssProperties.class)
@ConditionalOnProperty(prefix = "jeecg.oss", name = "enabled", havingValue = "true", matchIfMissing = true)
public class JeecgOssAutoConfiguration {
    
    private final JeecgOssProperties ossProperties;
    
    public JeecgOssAutoConfiguration(JeecgOssProperties ossProperties) {
        this.ossProperties = ossProperties;
        log.info("=============== Jeecg OSS 自动配置初始化 (简化版) ===============");
        log.info("OSS类型: {}", ossProperties.getType());
        log.info("OSS端点: {}", ossProperties.getActualEndpoint());
        log.info("存储桶: {}", ossProperties.getActualBucketName());
        log.info("本地路径: {}", ossProperties.getLocalPath());
        log.info("注意: 完整的OSS功能(MinIO/阿里云)将在 Phase 20 恢复");
        log.info("===============================================================");
    }
}