//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dira;

import lombok.Generated;

public class a {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    private String getQuerySql() {
        StringBuffer sb = new StringBuffer();
        String space = " ";
        sb.append("SELECT ");
        return null;
    }

    @Generated
    public String getTable() {
        return this.a;
    }

    @Generated
    public String getTxt() {
        return this.b;
    }

    @Generated
    public String getKey() {
        return this.c;
    }

    @Generated
    public String getLinkField() {
        return this.d;
    }

    @Generated
    public String getIdField() {
        return this.e;
    }

    @Generated
    public String getPidField() {
        return this.f;
    }

    @Generated
    public String getPidValue() {
        return this.g;
    }

    @Generated
    public String getCondition() {
        return this.h;
    }

    @Generated
    public void setTable(final String table) {
        this.a = table;
    }

    @Generated
    public void setTxt(final String txt) {
        this.b = txt;
    }

    @Generated
    public void setKey(final String key) {
        this.c = key;
    }

    @Generated
    public void setLinkField(final String linkField) {
        this.d = linkField;
    }

    @Generated
    public void setIdField(final String idField) {
        this.e = idField;
    }

    @Generated
    public void setPidField(final String pidField) {
        this.f = pidField;
    }

    @Generated
    public void setPidValue(final String pidValue) {
        this.g = pidValue;
    }

    @Generated
    public void setCondition(final String condition) {
        this.h = condition;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof a)) {
            return false;
        } else {
            a other = (a)o;
            if (!other.a(this)) {
                return false;
            } else {
                Object this$table = this.getTable();
                Object other$table = other.getTable();
                if (this$table == null) {
                    if (other$table != null) {
                        return false;
                    }
                } else if (!this$table.equals(other$table)) {
                    return false;
                }

                Object this$txt = this.getTxt();
                Object other$txt = other.getTxt();
                if (this$txt == null) {
                    if (other$txt != null) {
                        return false;
                    }
                } else if (!this$txt.equals(other$txt)) {
                    return false;
                }

                Object this$key = this.getKey();
                Object other$key = other.getKey();
                if (this$key == null) {
                    if (other$key != null) {
                        return false;
                    }
                } else if (!this$key.equals(other$key)) {
                    return false;
                }

                Object this$linkField = this.getLinkField();
                Object other$linkField = other.getLinkField();
                if (this$linkField == null) {
                    if (other$linkField != null) {
                        return false;
                    }
                } else if (!this$linkField.equals(other$linkField)) {
                    return false;
                }

                Object this$idField = this.getIdField();
                Object other$idField = other.getIdField();
                if (this$idField == null) {
                    if (other$idField != null) {
                        return false;
                    }
                } else if (!this$idField.equals(other$idField)) {
                    return false;
                }

                Object this$pidField = this.getPidField();
                Object other$pidField = other.getPidField();
                if (this$pidField == null) {
                    if (other$pidField != null) {
                        return false;
                    }
                } else if (!this$pidField.equals(other$pidField)) {
                    return false;
                }

                Object this$pidValue = this.getPidValue();
                Object other$pidValue = other.getPidValue();
                if (this$pidValue == null) {
                    if (other$pidValue != null) {
                        return false;
                    }
                } else if (!this$pidValue.equals(other$pidValue)) {
                    return false;
                }

                Object this$condition = this.getCondition();
                Object other$condition = other.getCondition();
                if (this$condition == null) {
                    if (other$condition != null) {
                        return false;
                    }
                } else if (!this$condition.equals(other$condition)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean a(final Object other) {
        return other instanceof a;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $table = this.getTable();
        result = result * 59 + ($table == null ? 43 : $table.hashCode());
        Object $txt = this.getTxt();
        result = result * 59 + ($txt == null ? 43 : $txt.hashCode());
        Object $key = this.getKey();
        result = result * 59 + ($key == null ? 43 : $key.hashCode());
        Object $linkField = this.getLinkField();
        result = result * 59 + ($linkField == null ? 43 : $linkField.hashCode());
        Object $idField = this.getIdField();
        result = result * 59 + ($idField == null ? 43 : $idField.hashCode());
        Object $pidField = this.getPidField();
        result = result * 59 + ($pidField == null ? 43 : $pidField.hashCode());
        Object $pidValue = this.getPidValue();
        result = result * 59 + ($pidValue == null ? 43 : $pidValue.hashCode());
        Object $condition = this.getCondition();
        result = result * 59 + ($condition == null ? 43 : $condition.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getTable();
        return "LinkDown(table=" + var10000 + ", txt=" + this.getTxt() + ", key=" + this.getKey() + ", linkField=" + this.getLinkField() + ", idField=" + this.getIdField() + ", pidField=" + this.getPidField() + ", pidValue=" + this.getPidValue() + ", condition=" + this.getCondition() + ")";
    }
}
