#!/bin/bash
# 恢复 jeecg-boot-base-core 模块
# 原因：模块包含大量在新架构中缺失的核心类，无法安全移除

echo "========================================"
echo "恢复 jeecg-boot-base-core 模块"
echo "========================================"

# 1. 恢复父POM中的模块声明和dependencyManagement
echo ""
echo "步骤 1/3: 恢复父POM文件..."
git checkout HEAD -- pom.xml
if [ $? -eq 0 ]; then
    echo "✓ 父POM已恢复"
else
    echo "✗ 恢复失败，请手动检查"
fi

# 2. 恢复依赖引用
echo ""
echo "步骤 2/3: 恢复依赖引用..."
git checkout HEAD -- jeecg-module-system/jeecg-system-api/jeecg-system-local-api/pom.xml
git checkout HEAD -- jeecg-boot-module/jeecg-module-demo/pom.xml

if [ $? -eq 0 ]; then
    echo "✓ 依赖引用已恢复"
else
    echo "✗ 恢复失败，请手动检查"
fi

# 3. 恢复模块目录（从Git历史）
echo ""
echo "步骤 3/3: 恢复模块目录..."
git checkout HEAD -- jeecg-boot-base-core/

if [ $? -eq 0 ]; then
    echo "✓ 模块目录已恢复"
else
    echo "✗ 恢复失败，请手动检查"
fi

echo ""
echo "========================================"
echo "恢复完成！"
echo "========================================"
echo ""
echo "📋 后续操作："
echo "   1. 验证文件是否恢复：ls -la jeecg-boot-base-core/"
echo "   2. 运行构建测试：mvn clean install -DskipTests"
echo ""
echo "📖 详细分析请查看："
echo "   jeecg-boot-base-core-移除失败分析报告.md"