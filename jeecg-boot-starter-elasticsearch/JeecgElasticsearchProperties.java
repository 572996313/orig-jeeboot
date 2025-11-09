package org.jeecg.config.elasticsearch;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Elasticsearch配置属性
 * 
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-11-08
 */
@Data
@ConfigurationProperties(prefix = "jeecg.elasticsearch")
public class JeecgElasticsearchProperties {

    /**
     * 是否启用Elasticsearch
     */
    private boolean enabled = false;

    /**
     * 集群名称
     */
    private String clusterName = "elasticsearch";

    /**
     * 集群节点地址列表（格式：host:port）
     */
    private List<String> clusterNodes = new ArrayList<>();

    /**
     * 用户名（可选）
     */
    private String username;

    /**
     * 密码（可选）
     */
    private String password;

    /**
     * 连接超时时间（毫秒）
     */
    private int connectionTimeout = 5000;

    /**
     * Socket超时时间（毫秒）
     */
    private int socketTimeout = 30000;

    /**
     * 连接请求超时时间（毫秒）
     */
    private int connectionRequestTimeout = 500;

    /**
     * 最大连接数
     */
    private int maxConnections = 100;

    /**
     * 每个路由的最大连接数
     */
    private int maxConnectionsPerRoute = 50;

    /**
     * 索引配置
     */
    private IndexConfig index = new IndexConfig();

    /**
     * 搜索配置
     */
    private SearchConfig search = new SearchConfig();

    /**
     * 索引配置
     */
    @Data
    public static class IndexConfig {
        /**
         * 默认分片数
         */
        private int numberOfShards = 3;

        /**
         * 默认副本数
         */
        private int numberOfReplicas = 1;

        /**
         * 刷新间隔（秒）
         */
        private int refreshInterval = 1;

        /**
         * 是否自动创建索引
         */
        private boolean autoCreate = true;

        /**
         * 索引前缀
         */
        private String prefix = "jeecg_";

        /**
         * 日期格式索引后缀（如：yyyy-MM-dd）
         */
        private String dateSuffix;
    }

    /**
     * 搜索配置
     */
    @Data
    public static class SearchConfig {
        /**
         * 默认分页大小
         */
        private int defaultPageSize = 10;

        /**
         * 最大分页大小
         */
        private int maxPageSize = 10000;

        /**
         * 高亮前缀标签
         */
        private String highlightPreTag = "<em>";

        /**
         * 高亮后缀标签
         */
        private String highlightPostTag = "</em>";

        /**
         * 是否启用查询缓存
         */
        private boolean queryCacheEnabled = true;

        /**
         * 最小相似度得分
         */
        private float minScore = 0.0f;

        /**
         * 搜索超时时间（毫秒）
         */
        private int timeout = 10000;
    }
}