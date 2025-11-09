package org.jeecg.common.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * Elasticsearch 查询构建器（简化版占位符）
 * 
 * @author jeecg-boot
 */
@Slf4j
public class QueryStringBuilder {

    /**
     * 创建匹配所有查询
     */
    public static QueryBuilder matchAll() {
        return QueryBuilders.matchAllQuery();
    }

    /**
     * 创建精确匹配查询
     */
    public static QueryBuilder term(String field, Object value) {
        return QueryBuilders.termQuery(field, value);
    }

    /**
     * 创建模糊匹配查询
     */
    public static QueryBuilder match(String field, Object value) {
        return QueryBuilders.matchQuery(field, value);
    }

    /**
     * 创建范围查询
     */
    public static QueryBuilder range(String field, Object from, Object to) {
        return QueryBuilders.rangeQuery(field).from(from).to(to);
    }

    /**
     * 创建布尔查询
     */
    public static BoolQueryBuilder bool() {
        return QueryBuilders.boolQuery();
    }

    /**
     * 创建多字段查询
     */
    public static QueryBuilder multiMatch(Object value, String... fields) {
        return QueryBuilders.multiMatchQuery(value, fields);
    }

    /**
     * 创建通配符查询
     */
    public static QueryBuilder wildcard(String field, String value) {
        return QueryBuilders.wildcardQuery(field, value);
    }

    /**
     * 创建前缀查询
     */
    public static QueryBuilder prefix(String field, String value) {
        return QueryBuilders.prefixQuery(field, value);
    }

    /**
     * 创建存在查询
     */
    public static QueryBuilder exists(String field) {
        return QueryBuilders.existsQuery(field);
    }
}