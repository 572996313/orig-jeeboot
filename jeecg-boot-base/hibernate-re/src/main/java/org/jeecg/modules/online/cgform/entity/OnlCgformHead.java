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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Generated;
import org.springframework.format.annotation.DateTimeFormat;

@TableName("onl_cgform_head")
public class OnlCgformHead implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(
        type = IdType.ASSIGN_UUID
    )
    private String id;
    private String tableName;
    private Integer tableType;
    private Integer tableVersion;
    private String tableTxt;
    private String isCheckbox;
    private String isDbSynch;
    private String isPage;
    private String isTree;
    private String idSequence;
    private String idType;
    private String queryMode;
    private Integer relationType;
    private String subTableStr;
    private Integer tabOrderNum;
    private String treeParentIdField;
    private String treeIdField;
    private String treeFieldname;
    private String formCategory;
    private String formTemplate;
    private String themeTemplate;
    private String formTemplateMobile;
    private String extConfigJson;
    private String updateBy;
    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @DateTimeFormat(
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date updateTime;
    private String createBy;
    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @DateTimeFormat(
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;
    private Integer copyType;
    private Integer copyVersion;
    private String physicId;
    private transient Integer hascopy;
    private Integer scroll;
    private transient String taskId;
    private String isDesForm;
    private String desFormCode;
    private Integer tenantId;
    private String lowAppId;
    private transient String selectFieldString;

    public List<String> getSelectFieldList() {
        return this.selectFieldString != null && !"".equals(this.selectFieldString) ? Arrays.asList(this.selectFieldString.split(",")) : null;
    }

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getTableName() {
        return this.tableName;
    }

    @Generated
    public Integer getTableType() {
        return this.tableType;
    }

    @Generated
    public Integer getTableVersion() {
        return this.tableVersion;
    }

    @Generated
    public String getTableTxt() {
        return this.tableTxt;
    }

    @Generated
    public String getIsCheckbox() {
        return this.isCheckbox;
    }

    @Generated
    public String getIsDbSynch() {
        return this.isDbSynch;
    }

    @Generated
    public String getIsPage() {
        return this.isPage;
    }

    @Generated
    public String getIsTree() {
        return this.isTree;
    }

    @Generated
    public String getIdSequence() {
        return this.idSequence;
    }

    @Generated
    public String getIdType() {
        return this.idType;
    }

    @Generated
    public String getQueryMode() {
        return this.queryMode;
    }

    @Generated
    public Integer getRelationType() {
        return this.relationType;
    }

    @Generated
    public String getSubTableStr() {
        return this.subTableStr;
    }

    @Generated
    public Integer getTabOrderNum() {
        return this.tabOrderNum;
    }

    @Generated
    public String getTreeParentIdField() {
        return this.treeParentIdField;
    }

    @Generated
    public String getTreeIdField() {
        return this.treeIdField;
    }

    @Generated
    public String getTreeFieldname() {
        return this.treeFieldname;
    }

    @Generated
    public String getFormCategory() {
        return this.formCategory;
    }

    @Generated
    public String getFormTemplate() {
        return this.formTemplate;
    }

    @Generated
    public String getThemeTemplate() {
        return this.themeTemplate;
    }

    @Generated
    public String getFormTemplateMobile() {
        return this.formTemplateMobile;
    }

    @Generated
    public String getExtConfigJson() {
        return this.extConfigJson;
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
    public String getCreateBy() {
        return this.createBy;
    }

    @Generated
    public Date getCreateTime() {
        return this.createTime;
    }

    @Generated
    public Integer getCopyType() {
        return this.copyType;
    }

    @Generated
    public Integer getCopyVersion() {
        return this.copyVersion;
    }

    @Generated
    public String getPhysicId() {
        return this.physicId;
    }

    @Generated
    public Integer getHascopy() {
        return this.hascopy;
    }

    @Generated
    public Integer getScroll() {
        return this.scroll;
    }

    @Generated
    public String getTaskId() {
        return this.taskId;
    }

    @Generated
    public String getIsDesForm() {
        return this.isDesForm;
    }

    @Generated
    public String getDesFormCode() {
        return this.desFormCode;
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
    public String getSelectFieldString() {
        return this.selectFieldString;
    }

    @Generated
    public void setId(final String id) {
        this.id = id;
    }

    @Generated
    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    @Generated
    public void setTableType(final Integer tableType) {
        this.tableType = tableType;
    }

    @Generated
    public void setTableVersion(final Integer tableVersion) {
        this.tableVersion = tableVersion;
    }

    @Generated
    public void setTableTxt(final String tableTxt) {
        this.tableTxt = tableTxt;
    }

    @Generated
    public void setIsCheckbox(final String isCheckbox) {
        this.isCheckbox = isCheckbox;
    }

    @Generated
    public void setIsDbSynch(final String isDbSynch) {
        this.isDbSynch = isDbSynch;
    }

    @Generated
    public void setIsPage(final String isPage) {
        this.isPage = isPage;
    }

    @Generated
    public void setIsTree(final String isTree) {
        this.isTree = isTree;
    }

    @Generated
    public void setIdSequence(final String idSequence) {
        this.idSequence = idSequence;
    }

    @Generated
    public void setIdType(final String idType) {
        this.idType = idType;
    }

    @Generated
    public void setQueryMode(final String queryMode) {
        this.queryMode = queryMode;
    }

    @Generated
    public void setRelationType(final Integer relationType) {
        this.relationType = relationType;
    }

    @Generated
    public void setSubTableStr(final String subTableStr) {
        this.subTableStr = subTableStr;
    }

    @Generated
    public void setTabOrderNum(final Integer tabOrderNum) {
        this.tabOrderNum = tabOrderNum;
    }

    @Generated
    public void setTreeParentIdField(final String treeParentIdField) {
        this.treeParentIdField = treeParentIdField;
    }

    @Generated
    public void setTreeIdField(final String treeIdField) {
        this.treeIdField = treeIdField;
    }

    @Generated
    public void setTreeFieldname(final String treeFieldname) {
        this.treeFieldname = treeFieldname;
    }

    @Generated
    public void setFormCategory(final String formCategory) {
        this.formCategory = formCategory;
    }

    @Generated
    public void setFormTemplate(final String formTemplate) {
        this.formTemplate = formTemplate;
    }

    @Generated
    public void setThemeTemplate(final String themeTemplate) {
        this.themeTemplate = themeTemplate;
    }

    @Generated
    public void setFormTemplateMobile(final String formTemplateMobile) {
        this.formTemplateMobile = formTemplateMobile;
    }

    @Generated
    public void setExtConfigJson(final String extConfigJson) {
        this.extConfigJson = extConfigJson;
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
    public void setCopyType(final Integer copyType) {
        this.copyType = copyType;
    }

    @Generated
    public void setCopyVersion(final Integer copyVersion) {
        this.copyVersion = copyVersion;
    }

    @Generated
    public void setPhysicId(final String physicId) {
        this.physicId = physicId;
    }

    @Generated
    public void setHascopy(final Integer hascopy) {
        this.hascopy = hascopy;
    }

    @Generated
    public void setScroll(final Integer scroll) {
        this.scroll = scroll;
    }

    @Generated
    public void setTaskId(final String taskId) {
        this.taskId = taskId;
    }

    @Generated
    public void setIsDesForm(final String isDesForm) {
        this.isDesForm = isDesForm;
    }

    @Generated
    public void setDesFormCode(final String desFormCode) {
        this.desFormCode = desFormCode;
    }

    @Generated
    public void setTenantId(final Integer tenantId) {
        this.tenantId = tenantId;
    }

    @Generated
    public void setLowAppId(final String lowAppId) {
        this.lowAppId = lowAppId;
    }

    @Generated
    public void setSelectFieldString(final String selectFieldString) {
        this.selectFieldString = selectFieldString;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OnlCgformHead)) {
            return false;
        } else {
            OnlCgformHead other = (OnlCgformHead)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$tableType = this.getTableType();
                Object other$tableType = other.getTableType();
                if (this$tableType == null) {
                    if (other$tableType != null) {
                        return false;
                    }
                } else if (!this$tableType.equals(other$tableType)) {
                    return false;
                }

                Object this$tableVersion = this.getTableVersion();
                Object other$tableVersion = other.getTableVersion();
                if (this$tableVersion == null) {
                    if (other$tableVersion != null) {
                        return false;
                    }
                } else if (!this$tableVersion.equals(other$tableVersion)) {
                    return false;
                }

                Object this$relationType = this.getRelationType();
                Object other$relationType = other.getRelationType();
                if (this$relationType == null) {
                    if (other$relationType != null) {
                        return false;
                    }
                } else if (!this$relationType.equals(other$relationType)) {
                    return false;
                }

                Object this$tabOrderNum = this.getTabOrderNum();
                Object other$tabOrderNum = other.getTabOrderNum();
                if (this$tabOrderNum == null) {
                    if (other$tabOrderNum != null) {
                        return false;
                    }
                } else if (!this$tabOrderNum.equals(other$tabOrderNum)) {
                    return false;
                }

                Object this$copyType = this.getCopyType();
                Object other$copyType = other.getCopyType();
                if (this$copyType == null) {
                    if (other$copyType != null) {
                        return false;
                    }
                } else if (!this$copyType.equals(other$copyType)) {
                    return false;
                }

                Object this$copyVersion = this.getCopyVersion();
                Object other$copyVersion = other.getCopyVersion();
                if (this$copyVersion == null) {
                    if (other$copyVersion != null) {
                        return false;
                    }
                } else if (!this$copyVersion.equals(other$copyVersion)) {
                    return false;
                }

                Object this$scroll = this.getScroll();
                Object other$scroll = other.getScroll();
                if (this$scroll == null) {
                    if (other$scroll != null) {
                        return false;
                    }
                } else if (!this$scroll.equals(other$scroll)) {
                    return false;
                }

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

                Object this$tableName = this.getTableName();
                Object other$tableName = other.getTableName();
                if (this$tableName == null) {
                    if (other$tableName != null) {
                        return false;
                    }
                } else if (!this$tableName.equals(other$tableName)) {
                    return false;
                }

                Object this$tableTxt = this.getTableTxt();
                Object other$tableTxt = other.getTableTxt();
                if (this$tableTxt == null) {
                    if (other$tableTxt != null) {
                        return false;
                    }
                } else if (!this$tableTxt.equals(other$tableTxt)) {
                    return false;
                }

                Object this$isCheckbox = this.getIsCheckbox();
                Object other$isCheckbox = other.getIsCheckbox();
                if (this$isCheckbox == null) {
                    if (other$isCheckbox != null) {
                        return false;
                    }
                } else if (!this$isCheckbox.equals(other$isCheckbox)) {
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

                Object this$isPage = this.getIsPage();
                Object other$isPage = other.getIsPage();
                if (this$isPage == null) {
                    if (other$isPage != null) {
                        return false;
                    }
                } else if (!this$isPage.equals(other$isPage)) {
                    return false;
                }

                Object this$isTree = this.getIsTree();
                Object other$isTree = other.getIsTree();
                if (this$isTree == null) {
                    if (other$isTree != null) {
                        return false;
                    }
                } else if (!this$isTree.equals(other$isTree)) {
                    return false;
                }

                Object this$idSequence = this.getIdSequence();
                Object other$idSequence = other.getIdSequence();
                if (this$idSequence == null) {
                    if (other$idSequence != null) {
                        return false;
                    }
                } else if (!this$idSequence.equals(other$idSequence)) {
                    return false;
                }

                Object this$idType = this.getIdType();
                Object other$idType = other.getIdType();
                if (this$idType == null) {
                    if (other$idType != null) {
                        return false;
                    }
                } else if (!this$idType.equals(other$idType)) {
                    return false;
                }

                Object this$queryMode = this.getQueryMode();
                Object other$queryMode = other.getQueryMode();
                if (this$queryMode == null) {
                    if (other$queryMode != null) {
                        return false;
                    }
                } else if (!this$queryMode.equals(other$queryMode)) {
                    return false;
                }

                Object this$subTableStr = this.getSubTableStr();
                Object other$subTableStr = other.getSubTableStr();
                if (this$subTableStr == null) {
                    if (other$subTableStr != null) {
                        return false;
                    }
                } else if (!this$subTableStr.equals(other$subTableStr)) {
                    return false;
                }

                Object this$treeParentIdField = this.getTreeParentIdField();
                Object other$treeParentIdField = other.getTreeParentIdField();
                if (this$treeParentIdField == null) {
                    if (other$treeParentIdField != null) {
                        return false;
                    }
                } else if (!this$treeParentIdField.equals(other$treeParentIdField)) {
                    return false;
                }

                Object this$treeIdField = this.getTreeIdField();
                Object other$treeIdField = other.getTreeIdField();
                if (this$treeIdField == null) {
                    if (other$treeIdField != null) {
                        return false;
                    }
                } else if (!this$treeIdField.equals(other$treeIdField)) {
                    return false;
                }

                Object this$treeFieldname = this.getTreeFieldname();
                Object other$treeFieldname = other.getTreeFieldname();
                if (this$treeFieldname == null) {
                    if (other$treeFieldname != null) {
                        return false;
                    }
                } else if (!this$treeFieldname.equals(other$treeFieldname)) {
                    return false;
                }

                Object this$formCategory = this.getFormCategory();
                Object other$formCategory = other.getFormCategory();
                if (this$formCategory == null) {
                    if (other$formCategory != null) {
                        return false;
                    }
                } else if (!this$formCategory.equals(other$formCategory)) {
                    return false;
                }

                Object this$formTemplate = this.getFormTemplate();
                Object other$formTemplate = other.getFormTemplate();
                if (this$formTemplate == null) {
                    if (other$formTemplate != null) {
                        return false;
                    }
                } else if (!this$formTemplate.equals(other$formTemplate)) {
                    return false;
                }

                Object this$themeTemplate = this.getThemeTemplate();
                Object other$themeTemplate = other.getThemeTemplate();
                if (this$themeTemplate == null) {
                    if (other$themeTemplate != null) {
                        return false;
                    }
                } else if (!this$themeTemplate.equals(other$themeTemplate)) {
                    return false;
                }

                Object this$formTemplateMobile = this.getFormTemplateMobile();
                Object other$formTemplateMobile = other.getFormTemplateMobile();
                if (this$formTemplateMobile == null) {
                    if (other$formTemplateMobile != null) {
                        return false;
                    }
                } else if (!this$formTemplateMobile.equals(other$formTemplateMobile)) {
                    return false;
                }

                Object this$extConfigJson = this.getExtConfigJson();
                Object other$extConfigJson = other.getExtConfigJson();
                if (this$extConfigJson == null) {
                    if (other$extConfigJson != null) {
                        return false;
                    }
                } else if (!this$extConfigJson.equals(other$extConfigJson)) {
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

                Object this$physicId = this.getPhysicId();
                Object other$physicId = other.getPhysicId();
                if (this$physicId == null) {
                    if (other$physicId != null) {
                        return false;
                    }
                } else if (!this$physicId.equals(other$physicId)) {
                    return false;
                }

                Object this$isDesForm = this.getIsDesForm();
                Object other$isDesForm = other.getIsDesForm();
                if (this$isDesForm == null) {
                    if (other$isDesForm != null) {
                        return false;
                    }
                } else if (!this$isDesForm.equals(other$isDesForm)) {
                    return false;
                }

                Object this$desFormCode = this.getDesFormCode();
                Object other$desFormCode = other.getDesFormCode();
                if (this$desFormCode == null) {
                    if (other$desFormCode != null) {
                        return false;
                    }
                } else if (!this$desFormCode.equals(other$desFormCode)) {
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

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof OnlCgformHead;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $tableType = this.getTableType();
        result = result * 59 + ($tableType == null ? 43 : $tableType.hashCode());
        Object $tableVersion = this.getTableVersion();
        result = result * 59 + ($tableVersion == null ? 43 : $tableVersion.hashCode());
        Object $relationType = this.getRelationType();
        result = result * 59 + ($relationType == null ? 43 : $relationType.hashCode());
        Object $tabOrderNum = this.getTabOrderNum();
        result = result * 59 + ($tabOrderNum == null ? 43 : $tabOrderNum.hashCode());
        Object $copyType = this.getCopyType();
        result = result * 59 + ($copyType == null ? 43 : $copyType.hashCode());
        Object $copyVersion = this.getCopyVersion();
        result = result * 59 + ($copyVersion == null ? 43 : $copyVersion.hashCode());
        Object $scroll = this.getScroll();
        result = result * 59 + ($scroll == null ? 43 : $scroll.hashCode());
        Object $tenantId = this.getTenantId();
        result = result * 59 + ($tenantId == null ? 43 : $tenantId.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $tableName = this.getTableName();
        result = result * 59 + ($tableName == null ? 43 : $tableName.hashCode());
        Object $tableTxt = this.getTableTxt();
        result = result * 59 + ($tableTxt == null ? 43 : $tableTxt.hashCode());
        Object $isCheckbox = this.getIsCheckbox();
        result = result * 59 + ($isCheckbox == null ? 43 : $isCheckbox.hashCode());
        Object $isDbSynch = this.getIsDbSynch();
        result = result * 59 + ($isDbSynch == null ? 43 : $isDbSynch.hashCode());
        Object $isPage = this.getIsPage();
        result = result * 59 + ($isPage == null ? 43 : $isPage.hashCode());
        Object $isTree = this.getIsTree();
        result = result * 59 + ($isTree == null ? 43 : $isTree.hashCode());
        Object $idSequence = this.getIdSequence();
        result = result * 59 + ($idSequence == null ? 43 : $idSequence.hashCode());
        Object $idType = this.getIdType();
        result = result * 59 + ($idType == null ? 43 : $idType.hashCode());
        Object $queryMode = this.getQueryMode();
        result = result * 59 + ($queryMode == null ? 43 : $queryMode.hashCode());
        Object $subTableStr = this.getSubTableStr();
        result = result * 59 + ($subTableStr == null ? 43 : $subTableStr.hashCode());
        Object $treeParentIdField = this.getTreeParentIdField();
        result = result * 59 + ($treeParentIdField == null ? 43 : $treeParentIdField.hashCode());
        Object $treeIdField = this.getTreeIdField();
        result = result * 59 + ($treeIdField == null ? 43 : $treeIdField.hashCode());
        Object $treeFieldname = this.getTreeFieldname();
        result = result * 59 + ($treeFieldname == null ? 43 : $treeFieldname.hashCode());
        Object $formCategory = this.getFormCategory();
        result = result * 59 + ($formCategory == null ? 43 : $formCategory.hashCode());
        Object $formTemplate = this.getFormTemplate();
        result = result * 59 + ($formTemplate == null ? 43 : $formTemplate.hashCode());
        Object $themeTemplate = this.getThemeTemplate();
        result = result * 59 + ($themeTemplate == null ? 43 : $themeTemplate.hashCode());
        Object $formTemplateMobile = this.getFormTemplateMobile();
        result = result * 59 + ($formTemplateMobile == null ? 43 : $formTemplateMobile.hashCode());
        Object $extConfigJson = this.getExtConfigJson();
        result = result * 59 + ($extConfigJson == null ? 43 : $extConfigJson.hashCode());
        Object $updateBy = this.getUpdateBy();
        result = result * 59 + ($updateBy == null ? 43 : $updateBy.hashCode());
        Object $updateTime = this.getUpdateTime();
        result = result * 59 + ($updateTime == null ? 43 : $updateTime.hashCode());
        Object $createBy = this.getCreateBy();
        result = result * 59 + ($createBy == null ? 43 : $createBy.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        Object $physicId = this.getPhysicId();
        result = result * 59 + ($physicId == null ? 43 : $physicId.hashCode());
        Object $isDesForm = this.getIsDesForm();
        result = result * 59 + ($isDesForm == null ? 43 : $isDesForm.hashCode());
        Object $desFormCode = this.getDesFormCode();
        result = result * 59 + ($desFormCode == null ? 43 : $desFormCode.hashCode());
        Object $lowAppId = this.getLowAppId();
        result = result * 59 + ($lowAppId == null ? 43 : $lowAppId.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "OnlCgformHead(id=" + var10000 + ", tableName=" + this.getTableName() + ", tableType=" + this.getTableType() + ", tableVersion=" + this.getTableVersion() + ", tableTxt=" + this.getTableTxt() + ", isCheckbox=" + this.getIsCheckbox() + ", isDbSynch=" + this.getIsDbSynch() + ", isPage=" + this.getIsPage() + ", isTree=" + this.getIsTree() + ", idSequence=" + this.getIdSequence() + ", idType=" + this.getIdType() + ", queryMode=" + this.getQueryMode() + ", relationType=" + this.getRelationType() + ", subTableStr=" + this.getSubTableStr() + ", tabOrderNum=" + this.getTabOrderNum() + ", treeParentIdField=" + this.getTreeParentIdField() + ", treeIdField=" + this.getTreeIdField() + ", treeFieldname=" + this.getTreeFieldname() + ", formCategory=" + this.getFormCategory() + ", formTemplate=" + this.getFormTemplate() + ", themeTemplate=" + this.getThemeTemplate() + ", formTemplateMobile=" + this.getFormTemplateMobile() + ", extConfigJson=" + this.getExtConfigJson() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ", createBy=" + this.getCreateBy() + ", createTime=" + this.getCreateTime() + ", copyType=" + this.getCopyType() + ", copyVersion=" + this.getCopyVersion() + ", physicId=" + this.getPhysicId() + ", hascopy=" + this.getHascopy() + ", scroll=" + this.getScroll() + ", taskId=" + this.getTaskId() + ", isDesForm=" + this.getIsDesForm() + ", desFormCode=" + this.getDesFormCode() + ", tenantId=" + this.getTenantId() + ", lowAppId=" + this.getLowAppId() + ", selectFieldString=" + this.getSelectFieldString() + ")";
    }
}
