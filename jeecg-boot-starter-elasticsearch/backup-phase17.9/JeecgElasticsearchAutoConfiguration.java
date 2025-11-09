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
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Elasticsearch自动配置类
 * 
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-11-08
 */
@Slf4j
@Configuration
@ConditionalOnClass(RestHighLevelClient.class)
@ConditionalOnProperty(prefix = "jeecg.elasticsearch", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(JeecgElasticsearchProperties.class)
public class JeecgElasticsearchAutoConfiguration {

    private final JeecgElasticsearchProperties properties;

    public JeecgElasticsearchAutoConfiguration(JeecgElasticsearchProperties properties) {
        this.properties = properties;
        log.info("初始化 JeecgElasticsearchAutoConfiguration");
    }

    /**
     * 创建RestHighLevelClient
     */
    @Bean
    @ConditionalOnMissingBean
    public RestHighLevelClient restHighLevelClient() {
        List<HttpHost> httpHosts = new ArrayList<>();
        
        // 解析集群节点地址
        for (String clusterNode : properties.getClusterNodes()) {
            String[] parts = clusterNode.split(":");
            String host = parts[0];
            int port = parts.length > 1 ? Integer.parseInt(parts[1]) : 9200;
            httpHosts.add(new HttpHost(host, port, "http"));
        }

        if (httpHosts.isEmpty()) {
            throw new IllegalArgumentException("Elasticsearch集群节点地址不能为空");
        }

        RestClientBuilder builder = RestClient.builder(httpHosts.toArray(new HttpHost[0]));

        // 配置连接超时
        builder.setRequestConfigCallback(requestConfigBuilder -> 
            requestConfigBuilder
                .setConnectTimeout(properties.getConnectionTimeout())
                .setSocketTimeout(properties.getSocketTimeout())
                .setConnectionRequestTimeout(properties.getConnectionRequestTimeout())
        );

        // 配置连接池
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder
                .setMaxConnTotal(properties.getMaxConnections())
                .setMaxConnPerRoute(properties.getMaxConnectionsPerRoute());

            // 配置认证
            if (StringUtils.hasText(properties.getUsername()) && 
                StringUtils.hasText(properties.getPassword())) {
                CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(
                    AuthScope.ANY,
                    new UsernamePasswordCredentials(
                        properties.getUsername(),
                        properties.getPassword()
                    )
                );
                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                log.info("Elasticsearch配置认证: {}", properties.getUsername());
            }

            return httpClientBuilder;
        });

        RestHighLevelClient client = new RestHighLevelClient(builder);
        log.info("Elasticsearch客户端初始化成功，集群节点: {}", properties.getClusterNodes());
        
        return client;
    }

    /**
     * 创建JeecgElasticsearchTemplate
     */
    @Bean
    @ConditionalOnMissingBean
    public JeecgElasticsearchTemplate jeecgElasticsearchTemplate(RestHighLevelClient restHighLevelClient) {
        JeecgElasticsearchTemplate template = new JeecgElasticsearchTemplate(restHighLevelClient, properties);
        log.info("JeecgElasticsearchTemplate初始化成功");
        return template;
    }
}