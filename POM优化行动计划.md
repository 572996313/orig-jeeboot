
# JeecgBoot POM 依赖优化行动计划

> 📅 制定时间: 2025-11-09  
> 🎯 目标: 解决依赖冗余、版本冲突、优化模块结构  
> ⏱️ 预计周期: 2-4周

---

## 🎯 优化目标

### 核心目标
1. ✅ **统一版本管理**: 消除版本碎片化，所有模块使用统一版本号
2. ✅ **消除依赖冗余**: 移除新旧架构并存导致的重复依赖
3. ✅ **优化依赖层次**: 建立清晰的模块依赖层次结构
4. ✅ **规范scope使用**: 正确使用compile/provided/optional/runtime
5. ✅ **减少包体积**: 通过按需引入减少最终应用包大小

### 量化指标
| 指标 | 当前值 | 目标值 | 优化幅度 |
|------|--------|--------|---------|
| 应用启动包大小 | ~150MB | <100MB | -33% |
| 直接依赖数量 | 80+ | <30 | -62% |
| 传递依赖数量 | 300+ | <200 | -33% |
| 版本冲突数量 | 15+ | 0 | -100% |
| 模块耦合度 | 高 | 低 | - |

---

## 📋 阶段一: 版本统一 (Week 1)

### 任务1.1: 统一模块版本号
**优先级**: P0 (最高)  
**预计工时**: 2小时

**当前问题**:
```
父POM: 3.8.3
新模块: 4.0.0-SNAPSHOT
旧模块: 3.8.3
业务模块: 3.8.3
```

**执行步骤**:
```bash
# 步骤1: 决定统一版本号
# 选项A: 保守升级到 3.8.4-SNAPSHOT
# 选项B: 激进升级到 4.0.0-SNAPSHOT

# 步骤2: 批量修改版本号
cd /path/to/jeecgboot-boot
mvn versions:set -DnewVersion=3.8.4-SNAPSHOT
mvn versions:commit

# 步骤3: 验证构建
mvn clean install -DskipTests

# 步骤4: 提交变更
git add .
git commit -m "chore: 统一所有模块版本号为3.8.4-SNAPSHOT"
```

**验证标准**:
- [ ] 所有模块版本号一致
- [ ] Maven构建成功
- [ ] 无版本冲突警告

---

### 任务1.2: 统一第三方库版本
**优先级**: P0  
**预计工时**: 3小时

**需要统一的依赖**:
```xml
<!-- 父POM需要添加/更新的版本管理 -->
<properties>
    <!-- 工具库统一 -->
    <hutool.version>5.8.25</hutool.version>
    
    <!-- JSON库统一 - 推荐全部使用fastjson2 -->
    <fastjson.version>2.0.57</fastjson.version>
    <fastjson2.version>2.0.57</fastjson2.version>
    
    <!-- 数据库驱动统一 -->
    <mysql-connector.version>8.0.33</mysql-connector.version>
    <mysql-connector-j.version>8.0.33</mysql-connector-j.version>
    
    <!-- MyBatis-Plus统一 -->
    <mybatis-plus.version>3.5.5</mybatis-plus.version>
    <jsqlparser.version>4.6</jsqlparser.version>
    
    <!-- Shiro统一 -->
    <shiro.version>2.0.4</shiro.version>
    
    <!-- 对象存储统一 -->
    <minio.version>8.5.7</minio.version>
    <aliyun-oss.version>3.17.4</aliyun-oss.version>
    
    <!-- API文档统一 -->
    <knife4j.version>4.5.0</knife4j.version>
    <springdoc.version>2.6.0</springdoc.version>
</properties>
```

**执行步骤**:
1. 在父POM的`<dependencyManagement>`中添加所有需要统一管理的依赖
2. 修改子模块POM，移除硬编码版本号
3. 全局搜索并替换不一致的版本号

**检查脚本**:
```bash
# 查找所有硬编码版本号的依赖
find . -name "pom.xml" -exec grep -H "<version>[0-9]" {} \; | grep -v "jeecgboot.version"
```

---

## 📋 阶段二: 依赖清理 (Week 1-2)

### 任务2.1: 移除新旧架构并存
**优先级**: P1  
**预计工时**: 4小时

**问题模块**: `jeecg-system-local-api`

**当前配置** (❌ 问题):
```xml
<dependencies>
    <!-- 新架构 -->
    <dependency>
        <groupId>org.jeecgframework.boot3</groupId>
        <artifactId>jeecg-boot-base-core-aggregator</artifactId>
    </dependency>
    
    <!-- 旧架构 - 冗余! -->
    <dependency>
        <groupId>org.jeecgframework.boot3</groupId>
        <artifactId>jeecg-boot-base-core</artifactId>
    </dependency>
</dependencies>
```

**优化方案A: 完全迁移到新架构** (✅ 推荐):
```xml
<dependencies>
    <!-- 仅使用新架构聚合包 -->
    <dependency>
        <groupId>org.jeecgframework.boot3</groupId>
        <artifactId>jeecg-boot-base-core-aggregator</artifactId>
    </dependency>
</dependencies>
```

**优化方案B: 按需引入** (✅ 更优):
```xml
<dependencies>
    <!-- 核心必选 -->
    <dependency>
        <groupId>org.jeecgframework.boot3</groupId>
        <artifactId>jeecg-boot-base-core-lite</artifactId>
    </dependency>
    
    <!-- 按需引入Starter -->
    <dependency>
        <groupId>org.jeecgframework.boot3</groupId>
        <artifactId>jeecg-boot-starter-mybatis-plus</artifactId>
    </dependency>
    <dependency>
        <groupId>org.jeecgframework.boot3</groupId>
        <artifactId>jeecg-boot-starter-web</artifactId>
    </dependency>
    <!-- 其他按需... -->
</dependencies>
```

**回归测试清单**:
- [ ] 应用能正常启动
- [ ] 数据库连接正常
- [ ] API接口可访问
- [ ] 登录认证功能正常
- [ ] 文件上传下载正常

---

### 任务2.2: 优化依赖scope
**优先级**: P2  
**预计工时**: 3小时

**需要修复的场景**:

#### 场景1: 数据库驱动应该是runtime
```xml
<!-- ❌ 错误 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <optional>true</optional>
</dependency>

<!-- ✅ 正确 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

#### 场景2: Starter不应该使用provided
```xml
<!-- ❌ 错误 - Starter中 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <scope>provided</scope>
</dependency>

<!-- ✅ 正确 - Starter应该传递依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <!-- 默认compile scope -->
</dependency>
```

#### 场景3: 编译期注解处理器应该是optional
```xml
<!-- ✅ 正确 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <scope>provided</scope>
    <optional>true</optional>
</dependency>
```

**批量检查命令**:
```bash
# 查找所有scope为provided的依赖
grep -r "<scope>provided</scope>" . --include="pom.xml"

# 查找所有optional为true的依赖
grep -r "<optional>true</optional>" . --include="pom.xml"
```

---

### 任务2.3: 解决循环依赖问题
**优先级**: P1  
**预计工时**: 6小时

**当前问题**:
```
jeecg-boot-base-api ⇄ jeecg-boot-base-utils
```

**解决方案A: 提取共享模块** (✅ 推荐):
```
创建新模块结构:
├─ jeecg-boot-base-shared (常量+异常+基础接口)
├─ jeecg-boot-base-model (VO/DTO/Entity)
└─ jeecg-boot-base-utils (工具类)

依赖关系:
shared (零依赖)
  ├─ model (依赖shared)
  └─ utils (依赖shared)
```

**实施步骤**:
1. 创建 `jeecg-boot-base-shared` 模块
2. 将 constants 和 api 中的基础类移动到 shared
3. 重构 api 为 model 模块
4. 更新 utils 的依赖关系
5. 更新所有引用模块

**风险评估**: 🔴 高 (需要大量重构)  
**建议**: 可以作为4.0.0版本的重构任务

---

## 📋 阶段三: 优化配置 (Week 2-3)

### 任务3.1: 标准化dependencyManagement
**优先级**: P2  
**预计工时**: 4小时

**目标**: 所有第三方依赖版本在父POM统一管理

**父POM模板**:
```xml
<dependencyManagement>
    <dependencies>
        <!-- ========== 内部模块 ========== -->
        <!-- Phase 1: 基础模块 -->
        <dependency>
            <groupId>org.jeecgframework.boot3</groupId>
            <artifactId>jeecg-boot-base-constants</artifactId>
            <version>${jeecgboot.version}</version>
        </dependency>
        <!-- ...其他内部模块 -->
        
        <!-- ========== Spring生态 ========== -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        
        <!-- ========== 数据库 ========== -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>${mybatis-plus.version}</version>
        </dependency>
        
        <!-- ========== 工具库 ========== -->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>${hutool.version}</version>
        </dependency>
        
        <!-- ...其他依赖 -->
    </dependencies>
</dependencyManagement>
```

**检查清单**:
- [ ] 所有子模块移除硬编码版本
- [ ] 父POM包含所有需要的依赖声明
- [ ] 版本号通过properties管理
- [ ] 构建无警告

---

### 任务3.2: 添加依赖分析插件
**优先级**: P3  
**预计工时**: 2小时

**在父POM中添加**:
```xml
<build>
    <plugins>
        <!-- 依赖分析插件 -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-dependency-plugin</artifactId>
            <version>3.6.1</version>
            <executions>
                <execution>
                    <id>analyze</id>
                    <goals>
                        <goal>analyze-only</goal>
                    </goals>
                    <configuration>
                        <failOnWarning>false</failOnWarning>
                        <ignoreNonCompile>true</ignoreNonCompile>
                    </configuration>
                </execution>
            </executions>
        </plugin>
        
        <!-- 版本一致性检查 -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-enforcer-plugin</artifactId>
            <version>3.4.1</version>
            <executions>
                <execution>
                    <id>enforce-versions</id>
                    <goals>
                        <goal>enforce</goal>
                    </goals>
                    <configuration>
                        <rules>
                            <dependencyConvergence/>
                            <requireMavenVersion>
                                <version>[3.6.0,)</version>
                            </requireMavenVersion>
                            <requireJavaVersion>
                                <version>[17,)</version>
                            </requireJavaVersion>
                        </rules>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

**使用命令**:
```bash
# 分析未使用的依赖
mvn dependency:analyze

# 查看依赖树
mvn dependency:tree

# 查找重复依赖
mvn dependency:tree -Dverbose

# 检查版本冲突
mvn enforcer:enforce
```

---

## 📋 阶段四: 文档和测试 (Week 3-4)

### 任务4.1: 更新依赖文档
**优先级**: P2  
**预计工时**: 4小时

**需要创建/更新的文档**:
1. `README-依赖管理.md` - 依赖管理规范
2. `新架构迁移指南.md` - 从旧架构迁移步骤
3. `Starter使用指南.md` - 