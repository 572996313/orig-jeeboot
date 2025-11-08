/**
 * MyBatis-Plus 配置包
 * <p>
 * 提供 MyBatis-Plus 增强功能的自动配置:
 * <ul>
 *   <li>{@link org.jeecg.config.mybatis.JeecgMybatisPlusAutoConfiguration} - MyBatis-Plus自动配置</li>
 *   <li>{@link org.jeecg.config.mybatis.JeecgMybatisPlusProperties} - 配置属性</li>
 *   <li>{@link org.jeecg.config.mybatis.MybatisPlusSaasConfig} - SaaS多租户配置</li>
 *   <li>{@link org.jeecg.config.mybatis.MybatisInterceptor} - MyBatis拦截器</li>
 *   <li>{@link org.jeecg.config.mybatis.JeecgTenantParser} - 租户解析器</li>
 *   <li>{@link org.jeecg.config.mybatis.TenantContext} - 租户上下文</li>
 *   <li>{@link org.jeecg.config.mybatis.ThreadLocalDataHelper} - 线程本地数据助手</li>
 * </ul>
 * 
 * <h2>核心功能</h2>
 * <ul>
 *   <li><b>多租户支持</b> - 自动根据租户ID隔离数据</li>
 *   <li><b>动态表名</b> - 支持运行时动态切换表名</li>
 *   <li><b>分页增强</b> - 自动分页插件，防止内存溢出</li>
 *   <li><b>乐观锁</b> - 自动处理version字段</li>
 *   <li><b>SQL拦截</b> - 性能监控、SQL美化</li>
 *   <li><b>数据权限</b> - 基于注解的数据过滤</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 1. 添加依赖
 * <dependency>
 *     <groupId>org.jeecgframework.boot</groupId>
 *     <artifactId>jeecg-boot-starter-mybatis-plus</artifactId>
 * </dependency>
 * 
 * // 2. 配置 application.yml
 * jeecg:
 *   mybatis-plus:
 *     enable: true
 *     tenant:
 *       enable: true
 *       column: tenant_id
 *       tables:
 *         - sys_user
 *         - sys_role
 *     pagination:
 *       enable: true
 *       max-limit: 500
 * 
 * // 3. 使用 Mapper
 * @Mapper
 * public interface UserMapper extends BaseMapper<User> {
 * }
 * 
 * // 4. 使用 Service
 * @Service
 * public class UserServiceImpl extends ServiceImpl<UserMapper, User> 
 *         implements IUserService {
 * }
 * }</pre>
 * 
 * @author jeecg
 * @version 4.0.0
 * @since 2025-11-08
 */
package org.jeecg.config.mybatis;