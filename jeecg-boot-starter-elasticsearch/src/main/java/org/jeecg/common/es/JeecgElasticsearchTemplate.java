package org.jeecg.common.es;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
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
import org.elasticsearch.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.jeecg.config.elasticsearch.JeecgElasticsearchProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Elasticsearch 模板类（简化版占位符）
 * 
 * @author jeecg-boot
 */
@Slf4j
public class JeecgElasticsearchTemplate {

    private final RestHighLevelClient restHighLevelClient;
    private final JeecgElasticsearchProperties properties;

    public JeecgElasticsearchTemplate(RestHighLevelClient restHighLevelClient,
                                     JeecgElasticsearchProperties properties) {
        this.restHighLevelClient = restHighLevelClient;
        this.properties = properties;
    }

    /**
     * 创建索引（简化版）
     */
    public boolean createIndex(String indexName, String mapping) {
        log.info("创建索引（简化版）: {}", indexName);
        try {
            CreateIndexRequest request = new CreateIndexRequest(indexName);
            if (mapping != null && !mapping.isEmpty()) {
                request.mapping(mapping, XContentType.JSON);
            }
            CreateIndexResponse response = restHighLevelClient.indices()
                .create(request, RequestOptions.DEFAULT);
            return response.isAcknowledged();
        } catch (Exception e) {
            log.error("创建索引失败: {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 判断索引是否存在
     */
    public boolean existsIndex(String indexName) {
        try {
            GetIndexRequest request = new GetIndexRequest(indexName);
            return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("判断索引是否存在失败: {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 删除索引
     */
    public boolean deleteIndex(String indexName) {
        log.info("删除索引: {}", indexName);
        try {
            DeleteIndexRequest request = new DeleteIndexRequest(indexName);
            AcknowledgedResponse response = restHighLevelClient.indices()
                .delete(request, RequestOptions.DEFAULT);
            return response.isAcknowledged();
        } catch (Exception e) {
            log.error("删除索引失败: {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 插入文档
     */
    public String insertDocument(String indexName, String id, Object document) {
        log.debug("插入文档到索引 {}, id: {}", indexName, id);
        try {
            IndexRequest request = new IndexRequest(indexName);
            if (id != null && !id.isEmpty()) {
                request.id(id);
            }
            String jsonData = JSON.toJSONString(document);
            request.source(jsonData, XContentType.JSON);
            
            IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            return response.getId();
        } catch (Exception e) {
            log.error("插入文档失败: {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 批量插入文档（简化版）
     */
    public boolean bulkInsert(String indexName, List<Map<String, Object>> documents) {
        log.info("批量插入文档到索引: {}, 数量: {}", indexName, documents.size());
        try {
            for (Map<String, Object> doc : documents) {
                String id = doc.get("id") != null ? doc.get("id").toString() : null;
                insertDocument(indexName, id, doc);
            }
            return true;
        } catch (Exception e) {
            log.error("批量插入文档失败: {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 更新文档
     */
    public boolean updateDocument(String indexName, String id, Object document) {
        log.debug("更新文档: 索引={}, id={}", indexName, id);
        try {
            UpdateRequest request = new UpdateRequest(indexName, id);
            String jsonData = JSON.toJSONString(document);
            request.doc(jsonData, XContentType.JSON);
            
            UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
            return response.getResult().toString().equals("UPDATED");
        } catch (Exception e) {
            log.error("更新文档失败: {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 删除文档
     */
    public boolean deleteDocument(String indexName, String id) {
        log.debug("删除文档: 索引={}, id={}", indexName, id);
        try {
            DeleteRequest request = new DeleteRequest(indexName, id);
            DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
            return response.getResult().toString().equals("DELETED");
        } catch (Exception e) {
            log.error("删除文档失败: {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 根据ID查询文档
     */
    public Map<String, Object> getDocumentById(String indexName, String id) {
        log.debug("根据ID查询文档: 索引={}, id={}", indexName, id);
        try {
            GetRequest request = new GetRequest(indexName, id);
            GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
            if (response.isExists()) {
                return response.getSourceAsMap();
            }
            return null;
        } catch (Exception e) {
            log.error("查询文档失败: {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 搜索文档（简化版）
     */
    public SearchResult search(String indexName, QueryBuilder queryBuilder, int from, int size) {
        log.debug("搜索文档: 索引={}, from={}, size={}", indexName, from, size);
        try {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(queryBuilder);
            sourceBuilder.from(from);
            sourceBuilder.size(size);
            searchRequest.source(sourceBuilder);
            
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            
            SearchResult result = new SearchResult();
            result.setTotal(response.getHits().getTotalHits().value);
            
            List<Map<String, Object>> dataList = new ArrayList<>();
            for (SearchHit hit : response.getHits().getHits()) {
                dataList.add(hit.getSourceAsMap());
            }
            result.setData(dataList);
            
            return result;
        } catch (Exception e) {
            log.error("搜索文档失败: {}", e.getMessage(), e);
            return new SearchResult();
        }
    }

    /**
     * 关闭客户端
     */
    public void close() {
        try {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        } catch (IOException e) {
            log.error("关闭Elasticsearch客户端失败: {}", e.getMessage(), e);
        }
    }
}