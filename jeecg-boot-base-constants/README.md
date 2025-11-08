# JeecgBoot Base Constants

## 📦 模块说明

`jeecg-boot-base-constants` 是 JeecgBoot 框架的基础常量和枚举模块,提供全局常量定义和枚举类型。

### 特性

- ✅ **零依赖**: 纯Java模块,不依赖任何第三方库
- ✅ **纯无状态**: 所有类都是常量或枚举,无状态设计
- ✅ **高复用**: 可在任何Java项目中使用
- ✅ **版本独立**: 独立版本管理,稳定性高

## 📋 包含内容

### 常量类 (12个)

| 类名 | 说明 |
|-----|------|
| [`CommonConstant`](src/main/java/org/jeecg/common/constant/CommonConstant.java) | 通用常量 |
| [`CommonSendStatus`](src/main/java/org/jeecg/common/constant/CommonSendStatus.java) | 发送状态常量 |
| [`DataBaseConstant`](src/main/java/org/jeecg/common/constant/DataBaseConstant.java) | 数据库常量 |
| [`DynamicTableConstant`](src/main/java/org/jeecg/common/constant/DynamicTableConstant.java) | 动态表常量 |
| [`FillRuleConstant`](src/main/java/org/jeecg/common/constant/FillRuleConstant.java) | 填充规则常量 |
| [`PasswordConstant`](src/main/java/org/jeecg/common/constant/PasswordConstant.java) | 密码常量 |
| [`ProvinceCityArea`](src/main/java/org/jeecg/common/constant/ProvinceCityArea.java) | 省市区常量 |
| [`ServiceNameConstants`](src/main/java/org/jeecg/common/constant/ServiceNameConstants.java) | 服务名常量 |
| [`SymbolConstant`](src/main/java/org/jeecg/common/constant/SymbolConstant.java) | 符号常量 |
| [`TenantConstant`](src/main/java/org/jeecg/common/constant/TenantConstant.java) | 租户常量 |
| [`VxeSocketConst`](src/main/java/org/jeecg/common/constant/VxeSocketConst.java) | VxeSocket常量 |
| [`WebsocketConst`](src/main/java/org/jeecg/common/constant/WebsocketConst.java) | WebSocket常量 |

### 枚举类 (15个)

| 类名 | 说明 |
|-----|------|
| [`CgformEnum`](src/main/java/org/jeecg/common/constant/enums/CgformEnum.java) | 表单枚举 |
| [`ClientTerminalTypeEnum`](src/main/java/org/jeecg/common/constant/enums/ClientTerminalTypeEnum.java) | 客户端类型枚举 |
| [`DateRangeEnum`](src/main/java/org/jeecg/common/constant/enums/DateRangeEnum.java) | 日期范围枚举 |
| [`DepartCategoryEnum`](src/main/java/org/jeecg/common/constant/enums/DepartCategoryEnum.java) | 部门分类枚举 |
| [`DySmsEnum`](src/main/java/org/jeecg/common/constant/enums/DySmsEnum.java) | 短信枚举 |
| [`EmailTemplateEnum`](src/main/java/org/jeecg/common/constant/enums/EmailTemplateEnum.java) | 邮件模板枚举 |
| [`FileTypeEnum`](src/main/java/org/jeecg/common/constant/enums/FileTypeEnum.java) | 文件类型枚举 |
| [`MessageTypeEnum`](src/main/java/org/jeecg/common/constant/enums/MessageTypeEnum.java) | 消息类型枚举 |
| [`ModuleType`](src/main/java/org/jeecg/common/constant/enums/ModuleType.java) | 模块类型枚举 |
| [`NoticeTypeEnum`](src/main/java/org/jeecg/common/constant/enums/NoticeTypeEnum.java) | 通知类型枚举 |
| [`OperateTypeEnum`](src/main/java/org/jeecg/common/constant/enums/OperateTypeEnum.java) | 操作类型枚举 |
| [`PositionLevelEnum`](src/main/java/org/jeecg/common/constant/enums/PositionLevelEnum.java) | 职位级别枚举 |
| [`RoleIndexConfigEnum`](src/main/java/org/jeecg/common/constant/enums/RoleIndexConfigEnum.java) | 角色首页配置枚举 |
| [`SysAnnmentTypeEnum`](src/main/java/org/jeecg/common/constant/enums/SysAnnmentTypeEnum.java) | 系统公告类型枚举 |
| [`Vue3MessageHrefEnum`](src/main/java/org/jeecg/common/constant/enums/Vue3MessageHrefEnum.java) | Vue3消息跳转枚举 |

## 🚀 使用方式

### Maven依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-base-constants</artifactId>
    <version>4.0.0</version>
</dependency>
```

### 使用示例

```java
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.constant.enums.MessageTypeEnum;

public class Example {
    public void demo() {
        // 使用常量
        String status = CommonConstant.STATUS_1;
        
        // 使用枚举
        MessageTypeEnum messageType = MessageTypeEnum.USER;
        String value = messageType.getType();
    }
}
```

## 📊 统计信息

- **总文件数**: 27个
- **常量类**: 12个
- **枚举类**: 15个
- **外部依赖**: 0个
- **代码行数**: 约2000行

## 🔗 相关模块

- [`jeecg-boot-base-api`](../jeecg-boot-base-api) - API接口定义模块
- [`jeecg-boot-base-utils`](../jeecg-boot-base-utils) - 工具类模块
- [`jeecg-boot-base-core-lite`](../jeecg-boot-base-core-lite) - 轻量核心模块

## 📝 版本历史

### v4.0.0 (2025-11-08)

- 🎉 首次发布
- ✅ 从 `jeecg-boot-base-core` 模块拆分
- ✅ 包含12个常量类和15个枚举类
- ✅ 零外部依赖,纯Java实现

## 📄 许可证

Apache License 2.0

## 🤝 贡献

欢迎提交 Issue 和 Pull Request!

---

**维护**: JeecgBoot 团队  
**文档**: [JeecgBoot 官方文档](http://doc.jeecg.com)