package org.jeecg.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jeecg.config.web.JeecgWebProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 自动日志切面
 * 记录接口调用日志
 *
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-01-08
 */
@Aspect
public class AutoLogAspect {

    private static final Logger log = LoggerFactory.getLogger(AutoLogAspect.class);

    private final JeecgWebProperties.LogConfig config;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AutoLogAspect(JeecgWebProperties.LogConfig config) {
        this.config = config;
        log.info("自动日志切面初始化");
    }

    /**
     * 切点：所有Controller方法
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) || " +
              "@within(org.springframework.stereotype.Controller)")
    public void controllerPointcut() {
    }

    /**
     * 环绕通知
     */
    @Around("controllerPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        if (!config.isEnabled()) {
            return point.proceed();
        }

        HttpServletRequest request = getCurrentRequest();
        if (request == null) {
            return point.proceed();
        }

        String uri = request.getRequestURI();
        
        // 排除URL检查
        if (isExcluded(uri)) {
            return point.proceed();
        }

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String className = point.getTarget().getClass().getName();
        String methodName = method.getName();

        long startTime = System.currentTimeMillis();
        Object result = null;
        Throwable exception = null;

        try {
            // 记录请求参数
            if (config.isLogArgs()) {
                Object[] args = point.getArgs();
                log.info("请求开始 - URI: {}, Method: {}.{}, Args: {}", 
                        uri, className, methodName, formatArgs(args));
            } else {
                log.info("请求开始 - URI: {}, Method: {}.{}", uri, className, methodName);
            }

            // 执行方法
            result = point.proceed();
            return result;

        } catch (Throwable e) {
            exception = e;
            throw e;

        } finally {
            long endTime = System.currentTimeMillis();
            long executeTime = endTime - startTime;

            // 记录执行结果
            if (config.isLogTime()) {
                if (exception != null) {
                    log.error("请求异常 - URI: {}, Method: {}.{}, 耗时: {}ms, Error: {}", 
                            uri, className, methodName, executeTime, exception.getMessage());
                } else {
                    if (executeTime > config.getSlowRequestThreshold()) {
                        log.warn("慢请求警告 - URI: {}, Method: {}.{}, 耗时: {}ms", 
                                uri, className, methodName, executeTime);
                    } else {
                        log.info("请求完成 - URI: {}, Method: {}.{}, 耗时: {}ms", 
                                uri, className, methodName, executeTime);
                    }

                    // 记录返回结果
                    if (config.isLogResult() && result != null) {
                        log.debug("返回结果 - {}", formatResult(result));
                    }
                }
            }
        }
    }

    /**
     * 获取当前请求
     */
    private HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attributes = 
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }

    /**
     * 是否排除
     */
    private boolean isExcluded(String uri) {
        return config.getExcludeUrls().stream()
                .anyMatch(pattern -> uri.matches(pattern));
    }

    /**
     * 格式化参数
     */
    private String formatArgs(Object[] args) {
        if (args == null || args.length == 0) {
            return "[]";
        }
        try {
            return objectMapper.writeValueAsString(args);
        } catch (Exception e) {
            return "[无法序列化]";
        }
    }

    /**
     * 格式化结果
     */
    private String formatResult(Object result) {
        try {
            String json = objectMapper.writeValueAsString(result);
            // 限制长度
            return json.length() > 500 ? json.substring(0, 500) + "..." : json;
        } catch (Exception e) {
            return "[无法序列化]";
        }
    }
}