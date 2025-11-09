@echo off
REM 自动化迁移脚本 - jeecg-boot-starter-security模块
REM 从jeecg-boot-base-core复制Shiro相关文件到新的security starter模块

echo ============================================================
echo 开始迁移 jeecg-boot-starter-security 模块
echo ============================================================

REM 设置源路径和目标路径
set SOURCE_BASE=jeecg-boot-base-core\src\main\java\org\jeecg
set TARGET_BASE=jeecg-boot-starter-security\src\main\java\org\jeecg

echo.
echo 创建目录结构...
mkdir "%TARGET_BASE%\config\shiro\filters" 2>nul
mkdir "%TARGET_BASE%\config\shiro\ignore" 2>nul
mkdir "%TARGET_BASE%\common\system\util" 2>nul
mkdir "%TARGET_BASE%\autoconfigure" 2>nul
mkdir "jeecg-boot-starter-security\src\main\resources\META-INF" 2>nul

echo.
echo 复制Shiro配置文件...
copy "%SOURCE_BASE%\config\shiro\ShiroConfig.java" "%TARGET_BASE%\config\shiro\" >nul 2>&1
if %errorlevel%==0 (echo    [OK] ShiroConfig.java) else (echo    [FAIL] ShiroConfig.java)

copy "%SOURCE_BASE%\config\shiro\ShiroRealm.java" "%TARGET_BASE%\config\shiro\" >nul 2>&1
if %errorlevel%==0 (echo    [OK] ShiroRealm.java) else (echo    [FAIL] ShiroRealm.java)

copy "%SOURCE_BASE%\config\shiro\IgnoreAuth.java" "%TARGET_BASE%\config\shiro\" >nul 2>&1
if %errorlevel%==0 (echo    [OK] IgnoreAuth.java) else (echo    [FAIL] IgnoreAuth.java)

copy "%SOURCE_BASE%\config\shiro\JwtToken.java" "%TARGET_BASE%\config\shiro\" >nul 2>&1
if %errorlevel%==0 (echo    [OK] JwtToken.java) else (echo    [FAIL] JwtToken.java)

echo.
echo 复制Shiro过滤器文件...
copy "%SOURCE_BASE%\config\shiro\filters\CustomShiroFilterFactoryBean.java" "%TARGET_BASE%\config\shiro\filters\" >nul 2>&1
if %errorlevel%==0 (echo    [OK] CustomShiroFilterFactoryBean.java) else (echo    [FAIL] CustomShiroFilterFactoryBean.java)

copy "%SOURCE_BASE%\config\shiro\filters\JwtFilter.java" "%TARGET_BASE%\config\shiro\filters\" >nul 2>&1
if %errorlevel%==0 (echo    [OK] JwtFilter.java) else (echo    [FAIL] JwtFilter.java)

copy "%SOURCE_BASE%\config\shiro\filters\ResourceCheckFilter.java" "%TARGET_BASE%\config\shiro\filters\" >nul 2>&1
if %errorlevel%==0 (echo    [OK] ResourceCheckFilter.java) else (echo    [FAIL] ResourceCheckFilter.java)

echo.
echo 复制Ignore认证处理器...
copy "%SOURCE_BASE%\config\shiro\ignore\IgnoreAuthPostProcessor.java" "%TARGET_BASE%\config\shiro\ignore\" >nul 2>&1
if %errorlevel%==0 (echo    [OK] IgnoreAuthPostProcessor.java) else (echo    [FAIL] IgnoreAuthPostProcessor.java)

copy "%SOURCE_BASE%\config\shiro\ignore\InMemoryIgnoreAuth.java" "%TARGET_BASE%\config\shiro\ignore\" >nul 2>&1
if %errorlevel%==0 (echo    [OK] InMemoryIgnoreAuth.java) else (echo    [FAIL] InMemoryIgnoreAuth.java)

echo.
echo 复制JWT工具类...
copy "%SOURCE_BASE%\common\system\util\JwtUtil.java" "%TARGET_BASE%\common\system\util\" >nul 2>&1
if %errorlevel%==0 (echo    [OK] JwtUtil.java) else (echo    [FAIL] JwtUtil.java)

echo.
echo ============================================================
echo 迁移完成!
echo ============================================================
echo.
echo 下一步:
echo    1. 运行创建配置类的脚本
echo    2. 编译模块: mvn clean compile -DskipTests
echo    3. 修复依赖问题
echo ============================================================