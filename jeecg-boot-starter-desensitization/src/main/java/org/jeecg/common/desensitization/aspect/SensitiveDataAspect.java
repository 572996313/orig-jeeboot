package org.jeecg.common.desensitization.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jeecg.common.desensitization.annotation.Sensitive;
import org.jeecg.common.desensitization.util.SensitiveInfoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * 数据脱敏AOP切面
 * 拦截Controller方法返回值，对标注了@Sensitive注解的字段进行脱敏处理
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@Aspect
@Component
public class SensitiveDataAspect {

    private static final Logger log = LoggerFactory.getLogger(SensitiveDataAspect.class);

    /**
     * 定义切入点：拦截所有Controller的方法
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) " +
              "|| within(@org.springframework.stereotype.Controller *)")
    public void controllerPointcut() {
    }

    /**
     * 环绕通知：处理返回值脱敏
     */
    @Around("controllerPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 执行原方法
        Object result = point.proceed();
        
        if (result == null) {
            return null;
        }

        try {
            // 处理返回值脱敏
            desensitize(result);
        } catch (Exception e) {
            log.error("数据脱敏处理异常", e);
        }

        return result;
    }

    /**
     * 递归处理对象脱敏
     */
    private void desensitize(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return;
        }

        Class<?> clazz = obj.getClass();
        
        // 处理集合类型
        if (obj instanceof Collection) {
            Collection<?> collection = (Collection<?>) obj;
            for (Object item : collection) {
                desensitize(item);
            }
            return;
        }

        // 处理数组类型
        if (clazz.isArray()) {
            Object[] array = (Object[]) obj;
            for (Object item : array) {
                desensitize(item);
            }
            return;
        }

        // 跳过Java基础类型和包装类
        if (clazz.isPrimitive() || 
            clazz.getName().startsWith("java.") || 
            clazz.getName().startsWith("javax.")) {
            return;
        }

        // 处理对象字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            
            // 检查字段是否有@Sensitive注解
            Sensitive sensitive = field.getAnnotation(Sensitive.class);
            if (sensitive != null && field.getType() == String.class) {
                // 获取字段值
                String value = (String) field.get(obj);
                if (value != null && !value.isEmpty()) {
                    // 脱敏处理
                    String desensitizedValue;
                    if (sensitive.type().name().equals("CUSTOM")) {
                        desensitizedValue = SensitiveInfoUtil.desensitizeCustom(
                            value, 
                            sensitive.regex(), 
                            sensitive.replacement()
                        );
                    } else {
                        desensitizedValue = SensitiveInfoUtil.desensitize(value, sensitive.type());
                    }
                    
                    // 设置脱敏后的值
                    field.set(obj, desensitizedValue);
                    
                    if (log.isDebugEnabled()) {
                        log.debug("字段脱敏: {}.{} = {} -> {}", 
                            clazz.getSimpleName(), 
                            field.getName(), 
                            value, 
                            desensitizedValue
                        );
                    }
                }
            } else {
                // 递归处理嵌套对象
                Object fieldValue = field.get(obj);
                if (fieldValue != null) {
                    desensitize(fieldValue);
                }
            }
        }
    }
}