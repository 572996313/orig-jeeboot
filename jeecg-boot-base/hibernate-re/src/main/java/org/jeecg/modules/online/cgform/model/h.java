//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.model;

import org.jeecg.common.util.oConvertUtils;

public class h {
    public static final String a = "asc";
    public static final String b = "desc";
    public static final String c = " ORDER BY ";
    public static final String d = "ID";
    private String e;
    private String f;
    private String g;

    public static h a() {
        return a("");
    }

    public static h a(String alias) {
        h sqlOrder = new h("ID");
        sqlOrder.setAlias(alias);
        return sqlOrder;
    }

    public String getRealSql() {
        String var10000 = this.g;
        String sql = var10000 + oConvertUtils.camelToUnderline(this.e);
        if ("asc".equals(this.f)) {
            sql = sql + " asc";
        } else {
            sql = sql + " desc";
        }

        return sql;
    }

    public h() {
    }

    public h(String column, String rule) {
        this.e = column;
        this.f = rule;
        this.g = "";
    }

    public h(String column) {
        this.f = "desc";
        this.e = column;
        this.g = "";
    }

    public String getColumn() {
        return this.e;
    }

    public void setColumn(String column) {
        this.e = column;
    }

    public String getRule() {
        return this.f;
    }

    public void setRule(String rule) {
        this.f = rule;
    }

    public String getAlias() {
        return this.g;
    }

    public void setAlias(String alias) {
        this.g = alias;
    }
}
