# DynamicDataSourceModel 引用更新报告

## 更新信息

- **类名**: `DynamicDataSourceModel`
- **旧包名**: `org.jeecg.common.system.vo`
- **新包名**: `org.jeecg.common.api.vo`
- **更新文件数**: 12
- **总替换次数**: 12

## 更新文件列表

| 文件 | 替换次数 |
|------|----------|
| `.\base-core-migration-backup-20251109_123935\jeecg-boot-base-core\src\main\java\org\jeecg\common\util\dynamic\db\DataSourceCachePool.java` | 1 |
| `.\base-core-migration-backup-20251109_123935\jeecg-boot-base-core\src\main\java\org\jeecg\common\util\dynamic\db\DynamicDBUtil.java` | 1 |
| `.\base-core-migration-backup-20251109_124006\jeecg-boot-base-core\src\main\java\org\jeecg\common\util\dynamic\db\DataSourceCachePool.java` | 1 |
| `.\base-core-migration-backup-20251109_124006\jeecg-boot-base-core\src\main\java\org\jeecg\common\util\dynamic\db\DynamicDBUtil.java` | 1 |
| `.\jeecg-boot-base-core\src\main\java\org\jeecg\common\util\dynamic\db\DataSourceCachePool.java` | 1 |
| `.\jeecg-boot-base-core\src\main\java\org\jeecg\common\util\dynamic\db\DynamicDBUtil.java` | 1 |
| `.\jeecg-boot-starter-datasource\backup-phase17.2\dynamic\db\DataSourceCachePool.java` | 1 |
| `.\jeecg-boot-starter-datasource\backup-phase17.2\dynamic\db\DynamicDBUtil.java` | 1 |
| `.\jeecg-boot-starter-datasource\backup-phase17.2\util\dynamic\db\DataSourceCachePool.java` | 1 |
| `.\jeecg-boot-starter-datasource\dynamic\db\DataSourceCachePool.java` | 1 |
| `.\jeecg-boot-starter-datasource\dynamic\db\DynamicDBUtil.java` | 1 |
| `.\jeecg-boot-starter-datasource\util\dynamic\db\DataSourceCachePool.java` | 1 |

## 验证步骤

```bash
# 1. 编译检查
mvn clean compile

# 2. 运行测试
mvn test

# 3. 搜索是否还有旧包名引用
grep -r "org.jeecg.common.system.vo.DynamicDataSourceModel" --include="*.java" .
```
