package org.jeecg.config.desensitization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.jeecg.common.desensitization.aspect.SensitiveDataAspect;
import org.jeecg.common.desensitization.serializer.SensitiveJsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据脱敏自动配置类
 * 
 * <p>自动配置数据脱敏功能，包括：</p>
 * <ul>
 *   <li>AOP切面：拦截Controller返回值进行脱敏</li>
 *   <li>Jackson序列化器：JSON序列化时自动脱敏</li>
 * </ul>
 * 
 * <p>配置示例：</p>
 * <pre>
 * jeecg:
 *   desensitization:
 *     enabled: true                # 是否启用脱敏功能
 *     strategy: mask               # 脱敏策略：mask(掩码) | encrypt(加密)
 *     json-enabled: true           # 是否启用JSON序列化脱敏
 *     method-enabled: true         # 是否启用方法返回值脱敏
 *     mask:
 *       phone-pattern: "^(\\d{3})\\d{4}(\\d{4})$"
 *       phone-replacement: "$1****$2"
 * </pre>
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@Configuration
@ConditionalOnProperty(prefix = "jeecg.desensitization", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(JeecgDesensitizationProperties.class)
public class JeecgDesensitizationAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(JeecgDesensitizationAutoConfiguration.class);

    private final JeecgDesensitizationProperties properties;

    public JeecgDesensitizationAutoConfiguration(JeecgDesensitizationProperties properties) {
        this.properties = properties;
        log.info("=========================");
        log.info("数据脱敏模块加载成功");
        log.info("脱敏策略: {}", properties.getStrategy());
        log.info("JSON脱敏: {}", properties.getJsonEnabled() ? "启用" : "禁用");
        log.info("方法脱敏: {}", properties.getMethodEnabled() ? "启用" : "禁用");
        log.info("=========================");
    }

    /**
     * 注册数据脱敏AOP切面
     * 
     * <p>拦截Controller方法返回值，对标注了@Sensitive注解的字段进行脱敏</p>
     */
    @Bean
    @ConditionalOnProperty(prefix = "jeecg.desensitization", name = "method-enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnMissingBean(SensitiveDataAspect.class)
    public SensitiveDataAspect sensitiveDataAspect() {
        log.info("注册数据脱敏AOP切面");
        return new SensitiveDataAspect();
    }

    /**
     * 配置Jackson序列化器
     * 
     * <p>为Jackson的ObjectMapper注册自定义序列化器，支持@Sensitive注解的自动脱敏</p>
     */
    @Bean
    @ConditionalOnClass(ObjectMapper.class)
    @ConditionalOnProperty(prefix = "jeecg.desensitization", name = "json-enabled", havingValue = "true", matchIfMissing = true)
    public JacksonDesensitizationCustomizer jacksonDesensitizationCustomizer() {
        log.info("注册Jackson数据脱敏序列化器");
        return new JacksonDesensitizationCustomizer();
    }

    /**
     * Jackson脱敏定制器
     */
    static class JacksonDesensitizationCustomizer {

        /**
         * 定制ObjectMapper，添加脱敏序列化器
         */
        public void customize(ObjectMapper objectMapper) {
            SimpleModule module = new SimpleModule("SensitiveModule");
            module.addSerializer(String.class, new SensitiveJsonSerializer());
            objectMapper.registerModule(module);
        }
    }
}