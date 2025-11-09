@echo off
echo ============================================================
echo VO类批量迁移脚本 - 第2批（中等引用次数）
echo ============================================================
echo.

REM 迁移第1个类
echo [1/8] 迁移 DictQuery...
python migrate-base-core-class.py --class DictQuery --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class DictQuery --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第2个类
echo [2/8] 迁移 SysPermissionDataRuleModel...
python migrate-base-core-class.py --class SysPermissionDataRuleModel --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class SysPermissionDataRuleModel --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第3个类
echo [3/8] 迁移 SysDepartModel...
python migrate-base-core-class.py --class SysDepartModel --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class SysDepartModel --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第4个类
echo [4/8] 迁移 Elasticsearch...
python migrate-base-core-class.py --class Elasticsearch --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class Elasticsearch --old-package org.jeecg.config.Elasticsearch --new-package org.jeecg.common.api.vo
echo.

REM 迁移第5个类
echo [5/8] 迁移 SysUserCacheInfo...
python migrate-base-core-class.py --class SysUserCacheInfo --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class SysUserCacheInfo --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第6个类
echo [6/8] 迁移 Shiro...
python migrate-base-core-class.py --class Shiro --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class Shiro --old-package org.jeecg.config.Shiro --new-package org.jeecg.common.api.vo
echo.

REM 迁移第7个类
echo [7/8] 迁移 DynamicDataSourceModel...
python migrate-base-core-class.py --class DynamicDataSourceModel --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class DynamicDataSourceModel --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第8个类
echo [8/8] 迁移 Firewall...
python migrate-base-core-class.py --class Firewall --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class Firewall --old-package org.jeecg.config.Firewall --new-package org.jeecg.common.api.vo
echo.

echo ============================================================
echo 编译验证...
echo ============================================================
mvn clean compile -pl jeecg-boot-base-api -am
if errorlevel 1 goto error

echo.
echo ============================================================
echo ✅ 第2批迁移完成！
echo ============================================================
echo 已迁移: 8个VO类
echo 总进度: 21/24 (87.5%%)
goto end

:error
echo.
echo ============================================================
echo ❌ 迁移失败！
echo ============================================================
exit /b 1

:end