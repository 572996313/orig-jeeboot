/**
 * Jeecg Security 自动配置包
 * <p>
 * 本包提供 Jeecg Security Starter 的自动配置
 * 
 * <h2>核心组件</h2>
 * <ul>
 *   <li>{@link org.jeecg.config.security.JeecgSecurityAutoConfiguration} - 安全认证自动配置</li>
 *   <li>{@link org.jeecg.config.security.JeecgSecurityProperties} - 安全认证配置属性</li>
 * </ul>
 * 
 * <h2>配置示例</h2>
 * <pre>{@code
 * # application.yml
 * jeecg:
 *   security:
 *     enabled: true
 *     shiro:
 *       enabled: true
 *       exclude-urls: /sys/login,/sys/logout,/public/**
 *       url-permission-enabled: false
 *     jwt:
 *       secret: ${JWT_SECRET:jiangbo-secret-key}
 *       expire-time: 604800  # 7天（秒）
 *       token-header: X-Access-Token
 * }</pre>
 * 
 * <h2>使用方式</h2>
 * <p>
 * 只需在项目中添加依赖，Spring Boot 会自动配置：
 * </p>
 * <pre>{@code
 * <dependency>
 *     <groupId>org.jeecgframework.boot3</groupId>
 *     <artifactId>jeecg-boot-starter-security</artifactId>
 * </dependency>
 * }</pre>
 * 
 * @author llllxf
 * @since 4.0.0
 */
package org.jeecg.config.security;