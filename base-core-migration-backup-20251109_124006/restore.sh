#!/bin/bash
# Base-Core模块备份恢复脚本
# 创建时间: 20251109_124006

echo "🔄 开始恢复base-core模块备份..."

# 恢复base-core模块
if [ -d "jeecg-boot-base-core" ]; then
    echo "⚠️  base-core模块已存在，将被覆盖"
    rm -rf jeecg-boot-base-core
fi
cp -r base-core-migration-backup-20251109_124006/jeecg-boot-base-core ./
echo "✅ base-core模块已恢复"

# 恢复POM文件
echo "🔄 恢复POM文件..."
cp base-core-migration-backup-20251109_124006\pom-files/pom.xml pom.xml
echo "✅ 已恢复: pom.xml"
cp base-core-migration-backup-20251109_124006\pom-files/jeecg-boot-base-api_pom.xml jeecg-boot-base-api/pom.xml
echo "✅ 已恢复: jeecg-boot-base-api/pom.xml"
cp base-core-migration-backup-20251109_124006\pom-files/jeecg-boot-base-constants_pom.xml jeecg-boot-base-constants/pom.xml
echo "✅ 已恢复: jeecg-boot-base-constants/pom.xml"
cp base-core-migration-backup-20251109_124006\pom-files/jeecg-boot-base-utils_pom.xml jeecg-boot-base-utils/pom.xml
echo "✅ 已恢复: jeecg-boot-base-utils/pom.xml"
cp base-core-migration-backup-20251109_124006\pom-files/jeecg-boot-base-core-lite_pom.xml jeecg-boot-base-core-lite/pom.xml
echo "✅ 已恢复: jeecg-boot-base-core-lite/pom.xml"
cp base-core-migration-backup-20251109_124006\pom-files/jeecg-boot-base-core-aggregator_pom.xml jeecg-boot-base-core-aggregator/pom.xml
echo "✅ 已恢复: jeecg-boot-base-core-aggregator/pom.xml"

echo "✅ 恢复完成！"
echo "请运行以下命令验证:"
echo "  mvn clean compile"
