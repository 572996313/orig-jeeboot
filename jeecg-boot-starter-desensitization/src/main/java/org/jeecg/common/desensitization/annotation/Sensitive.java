package org.jeecg.common.desensitization.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jeecg.common.desensitization.enums.SensitiveType;
import org.jeecg.common.desensitization.serializer.SensitiveJsonSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 敏感信息脱敏注解
 * 用于标记需要脱敏的字段
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveJsonSerializer.class)
public @interface Sensitive {
    
    /**
     * 敏感信息类型
     */
    SensitiveType type() default SensitiveType.CUSTOM;
    
    /**
     * 自定义正则表达式（当type为CUSTOM时使用）
     */
    String regex() default "";
    
    /**
     * 自定义替换规则（当type为CUSTOM时使用）
     */
    String replacement() default "****";
}