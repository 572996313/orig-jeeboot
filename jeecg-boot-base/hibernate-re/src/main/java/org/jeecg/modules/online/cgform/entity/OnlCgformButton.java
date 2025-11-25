//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Generated;

@TableName("onl_cgform_button")
public class OnlCgformButton implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(
        type = IdType.ASSIGN_UUID
    )
    private String id;
    private String cgformHeadId;
    private String buttonCode;
    private String buttonName;
    private String buttonStyle;
    private String optType;
    private String exp;
    private String buttonStatus;
    private Integer orderNum;
    private String buttonIcon;
    private String optPosition;

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getCgformHeadId() {
        return this.cgformHeadId;
    }

    @Generated
    public String getButtonCode() {
        return this.buttonCode;
    }

    @Generated
    public String getButtonName() {
        return this.buttonName;
    }

    @Generated
    public String getButtonStyle() {
        return this.buttonStyle;
    }

    @Generated
    public String getOptType() {
        return this.optType;
    }

    @Generated
    public String getExp() {
        return this.exp;
    }

    @Generated
    public String getButtonStatus() {
        return this.buttonStatus;
    }

    @Generated
    public Integer getOrderNum() {
        return this.orderNum;
    }

    @Generated
    public String getButtonIcon() {
        return this.buttonIcon;
    }

    @Generated
    public String getOptPosition() {
        return this.optPosition;
    }

    @Generated
    public void setId(final String id) {
        this.id = id;
    }

    @Generated
    public void setCgformHeadId(final String cgformHeadId) {
        this.cgformHeadId = cgformHeadId;
    }

    @Generated
    public void setButtonCode(final String buttonCode) {
        this.buttonCode = buttonCode;
    }

    @Generated
    public void setButtonName(final String buttonName) {
        this.buttonName = buttonName;
    }

    @Generated
    public void setButtonStyle(final String buttonStyle) {
        this.buttonStyle = buttonStyle;
    }

    @Generated
    public void setOptType(final String optType) {
        this.optType = optType;
    }

    @Generated
    public void setExp(final String exp) {
        this.exp = exp;
    }

    @Generated
    public void setButtonStatus(final String buttonStatus) {
        this.buttonStatus = buttonStatus;
    }

    @Generated
    public void setOrderNum(final Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Generated
    public void setButtonIcon(final String buttonIcon) {
        this.buttonIcon = buttonIcon;
    }

    @Generated
    public void setOptPosition(final String optPosition) {
        this.optPosition = optPosition;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OnlCgformButton)) {
            return false;
        } else {
            OnlCgformButton other = (OnlCgformButton)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$orderNum = this.getOrderNum();
                Object other$orderNum = other.getOrderNum();
                if (this$orderNum == null) {
                    if (other$orderNum != null) {
                        return false;
                    }
                } else if (!this$orderNum.equals(other$orderNum)) {
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

                Object this$cgformHeadId = this.getCgformHeadId();
                Object other$cgformHeadId = other.getCgformHeadId();
                if (this$cgformHeadId == null) {
                    if (other$cgformHeadId != null) {
                        return false;
                    }
                } else if (!this$cgformHeadId.equals(other$cgformHeadId)) {
                    return false;
                }

                Object this$buttonCode = this.getButtonCode();
                Object other$buttonCode = other.getButtonCode();
                if (this$buttonCode == null) {
                    if (other$buttonCode != null) {
                        return false;
                    }
                } else if (!this$buttonCode.equals(other$buttonCode)) {
                    return false;
                }

                Object this$buttonName = this.getButtonName();
                Object other$buttonName = other.getButtonName();
                if (this$buttonName == null) {
                    if (other$buttonName != null) {
                        return false;
                    }
                } else if (!this$buttonName.equals(other$buttonName)) {
                    return false;
                }

                Object this$buttonStyle = this.getButtonStyle();
                Object other$buttonStyle = other.getButtonStyle();
                if (this$buttonStyle == null) {
                    if (other$buttonStyle != null) {
                        return false;
                    }
                } else if (!this$buttonStyle.equals(other$buttonStyle)) {
                    return false;
                }

                Object this$optType = this.getOptType();
                Object other$optType = other.getOptType();
                if (this$optType == null) {
                    if (other$optType != null) {
                        return false;
                    }
                } else if (!this$optType.equals(other$optType)) {
                    return false;
                }

                Object this$exp = this.getExp();
                Object other$exp = other.getExp();
                if (this$exp == null) {
                    if (other$exp != null) {
                        return false;
                    }
                } else if (!this$exp.equals(other$exp)) {
                    return false;
                }

                Object this$buttonStatus = this.getButtonStatus();
                Object other$buttonStatus = other.getButtonStatus();
                if (this$buttonStatus == null) {
                    if (other$buttonStatus != null) {
                        return false;
                    }
                } else if (!this$buttonStatus.equals(other$buttonStatus)) {
                    return false;
                }

                Object this$buttonIcon = this.getButtonIcon();
                Object other$buttonIcon = other.getButtonIcon();
                if (this$buttonIcon == null) {
                    if (other$buttonIcon != null) {
                        return false;
                    }
                } else if (!this$buttonIcon.equals(other$buttonIcon)) {
                    return false;
                }

                Object this$optPosition = this.getOptPosition();
                Object other$optPosition = other.getOptPosition();
                if (this$optPosition == null) {
                    if (other$optPosition != null) {
                        return false;
                    }
                } else if (!this$optPosition.equals(other$optPosition)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof OnlCgformButton;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $orderNum = this.getOrderNum();
        result = result * 59 + ($orderNum == null ? 43 : $orderNum.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $cgformHeadId = this.getCgformHeadId();
        result = result * 59 + ($cgformHeadId == null ? 43 : $cgformHeadId.hashCode());
        Object $buttonCode = this.getButtonCode();
        result = result * 59 + ($buttonCode == null ? 43 : $buttonCode.hashCode());
        Object $buttonName = this.getButtonName();
        result = result * 59 + ($buttonName == null ? 43 : $buttonName.hashCode());
        Object $buttonStyle = this.getButtonStyle();
        result = result * 59 + ($buttonStyle == null ? 43 : $buttonStyle.hashCode());
        Object $optType = this.getOptType();
        result = result * 59 + ($optType == null ? 43 : $optType.hashCode());
        Object $exp = this.getExp();
        result = result * 59 + ($exp == null ? 43 : $exp.hashCode());
        Object $buttonStatus = this.getButtonStatus();
        result = result * 59 + ($buttonStatus == null ? 43 : $buttonStatus.hashCode());
        Object $buttonIcon = this.getButtonIcon();
        result = result * 59 + ($buttonIcon == null ? 43 : $buttonIcon.hashCode());
        Object $optPosition = this.getOptPosition();
        result = result * 59 + ($optPosition == null ? 43 : $optPosition.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "OnlCgformButton(id=" + var10000 + ", cgformHeadId=" + this.getCgformHeadId() + ", buttonCode=" + this.getButtonCode() + ", buttonName=" + this.getButtonName() + ", buttonStyle=" + this.getButtonStyle() + ", optType=" + this.getOptType() + ", exp=" + this.getExp() + ", buttonStatus=" + this.getButtonStatus() + ", orderNum=" + this.getOrderNum() + ", buttonIcon=" + this.getButtonIcon() + ", optPosition=" + this.getOptPosition() + ")";
    }
}
