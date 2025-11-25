//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.common.util.dira.dira;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecg.common.util.dira.a;
import org.jeecg.common.util.dira.b;

public class Classc extends b {
    String m;
    List<a> n;

    public String getDictTable() {
        return this.m;
    }

    public void setDictTable(String dictTable) {
        this.m = dictTable;
    }

    public List<a> getOtherColumns() {
        return this.n;
    }

    public void setOtherColumns(List<a> otherColumns) {
        this.n = otherColumns;
    }

    public Classc() {
    }

    public Classc(String key, String title, String dictTable) {
        this.b = "string";
        this.e = "link_down";
        this.a = key;
        this.f = title;
        this.m = dictTable;
    }

    public Map<String, Object> getPropertyJson() {
        Map map = new HashMap(5);
        map.put("key", this.getKey());
        JSONObject prop = this.getCommonJson();
        JSONObject temp = JSONObject.parseObject(this.m);
        prop.put("config", temp);
        prop.put("others", this.n);
        if (super.getPattern() != null) {
            prop.put("pattern", super.getPattern());
        }

        if (super.getErrorInfo() != null) {
            prop.put("errorInfo", super.getErrorInfo());
        }

        map.put("prop", prop);
        return map;
    }
}
