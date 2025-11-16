# Jeecg-Boot Elasticsearch Starter

这是 JeecgBoot 的 Elasticsearch 集成模块,提供了 Elasticsearch 的基础操作功能。

## 功能特性

- Elasticsearch 索引管理(创建、删除、查询)
- 数据的增删改查操作
- 批量数据操作
- 查询构建器
- 索引映射管理

## 使用方法

### 1. 添加依赖

在需要使用 Elasticsearch 功能的模块的 `pom.xml` 中添加依赖:

```xml
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-starter-elasticsearch</artifactId>
</dependency>
```

### 2. 配置

在 `application.yml` 中添加 Elasticsearch 配置:

```yaml
jeecg:
  elasticsearch:
    cluster-nodes: localhost:9200  # ES 服务地址
    check-enabled: true             # 是否启动时检查 ES 连接
```

### 3. 使用

注入 `JeecgElasticsearchTemplate` 即可使用:

```java
@Autowired
private JeecgElasticsearchTemplate elasticsearchTemplate;

// 创建索引
elasticsearchTemplate.createIndex("my_index");

// 保存数据
JSONObject data = new JSONObject();
data.put("name", "张三");
data.put("age", 25);
elasticsearchTemplate.save("my_index", "my_type", "1", data);

// 查询数据
JSONObject result = elasticsearchTemplate.getDataById("my_index", "my_type", "1");
```

## 主要类说明

- **JeecgElasticsearchTemplate**: Elasticsearch 操作模板类,提供索引管理和数据操作的核心方法
- **QueryStringBuilder**: 查询字符串构建器,用于构建 Elasticsearch 查询语句
- **Elasticsearch**: 配置类,用于加载 Elasticsearch 相关配置
- **ElasticsearchAutoConfiguration**: 自动配置类,负责自动装配 Elasticsearch 组件

## 注意事项

1. 使用前确保 Elasticsearch 服务已启动
2. 支持 Elasticsearch 7.x 版本
3. 如果不配置 `jeecg.elasticsearch.cluster-nodes`,相关 Bean 不会被创建
4. 建议在生产环境将 `check-enabled` 设置为 `true`,以便及时发现连接问题

## 迁移说明

该模块从 `jeecg-boot-base-core` 模块迁移而来,原来的类路径保持不变:
- `org.jeecg.config.es.JeecgElasticsearchTemplate`
- `org.jeecg.common.es.QueryStringBuilder`
- `org.jeecg.config.vo.Elasticsearch`

如果您的项目之前直接依赖 `jeecg-boot-base-core` 并使用了 Elasticsearch 功能,请添加本 starter 的依赖。