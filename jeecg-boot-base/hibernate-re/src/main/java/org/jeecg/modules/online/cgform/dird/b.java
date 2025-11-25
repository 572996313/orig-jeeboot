//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dird;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.springframework.beans.BeanUtils;

public class b {
    private static final List<OnlCgformButton> a = new ArrayList();
    private static final Set<String> b = new HashSet();

    private static void a(String code, String name, String icon, int orderNum) {
        OnlCgformButton btn = new OnlCgformButton();
        btn.setButtonCode(code);
        btn.setButtonName(name);
        btn.setButtonIcon(icon);
        btn.setOrderNum(orderNum);
        btn.setButtonStyle("built-in");
        btn.setButtonStatus("1");
        a.add(btn);
        b.add(code);
    }

    public static Set<String> getButtonCodeSet() {
        return b;
    }

    public static List<OnlCgformButton> a(String formId, List<OnlCgformButton> buttonList) {
        List result = new ArrayList();

        for(OnlCgformButton bIBtn : a) {
            OnlCgformButton find = (OnlCgformButton)buttonList.stream().filter((b) -> bIBtn.getButtonCode().equals(b.getButtonCode())).findFirst().orElse(null);
            if (find == null) {
                OnlCgformButton copy = new OnlCgformButton();
                BeanUtils.copyProperties(bIBtn, copy);
                copy.setCgformHeadId(formId);
                result.add(copy);
            } else {
                find.setOrderNum(bIBtn.getOrderNum());
                result.add(find);
            }
        }

        result.sort(Comparator.comparing(OnlCgformButton::getOrderNum));
        return result;
    }

    static {
        int orderNum = 0;
        ++orderNum;
        a("add", "新增", "plus-outlined", orderNum);
        ++orderNum;
        a("edit", "编辑", "", orderNum);
        ++orderNum;
        a("detail", "详情", "", orderNum);
        ++orderNum;
        a("delete", "删除", "", orderNum);
        ++orderNum;
        a("batch_delete", "批量删除", "delete-outlined", orderNum);
        ++orderNum;
        a("import", "导入", "import-outlined", orderNum);
        ++orderNum;
        a("export", "导出", "export-outlined", orderNum);
        ++orderNum;
        a("query", "查询", "search", orderNum);
        ++orderNum;
        a("reset", "重置", "reload", orderNum);
        ++orderNum;
        a("bpm", "提交流程", "", orderNum);
        ++orderNum;
        a("super_query", "高级查询", "filter-outlined", orderNum);
        ++orderNum;
        a("form_confirm", "确定", "", orderNum);
        ++orderNum;
        a("form_sub_add", "新增", "plus-outlined", orderNum);
        ++orderNum;
        a("form_sub_batch_delete", "删除", "minus-outlined", orderNum);
        ++orderNum;
        a("form_sub_open_add", "新增", "expand-alt-outlined", orderNum);
        ++orderNum;
        a("form_sub_open_edit", "", "form-outlined", orderNum);
        ++orderNum;
        a("aigc_mock_data", "生成测试数据", "robot-love-outline", orderNum);
    }
}
