/**
 * 系统注解包
 * 
 * <p>提供JeecgBoot系统级注解：</p>
 * <ul>
 *   <li>{@link org.jeecg.common.system.annotation.EnumDict} - 枚举字典注解</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 在字段上使用枚举字典注解
 * public class SysUser {
 *     @EnumDict("user_status")
 *     private Integer status;
 * }
 * }</pre>
 * 
 * @since 4.0.0
 * @author JeecgBoot Team
 */
package org.jeecg.common.system.annotation;