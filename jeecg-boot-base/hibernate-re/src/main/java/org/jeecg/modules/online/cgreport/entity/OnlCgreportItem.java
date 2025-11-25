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

@TableName("onl_cgreport_item")
public class OnlCgreportItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(
        type = IdType.ASSIGN_ID
    )
    private String id;
    private String cgrheadId;
    private String fieldName;
    private String fieldTxt;
    private Integer fieldWidth;
    private String fieldType;
    private String searchMode;
    private Integer isOrder;
    private Integer isSearch;
    private String dictCode;
    private String fieldHref;
    private Integer isShow;
    private Integer orderNum;
    private String replaceVal;
    private String isTotal;
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
    private String groupTitle;

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getCgrheadId() {
        return this.cgrheadId;
    }

    @Generated
    public String getFieldName() {
        return this.fieldName;
    }

    @Generated
    public String getFieldTxt() {
        return this.fieldTxt;
    }

    @Generated
    public Integer getFieldWidth() {
        return this.fieldWidth;
    }

    @Generated
    public String getFieldType() {
        return this.fieldType;
    }

    @Generated
    public String getSearchMode() {
        return this.searchMode;
    }

    @Generated
    public Integer getIsOrder() {
        return this.isOrder;
    }

    @Generated
    public Integer getIsSearch() {
        return this.isSearch;
    }

    @Generated
    public String getDictCode() {
        return this.dictCode;
    }

    @Generated
    public String getFieldHref() {
        return this.fieldHref;
    }

    @Generated
    public Integer getIsShow() {
        return this.isShow;
    }

    @Generated
    public Integer getOrderNum() {
        return this.orderNum;
    }

    @Generated
    public String getReplaceVal() {
        return this.replaceVal;
    }

    @Generated
    public String getIsTotal() {
        return this.isTotal;
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
    public String getGroupTitle() {
        return this.groupTitle;
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
    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }

    @Generated
    public void setFieldTxt(final String fieldTxt) {
        this.fieldTxt = fieldTxt;
    }

    @Generated
    public void setFieldWidth(final Integer fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    @Generated
    public void setFieldType(final String fieldType) {
        this.fieldType = fieldType;
    }

    @Generated
    public void setSearchMode(final String searchMode) {
        this.searchMode = searchMode;
    }

    @Generated
    public void setIsOrder(final Integer isOrder) {
        this.isOrder = isOrder;
    }

    @Generated
    public void setIsSearch(final Integer isSearch) {
        this.isSearch = isSearch;
    }

    @Generated
    public void setDictCode(final String dictCode) {
        this.dictCode = dictCode;
    }

    @Generated
    public void setFieldHref(final String fieldHref) {
        this.fieldHref = fieldHref;
    }

    @Generated
    public void setIsShow(final Integer isShow) {
        this.isShow = isShow;
    }

    @Generated
    public void setOrderNum(final Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Generated
    public void setReplaceVal(final String replaceVal) {
        this.replaceVal = replaceVal;
    }

    @Generated
    public void setIsTotal(final String isTotal) {
        this.isTotal = isTotal;
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
    public void setGroupTitle(final String groupTitle) {
        this.groupTitle = groupTitle;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OnlCgreportItem)) {
            return false;
        } else {
            OnlCgreportItem other = (OnlCgreportItem)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$fieldWidth = this.getFieldWidth();
                Object other$fieldWidth = other.getFieldWidth();
                if (this$fieldWidth == null) {
                    if (other$fieldWidth != null) {
                        return false;
                    }
                } else if (!this$fieldWidth.equals(other$fieldWidth)) {
                    return false;
                }

                Object this$isOrder = this.getIsOrder();
                Object other$isOrder = other.getIsOrder();
                if (this$isOrder == null) {
                    if (other$isOrder != null) {
                        return false;
                    }
                } else if (!this$isOrder.equals(other$isOrder)) {
                    return false;
                }

                Object this$isSearch = this.getIsSearch();
                Object other$isSearch = other.getIsSearch();
                if (this$isSearch == null) {
                    if (other$isSearch != null) {
                        return false;
                    }
                } else if (!this$isSearch.equals(other$isSearch)) {
                    return false;
                }

                Object this$isShow = this.getIsShow();
                Object other$isShow = other.getIsShow();
                if (this$isShow == null) {
                    if (other$isShow != null) {
                        return false;
                    }
                } else if (!this$isShow.equals(other$isShow)) {
                    return false;
                }

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

                Object this$fieldName = this.getFieldName();
                Object other$fieldName = other.getFieldName();
                if (this$fieldName == null) {
                    if (other$fieldName != null) {
                        return false;
                    }
                } else if (!this$fieldName.equals(other$fieldName)) {
                    return false;
                }

                Object this$fieldTxt = this.getFieldTxt();
                Object other$fieldTxt = other.getFieldTxt();
                if (this$fieldTxt == null) {
                    if (other$fieldTxt != null) {
                        return false;
                    }
                } else if (!this$fieldTxt.equals(other$fieldTxt)) {
                    return false;
                }

                Object this$fieldType = this.getFieldType();
                Object other$fieldType = other.getFieldType();
                if (this$fieldType == null) {
                    if (other$fieldType != null) {
                        return false;
                    }
                } else if (!this$fieldType.equals(other$fieldType)) {
                    return false;
                }

                Object this$searchMode = this.getSearchMode();
                Object other$searchMode = other.getSearchMode();
                if (this$searchMode == null) {
                    if (other$searchMode != null) {
                        return false;
                    }
                } else if (!this$searchMode.equals(other$searchMode)) {
                    return false;
                }

                Object this$dictCode = this.getDictCode();
                Object other$dictCode = other.getDictCode();
                if (this$dictCode == null) {
                    if (other$dictCode != null) {
                        return false;
                    }
                } else if (!this$dictCode.equals(other$dictCode)) {
                    return false;
                }

                Object this$fieldHref = this.getFieldHref();
                Object other$fieldHref = other.getFieldHref();
                if (this$fieldHref == null) {
                    if (other$fieldHref != null) {
                        return false;
                    }
                } else if (!this$fieldHref.equals(other$fieldHref)) {
                    return false;
                }

                Object this$replaceVal = this.getReplaceVal();
                Object other$replaceVal = other.getReplaceVal();
                if (this$replaceVal == null) {
                    if (other$replaceVal != null) {
                        return false;
                    }
                } else if (!this$replaceVal.equals(other$replaceVal)) {
                    return false;
                }

                Object this$isTotal = this.getIsTotal();
                Object other$isTotal = other.getIsTotal();
                if (this$isTotal == null) {
                    if (other$isTotal != null) {
                        return false;
                    }
                } else if (!this$isTotal.equals(other$isTotal)) {
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

                Object this$groupTitle = this.getGroupTitle();
                Object other$groupTitle = other.getGroupTitle();
                if (this$groupTitle == null) {
                    if (other$groupTitle != null) {
                        return false;
                    }
                } else if (!this$groupTitle.equals(other$groupTitle)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof OnlCgreportItem;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $fieldWidth = this.getFieldWidth();
        result = result * 59 + ($fieldWidth == null ? 43 : $fieldWidth.hashCode());
        Object $isOrder = this.getIsOrder();
        result = result * 59 + ($isOrder == null ? 43 : $isOrder.hashCode());
        Object $isSearch = this.getIsSearch();
        result = result * 59 + ($isSearch == null ? 43 : $isSearch.hashCode());
        Object $isShow = this.getIsShow();
        result = result * 59 + ($isShow == null ? 43 : $isShow.hashCode());
        Object $orderNum = this.getOrderNum();
        result = result * 59 + ($orderNum == null ? 43 : $orderNum.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $cgrheadId = this.getCgrheadId();
        result = result * 59 + ($cgrheadId == null ? 43 : $cgrheadId.hashCode());
        Object $fieldName = this.getFieldName();
        result = result * 59 + ($fieldName == null ? 43 : $fieldName.hashCode());
        Object $fieldTxt = this.getFieldTxt();
        result = result * 59 + ($fieldTxt == null ? 43 : $fieldTxt.hashCode());
        Object $fieldType = this.getFieldType();
        result = result * 59 + ($fieldType == null ? 43 : $fieldType.hashCode());
        Object $searchMode = this.getSearchMode();
        result = result * 59 + ($searchMode == null ? 43 : $searchMode.hashCode());
        Object $dictCode = this.getDictCode();
        result = result * 59 + ($dictCode == null ? 43 : $dictCode.hashCode());
        Object $fieldHref = this.getFieldHref();
        result = result * 59 + ($fieldHref == null ? 43 : $fieldHref.hashCode());
        Object $replaceVal = this.getReplaceVal();
        result = result * 59 + ($replaceVal == null ? 43 : $replaceVal.hashCode());
        Object $isTotal = this.getIsTotal();
        result = result * 59 + ($isTotal == null ? 43 : $isTotal.hashCode());
        Object $createBy = this.getCreateBy();
        result = result * 59 + ($createBy == null ? 43 : $createBy.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        Object $updateBy = this.getUpdateBy();
        result = result * 59 + ($updateBy == null ? 43 : $updateBy.hashCode());
        Object $updateTime = this.getUpdateTime();
        result = result * 59 + ($updateTime == null ? 43 : $updateTime.hashCode());
        Object $groupTitle = this.getGroupTitle();
        result = result * 59 + ($groupTitle == null ? 43 : $groupTitle.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "OnlCgreportItem(id=" + var10000 + ", cgrheadId=" + this.getCgrheadId() + ", fieldName=" + this.getFieldName() + ", fieldTxt=" + this.getFieldTxt() + ", fieldWidth=" + this.getFieldWidth() + ", fieldType=" + this.getFieldType() + ", searchMode=" + this.getSearchMode() + ", isOrder=" + this.getIsOrder() + ", isSearch=" + this.getIsSearch() + ", dictCode=" + this.getDictCode() + ", fieldHref=" + this.getFieldHref() + ", isShow=" + this.getIsShow() + ", orderNum=" + this.getOrderNum() + ", replaceVal=" + this.getReplaceVal() + ", isTotal=" + this.getIsTotal() + ", createBy=" + this.getCreateBy() + ", createTime=" + this.getCreateTime() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ", groupTitle=" + this.getGroupTitle() + ")";
    }
}
