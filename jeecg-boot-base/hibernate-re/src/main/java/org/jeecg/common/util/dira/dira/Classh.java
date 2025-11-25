//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.common.util.dira.dira;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.jeecg.common.util.dira.b;

public class Classh extends b {
    private String m;

    public Classh() {
    }

    public Classh(String key, String title, String extendStr) {
        this.b = "string";
        this.e = "switch";
        this.a = key;
        this.f = title;
        this.m = extendStr;
    }

    public Map<String, Object> getPropertyJson() {
        Map map = new HashMap(5);
        map.put("key", this.getKey());
        JSONObject prop = this.getCommonJson();
        JSONArray array = new JSONArray();
        if (this.m != null) {
            try {
                array = JSONArray.parseArray(this.m);
            } catch (JSONException var6) {
                JSONObject jsonObject = JSONArray.parseObject(this.m);
                if (jsonObject.containsKey("switchOptions")) {
                    array = jsonObject.getJSONArray("switchOptions");
                }
            }

            prop.put("extendOption", array);
        }

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
