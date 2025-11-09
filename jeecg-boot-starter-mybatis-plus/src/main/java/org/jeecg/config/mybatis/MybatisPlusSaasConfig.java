package org.jeecg.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus 配置类 (简化版)
 * 
 * 本类为Phase 17.3临时简化版本，仅包含基础分页和乐观锁功能
 * Phase 20将恢复完整功能：多租户、动态表名等
 */
@Slf4j
@Configuration
public class MybatisPlusSaasConfig {

    /**
     * MyBatis-Plus 拦截器配置 (简化版)
     * 仅包含分页和乐观锁功能
     */
    @Bean
    @ConditionalOnProperty(prefix = "jeecg.mybatis-plus", name = "enable", havingValue = "true", matchIfMissing = true)
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        log.info("=============== MyBatis-Plus 拦截器初始化 (简化版) ===============");
        
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        
        // 1. 分页插件
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        paginationInterceptor.setMaxLimit(1000L); // 最大分页限制
        paginationInterceptor.setOverflow(false); // 溢出总页数后是否进行处理
        interceptor.addInnerInterceptor(paginationInterceptor);
        log.info(">>> 分页插件已启用，数据库类型: MYSQL, 最大限制: 1000");
        
        // 2. 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        log.info(">>> 乐观锁插件已启用");
        
        log.info("=============== MyBatis-Plus 拦截器初始化完成 ===============");
        return interceptor;
    }
}