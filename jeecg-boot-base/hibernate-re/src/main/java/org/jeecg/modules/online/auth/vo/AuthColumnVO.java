//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.vo;

import java.io.Serializable;
import lombok.Generated;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class AuthColumnVO implements Serializable {
    private static final long serialVersionUID = 5445993027926933917L;
    private String id;
    private String cgformId;
    private Integer type = 1;
    private String code;
    private String title;
    private Integer status;
    private boolean listShow;
    private boolean formShow;
    private boolean formEditable;
    private Integer isShowForm;
    private Integer isShowList;
    private String tableName;
    private String tableNameTxt;
    private int switchFlag;
    private Integer dbIsPersist;
    private Boolean isMain;
    private String dbType;
    private String fieldShowType;

    public AuthColumnVO() {
    }

    public AuthColumnVO(OnlCgformField field) {
        this.id = field.getId();
        this.cgformId = field.getCgformHeadId();
        this.code = field.getDbFieldName();
        this.title = field.getDbFieldTxt();
        this.type = 1;
        this.isShowForm = field.getIsShowForm();
        this.isShowList = field.getIsShowList();
        this.dbIsPersist = field.getDbIsPersist();
        this.dbType = field.getDbType();
        this.fieldShowType = field.getFieldShowType();
    }

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getCgformId() {
        return this.cgformId;
    }

    @Generated
    public Integer getType() {
        return this.type;
    }

    @Generated
    public String getCode() {
        return this.code;
    }

    @Generated
    public String getTitle() {
        return this.title;
    }

    @Generated
    public Integer getStatus() {
        return this.status;
    }

    @Generated
    public boolean isListShow() {
        return this.listShow;
    }

    @Generated
    public boolean isFormShow() {
        return this.formShow;
    }

    @Generated
    public boolean isFormEditable() {
        return this.formEditable;
    }

    @Generated
    public Integer getIsShowForm() {
        return this.isShowForm;
    }

    @Generated
    public Integer getIsShowList() {
        return this.isShowList;
    }

    @Generated
    public String getTableName() {
        return this.tableName;
    }

    @Generated
    public String getTableNameTxt() {
        return this.tableNameTxt;
    }

    @Generated
    public int getSwitchFlag() {
        return this.switchFlag;
    }

    @Generated
    public Integer getDbIsPersist() {
        return this.dbIsPersist;
    }

    @Generated
    public Boolean getIsMain() {
        return this.isMain;
    }

    @Generated
    public String getDbType() {
        return this.dbType;
    }

    @Generated
    public String getFieldShowType() {
        return this.fieldShowType;
    }

    @Generated
    public void setId(final String id) {
        this.id = id;
    }

    @Generated
    public void setCgformId(final String cgformId) {
        this.cgformId = cgformId;
    }

    @Generated
    public void setType(final Integer type) {
        this.type = type;
    }

    @Generated
    public void setCode(final String code) {
        this.code = code;
    }

    @Generated
    public void setTitle(final String title) {
        this.title = title;
    }

    @Generated
    public void setStatus(final Integer status) {
        this.status = status;
    }

    @Generated
    public void setListShow(final boolean listShow) {
        this.listShow = listShow;
    }

    @Generated
    public void setFormShow(final boolean formShow) {
        this.formShow = formShow;
    }

    @Generated
    public void setFormEditable(final boolean formEditable) {
        this.formEditable = formEditable;
    }

    @Generated
    public void setIsShowForm(final Integer isShowForm) {
        this.isShowForm = isShowForm;
    }

    @Generated
    public void setIsShowList(final Integer isShowList) {
        this.isShowList = isShowList;
    }

    @Generated
    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    @Generated
    public void setTableNameTxt(final String tableNameTxt) {
        this.tableNameTxt = tableNameTxt;
    }

    @Generated
    public void setSwitchFlag(final int switchFlag) {
        this.switchFlag = switchFlag;
    }

    @Generated
    public void setDbIsPersist(final Integer dbIsPersist) {
        this.dbIsPersist = dbIsPersist;
    }

    @Generated
    public void setIsMain(final Boolean isMain) {
        this.isMain = isMain;
    }

    @Generated
    public void setDbType(final String dbType) {
        this.dbType = dbType;
    }

    @Generated
    public void setFieldShowType(final String fieldShowType) {
        this.fieldShowType = fieldShowType;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AuthColumnVO)) {
            return false;
        } else {
            AuthColumnVO other = (AuthColumnVO)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isListShow() != other.isListShow()) {
                return false;
            } else if (this.isFormShow() != other.isFormShow()) {
                return false;
            } else if (this.isFormEditable() != other.isFormEditable()) {
                return false;
            } else if (this.getSwitchFlag() != other.getSwitchFlag()) {
                return false;
            } else {
                Object this$type = this.getType();
                Object other$type = other.getType();
                if (this$type == null) {
                    if (other$type != null) {
                        return false;
                    }
                } else if (!this$type.equals(other$type)) {
                    return false;
                }

                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                Object this$isShowForm = this.getIsShowForm();
                Object other$isShowForm = other.getIsShowForm();
                if (this$isShowForm == null) {
                    if (other$isShowForm != null) {
                        return false;
                    }
                } else if (!this$isShowForm.equals(other$isShowForm)) {
                    return false;
                }

                Object this$isShowList = this.getIsShowList();
                Object other$isShowList = other.getIsShowList();
                if (this$isShowList == null) {
                    if (other$isShowList != null) {
                        return false;
                    }
                } else if (!this$isShowList.equals(other$isShowList)) {
                    return false;
                }

                Object this$dbIsPersist = this.getDbIsPersist();
                Object other$dbIsPersist = other.getDbIsPersist();
                if (this$dbIsPersist == null) {
                    if (other$dbIsPersist != null) {
                        return false;
                    }
                } else if (!this$dbIsPersist.equals(other$dbIsPersist)) {
                    return false;
                }

                Object this$isMain = this.getIsMain();
                Object other$isMain = other.getIsMain();
                if (this$isMain == null) {
                    if (other$isMain != null) {
                        return false;
                    }
                } else if (!this$isMain.equals(other$isMain)) {
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

                Object this$code = this.getCode();
                Object other$code = other.getCode();
                if (this$code == null) {
                    if (other$code != null) {
                        return false;
                    }
                } else if (!this$code.equals(other$code)) {
                    return false;
                }

                Object this$title = this.getTitle();
                Object other$title = other.getTitle();
                if (this$title == null) {
                    if (other$title != null) {
                        return false;
                    }
                } else if (!this$title.equals(other$title)) {
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

                Object this$tableNameTxt = this.getTableNameTxt();
                Object other$tableNameTxt = other.getTableNameTxt();
                if (this$tableNameTxt == null) {
                    if (other$tableNameTxt != null) {
                        return false;
                    }
                } else if (!this$tableNameTxt.equals(other$tableNameTxt)) {
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

                Object this$fieldShowType = this.getFieldShowType();
                Object other$fieldShowType = other.getFieldShowType();
                if (this$fieldShowType == null) {
                    if (other$fieldShowType != null) {
                        return false;
                    }
                } else if (!this$fieldShowType.equals(other$fieldShowType)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof AuthColumnVO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.isListShow() ? 79 : 97);
        result = result * 59 + (this.isFormShow() ? 79 : 97);
        result = result * 59 + (this.isFormEditable() ? 79 : 97);
        result = result * 59 + this.getSwitchFlag();
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $isShowForm = this.getIsShowForm();
        result = result * 59 + ($isShowForm == null ? 43 : $isShowForm.hashCode());
        Object $isShowList = this.getIsShowList();
        result = result * 59 + ($isShowList == null ? 43 : $isShowList.hashCode());
        Object $dbIsPersist = this.getDbIsPersist();
        result = result * 59 + ($dbIsPersist == null ? 43 : $dbIsPersist.hashCode());
        Object $isMain = this.getIsMain();
        result = result * 59 + ($isMain == null ? 43 : $isMain.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $cgformId = this.getCgformId();
        result = result * 59 + ($cgformId == null ? 43 : $cgformId.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        Object $tableName = this.getTableName();
        result = result * 59 + ($tableName == null ? 43 : $tableName.hashCode());
        Object $tableNameTxt = this.getTableNameTxt();
        result = result * 59 + ($tableNameTxt == null ? 43 : $tableNameTxt.hashCode());
        Object $dbType = this.getDbType();
        result = result * 59 + ($dbType == null ? 43 : $dbType.hashCode());
        Object $fieldShowType = this.getFieldShowType();
        result = result * 59 + ($fieldShowType == null ? 43 : $fieldShowType.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "AuthColumnVO(id=" + var10000 + ", cgformId=" + this.getCgformId() + ", type=" + this.getType() + ", code=" + this.getCode() + ", title=" + this.getTitle() + ", status=" + this.getStatus() + ", listShow=" + this.isListShow() + ", formShow=" + this.isFormShow() + ", formEditable=" + this.isFormEditable() + ", isShowForm=" + this.getIsShowForm() + ", isShowList=" + this.getIsShowList() + ", tableName=" + this.getTableName() + ", tableNameTxt=" + this.getTableNameTxt() + ", switchFlag=" + this.getSwitchFlag() + ", dbIsPersist=" + this.getDbIsPersist() + ", isMain=" + this.getIsMain() + ", dbType=" + this.getDbType() + ", fieldShowType=" + this.getFieldShowType() + ")";
    }
}
