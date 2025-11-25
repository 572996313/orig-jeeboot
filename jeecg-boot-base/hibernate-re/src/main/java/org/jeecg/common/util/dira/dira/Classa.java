package org.jeecg.common.util.dira.dira;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.jeecg.common.util.dira.b;

public class Classa extends b {
    private static final long m = 3786503639885610767L;
    private String n;
    private String o;
    private String p;

    public String getDictCode() {
        return this.n;
    }

    public void setDictCode(String dictCode) {
        this.n = dictCode;
    }

    public String getDictTable() {
        return this.o;
    }

    public void setDictTable(String dictTable) {
        this.o = dictTable;
    }

    public String getDictText() {
        return this.p;
    }

    public void setDictText(String dictText) {
        this.p = dictText;
    }

    public Classa() {
    }

    public Classa(String key, String title, String dictTable, String dictCode, String dictText) {
        this.b = "string";
        this.e = "sel_search";
        this.a = key;
        this.f = title;
        this.n = dictCode;
        this.o = dictTable;
        this.p = dictText;
    }

    public Classa(String key, String view, String title, String dictTable, String dictCode, String dictText) {
        this.b = "string";
        this.e = view;
        this.a = key;
        this.f = title;
        this.n = dictCode;
        this.o = dictTable;
        this.p = dictText;
    }

    public Map<String, Object> getPropertyJson() {
        Map map = new HashMap(5);
        map.put("key", this.getKey());
        JSONObject prop = this.getCommonJson();
        if (this.n != null) {
            prop.put("dictCode", this.n);
        }

        if (this.o != null) {
            prop.put("dictTable", this.o);
        }

        if (this.p != null) {
            prop.put("dictText", this.p);
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
