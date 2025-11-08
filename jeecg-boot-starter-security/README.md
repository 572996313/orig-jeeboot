# Jeecg Boot Starter Security

## 📦 模块简介

`jeecg-boot-starter-security` 是 JeecgBoot 框架的安全认证 Starter 模块，提供基于 **Apache Shiro + JWT** 的完整安全认证解决方案。

## ✨ 核心特性

- 🔐 **Shiro 安全框架** - 提供认证、授权、会话管理
- 🎫 **JWT 令牌认证** - 无状态的 Token 认证机制
- 🚀 **自动配置** - Spring Boot AutoConfiguration 自动装配
- ⚙️ **灵活配置** - 支持通过 YAML 配置文件自定义行为
- 🔓 **忽略认证** - 支持 `@IgnoreAuth` 注解排除认证
- 📝 **Redis 缓存** - Shiro 缓存集成 Redis
- 🌐 **跨域支持** - 内置跨域请求处理

## 📋 依赖说明

### Maven 依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-starter-security</artifactId>
    <version>4.0.0-SNAPSHOT</version>
</dependency>
```

### 内部依赖

本模块依赖以下 JeecgBoot 基础模块：

- `jeecg-boot-base-constants` - 常量定义
- `jeecg-boot-base-api` - API 接口
- `jeecg-boot-base-utils` - 工具类
- `jeecg-boot-base-core-lite` - 轻量核心

### 外部依赖

- Apache Shiro 1.13.0
- Shiro Redis 3.3.1
- Java JWT 4.4.0
- Spring Boot Starter Web
- Spring Boot Starter Data Redis

## 🚀 快速开始

### 1. 添加依赖

在项目的 `pom.xml` 中添加依赖：

```xml
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-starter-security</artifactId>
</dependency>
```

### 2. 配置文件

在 `application.yml` 中配置：

```yaml
jeecg:
  security:
    enabled: true  # 启用安全认证
    shiro:
      enabled: true
      exclude-urls: /sys/login,/sys/logout,/sys/cas/client/validateLogin  # 排除拦截的URL
      url-permission-enabled: false  # 是否启用URL权限控制
    jwt:
      secret: ${JWT_SECRET:jiangbo-secret-key}  # JWT密钥
      expire-time: 604800  # 过期时间（秒），默认7天
      token-header: X-Access-Token  # Token请求头名称

spring:
  redis:
    host: localhost
    port: 6379
    password: ${REDIS_PASSWORD:}
    database: 0
```

### 3. 启动应用

Spring Boot 会自动配置 Shiro + JWT 认证，无需额外配置代码。

## 📖 使用指南

### 1. 登录认证

```java
@RestController
@RequestMapping("/sys")
public class LoginController {
    
    @Autowired
    private ISysUserService userService;
    
    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginDto loginDto) {
        // 1. 验证用户名密码
        SysUser user = userService.getUserByName(loginDto.getUsername());
        if (user == null || !PasswordUtil.checkPassword(loginDto.getPassword(), user.getPassword())) {
            return Result.error("用户名或密码错误");
        }
        
        // 2. 生成 JWT Token
        String token = JwtUtil.sign(user.getUsername(), secretKey);
        
        // 3. 返回 Token
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("userInfo", user);
        return Result.OK(result);
    }
}
```

### 2. 使用 @IgnoreAuth 注解

对于不需要认证的接口，使用 `@IgnoreAuth` 注解：

```java
@RestController
@RequestMapping("/public")
public class PublicController {
    
    @IgnoreAuth
    @GetMapping("/info")
    public Result<?> getPublicInfo() {
        return Result.OK("这是公开接口，无需认证");
    }
}
```

### 3. 获取当前用户

```java
@RestController
@RequestMapping("/user")
public class UserController {
    
    @GetMapping("/current")
    public Result<?> getCurrentUser(HttpServletRequest request) {
        // 方式1: 通过 Shiro
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        
        // 方式2: 通过 JwtUtil
        String username = JwtUtil.getUserNameByToken(request);
        
        return Result.OK(loginUser);
    }
}
```

### 4. 权限验证

```java
@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @RequiresPermissions("system:user:add")
    @PostMapping("/user")
    public Result<?> addUser(@RequestBody SysUser user) {
        // 需要 system:user:add 权限才能访问
        return Result.OK("添加用户成功");
    }
    
    @RequiresRoles("admin")
    @DeleteMapping("/user/{id}")
    public Result<?> deleteUser(@PathVariable String id) {
        // 需要 admin 角色才能访问
        return Result.OK("删除用户成功");
    }
}
```

## ⚙️ 配置详解

### 安全配置属性

| 配置项 | 类型 | 默认值 | 说明 |
|-------|------|--------|------|
| `jeecg.security.enabled` | Boolean | true | 是否启用安全认证 |
| `jeecg.security.shiro.enabled` | Boolean | true | 是否启用 Shiro |
| `jeecg.security.shiro.exclude-urls` | String | /sys/login,/sys/logout,... | 排除拦截的 URL（逗号分隔） |
| `jeecg.security.shiro.url-permission-enabled` | Boolean | false | 是否启用 URL 权限控制 |
| `jeecg.security.jwt.secret` | String | jiangbo-secret-key | JWT 密钥 |
| `jeecg.security.jwt.expire-time` | Long | 604800 | JWT 过期时间（秒） |
| `jeecg.security.jwt.token-header` | String | X-Access-Token | Token 请求头名称 |

### 禁用安全认证

如果需要临时禁用安全认证（例如在开发环境）：

```yaml
jeecg:
  security:
    enabled: false
```

## 🏗️ 模块结构

```
jeecg-boot-starter-security/
├── src/main/java/
│   └── org/jeecg/
│       ├── common/system/util/
│       │   └── JwtUtil.java                      # JWT 工具类
│       ├── config/
│       │   ├── security/
│       │   │   ├── JeecgSecurityAutoConfiguration.java  # 自动配置
│       │   │   └── JeecgSecurityProperties.java         # 配置属性
│       │   └── shiro/
│       │       ├── ShiroConfig.java              # Shiro 配置
│       │       ├── ShiroRealm.java               # Shiro 认证授权域
│       │       ├── JwtToken.java                 # JWT Token 封装
│       │       ├── IgnoreAuth.java               # 忽略认证注解
│       │       ├── filters/
│       │       │   ├── JwtFilter.java            # JWT 过滤器
│       │       │   ├── ResourceCheckFilter.java  # 资源检查过滤器
│       │       │   └── CustomShiroFilterFactoryBean.java
│       │       └── ignore/
│       │           ├── InMemoryIgnoreAuth.java   # 内存忽略认证管理
│       │           └── IgnoreAuthPostProcessor.java
│       └── src/main/resources/
│           └── META-INF/
│               ├── spring/
│               │   └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
│               └── spring-configuration-metadata.json
└── pom.xml
```

## 🔧 高级配置

### 自定义 Shiro Realm

```java
@Configuration
public class CustomSecurityConfig {
    
    @Bean
    @Primary
    public ShiroRealm customShiroRealm() {
        return new CustomShiroRealm();
    }
}

public class CustomShiroRealm extends ShiroRealm {
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 自定义授权逻辑
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // ... 添加角色和权限
        return info;
    }
}
```

### 自定义 JWT 过期时间

```yaml
jeecg:
  security:
    jwt:
      expire-time: 86400  # 1天（秒）
```

### 动态排除 URL

```yaml
jeecg:
  security:
    shiro:
      exclude-urls: >
        /sys/login,
        /sys/logout,
        /sys/cas/client/validateLogin,
        /public/**,
        /swagger-ui.html,
        /v3/api-docs/**
```

## 🐛 常见问题

### Q1: Token 验证失败

**问题**: 前端传入 Token 后返回 401 未授权

**解决方案**:
1. 检查 Token 是否正确设置在请求头 `X-Access-Token` 中
2. 检查 JWT 密钥配置是否一致
3. 检查 Token 是否过期
4. 检查 Redis 连接是否正常

### Q2: @IgnoreAuth 注解不生效

**问题**: 添加了 `@IgnoreAuth` 注解但仍然需要认证

**解决方案**:
1. 确保注解添加在 `@RequestMapping` 方法上
2. 检查是否有其他拦截器干扰
3. 重启应用，确保注解扫描生效

### Q3: Shiro 缓存问题

**问题**: 修改用户权限后不生效

**解决方案**:
```java
// 清除用户的授权缓存
SimplePrincipalCollection principals = new SimplePrincipalCollection(username, getRealm().getName());
super.clearCachedAuthorizationInfo(principals);
```

### Q4: 跨域请求被拦截

**问题**: 前后端分离项目出现跨域问题

**解决方案**:
```yaml
jeecg:
  security:
    shiro:
      exclude-urls: /sys/login,/sys/logout,OPTIONS  # 添加 OPTIONS
```

## 📚 相关文档

- [Apache Shiro 官方文档](https://shiro.apache.org/documentation.html)
- [JWT 规范](https://jwt.io/)
- [JeecgBoot 官方文档](http://doc.jeecg.com)

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

## 📄 开源协议

本模块遵循 Apache License 2.0 开源协议。

---

**作者**: llllxf (个人开发工程师)  
**创建时间**: 2025-11-08  
**模块版本**: 4.0.0-SNAPSHOT