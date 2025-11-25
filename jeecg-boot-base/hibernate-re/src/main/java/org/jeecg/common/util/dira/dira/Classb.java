//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.common.util.dira.dira;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.util.a;

import java.util.HashMap;
import java.util.Map;

public class Classb extends org.jeecg.common.util.dira.b {
    private static final long m = -8939298551502162479L;

    public Classb() {
    }

    public Classb(String key, String title) {
        this.b = "string";
        this.e = "hidden";
        this.a = key;
        this.f = title;
    }

    public Map<String, Object> getPropertyJson() {
        Map map = new HashMap(5);
        map.put("key", this.getKey());
        JSONObject prop = this.getCommonJson();
        prop.put("hidden", true);
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
