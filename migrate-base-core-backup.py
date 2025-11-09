#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Base-Core模块迁移备份工具

功能：
1. 备份base-core模块的所有源文件
2. 备份父POM和相关模块的POM
3. 记录当前Git状态
4. 创建备份时间戳
"""

import os
import shutil
import datetime
import subprocess
import json

def run_command(cmd):
    """执行命令并返回输出"""
    try:
        result = subprocess.run(cmd, shell=True, capture_output=True, text=True, encoding='utf-8', errors='ignore')
        return result.returncode == 0, result.stdout, result.stderr
    except Exception as e:
        return False, "", str(e)

def backup_base_core():
    """备份base-core模块"""
    timestamp = datetime.datetime.now().strftime("%Y%m%d_%H%M%S")
    backup_dir = f"base-core-migration-backup-{timestamp}"
    
    print(f"🔧 创建备份目录: {backup_dir}")
    os.makedirs(backup_dir, exist_ok=True)
    
    backup_info = {
        "timestamp": timestamp,
        "backup_dir": backup_dir,
        "files_backed_up": [],
        "git_status": {},
        "errors": []
    }
    
    # 1. 备份base-core模块
    base_core_src = "jeecg-boot-base-core"
    if os.path.exists(base_core_src):
        print(f"📦 备份base-core模块...")
        base_core_dst = os.path.join(backup_dir, "jeecg-boot-base-core")
        try:
            shutil.copytree(base_core_src, base_core_dst)
            backup_info["files_backed_up"].append("jeecg-boot-base-core")
            print(f"  ✅ 已备份: jeecg-boot-base-core")
        except Exception as e:
            error_msg = f"备份base-core失败: {e}"
            backup_info["errors"].append(error_msg)
            print(f"  ❌ {error_msg}")
    else:
        print(f"  ⚠️  base-core模块不存在: {base_core_src}")
    
    # 2. 备份关键POM文件
    pom_files = [
        "pom.xml",
        "jeecg-boot-base-api/pom.xml",
        "jeecg-boot-base-constants/pom.xml",
        "jeecg-boot-base-utils/pom.xml",
        "jeecg-boot-base-core-lite/pom.xml",
        "jeecg-boot-base-core-aggregator/pom.xml"
    ]
    
    print(f"📦 备份POM文件...")
    pom_backup_dir = os.path.join(backup_dir, "pom-files")
    os.makedirs(pom_backup_dir, exist_ok=True)
    
    for pom_file in pom_files:
        if os.path.exists(pom_file):
            dst = os.path.join(pom_backup_dir, pom_file.replace("/", "_"))
            try:
                shutil.copy2(pom_file, dst)
                backup_info["files_backed_up"].append(pom_file)
                print(f"  ✅ 已备份: {pom_file}")
            except Exception as e:
                error_msg = f"备份{pom_file}失败: {e}"
                backup_info["errors"].append(error_msg)
                print(f"  ❌ {error_msg}")
    
    # 3. 记录Git状态
    print(f"📝 记录Git状态...")
    
    # 当前分支
    success, branch, err = run_command("git branch --show-current")
    if success:
        backup_info["git_status"]["branch"] = branch.strip()
        print(f"  当前分支: {branch.strip()}")
    
    # 最后一次提交
    success, commit, err = run_command("git log -1 --oneline")
    if success and commit:
        backup_info["git_status"]["last_commit"] = commit.strip()
        print(f"  最后提交: {commit.strip()}")
    else:
        backup_info["git_status"]["last_commit"] = "无法获取"
        print(f"  最后提交: 无法获取")
    
    # Git状态
    success, status, err = run_command("git status --short")
    if success and status:
        backup_info["git_status"]["status"] = status
        status_file = os.path.join(backup_dir, "git-status.txt")
        with open(status_file, 'w', encoding='utf-8') as f:
            f.write(status)
        print(f"  Git状态已保存到: {status_file}")
    else:
        backup_info["git_status"]["status"] = "无法获取"
        print(f"  Git状态: 无法获取")
    
    # 4. 保存备份信息
    info_file = os.path.join(backup_dir, "backup-info.json")
    with open(info_file, 'w', encoding='utf-8') as f:
        json.dump(backup_info, f, indent=2, ensure_ascii=False)
    print(f"📄 备份信息已保存到: {info_file}")
    
    # 5. 创建恢复脚本
    restore_script = os.path.join(backup_dir, "restore.sh")
    with open(restore_script, 'w', encoding='utf-8') as f:
        f.write(f"""#!/bin/bash
# Base-Core模块备份恢复脚本
# 创建时间: {timestamp}

echo "🔄 开始恢复base-core模块备份..."

# 恢复base-core模块
if [ -d "jeecg-boot-base-core" ]; then
    echo "⚠️  base-core模块已存在，将被覆盖"
    rm -rf jeecg-boot-base-core
fi
cp -r {backup_dir}/jeecg-boot-base-core ./
echo "✅ base-core模块已恢复"

# 恢复POM文件
echo "🔄 恢复POM文件..."
""")
        for pom_file in pom_files:
            if pom_file in backup_info["files_backed_up"]:
                dst = pom_file.replace("/", "_")
                f.write(f'cp {pom_backup_dir}/{dst} {pom_file}\n')
                f.write(f'echo "✅ 已恢复: {pom_file}"\n')
        
        f.write(f"""
echo "✅ 恢复完成！"
echo "请运行以下命令验证:"
echo "  mvn clean compile"
""")
    
    os.chmod(restore_script, 0o755)
    print(f"📝 恢复脚本已创建: {restore_script}")
    
    # 6. 创建README
    readme_file = os.path.join(backup_dir, "README.md")
    with open(readme_file, 'w', encoding='utf-8') as f:
        f.write(f"""# Base-Core模块迁移备份

## 备份信息

- **备份时间**: {timestamp}
- **备份目录**: {backup_dir}
- **Git分支**: {backup_info['git_status'].get('branch', 'unknown')}
- **最后提交**: {backup_info['git_status'].get('last_commit', 'unknown')}

## 备份内容

### 1. 模块备份
- jeecg-boot-base-core (完整目录)

### 2. POM文件备份
""")
        for pom_file in backup_info["files_backed_up"]:
            if pom_file.endswith(".xml"):
                f.write(f"- {pom_file}\n")
        
        f.write(f"""
### 3. Git状态
详见 `git-status.txt`

## 如何恢复

### 方法1：使用恢复脚本
```bash
bash {restore_script}
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
""")
    
    print(f"\n✅ 备份完成！")
    print(f"📁 备份目录: {backup_dir}")
    print(f"📄 备份文件数: {len(backup_info['files_backed_up'])}")
    
    if backup_info["errors"]:
        print(f"\n⚠️  发现 {len(backup_info['errors'])} 个错误:")
        for error in backup_info["errors"]:
            print(f"  - {error}")
    
    print(f"\n💡 提示:")
    print(f"  - 查看备份信息: cat {info_file}")
    print(f"  - 恢复备份: bash {restore_script}")
    print(f"  - 或使用Git: git checkout HEAD -- jeecg-boot-base-core/ pom.xml")
    
    return backup_dir

if __name__ == "__main__":
    print("=" * 60)
    print("Base-Core模块迁移备份工具")
    print("=" * 60)
    print()
    
    backup_dir = backup_base_core()
    
    print()
    print("=" * 60)
    print("🎉 备份任务完成！")
    print("=" * 60)