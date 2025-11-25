//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.model;

import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class e {
    private String a;
    private Map<String, Object> b;
    private Map<String, String> c;
    private List<OnlCgformField> d;

    public e() {
    }

    public e(String sql, Map<String, Object> params) {
        this.a = sql;
        this.b = params;
    }

    @Generated
    public String getSql() {
        return this.a;
    }

    @Generated
    public Map<String, Object> getParams() {
        return this.b;
    }

    @Generated
    public Map<String, String> getTableAliasMap() {
        return this.c;
    }

    @Generated
    public List<OnlCgformField> getFieldList() {
        return this.d;
    }

    @Generated
    public void setSql(final String sql) {
        this.a = sql;
    }

    @Generated
    public void setParams(final Map<String, Object> params) {
        this.b = params;
    }

    @Generated
    public void setTableAliasMap(final Map<String, String> tableAliasMap) {
        this.c = tableAliasMap;
    }

    @Generated
    public void setFieldList(final List<OnlCgformField> fieldList) {
        this.d = fieldList;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof e)) {
            return false;
        } else {
            e other = (e)o;
            if (!other.a(this)) {
                return false;
            } else {
                Object this$sql = this.getSql();
                Object other$sql = other.getSql();
                if (this$sql == null) {
                    if (other$sql != null) {
                        return false;
                    }
                } else if (!this$sql.equals(other$sql)) {
                    return false;
                }

                Object this$params = this.getParams();
                Object other$params = other.getParams();
                if (this$params == null) {
                    if (other$params != null) {
                        return false;
                    }
                } else if (!this$params.equals(other$params)) {
                    return false;
                }

                Object this$tableAliasMap = this.getTableAliasMap();
                Object other$tableAliasMap = other.getTableAliasMap();
                if (this$tableAliasMap == null) {
                    if (other$tableAliasMap != null) {
                        return false;
                    }
                } else if (!this$tableAliasMap.equals(other$tableAliasMap)) {
                    return false;
                }

                Object this$fieldList = this.getFieldList();
                Object other$fieldList = other.getFieldList();
                if (this$fieldList == null) {
                    if (other$fieldList != null) {
                        return false;
                    }
                } else if (!this$fieldList.equals(other$fieldList)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean a(final Object other) {
        return other instanceof e;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $sql = this.getSql();
        result = result * 59 + ($sql == null ? 43 : $sql.hashCode());
        Object $params = this.getParams();
        result = result * 59 + ($params == null ? 43 : $params.hashCode());
        Object $tableAliasMap = this.getTableAliasMap();
        result = result * 59 + ($tableAliasMap == null ? 43 : $tableAliasMap.hashCode());
        Object $fieldList = this.getFieldList();
        result = result * 59 + ($fieldList == null ? 43 : $fieldList.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getSql();
        return "OnlQueryModel(sql=" + var10000 + ", params=" + this.getParams() + ", tableAliasMap=" + this.getTableAliasMap() + ", fieldList=" + this.getFieldList() + ")";
    }
}
