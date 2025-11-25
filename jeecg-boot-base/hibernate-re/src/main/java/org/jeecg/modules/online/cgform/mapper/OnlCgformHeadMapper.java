//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;

public interface OnlCgformHeadMapper extends BaseMapper<OnlCgformHead> {
    @InterceptorIgnore(
        tenantLine = "true"
    )
    void executeDDL(@Param("sqlStr") String sql);

    List<Map<String, Object>> queryList(@Param("sqlStr") String sql);

    List<String> queryOnlinetables();

    int queryCountByTableName(@Param("tbname") String tbname);

    Map<String, Object> queryOneByTableNameAndId(@Param("tbname") String tbname, @Param("dataId") String dataId);

    void deleteOne(@Param("tbname") String tbname, @Param("dataId") String dataId);

    @Select({"select max(copy_version) from onl_cgform_head where physic_id = #{physicId}"})
    Integer getMaxCopyVersion(@Param("physicId") String physicId);

    @Select({"select table_name from onl_cgform_head where physic_id = #{physicId}"})
    List<String> queryAllCopyTableName(@Param("physicId") String physicId);

    @Select({"select physic_id from onl_cgform_head GROUP BY physic_id"})
    List<String> queryCopyPhysicId();

    String queryCategoryIdByCode(@Param("code") String code);

    @Select({"select count(*) from ${tableName} where ${pidField} = #{pidValue}"})
    Integer queryChildNode(@Param("tableName") String tableName, @Param("pidField") String pidField, @Param("pidValue") String pidValue);
}
