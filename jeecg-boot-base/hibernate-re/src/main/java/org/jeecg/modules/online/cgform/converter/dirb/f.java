//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter.dirb;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.jeecg.modules.online.cgform.converter.dira.a;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class f extends a {
    private String f;

    public String getLinkField() {
        return this.f;
    }

    public void setLinkField(String linkField) {
        this.f = linkField;
    }

    public f(OnlCgformField onlCgformField) {
        String dictTable = onlCgformField.getDictTable();
        org.jeecg.modules.online.cgform.dira.a linkDown = (org.jeecg.modules.online.cgform.dira.a)JSONObject.parseObject(dictTable, org.jeecg.modules.online.cgform.dira.a.class);
        this.setTable(linkDown.getTable());
        this.setCode(linkDown.getKey());
        this.setText(linkDown.getTxt());
        this.f = linkDown.getLinkField();
    }

    public Map<String, String> getConfig() {
        Map map = new HashMap(5);
        map.put("linkField", this.f);
        return map;
    }
}
