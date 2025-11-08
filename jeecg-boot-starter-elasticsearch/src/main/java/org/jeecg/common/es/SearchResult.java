package org.jeecg.common.es;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Elasticsearch搜索结果封装
 * 
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-11-08
 */
@Data
public class SearchResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total = 0;

    /**
     * 搜索耗时（毫秒）
     */
    private long took = 0;

    /**
     * 数据列表
     */
    private List<Map<String, Object>> data = new ArrayList<>();

    /**
     * 是否有数据
     * 
     * @return 是否有数据
     */
    public boolean hasData() {
        return data != null && !data.isEmpty();
    }

    /**
     * 获取数据数量
     * 
     * @return 数据数量
     */
    public int getSize() {
        return data == null ? 0 : data.size();
    }
}