//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.common.util.dira;

import lombok.Generated;

public class a {
    private String b;
    private String c;
    private Integer d;
    protected String a;

    public a() {
    }

    public a(String title, String field, Integer order) {
        this.b = title;
        this.c = field;
        this.d = order;
    }

    public a(String title, String field, Integer order, String defVal) {
        this.b = title;
        this.c = field;
        this.d = order;
        this.a = defVal;
    }

    @Generated
    public String getTitle() {
        return this.b;
    }

    @Generated
    public String getField() {
        return this.c;
    }

    @Generated
    public Integer getOrder() {
        return this.d;
    }

    @Generated
    public String getDefVal() {
        return this.a;
    }

    @Generated
    public void setTitle(final String title) {
        this.b = title;
    }

    @Generated
    public void setField(final String field) {
        this.c = field;
    }

    @Generated
    public void setOrder(final Integer order) {
        this.d = order;
    }

    @Generated
    public void setDefVal(final String defVal) {
        this.a = defVal;
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
                Object this$order = this.getOrder();
                Object other$order = other.getOrder();
                if (this$order == null) {
                    if (other$order != null) {
                        return false;
                    }
                } else if (!this$order.equals(other$order)) {
                    return false;
                }

                Object this$title = this.getTitle();
                Object other$title = other.getTitle();
                if (this$title == null) {
                    if (other$title != null) {
                        return false;
                    }
                } else if (!this$title.equals(other$title)) {
                    return false;
                }

                Object this$field = this.getField();
                Object other$field = other.getField();
                if (this$field == null) {
                    if (other$field != null) {
                        return false;
                    }
                } else if (!this$field.equals(other$field)) {
                    return false;
                }

                Object this$defVal = this.getDefVal();
                Object other$defVal = other.getDefVal();
                if (this$defVal == null) {
                    if (other$defVal != null) {
                        return false;
                    }
                } else if (!this$defVal.equals(other$defVal)) {
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
        Object $order = this.getOrder();
        result = result * 59 + ($order == null ? 43 : $order.hashCode());
        Object $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        Object $field = this.getField();
        result = result * 59 + ($field == null ? 43 : $field.hashCode());
        Object $defVal = this.getDefVal();
        result = result * 59 + ($defVal == null ? 43 : $defVal.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getTitle();
        return "BaseColumn(title=" + var10000 + ", field=" + this.getField() + ", order=" + this.getOrder() + ", defVal=" + this.getDefVal() + ")";
    }
}
