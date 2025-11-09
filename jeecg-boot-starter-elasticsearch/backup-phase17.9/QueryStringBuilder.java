package org.jeecg.common.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Elasticsearch查询构建器
 * 提供常用查询条件的构建方法
 * 
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-11-08
 */
@Slf4j
public class QueryStringBuilder {

    /**
     * 创建匹配所有查询
     * 
     * @return 查询构建器
     */
    public static QueryBuilder matchAll() {
        return QueryBuilders.matchAllQuery();
    }

    /**
     * 创建精确匹配查询
     * 
     * @param field 字段名
     * @param value 字段值
     * @return 查询构建器
     */
    public static QueryBuilder term(String field, Object value) {
        return QueryBuilders.termQuery(field, value);
    }

    /**
     * 创建多值精确匹配查询
     * 
     * @param field 字段名
     * @param values 字段值列表
     * @return 查询构建器
     */
    public static QueryBuilder terms(String field, Object... values) {
        return QueryBuilders.termsQuery(field, values);
    }

    /**
     * 创建全文匹配查询
     * 
     * @param field 字段名
     * @param text 搜索文本
     * @return 查询构建器
     */
    public static QueryBuilder match(String field, String text) {
        return QueryBuilders.matchQuery(field, text);
    }

    /**
     * 创建多字段全文匹配查询
     * 
     * @param text 搜索文本
     * @param fields 字段名列表
     * @return 查询构建器
     */
    public static QueryBuilder multiMatch(String text, String... fields) {
        return QueryBuilders.multiMatchQuery(text, fields);
    }

    /**
     * 创建模糊查询
     * 
     * @param field 字段名
     * @param value 字段值
     * @return 查询构建器
     */
    public static QueryBuilder fuzzy(String field, String value) {
        return QueryBuilders.fuzzyQuery(field, value);
    }

    /**
     * 创建前缀查询
     * 
     * @param field 字段名
     * @param prefix 前缀
     * @return 查询构建器
     */
    public static QueryBuilder prefix(String field, String prefix) {
        return QueryBuilders.prefixQuery(field, prefix);
    }

    /**
     * 创建通配符查询
     * 
     * @param field 字段名
     * @param wildcard 通配符表达式（支持*和?）
     * @return 查询构建器
     */
    public static QueryBuilder wildcard(String field, String wildcard) {
        return QueryBuilders.wildcardQuery(field, wildcard);
    }

    /**
     * 创建范围查询
     * 
     * @param field 字段名
     * @param from 起始值（包含）
     * @param to 结束值（包含）
     * @return 查询构建器
     */
    public static QueryBuilder range(String field, Object from, Object to) {
        return QueryBuilders.rangeQuery(field).gte(from).lte(to);
    }

    /**
     * 创建范围查询（大于等于）
     * 
     * @param field 字段名
     * @param value 比较值
     * @return 查询构建器
     */
    public static QueryBuilder gte(String field, Object value) {
        return QueryBuilders.rangeQuery(field).gte(value);
    }

    /**
     * 创建范围查询（小于等于）
     * 
     * @param field 字段名
     * @param value 比较值
     * @return 查询构建器
     */
    public static QueryBuilder lte(String field, Object value) {
        return QueryBuilders.rangeQuery(field).lte(value);
    }

    /**
     * 创建存在查询（字段不为null）
     * 
     * @param field 字段名
     * @return 查询构建器
     */
    public static QueryBuilder exists(String field) {
        return QueryBuilders.existsQuery(field);
    }

    /**
     * 创建布尔查询（AND组合）
     * 
     * @param queries 查询列表
     * @return 查询构建器
     */
    public static QueryBuilder boolMust(QueryBuilder... queries) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        for (QueryBuilder query : queries) {
            boolQuery.must(query);
        }
        return boolQuery;
    }

    /**
     * 创建布尔查询（OR组合）
     * 
     * @param queries 查询列表
     * @return 查询构建器
     */
    public static QueryBuilder boolShould(QueryBuilder... queries) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        for (QueryBuilder query : queries) {
            boolQuery.should(query);
        }
        return boolQuery;
    }

    /**
     * 创建布尔查询（NOT组合）
     * 
     * @param queries 查询列表
     * @return 查询构建器
     */
    public static QueryBuilder boolMustNot(QueryBuilder... queries) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        for (QueryBuilder query : queries) {
            boolQuery.mustNot(query);
        }
        return boolQuery;
    }

    /**
     * 创建复合布尔查询
     * 
     * @return 布尔查询构建器
     */
    public static BoolQueryBuilder bool() {
        return QueryBuilders.boolQuery();
    }

    /**
     * 根据查询字符串构建查询
     * 支持格式：field:value, field>value, field<value, field:value1,value2
     * 
     * @param queryString 查询字符串
     * @return 查询构建器
     */
    public static QueryBuilder buildFromString(String queryString) {
        if (queryString == null || queryString.trim().isEmpty()) {
            return matchAll();
        }

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        String[] parts = queryString.split("\\s+AND\\s+|\\s+and\\s+");

        for (String part : parts) {
            part = part.trim();
            if (part.isEmpty()) {
                continue;
            }

            QueryBuilder query = parseSingleQuery(part);
            if (query != null) {
                boolQuery.must(query);
            }
        }

        return boolQuery.hasClauses() ? boolQuery : matchAll();
    }

    /**
     * 解析单个查询条件
     * 
     * @param queryPart 查询部分
     * @return 查询构建器
     */
    private static QueryBuilder parseSingleQuery(String queryPart) {
        try {
            if (queryPart.contains(">=")) {
                String[] kv = queryPart.split(">=");
                return gte(kv[0].trim(), kv[1].trim());
            } else if (queryPart.contains("<=")) {
                String[] kv = queryPart.split("<=");
                return lte(kv[0].trim(), kv[1].trim());
            } else if (queryPart.contains(">")) {
                String[] kv = queryPart.split(">");
                return QueryBuilders.rangeQuery(kv[0].trim()).gt(kv[1].trim());
            } else if (queryPart.contains("<")) {
                String[] kv = queryPart.split("<");
                return QueryBuilders.rangeQuery(kv[0].trim()).lt(kv[1].trim());
            } else if (queryPart.contains(":")) {
                String[] kv = queryPart.split(":", 2);
                String field = kv[0].trim();
                String value = kv[1].trim();
                
                // 支持多值查询（逗号分隔）
                if (value.contains(",")) {
                    String[] values = value.split(",");
                    return terms(field, (Object[]) values);
                }
                
                // 支持通配符
                if (value.contains("*") || value.contains("?")) {
                    return wildcard(field, value);
                }
                
                // 默认精确匹配
                return term(field, value);
            }
            
            return null;
        } catch (Exception e) {
            log.error("解析查询条件失败: " + queryPart, e);
            return null;
        }
    }
}