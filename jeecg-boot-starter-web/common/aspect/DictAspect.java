package org.jeecg.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jeecg.config.web.JeecgWebProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 字典翻译切面
 * 自动翻译返回结果中的字典值
 *
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-01-08
 */
@Aspect
public class DictAspect {

    private static final Logger log = LoggerFactory.getLogger(DictAspect.class);

    private final JeecgWebProperties.DictConfig config;

    @Autowired(required = false)
    private ObjectMapper objectMapper;

    public DictAspect(JeecgWebProperties.DictConfig config) {
        this.config = config;
        log.info("字典翻译切面初始化 - 异步翻译: {}, 缓存时间: {}s", 
                config.isAsync(), config.getCacheSeconds());
    }

    /**
     * 切点：带有@Dict注解的方法
     */
    @Pointcut("@annotation(org.jeecg.common.aspect.annotation.Dict)")
    public void dictPointcut() {
    }

    /**
     * 环绕通知 - 翻译字典
     */
    @Around("dictPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        if (!config.isEnabled()) {
            return point.proceed();
        }

        // 执行方法
        Object result = point.proceed();

        // 异步翻译
        if (config.isAsync()) {
            translateAsync(result);
            return result;
        }

        // 同步翻译
        return translateSync(result);
    }

    /**
     * 同步翻译
     */
    private Object translateSync(Object result) {
        if (result == null) {
            return null;
        }

        try {
            // 处理单个对象
            if (result instanceof Map) {
                translateMap((Map<String, Object>) result);
            }
            // 处理列表
            else if (result instanceof List) {
                List<?> list = (List<?>) result;
                for (Object item : list) {
                    if (item instanceof Map) {
                        translateMap((Map<String, Object>) item);
                    }
                }
            }
            // 处理包装对象（如Result）
            else if (hasResultField(result)) {
                Object data = getResultData(result);
                if (data != null) {
                    translateSync(data);
                }
            }
        } catch (Exception e) {
            log.error("字典翻译失败: {}", e.getMessage(), e);
        }

        return result;
    }

    /**
     * 异步翻译
     */
    private void translateAsync(Object result) {
        // 异步翻译实现（这里简化处理）
        new Thread(() -> translateSync(result)).start();
    }

    /**
     * 翻译Map对象
     */
    private void translateMap(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return;
        }

        // 遍历所有字段，查找需要翻译的字典字段
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // 示例：性别字典翻译
            if ("sex".equals(key) && value != null) {
                String dictText = translateDictValue("sex", value.toString());
                map.put("sex_dictText", dictText);
            }
            // 示例：状态字典翻译
            else if ("status".equals(key) && value != null) {
                String dictText = translateDictValue("status", value.toString());
                map.put("status_dictText", dictText);
            }
            // 可以添加更多字典字段的翻译规则
        }
    }

    /**
     * 翻译字典值
     */
    private String translateDictValue(String dictCode, String dictValue) {
        // 这里简化实现，实际应该从数据库或缓存中查询
        // 示例翻译
        if ("sex".equals(dictCode)) {
            switch (dictValue) {
                case "1": return "男";
                case "2": return "女";
                default: return dictValue;
            }
        } else if ("status".equals(dictCode)) {
            switch (dictValue) {
                case "0": return "禁用";
                case "1": return "启用";
                default: return dictValue;
            }
        }
        return dictValue;
    }

    /**
     * 判断是否有result字段
     */
    private boolean hasResultField(Object obj) {
        try {
            obj.getClass().getDeclaredField("result");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    /**
     * 获取result字段的值
     */
    private Object getResultData(Object obj) {
        try {
            java.lang.reflect.Field field = obj.getClass().getDeclaredField("result");
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            return null;
        }
    }
}