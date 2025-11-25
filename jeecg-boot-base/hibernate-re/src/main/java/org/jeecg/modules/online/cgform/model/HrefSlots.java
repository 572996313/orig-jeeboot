//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.model;

import lombok.Generated;

public class HrefSlots {
    private String slotName;
    private String href;

    public HrefSlots() {
    }

    public HrefSlots(String slotName, String href) {
        this.slotName = slotName;
        this.href = href;
    }

    @Generated
    public String getSlotName() {
        return this.slotName;
    }

    @Generated
    public String getHref() {
        return this.href;
    }

    @Generated
    public void setSlotName(final String slotName) {
        this.slotName = slotName;
    }

    @Generated
    public void setHref(final String href) {
        this.href = href;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof HrefSlots)) {
            return false;
        } else {
            HrefSlots other = (HrefSlots)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$slotName = this.getSlotName();
                Object other$slotName = other.getSlotName();
                if (this$slotName == null) {
                    if (other$slotName != null) {
                        return false;
                    }
                } else if (!this$slotName.equals(other$slotName)) {
                    return false;
                }

                Object this$href = this.getHref();
                Object other$href = other.getHref();
                if (this$href == null) {
                    if (other$href != null) {
                        return false;
                    }
                } else if (!this$href.equals(other$href)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof HrefSlots;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $slotName = this.getSlotName();
        result = result * 59 + ($slotName == null ? 43 : $slotName.hashCode());
        Object $href = this.getHref();
        result = result * 59 + ($href == null ? 43 : $href.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getSlotName();
        return "HrefSlots(slotName=" + var10000 + ", href=" + this.getHref() + ")";
    }
}
