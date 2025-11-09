
# JeecgBoot POM 依赖优化建议报告

> 生成时间: 2025-11-09  
> 项目版本: 3.8.3 → 4.0.0-SNAPSHOT (模块化重构进行中)

---

## 📊 执行摘要

### 当前状态
- ✅ **已完成**: 模块拆分架构设计和10个Starter模块创建
- ⚠️ **进行中**: 从旧版 `jeecg-boot-base-core` 迁移到新架构
- 🔄 **共存期**: 新旧两套架构并存，存在依赖冗余

### 主要问题
1. **版本不一致**: 新模块4.0.0-SNAPSHOT vs 旧模块3.8.3
2. **依赖冗余**: 新旧架构同时引入相似的依赖
3. **循环依赖风险**: api ↔ utils 之间存在潜在循环
4. **版本碎片化**: 部分依赖版本未统一管理
5. **scope使用不当**: 部分依赖的scope设置需要优化

---

## 🎯 一、依赖关系可视化分析

### 1.1 模块依赖层次图

```
层级 0: Parent POM
  └─ jeecg-boot-parent (3.8.3)
      ├─ spring-boot-starter-parent:3.1.12

层级 1: 零依赖基础
  ├─ jeecg-boot-base-constants (v4.0.0)
  │   └─ 理想: 零依赖
  │   └─ 实际: Spring + FastJSON (optional)

层级 2: API定义层
  ├─ jeecg-boot-base-api (v4.0.0)
  │   ├─ constants
  │   └─ Spring Web (provided)
  │   └─ MyBatis-Plus (provided)

层级 3: 工具类层
  ├─ jeecg-boot-base-utils (v4.0.0)
  │   ├─ constants
  │   ├─ api (optional) ⚠️ 循环依赖风险
  │   └─ Hutool + Commons

层级 4: Spring核心层
  ├─ jeecg-boot-base-core-lite (v4.0.0)
  │   ├─ constants + api + utils
  │   └─ Spring Boot + MyBatis-Plus

层级 5: 功能Starter层
  ├─ starter-datasource (v4.0.0)
  │   ├─ core-lite + Druid
  ├─ starter-mybatis-plus (v4.0.0)
  │   ├─ core-lite + datasource
  ├─ starter-oss (v4.0.0)
  │   ├─ 基础模块 + MinIO/OSS
  ├─ starter-web (v4.0.0)
  │   ├─ core-lite + AOP
  └─ ...其他8个Starter

层级 6: 聚合层
  ├─ base-core-aggregator (v4.0.0)
  │   └─ 包含所有Starter

层级 7: 业务模块层
  ├─ jeecg-system-biz (v3.8.3)
  │   ├─ system-local-api
  │   └─ base-core (旧版) 或 aggregator (新版)
  └─ jeecg-system-start (v3.8.3)
      └─ system-biz + demo
```

### 1.2 依赖传递分析

```
应用启动模块 (jeecg-system-start)
    └─ jeecg-system-biz
        └─ jeecg-system-local-api
            └─ base-core-aggregator (新) 或 base-core (旧)
                │
                ├─ [新架构路径]
                │   ├─ constants → api → utils → core-lite
                │   ├─ 10个Starter (按需引入)
                │   └─ 传递依赖: Spring + MyBatis + Druid + ...
                │
                └─ [旧架构路径]
                    └─ jeecg-boot-common
                        └─ 大量直接依赖 (80+个)
```

---

## 🔍 二、依赖问题详细分析

### 2.1 版本管理问题

#### 问题1: 版本号不一致

| 模块 | 当前版本 | 问题描述 |
|------|---------|---------|
| 新架构模块 | 4.0.0-SNAPSHOT | 10个Starter + 基础模块 |
| 旧架构模块 | 3.8.3 | base-core, system模块 |
| 父POM | 3.8.3 | 版本管理基准 |

**影响**:
- Maven依赖解析可能出现版本冲突
- IDE显示依赖树混乱
- 构建时版本警告

**建议**:
```xml
<!-- 方案1: 统一使用3.8.3 (短期) -->
<version>3.8.3</version>

<!-- 方案2: 升级到4.0.0 (长期) -->
<parent>
    <artifactId>jeecg-boot-parent</artifactId>
    <version>4.0.0</version>
</parent>
```

---

#### 问题2: 第三方库版本碎片化

| 依赖 | 版本位置 | 版本号 | 是否统一 |
|------|---------|--------|---------|
| hutool-all | parent | 5.8.25 | ✅ |
| hutool-all | mybatis-plus | 5.8.23 | ❌ 不一致 |
| hutool-all | oss | 5.8.23 | ❌ 不一致 |
| fastjson | parent | 2.0.57 | ✅ |
| fastjson2 | mybatis-plus | 2.0.43 | ❌ 混用 |
| mysql-connector | parent | 8.0.27 | ✅ |
| mysql-connector-j | datasource | 未指定 | ⚠️ 新驱动 |

**建议**:
```xml
<!-- 在父POM中统一管理 -->
<properties>
    <hutool.version>5.8.25</hutool.version>
    <fastjson.version>2.0.57</fastjson.version>
    <!-- 统一使用fastjson2 -->
    <fastjson2.version>2.0.57</fastjson2.version>
    <!-- MySQL新驱动 -->
    <mysql-connector.version>8.0.33</mysql-connector.version>
</properties>
```

---

### 2.2 依赖scope问题

#### 问题3: scope使用不当

```xml
<!-- ❌ 问题案例1: core-lite中的数据库驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <optional>true</optional>  <!-- 应该是 runtime + optional -->
</dependency>

<!-- ✅ 正确写法 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

```xml
<!-- ❌ 问题案例2: Starter中的Spring Boot依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <scope>provided</scope>  <!-- Starter不应该是provided -->
</dependency>

<!-- ✅ 正确写法 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <!-- 默认compile scope -->
</dependency>
```

**影响分析**:
- `provided` scope: 编译期可用，运行期由容器提供，**不会传递**
- `optional`: 标记为可选，**不会自动传递**给依赖方
- Starter应该传递核心依赖，不应该使用 `provided`

---

### 2.3 循环依赖风险

#### 问题4: api ↔ utils 潜在循环

**当前状态**:
```
jeecg-boot-base-api
  └─ (注释掉) jeecg-boot-base-utils

jeecg-boot-base-utils
  └─ jeecg-boot-base-api (optional)
```

**问题原因**:
- `api` 模块中的VO类使用了 `utils` 中的工具方法
- `utils` 模块中的工具类需要访问 `api` 中的异常类

**解决方案**:
```
方案1: 提取共享层
  └─ base-shared (常量+异常+接口)
      ├─ base-api (VO/DTO)
      └─ base-utils (工具类)

方案2: 职责分离
  ├─ base-api: 仅包含接口定义和DTO
  ├─ base-utils: 纯工具类,不依赖api
  └─ base-model: VO类+业务模型(依赖api和utils)

方案3: 合并模块 (简单粗暴)
  └─ base-common (api + utils)
```

---

### 2.4 依赖冗余问题

#### 问题5: 新旧架构并存导致的冗余

**示例: jeecg-system-local-api 的依赖**

```xml
<!-- 当前配置 -->
<dependencies>
    <!-- 使用聚合模块 -->
    <dependency>
        <groupId>org.jeecgframework.boot3</groupId>
        <artifactId>jeecg-boot-base-core-aggregator</artifactId>
    </dependency>
    
    <!-- 同时还依赖旧版 base-core -->
    <dependency>
        <groupId>org.jeecgframework.boot3</groupId>
        <artifactId>jeecg-boot-base-core</artifactId>
        <version>3.8.3</version>
    </dependency>
</dependencies>
```

**问题**:
- 新旧两套架构的类可能冲突
- 包大小翻倍
- 依赖树混乱

**解决方案**:
```xml
<!-- 方案1: 仅使用新架构 -->
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-base-core-aggregator</artifactId>
</dependency>

<!-- 方案2: 仅使用旧架构 (保守) -->
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-base-core</artifactId>
</dependency>

<!-- 方案3: 按需引入新架构 -->
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-base-core-lite</artifactId>
</dependency>
<dependency>
    <groupId>org.jeecgframework.boot3</groupId>
    <artifactId>jeecg-boot-starter-mybatis-plus</artifactId>
</dependency>
```

---

### 2.5 dependencyManagement使用问题

#### 问题6: 部分Starter模块未使用父POM的版本管理

**问题案例**:
```xml
<!-- starter-mybatis-plus/pom.xml -->
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.8.23</version>  <!-- ❌ 硬编码版本 -->
</dependency>

<dependency>
    <groupId>com.alibaba.fastjson2</groupId>
    <artifactId>fastjson2</artifactId>
    <version>2.0.43</version>  <!-- ❌ 硬编码版本 -->
</dependency>
```

**正确做法**:
```xml
<!-- 子模块pom.xml -->
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <!-- ✅ 继承父POM的版本管理 -->
</dependency>

<!-- 父POM中定义 -->
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>${hutool.version}</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

---

## 💡 三、优化建议方案

### 3.1 短期优化 (1-2周)

#### 优先级P0: 修复版本不一致

**目标**: 统一所有模块版本号

```xml
<!-- 步骤1: 修改父POM版本 -->
<groupId>org.jeecgframework.boot3</groupId>
<artifactId>jeecg-boot-parent</artifactId>
<version>3.8.4-SNAPSHOT</version>

<!-- 步骤2: 修改所有子模块版本 -->
<parent>
    <artifactId>jeecg-boot-parent</artifactId>
    <version>3.8.4-SNAPSHOT</version>
</parent>
<version>3.8.4-SNAPSHOT</version>
```

**执行脚本**:
```bash
# 批量修改版本号
mvn versions:set -DnewVersion=3.8.4-SNAPSHOT
mvn versions:commit
```

---

#### 优先级P0: 统一第三方库版本

**目标**: 消除版本碎片化

```xml
<!-- 父POM: 