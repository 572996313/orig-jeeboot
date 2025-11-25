//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration("onlineConfiguration")
public class OnlineConfiguration implements WebMvcConfigurer {
    @Bean
    public b onlineInterceptor() {
        return new b();
    }

    @Bean
    public a cgreportInterceptor() {
        return new a();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        String[] exculudes = new String[]{"/*.html", "/html/**", "/js/**", "/css/**", "/images/**"};
        registry.addInterceptor(this.onlineInterceptor()).excludePathPatterns(exculudes).addPathPatterns(new String[]{"/online/cgform/api/**"});
        registry.addInterceptor(this.cgreportInterceptor()).excludePathPatterns(exculudes).addPathPatterns(new String[]{"/online/cgreport/api/**"});
    }
}
