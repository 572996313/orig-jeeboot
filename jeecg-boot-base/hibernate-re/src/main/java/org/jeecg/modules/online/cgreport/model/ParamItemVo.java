//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.model;

import com.alibaba.fastjson.JSONArray;
import java.util.Map;
import lombok.Generated;

public class ParamItemVo {
    private String fieldTxt;
    private String fieldName;
    private String fieldType;
    private String searchMode;
    private Object value;

    public void putMap(Map<String, Object> map) {
        if (this.value != null) {
            map.put(this.fieldName, this.value);
            boolean isGroup = "group".equals(this.searchMode);
            if (isGroup) {
                JSONArray arrayValue = (JSONArray)this.value;
                map.put(this.fieldName + "_begin", arrayValue.get(0));
                map.put(this.fieldName + "_end", arrayValue.get(1));
            }

        }
    }

    @Generated
    public String getFieldTxt() {
        return this.fieldTxt;
    }

    @Generated
    public String getFieldName() {
        return this.fieldName;
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
    public Object getValue() {
        return this.value;
    }

    @Generated
    public void setFieldTxt(final String fieldTxt) {
        this.fieldTxt = fieldTxt;
    }

    @Generated
    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
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
    public void setValue(final Object value) {
        this.value = value;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ParamItemVo)) {
            return false;
        } else {
            ParamItemVo other = (ParamItemVo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$fieldTxt = this.getFieldTxt();
                Object other$fieldTxt = other.getFieldTxt();
                if (this$fieldTxt == null) {
                    if (other$fieldTxt != null) {
                        return false;
                    }
                } else if (!this$fieldTxt.equals(other$fieldTxt)) {
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

                Object this$value = this.getValue();
                Object other$value = other.getValue();
                if (this$value == null) {
                    if (other$value != null) {
                        return false;
                    }
                } else if (!this$value.equals(other$value)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof ParamItemVo;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $fieldTxt = this.getFieldTxt();
        result = result * 59 + ($fieldTxt == null ? 43 : $fieldTxt.hashCode());
        Object $fieldName = this.getFieldName();
        result = result * 59 + ($fieldName == null ? 43 : $fieldName.hashCode());
        Object $fieldType = this.getFieldType();
        result = result * 59 + ($fieldType == null ? 43 : $fieldType.hashCode());
        Object $searchMode = this.getSearchMode();
        result = result * 59 + ($searchMode == null ? 43 : $searchMode.hashCode());
        Object $value = this.getValue();
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getFieldTxt();
        return "ParamItemVo(fieldTxt=" + var10000 + ", fieldName=" + this.getFieldName() + ", fieldType=" + this.getFieldType() + ", searchMode=" + this.getSearchMode() + ", value=" + this.getValue() + ")";
    }
}
