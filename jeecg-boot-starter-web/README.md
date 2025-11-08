# jeecg-boot-starter-web

## 模块简介

`jeecg-boot-starter-web` 是 JeecgBoot 框架的 Web 增强 Starter 模块，提供了一系列企业级 Web 开发的增强功能，包括 CORS 跨域、防火墙、自动日志、字典翻译、数据权限、全局异常处理等。

## 核心功能

### 1. CORS 跨域支持
- ✅ 灵活的跨域配置
- ✅ 支持通配符和精确匹配
- ✅ 预检请求缓存优化

### 2. 防火墙功能
- ✅ SQL 注入检查
- ✅ XSS 攻击检查
- ✅ 低代码模式限制
- ✅ URL 黑白名单

### 3. 自动日志
- ✅ 自动记录请求参数和响应
- ✅ 执行时间统计
- ✅ 慢请求警告
- ✅ 异常日志记录

### 4. 字典翻译
- ✅ 自动翻译字典字段
- ✅ 支持同步/异步翻译
- ✅ 缓存支持

### 5. 数据权限
- ✅ 自动过滤数据权限
- ✅ 基于用户/部门的数据隔离
- ✅ 灵活的排除规则

### 6. 全局异常处理
- ✅ 统一异常响应格式
- ✅ 支持多种异常类型
- ✅ 友好的错误提示

### 7. Undertow 服务器优化
- ✅ 高性能 Web 服务器
- ✅ 灵活的线程池配置
- ✅ HTTP/2 支持

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-web</artifactId>
    <version>4.0.0</version>
</dependency>
```

### 2. 配置文件

```yaml
jeecg:
  web:
    # 总开关（默认启用）
    enabled: true
    
    # CORS 跨域配置
    cors:
      enabled: true
      allowed-origins: "*"
      allowed-methods: "*"
      allowed-headers: "*"
      allow-credentials: true
      max-age: 3600
    
    # 防火墙配置
    firewall:
      enabled: true
      low-code-mode: false
      sql-injection-check: true
      xss-check: true
      whitelist-urls:
        - /login
        - /register
      blacklist-urls: []
    
    # 自动日志配置
    log:
      enabled: true
      log-args: true
      log-result: true
      log-time: true
      slow-request-threshold: 3000
      exclude-urls:
        - /health
        - /metrics
    
    # 字典翻译配置
    dict:
      enabled: true
      async: false
      cache-seconds: 300
    
    # 数据权限配置
    permission:
      enabled: true
      strict: false
      exclude-tables:
        - sys_dict
        - sys_config
    
    # Undertow 服务器配置
    undertow:
      io-threads: 16
      worker-threads: 256
      buffer-size: 1024
      direct-buffers: true
```

### 3. 使用示例

#### 3.1 字典翻译

```java
@RestController
@RequestMapping("/user")
public class UserController {
    
    @GetMapping("/list")
    @Dict  // 自动翻译字典字段
    public Result<List<User>> list() {
        List<User> users = userService.list();
        // 返回数据会自动添加 _dictText 后缀字段
        // 如: sex=1 -> sex_dictText=男
        return Result.OK(users);
    }
}
```

#### 3.2 数据权限

```java
@RestController
@RequestMapping("/data")
public class DataController {
    
    @GetMapping("/list")
    @PermissionData  // 自动过滤数据权限
    public Result<List<Data>> list() {
        // 只返回当前用户有权限的数据
        return Result.OK(dataService.list());
    }
}
```

#### 3.3 自定义异常

```java
@RestController
@RequestMapping("/business")
public class BusinessController {
    
    @GetMapping("/operation")
    public Result<?> operation() {
        if (condition) {
            // 抛出业务异常，会被全局异常处理器捕获
            throw new JeecgBootException("操作失败");
        }
        return Result.OK("操作成功");
    }
}
```

## 高级配置

### 1. 自定义异常处理

```java
@Component
public class CustomExceptionHandler extends JeecgBootExceptionHandler {
    
    @Override
    @ExceptionHandler(CustomException.class)
    public Result<?> handleCustomException(CustomException e) {
        log.error("自定义异常: {}", e.getMessage());
        return Result.error(e.getMessage());
    }
}
```

### 2. 自定义防火墙规则

```java
@Component
public class CustomFirewallInterceptor extends LowCodeModeInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
                           HttpServletResponse response, 
                           Object handler) throws Exception {
        // 自定义拦截逻辑
        String uri = request.getRequestURI();
        if (isBlocked(uri)) {
            throw new JeecgBootException("访问被拒绝");
        }
        return super.preHandle(request, response, handler);
    }
}
```

### 3. 自定义日志格式

可以通过实现 `HandlerInterceptor` 接口来自定义日志格式：

```java
@Component
public class CustomLogInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
                           HttpServletResponse response, 
                           Object handler) {
        // 自定义日志记录
        log.info("自定义日志: {}", request.getRequestURI());
        return true;
    }
}
```

## 依赖关系

```
jeecg-boot-starter-web
├── jeecg-boot-base-constants (必选)
├── jeecg-boot-base-api (必选)
├── jeecg-boot-base-utils (必选)
├── jeecg-boot-base-core-lite (必选)
├── spring-boot-starter-web (必选)
├── spring-boot-starter-aop (必选)
├── jackson-databind (必选)
└── spring-boot-starter-undertow (可选)
```

## 注意事项

1. **防火墙配置**: 生产环境建议启用 SQL 注入和 XSS 检查
2. **日志配置**: 注意排除不需要记录的 URL，避免日志量过大
3. **字典翻译**: 建议启用缓存，提高性能
4. **数据权限**: 严格模式会对性能有一定影响
5. **Undertow**: 线程数根据实际服务器配置调整

## 性能优化建议

1. **启用 Undertow**: 比 Tomcat 性能更好
2. **配置线程池**: 根据 CPU 核心数调整 IO 线程和工作线程
3. **启用缓存**: 字典翻译启用缓存可显著提升性能
4. **排除 URL**: 健康检查等 URL 排除在日志记录之外
5. **异步翻译**: 大量数据时考虑使用异步字典翻译

## 常见问题

### Q1: 跨域配置不生效？
**A**: 检查是否启用了 `jeecg.web.cors.enabled=true`，并确保配置了正确的 `allowed-origins`

### Q2: 日志记录了敏感信息？
**A**: 在 `exclude-urls` 中添加需要排除的 URL，或者在日志切面中自定义过滤规则

### Q3: 防火墙误杀正常请求？
**A**: 在 `whitelist-urls` 中添加白名单，或者调整 SQL 注入/XSS 检查规则

### Q4: 数据权限不生效？
**A**: 确保方法上添加了 `@PermissionData` 注解，并检查用户权限配置

## 版本历史

- **v4.0.0** (2025-11-08): 初始版本，从 jeecg-boot-base-core 拆分
  - 支持 CORS 跨域
  - 支持防火墙
  - 支持自动日志
  - 支持字典翻译
  - 支持数据权限
  - 支持全局异常处理
  - 支持 Undertow 服务器

## 许可证

Apache License 2.0

## 联系方式

- **项目主页**: https://github.com/jeecgboot/jeecg-boot
- **文档地址**: http://doc.jeecg.com
- **问题反馈**: https://github.com/jeecgboot/jeecg-boot/issues