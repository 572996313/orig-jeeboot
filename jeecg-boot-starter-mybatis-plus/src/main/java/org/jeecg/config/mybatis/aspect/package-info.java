/**
 * MyBatis AOP 切面包
 * <p>
 * 提供基于 AOP 的 MyBatis 增强功能:
 * <ul>
 *   <li>{@link org.jeecg.config.mybatis.aspect.DynamicTableAspect} - 动态表名切面</li>
 * </ul>
 * 
 * <h2>动态表名</h2>
 * <p>支持运行时动态切换表名，适用于分表场景</p>
 * <pre>{@code
 * @DynamicTable(value = "sys_log", suffix = "_2024")
 * public List<SysLog> queryLogList() {
 *     // 实际查询 sys_log_2024 表
 *     return mapper.selectList(null);
 * }
 * }</pre>
 * 
 * @author jeecg
 * @version 4.0.0
 */
package org.jeecg.config.mybatis.aspect;