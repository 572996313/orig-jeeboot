//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.model;

import lombok.Generated;

public class TreeModel {
    private String label;
    private String store;
    private String id;
    private String pid;

    @Generated
    public String getLabel() {
        return this.label;
    }

    @Generated
    public String getStore() {
        return this.store;
    }

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getPid() {
        return this.pid;
    }

    @Generated
    public void setLabel(final String label) {
        this.label = label;
    }

    @Generated
    public void setStore(final String store) {
        this.store = store;
    }

    @Generated
    public void setId(final String id) {
        this.id = id;
    }

    @Generated
    public void setPid(final String pid) {
        this.pid = pid;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TreeModel)) {
            return false;
        } else {
            TreeModel other = (TreeModel)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$label = this.getLabel();
                Object other$label = other.getLabel();
                if (this$label == null) {
                    if (other$label != null) {
                        return false;
                    }
                } else if (!this$label.equals(other$label)) {
                    return false;
                }

                Object this$store = this.getStore();
                Object other$store = other.getStore();
                if (this$store == null) {
                    if (other$store != null) {
                        return false;
                    }
                } else if (!this$store.equals(other$store)) {
                    return false;
                }

                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$pid = this.getPid();
                Object other$pid = other.getPid();
                if (this$pid == null) {
                    if (other$pid != null) {
                        return false;
                    }
                } else if (!this$pid.equals(other$pid)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof TreeModel;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $label = this.getLabel();
        result = result * 59 + ($label == null ? 43 : $label.hashCode());
        Object $store = this.getStore();
        result = result * 59 + ($store == null ? 43 : $store.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $pid = this.getPid();
        result = result * 59 + ($pid == null ? 43 : $pid.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getLabel();
        return "TreeModel(label=" + var10000 + ", store=" + this.getStore() + ", id=" + this.getId() + ", pid=" + this.getPid() + ")";
    }
}
