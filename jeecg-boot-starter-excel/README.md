# jeecg-boot-starter-excel

## 模块简介

`jeecg-boot-starter-excel` 是 JeecgBoot 的 Excel 导入导出 Starter 模块，提供基于 EasyPoi 和 Apache POI 的 Excel 处理能力。

### 核心特性

- ✅ **双引擎支持**: 支持 EasyPoi 和 Apache POI 两种引擎
- ✅ **自动配置**: Spring Boot 自动配置，开箱即用
- ✅ **可选依赖**: 按需引入 Excel 处理框架
- ✅ **安全验证**: 文件大小、扩展名、行数限制
- ✅ **灵活配置**: 支持导入导出多种自定义配置
- ✅ **临时文件管理**: 自动清理临时文件
- ✅ **向后兼容**: 兼容旧版 AutoPoi 配置

## 快速开始

### 1. 添加依赖

#### Maven

```xml
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-excel</artifactId>
    <version>4.0.0</version>
</dependency>

<!-- 选择Excel引擎（二选一） -->
<!-- EasyPoi（推荐） -->
<dependency>
    <groupId>cn.afterturn</groupId>
    <artifactId>easypoi-spring-boot-starter</artifactId>
    <version>4.4.0</version>
</dependency>

<!-- 或使用 Apache POI -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.3</version>
</dependency>
```

### 2. 配置文件

在 `application.yml` 中添加配置：

```yaml
jeecg:
  excel:
    enabled: true                    # 是否启用
    engine: easypoi                  # 引擎类型: easypoi, poi
    
    # 导入配置
    import-config:
      max-rows: 10000                # 最大导入行数
      max-file-size-mb: 10           # 最大文件大小(MB)
      allowed-extensions:             # 允许的扩展名
        - xls
        - xlsx
      validate-template: true        # 是否验证模板
      error-strategy: collect        # 错误处理: skip, stop, collect
    
    # 导出配置
    export-config:
      max-rows: 100000               # 最大导出行数
      default-sheet-name: "Sheet1"   # 默认Sheet名
      auto-size-column: true         # 自动调整列宽
      date-format: "yyyy-MM-dd HH:mm:ss"
      number-format: "#,##0.00"
    
    # 临时文件配置
    temp:
      path: "/temp/excel"            # 临时文件路径
      retention-hours: 24            # 保留时间(小时)
      auto-clean: true               # 自动清理
      clean-interval: 6              # 清理间隔(小时)
```

### 3. 使用示例

#### 导入Excel

```java
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private ImportExcelUtil importExcelUtil;
    
    @PostMapping("/import")
    public Result<?> importUsers(@RequestParam("file") MultipartFile file) {
        try {
            List<User> users = importExcelUtil.importExcel(file, User.class);
            // 处理导入的数据
            return Result.ok("导入成功，共" + users.size() + "条数据");
        } catch (Exception e) {
            return Result.error("导入失败: " + e.getMessage());
        }
    }
}
```

#### 导出Excel

```java
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private ExportExcelUtil exportExcelUtil;
    
    @GetMapping("/export")
    public void exportUsers(HttpServletResponse response) {
        List<User> users = userService.list();
        exportExcelUtil.exportExcel(response, users, User.class, "用户列表");
    }
}
```

#### 实体类注解

使用 EasyPoi 时，在实体类上添加注解：

```java
@Data
@Excel(name = "用户信息")
public class User {
    
    @Excel(name = "用户名", width = 20, orderNum = "1")
    private String username;
    
    @Excel(name = "姓名", width = 15, orderNum = "2")
    private String realName;
    
    @Excel(name = "邮箱", width = 25, orderNum = "3")
    private String email;
    
    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss", width = 25, orderNum = "4")
    private Date createTime;
}
```

## 配置说明

### 基础配置

| 配置项 | 类型 | 默认值 | 说明 |
|-------|------|--------|------|
| `jeecg.excel.enabled` | Boolean | true | 是否启用Excel功能 |
| `jeecg.excel.engine` | String | easypoi | 引擎类型：easypoi, poi |

### 导入配置

| 配置项 | 类型 | 默认值 | 说明 |
|-------|------|--------|------|
| `jeecg.excel.import-config.max-rows` | Integer | 10000 | 最大导入行数 |
| `jeecg.excel.import-config.max-file-size-mb` | Integer | 10 | 最大文件大小(MB) |
| `jeecg.excel.import-config.allowed-extensions` | String[] | xls, xlsx | 允许的扩展名 |
| `jeecg.excel.import-config.validate-template` | Boolean | true | 是否验证模板 |
| `jeecg.excel.import-config.error-strategy` | String | collect | 错误处理策略 |

### 导出配置

| 配置项 | 类型 | 默认值 | 说明 |
|-------|------|--------|------|
| `jeecg.excel.export-config.max-rows` | Integer | 100000 | 最大导出行数 |
| `jeecg.excel.export-config.default-sheet-name` | String | Sheet1 | 默认Sheet名称 |
| `jeecg.excel.export-config.auto-size-column` | Boolean | true | 自动调整列宽 |
| `jeecg.excel.export-config.date-format` | String | yyyy-MM-dd HH:mm:ss | 日期格式 |
| `jeecg.excel.export-config.number-format` | String | #,##0.00 | 数字格式 |

## 高级用法

### 1. 自定义验证

```java
@PostMapping("/import")
public Result<?> importUsers(@RequestParam("file") MultipartFile file) {
    // 自定义文件验证
    if (!importExcelUtil.getImportConfig().isAllowedExtension(file.getOriginalFilename())) {
        return Result.error("不支持的文件类型");
    }
    
    List<User> users = importExcelUtil.importExcel(file, User.class);
    
    // 自定义数据验证
    for (User user : users) {
        if (StringUtils.isEmpty(user.getUsername())) {
            return Result.error("用户名不能为空");
        }
    }
    
    return Result.ok("导入成功");
}
```

### 2. 错误处理策略

```yaml
jeecg:
  excel:
    import-config:
      error-strategy: collect  # skip: 跳过错误, stop: 停止, collect: 收集错误
```

### 3. 自定义导出样式

使用 EasyPoi 时可以自定义样式：

```java
ExportParams params = new ExportParams("用户列表", "Sheet1");
params.setStyle(CustomExcelExportStyler.class);
Workbook workbook = ExcelExportUtil.exportExcel(params, User.class, users);
```

## 常见问题

### 1. 导入时提示文件过大

**解决方案**: 调整配置
```yaml
jeecg:
  excel:
    import-config:
      max-file-size-mb: 20  # 增加到20MB
```

### 2. 导出时出现内存溢出

**原因**: 一次性导出数据量过大

**解决方案**: 分批导出或调整JVM内存
```yaml
jeecg:
  excel:
    export-config:
      max-rows: 50000  # 减少单次导出行数
```

### 3. 旧版配置迁移

**旧配置**:
```yaml
jeecg:
  autopoi:
    enable: true
```

**新配置**:
```yaml
jeecg:
  excel:
    enabled: true
    engine: easypoi
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
    <artifactId>jeecg-boot-starter-excel</artifactId>
    <version>4.0.0</version>
</dependency>
```

### 步骤2: 更新配置

参考上面的配置说明更新 application.yml

### 步骤3: 代码调整

如果使用了 `ImportExcelUtil` 或 `ExportExcelUtil`，注入方式保持不变：

```java
@Autowired
private ImportExcelUtil importExcelUtil;

@Autowired
private ExportExcelUtil exportExcelUtil;
```

## 技术栈

- Spring Boot 2.7.x
- EasyPoi 4.4.0 (可选)
- Apache POI 5.2.3 (可选)

## 依赖关系

```
jeecg-boot-starter-excel
├── jeecg-boot-base-constants (必选)
├── jeecg-boot-base-api (必选)
├── jeecg-boot-base-utils (必选)
├── Spring Boot Starter Web (必选)
├── EasyPoi (可选)
└── Apache POI (可选)
```

## 版本历史

### v4.0.0 (2025-01)
- ✨ 全新模块化架构
- ✨ 支持双引擎切换
- ✨ 可选依赖设计
- ✨ 完善的配置管理
- ✨ 向后兼容支持

## 许可证

Apache License 2.0

---

**注意**: 本模块是 jeecg-boot-base-core 模块拆分计划的一部分。