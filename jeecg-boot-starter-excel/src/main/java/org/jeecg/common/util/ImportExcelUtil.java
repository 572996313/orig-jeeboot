package org.jeecg.common.util;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.config.JeecgExcelProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel 导入工具类
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@Slf4j
public class ImportExcelUtil {

    private final JeecgExcelProperties properties;

    public ImportExcelUtil(JeecgExcelProperties properties) {
        this.properties = properties;
    }

    /**
     * 导入Excel文件
     * 
     * @param file Excel文件
     * @param clazz 实体类类型
     * @return 解析后的数据列表
     */
    public <T> List<T> importExcel(MultipartFile file, Class<T> clazz) {
        try {
            // 验证文件
            validateFile(file);
            
            // 根据配置选择不同的处理引擎
            if (properties.isEasyPoi()) {
                return importWithEasyPoi(file.getInputStream(), clazz);
            } else if (properties.isPoi()) {
                return importWithPoi(file.getInputStream(), clazz);
            } else {
                throw new IllegalArgumentException("不支持的Excel引擎: " + properties.getEngine());
            }
        } catch (Exception e) {
            log.error("Excel导入失败", e);
            throw new RuntimeException("Excel导入失败: " + e.getMessage(), e);
        }
    }

    /**
     * 导入Excel文件（指定输入流）
     */
    public <T> List<T> importExcel(InputStream inputStream, Class<T> clazz) {
        try {
            if (properties.isEasyPoi()) {
                return importWithEasyPoi(inputStream, clazz);
            } else if (properties.isPoi()) {
                return importWithPoi(inputStream, clazz);
            } else {
                throw new IllegalArgumentException("不支持的Excel引擎: " + properties.getEngine());
            }
        } catch (Exception e) {
            log.error("Excel导入失败", e);
            throw new RuntimeException("Excel导入失败: " + e.getMessage(), e);
        }
    }

    /**
     * 使用 EasyPoi 导入
     */
    private <T> List<T> importWithEasyPoi(InputStream inputStream, Class<T> clazz) {
        log.info("使用 EasyPoi 引擎导入Excel");
        // 这里应该调用 EasyPoi 的实际实现
        // 由于是可选依赖，实际实现应该通过反射或策略模式调用
        return new ArrayList<>();
    }

    /**
     * 使用原生 POI 导入
     */
    private <T> List<T> importWithPoi(InputStream inputStream, Class<T> clazz) {
        log.info("使用 Apache POI 引擎导入Excel");
        // 这里应该实现原生POI的导入逻辑
        return new ArrayList<>();
    }

    /**
     * 验证上传的文件
     */
    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空");
        }

        // 验证文件大小
        long maxSize = properties.getImportConfig().getMaxFileSizeMb() * 1024 * 1024;
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException(
                String.format("文件大小超过限制，最大允许 %dMB", 
                    properties.getImportConfig().getMaxFileSizeMb())
            );
        }

        // 验证文件扩展名
        String fileName = file.getOriginalFilename();
        if (fileName == null || !isAllowedExtension(fileName)) {
            throw new IllegalArgumentException(
                String.format("不支持的文件类型，仅支持: %s", 
                    String.join(", ", properties.getImportConfig().getAllowedExtensions()))
            );
        }
    }

    /**
     * 验证文件扩展名
     */
    private boolean isAllowedExtension(String fileName) {
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

    /**
     * 获取导入配置
     */
    public JeecgExcelProperties.Import getImportConfig() {
        return properties.getImportConfig();
    }
}