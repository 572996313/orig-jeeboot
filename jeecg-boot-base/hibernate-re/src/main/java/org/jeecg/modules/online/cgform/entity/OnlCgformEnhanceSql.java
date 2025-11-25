//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Generated;

@TableName("onl_cgform_enhance_sql")
public class OnlCgformEnhanceSql implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(
        type = IdType.ASSIGN_UUID
    )
    private String id;
    private String cgformHeadId;
    private String buttonCode;
    private String cgbSql;
    private String cgbSqlName;
    private String content;

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getCgformHeadId() {
        return this.cgformHeadId;
    }

    @Generated
    public String getButtonCode() {
        return this.buttonCode;
    }

    @Generated
    public String getCgbSql() {
        return this.cgbSql;
    }

    @Generated
    public String getCgbSqlName() {
        return this.cgbSqlName;
    }

    @Generated
    public String getContent() {
        return this.content;
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
    public void setButtonCode(final String buttonCode) {
        this.buttonCode = buttonCode;
    }

    @Generated
    public void setCgbSql(final String cgbSql) {
        this.cgbSql = cgbSql;
    }

    @Generated
    public void setCgbSqlName(final String cgbSqlName) {
        this.cgbSqlName = cgbSqlName;
    }

    @Generated
    public void setContent(final String content) {
        this.content = content;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OnlCgformEnhanceSql)) {
            return false;
        } else {
            OnlCgformEnhanceSql other = (OnlCgformEnhanceSql)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
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

                Object this$buttonCode = this.getButtonCode();
                Object other$buttonCode = other.getButtonCode();
                if (this$buttonCode == null) {
                    if (other$buttonCode != null) {
                        return false;
                    }
                } else if (!this$buttonCode.equals(other$buttonCode)) {
                    return false;
                }

                Object this$cgbSql = this.getCgbSql();
                Object other$cgbSql = other.getCgbSql();
                if (this$cgbSql == null) {
                    if (other$cgbSql != null) {
                        return false;
                    }
                } else if (!this$cgbSql.equals(other$cgbSql)) {
                    return false;
                }

                Object this$cgbSqlName = this.getCgbSqlName();
                Object other$cgbSqlName = other.getCgbSqlName();
                if (this$cgbSqlName == null) {
                    if (other$cgbSqlName != null) {
                        return false;
                    }
                } else if (!this$cgbSqlName.equals(other$cgbSqlName)) {
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

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof OnlCgformEnhanceSql;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $cgformHeadId = this.getCgformHeadId();
        result = result * 59 + ($cgformHeadId == null ? 43 : $cgformHeadId.hashCode());
        Object $buttonCode = this.getButtonCode();
        result = result * 59 + ($buttonCode == null ? 43 : $buttonCode.hashCode());
        Object $cgbSql = this.getCgbSql();
        result = result * 59 + ($cgbSql == null ? 43 : $cgbSql.hashCode());
        Object $cgbSqlName = this.getCgbSqlName();
        result = result * 59 + ($cgbSqlName == null ? 43 : $cgbSqlName.hashCode());
        Object $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "OnlCgformEnhanceSql(id=" + var10000 + ", cgformHeadId=" + this.getCgformHeadId() + ", buttonCode=" + this.getButtonCode() + ", cgbSql=" + this.getCgbSql() + ", cgbSqlName=" + this.getCgbSqlName() + ", content=" + this.getContent() + ")";
    }
}
