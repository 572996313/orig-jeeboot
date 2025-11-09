package org.jeecg.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Knife4j自动配置
 * 
 * @author jeecg-boot
 * @since 4.0.0
 */
@Slf4j
@Configuration
@EnableSwagger2
@EnableKnife4j
@ConditionalOnClass({Docket.class, EnableKnife4j.class})
@ConditionalOnProperty(prefix = "jeecg.api-doc", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(JeecgApiDocProperties.class)
@Import(BeanValidatorPluginsConfiguration.class)
public class Knife4jAutoConfiguration {

    private final JeecgApiDocProperties properties;

    public Knife4jAutoConfiguration(JeecgApiDocProperties properties) {
        this.properties = properties;
    }

    @Bean
    public Docket createRestApi() {
        log.info("初始化 Knife4j API文档配置...");
        
        // 检查生产环境
        if (!properties.isActuallyEnabled()) {
            log.warn("API文档在生产环境已禁用");
            return new Docket(DocumentationType.OAS_30)
                    .enable(false)
                    .select()
                    .build();
        }

        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                // 扫描有@ApiOperation注解的方法
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 或者扫描指定包
                //.apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                // 设置安全模式
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                // 设置全局参数
                .globalRequestParameters(globalRequestParameters());
    }

    /**
     * API基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version(properties.getVersion())
                .contact(new Contact(
                        properties.getContact().getName(),
                        properties.getContact().getUrl(),
                        properties.getContact().getEmail()
                ))
                .license(properties.getLicense().getName())
                .licenseUrl(properties.getLicense().getUrl())
                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                .build();
    }

    /**
     * 安全模式（JWT）
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> schemes = new ArrayList<>();
        
        // JWT Token
        ApiKey apiKey = new ApiKey("Authorization", "X-Access-Token", "header");
        schemes.add(apiKey);
        
        return schemes;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build()
        );
    }

    /**
     * 默认的安全引用
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(
                new SecurityReference("Authorization", authorizationScopes)
        );
    }

    /**
     * 全局请求参数
     */
    private List<springfox.documentation.builders.RequestParameter> globalRequestParameters() {
        List<springfox.documentation.builders.RequestParameter> parameters = new ArrayList<>();
        
        // 添加全局Header参数（可选）
        // RequestParameter tenantIdParam = new RequestParameterBuilder()
        //         .name("tenant-id")
        //         .description("租户ID")
        //         .in(ParameterType.HEADER)
        //         .required(false)
        //         .build();
        // parameters.add(tenantIdParam);
        
        return parameters;
    }
}