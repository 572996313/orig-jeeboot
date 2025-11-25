//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.vo;

import java.io.Serializable;
import lombok.Generated;

public class AuthDataVO implements Serializable {
    private static final long serialVersionUID = 1057819436991228603L;
    private String id;
    private String title;
    private String relId;
    private Boolean checked;

    public Boolean isChecked() {
        return this.relId != null && this.relId.length() > 0;
    }

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getTitle() {
        return this.title;
    }

    @Generated
    public String getRelId() {
        return this.relId;
    }

    @Generated
    public Boolean getChecked() {
        return this.checked;
    }

    @Generated
    public void setId(final String id) {
        this.id = id;
    }

    @Generated
    public void setTitle(final String title) {
        this.title = title;
    }

    @Generated
    public void setRelId(final String relId) {
        this.relId = relId;
    }

    @Generated
    public void setChecked(final Boolean checked) {
        this.checked = checked;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AuthDataVO)) {
            return false;
        } else {
            AuthDataVO other = (AuthDataVO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$checked = this.getChecked();
                Object other$checked = other.getChecked();
                if (this$checked == null) {
                    if (other$checked != null) {
                        return false;
                    }
                } else if (!this$checked.equals(other$checked)) {
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

                Object this$title = this.getTitle();
                Object other$title = other.getTitle();
                if (this$title == null) {
                    if (other$title != null) {
                        return false;
                    }
                } else if (!this$title.equals(other$title)) {
                    return false;
                }

                Object this$relId = this.getRelId();
                Object other$relId = other.getRelId();
                if (this$relId == null) {
                    if (other$relId != null) {
                        return false;
                    }
                } else if (!this$relId.equals(other$relId)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof AuthDataVO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $checked = this.getChecked();
        result = result * 59 + ($checked == null ? 43 : $checked.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        Object $relId = this.getRelId();
        result = result * 59 + ($relId == null ? 43 : $relId.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "AuthDataVO(id=" + var10000 + ", title=" + this.getTitle() + ", relId=" + this.getRelId() + ", checked=" + this.getChecked() + ")";
    }
}
