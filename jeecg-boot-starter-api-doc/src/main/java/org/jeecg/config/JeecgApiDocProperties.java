package org.jeecg.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * API文档配置属性
 * 
 * @author jeecg-boot
 * @since 4.0.0
 */
@Data
@ConfigurationProperties(prefix = "jeecg.api-doc")
public class JeecgApiDocProperties {

    /**
     * 是否启用API文档
     */
    private Boolean enabled = true;

    /**
     * 文档类型：swagger3, knife4j, springdoc
     */
    private String type = "knife4j";

    /**
     * 是否在生产环境启用
     */
    private Boolean production = false;

    /**
     * API文档标题
     */
    private String title = "JeecgBoot API文档";

    /**
     * API文档描述
     */
    private String description = "JeecgBoot 企业级快速开发平台";

    /**
     * API文档版本
     */
    private String version = "4.0.0";

    /**
     * 服务条款URL
     */
    private String termsOfServiceUrl;

    /**
     * 联系人信息
     */
    private Contact contact = new Contact();

    /**
     * 许可证信息
     */
    private License license = new License();

    /**
     * 扫描的包路径（多个用逗号分隔）
     */
    private String basePackage = "org.jeecg";

    /**
     * 排除的路径（多个用逗号分隔）
     */
    private String excludePaths = "/error,/actuator/**";

    /**
     * 分组配置
     */
    private Group group = new Group();

    /**
     * Knife4j增强配置
     */
    private Knife4j knife4j = new Knife4j();

    /**
     * 联系人信息
     */
    @Data
    public static class Contact {
        /**
         * 联系人名称
         */
        private String name = "JeecgBoot Team";

        /**
         * 联系人URL
         */
        private String url = "http://www.jeecg.com";

        /**
         * 联系人邮箱
         */
        private String email = "jeecgos@163.com";
    }

    /**
     * 许可证信息
     */
    @Data
    public static class License {
        /**
         * 许可证名称
         */
        private String name = "Apache License 2.0";

        /**
         * 许可证URL
         */
        private String url = "https://www.apache.org/licenses/LICENSE-2.0";
    }

    /**
     * 分组配置
     */
    @Data
    public static class Group {
        /**
         * 是否启用分组
         */
        private Boolean enabled = false;

        /**
         * 默认分组名称
         */
        private String defaultName = "default";
    }

    /**
     * Knife4j增强配置
     */
    @Data
    public static class Knife4j {
        /**
         * 是否启用Knife4j增强
         */
        private Boolean enable = true;

        /**
         * 是否开启生产环境屏蔽
         */
        private Boolean production = false;

        /**
         * 是否开启HTTP Basic认证
         */
        private Boolean basicEnable = false;

        /**
         * Basic认证用户名
         */
        private String basicUsername = "admin";

        /**
         * Basic认证密码
         */
        private String basicPassword = "123456";

        /**
         * 是否开启请求参数缓存
         */
        private Boolean enableRequestCache = true;

        /**
         * 是否开启过滤请求参数
         */
        private Boolean enableFilterMultipartApis = false;

        /**
         * 是否开启swagger资源保护
         */
        private Boolean enableSwaggerModels = true;

        /**
         * 是否开启主页自定义
         */
        private Boolean enableHomeCustom = false;

        /**
         * 自定义主页路径
         */
        private String homeCustomPath;

        /**
         * 自定义文档位置
         */
        private String homeCustomLocation;
    }

    /**
     * 获取实际的启用状态（考虑生产环境）
     */
    public boolean isActuallyEnabled() {
        if (!enabled) {
            return false;
        }
        // 生产环境下，如果production=false，则禁用
        String profile = System.getProperty("spring.profiles.active", "");
        if (profile.contains("prod") && !production) {
            return false;
        }
        return true;
    }
}