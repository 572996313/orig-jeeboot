package org.jeecg.config;

import org.jeecg.common.es.JeecgElasticsearchTemplate;
import org.jeecg.config.vo.Elasticsearch;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Elasticsearch 自动配置类
 * 
 * @author jeecg-boot
 */
@Configuration
@ConditionalOnProperty(prefix = "jeecg.elasticsearch", name = "cluster-nodes")
public class ElasticsearchAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "jeecg.elasticsearch")
    public Elasticsearch elasticsearch() {
        return new Elasticsearch();
    }

}