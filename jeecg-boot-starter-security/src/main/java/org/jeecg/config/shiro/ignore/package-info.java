/**
 * Shiro 认证忽略处理包
 * <p>
 * 本包提供忽略认证的注解和处理器
 * 
 * <h2>核心组件</h2>
 * <ul>
 *   <li>{@link org.jeecg.config.shiro.ignore.InMemoryIgnoreAuth} - 内存中的忽略认证管理</li>
 *   <li>{@link org.jeecg.config.shiro.ignore.IgnoreAuthPostProcessor} - 忽略认证注解后处理器</li>
 * </ul>
 * 
 * <h2>工作原理</h2>
 * <p>
 * 扫描所有标注了 @IgnoreAuth 注解的方法，
 * 将其 URL 路径添加到忽略认证列表中。
 * </p>
 * 
 * @author llllxf
 * @since 4.0.0
 */
package org.jeecg.config.shiro.ignore;