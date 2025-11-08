# jeecg-boot-starter-desensitization

JeecgBoot 数据脱敏 Starter 模块，提供敏感数据自动脱敏功能。

## 模块简介

本模块是从 `jeecg-boot-base-core` 拆分出的独立 Starter，专注于敏感数据脱敏功能。支持多种脱敏策略，可通过注解和配置灵活控制脱敏行为。

### 核心特性

- ✅ **多种脱敏类型**：支持姓名、身份证、手机号、邮箱、银行卡、地址等常见敏感信息脱敏
- ✅ **双重脱敏策略**：支持掩码（mask）和加密（encrypt）两种策略
- ✅ **注解驱动**：使用 `@Sensitive` 注解标记需要脱敏的字段
- ✅ **自动脱敏**：支持 JSON 序列化自动脱敏和方法返回值自动脱敏
- ✅ **灵活配置**：支持自定义脱敏规则和正则表达式
- ✅ **零侵入**：基于 AOP 和 Jackson 序列化器，无需修改业务代码

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-desensitization</artifactId>
    <version>4.0.0</version>
</dependency>
```

### 2. 配置启用

在 `application.yml` 中添加配置：

```yaml
jeecg:
  desensitization:
    enabled: true              # 启用脱敏功能
    strategy: mask             # 脱敏策略: mask(掩码) | encrypt(加密)
    json-enabled: true         # 启用JSON序列化脱敏
    method-enabled: true       # 启用方法返回值脱敏
```

### 3. 使用注解

在实体类字段上使用 `@Sensitive` 注解：

```java
import org.jeecg.common.desensitization.annotation.Sensitive;
import org.jeecg.common.desensitization.enums.SensitiveType;

public class User {
    
    @Sensitive(type = SensitiveType.CHINESE_NAME)
    private String name;           // 姓名脱敏: 张三 -> 张*
    
    @Sensitive(type = SensitiveType.ID_CARD)
    private String idCard;         // 身份证脱敏: 110101199001011234 -> 110101********1234
    
    @Sensitive(type = SensitiveType.MOBILE_PHONE)
    private String phone;          // 手机号脱敏: 13812345678 -> 138****5678
    
    @Sensitive(type = SensitiveType.EMAIL)
    private String email;          // 邮箱脱敏: user@example.com -> u****@example.com
    
    @Sensitive(type = SensitiveType.BANK_CARD)
    private String bankCard;       // 银行卡脱敏: 6222021234567890 -> 6222 **** **** 7890
    
    @Sensitive(type = SensitiveType.ADDRESS)
    private String address;        // 地址脱敏: 北京市朝阳区xxx街道 -> 北京市朝阳****
    
    @Sensitive(type = SensitiveType.PASSWORD)
    private String password;       // 密码完全隐藏: 123456 -> ******
    
    // 自定义脱敏规则
    @Sensitive(
        type = SensitiveType.CUSTOM,
        regex = "^(\\d{4})\\d+(\\d{4})$",
        replacement = "$1****$2"
    )
    private String customField;
    
    // getter/setter...
}
```

### 4. 自动脱敏

当 Controller 返回包含敏感字段的对象时，会自动脱敏：

```java
@RestController
@RequestMapping("/user")
public class UserController {
    
    @GetMapping("/{id}")
    public Result<User> getUser(@PathVariable String id) {
        User user = userService.getById(id);
 | 显示前6个字符 | 北京市朝阳区xxx街道 → 北京市朝阳**** |
| 邮箱 | `EMAIL` | 显示第一个字母和域名 | user@example.com → u****@example.com |
| 银行卡号 | `BANK_CARD` | 显示前4位和后4位 | 6222021234567890 → 6222 **** **** 7890 |
| 密码 | `PASSWORD` | 完全隐藏 | 123456 → ****** |
| 车牌号 | `CAR_LICENSE` | 显示前3位和后1位 | 京A12345 → 京A1***5 |
| 自定义 | `CUSTOM` | 使用自定义正则 | 根据配置 |

## 配置说明

### 基础配置

```yaml
jeecg:
  desensitization:
    # 是否启用脱敏功能（默认: true）
    enabled: true
    
    # 脱敏策略（默认: mask）
    # mask: 掩码策略，使用*号隐藏
    # encrypt: 加密策略，使用加密算法
    strategy: mask
    
    # 是否启用JSON序列化脱敏（默认: true）
    json-enabled: true
    
    # 是否启用方法返回值脱敏（默认: true）
    method-enabled: true
```

### 掩码策略配置

```yaml
jeecg:
  desensitization:
    strategy: mask
    mask:
      phone-pattern: "^(\\d{3})\\d{4}(\\d{4})$"
      phone-replacement: "$1****$2"
      id-card-pattern: "^(\\d{6})\\d{8}(\\d{4})$"
      id-card-replacement: "$1********$2"
```

### 加密策略配置

```yaml
jeecg:
  desensitization:
    strategy: encrypt
    encrypt:
      algorithm: AES
      key: "your-secret-key-16byte"
      iv: "your-iv-16-byte!!"
      base64-encode: true
```

## 使用示例

详细使用示例请参考源码中的测试用例。

## 依赖模块

```
jeecg-boot-starter-desensitization
├── jeecg-boot-base-constants (必选)
├── jeecg-boot-base-api (必选)
├── jeecg-boot-base-utils (必选)
├── Spring Boot Starter (必选)
├── Spring Boot Starter AOP (必选)
├── Jackson Databind (必选)
└── Hutool Crypto (可选)
```

## 版本历史

| 版本 | 日期 | 说明 |
|-----|------|------|
| 4.0.0 | 2025-11 | 从 jeecg-boot-base-core 拆分独立模块 |

## 技术支持

- 官方文档: http://doc.jeecg.com
- GitHub: https://github.com/jeecgboot/jeecg-boot
- Gitee: https://gitee.com/jeecg/jeecg-boot

---

**© 2025 JeecgBoot Team**
        // 返回时自动脱敏，无需手动处理
        return Result.ok(user);
    }
}
```

## 支持的脱敏类型

| 类型 | 枚举值 | 说明 | 示例 |
|-----|-------|------|------|
| 中文姓名 | `CHINESE_NAME` | 保留姓，隐藏名 | 张三 → 张* |
| 身份证号 | `ID_CARD` | 显示前6位和后4位 | 110101199001011234 → 110101********1234 |
| 手机号 | `MOBILE_PHONE` | 显示前3位和后4位 | 13812345678 → 138****5678 |
| 座机号 | `FIXED_PHONE` | 显示前4位和后2位 | 01012345678 → 0101****78 |
| 地址 | `ADDRESS` 