/**
 * 系统工具类包
 * 
 * <p>提供JeecgBoot系统级工具类：</p>
 * <ul>
 *   <li>{@link org.jeecg.common.system.util.JeecgDataAutorUtils} - 数据作者工具类</li>
 *   <li>{@link org.jeecg.common.system.util.ResourceUtil} - 资源工具类</li>
 *   <li>{@link org.jeecg.common.system.util.SqlConcatUtil} - SQL拼接工具类</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 获取资源信息
 * String content = ResourceUtil.getResourceAsString("template.ftl");
 * 
 * // SQL拼接
 * String sql = SqlConcatUtil.concat("SELECT * FROM ", tableName, " WHERE id = ", id);
 * }</pre>
 * 
 * @since 4.0.0
 * @author JeecgBoot Team
 */
package org.jeecg.common.system.util;