//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Generated;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.dird.j;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.model.ParamItemVo;

public class OnlineFieldConfig {
    private String name;
    private String type;
    private String view;
    private String mode;
    private String val;
    private String rule;
    private Integer isSearch;
    private String mainField;
    private String mainTable;
    private String table;

    public OnlineFieldConfig() {
    }

    public OnlineFieldConfig(JSONObject parameter) {
        String field = parameter.getString("field");
        String[] fieldArray = field.split(",");
        if (fieldArray.length == 1) {
            this.name = field;
        } else if (fieldArray.length == 2) {
            this.name = fieldArray[1];
            this.table = fieldArray[0];
        }

        String type = parameter.getString("type");
        String dbType = parameter.getString("dbType");
        if (oConvertUtils.isNotEmpty(dbType) && j.a(dbType)) {
            this.type = dbType;
        } else {
            this.type = type;
        }

        if ("list_multi".equals(type) || "popup".equals(type)) {
            this.view = type;
        }

        this.rule = parameter.getString("rule");
        this.val = parameter.getString("val");
        this.mode = "single";
        this.isSearch = 1;
    }

    public OnlineFieldConfig(OnlCgreportItem item) {
        this.name = item.getFieldName();
        this.type = item.getFieldType();
        this.mode = item.getSearchMode();
        this.isSearch = item.getIsSearch();
    }

    public OnlineFieldConfig(ParamItemVo item) {
        this.name = item.getFieldName();
        this.type = item.getFieldType();
        this.mode = item.getSearchMode();
        this.isSearch = 1;
    }

    public OnlineFieldConfig(OnlCgformField item) {
        this.name = item.getDbFieldName();
        this.type = item.getDbType();
        this.mode = item.getQueryMode();
        this.isSearch = item.getIsQuery();
        this.mainField = item.getMainField();
        this.mainTable = item.getMainTable();
        String queryConfigFlag = item.getQueryConfigFlag();
        if ("1".equals(queryConfigFlag)) {
            this.view = item.getQueryShowType();
        } else {
            this.view = item.getFieldShowType();
        }

    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getType() {
        return this.type;
    }

    @Generated
    public String getView() {
        return this.view;
    }

    @Generated
    public String getMode() {
        return this.mode;
    }

    @Generated
    public String getVal() {
        return this.val;
    }

    @Generated
    public String getRule() {
        return this.rule;
    }

    @Generated
    public Integer getIsSearch() {
        return this.isSearch;
    }

    @Generated
    public String getMainField() {
        return this.mainField;
    }

    @Generated
    public String getMainTable() {
        return this.mainTable;
    }

    @Generated
    public String getTable() {
        return this.table;
    }

    @Generated
    public void setName(final String name) {
        this.name = name;
    }

    @Generated
    public void setType(final String type) {
        this.type = type;
    }

    @Generated
    public void setView(final String view) {
        this.view = view;
    }

    @Generated
    public void setMode(final String mode) {
        this.mode = mode;
    }

    @Generated
    public void setVal(final String val) {
        this.val = val;
    }

    @Generated
    public void setRule(final String rule) {
        this.rule = rule;
    }

    @Generated
    public void setIsSearch(final Integer isSearch) {
        this.isSearch = isSearch;
    }

    @Generated
    public void setMainField(final String mainField) {
        this.mainField = mainField;
    }

    @Generated
    public void setMainTable(final String mainTable) {
        this.mainTable = mainTable;
    }

    @Generated
    public void setTable(final String table) {
        this.table = table;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OnlineFieldConfig)) {
            return false;
        } else {
            OnlineFieldConfig other = (OnlineFieldConfig)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$isSearch = this.getIsSearch();
                Object other$isSearch = other.getIsSearch();
                if (this$isSearch == null) {
                    if (other$isSearch != null) {
                        return false;
                    }
                } else if (!this$isSearch.equals(other$isSearch)) {
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

                Object this$type = this.getType();
                Object other$type = other.getType();
                if (this$type == null) {
                    if (other$type != null) {
                        return false;
                    }
                } else if (!this$type.equals(other$type)) {
                    return false;
                }

                Object this$view = this.getView();
                Object other$view = other.getView();
                if (this$view == null) {
                    if (other$view != null) {
                        return false;
                    }
                } else if (!this$view.equals(other$view)) {
                    return false;
                }

                Object this$mode = this.getMode();
                Object other$mode = other.getMode();
                if (this$mode == null) {
                    if (other$mode != null) {
                        return false;
                    }
                } else if (!this$mode.equals(other$mode)) {
                    return false;
                }

                Object this$val = this.getVal();
                Object other$val = other.getVal();
                if (this$val == null) {
                    if (other$val != null) {
                        return false;
                    }
                } else if (!this$val.equals(other$val)) {
                    return false;
                }

                Object this$rule = this.getRule();
                Object other$rule = other.getRule();
                if (this$rule == null) {
                    if (other$rule != null) {
                        return false;
                    }
                } else if (!this$rule.equals(other$rule)) {
                    return false;
                }

                Object this$mainField = this.getMainField();
                Object other$mainField = other.getMainField();
                if (this$mainField == null) {
                    if (other$mainField != null) {
                        return false;
                    }
                } else if (!this$mainField.equals(other$mainField)) {
                    return false;
                }

                Object this$mainTable = this.getMainTable();
                Object other$mainTable = other.getMainTable();
                if (this$mainTable == null) {
                    if (other$mainTable != null) {
                        return false;
                    }
                } else if (!this$mainTable.equals(other$mainTable)) {
                    return false;
                }

                Object this$table = this.getTable();
                Object other$table = other.getTable();
                if (this$table == null) {
                    if (other$table != null) {
                        return false;
                    }
                } else if (!this$table.equals(other$table)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof OnlineFieldConfig;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $isSearch = this.getIsSearch();
        result = result * 59 + ($isSearch == null ? 43 : $isSearch.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $view = this.getView();
        result = result * 59 + ($view == null ? 43 : $view.hashCode());
        Object $mode = this.getMode();
        result = result * 59 + ($mode == null ? 43 : $mode.hashCode());
        Object $val = this.getVal();
        result = result * 59 + ($val == null ? 43 : $val.hashCode());
        Object $rule = this.getRule();
        result = result * 59 + ($rule == null ? 43 : $rule.hashCode());
        Object $mainField = this.getMainField();
        result = result * 59 + ($mainField == null ? 43 : $mainField.hashCode());
        Object $mainTable = this.getMainTable();
        result = result * 59 + ($mainTable == null ? 43 : $mainTable.hashCode());
        Object $table = this.getTable();
        result = result * 59 + ($table == null ? 43 : $table.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getName();
        return "OnlineFieldConfig(name=" + var10000 + ", type=" + this.getType() + ", view=" + this.getView() + ", mode=" + this.getMode() + ", val=" + this.getVal() + ", rule=" + this.getRule() + ", isSearch=" + this.getIsSearch() + ", mainField=" + this.getMainField() + ", mainTable=" + this.getMainTable() + ", table=" + this.getTable() + ")";
    }
}
