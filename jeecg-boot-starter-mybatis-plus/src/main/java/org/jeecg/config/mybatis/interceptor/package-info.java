/**
 * MyBatis 拦截器包
 * <p>
 * 提供 MyBatis SQL 拦截器:
 * <ul>
 *   <li>{@link org.jeecg.config.mybatis.interceptor.DynamicDatasourceInterceptor} - 动态数据源拦截器</li>
 * </ul>
 * 
 * <h2>动态数据源切换</h2>
 * <p>支持在 SQL 执行前动态切换数据源，实现读写分离、多数据源路由等功能</p>
 * <pre>{@code
 * // 设置动态数据源
 * DynamicDataSourceContextHolder.setDataSourceType("slave");
 * try {
 *     // 执行查询（使用从库）
 *     List<User> users = userMapper.selectList(null);
 * } finally {
 *     // 清除数据源设置
 *     DynamicDataSourceContextHolder.clearDataSourceType();
 * }
 * }</pre>
 * 
 * @author jeecg
 * @version 4.0.0
 */
package org.jeecg.config.mybatis.interceptor;