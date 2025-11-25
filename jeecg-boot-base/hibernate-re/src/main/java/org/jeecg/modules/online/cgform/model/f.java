//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.model;

import java.util.List;
import lombok.Generated;
import org.jeecg.common.system.vo.SysPermissionDataRuleModel;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class f {
    private String a;
    private String b;
    private List<OnlCgformField> c;
    private List<OnlCgformField> d;
    private List<SysPermissionDataRuleModel> e;
    private String f;
    private String g;
    private String h;
    private boolean i;

    public void setAliasByIntValue(int index) {
        char c = (char)index;
        this.h = String.valueOf(c);
    }

    public String getTableAlias() {
        return this.h + ".";
    }

    public f() {
    }

    public f(String tableName, String tableId, boolean isMain) {
        this.a = tableName;
        this.b = tableId;
        this.i = isMain;
    }

    @Generated
    public String getTableName() {
        return this.a;
    }

    @Generated
    public String getTableId() {
        return this.b;
    }

    @Generated
    public List<OnlCgformField> getAllFieldList() {
        return this.c;
    }

    @Generated
    public List<OnlCgformField> getSelectFieldList() {
        return this.d;
    }

    @Generated
    public List<SysPermissionDataRuleModel> getAuthList() {
        return this.e;
    }

    @Generated
    public String getMainField() {
        return this.f;
    }

    @Generated
    public String getJoinField() {
        return this.g;
    }

    @Generated
    public String getAlias() {
        return this.h;
    }

    @Generated
    public boolean a() {
        return this.i;
    }

    @Generated
    public void setTableName(final String tableName) {
        this.a = tableName;
    }

    @Generated
    public void setTableId(final String tableId) {
        this.b = tableId;
    }

    @Generated
    public void setAllFieldList(final List<OnlCgformField> allFieldList) {
        this.c = allFieldList;
    }

    @Generated
    public void setSelectFieldList(final List<OnlCgformField> selectFieldList) {
        this.d = selectFieldList;
    }

    @Generated
    public void setAuthList(final List<SysPermissionDataRuleModel> authList) {
        this.e = authList;
    }

    @Generated
    public void setMainField(final String mainField) {
        this.f = mainField;
    }

    @Generated
    public void setJoinField(final String joinField) {
        this.g = joinField;
    }

    @Generated
    public void setAlias(final String alias) {
        this.h = alias;
    }

    @Generated
    public void setMain(final boolean isMain) {
        this.i = isMain;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof f)) {
            return false;
        } else {
            f other = (f)o;
            if (!other.a(this)) {
                return false;
            } else if (this.a() != other.a()) {
                return false;
            } else {
                Object this$tableName = this.getTableName();
                Object other$tableName = other.getTableName();
                if (this$tableName == null) {
                    if (other$tableName != null) {
                        return false;
                    }
                } else if (!this$tableName.equals(other$tableName)) {
                    return false;
                }

                Object this$tableId = this.getTableId();
                Object other$tableId = other.getTableId();
                if (this$tableId == null) {
                    if (other$tableId != null) {
                        return false;
                    }
                } else if (!this$tableId.equals(other$tableId)) {
                    return false;
                }

                Object this$allFieldList = this.getAllFieldList();
                Object other$allFieldList = other.getAllFieldList();
                if (this$allFieldList == null) {
                    if (other$allFieldList != null) {
                        return false;
                    }
                } else if (!this$allFieldList.equals(other$allFieldList)) {
                    return false;
                }

                Object this$selectFieldList = this.getSelectFieldList();
                Object other$selectFieldList = other.getSelectFieldList();
                if (this$selectFieldList == null) {
                    if (other$selectFieldList != null) {
                        return false;
                    }
                } else if (!this$selectFieldList.equals(other$selectFieldList)) {
                    return false;
                }

                Object this$authList = this.getAuthList();
                Object other$authList = other.getAuthList();
                if (this$authList == null) {
                    if (other$authList != null) {
                        return false;
                    }
                } else if (!this$authList.equals(other$authList)) {
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

                Object this$joinField = this.getJoinField();
                Object other$joinField = other.getJoinField();
                if (this$joinField == null) {
                    if (other$joinField != null) {
                        return false;
                    }
                } else if (!this$joinField.equals(other$joinField)) {
                    return false;
                }

                Object this$alias = this.getAlias();
                Object other$alias = other.getAlias();
                if (this$alias == null) {
                    if (other$alias != null) {
                        return false;
                    }
                } else if (!this$alias.equals(other$alias)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean a(final Object other) {
        return other instanceof f;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.a() ? 79 : 97);
        Object $tableName = this.getTableName();
        result = result * 59 + ($tableName == null ? 43 : $tableName.hashCode());
        Object $tableId = this.getTableId();
        result = result * 59 + ($tableId == null ? 43 : $tableId.hashCode());
        Object $allFieldList = this.getAllFieldList();
        result = result * 59 + ($allFieldList == null ? 43 : $allFieldList.hashCode());
        Object $selectFieldList = this.getSelectFieldList();
        result = result * 59 + ($selectFieldList == null ? 43 : $selectFieldList.hashCode());
        Object $authList = this.getAuthList();
        result = result * 59 + ($authList == null ? 43 : $authList.hashCode());
        Object $mainField = this.getMainField();
        result = result * 59 + ($mainField == null ? 43 : $mainField.hashCode());
        Object $joinField = this.getJoinField();
        result = result * 59 + ($joinField == null ? 43 : $joinField.hashCode());
        Object $alias = this.getAlias();
        result = result * 59 + ($alias == null ? 43 : $alias.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getTableName();
        return "OnlTable(tableName=" + var10000 + ", tableId=" + this.getTableId() + ", allFieldList=" + this.getAllFieldList() + ", selectFieldList=" + this.getSelectFieldList() + ", authList=" + this.getAuthList() + ", mainField=" + this.getMainField() + ", joinField=" + this.getJoinField() + ", alias=" + this.getAlias() + ", isMain=" + this.a() + ")";
    }
}
