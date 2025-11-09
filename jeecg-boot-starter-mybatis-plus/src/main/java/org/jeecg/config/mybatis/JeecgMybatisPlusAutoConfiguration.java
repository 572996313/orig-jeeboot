package org.jeecg.config.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * MyBatis-Plus 自动配置 (简化版)
 * 
 * 本类为Phase 17.3临时简化版本，仅包含基础配置
 * Phase 20将恢复完整功能：租户、动态表、动态数据源、BaseMapper等
 * 
 * @author jeecg
 * @version 4.0.0
 */
@Slf4j
@AutoConfiguration
@ConditionalOnProperty(prefix = "jeecg.mybatis-plus", name = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(JeecgMybatisPlusProperties.class)
@MapperScan("org.jeecg.modules.*.mapper")
@Import({
    MybatisPlusSaasConfig.class,
    ThreadLocalDataHelper.class
})
public class JeecgMybatisPlusAutoConfiguration {
    
    private final JeecgMybatisPlusProperties properties;
    
    public JeecgMybatisPlusAutoConfiguration(JeecgMybatisPlusProperties properties) {
        this.properties = properties;
        log.info("=============== Jeecg MyBatis-Plus 自动配置初始化 (简化版) ===============");
        log.info(">>> 配置状态: 启用={}", properties.isEnable());
        log.info("=============== 配置完成 ===============");
    }
}