# Base-Core模块迁移备份

## 备份信息

- **备份时间**: 20251109_124006
- **备份目录**: base-core-migration-backup-20251109_124006
- **Git分支**: master
- **最后提交**: 57420ca 删减版

## 备份内容

### 1. 模块备份
- jeecg-boot-base-core (完整目录)

### 2. POM文件备份
- pom.xml
- jeecg-boot-base-api/pom.xml
- jeecg-boot-base-constants/pom.xml
- jeecg-boot-base-utils/pom.xml
- jeecg-boot-base-core-lite/pom.xml
- jeecg-boot-base-core-aggregator/pom.xml

### 3. Git状态
详见 `git-status.txt`

## 如何恢复

### 方法1：使用恢复脚本
```bash
bash base-core-migration-backup-20251109_124006\restore.sh
```

### 方法2：使用Git
```bash
git checkout HEAD -- jeecg-boot-base-core/ pom.xml
```

### 方法3：手动恢复
1. 复制 `jeecg-boot-base-core` 目录到项目根目录
2. 恢复各个POM文件

## 验证恢复

```bash
mvn clean compile
```

## 备份详情

详见 `backup-info.json`
