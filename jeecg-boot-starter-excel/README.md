# jeecg-boot-starter-excel

## 模块说明

JeecgBoot Excel 导入导出功能模块,提供基于 AutoPoi 的 Excel 文件处理能力。

## 功能特性

- ✅ Excel 文件导入
- ✅ Excel 文件导出
- ✅ 大数据量 Excel 导出
- ✅ 多 Sheet 页导出
- ✅ 字典翻译支持
- ✅ 模板导出
- ✅ 数据验证

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-excel</artifactId>
    <version>3.7.1</version>
</dependency>
```

### 2. 配置说明

```yaml
jeecg:
  excel:
    max-import-rows: 10000      # 最大导入行数
    temp-path: /temp/excel      # 临时文件路径
```

### 3. 使用示例

#### 导出 Excel

```java
@RestController
@RequestMapping("/user")
public class UserController extends JeecgExcelController<User, IUserService> {
    
    @GetMapping("/export")
    public void exportXls(HttpServletRequest request, HttpServletResponse response) {
        // 查询数据
        List<User> userList = service.list();
        
        // 导出Excel
        exportXls(request, response, userList, "用户列表", User.class);
    }
}
```

#### 导入 Excel

```java
@PostMapping("/import")
public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
    
    for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
        MultipartFile file = entity.getValue();
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        
        try {
            List<User> list = ExcelImportUtil.importExcel(file.getInputStream(), User.class, params);
            service.saveBatch(list);
            return Result.OK("导入成功!");
        } catch (Exception e) {
            return Result.error("导入失败:" + e.getMessage());
        }
    }
    return Result.error("未找到文件");
}
```

#### 实体类配置

```java
@Data
@TableName("sys_user")
public class User {
    
    @Excel(name = "用户名", width = 15)
    private String username;
    
    @Excel(name = "真实姓名", width = 15)
    private String realname;
    
    @Excel(name = "性别", width = 10, dicCode = "sex")
    private Integer sex;
    
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
```

## 核心组件

### AutoPoiDictConfig

字典配置类,支持 Excel 导出时的字典翻译功能。

### ExcelAutoConfiguration

自动配置类,负责初始化 Excel 相关的 Bean。

### ExcelProperties

配置属性类,支持通过 `application.yml` 进行配置。

### ImportExcelUtil

Excel 导入工具类,提供便捷的 Excel 导入方法。

### JeecgExcelController

Excel 控制器基类,提供常用的 Excel 导入导出方法:
- `exportXls()` - 基础导出
- `exportXlsSheet()` - 多 Sheet 导出
- `exportXlsForBigData()` - 大数据量导出
- `importExcel()` - Excel 导入

## 依赖说明

本模块依赖以下组件:
- `spring-boot-starter-web` - Web 支持
- `autopoi-web` - AutoPoi Excel 工具
- `poi` / `poi-ooxml` - Apache POI

## 注意事项

1. 导入时建议设置最大行数限制,防止内存溢出
2. 大数据量导出建议使用 `exportXlsForBigData()` 方法
3. 使用字典翻译功能需要实现相应的字典查询接口
4. 临时文件会自动清理,无需手动处理

## 版本历史

- **3.7.1** - 初始版本,从 jeecg-boot-base-core 拆分

## 技术支持

- 官网: http://www.jeecg.com
- 文档: http://doc.jeecg.com
- GitHub: https://github.com/jeecgboot/jeecg-boot