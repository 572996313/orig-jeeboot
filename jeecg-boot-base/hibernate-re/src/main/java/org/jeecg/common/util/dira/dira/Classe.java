//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.common.util.dira.dira;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.jeecg.common.util.dira.b;

public class Classe extends b {
    private static final long m = -6726224734007205673L;
    private String n;
    private String o;
    private String p;
    private Boolean q;

    public String getCode() {
        return this.n;
    }

    public void setCode(String code) {
        this.n = code;
    }

    public String getDestFields() {
        return this.o;
    }

    public void setDestFields(String destFields) {
        this.o = destFields;
    }

    public String getOrgFields() {
        return this.p;
    }

    public void setOrgFields(String orgFields) {
        this.p = orgFields;
    }

    public Boolean getPopupMulti() {
        return this.q;
    }

    public void setPopupMulti(Boolean popupMulti) {
        this.q = popupMulti;
    }

    public Classe() {
    }

    public Classe(String key, String title, String code, String destFields, String orgFields) {
        this.e = "popup_dict";
        this.b = "string";
        this.a = key;
        this.f = title;
        this.n = code;
        this.o = destFields;
        this.p = orgFields;
        this.q = true;
    }

    public Map<String, Object> getPropertyJson() {
        Map map = new HashMap(5);
        map.put("key", this.getKey());
        JSONObject prop = this.getCommonJson();
        if (this.n != null) {
            prop.put("code", this.n);
        }

        if (this.o != null) {
            prop.put("destFields", this.o);
        }

        if (this.p != null) {
            prop.put("orgFields", this.p);
        }

        prop.put("popupMulti", this.q);
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
