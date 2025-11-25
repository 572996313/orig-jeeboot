//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Generated;
import org.springframework.format.annotation.DateTimeFormat;

@TableName("onl_cgform_index")
public class OnlCgformIndex implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(
        type = IdType.ASSIGN_UUID
    )
    private String id;
    private String cgformHeadId;
    private String indexName;
    private String indexNameOld;
    private String indexField;
    private String isDbSynch;
    private Integer delFlag;
    private String indexType;
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
    public String getCgformHeadId() {
        return this.cgformHeadId;
    }

    @Generated
    public String getIndexName() {
        return this.indexName;
    }

    @Generated
    public String getIndexNameOld() {
        return this.indexNameOld;
    }

    @Generated
    public String getIndexField() {
        return this.indexField;
    }

    @Generated
    public String getIsDbSynch() {
        return this.isDbSynch;
    }

    @Generated
    public Integer getDelFlag() {
        return this.delFlag;
    }

    @Generated
    public String getIndexType() {
        return this.indexType;
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
    public void setCgformHeadId(final String cgformHeadId) {
        this.cgformHeadId = cgformHeadId;
    }

    @Generated
    public void setIndexName(final String indexName) {
        this.indexName = indexName;
    }

    @Generated
    public void setIndexNameOld(final String indexNameOld) {
        this.indexNameOld = indexNameOld;
    }

    @Generated
    public void setIndexField(final String indexField) {
        this.indexField = indexField;
    }

    @Generated
    public void setIsDbSynch(final String isDbSynch) {
        this.isDbSynch = isDbSynch;
    }

    @Generated
    public void setDelFlag(final Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Generated
    public void setIndexType(final String indexType) {
        this.indexType = indexType;
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
        } else if (!(o instanceof OnlCgformIndex)) {
            return false;
        } else {
            OnlCgformIndex other = (OnlCgformIndex)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$delFlag = this.getDelFlag();
                Object other$delFlag = other.getDelFlag();
                if (this$delFlag == null) {
                    if (other$delFlag != null) {
                        return false;
                    }
                } else if (!this$delFlag.equals(other$delFlag)) {
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

                Object this$indexName = this.getIndexName();
                Object other$indexName = other.getIndexName();
                if (this$indexName == null) {
                    if (other$indexName != null) {
                        return false;
                    }
                } else if (!this$indexName.equals(other$indexName)) {
                    return false;
                }

                Object this$indexNameOld = this.getIndexNameOld();
                Object other$indexNameOld = other.getIndexNameOld();
                if (this$indexNameOld == null) {
                    if (other$indexNameOld != null) {
                        return false;
                    }
                } else if (!this$indexNameOld.equals(other$indexNameOld)) {
                    return false;
                }

                Object this$indexField = this.getIndexField();
                Object other$indexField = other.getIndexField();
                if (this$indexField == null) {
                    if (other$indexField != null) {
                        return false;
                    }
                } else if (!this$indexField.equals(other$indexField)) {
                    return false;
                }

                Object this$isDbSynch = this.getIsDbSynch();
                Object other$isDbSynch = other.getIsDbSynch();
                if (this$isDbSynch == null) {
                    if (other$isDbSynch != null) {
                        return false;
                    }
                } else if (!this$isDbSynch.equals(other$isDbSynch)) {
                    return false;
                }

                Object this$indexType = this.getIndexType();
                Object other$indexType = other.getIndexType();
                if (this$indexType == null) {
                    if (other$indexType != null) {
                        return false;
                    }
                } else if (!this$indexType.equals(other$indexType)) {
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
        return other instanceof OnlCgformIndex;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $delFlag = this.getDelFlag();
        result = result * 59 + ($delFlag == null ? 43 : $delFlag.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $cgformHeadId = this.getCgformHeadId();
        result = result * 59 + ($cgformHeadId == null ? 43 : $cgformHeadId.hashCode());
        Object $indexName = this.getIndexName();
        result = result * 59 + ($indexName == null ? 43 : $indexName.hashCode());
        Object $indexNameOld = this.getIndexNameOld();
        result = result * 59 + ($indexNameOld == null ? 43 : $indexNameOld.hashCode());
        Object $indexField = this.getIndexField();
        result = result * 59 + ($indexField == null ? 43 : $indexField.hashCode());
        Object $isDbSynch = this.getIsDbSynch();
        result = result * 59 + ($isDbSynch == null ? 43 : $isDbSynch.hashCode());
        Object $indexType = this.getIndexType();
        result = result * 59 + ($indexType == null ? 43 : $indexType.hashCode());
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
        return "OnlCgformIndex(id=" + var10000 + ", cgformHeadId=" + this.getCgformHeadId() + ", indexName=" + this.getIndexName() + ", indexNameOld=" + this.getIndexNameOld() + ", indexField=" + this.getIndexField() + ", isDbSynch=" + this.getIsDbSynch() + ", delFlag=" + this.getDelFlag() + ", indexType=" + this.getIndexType() + ", createBy=" + this.getCreateBy() + ", createTime=" + this.getCreateTime() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ")";
    }
}
