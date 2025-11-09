package org.jeecg.common.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.jeecg.config.elasticsearch.JeecgElasticsearchProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Elasticsearch操作模板类
 * 提供索引管理、文档CRUD、搜索等功能
 * 
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-11-08
 */
@Slf4j
public class JeecgElasticsearchTemplate {

    private final RestHighLevelClient restHighLevelClient;
    private final JeecgElasticsearchProperties properties;
    private final ObjectMapper objectMapper;

    public JeecgElasticsearchTemplate(RestHighLevelClient restHighLevelClient, 
                                      JeecgElasticsearchProperties properties) {
        this.restHighLevelClient = restHighLevelClient;
        this.properties = properties;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * 创建索引
     * 
     * @param indexName 索引名称
     * @return 是否成功
     */
    public boolean createIndex(String indexName) {
        return createIndex(indexName, null);
    }

    /**
     * 创建索引（带映射）
     * 
     * @param indexName 索引名称
     * @param mapping 映射配置（JSON字符串）
     * @return 是否成功
     */
    public boolean createIndex(String indexName, String mapping) {
        try {
            if (existsIndex(indexName)) {
                log.warn("索引已存在: {}", indexName);
                return true;
            }

            CreateIndexRequest request = new CreateIndexRequest(indexName);
            
            // 设置分片和副本
            Settings.Builder settings = Settings.builder()
                .put("index.number_of_shards", properties.getIndex().getNumberOfShards())
                .put("index.number_of_replicas", properties.getIndex().getNumberOfReplicas())
                .put("index.refresh_interval", properties.getIndex().getRefreshInterval() + "s");
            request.settings(settings);

            // 设置映射
            if (mapping != null && !mapping.isEmpty()) {
                request.mapping(mapping, XContentType.JSON);
            }

            CreateIndexResponse response = restHighLevelClient.indices()
                .create(request, RequestOptions.DEFAULT);
            
            log.info("创建索引成功: {}", indexName);
            return response.isAcknowledged();
        } catch (IOException e) {
            log.error("创建索引失败: " + indexName, e);
            return false;
        }
    }

    /**
     * 删除索引
     * 
     * @param indexName 索引名称
     * @return 是否成功
     */
    public boolean deleteIndex(String indexName) {
        try {
            if (!existsIndex(indexName)) {
                log.warn("索引不存在: {}", indexName);
                return true;
            }

            DeleteIndexRequest request = new DeleteIndexRequest(indexName);
            AcknowledgedResponse response = restHighLevelClient.indices()
                .delete(request, RequestOptions.DEFAULT);
            
            log.info("删除索引成功: {}", indexName);
            return response.isAcknowledged();
        } catch (IOException e) {
            log.error("删除索引失败: " + indexName, e);
            return false;
        }
    }

    /**
     * 判断索引是否存在
     * 
     * @param indexName 索引名称
     * @return 是否存在
     */
    public boolean existsIndex(String indexName) {
        try {
            GetIndexRequest request = new GetIndexRequest(indexName);
            return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("检查索引是否存在失败: " + indexName, e);
            return false;
        }
    }

    /**
     * 添加文档
     * 
     * @param indexName 索引名称
     * @param id 文档ID
     * @param data 文档数据
     * @return 是否成功
     */
    public boolean addDocument(String indexName, String id, Object data) {
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            IndexRequest request = new IndexRequest(indexName)
                .id(id)
                .source(jsonData, XContentType.JSON);
            
            IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            log.debug("添加文档成功: index={}, id={}", indexName, id);
            return response.status().getStatus() < 400;
        } catch (Exception e) {
            log.error("添加文档失败: index=" + indexName + ", id=" + id, e);
            return false;
        }
    }

    /**
     * 批量添加文档
     * 
     * @param indexName 索引名称
     * @param dataList 文档列表（Map<id, data>）
     * @return 是否成功
     */
    public boolean bulkAddDocument(String indexName, Map<String, Object> dataList) {
        try {
            BulkRequest bulkRequest = new BulkRequest();
            
            for (Map.Entry<String, Object> entry : dataList.entrySet()) {
                String jsonData = objectMapper.writeValueAsString(entry.getValue());
                IndexRequest request = new IndexRequest(indexName)
                    .id(entry.getKey())
                    .source(jsonData, XContentType.JSON);
                bulkRequest.add(request);
            }
            
            BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            log.info("批量添加文档成功: index={}, count={}", indexName, dataList.size());
            return !response.hasFailures();
        } catch (Exception e) {
            log.error("批量添加文档失败: index=" + indexName, e);
            return false;
        }
    }

    /**
     * 更新文档
     * 
     * @param indexName 索引名称
     * @param id 文档ID
     * @param data 更新数据
     * @return 是否成功
     */
    public boolean updateDocument(String indexName, String id, Object data) {
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            UpdateRequest request = new UpdateRequest(indexName, id)
                .doc(jsonData, XContentType.JSON);
            
            UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
            log.debug("更新文档成功: index={}, id={}", indexName, id);
            return response.status().getStatus() < 400;
        } catch (Exception e) {
            log.error("更新文档失败: index=" + indexName + ", id=" + id, e);
            return false;
        }
    }

    /**
     * 删除文档
     * 
     * @param indexName 索引名称
     * @param id 文档ID
     * @return 是否成功
     */
    public boolean deleteDocument(String indexName, String id) {
        try {
            DeleteRequest request = new DeleteRequest(indexName, id);
            DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
            log.debug("删除文档成功: index={}, id={}", indexName, id);
            return response.status().getStatus() < 400;
        } catch (IOException e) {
            log.error("删除文档失败: index=" + indexName + ", id=" + id, e);
            return false;
        }
    }

    /**
     * 根据查询条件删除文档
     * 
     * @param indexName 索引名称
     * @param queryBuilder 查询条件
     * @return 删除的文档数量
     */
    public long deleteByQuery(String indexName, QueryBuilder queryBuilder) {
        try {
            DeleteByQueryRequest request = new DeleteByQueryRequest(indexName);
            request.setQuery(queryBuilder);
            
            BulkByScrollResponse response = restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
            long deleted = response.getDeleted();
            log.info("根据查询删除文档成功: index={}, deleted={}", indexName, deleted);
            return deleted;
        } catch (IOException e) {
            log.error("根据查询删除文档失败: index=" + indexName, e);
            return 0;
        }
    }

    /**
     * 获取文档
     * 
     * @param indexName 索引名称
     * @param id 文档ID
     * @param clazz 返回类型
     * @return 文档对象
     */
    public <T> T getDocument(String indexName, String id, Class<T> clazz) {
        try {
            GetRequest request = new GetRequest(indexName, id);
            GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
            
            if (response.isExists()) {
                String sourceAsString = response.getSourceAsString();
                return objectMapper.readValue(sourceAsString, clazz);
            }
            return null;
        } catch (Exception e) {
            log.error("获取文档失败: index=" + indexName + ", id=" + id, e);
            return null;
        }
    }

    /**
     * 搜索文档
     * 
     * @param indexName 索引名称
     * @param queryBuilder 查询条件
     * @param from 起始位置
     * @param size 返回数量
     * @return 搜索结果
     */
    public SearchResult search(String indexName, QueryBuilder queryBuilder, int from, int size) {
        return search(indexName, queryBuilder, from, size, null, null, null);
    }

    /**
     * 搜索文档（带排序和高亮）
     * 
     * @param indexName 索引名称
     * @param queryBuilder 查询条件
     * @param from 起始位置
     * @param size 返回数量
     * @param sortField 排序字段
     * @param sortOrder 排序方向
     * @param highlightFields 高亮字段
     * @return 搜索结果
     */
    public SearchResult search(String indexName, QueryBuilder queryBuilder, int from, int size,
                               String sortField, SortOrder sortOrder, String[] highlightFields) {
        try {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            
            // 设置查询条件
            sourceBuilder.query(queryBuilder);
            sourceBuilder.from(from);
            sourceBuilder.size(Math.min(size, properties.getSearch().getMaxPageSize()));
            
            // 设置排序
            if (sortField != null && sortOrder != null) {
                sourceBuilder.sort(sortField, sortOrder);
            }
            
            // 设置高亮
            if (highlightFields != null && highlightFields.length > 0) {
                HighlightBuilder highlightBuilder = new HighlightBuilder();
                highlightBuilder.preTags(properties.getSearch().getHighlightPreTag());
                highlightBuilder.postTags(properties.getSearch().getHighlightPostTag());
                for (String field : highlightFields) {
                    highlightBuilder.field(field);
                }
                sourceBuilder.highlighter(highlightBuilder);
            }
            
            // 设置超时
            sourceBuilder.timeout(org.elasticsearch.common.unit.TimeValue.timeValueMillis(
                properties.getSearch().getTimeout()));
            
            searchRequest.source(sourceBuilder);
            
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            
            // 解析结果
            SearchResult result = new SearchResult();
            result.setTotal(response.getHits().getTotalHits().value);
            result.setTook(response.getTook().millis());
            
            List<Map<String, Object>> dataList = new ArrayList<>();
            for (SearchHit hit : response.getHits().getHits()) {
                Map<String, Object> data = new HashMap<>(hit.getSourceAsMap());
                data.put("_id", hit.getId());
                data.put("_score", hit.getScore());
                
                // 添加高亮结果
                if (hit.getHighlightFields() != null && !hit.getHighlightFields().isEmpty()) {
                    Map<String, String> highlights = new HashMap<>();
                    hit.getHighlightFields().forEach((key, value) -> 
                        highlights.put(key, value.fragments()[0].string())
                    );
                    data.put("_highlight", highlights);
                }
                
                dataList.add(data);
            }
            result.setData(dataList);
            
            log.debug("搜索成功: index={}, total={}, took={}ms", indexName, result.getTotal(), result.getTook());
            return result;
        } catch (IOException e) {
            log.error("搜索失败: index=" + indexName, e);
            return new SearchResult();
        }
    }

    /**
     * 获取RestHighLevelClient（用于高级操作）
     * 
     * @return RestHighLevelClient
     */
    public RestHighLevelClient getClient() {
        return restHighLevelClient;
    }
}