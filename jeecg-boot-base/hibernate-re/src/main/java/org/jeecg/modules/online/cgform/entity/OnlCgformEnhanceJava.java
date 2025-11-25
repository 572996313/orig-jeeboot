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

@TableName("onl_cgform_enhance_java")
public class OnlCgformEnhanceJava implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(
        type = IdType.ASSIGN_UUID
    )
    private String id;
    private String cgformHeadId;
    private String buttonCode;
    private String cgJavaType;
    private String cgJavaValue;
    private String activeStatus;
    private String event;

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
    public String getCgJavaType() {
        return this.cgJavaType;
    }

    @Generated
    public String getCgJavaValue() {
        return this.cgJavaValue;
    }

    @Generated
    public String getActiveStatus() {
        return this.activeStatus;
    }

    @Generated
    public String getEvent() {
        return this.event;
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
    public void setCgJavaType(final String cgJavaType) {
        this.cgJavaType = cgJavaType;
    }

    @Generated
    public void setCgJavaValue(final String cgJavaValue) {
        this.cgJavaValue = cgJavaValue;
    }

    @Generated
    public void setActiveStatus(final String activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Generated
    public void setEvent(final String event) {
        this.event = event;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OnlCgformEnhanceJava)) {
            return false;
        } else {
            OnlCgformEnhanceJava other = (OnlCgformEnhanceJava)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
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

                Object this$cgJavaType = this.getCgJavaType();
                Object other$cgJavaType = other.getCgJavaType();
                if (this$cgJavaType == null) {
                    if (other$cgJavaType != null) {
                        return false;
                    }
                } else if (!this$cgJavaType.equals(other$cgJavaType)) {
                    return false;
                }

                Object this$cgJavaValue = this.getCgJavaValue();
                Object other$cgJavaValue = other.getCgJavaValue();
                if (this$cgJavaValue == null) {
                    if (other$cgJavaValue != null) {
                        return false;
                    }
                } else if (!this$cgJavaValue.equals(other$cgJavaValue)) {
                    return false;
                }

                Object this$activeStatus = this.getActiveStatus();
                Object other$activeStatus = other.getActiveStatus();
                if (this$activeStatus == null) {
                    if (other$activeStatus != null) {
                        return false;
                    }
                } else if (!this$activeStatus.equals(other$activeStatus)) {
                    return false;
                }

                Object this$event = this.getEvent();
                Object other$event = other.getEvent();
                if (this$event == null) {
                    if (other$event != null) {
                        return false;
                    }
                } else if (!this$event.equals(other$event)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof OnlCgformEnhanceJava;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $cgformHeadId = this.getCgformHeadId();
        result = result * 59 + ($cgformHeadId == null ? 43 : $cgformHeadId.hashCode());
        Object $buttonCode = this.getButtonCode();
        result = result * 59 + ($buttonCode == null ? 43 : $buttonCode.hashCode());
        Object $cgJavaType = this.getCgJavaType();
        result = result * 59 + ($cgJavaType == null ? 43 : $cgJavaType.hashCode());
        Object $cgJavaValue = this.getCgJavaValue();
        result = result * 59 + ($cgJavaValue == null ? 43 : $cgJavaValue.hashCode());
        Object $activeStatus = this.getActiveStatus();
        result = result * 59 + ($activeStatus == null ? 43 : $activeStatus.hashCode());
        Object $event = this.getEvent();
        result = result * 59 + ($event == null ? 43 : $event.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "OnlCgformEnhanceJava(id=" + var10000 + ", cgformHeadId=" + this.getCgformHeadId() + ", buttonCode=" + this.getButtonCode() + ", cgJavaType=" + this.getCgJavaType() + ", cgJavaValue=" + this.getCgJavaValue() + ", activeStatus=" + this.getActiveStatus() + ", event=" + this.getEvent() + ")";
    }
}
