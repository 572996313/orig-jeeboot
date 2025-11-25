//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.model;

import lombok.Generated;

public class OnlColumn {
    private String title;
    private String dataIndex;
    private String align;
    private String fieldExtendJson;
    private String customRender;
    private g scopedSlots;
    private String hrefSlotName;
    private int showLength;
    private boolean sorter = false;
    private String linkField;
    private String tableName;
    private String dbType;
    private String fieldType;

    public OnlColumn() {
    }

    public OnlColumn(String title, String dataIndex) {
        this.align = "center";
        this.title = title;
        this.dataIndex = dataIndex;
    }

    @Generated
    public String getTitle() {
        return this.title;
    }

    @Generated
    public String getDataIndex() {
        return this.dataIndex;
    }

    @Generated
    public String getAlign() {
        return this.align;
    }

    @Generated
    public String getFieldExtendJson() {
        return this.fieldExtendJson;
    }

    @Generated
    public String getCustomRender() {
        return this.customRender;
    }

    @Generated
    public g getScopedSlots() {
        return this.scopedSlots;
    }

    @Generated
    public String getHrefSlotName() {
        return this.hrefSlotName;
    }

    @Generated
    public int getShowLength() {
        return this.showLength;
    }

    @Generated
    public boolean isSorter() {
        return this.sorter;
    }

    @Generated
    public String getLinkField() {
        return this.linkField;
    }

    @Generated
    public String getTableName() {
        return this.tableName;
    }

    @Generated
    public String getDbType() {
        return this.dbType;
    }

    @Generated
    public String getFieldType() {
        return this.fieldType;
    }

    @Generated
    public void setTitle(final String title) {
        this.title = title;
    }

    @Generated
    public void setDataIndex(final String dataIndex) {
        this.dataIndex = dataIndex;
    }

    @Generated
    public void setAlign(final String align) {
        this.align = align;
    }

    @Generated
    public void setFieldExtendJson(final String fieldExtendJson) {
        this.fieldExtendJson = fieldExtendJson;
    }

    @Generated
    public void setCustomRender(final String customRender) {
        this.customRender = customRender;
    }

    @Generated
    public void setScopedSlots(final g scopedSlots) {
        this.scopedSlots = scopedSlots;
    }

    @Generated
    public void setHrefSlotName(final String hrefSlotName) {
        this.hrefSlotName = hrefSlotName;
    }

    @Generated
    public void setShowLength(final int showLength) {
        this.showLength = showLength;
    }

    @Generated
    public void setSorter(final boolean sorter) {
        this.sorter = sorter;
    }

    @Generated
    public void setLinkField(final String linkField) {
        this.linkField = linkField;
    }

    @Generated
    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    @Generated
    public void setDbType(final String dbType) {
        this.dbType = dbType;
    }

    @Generated
    public void setFieldType(final String fieldType) {
        this.fieldType = fieldType;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OnlColumn)) {
            return false;
        } else {
            OnlColumn other = (OnlColumn)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getShowLength() != other.getShowLength()) {
                return false;
            } else if (this.isSorter() != other.isSorter()) {
                return false;
            } else {
                Object this$title = this.getTitle();
                Object other$title = other.getTitle();
                if (this$title == null) {
                    if (other$title != null) {
                        return false;
                    }
                } else if (!this$title.equals(other$title)) {
                    return false;
                }

                Object this$dataIndex = this.getDataIndex();
                Object other$dataIndex = other.getDataIndex();
                if (this$dataIndex == null) {
                    if (other$dataIndex != null) {
                        return false;
                    }
                } else if (!this$dataIndex.equals(other$dataIndex)) {
                    return false;
                }

                Object this$align = this.getAlign();
                Object other$align = other.getAlign();
                if (this$align == null) {
                    if (other$align != null) {
                        return false;
                    }
                } else if (!this$align.equals(other$align)) {
                    return false;
                }

                Object this$fieldExtendJson = this.getFieldExtendJson();
                Object other$fieldExtendJson = other.getFieldExtendJson();
                if (this$fieldExtendJson == null) {
                    if (other$fieldExtendJson != null) {
                        return false;
                    }
                } else if (!this$fieldExtendJson.equals(other$fieldExtendJson)) {
                    return false;
                }

                Object this$customRender = this.getCustomRender();
                Object other$customRender = other.getCustomRender();
                if (this$customRender == null) {
                    if (other$customRender != null) {
                        return false;
                    }
                } else if (!this$customRender.equals(other$customRender)) {
                    return false;
                }

                Object this$scopedSlots = this.getScopedSlots();
                Object other$scopedSlots = other.getScopedSlots();
                if (this$scopedSlots == null) {
                    if (other$scopedSlots != null) {
                        return false;
                    }
                } else if (!this$scopedSlots.equals(other$scopedSlots)) {
                    return false;
                }

                Object this$hrefSlotName = this.getHrefSlotName();
                Object other$hrefSlotName = other.getHrefSlotName();
                if (this$hrefSlotName == null) {
                    if (other$hrefSlotName != null) {
                        return false;
                    }
                } else if (!this$hrefSlotName.equals(other$hrefSlotName)) {
                    return false;
                }

                Object this$linkField = this.getLinkField();
                Object other$linkField = other.getLinkField();
                if (this$linkField == null) {
                    if (other$linkField != null) {
                        return false;
                    }
                } else if (!this$linkField.equals(other$linkField)) {
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

                Object this$dbType = this.getDbType();
                Object other$dbType = other.getDbType();
                if (this$dbType == null) {
                    if (other$dbType != null) {
                        return false;
                    }
                } else if (!this$dbType.equals(other$dbType)) {
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

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof OnlColumn;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getShowLength();
        result = result * 59 + (this.isSorter() ? 79 : 97);
        Object $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        Object $dataIndex = this.getDataIndex();
        result = result * 59 + ($dataIndex == null ? 43 : $dataIndex.hashCode());
        Object $align = this.getAlign();
        result = result * 59 + ($align == null ? 43 : $align.hashCode());
        Object $fieldExtendJson = this.getFieldExtendJson();
        result = result * 59 + ($fieldExtendJson == null ? 43 : $fieldExtendJson.hashCode());
        Object $customRender = this.getCustomRender();
        result = result * 59 + ($customRender == null ? 43 : $customRender.hashCode());
        Object $scopedSlots = this.getScopedSlots();
        result = result * 59 + ($scopedSlots == null ? 43 : $scopedSlots.hashCode());
        Object $hrefSlotName = this.getHrefSlotName();
        result = result * 59 + ($hrefSlotName == null ? 43 : $hrefSlotName.hashCode());
        Object $linkField = this.getLinkField();
        result = result * 59 + ($linkField == null ? 43 : $linkField.hashCode());
        Object $tableName = this.getTableName();
        result = result * 59 + ($tableName == null ? 43 : $tableName.hashCode());
        Object $dbType = this.getDbType();
        result = result * 59 + ($dbType == null ? 43 : $dbType.hashCode());
        Object $fieldType = this.getFieldType();
        result = result * 59 + ($fieldType == null ? 43 : $fieldType.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getTitle();
        return "OnlColumn(title=" + var10000 + ", dataIndex=" + this.getDataIndex() + ", align=" + this.getAlign() + ", fieldExtendJson=" + this.getFieldExtendJson() + ", customRender=" + this.getCustomRender() + ", scopedSlots=" + this.getScopedSlots() + ", hrefSlotName=" + this.getHrefSlotName() + ", showLength=" + this.getShowLength() + ", sorter=" + this.isSorter() + ", linkField=" + this.getLinkField() + ", tableName=" + this.getTableName() + ", dbType=" + this.getDbType() + ", fieldType=" + this.getFieldType() + ")";
    }
}
