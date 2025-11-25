//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.common.util.dira.dira;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.dira.b;

public class Classg extends b {
    private static final long m = -3200493311633999539L;
    private Integer n;
    private Integer o;

    public Integer getMaxLength() {
        return this.n;
    }

    public void setMaxLength(Integer maxLength) {
        this.n = maxLength;
    }

    public Integer getMinLength() {
        return this.o;
    }

    public void setMinLength(Integer minLength) {
        this.o = minLength;
    }

    public Classg() {
    }

    public Classg(String key, String title, String view, Integer maxLength) {
        this.n = maxLength;
        this.a = key;
        this.e = view;
        this.f = title;
        this.b = "string";
    }

    public Classg(String key, String title, String view, Integer maxLength, List<DictModel> include) {
        this.n = maxLength;
        this.a = key;
        this.e = view;
        this.f = title;
        this.b = "string";
        this.c = include;
    }

    public Map<String, Object> getPropertyJson() {
        Map map = new HashMap(5);
        map.put("key", this.getKey());
        JSONObject prop = this.getCommonJson();
        if (this.n != null) {
            prop.put("maxLength", this.n);
        }

        if (this.o != null) {
            prop.put("minLength", this.o);
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
