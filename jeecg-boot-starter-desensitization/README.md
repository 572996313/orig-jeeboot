
# JeecgBoot 数据脱敏 Starter

## 简介

`jeecg-boot-starter-desensitization` 是 JeecgBoot 框架的数据脱敏功能模块,提供了灵活的数据脱敏和加密解密能力。

## 功能特性

- ✅ 支持多种脱敏类型:中文姓名、身份证号、手机号、固定电话、地址、邮箱、银行卡号等
- ✅ 支持 AES 加密/解密
- ✅ 基于注解的声明式脱敏
- ✅ 支持 JSON 序列化时自动脱敏
- ✅ 支持方法级别的批量加密/解密(AOP)

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-starter-desensitization</artifactId>
    <version>3.8.3</version>
</dependency>
```

### 2. 配置启用(可选)

在 `application.yml` 中配置:

```yaml
jeecg:
  desensitization:
    enabled: true  # 默认为 true,可以设置为 false 禁用脱敏功能
```

### 3. 使用示例

#### 字段级脱敏

使用 `@SensitiveField` 注解标记需要加密的字段:

```java
public class User {
    @SensitiveField(type = SensitiveEnum.ENCODE)
    private String password;
    
    @SensitiveField(type = SensitiveEnum.MOBILE_PHONE)
    private String phone;
    
    @SensitiveField(type = SensitiveEnum.ID_CARD)
    private String idCard;
}
```

#### JSON 序列化脱敏

使用 `@Sensitive` 注解在 JSON 序列化时自动脱敏:
