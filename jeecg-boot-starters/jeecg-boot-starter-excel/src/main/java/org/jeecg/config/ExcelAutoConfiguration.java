//package org.jeecg.config;
//
//import org.jeecg.common.util.SpringContextUtils;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * Excel 自动配置类
// * 整合原 AutoPoiConfig 的功能
// *
// * @Author: llllxf
// * @Date: 2025-11-12
// * @Version: 1.0
// */
//@Slf4j
//@Configuration
//@EnableConfigurationProperties(ExcelProperties.class)
//@ConditionalOnProperty(prefix = "jeecg.excel", name = "enable", havingValue = "true", matchIfMissing = true)
//@Import({AutoPoiDictConfig.class})
//public class ExcelAutoConfiguration {
//
//    public ExcelAutoConfiguration() {
//        log.info("====== JeecgBoot Excel Starter 初始化完成 ======");
//    }
//
//    /**
//     * 注册 Spring 上下文工具类
//     * 用于在非 Spring 管理的类中获取 Bean
//     */
//    @Bean
//    public SpringContextUtils springContextUtils() {
//        return new SpringContextUtils();
//    }
//}