
# JeecgBoot Base-Core 模块迁移执行计划

## 📋 项目概述

**目标**：将 `jeecg-boot-base-core` (v3.8.3) 中的类迁移到新的模块化架构中

**当前状态**：
- base-core包含202个源文件
- 识别出30+个关键类需要迁移
- 其他模块有大量引用需要更新

**预计时间**：5-7小时

**风险等级**：⚠️ 高（影响范围广，需要谨慎操作）

---

## 🎯 迁移策略

采用**渐进式迁移**策略，分4个阶段执行：

```
阶段1：准备工作 (1h)
   ↓
阶段2：VO类迁移 (2h)
   ↓
阶段3：工具类和注解迁移 (2h)
   ↓
阶段4：验证和清理 (1h)
```

---

## 📦 目标模块映射

根据类的职责，将base-core中的类迁移到对应模块：

| 类型 | 目标模块 | 类数量 |
|------|---------|--------|
| VO类（LoginUser等） | `jeecg-boot-base-api` | ~8个 |
| 基础Controller/Entity | `jeecg-boot-base-core-lite` | ~5个 |
| 工具类（RedisUtil等） | `jeecg-boot-base-utils` | ~10个 |
| 常量类 | `jeecg-boot-base-constants` | ~3个 |
| 注解类（AutoLog等） | `jeecg-boot-base-api` | ~4个 |

---

## 🔧 阶段1：准备工作 (预计1小时)

### 1.1 创建迁移分支
```bash
git checkout -b feature/migrate-base-core-phase1
git status
```

### 1.2 完整备份
```bash
# 执行备份脚本
python migrate-base-core-backup.py

# 验证备份
ls -la base-core-migration-backup/
```

### 1.3 建立测试基准
```bash
# 运行所有测试并记录结果
mvn clean test > test-baseline-before-migration.txt 2>&1

# 记录编译状态
mvn clean compile > compile-baseline-before-migration.txt 2>&1

# 记录依赖树
mvn dependency:tree > dependency-tree-before-migration.txt
```

### 1.4 分析类引用关系
```bash
# 执行分析脚本
python analyze-base-core-references.py

# 输出：base-core-class-references.json
```

### 1.5 创建迁移检查清单
- [ ] 备份完成
- [ ] 测试基准建立
- [ ] 引用关系分析完成
- [ ] 迁移工具脚本准备就绪

---

## 🔧 阶段2：VO类迁移 (预计2小时)

### 2.1 迁移优先级

**P0 - 核心VO类**（必须先迁移）：
1. `LoginUser.java` - 用户登录信息
2. `SysUserCacheInfo.java` - 用户缓存信息
3. `UserAccountInfo.java` - 用户账户信息

**P1 - 业务VO类**：
4. `SysCategoryModel.java` - 分类模型
5. `DictModel.java` - 字典模型
6. `ComboModel.java` - 下拉框模型
7. `TreeModel.java` - 树形模型
8. `SysPermissionDataRuleModel.java` - 权限数据规则

### 2.2 迁移步骤（针对每个类）

#### Step 1: 复制类文件
```bash
# 示例：迁移LoginUser
python migrate-base-core-class.py --class LoginUser --target jeecg-boot-base-api
```

#### Step 2: 更新包名
```java
// 旧包名
package org.jeecg.common.system.vo;

// 新包名
package org.jeecg.common.api.vo;
```

#### Step 3: 更新导入语句
```bash
# 自动更新所有引用
python update-class-references.py --class LoginUser --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
```

#### Step 4: 编译验证
```bash
mvn clean compile -pl jeecg-boot-base-api -am
```

#### Step 5: 更新其他模块的引用
```bash
# 更新所有引用该类的模块
mvn clean compile
```

#### Step 6: 运行测试
```bash
mvn test -Dtest=*LoginUser*
```

### 2.3 迁移检查清单（每个类完成后检查）
- [ ] 类文件已复制到目标模块
- [ ] 包名已更新
- [ ] 所有引用已更新
- [ ] 编译通过（无错误）
- [ ] 相关测试通过
- [ ] 提交一个小commit

---

## 🔧 阶段3：工具类和注解迁移 (预计2小时)

### 3.1 基础类迁移

**目标模块**：`jeecg-boot-base-core-lite`

**迁移列表**：
1. `JeecgController.java` - 基础控制器
2. `JeecgEntity.java` - 基础实体类
3. `BaseMap.java` - 基础Map类
4. `JeecgBeanUtil.java` - Bean工具类

**迁移命令**：
```bash
python migrate-base-core-class.py --class JeecgController --target jeecg-boot-base-core-lite
python migrate-base-core-class.py --class JeecgEntity --target jeecg-boot-base-core-lite
# ... 其他类
```

### 3.2 工具类迁移

**目标模块**：`jeecg-boot-base-utils`

**迁移列表**：
1. `RedisUtil.java` - Redis工具类
2. `JwtUtil.java` - JWT工具类
3. `TokenUtils.java` - Token工具类
4. `PasswordUtil.java` - 密码工具类
5. `PathMatcherUtil.java` - 路径匹配工具
6. `SqlInjectionUtil.java` - SQL注入防护
7. `CommonUtils.java` - 通用工具类
8. `SpringContextUtils.java` - Spring上下文工具
9. `DateUtils.java` - 日期工具类（如果不在base-utils中）
10. `IpUtils.java` - IP工具类

**批量迁移命令**：
```bash
python migrate-base-core-batch.py --classes RedisUtil,JwtUtil,TokenUtils,PasswordUtil,PathMatcherUtil,SqlInjectionUtil,CommonUtils,SpringContextUtils --target jeecg-boot-base-utils
```

### 3.3 常量类迁移

**目标模块**：`jeecg-boot-base-constants`

**迁移列表**：
1. `CacheConstant.java` - 缓存常量
2. `CommonConstant.java` - 通用常量（如果不存在）
3. `SecurityConstants.java` - 安全常量

**迁移命令**：
```bash
python migrate-base-core-batch.py --classes CacheConstant,SecurityConstants --target jeecg-boot-base-constants
```

### 3.4 注解类迁移

**目标模块**：`jeecg-boot-base-api`

**迁移列表**：
1. `AutoLog.java` - 自动日志注解
2. `PermissionData.java` - 权限数据注解
3. `Dict.java` - 字典注解
4. `EnableJeecgDict.java` - 启用字典注解

**迁移命令**：
```bash
python migrate-base-core-batch.py --classes AutoLog,PermissionData,Dict,EnableJeecgDict --target jeecg-boot-base-api --subpackage annotation
```

### 3.5 每批次迁移后的验证
```bash
# 编译验证
mvn clean compile

# 运行测试
mvn test

# 检查是否有新的编译错误
mvn clean compile 2>&1 | grep ERROR
```

---

## 🔧 阶段4：验证和清理 (预计1小时)

### 4.1 完整构建验证
```bash
# 完整构建
mvn clean install -DskipTests

# 运行所有测试
mvn clean test

# 对比测试结果
diff test-baseline-before-migration.txt test-baseline-after-migration.txt
```

### 4.2 依赖验证
```bash
# 检查是否还有对base-core的引用
mvn dependency:tree | grep "jeecg-boot-base-core:3.8.3"

# 如果输出为空，说明迁移成功
```

### 4.3 代码引用检查
```bash
# 搜索是否还有旧包名的引用
python check-old-package-references.py

# 输出应该为空或只包含注释中的引用
```

### 4.4 清理base-core模块
```bash
# 方案A：完全删除（推荐）
git rm -rf jeecg-boot-base-core/
# 从父POM中移除
# <module>jeecg-boot-base-core</module>

# 方案B：保留但标记为deprecated
# 在base-core的README.md中添加废弃说明
```

### 4.5 更新文档
- [ ] 更新项目README.md，说明模块结构变更
- [ ] 更新ARCHITECTURE.md，说明新的模块架构
- [ ] 创建MIGRATION_GUIDE.md，记录迁移过程
- [ ] 更新各模块的README.md

### 4.6 最终检查清单
- [ ] 所有类已迁移到目标模块
- [ ] 所有引用已更新
- [ ] 编译无错误
- [ ] 所有测试通过（或已知失败的测试数量不增加）
- [ ] 依赖树中无base-core:3.8.3引用
- [ ] 代码中无旧包名引用
- [ ] 文档已更新
- [ ] base-core模块已删除或标记为废弃

---

## 🛠️ 辅助工具脚本

### 1. migrate-base-core-backup.py
备份base-core模块和相关文件

### 2. analyze-base-core-references.py
分析base-core中类的引用关系

### 3. migrate-base-core-class.py
迁移单个类到目标模块

### 4. migrate-base-core-batch.py
批量迁移多个类

### 5. update-class-references.py
更新类引用的包名

### 6. check-old-package-references.py
检查是否还有旧包名的引用

### 7. verify-migration.py
验证迁移完整性

---

## 🎯 成功标准

- ✅ base-core模块已从父POM中移除
- ✅ 所有类已迁移到合适的目标模块
- ✅ `mvn clean install` 成功
- ✅ `mvn test` 通过率 ≥ 迁移前
- ✅ 无编译警告（关于缺失类）
- ✅ 代码中无旧包名引用
- ✅ 文档已更新

---

## ⚠️ 风险和应对

### 风险1：编译错误数量过多
**应对**：按优先级分批迁移，每批次不超过5个类

### 风险2：测试失败
**应对**：
1. 记录迁移前的测试基准
2. 每迁移一批类就运行测试
3. 如果测试失败，立即回滚该批次

### 风险3：依赖循环
**应对**：
1. 先迁移叶子节点类（无依赖）
2. 再迁移中间层类
3. 最后迁移根节点类（被广泛依赖）

### 风险4：运行时错误
**应对**：
1. 在测试环境先运行
2. 检查日志中的ClassNotFoundException
3. 使用类加载器调试工具

---

## 📊 进度跟踪

使用 `base-core-migration-progress.md` 跟踪进度：

```markdown
## 迁移进度

### 阶段1：准备工作
- [x] 创建迁移分支
- [x] 完整备份
- [x] 建立测试基准
- [x] 分析类引用关系

### 阶段2：VO类迁移 (8/8)
- [x] LoginUser
- [x] SysUserCacheInfo
- [x] UserAccountInfo
- [x] SysCategoryModel
- [x] DictModel
- [x] ComboModel
- [x] TreeModel
- [x] SysPermissionDataRuleModel

### 阶段3：工具类和注解迁移
- [ ] 基础类迁移 (0/4)
- [ ] 工具类迁移 (0/10)
- [ ] 常量类迁移 (0/3)
- [ ] 注解类迁移 (0/4)

### 阶段4：验证和清理
- [ ] 完整构建验证
- [ ] 依赖验证
- [ ] 代码引用检查
- [ ] 清理base-core模块
- [ ] 更新文档
```

---

## 📝 提交规范

每完成一个类的迁移，提交一个commit：

```bash
git add .
git commit -m "refactor(base-core): migrate LoginUser to jeecg-boot-base-api

- 