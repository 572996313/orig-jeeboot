@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

echo ============================================================
echo VO类批量迁移脚本 - 第3批（高引用次数）
echo ============================================================
echo.

set "TOTAL=2"
set "CURRENT=0"

REM 定义类列表
set "CLASS_1=LoginUser"
set "CLASS_2=ComboModel"

REM 迁移 LoginUser
set /a CURRENT+=1
echo [!CURRENT!/%TOTAL%] 迁移 !CLASS_1!...
python migrate-base-core-class.py --class "!CLASS_1!" --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 (
    echo ❌ 迁移 !CLASS_1! 失败！
    pause
    exit /b 1
)
echo.

echo 更新 !CLASS_1! 的引用...
python update-class-references.py --class "!CLASS_1!" --old-package "org.jeecg.common.system.vo" --new-package "org.jeecg.common.api.vo"
if errorlevel 1 (
    echo ❌ 更新 !CLASS_1! 引用失败！
    pause
    exit /b 1
)
echo.

REM 迁移 ComboModel
set /a CURRENT+=1
echo [!CURRENT!/%TOTAL%] 迁移 !CLASS_2!...
python migrate-base-core-class.py --class "!CLASS_2!" --target jeecg-boot-base-api --subpackage vo
if errorlevel 1 (
    echo ❌ 迁移 !CLASS_2! 失败！
    pause
    exit /b 1
)
echo.

echo 更新 !CLASS_2! 的引用...
python update-class-references.py --class "!CLASS_2!" --old-package "org.jeecg.common.system.vo" --new-package "org.jeecg.common.api.vo"
if errorlevel 1 (
    echo ❌ 更新 !CLASS_2! 引用失败！
    pause
    exit /b 1
)
echo.

echo ============================================================
echo 编译验证...
echo ============================================================
call mvn clean compile -pl jeecg-boot-base-api -am
if errorlevel 1 (
    echo.
    echo ❌ 编译失败！请检查错误信息。
    echo.
    echo 可能的原因：
    echo 1. 缺少依赖类
    echo 2. 包名映射错误
    echo 3. import语句未正确更新
    echo.
    echo 建议：
    echo 1. 检查编译错误信息
    echo 2. 查看 base-core-migration-log.md 日志
    echo 3. 手动修复问题后重新编译
    echo.
    pause
    exit /b 1
)

echo.
echo ============================================================
echo ✅ 第3批迁移全部完成！
echo ============================================================
echo.
echo 迁移统计：
echo - 总类数：%TOTAL%
echo - 成功：%TOTAL%
echo - 失败：0
echo.
echo VO类迁移进度：23/24 (95.8%%)
echo.
echo 下一步：
echo 1. 查看完成报告：vo-migration-phase3-summary.md
echo 2. 更新进度文档：base-core-migration-progress.md
echo 3. 准备工具类迁移
echo.
pause