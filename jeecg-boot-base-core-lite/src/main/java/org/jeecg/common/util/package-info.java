/**
 * Spring上下文工具包
 * 
 * <p>提供Spring容器相关的工具类：</p>
 * <ul>
 *   <li>{@link org.jeecg.common.util.SpringContextUtils} - Spring上下文工具类，用于获取Bean实例</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 获取Bean实例
 * ISysUserService userService = SpringContextUtils.getBean(ISysUserService.class);
 * 
 * // 根据名称获取Bean
 * Object bean = SpringContextUtils.getBean("sysUserService");
 * 
 * // 获取ApplicationContext
 * ApplicationContext context = SpringContextUtils.getApplicationContext();
 * }</pre>
 * 
 * @since 4.0.0
 * @author JeecgBoot Team
 */
package org.jeecg.common.util;