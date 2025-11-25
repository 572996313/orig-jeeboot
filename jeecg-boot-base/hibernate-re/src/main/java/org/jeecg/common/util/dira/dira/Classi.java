//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.common.util.dira.dira;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.jeecg.common.util.dira.b;

public class Classi extends b {
    private static final long m = 3786503639885610767L;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private Integer s;

    public String getDict() {
        return this.n;
    }

    public void setDict(String dict) {
        this.n = dict;
    }

    public String getPidField() {
        return this.o;
    }

    public void setPidField(String pidField) {
        this.o = pidField;
    }

    public String getPidValue() {
        return this.p;
    }

    public void setPidValue(String pidValue) {
        this.p = pidValue;
    }

    public String getHasChildField() {
        return this.q;
    }

    public void setHasChildField(String hasChildField) {
        this.q = hasChildField;
    }

    public Classi() {
        this.s = 0;
    }

    public String getTextField() {
        return this.r;
    }

    public void setTextField(String textField) {
        this.r = textField;
    }

    public Integer getPidComponent() {
        return this.s;
    }

    public void setPidComponent(Integer pidComponent) {
        this.s = pidComponent;
    }

    public Classi(String key, String title, String dict, String pidField, String pidValue) {
        this.s = 0;
        this.b = "string";
        this.e = "sel_tree";
        this.a = key;
        this.f = title;
        this.n = dict;
        this.o = pidField;
        this.p = pidValue;
    }

    public Classi(String key, String title, String pidValue) {
        this.s = 0;
        this.b = "string";
        this.e = "cat_tree";
        this.a = key;
        this.f = title;
        this.p = pidValue;
    }

    public Classi(String key, String title, String pidValue, String textField) {
        this(key, title, pidValue);
        this.r = textField;
    }

    public Map<String, Object> getPropertyJson() {
        Map map = new HashMap(5);
        map.put("key", this.getKey());
        JSONObject prop = this.getCommonJson();
        if (this.n != null) {
            prop.put("dict", this.n);
        }

        if (this.o != null) {
            prop.put("pidField", this.o);
        }

        if (this.p != null) {
            prop.put("pidValue", this.p);
        }

        if (this.r != null) {
            prop.put("textField", this.r);
        }

        if (this.q != null) {
            prop.put("hasChildField", this.q);
        }

        if (this.s != null) {
            prop.put("pidComponent", this.s);
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
