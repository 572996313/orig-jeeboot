//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.model;

import lombok.Generated;

public class g {
    private String a;

    public g() {
    }

    public g(String customRender) {
        this.a = customRender;
    }

    @Generated
    public String getCustomRender() {
        return this.a;
    }

    @Generated
    public void setCustomRender(final String customRender) {
        this.a = customRender;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof g)) {
            return false;
        } else {
            g other = (g)o;
            if (!other.a(this)) {
                return false;
            } else {
                Object this$customRender = this.getCustomRender();
                Object other$customRender = other.getCustomRender();
                if (this$customRender == null) {
                    if (other$customRender != null) {
                        return false;
                    }
                } else if (!this$customRender.equals(other$customRender)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean a(final Object other) {
        return other instanceof g;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $customRender = this.getCustomRender();
        result = result * 59 + ($customRender == null ? 43 : $customRender.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "ScopedSlots(customRender=" + this.getCustomRender() + ")";
    }
}
