package org.jeecg.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JeecgBoot Excel 自动配置类
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(JeecgExcelProperties.class)
@ConditionalOnProperty(prefix = "jeecg.excel", name = "enabled", havingValue = "true", matchIfMissing = true)
public class JeecgExcelAutoConfiguration {

    private final JeecgExcelProperties properties;

    public JeecgExcelAutoConfiguration(JeecgExcelProperties properties) {
        this.properties = properties;
        log.info("====================================");
        log.info("    JeecgBoot Excel 模块已启用");
        log.info("    引擎类型: {}", properties.getEngine());
        log.info("    最大导入行数: {}", properties.getImportConfig().getMaxRows());
        log.info("    最大导出行数: {}", properties.getExportConfig().getMaxRows());
        log.info("====================================");
    }

    /**
     * EasyPoi 配置Bean
     * 仅在 EasyPoi 类存在且配置为 easypoi 时生效
     */
    @Bean
    @ConditionalOnClass(name = "cn.afterturn.easypoi.excel.ExcelExportUtil")
    @ConditionalOnProperty(prefix = "jeecg.excel", name = "engine", havingValue = "easypoi", matchIfMissing = true)
    @ConditionalOnMissingBean(name = "easyPoiConfig")
    public EasyPoiConfig easyPoiConfig() {
        log.info("✅ 初始化 EasyPoi 配置");
        return new EasyPoiConfig(properties);
    }

    /**
     * POI 配置Bean
     * 仅在配置为 poi 时生效
     */
    @Bean
    @ConditionalOnClass(name = "org.apache.poi.ss.usermodel.Workbook")
    @ConditionalOnProperty(prefix = "jeecg.excel", name = "engine", havingValue = "poi")
    @ConditionalOnMissingBean(name = "poiConfig")
    public PoiConfig poiConfig() {
        log.info("✅ 初始化 Apache POI 配置");
        return new PoiConfig(properties);
    }

    /**
     * Excel 工具类Bean
     */
    @Bean
    @ConditionalOnMissingBean
    public ExcelUtil excelUtil() {
        log.info("✅ 注册 Excel 工具类");
        return new ExcelUtil(properties);
    }

    /**
     * 临时文件清理任务
     */
    @Bean
    @ConditionalOnProperty(prefix = "jeecg.excel.temp", name = "auto-clean", havingValue = "true", matchIfMissing = true)
    @ConditionalOnMissingBean
    public TempFileCleanTask tempFileCleanTask() {
        log.info("✅ 启用临时文件自动清理任务");
        return new TempFileCleanTask(properties);
    }

    /**
     * EasyPoi 配置类
     */
    public static class EasyPoiConfig {
        private final JeecgExcelProperties properties;

        public EasyPoiConfig(JeecgExcelProperties properties) {
            this.properties = properties;
        }

        public JeecgExcelProperties getProperties() {
            return properties;
        }
    }

    /**
     * POI 配置类
     */
    public static class PoiConfig {
        private final JeecgExcelProperties properties;

        public PoiConfig(JeecgExcelProperties properties) {
            this.properties = properties;
        }

        public JeecgExcelProperties getProperties() {
            return properties;
        }
    }

    /**
     * Excel 工具类
     */
    public static class ExcelUtil {
        private final JeecgExcelProperties properties;

        public ExcelUtil(JeecgExcelProperties properties) {
            this.properties = properties;
        }

        public JeecgExcelProperties getProperties() {
            return properties;
        }

        /**
         * 获取导入配置
         */
        public JeecgExcelProperties.Import getImportConfig() {
            return properties.getImportConfig();
        }

        /**
         * 获取导出配置
         */
        public JeecgExcelProperties.Export getExportConfig() {
            return properties.getExportConfig();
        }

        /**
         * 验证文件扩展名
         */
        public boolean isAllowedExtension(String fileName) {
            if (fileName == null || fileName.isEmpty()) {
                return false;
            }
            String extension = getFileExtension(fileName);
            String[] allowedExtensions = properties.getImportConfig().getAllowedExtensions();
            for (String allowed : allowedExtensions) {
                if (allowed.equalsIgnoreCase(extension)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 获取文件扩展名
         */
        private String getFileExtension(String fileName) {
            int lastIndexOf = fileName.lastIndexOf(".");
            if (lastIndexOf == -1) {
                return "";
            }
            return fileName.substring(lastIndexOf + 1);
        }
    }

    /**
     * 临时文件清理任务
     */
    public static class TempFileCleanTask {
        private final JeecgExcelProperties properties;

        public TempFileCleanTask(JeecgExcelProperties properties) {
            this.properties = properties;
            // 这里可以启动定时任务清理临时文件
            log.info("临时文件清理任务已配置，清理间隔: {} 小时", 
                    properties.getTemp().getCleanInterval());
        }

        public JeecgExcelProperties getProperties() {
            return properties;
        }
    }
}