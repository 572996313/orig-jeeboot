//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.vo;

import java.io.Serializable;
import lombok.Generated;

public class AuthPageVO implements Serializable {
    private static final long serialVersionUID = 724713901683956568L;
    private String id;
    private String code;
    private String title;
    private Integer page;
    private Integer control;
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
    public String getCode() {
        return this.code;
    }

    @Generated
    public String getTitle() {
        return this.title;
    }

    @Generated
    public Integer getPage() {
        return this.page;
    }

    @Generated
    public Integer getControl() {
        return this.control;
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
    public void setCode(final String code) {
        this.code = code;
    }

    @Generated
    public void setTitle(final String title) {
        this.title = title;
    }

    @Generated
    public void setPage(final Integer page) {
        this.page = page;
    }

    @Generated
    public void setControl(final Integer control) {
        this.control = control;
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
        } else if (!(o instanceof AuthPageVO)) {
            return false;
        } else {
            AuthPageVO other = (AuthPageVO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$page = this.getPage();
                Object other$page = other.getPage();
                if (this$page == null) {
                    if (other$page != null) {
                        return false;
                    }
                } else if (!this$page.equals(other$page)) {
                    return false;
                }

                Object this$control = this.getControl();
                Object other$control = other.getControl();
                if (this$control == null) {
                    if (other$control != null) {
                        return false;
                    }
                } else if (!this$control.equals(other$control)) {
                    return false;
                }

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

                Object this$code = this.getCode();
                Object other$code = other.getCode();
                if (this$code == null) {
                    if (other$code != null) {
                        return false;
                    }
                } else if (!this$code.equals(other$code)) {
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
        return other instanceof AuthPageVO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $page = this.getPage();
        result = result * 59 + ($page == null ? 43 : $page.hashCode());
        Object $control = this.getControl();
        result = result * 59 + ($control == null ? 43 : $control.hashCode());
        Object $checked = this.getChecked();
        result = result * 59 + ($checked == null ? 43 : $checked.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        Object $relId = this.getRelId();
        result = result * 59 + ($relId == null ? 43 : $relId.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "AuthPageVO(id=" + var10000 + ", code=" + this.getCode() + ", title=" + this.getTitle() + ", page=" + this.getPage() + ", control=" + this.getControl() + ", relId=" + this.getRelId() + ", checked=" + this.getChecked() + ")";
    }
}
