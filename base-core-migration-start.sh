#!/bin/bash
# Base-Core模块迁移快速启动脚本

echo "============================================================"
echo "Base-Core模块迁移工具集"
echo "============================================================"
echo ""

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# 显示菜单
show_menu() {
    echo -e "${BLUE}请选择操作：${NC}"
    echo ""
    echo "  ${GREEN}阶段1：准备工作${NC}"
    echo "    1) 备份base-core模块"
    echo "    2) 分析类引用关系"
    echo "    3) 建立测试基准"
    echo "    4) 创建迁移分支"
    echo ""
    echo "  ${GREEN}阶段2-3：执行迁移${NC}"
    echo "    5) 迁移单个类（交互式）"
    echo "    6) 更新类引用（交互式）"
    echo "    7) 编译验证"
    echo "    8) 运行测试"
    echo ""
    echo "  ${GREEN}查看与帮助${NC}"
    echo "    9) 查看迁移计划"
    echo "   10) 查看迁移日志"
    echo "   11) 查看类引用分析报告"
    echo "   12) 显示帮助信息"
    echo ""
    echo "    0) 退出"
    echo ""
    echo -n -e "${YELLOW}请输入选项 [0-12]: ${NC}"
}

# 1. 备份base-core模块
backup_base_core() {
    echo -e "\n${GREEN}=== 备份base-core模块 ===${NC}\n"
    python3 migrate-base-core-backup.py
    echo ""
    read -p "按Enter键继续..."
}

# 2. 分析类引用关系
analyze_references() {
    echo -e "\n${GREEN}=== 分析类引用关系 ===${NC}\n"
    python3 analyze-base-core-references.py
    echo ""
    echo -e "${BLUE}分析报告已生成：${NC}"
    echo "  - base-core-class-references.json"
    echo "  - base-core-class-references-report.md"
    echo ""
    read -p "按Enter键继续..."
}

# 3. 建立测试基准
establish_baseline() {
    echo -e "\n${GREEN}=== 建立测试基准 ===${NC}\n"
    
    echo "正在运行测试..."
    mvn clean test > test-baseline-before-migration.txt 2>&1
    echo -e "${GREEN}✅ 测试基准已保存: test-baseline-before-migration.txt${NC}"
    
    echo ""
    echo "正在记录编译状态..."
    mvn clean compile > compile-baseline-before-migration.txt 2>&1
    echo -e "${GREEN}✅ 编译基准已保存: compile-baseline-before-migration.txt${NC}"
    
    echo ""
    echo "正在记录依赖树..."
    mvn dependency:tree > dependency-tree-before-migration.txt 2>&1
    echo -e "${GREEN}✅ 依赖树已保存: dependency-tree-before-migration.txt${NC}"
    
    echo ""
    read -p "按Enter键继续..."
}

# 4. 创建迁移分支
create_branch() {
    echo -e "\n${GREEN}=== 创建迁移分支 ===${NC}\n"
    
    BRANCH_NAME="feature/migrate-base-core-$(date +%Y%m%d)"
    
    echo "当前分支:"
    git branch --show-current
    echo ""
    
    read -p "是否创建新分支 '$BRANCH_NAME'? (y/n): " confirm
    if [ "$confirm" = "y" ]; then
        git checkout -b "$BRANCH_NAME"
        echo -e "${GREEN}✅ 已创建并切换到分支: $BRANCH_NAME${NC}"
    else
        echo "已取消"
    fi
    
    echo ""
    read -p "按Enter键继续..."
}

# 5. 迁移单个类
migrate_class() {
    echo -e "\n${GREEN}=== 迁移单个类 ===${NC}\n"
    
    echo "可用的目标模块:"
    echo "  1) jeecg-boot-base-api"
    echo "  2) jeecg-boot-base-constants"
    echo "  3) jeecg-boot-base-utils"
    echo "  4) jeecg-boot-base-core-lite"
    echo ""
    
    read -p "请输入类名: " class_name
    read -p "请选择目标模块 [1-4]: " module_choice
    read -p "请输入子包名（可选，如：vo, annotation）: " subpackage
    
    case $module_choice in
        1) target_module="jeecg-boot-base-api" ;;
        2) target_module="jeecg-boot-base-constants" ;;
        3) target_module="jeecg-boot-base-utils" ;;
        4) target_module="jeecg-boot-base-core-lite" ;;
        *) echo -e "${RED}无效选项${NC}"; return ;;
    esac
    
    echo ""
    read -p "是否先试运行? (y/n): " dry_run
    
    if [ "$dry_run" = "y" ]; then
        python3 migrate-base-core-class.py --class "$class_name" --target "$target_module" ${subpackage:+--subpackage "$subpackage"} --dry-run
    else
        python3 migrate-base-core-class.py --class "$class_name" --target "$target_module" ${subpackage:+--subpackage "$subpackage"}
    fi
    
    echo ""
    read -p "按Enter键继续..."
}

# 6. 更新类引用
update_references() {
    echo -e "\n${GREEN}=== 更新类引用 ===${NC}\n"
    
    read -p "请输入类名: " class_name
    read -p "请输入旧包名: " old_package
    read -p "请输入新包名: " new_package
    
    echo ""
    read -p "是否先试运行? (y/n): " dry_run
    
    if [ "$dry_run" = "y" ]; then
        python3 update-class-references.py --class "$class_name" --old-package "$old_package" --new-package "$new_package" --dry-run
    else
        python3 update-class-references.py --class "$class_name" --old-package "$old_package" --new-package "$new_package"
    fi
    
    echo ""
    read -p "按Enter键继续..."
}

# 7. 编译验证
compile_verify() {
    echo -e "\n${GREEN}=== 编译验证 ===${NC}\n"
    
    echo "正在编译..."
    mvn clean compile
    
    if [ $? -eq 0 ]; then
        echo -e "\n${GREEN}✅ 编译成功！${NC}"
    else
        echo -e "\n${RED}❌ 编译失败，请检查错误信息${NC}"
    fi
    
    echo ""
    read -p "按Enter键继续..."
}

# 8. 运行测试
run_tests() {
    echo -e "\n${GREEN}=== 运行测试 ===${NC}\n"
    
    read -p "运行所有测试还是特定测试? (all/specific): " test_type
    
    if [ "$test_type" = "specific" ]; then
        read -p "请输入测试类名模式（如：*LoginUser*）: " test_pattern
        mvn test -Dtest="$test_pattern"
    else
        mvn test
    fi
    
    if [ $? -eq 0 ]; then
        echo -e "\n${GREEN}✅ 测试通过！${NC}"
    else
        echo -e "\n${YELLOW}⚠️  部分测试失败，请检查详情${NC}"
    fi
    
    echo ""
    read -p "按Enter键继续..."
}

# 9. 查看迁移计划
view_plan() {
    echo -e "\n${GREEN}=== 迁移计划 ===${NC}\n"
    
    if [ -f "base-core-migration-plan.md" ]; then
        less base-core-migration-plan.md
    else
        echo -e "${RED}迁移计划文件不存在${NC}"
    fi
    
    echo ""
    read -p "按Enter键继续..."
}

# 10. 查看迁移日志
view_log() {
    echo -e "\n${GREEN}=== 迁移日志 ===${NC}\n"
    
    if [ -f "base-core-migration-log.md" ]; then
        cat base-core-migration-log.md
    else
        echo -e "${YELLOW}尚无迁移日志${NC}"
    fi
    
    echo ""
    read -p "按Enter键继续..."
}

# 11. 查看类引用分析报告
view_analysis() {
    echo -e "\n${GREEN}=== 类引用分析报告 ===${NC}\n"
    
    if [ -f "base-core-class-references-report.md" ]; then
        less base-core-class-references-report.md
    else
        echo -e "${RED}分析报告不存在，请先运行'分析类引用关系'${NC}"
    fi
    
    echo ""
    read -p "按Enter键继续..."
}

# 12. 显示帮助信息
show_help() {
    echo -e "\n${GREEN}=== 帮助信息 ===${NC}\n"
    
    cat << 'EOF'
Base-Core模块迁移工具使用指南

推荐流程：
  1. 首先备份base-core模块（选项1）
  2. 分析类引用关系（选项2）- 生成迁移优先级报告
  3. 建立测试基准（选项3）- 记录迁移前状态
  4. 创建迁移分支（选项4）- Git分支隔离

迁移步骤（针对每个类）：
  5. 迁移单个类（选项5）
     - 输入类名、目标模块、子包名
     - 建议先试运行查看效果
  
  6. 更新类引用（选项6）
     - 自动更新所有import语句
     - 建议先试运行
  
  7. 编译验证（选项7）
     - 确保没有编译错误
  
  8. 运行测试（选项8）
     - 确保功能正常

每迁移完一个类，建议提交一次：
  git add .
  git commit -m "refactor: migrate ClassName to target-module"

常用命令：
  # 查看当前base-core中的类
  find jeecg-boot-base-core/src -name "*.java" | wc -l
  
  # 搜索特定类的引用
  grep -r "ClassName" --include="*.java" .
  
  # 恢复备份
  bash base-core-migration-backup-*/restore.sh

相关文档：
  - base-core-migration-plan.md - 详细迁移计划
  - base-core-class-references-report.md - 类引用分析
  - base-core-migration-log.md - 迁移记录

EOF
    
    echo ""
    read -p "按Enter键继续..."
}

# 主循环
main() {
    while true; do
        clear
        echo "============================================================"
        echo "Base-Core模块迁移工具集"
        echo "============================================================"
        echo ""
        show_menu
        
        read choice
        
        case $choice in
            1) backup_base_core ;;
            2) analyze_references ;;
            3) establish_baseline ;;
            4) create_branch ;;
            5) migrate_class ;;
            6) update_references ;;
            7) compile_verify ;;
            8) run_tests ;;
            9) view_plan ;;
            10) view_log ;;
            11) view_analysis ;;
            12) show_help ;;
            0) 
                echo ""
                echo -e "${GREEN}感谢使用！${NC}"
                exit 0
                ;;
            *)
                echo -e "\n${RED}无效选项，请重试${NC}"
                sleep 2
                ;;
        esac
    done
}

# 运行主程序
main