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

public class Classd extends b {
    private static final long m = -558615331436437200L;
    private Integer n;
    private Integer o;
    private Integer p;
    private Integer q;
    private Integer r;

    public Integer getMultipleOf() {
        return this.n;
    }

    public void setMultipleOf(Integer multipleOf) {
        this.n = multipleOf;
    }

    public Integer getMaxinum() {
        return this.o;
    }

    public void setMaxinum(Integer maxinum) {
        this.o = maxinum;
    }

    public Integer getExclusiveMaximum() {
        return this.p;
    }

    public void setExclusiveMaximum(Integer exclusiveMaximum) {
        this.p = exclusiveMaximum;
    }

    public Integer getMinimum() {
        return this.q;
    }

    public void setMinimum(Integer minimum) {
        this.q = minimum;
    }

    public Integer getExclusiveMinimum() {
        return this.r;
    }

    public void setExclusiveMinimum(Integer exclusiveMinimum) {
        this.r = exclusiveMinimum;
    }

    public Classd() {
    }

    public Classd(String key, String title, String type) {
        this.a = key;
        this.b = type;
        this.f = title;
        this.e = "number";
    }

    public Classd(String key, String title, String view, List<DictModel> include) {
        this.b = "integer";
        this.a = key;
        this.e = view;
        this.f = title;
        this.c = include;
    }

    public Map<String, Object> getPropertyJson() {
        Map map = new HashMap(5);
        map.put("key", this.getKey());
        JSONObject prop = this.getCommonJson();
        if (this.n != null) {
            prop.put("multipleOf", this.n);
        }

        if (this.o != null) {
            prop.put("maxinum", this.o);
        }

        if (this.p != null) {
            prop.put("exclusiveMaximum", this.p);
        }

        if (this.q != null) {
            prop.put("minimum", this.q);
        }

        if (this.r != null) {
            prop.put("exclusiveMinimum", this.r);
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
