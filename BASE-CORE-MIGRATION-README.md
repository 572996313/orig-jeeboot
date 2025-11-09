
# Base-Core模块迁移指南

## 📚 概述

本指南和工具集用于将 `jeecg-boot-base-core` (v3.8.3) 模块中的类迁移到新的模块化架构中。

### 当前状态
- ✅ base-core包含202个源文件
- ✅ 识别出30+个关键类需要迁移
- ✅ 其他模块有大量引用需要更新
- ⏱️ 预计时间：5-7小时

---

## 🛠️ 工具清单

### 1. 迁移计划文档
- **`base-core-migration-plan.md`** - 详细的4阶段迁移计划

### 2. Python工具脚本

| 脚本 | 功能 | 使用示例 |
|------|------|----------|
| `migrate-base-core-backup.py` | 备份base-core模块 | `python3 migrate-base-core-backup.py` |
| `analyze-base-core-references.py` | 分析类引用关系 | `python3 analyze-base-core-references.py` |
| `migrate-base-core-class.py` | 迁移单个类 | `python3 migrate-base-core-class.py --class LoginUser --target jeecg-boot-base-api` |
| `update-class-references.py` | 更新类引用 | `python3 update-class-references.py --class LoginUser --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo` |

### 3. Shell启动脚本
- **`base-core-migration-start.sh`** - 交互式菜单工具（推荐使用）

---

## 🚀 快速开始

### 方式1：使用交互式菜单（推荐）

```bash
# 添加执行权限
chmod +x base-core-migration-start.sh

# 启动工具
./base-core-migration-start.sh
```

菜单界面提供：
- ✅ 一键备份
- ✅ 自动分析
- ✅ 交互式迁移
- ✅ 自动编译验证
- ✅ 查看报告和日志

### 方式2：手动执行

#### 阶段1：准备工作

```bash
# 1. 创建迁移分支
git checkout -b feature/migrate-base-core-phase1

# 2. 备份base-core模块
python3 migrate-base-core-backup.py

# 3. 分析类引用关系
python3 analyze-base-core-references.py
# 输出：base-core-class-references.json
# 输出：base-core-class-references-report.md

# 4. 建立测试基准
mvn clean test > test-baseline-before-migration.txt 2>&1
mvn clean compile > compile-baseline-before-migration.txt 2>&1
mvn dependency:tree > dependency-tree-before-migration.txt
```

#### 阶段2-3：执行迁移

**示例：迁移LoginUser类**

```bash
# 1. 试运行（查看效果，不实际修改）
python3 migrate-base-core-class.py \
  --class LoginUser \
  --target jeecg-boot-base-api \
  --subpackage vo \
  --dry-run

# 2. 正式迁移
python3 migrate-base-core-class.py \
  --class LoginUser \
  --target jeecg-boot-base-api \
  --subpackage vo

# 3. 更新所有引用（试运行）
python3 update-class-references.py \
  --class LoginUser \
  --old-package org.jeecg.common.system.vo \
  --new-package org.jeecg.common.api.vo \
  --dry-run

# 4. 正式更新引用
python3 update-class-references.py \
  --class LoginUser \
  --old-package org.jeecg.common.system.vo \
  --new-package org.jeecg.common.api.vo

# 5. 编译验证
mvn clean compile -pl jeecg-boot-base-api -am

# 6. 运行测试
mvn test -Dtest=*LoginUser*

# 7. 提交代码
git add .
git commit -m "refactor(base-core): migrate LoginUser to jeecg-boot-base-api

- Move LoginUser from base-core to base-api
- Update package: org.jeecg.common.system.vo -> org.jeecg.common.api.vo
- Update all references in project
"
```

---

## 📦 目标模块映射

根据类的职责，选择合适的目标模块：

| 类型 | 目标模块 | 子包建议 |
|------|---------|----------|
| VO类（LoginUser等） | `jeecg-boot-base-api` | `vo` |
| 基础Controller/Entity | `jeecg-boot-base-core-lite` | `base` |
| 工具类（RedisUtil等） | `jeecg-boot-base-utils` | `util` |
| 常量类 | `jeecg-boot-base-constants` | - |
| 注解类（AutoLog等） | `jeecg-boot-base-api` | `annotation` |

---

## 📋 迁移优先级（建议顺序）

### P0 - 高优先级（叶子节点，引用少）
先迁移这些类，影响范围小：
1. 辅助VO类
2. 枚举类
3. 独立工具类

### P1 - 中优先级
4. 业务VO类（如SysCategoryModel）
5. 独立注解类

### P2 - 低优先级（核心类，引用多）
最后迁移这些关键类：
6. LoginUser - 用户登录信息
7. JeecgController - 基础控制器
8. JeecgEntity - 基础实体类
9. RedisUtil - Redis工具类

**推荐策略**：查看 `base-core-class-references-report.md` 中的建议迁移顺序

---

## 🔍 工具详解

### 1. migrate-base-core-backup.py

**功能**：
- 备份base-core完整目录
- 备份关键POM文件
- 记录Git状态
- 生成恢复脚本

**输出**：
- `base-core-migration-backup-YYYYMMDD_HHMMSS/` - 备份目录
- `backup-info.json` - 备份元数据
- `restore.sh` - 一键恢复脚本

**恢复方法**：
```bash
# 方法1：使用恢复脚本
bash base-core-migration-backup-*/restore.sh

# 方法2：使用Git
git checkout HEAD -- jeecg-boot-base-core/ pom.xml
```

---

### 2. analyze-base-core-references.py

**功能**：
- 扫描base-core中的所有类
- 分析每个类的引用情况
- 计算迁移优先级
- 生成详细报告

**输出**：
- `base-core-class-references.json` - JSON格式详细数据
- `base-core-class-references-report.md` - Markdown格式报告

**报告内容**：
- 类别统计
- 建议迁移顺序（按优先级排序）
- 按类别分组
- 高优先级类详情

---

### 3. migrate-base-core-class.py

**功能**：
- 从base-core复制类到目标模块
- 自动更新包名
- 创建必要的目录结构
- 记录迁移日志

**参数**：
- `--class` - 类名（必需）
- `--target` - 目标模块（必需）
- `--subpackage` - 子包名（可选）
- `--dry-run` - 试运行模式

**示例**：
```bash
# VO类迁移到base-api
python3 migrate-base-core-class.py \
  --class SysCategoryModel \
  --target jeecg-boot-base-api \
  --subpackage vo

# 工具类迁移到base-utils
python3 migrate-base-core-class.py \
  --class RedisUtil \
  --target jeecg-boot-base-utils

# 常量类迁移到base-constants
python3 migrate-base-core-class.py \
  --class CacheConstant \
  --target jeecg-boot-base-constants
```

**输出**：
- 迁移后的Java文件
- `base-core-migration-log.md` - 迁移记录表格

---

### 4. update-class-references.py

**功能**：
- 扫描所有Java文件
- 自动更新import语句
- 替换旧包名为新包名
- 生成更新报告

**参数**：
- `--class` - 类名（必需）
- `--old-package` - 旧包名（必需）
- `--new-package` - 新包名（必需）
- `--dry-run` - 试运行模式

**示例**：
```bash
python3 update-class-references.py \
  --class LoginUser \
  --old-package org.jeecg.common.system.vo \
  --new-package org.jeecg.common.api.vo \
  --dry-run  # 先试运行查看效果
```

**输出**：
- 更新后的Java文件
- `update-references-ClassName.md` - 更新报告

---

## ✅ 验证检查清单

### 每迁移一个类后：
- [ ] 类文件已成功复制到目标模块
- [ ] 包名已正确更新
- [ ] 所有import引用已更新
- [ ] 目标模块编译通过：`mvn clean compile -pl <target-module> -am`
- [ ] 完整项目编译通过：`mvn clean compile`
- [ ] 相关测试通过：`mvn test -Dtest=*ClassName*`
- [ ] 没有旧包名残留：`grep -r "old.package.ClassName" --include="*.java" .`
- [ ] Git提交：`git commit -m "refactor: migrate ClassName"`

### 迁移完成后：
- [ ] 所有类已迁移
- [ ] 完整构建成功：`mvn clean install`
- [ ] 所有测试通过：`mvn test`
- [ ] 依赖树中无base-core:3.8.3：`mvn dependency:tree | grep base-core:3.8.3`
- [ ] 可以删除base-core模块
- [ ] 文档已更新

---

## 🎯 最佳实践

### 1. 小步快跑
- 每次只迁移1-3个相关的类
- 立即验证编译和测试
- 及时提交代码

### 2. 先试后行
- 使用`--dry-run`参数先查看效果
- 确认无误后再正式执行

### 3. 持续验证
- 每次迁移后立即编译
- 运行相关测试
- 检查是否有新的错误

### 4. 详细记录
- 工具会自动记录日志
- 提交信息要清晰
- 遇到问题记录在文档中

### 5. 分批迁移
- 按类别分批（先VO，再工具，最后基础类）
- 按优先级分批（先高优先级，再低优先级）
- 每批完成后合并到主分支

---

## ⚠️ 常见问题

### Q1: 编译错误 - 找不到类
**原因**：引用未更新或包名错误

**解决**：
```bash
# 搜索旧包名
grep -r "old.package.ClassName" --include="*.java" .

# 重新运行更新工具
python3 update-class-references.py --class ClassName --old-package old.pkg --new-package new.pkg
```

### Q2: 测试失败
**原因**：类行为变化或依赖问题

**解决**：
1. 对比迁移前后的测试结果
2. 检查类的依赖是否完整
3. 查看测试日志定位问题

### Q3: 循环依赖
**原因**：类之间相互依赖

**解决**：
1. 先迁移叶子节点类（无依赖）
2. 使用接口解耦
3. 重构代码消除循环依赖

### Q4: 想回滚怎么办？
**方法**：
```bash
# 方法1：使用备份恢复脚本
bash base-core-migration-backup-*/restore.sh

# 方法2：Git回滚
git checkout <commit-before-migration>

# 方法3：Git重置分支
git reset --hard origin/main
```

---

## 📊 进度跟踪

创建 `base-core-migration-progress.md` 跟踪进度：

```markdown
## 迁移进度

- 总类数：202
- 已迁移：0
- 进行中：0
- 待迁移：202

### 本周计划
- [ ] 迁移10个VO类
- [ ] 迁移5个工具类

### 已完成
- [x] 2024-01-15: LoginUser
- [x] 2024-01-15: SysUserCacheInfo
```

---

## 📞 获取帮助

### 查看文档
- **迁移计划**：`base-core-migration-plan.md`
- **类引用分析**：`base-core-class-references-report.md`
- **迁移日志**：`base-core-migration-log.md`

### 使用交互式工具
```bash
./base-core-migration-start.sh
# 选择 "12) 显示帮助信息"
```

### 命令行帮助
```bash
