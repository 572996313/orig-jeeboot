package org.jeecg.config.mybatis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MyBatis-Plus 配置属性
 * 
 * @author jeecg
 * @version 4.0.0
 */
@Data
@ConfigurationProperties(prefix = "jeecg.mybatis-plus")
public class JeecgMybatisPlusProperties {
    
    /**
     * 是否启用 MyBatis-Plus 增强功能
     */
    private boolean enable = true;
    
    /**
     * 租户配置
     */
    private TenantProperties tenant = new TenantProperties();
    
    /**
     * 动态表名配置
     */
    private DynamicTableProperties dynamicTable = new DynamicTableProperties();
    
    /**
     * SQL拦截器配置
     */
    private InterceptorProperties interceptor = new InterceptorProperties();
    
    /**
     * 分页配置
     */
    private PaginationProperties pagination = new PaginationProperties();
    
    /**
     * 租户配置
     */
    @Data
    public static class TenantProperties {
        /**
         * 是否启用多租户
         */
        private boolean enable = false;
        
        /**
         * 租户字段名
         */
        private String column = "tenant_id";
        
        /**
         * 需要进行租户隔离的表
         */
        private String[] tables = new String[0];
        
        /**
         * 排除的表(不进行租户隔离)
         */
        private String[] ignoreTables = new String[0];
    }
    
    /**
     * 动态表名配置
     */
    @Data
    public static class DynamicTableProperties {
        /**
         * 是否启用动态表名
         */
        private boolean enable = false;
        
        /**
         * 动态表名前缀
         */
        private String prefix = "";
        
        /**
         * 动态表名后缀
         */
        private String suffix = "";
    }
    
    /**
     * SQL拦截器配置
     */
    @Data
    public static class InterceptorProperties {
        /**
         * 是否启用SQL性能分析
         */
        private boolean sqlPerformance = false;
        
        /**
         * SQL性能分析最大执行时间(ms)
         */
        private long maxTime = 3000;
        
        /**
         * 是否格式化SQL
         */
        private boolean format = true;
        
        /**
         * 是否启用乐观锁插件
         */
        private boolean optimisticLocker = true;
        
        /**
         * 是否启用数据变更记录
         */
        private boolean dataChange = false;
    }
    
    /**
     * 分页配置
     */
    @Data
    public static class PaginationProperties {
        /**
         * 是否启用分页插件
         */
        private boolean enable = true;
        
        /**
         * 单页最大记录数(默认500条,-1不限制)
         */
        private long maxLimit = 500L;
        
        /**
         * 溢出总页数后是否进行处理
         */
        private boolean overflow = false;
        
        /**
         * 是否启用分页合理化(pageNum<=0时查询第一页,pageNum>pages查询最后一页)
         */
        private boolean reasonable = true;
    }
}