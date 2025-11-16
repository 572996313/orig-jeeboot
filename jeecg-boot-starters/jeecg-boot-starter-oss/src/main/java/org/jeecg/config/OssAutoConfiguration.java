package org.jeecg.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * OSS 自动配置类
 * @author: jeecg-boot
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(OssProperties.class)
@Import({AliOssConfiguration.class, MinioConfiguration.class})
public class OssAutoConfiguration {

    public OssAutoConfiguration() {
        log.info("=== Jeecg Boot OSS Starter Initialized ===");
    }

}