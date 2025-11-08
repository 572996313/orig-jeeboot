/**
 * SQL 防火墙包
 * <p>
 * 提供 SQL 安全防护功能:
 * <ul>
 *   <li>{@link org.jeecg.config.firewall.SqlInjection.IDictTableWhiteListHandler} - 字典表白名单处理接口</li>
 *   <li>{@link org.jeecg.config.firewall.SqlInjection.SysDictTableWhite} - 字典表白名单实现</li>
 * </ul>
 * 
 * <h2>字典表白名单</h2>
 * <p>防止通过字典查询接口进行 SQL 注入攻击，只允许查询白名单中的表</p>
 * <pre>{@code
 * @Autowired
 * private IDictTableWhiteListHandler whiteListHandler;
 * 
 * // 检查表是否在白名单中
 * if (whiteListHandler.isWhiteTable("sys_user")) {
 *     // 允许查询
 *     dictService.queryTableData("sys_user", "id", "username");
 * } else {
 *     // 拒绝查询
 *     throw new SecurityException("表不在白名单中");
 * }
 * }</pre>
 * 
 * <h2>配置示例</h2>
 * <pre>{@code
 * jeecg:
 *   mybatis-plus:
 *     firewall:
 *       dict-white-tables:
 *         - sys_user
 *         - sys_role
 *         - sys_depart
 * }</pre>
 * 
 * @author jeecg
 * @version 4.0.0
 */
package org.jeecg.config.firewall;