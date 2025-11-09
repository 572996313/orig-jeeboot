package org.jeecg.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * CORS过滤器条件判断
 * <p>用于判断是否需要配置CORS跨域</p>
 *
 * @author jeecg
 */
public class CorsFilterCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 检查配置文件中是否启用了CORS
        String corsEnabled = context.getEnvironment().getProperty("jeecg.cors.enable");
        return "true".equalsIgnoreCase(corsEnabled);
    }
}