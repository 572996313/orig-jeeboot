# jeecg-boot-base-api

## 📖 模块说明

`jeecg-boot-base-api` 是 JeecgBoot 框架的 **API接口定义模块**，包含了API接口、DTO（数据传输对象）、VO（视图对象）等核心数据结构定义。

## ✨ 模块特性

- 🎯 **最小依赖** - 仅依赖常量模块，可选依赖Jackson和Swagger注解
- 📦 **接口契约** - 定义统一的API接口和数据传输格式
- 🔄 **跨模块复用** - 可被多个模块依赖使用
- 📚 **完整文档** - 提供详细的JavaDoc和使用示例

## 📦 模块内容

### API接口
- `CommonAPI` - 通用API接口定义

### DTO (数据传输对象)
- `LogDTO` - 日志数据传输对象
- `DataLogDTO` - 数据日志DTO
- `FileUploadDTO` - 文件上传DTO
- `FileDownDTO` - 文件下载DTO
- `OnlineAuthDTO` - 在线授权DTO
- `AiragFlowDTO` - AI流程DTO
- `MessageDTO` - 消息DTO
- `BusMessageDTO` - 业务消息DTO
- `TemplateDTO` - 模板DTO
- `TemplateMessageDTO` - 模板消息DTO
- `BusTemplateMessageDTO` - 业务模板消息DTO

### VO (视图对象)
- `Result` - 统一响应结果对象
- `LoginUser` - 登录用户信息
- `DictModel` - 字典模型
- `DictModelMany` - 多值字典模型
- `DictQuery` - 字典查询对象
- `ComboModel` - 下拉选择模型
- `SelectTreeModel` - 树形选择模型
- `SysCategoryModel` - 系统分类模型
- `SysDepartModel` - 系统部门模型
- `SysFilesModel` - 系统文件模型
- `SysPermissionDataRuleModel` - 权限数据规则模型
- `SysUserCacheInfo` - 用户缓存信息
- `UserAccountInfo` - 用户账户信息
- `DynamicDataSourceModel` - 动态数据源模型

## 🔧 依赖关系

```xml
<dependencies>
    <!-- 必选：常量模块 -->
    <dependency>
        <groupId>org.jeecgframework.boot3</groupId>
        <artifactId>jeecg-boot-base-constants</artifactId>
    </dependency>
    
    <!-- 可选：Jackson注解 -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-annotations</artifactId>
        <optional>true</optional>
    </dependency>
    
    <!-- 可选：Swagger注解 -->
    <dependency>
        <groupId>io.swagger.core.v3</groupId>
        <artifactId>swagger-annotations-jakarta</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

## 📥 Maven引入

```xml
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-base-api</artifactId>
    <version>4.0.0-SNAPSHOT</version>
</dependency>
```

## 💡 使用示例

### 1. 统一响应对象

```java
import org.jeecg.common.api.vo.Result;

// 成功响应
Result<User> result = Result.ok(user);

// 错误响应
Result<Void> error = Result.error("操作失败");

// 带消息的成功响应
Result<List<User>> success = Result.ok("查询成功", userList);
```

### 2. 数据传输对象

```java
import org.jeecg.common.api.dto.LogDTO;

// 创建日志DTO
LogDTO logDTO = new LogDTO();
logDTO.setLogType(1);
logDTO.setLogContent("用户登录");
logDTO.setOperateType(1);
```

### 3. 视图对象

```java
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.system.vo.DictModel;

// 登录用户信息
LoginUser loginUser = new LoginUser();
loginUser.setUsername("admin");
loginUser.setRealname("管理员");

// 字典模型
DictModel dict = new DictModel();
dict.setValue("1");
dict.setText("启用");
```

## 📂 目录结构

```
jeecg-boot-base-api/
├── pom.xml
├── README.md
└── src/main/java/org/jeecg/
    └── common/
        ├── api/
        │   ├── CommonAPI.java
        │   ├── dto/
        │   │   ├── LogDTO.java
        │   │   ├── DataLogDTO.java
        │   │   ├── FileUploadDTO.java
        │   │   ├── FileDownDTO.java
        │   │   ├── OnlineAuthDTO.java
        │   │   ├── AiragFlowDTO.java
        │   │   └── message/
        │   │       ├── MessageDTO.java
        │   │       ├── BusMessageDTO.java
        │   │       ├── TemplateDTO.java
        │   │       ├── TemplateMessageDTO.java
        │   │       └── BusTemplateMessageDTO.java
        │   └── vo/
        │       └── Result.java
        └── system/
            └── vo/
                ├── LoginUser.java
                ├── DictModel.java
                ├── DictModelMany.java
                ├── DictQuery.java
                ├── ComboModel.java
                ├── SelectTreeModel.java
                ├── SysCategoryModel.java
                ├── SysDepartModel.java
                ├── SysFilesModel.java
                ├── SysPermissionDataRuleModel.java
                ├── SysUserCacheInfo.java
                ├── UserAccountInfo.java
                └── DynamicDataSourceModel.java
```

## 🎯 设计原则

1. **接口优先** - 定义清晰的API契约
2. **最小依赖** - 减少对外部框架的依赖
3. **向后兼容** - 保持包路径和类名不变
4. **文档完善** - 提供详细的JavaDoc

## 🔗 相关模块

- **jeecg-boot-base-constants** - 常量和枚举定义（本模块的依赖）
- **jeecg-boot-base-utils** - 工具类模块（可选配合使用）
- **jeecg-boot-base-core-lite** - 轻量核心模块（依赖本模块）

## 📋 版本信息

- **当前版本**: 4.0.0-SNAPSHOT
- **JDK版本**: 17+
- **构建工具**: Maven 3.6+

## 👥 维护者

JeecgBoot Team

## 📄 许可证

Apache License 2.0