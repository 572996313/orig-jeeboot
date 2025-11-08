/**
 * SQL解析工具包
 * 
 * <p>基于JSqlParser库提供SQL解析和分析功能，支持表名提取、SQL改写等。</p>
 * 
 * <h2>核心类</h2>
 * <ul>
 *   <li>{@link org.jeecg.common.util.sqlparse.JSqlParserUtils} - SQL解析工具类</li>
 *   <li>{@link org.jeecg.common.util.sqlparse.JSqlParserAllTableManager} - SQL表管理器</li>
 *   <li>{@link org.jeecg.common.util.sqlparse.SelectSqlInfo} - SELECT语句信息</li>
 * </ul>
 * 
 * <h2>提取表名示例</h2>
 * <pre>{@code
 * String sql = "SELECT u.*, d.name FROM sys_user u LEFT JOIN sys_depart d ON u.depart_id = d.id WHERE u.status = 1";
 * 
 * // 提取所有表名
 * List<String> tables = JSqlParserUtils.getAllTableNameList(sql);
 * // 结果: ["sys_user", "sys_depart"]
 * }</pre>
 * 
 * <h2>SQL改写示例</h2>
 * <pre>{@code
 * String sql = "SELECT * FROM sys_user WHERE username = ?";
 * 
 * // 添加租户条件
 * String rewriteSql = JSqlParserUtils.addCondition(sql, "tenant_id = '1001'");
 * // 结果: SELECT * FROM sys_user WHERE username = ? AND tenant_id = '1001'
 * }</pre>
 * 
 * <h2>解析SELECT信息</h2>
 * <pre>{@code
 * String sql = "SELECT id, username, create_time FROM sys_user";
 * SelectSqlInfo info = JSqlParserUtils.parseSelectSql(sql);
 * 
 * List<String> columns = info.getSelectFields(); // ["id", "username", "create_time"]
 * String tableName = info.getFromTableName();    // "sys_user"
 * }</pre>
 * 
 * <h2>特点</h2>
 * <ul>
 *   <li>支持标准SQL语法解析</li>
 *   <li>支持复杂JOIN查询</li>
 *   <li>支持子查询和联合查询</li>
 *   <li>支持SQL动态改写</li>
 *   <li>线程安全</li>
 * </ul>
 * 
 * <h2>依赖</h2>
 * <pre>{@code
 * <dependency>
 *     <groupId>com.github.jsqlparser</groupId>
 *     <artifactId>jsqlparser</artifactId>
 *     <scope>provided</scope>
 *     <optional>true</optional>
 * </dependency>
 * }</pre>
 * 
 * @author llllxf
 * @version 4.0.0-SNAPSHOT
 * @since 2025-11-08
 */
package org.jeecg.common.util.sqlparse;