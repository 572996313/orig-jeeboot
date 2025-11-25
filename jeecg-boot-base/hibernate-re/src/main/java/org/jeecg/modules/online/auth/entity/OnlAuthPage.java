//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.Generated;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

@TableName("onl_auth_page")
@Schema(
    description = "onl_auth_page"
)
public class OnlAuthPage implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(
        type = IdType.ASSIGN_ID
    )
    @Schema(
        description = " 主键"
    )
    private String id;
    @Excel(
        name = "online表id",
        width = (double)15.0F
    )
    @Schema(
        description = "online表id"
    )
    private String cgformId;
    @Excel(
        name = "字段名/按钮编码",
        width = (double)15.0F
    )
    @Schema(
        description = "字段名/按钮编码"
    )
    private String code;
    @Excel(
        name = "1字段 2按钮",
        width = (double)15.0F
    )
    @Schema(
        description = "1字段 2按钮"
    )
    private Integer type;
    @Excel(
        name = "3可编辑 5可见",
        width = (double)15.0F
    )
    @Schema(
        description = "3可编辑 5可见"
    )
    private Integer control;
    @Excel(
        name = "3列表 5表单",
        width = (double)15.0F
    )
    @Schema(
        description = "3列表 5表单"
    )
    private Integer page;
    @Excel(
        name = "1有效 0无效",
        width = (double)15.0F
    )
    @Schema(
        description = "1有效 0无效"
    )
    private Integer status;
    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd"
    )
    @DateTimeFormat(
        pattern = "yyyy-MM-dd"
    )
    @Schema(
        description = "创建时间"
    )
    @JsonIgnore
    private Date createTime;
    @Schema(
        description = "创建人"
    )
    @JsonIgnore
    private String createBy;
    @Schema(
        description = "更新人"
    )
    @JsonIgnore
    private String updateBy;
    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd"
    )
    @DateTimeFormat(
        pattern = "yyyy-MM-dd"
    )
    @Schema(
        description = "更新日期"
    )
    @JsonIgnore
    private Date updateTime;

    public OnlAuthPage() {
    }

    public OnlAuthPage(String cgformId, String code, int page, int control) {
        this.type = 1;
        this.cgformId = cgformId;
        this.code = code;
        this.control = control;
        this.page = page;
        this.status = 1;
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
    public String getCode() {
        return this.code;
    }

    @Generated
    public Integer getType() {
        return this.type;
    }

    @Generated
    public Integer getControl() {
        return this.control;
    }

    @Generated
    public Integer getPage() {
        return this.page;
    }

    @Generated
    public Integer getStatus() {
        return this.status;
    }

    @Generated
    public Date getCreateTime() {
        return this.createTime;
    }

    @Generated
    public String getCreateBy() {
        return this.createBy;
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
    public OnlAuthPage setId(final String id) {
        this.id = id;
        return this;
    }

    @Generated
    public OnlAuthPage setCgformId(final String cgformId) {
        this.cgformId = cgformId;
        return this;
    }

    @Generated
    public OnlAuthPage setCode(final String code) {
        this.code = code;
        return this;
    }

    @Generated
    public OnlAuthPage setType(final Integer type) {
        this.type = type;
        return this;
    }

    @Generated
    public OnlAuthPage setControl(final Integer control) {
        this.control = control;
        return this;
    }

    @Generated
    public OnlAuthPage setPage(final Integer page) {
        this.page = page;
        return this;
    }

    @Generated
    public OnlAuthPage setStatus(final Integer status) {
        this.status = status;
        return this;
    }

    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd"
    )
    @JsonIgnore
    @Generated
    public OnlAuthPage setCreateTime(final Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @JsonIgnore
    @Generated
    public OnlAuthPage setCreateBy(final String createBy) {
        this.createBy = createBy;
        return this;
    }

    @JsonIgnore
    @Generated
    public OnlAuthPage setUpdateBy(final String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    @JsonFormat(
        timezone = "GMT+8",
        pattern = "yyyy-MM-dd"
    )
    @JsonIgnore
    @Generated
    public OnlAuthPage setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "OnlAuthPage(id=" + var10000 + ", cgformId=" + this.getCgformId() + ", code=" + this.getCode() + ", type=" + this.getType() + ", control=" + this.getControl() + ", page=" + this.getPage() + ", status=" + this.getStatus() + ", createTime=" + this.getCreateTime() + ", createBy=" + this.getCreateBy() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ")";
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OnlAuthPage)) {
            return false;
        } else {
            OnlAuthPage other = (OnlAuthPage)o;
            if (!other.canEqual(this)) {
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

                Object this$control = this.getControl();
                Object other$control = other.getControl();
                if (this$control == null) {
                    if (other$control != null) {
                        return false;
                    }
                } else if (!this$control.equals(other$control)) {
                    return false;
                }

                Object this$page = this.getPage();
                Object other$page = other.getPage();
                if (this$page == null) {
                    if (other$page != null) {
                        return false;
                    }
                } else if (!this$page.equals(other$page)) {
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

                Object this$createTime = this.getCreateTime();
                Object other$createTime = other.getCreateTime();
                if (this$createTime == null) {
                    if (other$createTime != null) {
                        return false;
                    }
                } else if (!this$createTime.equals(other$createTime)) {
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

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof OnlAuthPage;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $control = this.getControl();
        result = result * 59 + ($control == null ? 43 : $control.hashCode());
        Object $page = this.getPage();
        result = result * 59 + ($page == null ? 43 : $page.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $cgformId = this.getCgformId();
        result = result * 59 + ($cgformId == null ? 43 : $cgformId.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        Object $createBy = this.getCreateBy();
        result = result * 59 + ($createBy == null ? 43 : $createBy.hashCode());
        Object $updateBy = this.getUpdateBy();
        result = result * 59 + ($updateBy == null ? 43 : $updateBy.hashCode());
        Object $updateTime = this.getUpdateTime();
        result = result * 59 + ($updateTime == null ? 43 : $updateTime.hashCode());
        return result;
    }
}
