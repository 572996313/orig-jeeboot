/**
 * 基础配置包
 * 
 * <p>提供JeecgBoot框架的基础配置类：</p>
 * <ul>
 *   <li>{@link org.jeecg.config.JeecgBaseConfig} - Jeecg基础配置</li>
 *   <li>{@link org.jeecg.config.RestTemplateConfig} - RestTemplate配置</li>
 *   <li>{@link org.jeecg.config.StaticConfig} - 静态配置</li>
 * </ul>
 * 
 * <h2>使用说明</h2>
 * <p>这些配置类由Spring Boot自动加载，无需手动配置。</p>
 * 
 * <h2>自定义配置</h2>
 * <pre>{@code
 * // 在application.yml中配置
 * jeecg:
 *   path:
 *     upload: /opt/upload
 *     webapp: /opt/webapp
 * }</pre>
 * 
 * @since 4.0.0
 * @author JeecgBoot Team
 */
package org.jeecg.config;