//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.common.util.dira;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.jeecg.common.system.vo.DictModel;

public abstract class b implements Serializable {
    private static final long m = -426159949502493187L;
    protected String a;
    protected String b;
    protected List<DictModel> c;
    protected Object d;
    private String n;
    private String o;
    protected String e;
    protected String f;
    protected Integer g;
    protected boolean h;
    protected String i;
    protected String j;
    protected Integer k;
    protected String l;

    public String getDefVal() {
        return this.i;
    }

    public void setDefVal(String defVal) {
        this.i = defVal;
    }

    public boolean a() {
        return this.h;
    }

    public void setDisabled(boolean disabled) {
        this.h = disabled;
    }

    public String getView() {
        return this.e;
    }

    public void setView(String view) {
        this.e = view;
    }

    public String getKey() {
        return this.a;
    }

    public void setKey(String key) {
        this.a = key;
    }

    public String getType() {
        return this.b;
    }

    public void setType(String type) {
        this.b = type;
    }

    public List<DictModel> getInclude() {
        return this.c;
    }

    public void setInclude(List<DictModel> include) {
        this.c = include;
    }

    public Object getConstant() {
        return this.d;
    }

    public void setConstant(Object constant) {
        this.d = constant;
    }

    public String getTitle() {
        return this.f;
    }

    public void setTitle(String title) {
        this.f = title;
    }

    public Integer getOrder() {
        return this.g;
    }

    public void setOrder(Integer order) {
        this.g = order;
    }

    public String getFieldExtendJson() {
        return this.j;
    }

    public void setFieldExtendJson(String fieldExtendJson) {
        this.j = fieldExtendJson;
    }

    public Integer getDbPointLength() {
        return this.k;
    }

    public void setDbPointLength(Integer dbPointLength) {
        this.k = dbPointLength;
    }

    public String getMode() {
        return this.l;
    }

    public void setMode(String mode) {
        this.l = mode;
    }

    public String getPattern() {
        return this.n;
    }

    public void setPattern(String pattern) {
        this.n = pattern;
    }

    public String getErrorInfo() {
        return this.o;
    }

    public void setErrorInfo(String errorInfo) {
        this.o = errorInfo;
    }

    public abstract Map<String, Object> getPropertyJson();

    public JSONObject getCommonJson() {
        JSONObject json = new JSONObject();
        json.put("type", this.b);
        if (this.c != null && this.c.size() > 0) {
            json.put("enum", this.c);
        }

        if (this.d != null) {
            json.put("const", this.d);
        }

        if (this.f != null) {
            json.put("title", this.f);
        }

        if (this.g != null) {
            json.put("order", this.g);
        }

        if (this.e == null) {
            json.put("view", "input");
        } else {
            json.put("view", this.e);
        }

        if (this.h) {
            String str = "{\"widgetattrs\":{\"disabled\":true}}";
            JSONObject ui = JSONObject.parseObject(str);
            json.put("ui", ui);
        }

        if (this.i != null && this.i.length() > 0) {
            json.put("defVal", this.i);
        }

        if (this.j != null) {
            json.put("fieldExtendJson", this.j);
        }

        if (this.k != null) {
            json.put("dbPointLength", this.k);
        }

        if (this.l != null) {
            json.put("mode", this.l);
        }

        if (this.n != null) {
            json.put("pattern", this.n);
        }

        if (this.o != null) {
            json.put("errorInfo", this.o);
        }

        return json;
    }
}
