package org.jeecg.config.firewall.interceptor;

import org.jeecg.config.firewall.interceptor.enums.LowCodeUrlsEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @deprecated 此类已被 jeecg-boot-starter-web 模块中的 org.jeecg.config.firewall.LowCodeModeConfiguration 替代
 * 保留此类仅为了兼容性，但不再作为 Spring 配置类使用
 */
//@Configuration  // 已注释：避免与 jeecg-boot-starter-web 模块中的 LowCodeModeConfiguration 冲突
@Deprecated
public class LowCodeModeConfiguration implements WebMvcConfigurer {

    private final LowCodeModeInterceptor lowCodeModeInterceptor;

    public LowCodeModeConfiguration(LowCodeModeInterceptor lowCodeModeInterceptor) {
        this.lowCodeModeInterceptor = lowCodeModeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(lowCodeModeInterceptor).addPathPatterns(LowCodeUrlsEnum.getLowCodeInterceptUrls());
    }
}
