# jeecg-boot-starter-api-doc

## 模块简介

`jeecg-boot-starter-api-doc` 是 JeecgBoot 的 API 文档 Starter 模块，提供自动化的 API 文档生成和管理功能。

### 核心特性

- ✅ **多框架支持**: 支持 Swagger3、Knife4j、SpringDoc 三种文档框架
- ✅ **自动配置**: Spring Boot 自动配置，开箱即用
- ✅ **可选依赖**: 按需引入文档框架，减少依赖冲突
- ✅ **环境感知**: 生产环境自动禁用，保护接口安全
- ✅ **JWT集成**: 内置 JWT 认证支持
- ✅ **向后兼容**: 兼容旧版配置格式
- ✅ **配置灵活**: 支持多种自定义配置

## 快速开始

### 1. 添加依赖

#### Maven

```xml
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-api-doc</artifactId>
    <version>4.0.0</version>
</dependency>

<!-- 可选：选择文档框架（至少选择一个） -->
<!-- Knife4j（推荐） -->
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-openapi3-spring-boot-starter</artifactId>
    <version>4.1.0</version>
</dependency>

<!-- 或者使用 Swagger3 -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>

<!-- 或者使用 SpringDoc -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.15</version>
</dependency>
```

### 2. 配置文件

在 `application.yml` 中添加配置：

```yaml
jeecg:
  api-doc:
    enabled: true                         # 是否启用
    type: knife4j                         # 文档类型: swagger3, knife4j, springdoc
    production: false                     # 生产环境是否启用
    title: "项目 API 文档"
    description: "项目接口文档说明"
    version: "1.0.0"
    base-package: "com.example.project"  # 扫描的包路径
    
    # 联系人信息
    contact:
      name: "开发团队"
      url: "https://example.com"
      email: "dev@example.com"
    
    # 许可证信息
    license:
      name: "Apache License 2.0"
      url: "https://www.apache.org/licenses/LICENSE-2.0"
    
    # Knife4j 增强配置
    knife4j:
      enable: true
      production: false
      basic-enable: false                # 是否开启 Basic 认证
      basic-username: admin
      basic-password: 123456
```

### 3. 使用注解

在 Controller 中使用 Swagger 注解：

```java
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户管理")
public class UserController {
    
    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询用户", notes = "查询单个用户详情")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
    public Result<User> getUser(@PathVariable String id) {
        // 业务逻辑
        return Result.ok(user);
    }
    
    @PostMapping
    @ApiOperation(value = "创建用户", notes = "新增一个用户")
    public Result<String> createUser(@RequestBody @ApiParam("用户信息") User user) {
        // 业务逻辑
        return Result.ok("创建成功");
    }
}
```

### 4. 访问文档

启动应用后，访问以下地址：

- **Knife4j**: http://localhost:8080/doc.html
- **Swagger3**: http://localhost:8080/swagger-ui/index.html
- **SpringDoc**: http://localhost:8080/swagger-ui.html

## 配置说明

### 基础配置

| 配置项 | 类型 | 默认值 | 说明 |
|-------|------|--------|------|
| `jeecg.api-doc.enabled` | Boolean | true | 是否启用 API 文档 |
| `jeecg.api-doc.type` | String | knife4j | 文档类型：swagger3, knife4j, springdoc |
| `jeecg.api-doc.production` | Boolean | false | 生产环境是否启用 |
| `jeecg.api-doc.title` | String | JeecgBoot API文档 | 文档标题 |
| `jeecg.api-doc.description` | String | - | 文档描述 |
| `jeecg.api-doc.version` | String | 4.0.0 | API 版本 |
| `jeecg.api-doc.base-package` | String | org.jeecg | 扫描的包路径 |

### 联系人配置

| 配置项 | 类型 | 默认值 | 说明 |
|-------|------|--------|------|
| `jeecg.api-doc.contact.name` | String | JeecgBoot Team | 联系人名称 |
| `jeecg.api-doc.contact.url` | String | http://www.jeecg.com | 联系人 URL |
| `jeecg.api-doc.contact.email` | String | jeecgos@163.com | 联系人邮箱 |

### Knife4j 增强配置

| 配置项 | 类型 | 默认值 | 说明 |
|-------|------|--------|------|
| `jeecg.api-doc.knife4j.enable` | Boolean | true | 是否启用 Knife4j 增强 |
| `jeecg.api-doc.knife4j.production` | Boolean | false | 生产环境是否屏蔽 |
| `jeecg.api-doc.knife4j.basic-enable` | Boolean | false | 是否开启 Basic 认证 |
| `jeecg.api-doc.knife4j.basic-username` | String | admin | Basic 认证用户名 |
| `jeecg.api-doc.knife4j.basic-password` | String | 123456 | Basic 认证密码 |

## 高级用法

### 1. 环境隔离

生产环境自动禁用文档：

```yaml
spring:
  profiles:
    active: prod

jeecg:
  api-doc:
    production: false  # 生产环境强制禁用
```

或通过环境变量控制：

```bash
export JEECG_API_DOC_ENABLED=false
```

### 2. JWT 认证集成

模块已内置 JWT 支持，访问文档时可输入 Token：

```java
// 在 Swagger UI 中，点击 "Authorize" 按钮
// 输入格式: Bearer <your-token>
```

### 3. 分组配置

```yaml
jeecg:
  api-doc:
    group:
      enabled: true
      groups:
        - name: "系统管理"
          base-package: "com.example.system"
        - name: "业务模块"
          base-package: "com.example.business"
```

### 4. 自定义 Docket

```java
@Configuration
public class CustomSwaggerConfig {
    
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("自定义分组")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.custom"))
                .paths(PathSelectors.ant("/custom/**"))
                .build();
    }
}
```

## 常见问题

### 1. 文档无法访问？

**原因**: 可能配置未生效或端口号错误

**解决**:
- 检查 `jeecg.api-doc.enabled=true`
- 确认选择的文档框架依赖已添加
- 检查端口号和路径是否正确

### 2. 生产环境如何禁用？

**方法1**: 配置文件
```yaml
jeecg:
  api-doc:
    production: false
```

**方法2**: 环境变量
```bash
export SPRING_PROFILES_ACTIVE=prod
```

### 3. 如何自定义 JWT Header？

```java
@Configuration
public class CustomSecurityConfig {
    
    @Bean
    public SecurityScheme customSecurityScheme() {
        return new ApiKey("Authorization", "X-Token", "header");
    }
}
```

### 4. 旧版配置迁移

**旧配置**:
```yaml
knife4j:
  enable: true
swagger:
  enable: true
```

**新配置**:
```yaml
jeecg:
  api-doc:
    enabled: true
    type: knife4j
```

## 迁移指南

从旧版本升级：

### 步骤1: 更新依赖

```xml
<!-- 移除旧依赖 -->
<!--
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-core</artifactId>
</dependency>
-->

<!-- 添加新依赖 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-api-doc</artifactId>
    <version>4.0.0</version>
</dependency>
```

### 步骤2: 更新配置

参考上面的配置说明，将旧配置迁移到新格式。

### 步骤3: 测试验证

启动应用，访问文档地址，确认功能正常。

## 技术栈

- Spring Boot 2.7.x
- Springfox 3.0.0（Swagger3）
- Knife4j 4.1.0
- SpringDoc 1.6.15

## 依赖关系

```
jeecg-boot-starter-api-doc
├── jeecg-boot-base-constants (必选)
├── Spring Boot Starter Web (必选)
├── Springfox (可选)
├── Knife4j (可选)
└── SpringDoc (可选)
```

## 模块结构

```
jeecg-boot-starter-api-doc/
├── src/main/java/org/jeecg/config/
│   ├── JeecgApiDocProperties.java        # 配置属性类
│   ├── Knife4jAutoConfiguration.java      # Knife4j 自动配置
│   ├── Swagger2Config.java                # Swagger2 配置（已过时）
│   └── Swagger3Config.java                # Swagger3 配置（已过时）
├── src/main/resources/
│   ├── META-INF/spring/
│   │   └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
│   ├── META-INF/
│   │   └── spring-configuration-metadata.json
│   └── config/
│       └── default-spring-doc.properties
├── pom.xml
└── README.md
```

## 版本历史

### v4.0.0 (2025-01)
- ✨ 全新模块化架构
- ✨ 支持多文档框架
- ✨ 可选依赖设计
- ✨ 环境感知功能
- ✨ 向后兼容支持

## 贡献指南

欢迎提交 Issue 和 Pull Request！

## 许可证

Apache License 2.0

## 联系方式

- 官网: http://www.jeecg.com
- 文档: http://doc.jeecg.com
- GitHub: https://github.com/jeecgboot/jeecg-boot
- QQ群: 284271917

---

**注意**: 本模块是 jeecg-boot-base-core 模块拆分计划的一部分，旨在提供更灵活、更易维护的模块化架构。