package org.jeecg.config.elasticsearch;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Elasticsearch 配置属性（简化版占位符）
 * 
 * @author jeecg-boot
 */
@Data
@ConfigurationProperties(prefix = "jeecg.elasticsearch")
public class JeecgElasticsearchProperties {

    /**
     * 是否启用
     */
    private boolean enabled = false;

    /**
     * 集群名称
     */
    private String clusterName = "elasticsearch";

    /**
     * 集群节点地址（逗号分隔）
     */
    private String clusterNodes = "localhost:9200";

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 连接超时时间（秒）
     */
    private int connectTimeout = 5;

    /**
     * Socket超时时间（秒）
     */
    private int socketTimeout = 30;

    /**
     * 连接请求超时时间（秒）
     */
    private int connectionRequestTimeout = 5;

    /**
     * 最大连接数
     */
    private int maxConnTotal = 100;

    /**
     * 每个路由的最大连接数
     */
    private int maxConnPerRoute = 100;
}