# jeecg-boot-starter-elasticsearch

Elasticsearch搜索引擎集成模块，提供全文搜索、数据同步、索引管理等功能。

## 模块说明

本模块是 JeecgBoot 4.0 模块化拆分的一部分，专门提供 Elasticsearch 搜索引擎的集成支持。

## 功能特性

- 索引管理 - 创建、删除、判断索引存在
- 文档操作 - 增删改查、批量操作
- 全文搜索 - 支持多种查询方式
- 高亮显示 - 搜索结果关键词高亮
- 分页排序 - 支持分页和自定义排序
- 查询构建器 - 简化复杂查询条件的构建

## 快速开始

### 添加依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-elasticsearch</artifactId>
    <version>4.0.0</version>
</dependency>
```

### 配置文件

```yaml
jeecg:
  elasticsearch:
    enabled: true
    cluster-nodes:
      - localhost:9200
    username: elastic
    password: your_password
```

### 使用示例

```java
@Autowired
private JeecgElasticsearchTemplate esTemplate;

// 创建索引
esTemplate.createIndex("articles");

// 添加文档
esTemplate.addDocument("articles", "1", article);

// 搜索
QueryBuilder query = QueryStringBuilder.match("title", "Java");
SearchResult result = esTemplate.search("articles", query, 0, 10);
```

## 许可证

Apache License 2.0