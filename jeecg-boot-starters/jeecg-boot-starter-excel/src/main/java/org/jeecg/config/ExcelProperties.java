package org.jeecg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Excel 配置属性类
 * 支持通过 application.yml 配置
 * 
 * @Author: llllxf
 * @Date: 2025-11-12
 * @Version: 1.0
 */
@Data
@ConfigurationProperties(prefix = "jeecg.excel")
public class ExcelProperties {

    /**
     * 是否启用 Excel 功能
     * 默认: true
     */
    private Boolean enable = true;

    /**
     * Excel 导入最大行数
     * 默认: 10000
     */
    private Integer maxImportRows = 10000;

    /**
     * Excel 临时文件存储路径
     * 默认: /temp/excel
     */
    private String tempPath = "/temp/excel";

    /**
     * Excel 导出模板路径
     * 默认: /templates/excel
     */
    private String templatePath = "/templates/excel";

    /**
     * 是否启用字典翻译
     * 默认: true
     */
    private Boolean enableDict = true;

    /**
     * 导出文件名日期格式
     * 默认: yyyyMMddHHmmss
     */
    private String filenameDateFormat = "yyyyMMddHHmmss";
}