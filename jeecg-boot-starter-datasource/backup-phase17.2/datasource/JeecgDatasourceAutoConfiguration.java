package org.jeecg.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.dynamic.db.DataSourceCachePool;
import org.jeecg.config.filter.RequestBodyReserveFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import javax.sql.DataSource;

/**
 * Jeecg数据源自动配置类
 * <p>
 * 提供以下功能:
 * <ul>
 *   <li>Druid数据源配置与监控</li>
 *   <li>动态数据源支持</li>
 *   <li>数据源缓存池管理</li>
 *   <li>请求体保留过滤器</li>
 * </ul>
 * 
 * @author llllxf
 * @version 1.0
 * @since 2025-11-08
 */
@Slf4j
@AutoConfiguration(after = DataSourceAutoConfiguration.class)
@ConditionalOnClass(DataSource.class)
@ConditionalOnProperty(prefix = "jeecg.datasource", name = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(JeecgDatasourceProperties.class)
@Import({
    DruidConfig.class,
    DruidWallConfigRegister.class,
    CorsFilterCondition.class
})
public class JeecgDatasourceAutoConfiguration {

    private final JeecgDatasourceProperties properties;

    public JeecgDatasourceAutoConfiguration(JeecgDatasourceProperties properties) {
        this.properties = properties;
        log.info("=== Jeecg Datasource Starter 初始化开始 ===");
        log.info("Druid监控启用状态: {}", properties.getDruid().isStatViewServlet());
        log.info("动态数据源启用状态: {}", properties.getDynamic().isEnable());
        log.info("CORS跨域启用状态: {}", properties.getCors().isEnable());
    }

    /**
     * 配置数据源缓存池
     * <p>
     * 用于缓存动态创建的数据源，避免频繁创建和销毁
     * 
     * @return 数据源缓存池实例
     */
    @Bean
    @ConditionalOnProperty(prefix = "jeecg.datasource.dynamic", name = "enable", havingValue = "true")
    @ConditionalOnMissingBean
    public DataSourceCachePool dataSourceCachePool() {
        log.info("初始化数据源缓存池...");
        DataSourceCachePool cachePool = DataSourceCachePool.getInstance();
        
        // 配置缓存参数
        if (properties.getDynamic().isCache()) {
            log.info("启用数据源缓存，过期时间: {} 分钟", properties.getDynamic().getCacheExpireMinutes());
        }
        
        return cachePool;
    }

    /**
     * 配置请求体保留过滤器
     * <p>
     * 用于在Filter链中多次读取HttpServletRequest的Body内容
     * 主要用于日志记录、签名验证等场景
     * 
     * @return 过滤器注册Bean
     */
    @Bean
    @ConditionalOnMissingBean(name = "requestBodyReserveFilter")
    public FilterRegistrationBean<RequestBodyReserveFilter> requestBodyReserveFilter() {
        log.info("注册请求体保留过滤器...");
        FilterRegistrationBean<RequestBodyReserveFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RequestBodyReserveFilter());
        registration.addUrlPatterns("/*");
        registration.setName("requestBodyReserveFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    /**
     * 配置Druid数据源（如果使用Druid）
     * <p>
     * 此Bean仅在未配置其他DataSource时生效
     * 
     * @return Druid数据源
     */
    @Bean
    @ConditionalOnClass(DruidDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(prefix = "spring.datasource.druid", name = "enable", havingValue = "true", matchIfMissing = true)
    public DruidDataSource druidDataSource() {
        log.info("使用Druid数据源");
        DruidDataSource dataSource = new DruidDataSource();
        
        // 基本配置由Spring Boot自动注入
        // 这里可以添加额外的默认配置
        
        log.info("Druid数据源初始化完成");
        return dataSource;
    }

    /**
     * 配置完成后的回调
     */
    @Bean
    public DatasourceInitializer datasourceInitializer() {
        return new DatasourceInitializer(properties);
    }

    /**
     * 数据源初始化器
     * <p>
     * 用于在所有配置完成后执行初始化逻辑
     */
    static class DatasourceInitializer {
        
        public DatasourceInitializer(JeecgDatasourceProperties properties) {
            log.info("=== Jeecg Datasource Starter 初始化完成 ===");
            log.info("数据源配置摘要:");
            log.info("  - Druid监控: {}", properties.getDruid().isStatViewServlet());
            log.info("  - Druid监控路径: {}", properties.getDruid().getStatViewServletUrlPattern());
            log.info("  - 动态数据源: {}", properties.getDynamic().isEnable());
            log.info("  - CORS跨域: {}", properties.getCors().isEnable());
            log.info("  - SQL防火墙: {}", properties.getDruid().isWallEnabled());
            log.info("=====================================");
        }
    }
}