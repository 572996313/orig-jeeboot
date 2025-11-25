//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Generated;
import org.springframework.format.annotation.DateTimeFormat;

@TableName("onl_cgreport_param")
public class OnlCgreportParam implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(
        type = IdType.ASSIGN_ID
    )
    private String id;
    private String cgrheadId;
    private String paramName;
    private String paramTxt;
    private String paramValue;
    private Integer orderNum;
    private String createBy;
    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @DateTimeFormat(
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;
    private String updateBy;
    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @DateTimeFormat(
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date updateTime;

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getCgrheadId() {
        return this.cgrheadId;
    }

    @Generated
    public String getParamName() {
        return this.paramName;
    }

    @Generated
    public String getParamTxt() {
        return this.paramTxt;
    }

    @Generated
    public String getParamValue() {
        return this.paramValue;
    }

    @Generated
    public Integer getOrderNum() {
        return this.orderNum;
    }

    @Generated
    public String getCreateBy() {
        return this.createBy;
    }

    @Generated
    public Date getCreateTime() {
        return this.createTime;
    }

    @Generated
    public String getUpdateBy() {
        return this.updateBy;
    }

    @Generated
    public Date getUpdateTime() {
        return this.updateTime;
    }

    @Generated
    public void setId(final String id) {
        this.id = id;
    }

    @Generated
    public void setCgrheadId(final String cgrheadId) {
        this.cgrheadId = cgrheadId;
    }

    @Generated
    public void setParamName(final String paramName) {
        this.paramName = paramName;
    }

    @Generated
    public void setParamTxt(final String paramTxt) {
        this.paramTxt = paramTxt;
    }

    @Generated
    public void setParamValue(final String paramValue) {
        this.paramValue = paramValue;
    }

    @Generated
    public void setOrderNum(final Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Generated
    public void setCreateBy(final String createBy) {
        this.createBy = createBy;
    }

    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @Generated
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    @Generated
    public void setUpdateBy(final String updateBy) {
        this.updateBy = updateBy;
    }

    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @Generated
    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OnlCgreportParam)) {
            return false;
        } else {
            OnlCgreportParam other = (OnlCgreportParam)o;
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

                Object this$cgrheadId = this.getCgrheadId();
                Object other$cgrheadId = other.getCgrheadId();
                if (this$cgrheadId == null) {
                    if (other$cgrheadId != null) {
                        return false;
                    }
                } else if (!this$cgrheadId.equals(other$cgrheadId)) {
                    return false;
                }

                Object this$paramName = this.getParamName();
                Object other$paramName = other.getParamName();
                if (this$paramName == null) {
                    if (other$paramName != null) {
                        return false;
                    }
                } else if (!this$paramName.equals(other$paramName)) {
                    return false;
                }

                Object this$paramTxt = this.getParamTxt();
                Object other$paramTxt = other.getParamTxt();
                if (this$paramTxt == null) {
                    if (other$paramTxt != null) {
                        return false;
                    }
                } else if (!this$paramTxt.equals(other$paramTxt)) {
                    return false;
                }

                Object this$paramValue = this.getParamValue();
                Object other$paramValue = other.getParamValue();
                if (this$paramValue == null) {
                    if (other$paramValue != null) {
                        return false;
                    }
                } else if (!this$paramValue.equals(other$paramValue)) {
                    return false;
                }

                Object this$createBy = this.getCreateBy();
                Object other$createBy = other.getCreateBy();
                if (this$createBy == null) {
                    if (other$createBy != null) {
                        return false;
                    }
                } else if (!this$createBy.equals(other$createBy)) {
                    return false;
                }

                Object this$createTime = this.getCreateTime();
                Object other$createTime = other.getCreateTime();
                if (this$createTime == null) {
                    if (other$createTime != null) {
                        return false;
                    }
                } else if (!this$createTime.equals(other$createTime)) {
                    return false;
                }

                Object this$updateBy = this.getUpdateBy();
                Object other$updateBy = other.getUpdateBy();
                if (this$updateBy == null) {
                    if (other$updateBy != null) {
                        return false;
                    }
                } else if (!this$updateBy.equals(other$updateBy)) {
                    return false;
                }

                Object this$updateTime = this.getUpdateTime();
                Object other$updateTime = other.getUpdateTime();
                if (this$updateTime == null) {
                    if (other$updateTime != null) {
                        return false;
                    }
                } else if (!this$updateTime.equals(other$updateTime)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof OnlCgreportParam;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $orderNum = this.getOrderNum();
        result = result * 59 + ($orderNum == null ? 43 : $orderNum.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $cgrheadId = this.getCgrheadId();
        result = result * 59 + ($cgrheadId == null ? 43 : $cgrheadId.hashCode());
        Object $paramName = this.getParamName();
        result = result * 59 + ($paramName == null ? 43 : $paramName.hashCode());
        Object $paramTxt = this.getParamTxt();
        result = result * 59 + ($paramTxt == null ? 43 : $paramTxt.hashCode());
        Object $paramValue = this.getParamValue();
        result = result * 59 + ($paramValue == null ? 43 : $paramValue.hashCode());
        Object $createBy = this.getCreateBy();
        result = result * 59 + ($createBy == null ? 43 : $createBy.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        Object $updateBy = this.getUpdateBy();
        result = result * 59 + ($updateBy == null ? 43 : $updateBy.hashCode());
        Object $updateTime = this.getUpdateTime();
        result = result * 59 + ($updateTime == null ? 43 : $updateTime.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "OnlCgreportParam(id=" + var10000 + ", cgrheadId=" + this.getCgrheadId() + ", paramName=" + this.getParamName() + ", paramTxt=" + this.getParamTxt() + ", paramValue=" + this.getParamValue() + ", orderNum=" + this.getOrderNum() + ", createBy=" + this.getCreateBy() + ", createTime=" + this.getCreateTime() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ")";
    }
}
