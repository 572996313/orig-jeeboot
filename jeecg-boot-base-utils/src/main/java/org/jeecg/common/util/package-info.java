/**
 * JeecgBoot 工具类包
 * 
 * <p>本包提供了丰富的Java工具类，涵盖以下领域：</p>
 * 
 * <h2>主要功能模块</h2>
 * 
 * <h3>1. 基础工具类</h3>
 * <ul>
 *   <li>{@link org.jeecg.common.util.DateUtils} - 日期处理工具</li>
 *   <li>{@link org.jeecg.common.util.CommonUtils} - 通用工具方法</li>
 *   <li>{@link org.jeecg.common.util.oConvertUtils} - 类型转换工具</li>
 *   <li>{@link org.jeecg.common.util.UUIDGenerator} - UUID生成器</li>
 * </ul>
 * 
 * <h3>2. 加密安全</h3>
 * <ul>
 *   <li>{@link org.jeecg.common.util.Md5Util} - MD5加密</li>
 *   <li>{@link org.jeecg.common.util.PasswordUtil} - 密码加密工具</li>
 *   <li>{@link org.jeecg.common.util.encryption} - AES加密子包</li>
 *   <li>{@link org.jeecg.common.util.security} - 安全工具子包</li>
 * </ul>
 * 
 * <h3>3. SQL相关</h3>
 * <ul>
 *   <li>{@link org.jeecg.common.util.SqlInjectionUtil} - SQL注入防护</li>
 *   <li>{@link org.jeecg.common.util.sqlparse} - SQL解析子包</li>
 * </ul>
 * 
 * <h3>4. 其他工具</h3>
 * <ul>
 *   <li>{@link org.jeecg.common.util.IpUtils} - IP地址工具</li>
 *   <li>{@link org.jeecg.common.util.BrowserUtils} - 浏览器识别</li>
 *   <li>{@link org.jeecg.common.util.ReflectHelper} - 反射工具</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 日期处理
 * String today = DateUtils.getToday();
 * Date date = DateUtils.parseDate("2025-11-08");
 * 
 * // 加密
 * String md5 = Md5Util.md5("password");
 * String encrypted = PasswordUtil.encrypt("user", "pwd", "salt");
 * 
 * // UUID生成
 * String uuid = UUIDGenerator.generate();
 * 
 * // SQL注入防护
 * String safe = SqlInjectionUtil.filterContent(userInput);
 * }</pre>
 * 
 * <h2>设计原则</h2>
 * <ul>
 *   <li><b>无状态</b>：所有工具方法为静态方法，线程安全</li>
 *   <li><b>零Spring依赖</b>：可用于任何Java项目</li>
 *   <li><b>高性能</b>：无实例化开销</li>
 * </ul>
 * 
 * @author llllxf
 * @version 4.0.0-SNAPSHOT
 * @since 2025-11-08
 */
package org.jeecg.common.util;