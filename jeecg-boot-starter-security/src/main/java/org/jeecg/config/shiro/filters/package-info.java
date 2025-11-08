/**
 * Shiro 过滤器包
 * <p>
 * 本包提供 Shiro 安全框架的自定义过滤器
 * 
 * <h2>核心组件</h2>
 * <ul>
 *   <li>{@link org.jeecg.config.shiro.filters.JwtFilter} - JWT 认证过滤器</li>
 *   <li>{@link org.jeecg.config.shiro.filters.ResourceCheckFilter} - 资源权限检查过滤器</li>
 *   <li>{@link org.jeecg.config.shiro.filters.CustomShiroFilterFactoryBean} - 自定义 Shiro 过滤器工厂</li>
 * </ul>
 * 
 * <h2>工作原理</h2>
 * <p>
 * JwtFilter 在每次请求时验证 JWT Token 的有效性，
 * 并将用户信息注入到 Shiro 的 Subject 中。
 * </p>
 * 
 * @author llllxf
 * @since 4.0.0
 */
package org.jeecg.config.shiro.filters;