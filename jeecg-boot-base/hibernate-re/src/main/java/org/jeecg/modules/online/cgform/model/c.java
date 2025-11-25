//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.model;

import lombok.Generated;

public class c {
    private String a;
    private String b;
    private String c;

    public c() {
    }

    public c(String field, String key) {
        this.c = key;
        this.a = field;
    }

    @Generated
    public String getField() {
        return this.a;
    }

    @Generated
    public String getTable() {
        return this.b;
    }

    @Generated
    public String getKey() {
        return this.c;
    }

    @Generated
    public void setField(final String field) {
        this.a = field;
    }

    @Generated
    public void setTable(final String table) {
        this.b = table;
    }

    @Generated
    public void setKey(final String key) {
        this.c = key;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof c)) {
            return false;
        } else {
            c other = (c)o;
            if (!other.a(this)) {
                return false;
            } else {
                Object this$field = this.getField();
                Object other$field = other.getField();
                if (this$field == null) {
                    if (other$field != null) {
                        return false;
                    }
                } else if (!this$field.equals(other$field)) {
                    return false;
                }

                Object this$table = this.getTable();
                Object other$table = other.getTable();
                if (this$table == null) {
                    if (other$table != null) {
                        return false;
                    }
                } else if (!this$table.equals(other$table)) {
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

                return true;
            }
        }
    }

    @Generated
    protected boolean a(final Object other) {
        return other instanceof c;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $field = this.getField();
        result = result * 59 + ($field == null ? 43 : $field.hashCode());
        Object $table = this.getTable();
        result = result * 59 + ($table == null ? 43 : $table.hashCode());
        Object $key = this.getKey();
        result = result * 59 + ($key == null ? 43 : $key.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getField();
        return "OnlForeignKey(field=" + var10000 + ", table=" + this.getTable() + ", key=" + this.getKey() + ")";
    }
}
