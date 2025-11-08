# Jeecg Boot Starter Datasource

## 模块概述

`jeecg-boot-starter-datasource` 是 JeecgBoot 框架的数据源自动配置模块，提供 Druid 数据源配置、动态数据源支持、SQL 监控和防火墙功能。

## 功能特性

### 🔥 核心功能

- **Druid 连接池**: 高性能数据库连接池，内置监控统计
- **SQL 监控**: 实时监控 SQL 执行情况、慢查询分析
- **SQL 防火墙**: 防止 SQL 注入攻击，支持自定义规则
- **动态数据源**: 运行时动态创建和切换数据源
- **数据源缓存**: 缓存动态创建的数据源，提高性能
- **CORS 支持**: 跨域资源共享配置
- **请求体缓存**: 支持多次读取 HttpServletRequest Body

### 📦 包含组件

```
jeecg-boot-starter-datasource/
├── config/datasource/           # 数据源配置
│   ├── DruidConfig             # Druid 配置
│   ├── DruidWallConfigRegister # SQL 防火墙配置
│   ├── CorsFilterCondition     # CORS 条件配置
│   └── JeecgDatasourceAutoConfiguration  # 自动配置类
├── config/filter/              # 过滤器
│   └── RequestBodyReserveFilter  # 请求体保留过滤器
└── common/util/dynamic/db/     # 动态数据源工具
    ├── DataSourceCachePool     # 数据源缓存池
    ├── DynamicDBUtil          # 动态数据库工具
    ├── DbTypeUtils            # 数据库类型工具
    └── FreemarkerParseFactory # SQL 模板解析
```

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-starter-datasource</artifactId>
    <version>4.0.0</version>
</dependency>
```

### 2. 配置数据源

#### application.yml 配置示例

```yaml
# Spring Boot 数据源配置
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/jeecg-boot?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: root
    
    # Druid 配置
    druid:
      initial-size: 5
      min-idle: 5
      max-active: 20
      max-wait: 60000
      time-between-eviction-runs-millis: 60000
      min-evictable-idle-time-millis: 300000
      validation-query: SELECT 1
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false
      pool-prepared-statements: true
      max-pool-prepared-statement-per-connection-size: 20

# Jeecg 数据源配置
jeecg:
  datasource:
    enable: true
    
    # Druid 监控配置
    druid:
      stat-view-servlet: true
      stat-view-servlet-url-pattern: /druid/*
      stat-view-servlet-login-username: admin
      stat-view-servlet-login-password: 123456
      web-stat-filter: true
      web-stat-filter-exclusions: "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"
      wall-enabled: true
      multi-statement-allow: true
    
    # 动态数据源配置
    dynamic:
      enable: false
      cache: true
      cache-expire-minutes: 30
    
    # CORS 跨域配置
    cors:
      enable: true
      allowed-origins: "*"
      allowed-methods: "GET,POST,PUT,DELETE,OPTIONS"
      allowed-headers: "*"
      allow-credentials: true
      max-age: 3600
```

### 3. 访问 Druid 监控

启动应用后，访问：

```
http://localhost:8080/druid/
```

使用配置的用户名密码登录（默认：admin/123456）

## 使用指南

### Druid 监控使用

Druid 提供了强大的监控功能：

- **数据源监控**: 查看连接池状态、活跃连接数等
- **SQL 监控**: 查看 SQL 执行次数、执行时间、慢查询
- **Web 应用监控**: 查看 URL 访问统计
- **Session 监控**: 查看会话信息
- **Spring 监控**: 查看 Spring 方法调用统计

### 动态数据源使用

```java
import org.jeecg.common.util.dynamic.db.DynamicDBUtil;
import org.jeecg.common.util.dynamic.db.DataSourceCachePool;
import org.jeecg.common.system.vo.DynamicDataSourceModel;

@Service
public class DynamicDataSourceService {
    
    /**
     * 创建动态数据源
     */
    public DataSource createDynamicDataSource() {
        DynamicDataSourceModel model = new DynamicDataSourceModel();
        model.setDbType("mysql");
        model.setUrl("jdbc:mysql://localhost:3306/tenant_db");
        model.setUsername("root");
        model.setPassword("password");
        model.setDbDriver("com.mysql.cj.jdbc.Driver");
        
        return DynamicDBUtil.createDataSource(model);
    }
    
    /**
     * 使用数据源缓存池
     */
    public void useCachedDataSource() {
        DataSourceCachePool pool = DataSourceCachePool.getInstance();
        
        // 缓存数据源
        DataSource ds = createDynamicDataSource();
        pool.putDataSource("tenant1", ds);
        
        // 获取缓存的数据源
        DataSource cachedDs = pool.getDataSource("tenant1");
        
        // 移除数据源
        pool.removeDataSource("tenant1");
    }
}
```

### 数据库类型判断

```java
import org.jeecg.common.util.dynamic.db.DbTypeUtils;

// 判断数据库类型
String dbType = DbTypeUtils.getDatabaseType();
if ("mysql".equalsIgnoreCase(dbType)) {
    // MySQL 特定逻辑
} else if ("postgresql".equalsIgnoreCase(dbType)) {
    // PostgreSQL 特定逻辑
}

// 根据 JDBC URL 判断
String url = "jdbc:mysql://localhost:3306/test";
String type = DbTypeUtils.getDatabaseTypeByUrl(url); // 返回 "mysql"
```

### SQL 模板解析

```java
import org.jeecg.common.util.dynamic.db.FreemarkerParseFactory;
import java.util.HashMap;
import java.util.Map;

// 准备模板变量
Map<String, Object> params = new HashMap<>();
params.put("tableName", "sys_user");
params.put("userName", "admin");

// SQL 模板
String sqlTemplate = "SELECT * FROM ${tableName} WHERE username = '${userName}'";

// 解析模板
String sql = FreemarkerParseFactory.parseTemplateContent(sqlTemplate, params);
// 结果: SELECT * FROM sys_user WHERE username = 'admin'
```

## 配置属性说明

### jeecg.datasource.druid

| 属性 | 类型 | 默认值 | 说明 |
|-----|------|--------|------|
| stat-view-servlet | Boolean | true | 是否启用监控页面 |
| stat-view-servlet-url-pattern | String | /druid/* | 监控页面访问路径 |
| stat-view-servlet-login-username | String | admin | 监控页面登录用户名 |
| stat-view-servlet-login-password | String | 123456 | 监控页面登录密码 |
| web-stat-filter | Boolean | true | 是否启用 Web 监控 |
| web-stat-filter-exclusions | String | *.js,*.gif,... | Web 监控排除路径 |
| wall-enabled | Boolean | true | 是否启用 SQL 防火墙 |
| multi-statement-allow | Boolean | true | 是否允许多语句执行 |

### jeecg.datasource.dynamic

| 属性 | 类型 | 默认值 | 说明 |
|-----|------|--------|------|
| enable | Boolean | false | 是否启用动态数据源 |
| cache | Boolean | true | 是否启用缓存 |
| cache-expire-minutes | Integer | 30 | 缓存过期时间（分钟） |

### jeecg.datasource.cors

| 属性 | 类型 | 默认值 | 说明 |
|-----|------|--------|------|
| enable | Boolean | true | 是否启用 CORS |
| allowed-origins | String | * | 允许的源 |
| allowed-methods | String | GET,POST,PUT,DELETE,OPTIONS | 允许的方法 |
| allowed-headers | String | * | 允许的请求头 |
| allow-credentials | Boolean | true | 是否允许携带凭证 |
| max-age | Long | 3600 | 预检请求缓存时间（秒） |

## 依赖说明

### 必需依赖

- `jeecg-boot-base-constants`: 常量定义
- `jeecg-boot-base-api`: API 接口定义
- `jeecg-boot-base-utils`: 工具类
- `jeecg-boot-base-core-lite`: 轻量核心
- `spring-boot-starter-jdbc`: Spring JDBC
- `druid-spring-boot-3-starter`: Druid 连接池

### 可选依赖

- `dynamic-datasource-spring-boot3-starter`: 动态数据源增强（多数据源切换）
- `mysql-connector-j`: MySQL 驱动
- `postgresql`: PostgreSQL 驱动

## 最佳实践

### 1. 生产环境配置

```yaml
jeecg:
  datasource:
    druid:
      # 生产环境建议关闭监控或设置访问控制
      stat-view-servlet: false
      # 或者使用强密码
      stat-view-servlet-login-username: ${DRUID_USERNAME}
      stat-view-servlet-login-password: ${DRUID_PASSWORD}
```

### 2. 性能优化

```yaml
spring:
  datasource:
    druid:
      # 根据业务量调整连接池大小
      initial-size: 10
      min-idle: 10
      max-active: 50
      
      # 启用 PSCache 提升性能
      pool-prepared-statements: true
      max-pool-prepared-statement-per-connection-size: 20
      
      # 优化连接检测
      test-while-idle: true
      time-between-eviction-runs-millis: 60000
```

### 3. 安全配置

```yaml
jeecg:
  datasource:
    druid:
      # 启用 SQL 防火墙
      wall-enabled: true
      
      # 根据需要配置是否允许多语句
      multi-statement-allow: false
```

## 常见问题

### Q1: 如何禁用 Druid 监控？

```yaml
jeecg:
  datasource:
    druid:
      stat-view-servlet: false
      web-stat-filter: false
```

### Q2: 如何自定义监控页面访问路径？

```yaml
jeecg:
  datasource:
    druid:
      stat-view-servlet-url-pattern: /monitor/druid/*
```

### Q3: 动态数据源如何配置？

参考"动态数据源使用"章节的代码示例。

### Q4: 如何配置多数据源？

建议使用 `dynamic-datasource-spring-boot3-starter` 配合本模块使用。

## 版本历史

### v4.0.0 (2025-11-08)

- ✨ 初始版本发布
- ✅ 支持 Druid 数据源配置
- ✅ 支持 SQL 监控和防火墙
- ✅ 支持动态数据源
- ✅ 支持数据源缓存池
- ✅ 支持 CORS 配置

## 许可证

Apache License 2.0

## 相关链接

- [JeecgBoot 官网](http://www.jeecg.com)
- [Druid 官方文档](https://github.com/alibaba/druid)
- [Spring Boot 文档](https://spring.io/projects/spring-boot)

## 技术支持

- 📧 Email: jeecg@qq.com
- 💬 QQ群: 284271917
- 🌐 官网: http://www.jeecg.com