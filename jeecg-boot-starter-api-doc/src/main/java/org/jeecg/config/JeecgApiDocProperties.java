package org.jeecg.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jeecg API文档配置属性
 * 
 * @author llllxf
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
     * API文档类型: swagger3, knife4j
     */
    private String type = "swagger3";
    
    /**
     * API标题
     */
    private String title = "JeecgBoot 后台服务API接口文档";
    
    /**
     * API版本
     */
    private String version = "3.8.3";
    
    /**
     * API描述
     */
    private String description = "后台API接口";
    
    /**
     * 联系人姓名
     */
    private String contactName = "北京国炬信息技术有限公司";
    
    /**
     * 联系人网址
     */
    private String contactUrl = "www.jeecg.com";
    
    /**
     * 联系人邮箱
     */
    private String contactEmail = "jeecgos@163.com";
    
    /**
     * 许可证名称
     */
    private String licenseName = "Apache 2.0";
    
    /**
     * 许可证URL
     */
    private String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.html";
    
    /**
     * 服务条款URL
     */
    private String termsOfServiceUrl = "NO terms of service";
    
    /**
     * 扫描的包路径
     */
    private String basePackage = "org.jeecg";
    
    /**
     * 是否自动标记类
     */
    private Boolean autoTagClasses = false;
    
    /**
     * 排除的路径（不需要Token验证）
     */
    private String[] excludedPaths = {
        "/sys/randomImage/{key}",
        "/sys/login",
        "/sys/phoneLogin",
        "/sys/mLogin",
        "/sys/sms",
        "/sys/cas/client/validateLogin",
        "/test/jeecgDemo/demo3",
        "/sys/thirdLogin/**",
        "/sys/user/register"
    };
    
    /**
     * Token参数名
     */
    private String tokenName = "X-Access-Token";
    
    /**
     * 生产环境是否启用（默认不启用）
     */
    private Boolean production = false;
}