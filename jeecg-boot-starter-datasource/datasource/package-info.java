/**
 * Jeecg数据源自动配置包
 * <p>
 * 本包提供JeecgBoot框架的数据源自动配置功能，包括：
 * <ul>
 *   <li>Druid数据源配置与监控</li>
 *   <li>SQL防火墙配置</li>
 *   <li>CORS跨域配置</li>
 *   <li>动态数据源支持</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * # application.yml
 * jeecg:
 *   datasource:
 *     enable: true
 *     druid:
 *       stat-view-servlet: true
 *       stat-view-servlet-url-pattern: /druid/*
 *       stat-view-servlet-login-username: admin
 *       stat-view-servlet-login-password: 123456
 *     dynamic:
 *       enable: false
 *     cors:
 *       enable: true
 *       allowed-origins: "*"
 * }</pre>
 * 
 * <h2>Druid监控访问</h2>
 * <p>启用Druid监控后，可以通过以下地址访问：</p>
 * <pre>http://localhost:8080/druid/</pre>
 * 
 * @author llllxf
 * @version 1.0
 * @since 2025-11-08
 */
package org.jeecg.config.datasource;