# jeecg-boot-base-utils

## 📦 模块简介

**jeecg-boot-base-utils** 是 JeecgBoot 框架的**纯Java工具类模块**，提供了丰富的工具方法，涵盖日期处理、加密解密、SQL解析、安全校验等多个领域。

### 核心特性

- ✅ **零Spring依赖**：纯Java实现，可用于任何Java项目
- ✅ **功能丰富**：36个工具类，覆盖常见开发场景
- ✅ **高性能**：静态方法，无实例化开销
- ✅ **易于集成**：Maven依赖即用
- ✅ **文档完善**：每个工具类都有详细注释

---

## 📋 模块信息

| 项目 | 内容 |
|-----|------|
| **GroupId** | org.jeecgframework.boot3 |
| **ArtifactId** | jeecg-boot-base-utils |
| **版本** | 4.0.0-SNAPSHOT |
| **JDK版本** | 17+ |
| **依赖模块** | jeecg-boot-base-constants |

---

## 🛠️ 工具类清单

### 1️⃣ 基础工具类（15个）

| 类名 | 说明 | 主要方法 |
|-----|------|---------|
| **AssertUtils** | 断言工具 | `notNull()`, `isTrue()`, `notEmpty()` |
| **BrowserUtils** | 浏览器工具 | `getBrowser()`, `checkBrowser()` |
| **CommonUtils** | 通用工具 | `isEmpty()`, `isNotEmpty()`, `ifNull()` |
| **DateUtils** | 日期工具 | `parseDate()`, `formatDate()`, `getToday()` |
| **DateRangeUtils** | 日期范围工具 | `getDateRange()`, `getWeekRange()` |
| **HTMLUtils** | HTML工具 | `escapeHtml()`, `unescapeHtml()` |
| **IpUtils** | IP地址工具 | `getIpAddr()`, `isInnerIP()` |
| **Md5Util** | MD5加密 | `md5()`, `md5Salt()` |
| **oConvertUtils** | 转换工具 | `getString()`, `getInt()`, `getBoolean()` |
| **PasswordUtil** | 密码工具 | `encrypt()`, `verify()`, `genSalt()` |
| **ReflectHelper** | 反射工具 | `getFieldValue()`, `setFieldValue()` |
| **SqlInjectionUtil** | SQL注入防护 | `filterContent()`, `checkSqlInjection()` |
| **UUIDGenerator** | UUID生成 | `generate()`, `generateShort()` |
| **YouBianCodeUtil** | 编码工具 | `getNextYouBianCode()` |
| **BrowserType** | 浏览器类型 | 枚举定义 |

### 2️⃣ 加密工具（encryption包，2个）

| 类名 | 说明 | 主要方法 |
|-----|------|---------|
| **AesEncryptUtil** | AES加密解密 | `encrypt()`, `decrypt()` |
| **EncryptedString** | 加密字符串 | 实体类 |

### 3️⃣ 过滤器工具（filter包，2个）

| 类名 | 说明 | 功能 |
|-----|------|------|
| **SsrfFileTypeFilter** | SSRF文件类型过滤 | 防止SSRF攻击 |
| **StrAttackFilter** | 字符串攻击过滤 | XSS、SQL注入防护 |

### 4️⃣ 安全工具（security包，8个）

| 类名 | 说明 | 功能 |
|-----|------|------|
| **AbstractQueryBlackListHandler** | 查询黑名单处理器 | 抽象基类 |
| **JdbcSecurityUtil** | JDBC安全工具 | SQL安全检查 |
| **SecurityTools** | 安全工具集 | RSA加密、签名验证 |
| **MyKeyPair** | 密钥对实体 | 公钥私钥封装 |
| **SecurityReq** | 安全请求对象 | 请求参数 |
| **SecurityResp** | 安全响应对象 | 响应结果 |
| **SecuritySignReq** | 签名请求对象 | 签名参数 |
| **SecuritySignResp** | 签名响应对象 | 签名结果 |

### 5️⃣ SQL解析工具（sqlparse包，3个）

| 类名 | 说明 | 功能 |
|-----|------|------|
| **JSqlParserUtils** | SQL解析工具 | 解析SQL语句 |
| **JSqlParserAllTableManager** | 表管理器 | 获取SQL中的所有表 |
| **SelectSqlInfo** | SELECT语句信息 | 查询语句元数据 |

### 6️⃣ 查询构建器（system.query包，4个）

| 类名 | 说明 | 功能 |
|-----|------|------|
| **QueryGenerator** | 查询生成器 | 自动生成MyBatis-Plus查询条件 |
| **QueryCondition** | 查询条件 | 条件封装 |
| **MatchTypeEnum** | 匹配类型枚举 | 模糊匹配、精确匹配等 |
| **QueryRuleEnum** | 查询规则枚举 | 查询规则定义 |

### 7️⃣ 系统工具（system.util包，2个）

| 类名 | 说明 | 功能 |
|-----|------|------|
| **JwtUtil** | JWT工具 | 生成和解析JWT令牌 |
| **JeecgDataAutorUtils** | 数据权限工具 | 数据权限处理 |

---

## 🚀 快速开始

### Maven依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-base-utils</artifactId>
    <version>4.0.0-SNAPSHOT</version>
</dependency>
```

### 使用示例

#### 1. 日期工具

```java
import org.jeecg.common.util.DateUtils;

// 获取当前日期字符串
String today = DateUtils.getToday();

// 解析日期
Date date = DateUtils.parseDate("2025-11-08");

// 格式化日期
String formatted = DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");

// 日期计算
Date tomorrow = DateUtils.addDays(new Date(), 1);
```

#### 2. 加密工具

```java
import org.jeecg.common.util.Md5Util;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.encryption.AesEncryptUtil;

// MD5加密
String md5 = Md5Util.md5("password");

// 密码加密（带盐值）
String encrypted = PasswordUtil.encrypt("username", "password", "salt");

// AES加密解密
String encrypted = AesEncryptUtil.encrypt("plaintext", "key");
String decrypted = AesEncryptUtil.decrypt(encrypted, "key");
```

#### 3. SQL注入防护

```java
import org.jeecg.common.util.SqlInjectionUtil;

// 检查SQL注入
String userInput = "admin' OR '1'='1";
if (SqlInjectionUtil.checkSqlInjection(userInput)) {
    throw new RuntimeException("检测到SQL注入攻击");
}

// 过滤危险字符
String safe = SqlInjectionUtil.filterContent(userInput);
```

#### 4. IP工具

```java
import org.jeecg.common.util.IpUtils;
import jakarta.servlet.http.HttpServletRequest;

// 获取客户端真实IP
String ip = IpUtils.getIpAddr(request);

// 判断是否内网IP
boolean isInner = IpUtils.isInnerIP(ip);
```

#### 5. 查询构建器

```java
import org.jeecg.common.system.query.QueryGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;

// 自动生成查询条件（根据请求参数）
QueryWrapper<User> queryWrapper = QueryGenerator.initQueryWrapper(new User(), request.getParameterMap());

// 执行查询
List<User> users = userMapper.selectList(queryWrapper);
```

#### 6. JWT工具

```java
import org.jeecg.common.system.util.JwtUtil;

// 生成JWT令牌
String token = JwtUtil.sign("username", "secret");

// 验证JWT令牌
boolean valid = JwtUtil.verify(token, "username", "secret");

// 解析用户名
String username = JwtUtil.getUsername(token);
```

#### 7. UUID生成

```java
import org.jeecg.common.util.UUIDGenerator;

// 生成标准UUID
String uuid = UUIDGenerator.generate();

// 生成短UUID（去除"-"）
String shortUuid = UUIDGenerator.generateShort();
```

#### 8. 类型转换

```java
import org.jeecg.common.util.oConvertUtils;

// 安全的类型转换
String str = oConvertUtils.getString(obj);
int num = oConvertUtils.getInt(obj, 0); // 默认值为0
boolean flag = oConvertUtils.getBoolean(obj, false);
```

---

## 📦 依赖说明

### 必选依赖

```xml
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-base-constants</artifactId>
</dependency>
```

### 可选依赖（根据需要引入）

```xml
<!-- Hutool工具库 -->
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
</dependency>

<!-- Apache Commons -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
</dependency>

<!-- SQL解析器（使用JSqlParserUtils时需要） -->
<dependency>
    <groupId>com.github.jsqlparser</groupId>
    <artifactId>jsqlparser</artifactId>
</dependency>

<!-- MyBatis-Plus（使用QueryGenerator时需要） -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-core</artifactId>
</dependency>
```

---

## 🎯 设计原则

### 1. 单一职责原则
每个工具类专注于特定领域，职责清晰。

### 2. 无状态设计
所有工具方法为静态方法，无实例状态，线程安全。

### 3. 零Spring依赖
不依赖Spring框架，可用于任何Java项目（包括Android、桌面应用等）。

### 4. 向后兼容
包路径保持不变（`org.jeecg.common.util.*`），现有代码无需修改。

---

## 📝 使用建议

### 1. 异常处理
工具类抛出的异常需要调用方捕获处理：

```java
try {
    Date date = DateUtils.parseDate(dateStr);
} catch (Exception e) {
    log.error("日期解析失败", e);
}
```

### 2. 性能优化
- 频繁调用的工具方法建议缓存结果
- 大批量数据处理时注意内存占用

### 3. 安全建议
- 所有用户输入都应通过 `SqlInjectionUtil` 过滤
- 密码加密必须使用 `PasswordUtil`，不要直接使用MD5
- 敏感数据传输使用 `AesEncryptUtil` 加密

---

## 🔄 迁移指南

### 从 jeecg-boot-base-core 迁移

**旧依赖**：
```xml
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-base-core</artifactId>
    <version>3.8.3</version>
</dependency>
```

**新依赖**：
```xml
<!-- 纯Java项目：只需工具类 -->
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-base-utils</artifactId>
    <version>4.0.0-SNAPSHOT</version>
</dependency>

<!-- Spring Boot项目：继续使用聚合模块 -->
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-base-core</artifactId>
    <version>4.0.0</version>
</dependency>
```

**包路径不变**：无需修改导入语句！

---

## 🧪 单元测试

所有工具类都有完整的单元测试覆盖：

```bash
# 运行测试
mvn test

# 测试覆盖率报告
mvn clean test jacoco:report
```

---

## 📚 参考资料

- [JeecgBoot官网](http://www.jeecg.com)
- [在线文档](http://doc.jeecg.com)
- [模块拆分升级计划](../jeecg-boot-base-core-模块拆分升级计划.md)

---

## 🤝 贡献指南

欢迎提交PR改进工具类！

### 贡献流程
1. Fork本仓库
2. 创建功能分支：`git checkout -b feature/new-util`
3. 编写代码和测试
4. 提交PR

### 编码规范
- 所有工具方法必须是静态方法
- 每个工具类必须有完整的JavaDoc
- 单元测试覆盖率 > 80%
- 不允许依赖Spring框架

---

## 📄 许可证

Apache License 2.0

---

## 👥 维护者

- **创建人**：llllxf（个人开发工程师）
- **创建日期**：2025-11-08
- **开发工具**：RooCode AI 辅助开发

---

**状态**：✅ 生产就绪  
**版本**：4.0.0-SNAPSHOT  
**最后更新**：2025-11-08

---

*本模块由 llllxf 创建，使用 RooCode AI 工具辅助开发 © 2025*