package org.jeecg.common.es;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Elasticsearch 搜索结果（简化版）
 * 
 * @author jeecg-boot
 */
@Data
public class SearchResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total = 0;

    /**
     * 数据列表
     */
    private List<Map<String, Object>> data = new ArrayList<>();

    /**
     * 聚合结果
     */
    private Map<String, Object> aggregations;

    /**
     * 是否有数据
     */
    public boolean hasData() {
        return data != null && !data.isEmpty();
    }
}