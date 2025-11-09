package org.jeecg.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jeecg.config.web.JeecgWebProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据权限切面
 * 根据用户权限自动过滤数据
 *
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-01-08
 */
@Aspect
public class PermissionDataAspect {

    private static final Logger log = LoggerFactory.getLogger(PermissionDataAspect.class);

    private final JeecgWebProperties.PermissionConfig config;

    public PermissionDataAspect(JeecgWebProperties.PermissionConfig config) {
        this.config = config;
        log.info("数据权限切面初始化 - 严格模式: {}", config.isStrict());
    }

    /**
     * 切点：带有@PermissionData注解的方法
     */
    @Pointcut("@annotation(org.jeecg.common.aspect.annotation.PermissionData)")
    public void permissionDataPointcut() {
    }

    /**
     * 环绕通知 - 数据权限过滤
     */
    @Around("permissionDataPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        if (!config.isEnabled()) {
            return point.proceed();
        }

        // 获取当前用户权限信息
        String userId = getCurrentUserId();
        String deptId = getCurrentDeptId();
        
        log.debug("数据权限过滤 - 用户: {}, 部门: {}", userId, deptId);

        // TODO: 实际实现中需要：
        // 1. 解析@PermissionData注解参数
        // 2. 获取用户数据权限规则
        // 3. 动态修改SQL（通过MyBatis拦截器）
        // 4. 或在方法执行后过滤结果集

        // 执行方法
        Object result = point.proceed();

        // 如果是严格模式，验证返回数据是否符合权限
        if (config.isStrict()) {
            result = validateDataPermission(result, userId, deptId);
        }

        return result;
    }

    /**
     * 获取当前用户ID
     */
    private String getCurrentUserId() {
        // 从SecurityContext或Session中获取
        // 这里简化处理
        return "current_user_id";
    }

    /**
     * 获取当前部门ID
     */
    private String getCurrentDeptId() {
        // 从SecurityContext或Session中获取
        // 这里简化处理
        return "current_dept_id";
    }

    /**
     * 验证数据权限
     */
    private Object validateDataPermission(Object result, String userId, String deptId) {
        // 实际实现中需要验证返回数据是否符合用户权限
        // 这里简化处理
        log.debug("验证数据权限 - 用户: {}, 部门: {}", userId, deptId);
        return result;
    }

    /**
     * 判断表是否排除
     */
    private boolean isTableExcluded(String tableName) {
        return config.getExcludeTables().stream()
                .anyMatch(pattern -> tableName.matches(pattern));
    }
}