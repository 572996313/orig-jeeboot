/**
 * 过滤器工具包
 * 
 * <p>提供安全过滤工具，包括SSRF防护、XSS攻击防护等。</p>
 * 
 * <h2>核心类</h2>
 * <ul>
 *   <li>{@link org.jeecg.common.util.filter.SsrfFileTypeFilter} - SSRF文件类型过滤器</li>
 *   <li>{@link org.jeecg.common.util.filter.StrAttackFilter} - 字符串攻击过滤器</li>
 * </ul>
 * 
 * <h2>SsrfFileTypeFilter 使用示例</h2>
 * <pre>{@code
 * // 检查URL是否安全
 * String url = "http://example.com/file.jpg";
 * boolean isSafe = SsrfFileTypeFilter.checkUrl(url);
 * 
 * // 检查文件类型是否允许
 * String contentType = "image/jpeg";
 * boolean isAllowed = SsrfFileTypeFilter.isAllowedContentType(contentType);
 * }</pre>
 * 
 * <h2>StrAttackFilter 使用示例</h2>
 * <pre>{@code
 * // 过滤XSS攻击
 * String input = "<script>alert('xss')</script>";
 * String filtered = StrAttackFilter.filterXSS(input);
 * 
 * // 过滤SQL注入
 * String sql = "SELECT * FROM users WHERE id = 1 OR 1=1";
 * String safeSql = StrAttackFilter.filterSqlInjection(sql);
 * }</pre>
 * 
 * <h2>防护特点</h2>
 * <ul>
 *   <li>防止SSRF（服务器端请求伪造）攻击</li>
 *   <li>防止XSS（跨站脚本）攻击</li>
 *   <li>防止SQL注入攻击</li>
 *   <li>支持白名单和黑名单机制</li>
 *   <li>纯静态方法，无外部依赖</li>
 * </ul>
 * 
 * @author llllxf
 * @version 4.0.0-SNAPSHOT
 * @since 2025-11-08
 */
package org.jeecg.common.util.filter;