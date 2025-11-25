//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter.dirb;

import java.util.HashMap;
import java.util.Map;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class a extends org.jeecg.modules.online.cgform.converter.dira.a {
    private String f;

    public String getTreeText() {
        return this.f;
    }

    public void setTreeText(String treeText) {
        this.f = treeText;
    }

    public a(OnlCgformField onlCgformField) {
        super("SYS_CATEGORY", "ID", "NAME");
        this.f = onlCgformField.getDictText();
        this.b = onlCgformField.getDbFieldName();
    }

    public Map<String, String> getConfig() {
        if (oConvertUtils.isEmpty(this.f)) {
            return null;
        } else {
            Map map = new HashMap(5);
            map.put("treeText", this.f);
            map.put("field", this.b);
            return map;
        }
    }
}
