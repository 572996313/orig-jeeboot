@echo off
echo ============================================================
echo VO类批量迁移脚本
echo ============================================================
echo.

REM 迁移第2个类
echo [2/11] 迁移 QueryRuleVo...
python migrate-base-core-class.py --class QueryRuleVo --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class QueryRuleVo --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第3个类
echo [3/11] 迁移 BaiduApi...
python migrate-base-core-class.py --class BaiduApi --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class BaiduApi --old-package org.jeecg.config.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第4个类
echo [4/11] 迁移 WeiXinPay...
python migrate-base-core-class.py --class WeiXinPay --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class WeiXinPay --old-package org.jeecg.config.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第5个类
echo [5/11] 迁移 SysFilesModel...
python migrate-base-core-class.py --class SysFilesModel --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class SysFilesModel --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第6个类
echo [6/11] 迁移 DictModelMany...
python migrate-base-core-class.py --class DictModelMany --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class DictModelMany --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第7个类
echo [7/11] 迁移 DomainUrl...
python migrate-base-core-class.py --class DomainUrl --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class DomainUrl --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第8个类
echo [8/11] 迁移 ComboModel...
python migrate-base-core-class.py --class ComboModel --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class ComboModel --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第9个类
echo [9/11] 迁移 SysCategoryModel...
python migrate-base-core-class.py --class SysCategoryModel --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class SysCategoryModel --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第10个类
echo [10/11] 迁移 UserAccountInfo...
python migrate-base-core-class.py --class UserAccountInfo --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class UserAccountInfo --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
echo.

REM 迁移第11个类
echo [11/11] 迁移 SelectSqlInfo...
python migrate-base-core-class.py --class SelectSqlInfo --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 goto error
python update-class-references.py --class SelectSqlInfo --old-package org.jeecg.common.system.vo --new-package org.jeecg.common.api.vo
echo.

echo ============================================================
echo 编译验证...
echo ============================================================
mvn clean compile -pl jeecg-boot-base-api -am
if errorlevel 1 goto error

echo.
echo ============================================================
echo ✅ 批量迁移完成！
echo ============================================================
goto end

:error
echo.
echo ============================================================
echo ❌ 迁移失败！
echo ============================================================
exit /b 1

:end