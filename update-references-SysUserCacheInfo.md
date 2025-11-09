# SysUserCacheInfo 引用更新报告

## 更新信息

- **类名**: `SysUserCacheInfo`
- **旧包名**: `org.jeecg.common.system.vo`
- **新包名**: `org.jeecg.common.api.vo`
- **更新文件数**: 15
- **总替换次数**: 15

## 更新文件列表

| 文件 | 替换次数 |
|------|----------|
| `.\base-core-migration-backup-20251109_123935\jeecg-boot-base-core\src\main\java\org\jeecg\common\aspect\PermissionDataAspect.java` | 1 |
| `.\base-core-migration-backup-20251109_123935\jeecg-boot-base-core\src\main\java\org\jeecg\common\system\util\JeecgDataAutorUtils.java` | 1 |
| `.\base-core-migration-backup-20251109_123935\jeecg-boot-base-core\src\main\java\org\jeecg\common\system\util\JwtUtil.java` | 1 |
| `.\base-core-migration-backup-20251109_124006\jeecg-boot-base-core\src\main\java\org\jeecg\common\aspect\PermissionDataAspect.java` | 1 |
| `.\base-core-migration-backup-20251109_124006\jeecg-boot-base-core\src\main\java\org\jeecg\common\system\util\JeecgDataAutorUtils.java` | 1 |
| `.\base-core-migration-backup-20251109_124006\jeecg-boot-base-core\src\main\java\org\jeecg\common\system\util\JwtUtil.java` | 1 |
| `.\jeecg-boot-base-core\src\main\java\org\jeecg\common\aspect\PermissionDataAspect.java` | 1 |
| `.\jeecg-boot-base-core\src\main\java\org\jeecg\common\system\util\JeecgDataAutorUtils.java` | 1 |
| `.\jeecg-boot-base-core\src\main\java\org\jeecg\common\system\util\JwtUtil.java` | 1 |
| `.\jeecg-boot-base-core-lite\src\main\java\org\jeecg\common\system\util\JeecgDataAutorUtils.java` | 1 |
| `.\jeecg-boot-base-utils\src\main\java-backup\JeecgDataAutorUtils.java` | 1 |
| `.\jeecg-boot-base-utils\src\main\java-backup\JwtUtil.java` | 1 |
| `.\jeecg-module-system\jeecg-system-biz\src\main\java\org\jeecg\config\jimureport\JimuReportTokenService.java` | 1 |
| `.\jeecg-module-system\jeecg-system-biz\src\main\java\org\jeecg\modules\system\service\ISysUserService.java` | 1 |
| `.\jeecg-module-system\jeecg-system-biz\src\main\java\org\jeecg\modules\system\service\impl\SysUserServiceImpl.java` | 1 |

## 验证步骤

```bash
# 1. 编译检查
mvn clean compile

# 2. 运行测试
mvn test

# 3. 搜索是否还有旧包名引用
grep -r "org.jeecg.common.system.vo.SysUserCacheInfo" --include="*.java" .
```
