/**
 * 动态数据库工具包
 * <p>
 * 本包提供运行时动态创建和管理数据源的功能，主要用于多租户场景或需要
 * 动态切换数据源的业务场景。
 * 
 * <h2>核心组件</h2>
 * <ul>
 *   <li>{@link org.jeecg.common.util.dynamic.db.DataSourceCachePool} - 数据源缓存池</li>
 *   <li>{@link org.jeecg.common.util.dynamic.db.DynamicDBUtil} - 动态数据库工具类</li>
 *   <li>{@link org.jeecg.common.util.dynamic.db.DbTypeUtils} - 数据库类型判断工具</li>
 *   <li>{@link org.jeecg.common.util.dynamic.db.FreemarkerParseFactory} - SQL模板解析工厂</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 1. 创建动态数据源
 * DynamicDataSourceModel model = new DynamicDataSourceModel();
 * model.setDbType("mysql");
 * model.setUrl("jdbc:mysql://localhost:3306/test");
 * model.setUsername("root");
 * model.setPassword("123456");
 * 
 * DataSource dataSource = DynamicDBUtil.createDataSource(model);
 * 
 * // 2. 使用数据源缓存池
 * DataSourceCachePool pool = DataSourceCachePool.getInstance();
 * pool.putDataSource("tenant1", dataSource);
 * 
 * // 3. 获取缓存的数据源
 * DataSource cachedDs = pool.getDataSource("tenant1");
 * }</pre>
 * 
 * <h2>注意事项</h2>
 * <ul>
 *   <li>动态创建的数据源需要手动管理生命周期</li>
 *   <li>使用缓存池可以避免频繁创建和销毁数据源</li>
 *   <li>建议配置合理的缓存过期时间</li>
 * </ul>
 * 
 * @author llllxf
 * @version 1.0
 * @since 2025-11-08
 */
package org.jeecg.common.util.dynamic.db;