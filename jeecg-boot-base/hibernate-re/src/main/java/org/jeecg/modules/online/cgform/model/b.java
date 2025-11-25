//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;

public class b implements Serializable {
    private static final long b = 1L;
    private String c;
    private String d;
    private String e;
    private String f;
    private Integer g;
    private String h;
    private String i;
    private Integer j;
    private List<OnlColumn> k;
    private List<String> l;
    private Map<String, List<DictModel>> m = new HashMap();
    private List<OnlCgformButton> n;
    List<HrefSlots> a;
    private String o;
    private List<c> p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private Integer v;

    @Generated
    public String getCode() {
        return this.c;
    }

    @Generated
    public String getFormTemplate() {
        return this.d;
    }

    @Generated
    public String getDescription() {
        return this.e;
    }

    @Generated
    public String getCurrentTableName() {
        return this.f;
    }

    @Generated
    public Integer getTableType() {
        return this.g;
    }

    @Generated
    public String getPaginationFlag() {
        return this.h;
    }

    @Generated
    public String getCheckboxFlag() {
        return this.i;
    }

    @Generated
    public Integer getScrollFlag() {
        return this.j;
    }

    @Generated
    public List<OnlColumn> getColumns() {
        return this.k;
    }

    @Generated
    public List<String> getHideColumns() {
        return this.l;
    }

    @Generated
    public Map<String, List<DictModel>> getDictOptions() {
        return this.m;
    }

    @Generated
    public List<OnlCgformButton> getCgButtonList() {
        return this.n;
    }

    @Generated
    public List<HrefSlots> getFieldHrefSlots() {
        return this.a;
    }

    @Generated
    public String getEnhanceJs() {
        return this.o;
    }

    @Generated
    public List<c> getForeignKeys() {
        return this.p;
    }

    @Generated
    public String getPidField() {
        return this.q;
    }

    @Generated
    public String getHasChildrenField() {
        return this.r;
    }

    @Generated
    public String getTextField() {
        return this.s;
    }

    @Generated
    public String getIsDesForm() {
        return this.t;
    }

    @Generated
    public String getDesFormCode() {
        return this.u;
    }

    @Generated
    public Integer getRelationType() {
        return this.v;
    }

    @Generated
    public void setCode(final String code) {
        this.c = code;
    }

    @Generated
    public void setFormTemplate(final String formTemplate) {
        this.d = formTemplate;
    }

    @Generated
    public void setDescription(final String description) {
        this.e = description;
    }

    @Generated
    public void setCurrentTableName(final String currentTableName) {
        this.f = currentTableName;
    }

    @Generated
    public void setTableType(final Integer tableType) {
        this.g = tableType;
    }

    @Generated
    public void setPaginationFlag(final String paginationFlag) {
        this.h = paginationFlag;
    }

    @Generated
    public void setCheckboxFlag(final String checkboxFlag) {
        this.i = checkboxFlag;
    }

    @Generated
    public void setScrollFlag(final Integer scrollFlag) {
        this.j = scrollFlag;
    }

    @Generated
    public void setColumns(final List<OnlColumn> columns) {
        this.k = columns;
    }

    @Generated
    public void setHideColumns(final List<String> hideColumns) {
        this.l = hideColumns;
    }

    @Generated
    public void setDictOptions(final Map<String, List<DictModel>> dictOptions) {
        this.m = dictOptions;
    }

    @Generated
    public void setCgButtonList(final List<OnlCgformButton> cgButtonList) {
        this.n = cgButtonList;
    }

    @Generated
    public void setFieldHrefSlots(final List<HrefSlots> fieldHrefSlots) {
        this.a = fieldHrefSlots;
    }

    @Generated
    public void setEnhanceJs(final String enhanceJs) {
        this.o = enhanceJs;
    }

    @Generated
    public void setForeignKeys(final List<c> foreignKeys) {
        this.p = foreignKeys;
    }

    @Generated
    public void setPidField(final String pidField) {
        this.q = pidField;
    }

    @Generated
    public void setHasChildrenField(final String hasChildrenField) {
        this.r = hasChildrenField;
    }

    @Generated
    public void setTextField(final String textField) {
        this.s = textField;
    }

    @Generated
    public void setIsDesForm(final String isDesForm) {
        this.t = isDesForm;
    }

    @Generated
    public void setDesFormCode(final String desFormCode) {
        this.u = desFormCode;
    }

    @Generated
    public void setRelationType(final Integer relationType) {
        this.v = relationType;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof b)) {
            return false;
        } else {
            b other = (b)o;
            if (!other.a(this)) {
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

                Object this$scrollFlag = this.getScrollFlag();
                Object other$scrollFlag = other.getScrollFlag();
                if (this$scrollFlag == null) {
                    if (other$scrollFlag != null) {
                        return false;
                    }
                } else if (!this$scrollFlag.equals(other$scrollFlag)) {
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

                Object this$code = this.getCode();
                Object other$code = other.getCode();
                if (this$code == null) {
                    if (other$code != null) {
                        return false;
                    }
                } else if (!this$code.equals(other$code)) {
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

                Object this$description = this.getDescription();
                Object other$description = other.getDescription();
                if (this$description == null) {
                    if (other$description != null) {
                        return false;
                    }
                } else if (!this$description.equals(other$description)) {
                    return false;
                }

                Object this$currentTableName = this.getCurrentTableName();
                Object other$currentTableName = other.getCurrentTableName();
                if (this$currentTableName == null) {
                    if (other$currentTableName != null) {
                        return false;
                    }
                } else if (!this$currentTableName.equals(other$currentTableName)) {
                    return false;
                }

                Object this$paginationFlag = this.getPaginationFlag();
                Object other$paginationFlag = other.getPaginationFlag();
                if (this$paginationFlag == null) {
                    if (other$paginationFlag != null) {
                        return false;
                    }
                } else if (!this$paginationFlag.equals(other$paginationFlag)) {
                    return false;
                }

                Object this$checkboxFlag = this.getCheckboxFlag();
                Object other$checkboxFlag = other.getCheckboxFlag();
                if (this$checkboxFlag == null) {
                    if (other$checkboxFlag != null) {
                        return false;
                    }
                } else if (!this$checkboxFlag.equals(other$checkboxFlag)) {
                    return false;
                }

                Object this$columns = this.getColumns();
                Object other$columns = other.getColumns();
                if (this$columns == null) {
                    if (other$columns != null) {
                        return false;
                    }
                } else if (!this$columns.equals(other$columns)) {
                    return false;
                }

                Object this$hideColumns = this.getHideColumns();
                Object other$hideColumns = other.getHideColumns();
                if (this$hideColumns == null) {
                    if (other$hideColumns != null) {
                        return false;
                    }
                } else if (!this$hideColumns.equals(other$hideColumns)) {
                    return false;
                }

                Object this$dictOptions = this.getDictOptions();
                Object other$dictOptions = other.getDictOptions();
                if (this$dictOptions == null) {
                    if (other$dictOptions != null) {
                        return false;
                    }
                } else if (!this$dictOptions.equals(other$dictOptions)) {
                    return false;
                }

                Object this$cgButtonList = this.getCgButtonList();
                Object other$cgButtonList = other.getCgButtonList();
                if (this$cgButtonList == null) {
                    if (other$cgButtonList != null) {
                        return false;
                    }
                } else if (!this$cgButtonList.equals(other$cgButtonList)) {
                    return false;
                }

                Object this$fieldHrefSlots = this.getFieldHrefSlots();
                Object other$fieldHrefSlots = other.getFieldHrefSlots();
                if (this$fieldHrefSlots == null) {
                    if (other$fieldHrefSlots != null) {
                        return false;
                    }
                } else if (!this$fieldHrefSlots.equals(other$fieldHrefSlots)) {
                    return false;
                }

                Object this$enhanceJs = this.getEnhanceJs();
                Object other$enhanceJs = other.getEnhanceJs();
                if (this$enhanceJs == null) {
                    if (other$enhanceJs != null) {
                        return false;
                    }
                } else if (!this$enhanceJs.equals(other$enhanceJs)) {
                    return false;
                }

                Object this$foreignKeys = this.getForeignKeys();
                Object other$foreignKeys = other.getForeignKeys();
                if (this$foreignKeys == null) {
                    if (other$foreignKeys != null) {
                        return false;
                    }
                } else if (!this$foreignKeys.equals(other$foreignKeys)) {
                    return false;
                }

                Object this$pidField = this.getPidField();
                Object other$pidField = other.getPidField();
                if (this$pidField == null) {
                    if (other$pidField != null) {
                        return false;
                    }
                } else if (!this$pidField.equals(other$pidField)) {
                    return false;
                }

                Object this$hasChildrenField = this.getHasChildrenField();
                Object other$hasChildrenField = other.getHasChildrenField();
                if (this$hasChildrenField == null) {
                    if (other$hasChildrenField != null) {
                        return false;
                    }
                } else if (!this$hasChildrenField.equals(other$hasChildrenField)) {
                    return false;
                }

                Object this$textField = this.getTextField();
                Object other$textField = other.getTextField();
                if (this$textField == null) {
                    if (other$textField != null) {
                        return false;
                    }
                } else if (!this$textField.equals(other$textField)) {
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

                return true;
            }
        }
    }

    @Generated
    protected boolean a(final Object other) {
        return other instanceof b;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $tableType = this.getTableType();
        result = result * 59 + ($tableType == null ? 43 : $tableType.hashCode());
        Object $scrollFlag = this.getScrollFlag();
        result = result * 59 + ($scrollFlag == null ? 43 : $scrollFlag.hashCode());
        Object $relationType = this.getRelationType();
        result = result * 59 + ($relationType == null ? 43 : $relationType.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $formTemplate = this.getFormTemplate();
        result = result * 59 + ($formTemplate == null ? 43 : $formTemplate.hashCode());
        Object $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        Object $currentTableName = this.getCurrentTableName();
        result = result * 59 + ($currentTableName == null ? 43 : $currentTableName.hashCode());
        Object $paginationFlag = this.getPaginationFlag();
        result = result * 59 + ($paginationFlag == null ? 43 : $paginationFlag.hashCode());
        Object $checkboxFlag = this.getCheckboxFlag();
        result = result * 59 + ($checkboxFlag == null ? 43 : $checkboxFlag.hashCode());
        Object $columns = this.getColumns();
        result = result * 59 + ($columns == null ? 43 : $columns.hashCode());
        Object $hideColumns = this.getHideColumns();
        result = result * 59 + ($hideColumns == null ? 43 : $hideColumns.hashCode());
        Object $dictOptions = this.getDictOptions();
        result = result * 59 + ($dictOptions == null ? 43 : $dictOptions.hashCode());
        Object $cgButtonList = this.getCgButtonList();
        result = result * 59 + ($cgButtonList == null ? 43 : $cgButtonList.hashCode());
        Object $fieldHrefSlots = this.getFieldHrefSlots();
        result = result * 59 + ($fieldHrefSlots == null ? 43 : $fieldHrefSlots.hashCode());
        Object $enhanceJs = this.getEnhanceJs();
        result = result * 59 + ($enhanceJs == null ? 43 : $enhanceJs.hashCode());
        Object $foreignKeys = this.getForeignKeys();
        result = result * 59 + ($foreignKeys == null ? 43 : $foreignKeys.hashCode());
        Object $pidField = this.getPidField();
        result = result * 59 + ($pidField == null ? 43 : $pidField.hashCode());
        Object $hasChildrenField = this.getHasChildrenField();
        result = result * 59 + ($hasChildrenField == null ? 43 : $hasChildrenField.hashCode());
        Object $textField = this.getTextField();
        result = result * 59 + ($textField == null ? 43 : $textField.hashCode());
        Object $isDesForm = this.getIsDesForm();
        result = result * 59 + ($isDesForm == null ? 43 : $isDesForm.hashCode());
        Object $desFormCode = this.getDesFormCode();
        result = result * 59 + ($desFormCode == null ? 43 : $desFormCode.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getCode();
        return "OnlComplexModel(code=" + var10000 + ", formTemplate=" + this.getFormTemplate() + ", description=" + this.getDescription() + ", currentTableName=" + this.getCurrentTableName() + ", tableType=" + this.getTableType() + ", paginationFlag=" + this.getPaginationFlag() + ", checkboxFlag=" + this.getCheckboxFlag() + ", scrollFlag=" + this.getScrollFlag() + ", columns=" + this.getColumns() + ", hideColumns=" + this.getHideColumns() + ", dictOptions=" + this.getDictOptions() + ", cgButtonList=" + this.getCgButtonList() + ", fieldHrefSlots=" + this.getFieldHrefSlots() + ", enhanceJs=" + this.getEnhanceJs() + ", foreignKeys=" + this.getForeignKeys() + ", pidField=" + this.getPidField() + ", hasChildrenField=" + this.getHasChildrenField() + ", textField=" + this.getTextField() + ", isDesForm=" + this.getIsDesForm() + ", desFormCode=" + this.getDesFormCode() + ", relationType=" + this.getRelationType() + ")";
    }
}
