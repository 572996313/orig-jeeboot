package org.jeecg.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.config.firewall.SqlInjection.IDictTableWhiteListHandler;
import org.jeecg.config.firewall.SqlInjection.SysDictTableWhite;
import org.jeecg.config.mybatis.aspect.DynamicTableAspect;
import org.jeecg.config.mybatis.interceptor.DynamicDatasourceInterceptor;
import org.jeecg.modules.base.mapper.BaseCommonMapper;
import org.jeecg.modules.base.service.BaseCommonService;
import org.jeecg.modules.base.service.impl.BaseCommonServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * MyBatis-Plus 自动配置
 * 
 * @author jeecg
 * @version 4.0.0
 */
@Slf4j
@AutoConfiguration
@ConditionalOnClass({MybatisPlusInterceptor.class})
@ConditionalOnProperty(prefix = "jeecg.mybatis-plus", name = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(JeecgMybatisPlusProperties.class)
@MapperScan("org.jeecg.modules.*.mapper")
@Import({
    MybatisPlusSaasConfig.class,
    MybatisInterceptor.class,
    JeecgTenantParser.class,
    TenantContext.class,
    ThreadLocalDataHelper.class
})
public class JeecgMybatisPlusAutoConfiguration {
    
    private final JeecgMybatisPlusProperties properties;
    
    public JeecgMybatisPlusAutoConfiguration(JeecgMybatisPlusProperties properties) {
        this.properties = properties;
    }
    
    /**
     * MyBatis-Plus 拦截器
     */
    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        log.info("【Jeecg Boot】初始化 MyBatis-Plus 拦截器");
        
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        
        // 1. 租户拦截器
        if (properties.getTenant().isEnable()) {
            log.info("【Jeecg Boot】启用多租户插件，租户字段: {}", properties.getTenant().getColumn());
            TenantLineInnerInterceptor tenantInterceptor = new TenantLineInnerInterceptor();
            // 这里可以配置租户处理器
            interceptor.addInnerInterceptor(tenantInterceptor);
        }
        
        // 2. 分页拦截器
        if (properties.getPagination().isEnable()) {
            log.info("【Jeecg Boot】启用分页插件，单页最大记录数: {}", properties.getPagination().getMaxLimit());
            PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
            paginationInterceptor.setMaxLimit(properties.getPagination().getMaxLimit());
            paginationInterceptor.setOverflow(properties.getPagination().isOverflow());
            interceptor.addInnerInterceptor(paginationInterceptor);
        }
        
        // 3. 乐观锁拦截器
        if (properties.getInterceptor().isOptimisticLocker()) {
            log.info("【Jeecg Boot】启用乐观锁插件");
            interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        }
        
        return interceptor;
    }
    
    /**
     * 动态表名切面
     */
    @Bean
    @ConditionalOnProperty(prefix = "jeecg.mybatis-plus.dynamic-table", name = "enable", havingValue = "true")
    public DynamicTableAspect dynamicTableAspect() {
        log.info("【Jeecg Boot】启用动态表名切面");
        return new DynamicTableAspect();
    }
    
    /**
     * 动态数据源拦截器
     */
    @Bean
    @ConditionalOnMissingBean
    public DynamicDatasourceInterceptor dynamicDatasourceInterceptor() {
        log.info("【Jeecg Boot】注册动态数据源拦截器");
        return new DynamicDatasourceInterceptor();
    }
    
    /**
     * 基础 Mapper
     */
    @Bean
    @ConditionalOnMissingBean
    public BaseCommonMapper baseCommonMapper() {
        log.info("【Jeecg Boot】注册 BaseCommonMapper");
        return new BaseCommonMapper();
    }
    
    /**
     * 基础 Service
     */
    @Bean
    @ConditionalOnMissingBean
    public BaseCommonService baseCommonService(BaseCommonMapper baseCommonMapper) {
        log.info("【Jeecg Boot】注册 BaseCommonService");
        return new BaseCommonServiceImpl(baseCommonMapper);
    }
    
    /**
     * 字典表白名单处理器
     */
    @Bean
    @ConditionalOnMissingBean(IDictTableWhiteListHandler.class)
    public SysDictTableWhite sysDictTableWhite() {
        log.info("【Jeecg Boot】注册字典表白名单处理器");
        return new SysDictTableWhite();
    }
}