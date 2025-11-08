/**
 * JWT 工具包
 * <p>
 * 本包提供 JWT (JSON Web Token) 令牌的生成、验证和解析功能
 * 
 * <h2>核心组件</h2>
 * <ul>
 *   <li>{@link org.jeecg.common.system.util.JwtUtil} - JWT 令牌工具类</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 1. 生成 Token
 * String username = "admin";
 * String token = JwtUtil.sign(username, "secret-key");
 * 
 * // 2. 验证 Token
 * boolean valid = JwtUtil.verify(token, username, "secret-key");
 * 
 * // 3. 获取用户名
 * String user = JwtUtil.getUsername(token);
 * }</pre>
 * 
 * @author llllxf
 * @since 4.0.0
 */
package org.jeecg.common.system.util;