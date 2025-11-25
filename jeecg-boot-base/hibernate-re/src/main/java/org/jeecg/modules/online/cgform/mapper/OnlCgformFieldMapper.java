//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public interface OnlCgformFieldMapper extends BaseMapper<OnlCgformField> {
    String SELECT_SQL = "SELECT ${ew.sqlSelect} FROM ${tableName} ${ew.customSqlSegment}";

    @Select({"SELECT ${ew.sqlSelect} FROM ${tableName} ${ew.customSqlSegment}"})
    JSONObject doSelect(@Param("tableName") String tableName, @Param("ew") QueryWrapper<?> wrapper);

    @Select({"SELECT ${ew.sqlSelect} FROM ${tableName} ${ew.customSqlSegment}"})
    List<JSONObject> doSelectList(@Param("tableName") String tableName, @Param("ew") QueryWrapper<?> wrapper);

    @Update({"UPDATE ${tableName} SET ${ew.sqlSet} ${ew.customSqlSegment}"})
    int doUpdate(@Param("tableName") String tableName, @Param("ew") UpdateWrapper<?> wrapper);

    @Delete({"DELETE FROM ${tableName} ${ew.customSqlSegment}"})
    int doDelete(@Param("tableName") String tableName, @Param("ew") QueryWrapper<?> wrapper);

    void executeInsertSQL(Map<String, Object> params);

    void executeUpdatetSQL(Map<String, Object> params);

    List<String> selectOnlineHideColumns(@Param("user_id") String userId, @Param("online_tbname") String onlineTbname);

    List<String> selectOnlineDisabledColumns(@Param("user_id") String userId, @Param("online_tbname") String onlineTbname);

    List<String> selectFlowAuthColumns(@Param("table_name") String tableName, @Param("task_id") String taskId, @Param("rule_type") String ruleType);
}
