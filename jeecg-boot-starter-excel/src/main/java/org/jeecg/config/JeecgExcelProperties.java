package org.jeecg.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * JeecgBoot Excel 配置属性
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@Data
@ConfigurationProperties(prefix = "jeecg.excel")
public class JeecgExcelProperties {

    /**
     * 是否启用 Excel 功能
     */
    private boolean enabled = true;

    /**
     * Excel 引擎类型：easypoi, poi
     */
    private String engine = "easypoi";

    /**
     * 导入配置
     */
    private Import importConfig = new Import();

    /**
     * 导出配置
     */
    private Export exportConfig = new Export();

    /**
     * 临时文件配置
     */
    private Temp temp = new Temp();

    /**
     * 导入配置类
     */
    @Data
    public static class Import {
        
        /**
         * 最大导入行数
         */
        private int maxRows = 10000;

        /**
         * 最大导入文件大小（MB）
         */
        private int maxFileSizeMb = 10;

        /**
         * 允许的文件扩展名
         */
        private String[] allowedExtensions = {"xls", "xlsx"};

        /**
         * 是否验证模板
         */
        private boolean validateTemplate = true;

        /**
         * 错误数据处理策略：skip, stop, collect
         */
        private String errorStrategy = "collect";

        /**
         * 是否需要表头校验
         */
        private boolean needVerify = true;

        /**
         * 开始读取的行数（从0开始）
         */
        private int startRows = 1;

        /**
         * 读取的sheet数量
         */
        private int readRows = 1;
    }

    /**
     * 导出配置类
     */
    @Data
    public static class Export {
        
        /**
         * 最大导出行数
         */
        private int maxRows = 100000;

        /**
         * 默认Sheet名称
         */
        private String defaultSheetName = "Sheet1";

        /**
         * 是否自动换行
         */
        private boolean wrapText = false;

        /**
         * 列宽度自适应
         */
        private boolean autoSizeColumn = true;

        /**
         * 是否需要索引列
         */
        private boolean needIndex = false;

        /**
         * 默认字体大小
         */
        private short fontSize = 11;

        /**
         * 标题行高度
         */
        private short titleHeight = 450;

        /**
         * 数据行高度
         */
        private short dataHeight = 400;

        /**
         * 是否使用样式
         */
        private boolean useStyle = true;

        /**
         * 日期格式
         */
        private String dateFormat = "yyyy-MM-dd HH:mm:ss";

        /**
         * 数字格式
         */
        private String numberFormat = "#,##0.00";
    }

    /**
     * 临时文件配置类
     */
    @Data
    public static class Temp {
        
        /**
         * 临时文件存储路径
         */
        private String path = System.getProperty("java.io.tmpdir") + "/jeecg/excel";

        /**
         * 临时文件保留时间（小时）
         */
        private int retentionHours = 24;

        /**
         * 是否自动清理临时文件
         */
        private boolean autoClean = true;

        /**
         * 清理任务执行间隔（小时）
         */
        private int cleanInterval = 6;
    }

    /**
     * 判断是否启用
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * 判断是否使用 EasyPoi
     */
    public boolean isEasyPoi() {
        return "easypoi".equalsIgnoreCase(this.engine);
    }

    /**
     * 判断是否使用原生 POI
     */
    public boolean isPoi() {
        return "poi".equalsIgnoreCase(this.engine);
    }
}