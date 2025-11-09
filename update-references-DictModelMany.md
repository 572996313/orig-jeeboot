# DictModelMany 引用更新报告

## 更新信息

- **类名**: `DictModelMany`
- **旧包名**: `org.jeecg.common.system.vo`
- **新包名**: `org.jeecg.common.api.vo`
- **更新文件数**: 2
- **总替换次数**: 2

## 更新文件列表

| 文件 | 替换次数 |
|------|----------|
| `.\jeecg-module-system\jeecg-system-biz\src\main\java\org\jeecg\modules\system\mapper\SysDictMapper.java` | 1 |
| `.\jeecg-module-system\jeecg-system-biz\src\main\java\org\jeecg\modules\system\service\impl\SysDictServiceImpl.java` | 1 |

## 验证步骤

```bash
# 1. 编译检查
mvn clean compile

# 2. 运行测试
mvn test

# 3. 搜索是否还有旧包名引用
grep -r "org.jeecg.common.system.vo.DictModelMany" --include="*.java" .
```
