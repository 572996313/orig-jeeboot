# jeecg-boot-base-core-lite

## 📦 模块简介

`jeecg-boot-base-core-lite` 是 JeecgBoot 框架的**轻量级核心模块**，提供最基础的 Spring 集成能力和 CRUD 功能支持。

### 设计目标

- ✅ **最小依赖**: 仅依赖 Spring Boot 和 MyBatis-Plus 核心功能
- ✅ **基础能力**: 提供 Controller、Service、Entity 基类
- ✅ **扩展点**: 定义处理器接口，支持功能扩展
- ✅ **工具集成**: 集成 Spring 上下文工具类
- ✅ **配置管理**: 提供基础配置类

## 🎯 功能特性

### 1. 基础 CRUD 能力

提供开箱即用的 CRUD 基类：

```java
// 实体基类
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends JeecgEntity {
    private String username;
    private String realname;
}

// 服务接口
public interface ISysUserService extends JeecgService<SysUser> {
}

// 服务实现
@Service
public class SysUserServiceImpl extends JeecgServiceImpl<SysUserMapper, SysUser> 
        implements ISysUserService {
}

// 控制器
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends JeecgController<SysUser, ISysUserService> {
}
```

### 2. Spring 容器工具

```java
// 获取 Bean 实例
ISysUserService userService = SpringContextUtils.getBean(ISysUserService.class);

// 根据名称获取 Bean
Object bean = SpringContextUtils.getBean("sysUserService");

// 获取 ApplicationContext
ApplicationContext context = SpringContextUtils.getApplicationContext();
```

### 3. 扩展点接口

```java
// 自定义填充规则处理器
@Component
public class CustomFillRuleHandler implements IFillRuleHandler {
    @Override
    public Object execute(String ruleCode, JSONObject param) {
        // 自定义填充逻辑
        return "填充值";
    }
}
```

### 4. 系统工具类

- **ResourceUtil**: 资源文件工具
- **SqlConcatUtil**: SQL 拼接工具
- **JeecgDataAutorUtils**: 数据作者工具

### 5. 基础配置

- **JeecgBaseConfig**: Jeecg 基础配置
- **RestTemplateConfig**: HTTP 客户端配置
- **StaticConfig**: 静态配置管理

## 📦 Maven 依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-core-lite</artifactId>
    <version>4.0.0</version>
</dependency>
```

### 传递依赖

本模块自动引入以下依赖：

```xml
<!-- 基础模块 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-constants</artifactId>
</dependency>
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-api</artifactId>
</dependency>
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-utils</artifactId>
</dependency>

<!-- Spring Boot 核心 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- MyBatis-Plus -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
</dependency>
```

## 🚀 快速开始

### 1. 创建实体类

```java
package com.example.entity;

import org.jeecg.common.system.base.entity.JeecgEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("demo_user")
public class DemoUser extends JeecgEntity {
    private String username;
    private String email;
    private Integer age;
}
```

### 2. 创建 Mapper 接口

```java
package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.DemoUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoUserMapper extends BaseMapper<DemoUser> {
}
```

### 3. 创建 Service

```java
package com.example.service;

import com.example.entity.DemoUser;
import org.jeecg.common.system.base.service.JeecgService;

public interface IDemoUserService extends JeecgService<DemoUser> {
}
```

```java
package com.example.service.impl;

import com.example.entity.DemoUser;
import com.example.mapper.DemoUserMapper;
import com.example.service.IDemoUserService;
import org.jeecg.common.system.base.service.impl.JeecgServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DemoUserServiceImpl extends JeecgServiceImpl<DemoUserMapper, DemoUser> 
        implements IDemoUserService {
}
```

### 4. 创建 Controller

```java
package com.example.controller;

import com.example.entity.DemoUser;
import com.example.service.IDemoUserService;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo/user")
public class DemoUserController extends JeecgController<DemoUser, IDemoUserService> {
    
    // 继承自 JeecgController 的方法：
    // - list(): 分页查询
    // - add(): 新增
    // - edit(): 编辑
    // - delete(): 删除
    // - queryById(): 根据ID查询
    
    // 可以添加自定义方法
    @GetMapping("/custom")
    public Result<?> customMethod() {
        return Result.OK("自定义方法");
    }
}
```

### 5. 配置文件

```yaml
# application.yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/jeecg-boot?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: root

mybatis-plus:
  mapper-locations: classpath:mapper/**/*.xml
  global-config:
    db-config:
      id-type: ASSIGN_ID
      field-strategy: NOT_EMPTY
  configuration:
    map-underscore-to-camel-case: true
```

## 📚 包结构

```
org.jeecg
├── common
│   ├── handler
│   │   └── IFillRuleHandler.java          # 填充规则处理器接口
│   ├── system
│   │   ├── annotation
│   │   │   └── EnumDict.java              # 枚举字典注解
│   │   ├── base
│   │   │   ├── controller
│   │   │   │   └── JeecgController.java   # 控制器基类
│   │   │   ├── entity
│   │   │   │   └── JeecgEntity.java       # 实体基类
│   │   │   └── service
│   │   │       ├── JeecgService.java      # 服务接口
│   │   │       └── impl
│   │   │           └── JeecgServiceImpl.java  # 服务实现基类
│   │   ├── enhance
│   │   │   └── UserFilterEnhance.java     # 用户过滤增强
│   │   └── util
│   │       ├── JeecgDataAutorUtils.java   # 数据作者工具
│   │       ├── ResourceUtil.java          # 资源工具
│   │       └── SqlConcatUtil.java         # SQL拼接工具
│   └── util
│       └── SpringContextUtils.java        # Spring上下文工具
└── config
    ├── JeecgBaseConfig.java               # Jeecg基础配置
    ├── RestTemplateConfig.java            # RestTemplate配置
    └── StaticConfig.java                  # 静态配置
```

## 🔧 配置说明

### 基础配置

```yaml
jeecg:
  path:
    upload: /opt/upload          # 文件上传路径
    webapp: /opt/webapp          # Web应用路径
```

### RestTemplate 配置

自动配置 RestTemplate Bean，支持 HTTP 调用：

```java
@Autowired
private RestTemplate restTemplate;

public String callApi() {
    return restTemplate.getForObject("http://example.com/api", String.class);
}
```

## 🎨 最佳实践

### 1. 实体类设计

```java
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@ApiModel(value="用户", description="用户信息")
public class SysUser extends JeecgEntity {
    
    @ApiModelProperty(value = "用户名")
    @Excel(name = "用户名", width = 15)
    private String username;
    
    @ApiModelProperty(value = "真实姓名")
    @Excel(name = "真实姓名", width = 15)
    private String realname;
    
    @EnumDict("user_status")
    @ApiModelProperty(value = "状态")
    private Integer status;
}
```

### 2. Service 层扩展

```java
@Service
public class SysUserServiceImpl extends JeecgServiceImpl<SysUserMapper, SysUser> 
        implements ISysUserService {
    
    @Override
    public boolean saveUser(SysUser user) {
        // 添加业务逻辑
        user.setCreateTime(new Date());
        return this.save(user);
    }
    
    @Override
    public List<SysUser> listByStatus(Integer status) {
        return this.list(new QueryWrapper<SysUser>().eq("status", status));
    }
}
```

### 3. Controller 层扩展

```java
@RestController
@RequestMapping("/sys/user")
@Api(tags="用户管理")
public class SysUserController extends JeecgController<SysUser, ISysUserService> {
    
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result<?> register(@RequestBody SysUser user) {
        service.saveUser(user);
        return Result.OK("注册成功");
    }
    
    @GetMapping("/listByStatus")
    @ApiOperation("根据状态查询")
    public Result<?> listByStatus(@RequestParam Integer status) {
        List<SysUser> list = service.listByStatus(status);
        return Result.OK(list);
    }
}
```

## 🔗 相关模块

- [jeecg-boot-base-constants](../jeecg-boot-base-constants) - 常量定义模块
- [jeecg-boot-base-api](../jeecg-boot-base-api) - API接口定义模块
- [jeecg-boot-base-utils](../jeecg-boot-base-utils) - 工具类模块
- [jeecg-boot-starter-security](../jeecg-boot-starter-security) - 安全认证模块
- [jeecg-boot-starter-mybatis-plus](../jeecg-boot-starter-mybatis-plus) - MyBatis增强模块

## 📝 更新日志

### v4.0.0 (2025-11-08)

- ✨ 首次发布
- ✅ 提供基础CRUD能力
- ✅ 提供Spring容器工具
- ✅ 定义扩展点接口
- ✅ 提供基础配置类

## 📄 许可证

Apache License 2.0

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

## 📧 联系方式

- 官网: http://www.jeecg.com
- 文档: http://doc.jeecg.com
- GitHub: https://github.com/jeecgboot/jeecg-boot
- Gitee: https://gitee.com/jeecg/jeecg-boot

---

**JeecgBoot** - 让开发更简单 🚀