//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

import lombok.Data;
import lombok.Generated;
import org.jeecgframework.poi.excel.annotation.Excel;

@TableName("onl_auth_relation")
@Schema(
    description = "onl_auth_relation"
)
@Data
public class OnlAuthRelation implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(
        type = IdType.ASSIGN_ID
    )
    @Schema(
        description = "id"
    )
    private String id;
    @Excel(
        name = "角色id",
        width = (double)15.0F
    )
    @Schema(
        description = "角色id"
    )
    private String roleId;
    @Excel(
        name = "权限id",
        width = (double)15.0F
    )
    @Schema(
        description = "权限id"
    )
    private String authId;
    @Excel(
        name = "1字段 2按钮 3数据权限",
        width = (double)15.0F
    )
    @Schema(
        description = "1字段 2按钮 3数据权限"
    )
    private Integer type;
    private String cgformId;
    private String authMode;

}
