//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Generated;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

@TableName("onl_auth_data")
public class OnlAuthData implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(
        type = IdType.ASSIGN_ID
    )
    private String id;
    @Excel(
        name = "online表ID",
        width = (double)15.0F
    )
    private String cgformId;
    @Excel(
        name = "规则名",
        width = (double)15.0F
    )
    private String ruleName;
    @Excel(
        name = "规则列",
        width = (double)15.0F
    )
    private String ruleColumn;
    @Excel(
        name = "规则条件 大于小于like",
        width = (double)15.0F
    )
    private String ruleOperator;
    @Excel(
        name = "规则值",
        width = (double)15.0F
    )
    private String ruleValue;
    @Excel(
        name = "1有效 0无效",
        width = (double)15.0F
    )
    private Integer status;
    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd"
    )
    @DateTimeFormat(
        pattern = "yyyy-MM-dd"
    )
    private Date createTime;
    private String createBy;
    private String updateBy;
    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd"
    )
    @DateTimeFormat(
        pattern = "yyyy-MM-dd"
    )
    private Date updateTime;

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getCgformId() {
        return this.cgformId;
    }

    @Generated
    public String getRuleName() {
        return this.ruleName;
    }

    @Generated
    public String getRuleColumn() {
        return this.ruleColumn;
    }

    @Generated
    public String getRuleOperator() {
        return this.ruleOperator;
    }

    @Generated
    public String getRuleValue() {
        return this.ruleValue;
    }

    @Generated
    public Integer getStatus() {
        return this.status;
    }

    @Generated
    public Date getCreateTime() {
        return this.createTime;
    }

    @Generated
    public String getCreateBy() {
        return this.createBy;
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
    public OnlAuthData setId(final String id) {
        this.id = id;
        return this;
    }

    @Generated
    public OnlAuthData setCgformId(final String cgformId) {
        this.cgformId = cgformId;
        return this;
    }

    @Generated
    public OnlAuthData setRuleName(final String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    @Generated
    public OnlAuthData setRuleColumn(final String ruleColumn) {
        this.ruleColumn = ruleColumn;
        return this;
    }

    @Generated
    public OnlAuthData setRuleOperator(final String ruleOperator) {
        this.ruleOperator = ruleOperator;
        return this;
    }

    @Generated
    public OnlAuthData setRuleValue(final String ruleValue) {
        this.ruleValue = ruleValue;
        return this;
    }

    @Generated
    public OnlAuthData setStatus(final Integer status) {
        this.status = status;
        return this;
    }

    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd"
    )
    @Generated
    public OnlAuthData setCreateTime(final Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Generated
    public OnlAuthData setCreateBy(final String createBy) {
        this.createBy = createBy;
        return this;
    }

    @Generated
    public OnlAuthData setUpdateBy(final String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd"
    )
    @Generated
    public OnlAuthData setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "OnlAuthData(id=" + var10000 + ", cgformId=" + this.getCgformId() + ", ruleName=" + this.getRuleName() + ", ruleColumn=" + this.getRuleColumn() + ", ruleOperator=" + this.getRuleOperator() + ", ruleValue=" + this.getRuleValue() + ", status=" + this.getStatus() + ", createTime=" + this.getCreateTime() + ", createBy=" + this.getCreateBy() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ")";
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OnlAuthData)) {
            return false;
        } else {
            OnlAuthData other = (OnlAuthData)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
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

                Object this$cgformId = this.getCgformId();
                Object other$cgformId = other.getCgformId();
                if (this$cgformId == null) {
                    if (other$cgformId != null) {
                        return false;
                    }
                } else if (!this$cgformId.equals(other$cgformId)) {
                    return false;
                }

                Object this$ruleName = this.getRuleName();
                Object other$ruleName = other.getRuleName();
                if (this$ruleName == null) {
                    if (other$ruleName != null) {
                        return false;
                    }
                } else if (!this$ruleName.equals(other$ruleName)) {
                    return false;
                }

                Object this$ruleColumn = this.getRuleColumn();
                Object other$ruleColumn = other.getRuleColumn();
                if (this$ruleColumn == null) {
                    if (other$ruleColumn != null) {
                        return false;
                    }
                } else if (!this$ruleColumn.equals(other$ruleColumn)) {
                    return false;
                }

                Object this$ruleOperator = this.getRuleOperator();
                Object other$ruleOperator = other.getRuleOperator();
                if (this$ruleOperator == null) {
                    if (other$ruleOperator != null) {
                        return false;
                    }
                } else if (!this$ruleOperator.equals(other$ruleOperator)) {
                    return false;
                }

                Object this$ruleValue = this.getRuleValue();
                Object other$ruleValue = other.getRuleValue();
                if (this$ruleValue == null) {
                    if (other$ruleValue != null) {
                        return false;
                    }
                } else if (!this$ruleValue.equals(other$ruleValue)) {
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

                Object this$createBy = this.getCreateBy();
                Object other$createBy = other.getCreateBy();
                if (this$createBy == null) {
                    if (other$createBy != null) {
                        return false;
                    }
                } else if (!this$createBy.equals(other$createBy)) {
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
        return other instanceof OnlAuthData;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $cgformId = this.getCgformId();
        result = result * 59 + ($cgformId == null ? 43 : $cgformId.hashCode());
        Object $ruleName = this.getRuleName();
        result = result * 59 + ($ruleName == null ? 43 : $ruleName.hashCode());
        Object $ruleColumn = this.getRuleColumn();
        result = result * 59 + ($ruleColumn == null ? 43 : $ruleColumn.hashCode());
        Object $ruleOperator = this.getRuleOperator();
        result = result * 59 + ($ruleOperator == null ? 43 : $ruleOperator.hashCode());
        Object $ruleValue = this.getRuleValue();
        result = result * 59 + ($ruleValue == null ? 43 : $ruleValue.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        Object $createBy = this.getCreateBy();
        result = result * 59 + ($createBy == null ? 43 : $createBy.hashCode());
        Object $updateBy = this.getUpdateBy();
        result = result * 59 + ($updateBy == null ? 43 : $updateBy.hashCode());
        Object $updateTime = this.getUpdateTime();
        result = result * 59 + ($updateTime == null ? 43 : $updateTime.hashCode());
        return result;
    }
}
