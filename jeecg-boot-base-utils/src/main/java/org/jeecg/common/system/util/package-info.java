/**
 * 系统工具包
 * 
 * <p>提供系统级通用工具类，包括JWT工具和数据权限工具。</p>
 * 
 * <h2>核心类</h2>
 * <ul>
 *   <li>{@link org.jeecg.common.system.util.JwtUtil} - JWT令牌工具类</li>
 *   <li>{@link org.jeecg.common.system.util.JeecgDataAutorUtils} - 数据权限工具类</li>
 * </ul>
 * 
 * <h2>JwtUtil 使用示例</h2>
 * <pre>{@code
 * // 生成JWT令牌
 * String token = JwtUtil.sign("admin", "my-secret-key");
 * 
 * // 验证JWT令牌
 * boolean isValid = JwtUtil.verify(token, "admin", "my-secret-key");
 * 
 * // 解析JWT获取用户名
 * String username = JwtUtil.getUsername(token);
 * }</pre>
 * 
 * <h2>JeecgDataAutorUtils 使用示例</h2>
 * <pre>{@code
 * // 根据权限规则过滤查询条件
 * QueryWrapper<User> queryWrapper = new QueryWrapper<>();
 * JeecgDataAutorUtils.installDataSearchCondition(
 *     queryWrapper,
 *     loginUser.getId(),
 *     dataRule
 * );
 * }</pre>
 * 
 * <h2>特点</h2>
 * <ul>
 *   <li>JwtUtil采用纯静态方法，无Spring依赖</li>
 *   <li>支持HS256算法签名</li>
 *   <li>支持自定义过期时间</li>
 *   <li>数据权限工具与QueryWrapper集成</li>
 * </ul>
 * 
 * @author llllxf
 * @version 4.0.0-SNAPSHOT
 * @since 2025-11-08
 */
package org.jeecg.common.system.util;