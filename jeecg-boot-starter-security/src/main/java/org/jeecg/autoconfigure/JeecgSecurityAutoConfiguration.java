package org.jeecg.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Jeecg Security 自动配置类
 * 
 * <p>当前版本为简化版本，仅提供配置属性支持。</p>
 * <p>完整的Shiro配置类（ShiroConfig, ShiroRealm等）已备份至backup-phase17目录，
 * 将在完成所有依赖模块后恢复。</p>
 * 
 * <h3>备份原因：</h3>
 * <ul>
 *   <li>缺少LoginUser, RedisUtil等依赖类（来自未完成的模块）</li>
 *   <li>Servlet API不兼容（javax vs jakarta）</li>
 *   <li>跨Starter依赖（需要mybatis-plus starter的TenantContext）</li>
 * </ul>
 * 
 * @author jeecg-boot
 * @version 1.0-SNAPSHOT
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "jeecg.security", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(JeecgSecurityProperties.class)
public class JeecgSecurityAutoConfiguration {
    
    /**
     * 当前仅提供配置属性绑定
     * 
     * <p>TODO: 在Phase 20+恢复以下配置：</p>
     * <ul>
     *   <li>ShiroConfig - Shiro安全配置</li>
     *   <li>ShiroRealm - 认证授权实现</li>
     *   <li>JwtFilter - JWT Token过滤器</li>
     *   <li>ResourceCheckFilter - 资源权限检查</li>
     *   <li>IgnoreAuthPostProcessor - 免认证URL处理器</li>
     * </ul>
     */
    
}