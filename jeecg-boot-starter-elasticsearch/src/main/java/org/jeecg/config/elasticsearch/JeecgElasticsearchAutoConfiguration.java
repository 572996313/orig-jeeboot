package org.jeecg.config.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.jeecg.common.es.JeecgElasticsearchTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Elasticsearch 自动配置（简化版占位符）
 * 
 * @author jeecg-boot
 */
@Slf4j
@Configuration
@ConditionalOnClass(RestHighLevelClient.class)
@ConditionalOnProperty(prefix = "jeecg.elasticsearch", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(JeecgElasticsearchProperties.class)
public class JeecgElasticsearchAutoConfiguration {

    /**
     * 创建 RestHighLevelClient（简化版）
     */
    @Bean
    @ConditionalOnMissingBean
    public RestHighLevelClient restHighLevelClient(JeecgElasticsearchProperties properties) {
        log.info("初始化 Elasticsearch RestHighLevelClient（简化版）...");
        
        try {
            // 解析集群节点
            String[] nodes = properties.getClusterNodes().split(",");
            HttpHost[] httpHosts = new HttpHost[nodes.length];
            
            for (int i = 0; i < nodes.length; i++) {
                String node = nodes[i].trim();
                String[] parts = node.split(":");
                String host = parts[0];
                int port = parts.length > 1 ? Integer.parseInt(parts[1]) : 9200;
                httpHosts[i] = new HttpHost(host, port, "http");
            }
            
            // 创建 RestClientBuilder
            RestClientBuilder builder = RestClient.builder(httpHosts);
            
            // 设置超时时间
            builder.setRequestConfigCallback(requestConfigBuilder -> 
                requestConfigBuilder
                    .setConnectTimeout(properties.getConnectTimeout() * 1000)
                    .setSocketTimeout(properties.getSocketTimeout() * 1000)
                    .setConnectionRequestTimeout(properties.getConnectionRequestTimeout() * 1000)
            );
            
            // 设置连接池
            builder.setHttpClientConfigCallback(httpClientBuilder -> {
                httpClientBuilder
                    .setMaxConnTotal(properties.getMaxConnTotal())
                    .setMaxConnPerRoute(properties.getMaxConnPerRoute());
                
                // 设置认证
                if (properties.getUsername() != null && !properties.getUsername().isEmpty()) {
                    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                    credentialsProvider.setCredentials(AuthScope.ANY,
                        new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword()));
                    httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                }
                
                return httpClientBuilder;
            });
            
            RestHighLevelClient client = new RestHighLevelClient(builder);
            log.info("Elasticsearch RestHighLevelClient 初始化成功");
            return client;
            
        } catch (Exception e) {
            log.error("Elasticsearch RestHighLevelClient 初始化失败: {}", e.getMessage(), e);
            throw new RuntimeException("Elasticsearch 客户端初始化失败", e);
        }
    }

    /**
     * 创建 JeecgElasticsearchTemplate（简化版）
     */
    @Bean
    @ConditionalOnMissingBean
    public JeecgElasticsearchTemplate jeecgElasticsearchTemplate(
            RestHighLevelClient restHighLevelClient,
            JeecgElasticsearchProperties properties) {
        log.info("初始化 JeecgElasticsearchTemplate（简化版）...");
        return new JeecgElasticsearchTemplate(restHighLevelClient, properties);
    }
}