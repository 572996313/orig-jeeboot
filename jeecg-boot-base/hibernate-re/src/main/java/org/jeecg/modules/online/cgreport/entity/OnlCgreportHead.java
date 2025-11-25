//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Generated;
import org.apache.ibatis.type.JdbcType;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

@TableName("onl_cgreport_head")
public class OnlCgreportHead implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(
        type = IdType.ASSIGN_ID
    )
    private String id;
    private String code;
    private String name;
    private String cgrSql;
    private String returnValField;
    private String returnTxtField;
    private String returnType;
    @TableField(
        updateStrategy = FieldStrategy.ALWAYS,
        jdbcType = JdbcType.VARCHAR
    )
    @Dict(
        dicCode = "code",
        dicText = "name",
        dictTable = "sys_data_source"
    )
    private String dbSource;
    private String content;
    private Integer tenantId;
    private String lowAppId;
    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @DateTimeFormat(
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date updateTime;
    private String updateBy;
    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @DateTimeFormat(
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;
    private String createBy;

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getCode() {
        return this.code;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getCgrSql() {
        return this.cgrSql;
    }

    @Generated
    public String getReturnValField() {
        return this.returnValField;
    }

    @Generated
    public String getReturnTxtField() {
        return this.returnTxtField;
    }

    @Generated
    public String getReturnType() {
        return this.returnType;
    }

    @Generated
    public String getDbSource() {
        return this.dbSource;
    }

    @Generated
    public String getContent() {
        return this.content;
    }

    @Generated
    public Integer getTenantId() {
        return this.tenantId;
    }

    @Generated
    public String getLowAppId() {
        return this.lowAppId;
    }

    @Generated
    public Date getUpdateTime() {
        return this.updateTime;
    }

    @Generated
    public String getUpdateBy() {
        return this.updateBy;
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
    public void setId(final String id) {
        this.id = id;
    }

    @Generated
    public void setCode(final String code) {
        this.code = code;
    }

    @Generated
    public void setName(final String name) {
        this.name = name;
    }

    @Generated
    public void setCgrSql(final String cgrSql) {
        this.cgrSql = cgrSql;
    }

    @Generated
    public void setReturnValField(final String returnValField) {
        this.returnValField = returnValField;
    }

    @Generated
    public void setReturnTxtField(final String returnTxtField) {
        this.returnTxtField = returnTxtField;
    }

    @Generated
    public void setReturnType(final String returnType) {
        this.returnType = returnType;
    }

    @Generated
    public void setDbSource(final String dbSource) {
        this.dbSource = dbSource;
    }

    @Generated
    public void setContent(final String content) {
        this.content = content;
    }

    @Generated
    public void setTenantId(final Integer tenantId) {
        this.tenantId = tenantId;
    }

    @Generated
    public void setLowAppId(final String lowAppId) {
        this.lowAppId = lowAppId;
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
    public void setUpdateBy(final String updateBy) {
        this.updateBy = updateBy;
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
    public void setCreateBy(final String createBy) {
        this.createBy = createBy;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OnlCgreportHead)) {
            return false;
        } else {
            OnlCgreportHead other = (OnlCgreportHead)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$tenantId = this.getTenantId();
                Object other$tenantId = other.getTenantId();
                if (this$tenantId == null) {
                    if (other$tenantId != null) {
                        return false;
                    }
                } else if (!this$tenantId.equals(other$tenantId)) {
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

                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$cgrSql = this.getCgrSql();
                Object other$cgrSql = other.getCgrSql();
                if (this$cgrSql == null) {
                    if (other$cgrSql != null) {
                        return false;
                    }
                } else if (!this$cgrSql.equals(other$cgrSql)) {
                    return false;
                }

                Object this$returnValField = this.getReturnValField();
                Object other$returnValField = other.getReturnValField();
                if (this$returnValField == null) {
                    if (other$returnValField != null) {
                        return false;
                    }
                } else if (!this$returnValField.equals(other$returnValField)) {
                    return false;
                }

                Object this$returnTxtField = this.getReturnTxtField();
                Object other$returnTxtField = other.getReturnTxtField();
                if (this$returnTxtField == null) {
                    if (other$returnTxtField != null) {
                        return false;
                    }
                } else if (!this$returnTxtField.equals(other$returnTxtField)) {
                    return false;
                }

                Object this$returnType = this.getReturnType();
                Object other$returnType = other.getReturnType();
                if (this$returnType == null) {
                    if (other$returnType != null) {
                        return false;
                    }
                } else if (!this$returnType.equals(other$returnType)) {
                    return false;
                }

                Object this$dbSource = this.getDbSource();
                Object other$dbSource = other.getDbSource();
                if (this$dbSource == null) {
                    if (other$dbSource != null) {
                        return false;
                    }
                } else if (!this$dbSource.equals(other$dbSource)) {
                    return false;
                }

                Object this$content = this.getContent();
                Object other$content = other.getContent();
                if (this$content == null) {
                    if (other$content != null) {
                        return false;
                    }
                } else if (!this$content.equals(other$content)) {
                    return false;
                }

                Object this$lowAppId = this.getLowAppId();
                Object other$lowAppId = other.getLowAppId();
                if (this$lowAppId == null) {
                    if (other$lowAppId != null) {
                        return false;
                    }
                } else if (!this$lowAppId.equals(other$lowAppId)) {
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

                Object this$updateBy = this.getUpdateBy();
                Object other$updateBy = other.getUpdateBy();
                if (this$updateBy == null) {
                    if (other$updateBy != null) {
                        return false;
                    }
                } else if (!this$updateBy.equals(other$updateBy)) {
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

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof OnlCgreportHead;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $tenantId = this.getTenantId();
        result = result * 59 + ($tenantId == null ? 43 : $tenantId.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $cgrSql = this.getCgrSql();
        result = result * 59 + ($cgrSql == null ? 43 : $cgrSql.hashCode());
        Object $returnValField = this.getReturnValField();
        result = result * 59 + ($returnValField == null ? 43 : $returnValField.hashCode());
        Object $returnTxtField = this.getReturnTxtField();
        result = result * 59 + ($returnTxtField == null ? 43 : $returnTxtField.hashCode());
        Object $returnType = this.getReturnType();
        result = result * 59 + ($returnType == null ? 43 : $returnType.hashCode());
        Object $dbSource = this.getDbSource();
        result = result * 59 + ($dbSource == null ? 43 : $dbSource.hashCode());
        Object $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        Object $lowAppId = this.getLowAppId();
        result = result * 59 + ($lowAppId == null ? 43 : $lowAppId.hashCode());
        Object $updateTime = this.getUpdateTime();
        result = result * 59 + ($updateTime == null ? 43 : $updateTime.hashCode());
        Object $updateBy = this.getUpdateBy();
        result = result * 59 + ($updateBy == null ? 43 : $updateBy.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        Object $createBy = this.getCreateBy();
        result = result * 59 + ($createBy == null ? 43 : $createBy.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "OnlCgreportHead(id=" + var10000 + ", code=" + this.getCode() + ", name=" + this.getName() + ", cgrSql=" + this.getCgrSql() + ", returnValField=" + this.getReturnValField() + ", returnTxtField=" + this.getReturnTxtField() + ", returnType=" + this.getReturnType() + ", dbSource=" + this.getDbSource() + ", content=" + this.getContent() + ", tenantId=" + this.getTenantId() + ", lowAppId=" + this.getLowAppId() + ", updateTime=" + this.getUpdateTime() + ", updateBy=" + this.getUpdateBy() + ", createTime=" + this.getCreateTime() + ", createBy=" + this.getCreateBy() + ")";
    }
}
