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

@TableName("onl_cgform_enhance_js")
public class OnlCgformEnhanceJs implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(
        type = IdType.ASSIGN_UUID
    )
    private String id;
    private String cgformHeadId;
    private String cgJsType;
    private String cgJs;
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
    public String getCgJsType() {
        return this.cgJsType;
    }

    @Generated
    public String getCgJs() {
        return this.cgJs;
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
    public void setCgJsType(final String cgJsType) {
        this.cgJsType = cgJsType;
    }

    @Generated
    public void setCgJs(final String cgJs) {
        this.cgJs = cgJs;
    }

    @Generated
    public void setContent(final String content) {
        this.content = content;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OnlCgformEnhanceJs)) {
            return false;
        } else {
            OnlCgformEnhanceJs other = (OnlCgformEnhanceJs)o;
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

                Object this$cgJsType = this.getCgJsType();
                Object other$cgJsType = other.getCgJsType();
                if (this$cgJsType == null) {
                    if (other$cgJsType != null) {
                        return false;
                    }
                } else if (!this$cgJsType.equals(other$cgJsType)) {
                    return false;
                }

                Object this$cgJs = this.getCgJs();
                Object other$cgJs = other.getCgJs();
                if (this$cgJs == null) {
                    if (other$cgJs != null) {
                        return false;
                    }
                } else if (!this$cgJs.equals(other$cgJs)) {
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
        return other instanceof OnlCgformEnhanceJs;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $cgformHeadId = this.getCgformHeadId();
        result = result * 59 + ($cgformHeadId == null ? 43 : $cgformHeadId.hashCode());
        Object $cgJsType = this.getCgJsType();
        result = result * 59 + ($cgJsType == null ? 43 : $cgJsType.hashCode());
        Object $cgJs = this.getCgJs();
        result = result * 59 + ($cgJs == null ? 43 : $cgJs.hashCode());
        Object $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "OnlCgformEnhanceJs(id=" + var10000 + ", cgformHeadId=" + this.getCgformHeadId() + ", cgJsType=" + this.getCgJsType() + ", cgJs=" + this.getCgJs() + ", content=" + this.getContent() + ")";
    }
}
