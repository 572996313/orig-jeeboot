@echo off
echo ======================================================================
echo Phase 21: 重新编译恢复文件的模块
echo ======================================================================

set MODULES=jeecg-boot-starter-oss jeecg-boot-starter-api-doc jeecg-boot-starter-communication jeecg-boot-starter-elasticsearch jeecg-boot-starter-web

for %%M in (%MODULES%) do (
    echo.
    echo ======================================================================
    echo 正在编译: %%M
    echo ======================================================================
    cd %%M
    call mvn clean install -DskipTests
    if errorlevel 1 (
        echo [ERROR] %%M 编译失败！
        cd ..
        exit /b 1
    )
    echo [SUCCESS] %%M 编译成功！
    cd ..
)

echo.
echo ======================================================================
echo ✅ 所有模块编译完成！
echo ======================================================================