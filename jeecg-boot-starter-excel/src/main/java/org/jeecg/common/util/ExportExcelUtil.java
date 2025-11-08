package org.jeecg.common.util;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.config.JeecgExcelProperties;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Excel 导出工具类
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@Slf4j
public class ExportExcelUtil {

    private final JeecgExcelProperties properties;

    public ExportExcelUtil(JeecgExcelProperties properties) {
        this.properties = properties;
    }

    /**
     * 导出Excel到HTTP响应
     * 
     * @param response HTTP响应对象
     * @param dataList 数据列表
     * @param clazz 实体类类型
     * @param fileName 文件名
     */
    public <T> void exportExcel(HttpServletResponse response, 
                                List<T> dataList, 
                                Class<T> clazz, 
                                String fileName) {
        try {
            // 验证数据
            validateExportData(dataList);
            
            // 设置响应头
            setResponseHeader(response, fileName);
            
            // 根据配置选择不同的处理引擎
            if (properties.isEasyPoi()) {
                exportWithEasyPoi(response.getOutputStream(), dataList, clazz);
            } else if (properties.isPoi()) {
                exportWithPoi(response.getOutputStream(), dataList, clazz);
            } else {
                throw new IllegalArgumentException("不支持的Excel引擎: " + properties.getEngine());
            }
            
            response.flushBuffer();
        } catch (Exception e) {
            log.error("Excel导出失败", e);
            throw new RuntimeException("Excel导出失败: " + e.getMessage(), e);
        }
    }

    /**
     * 导出Excel到输出流
     */
    public <T> void exportExcel(OutputStream outputStream, 
                               List<T> dataList, 
                               Class<T> clazz) {
        try {
            validateExportData(dataList);
            
            if (properties.isEasyPoi()) {
                exportWithEasyPoi(outputStream, dataList, clazz);
            } else if (properties.isPoi()) {
                exportWithPoi(outputStream, dataList, clazz);
            } else {
                throw new IllegalArgumentException("不支持的Excel引擎: " + properties.getEngine());
            }
        } catch (Exception e) {
            log.error("Excel导出失败", e);
            throw new RuntimeException("Excel导出失败: " + e.getMessage(), e);
        }
    }

    /**
     * 使用 EasyPoi 导出
     */
    private <T> void exportWithEasyPoi(OutputStream outputStream, 
                                      List<T> dataList, 
                                      Class<T> clazz) {
        log.info("使用 EasyPoi 引擎导出Excel，数据量: {}", dataList.size());
        // 这里应该调用 EasyPoi 的实际实现
        // 由于是可选依赖，实际实现应该通过反射或策略模式调用
    }

    /**
     * 使用原生 POI 导出
     */
    private <T> void exportWithPoi(OutputStream outputStream, 
                                  List<T> dataList, 
                                  Class<T> clazz) {
        log.info("使用 Apache POI 引擎导出Excel，数据量: {}", dataList.size());
        // 这里应该实现原生POI的导出逻辑
    }

    /**
     * 验证导出数据
     */
    private <T> void validateExportData(List<T> dataList) {
        if (dataList == null) {
            throw new IllegalArgumentException("导出数据不能为null");
        }

        int maxRows = properties.getExportConfig().getMaxRows();
        if (dataList.size() > maxRows) {
            throw new IllegalArgumentException(
                String.format("导出数据量超过限制，最大允许 %d 行", maxRows)
            );
        }
    }

    /**
     * 设置HTTP响应头
     */
    private void setResponseHeader(HttpServletResponse response, String fileName) 
            throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        
        // 处理文件名编码
        String encodedFileName = URLEncoder.encode(fileName, "UTF-8")
                .replaceAll("\\+", "%20");
        
        response.setHeader("Content-Disposition", 
            "attachment;filename=" + encodedFileName + ".xlsx");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
    }

    /**
     * 获取导出配置
     */
    public JeecgExcelProperties.Export getExportConfig() {
        return properties.getExportConfig();
    }
}