/**
 * Apache Shiro 安全框架配置包
 * <p>
 * 本包提供基于 Apache Shiro 的安全认证和授权配置
 * 
 * <h2>核心组件</h2>
 * <ul>
 *   <li>{@link org.jeecg.config.shiro.ShiroConfig} - Shiro 核心配置类</li>
 *   <li>{@link org.jeecg.config.shiro.ShiroRealm} - Shiro 认证授权域</li>
 *   <li>{@link org.jeecg.config.shiro.JwtToken} - JWT 令牌封装</li>
 *   <li>{@link org.jeecg.config.shiro.IgnoreAuth} - 忽略认证注解</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 1. 在 Controller 方法上使用忽略认证注解
 * @IgnoreAuth
 * @GetMapping("/public/info")
 * public Result<?> publicInfo() {
 *     return Result.OK("公开接口");
 * }
 * 
 * // 2. 配置排除URL
 * jeecg:
 *   security:
 *     shiro:
 *       exclude-urls: /sys/login,/sys/logout,/public/**
 * }</pre>
 * 
 * @author llllxf
 * @since 4.0.0
 */
package org.jeecg.config.shiro;