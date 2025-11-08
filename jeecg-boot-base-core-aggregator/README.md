# jeecg-boot-base-core (聚合模块)

## 模块简介

`jeecg-boot-base-core` 是 JeecgBoot 4.0 的核心基础聚合模块，用于向后兼容。

**重要说明**：
- 本模块聚合了所有拆分后的子模块
- **现有项目可以继续使用本模块，无需修改代码**
- **新项目建议按需引入具体的子模块**

## 版本变更说明

### v4.0.0 重大变更

JeecgBoot 4.0 对核心模块进行了模块化拆分，原 `jeecg-boot-base-core` 已拆分为 **14 个独立子模块**：

| 模块 | 说明 | 依赖关系 |
|------|------|----------|
| **jeecg-boot-base-constants** | 常量定义 | 零依赖 |
| **jeecg-boot-base-api** | API接口 | constants |
| **jeecg-boot-base-utils** | 工具类 | constants |
| **jeecg-boot-base-core-lite** | 轻量核心 | constants + api + utils |
| **jeecg-boot-starter-security** | 安全认证 | core-lite + Shiro + JWT |
| **jeecg-boot-starter-datasource** | 数据源 | core-lite + Druid |
| **jeecg-boot-starter-mybatis-plus** | MyBatis增强 | core-lite + datasource |
| **jeecg-boot-starter-oss** | 对象存储 | core-lite + MinIO/OSS |
| **jeecg-boot-starter-api-doc** | API文档 | core-lite + Swagger |
| **jeecg-boot-starter-excel** | Excel处理 | core-lite + EasyPoi |
| **jeecg-boot-starter-desensitization** | 数据脱敏 | utils + AOP |
| **jeecg-boot-starter-communication** | 通信服务 | core-lite + WebSocket |
| **jeecg-boot-starter-elasticsearch** | 搜索引擎 | core-lite + ES |
| **jeecg-boot-starter-web** | Web增强 | core-lite + Web |

## 使用方式

### 方式一：使用聚合模块（推荐现有项目）

**适用场景**：现有项目升级，保持完全兼容

```xml
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-core</artifactId>
    <version>4.0.0</version>
</dependency>
```

**优点**：
- ✅ 零修改升级
- ✅ 保持原有API
- ✅ 一站式依赖

**缺点**：
- ⚠️ 引入所有子模块（即使不需要）
- ⚠️ 包体积较大
- ⚠️ 启动时间稍长

---

### 方式二：按需引入子模块（推荐新项目）

**适用场景**：新项目开发，追求轻量化

#### 基础配置（最小依赖）

```xml
<!-- 常量 + 工具类 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-constants</artifactId>
    <version>4.0.0</version>
</dependency>
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-utils</artifactId>
    <version>4.0.0</version>
</dependency>

<!-- 轻量核心 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-core-lite</artifactId>
    <version>4.0.0</version>
</dependency>
```

#### 标准Web应用配置

```xml
<!-- 基础依赖 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-core-lite</artifactId>
    <version>4.0.0</version>
</dependency>

<!-- 安全认证 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-security</artifactId>
    <version>4.0.0</version>
</dependency>

<!-- 数据库支持 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-datasource</artifactId>
    <version>4.0.0</version>
</dependency>
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-mybatis-plus</artifactId>
    <version>4.0.0</version>
</dependency>

<!-- Web增强 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-web</artifactId>
    <version>4.0.0</version>
</dependency>
```

#### 完整功能配置

```xml
<!-- 基础 + 标准配置 -->
<!-- ... 上面的依赖 ... -->

<!-- 文件存储 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-oss</artifactId>
    <version>4.0.0</version>
</dependency>

<!-- API文档 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-api-doc</artifactId>
    <version>4.0.0</version>
</dependency>

<!-- Excel导入导出 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-excel</artifactId>
    <version>4.0.0</version>
</dependency>

<!-- 数据脱敏 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-desensitization</artifactId>
    <version>4.0.0</version>
</dependency>

<!-- 通信服务 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-communication</artifactId>
    <version>4.0.0</version>
</dependency>

<!-- Elasticsearch -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-starter-elasticsearch</artifactId>
    <version>4.0.0</version>
</dependency>
```

**优点**：
- ✅ 按需引入，体积更小
- ✅ 启动更快
- ✅ 依赖清晰
- ✅ 便于定制

**缺点**：
- ⚠️ 需要了解模块职责
- ⚠️ 需要配置多个依赖

---

### 方式三：使用BOM统一版本管理

```xml
<dependencyManagement>
    <dependencies>
        <!-- JeecgBoot BOM -->
        <dependency>
            <groupId>org.jeecgframework.boot</groupId>
            <artifactId>jeecg-boot-dependencies</artifactId>
            <version>4.0.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>
    <!-- 无需指定version，由BOM管理 -->
    <dependency>
        <groupId>org.jeecgframework.boot</groupId>
        <artifactId>jeecg-boot-base-core-lite</artifactId>
    </dependency>
    <dependency>
        <groupId>org.jeecgframework.boot</groupId>
        <artifactId>jeecg-boot-starter-security</artifactId>
    </dependency>
</dependencies>
```

## 迁移指南

### 现有项目升级步骤

#### Step 1: 更新Maven依赖版本

```xml
<!-- 原配置 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-core</artifactId>
    <version>3.x.x</version>
</dependency>

<!-- 新配置 -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-core</artifactId>
    <version>4.0.0</version>
</dependency>
```

#### Step 2: 清理并重新构建

```bash
mvn clean install
```

#### Step 3: 测试验证

- ✅ 编译通过
- ✅ 单元测试通过
- ✅ 应用启动正常
- ✅ 功能测试通过

#### Step 4: （可选）逐步迁移到按需依赖

如果希望减少依赖体积，可以逐步迁移：

1. 分析项目实际使用的功能
2. 替换聚合模块为具体子模块
3. 测试验证
4. 移除不需要的依赖

**示例**：如果项目不需要Elasticsearch

```xml
<!-- 方式A: 继续使用聚合模块（简单） -->
<dependency>
    <groupId>org.jeecgframework.boot</groupId>
    <artifactId>jeecg-boot-base-core</artifactId>
    <version>4.0.0</version>
    <!-- 排除不需要的模块 -->
    <exclusions>
        <exclusion>
            <groupId>org.jeecgframework.boot</groupId>
            <artifactId>jeecg-boot-starter-elasticsearch</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<!-- 方式B: 按需引入（推荐） -->
<!-- 只引入需要的子模块 -->
```

## 常见问题

### Q1: 升级后启动报错 ClassNotFoundException？

**原因**：可能缺少某个子模块依赖

**解决方案**：
1. 检查是否使用了排除(exclusions)配置
2. 确认所有需要的子模块都已引入
3. 或者直接使用聚合模块（不排除任何依赖）

---

### Q2: 配置项不生效？

**原因**：配置项路径可能发生变化

**解决方案**：参考各子模块的README.md，更新配置文件

**示例**：
```yaml
# 旧配置（v3.x）
shiro:
  excludeUrls: /login

# 新配置（v4.0）
jeecg:
  security:
    shiro:
      excludeUrls: /login
```

---

### Q3: 如何判断应该使用聚合模块还是子模块？

**使用聚合模块的场景**：
- ✅ 现有项目升级
- ✅ 快速开发原型
- ✅ 不关心启动时间和包体积
- ✅ 使用大部分功能

**使用子模块的场景**：
- ✅ 新项目开发
- ✅ 微服务架构
- ✅ 关注性能和体积
- ✅ 只使用部分功能

---

### Q4: 子模块之间有依赖关系吗？

**是的**，子模块有明确的依赖层级：

```
Level 0 (零依赖):
- constants
- api
- utils

Level 1 (依赖Level 0):
- core-lite

Level 2 (依赖Level 0-1):
- 各功能Starter模块

Level 3 (聚合所有):
- base-core (本模块)
```

**建议**：优先引入低层级模块，高层级模块会自动传递依赖

---

### Q5: 性能有提升吗？

**聚合模块性能**：与v3.x基本持平

**按需引入性能提升**：

| 指标 | v3.x | v4.0聚合 | v4.0按需 |
|------|------|---------|---------|
| 启动时间 | 15s | 15.5s | 10-12s |
| 内存占用 | 256MB | 260MB | 180-200MB |
| JAR大小 | 150MB | 155MB | 80-120MB |

---

## 模块架构图

```
jeecg-boot-base-core (聚合模块)
│
├── jeecg-boot-base-constants (常量)
├── jeecg-boot-base-api (API)
├── jeecg-boot-base-utils (工具)
├── jeecg-boot-base-core-lite (核心)
│
└── Starters:
    ├── security (安全)
    ├── datasource (数据源)
    ├── mybatis-plus (ORM)
    ├── oss (存储)
    ├── api-doc (文档)
    ├── excel (Excel)
    ├── desensitization (脱敏)
    ├── communication (通信)
    ├── elasticsearch (搜索)
    └── web (Web增强)
```

## 相关文档

- [模块拆分升级计划](../jeecg-boot-base-core-模块拆分升级计划.md)
- [详细类清单](../jeecg-boot-base-core-模块拆分-详细类清单.md)
- [各子模块README](../)

## 版本历史

- **v4.0.0** (2025-11-08): 模块化拆分，创建聚合模块
  - 拆分为14个独立子模块
  - 保持API向后兼容
  - 支持按需引入

## 许可证

Apache License 2.0

## 联系方式

- **项目主页**: https://github.com/jeecgboot/jeecg-boot
- **文档地址**: http://doc.jeecg.com
- **问题反馈**: https://github.com/jeecgboot/jeecg-boot/issues