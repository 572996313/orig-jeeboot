package org.jeecg;

import com.xkcoding.justauth.autoconfigure.JustAuthAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.jeecg.common.util.oConvertUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * 单体启动类（采用此类启动为单体模式）
 * 报错提醒: 未集成mongo报错，可以打开启动类上面的注释 exclude={MongoAutoConfiguration.class}
 */
@Slf4j
@SpringBootApplication(exclude = {
    MongoAutoConfiguration.class,
    org.apache.shiro.spring.boot.autoconfigure.ShiroAutoConfiguration.class,
    org.apache.shiro.spring.boot.autoconfigure.ShiroAnnotationProcessorAutoConfiguration.class,
    org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration.class,
    org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration.class
    // 排除 SpringDoc 自动配置，避免与 Spring Boot 3.3.5 兼容性问题
    // 项目使用 Knife4j，不需要 SpringDoc
    // org.springdoc.webmvc.ui.SwaggerConfig.class,
    // org.springdoc.core.configuration.SpringDocConfiguration.class
}, scanBasePackages = {
    "org.jeecg"
}, scanBasePackageClasses = {})
@ImportAutoConfiguration(JustAuthAutoConfiguration.class)  // spring boot 3.x justauth 兼容性处理
@ComponentScan(
    basePackages = "org.jeecg",
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = {
                org.jeecg.config.web.UndertowCustomizer.class,
                org.jeecg.config.shiro.ShiroConfig.class  // 排除 Shiro 配置（与 Spring Boot 3.x 不兼容）
            }
        ),
        @ComponentScan.Filter(
            type = FilterType.REGEX,
            pattern = "org\\.jeecg\\.modules\\.online\\..*"  // 排除 online 模块（代码缺失）
        )
    }
)
@MapperScan(value = {"org.jeecg.modules.**.mapper*", "org.jeecg.**.mapper*"}, annotationClass = Mapper.class)
public class JeecgSystemApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JeecgSystemApplication.class);
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(JeecgSystemApplication.class);
        Map<String, Object> defaultProperties = new HashMap<>();
        defaultProperties.put("management.health.elasticsearch.enabled", false);
        app.setDefaultProperties(defaultProperties);
        log.info("[JEECG] Elasticsearch Health Check Enabled: false" );
        
        ConfigurableApplicationContext application = app.run(args);;
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = oConvertUtils.getString(env.getProperty("server.servlet.context-path"));
        log.info("\n----------------------------------------------------------\n\t" +
                "Application Jeecg-Boot is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/doc.html\n\t" +
                "Swagger文档: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");

    }

}