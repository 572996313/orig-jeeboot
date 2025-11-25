//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;

public interface OnlCgreportHeadMapper extends BaseMapper<OnlCgreportHead> {
    @Select({"${selectSql}"})
    List<Map<String, Object>> executeSqlDict(@Param("selectSql") String selectSql);

    List<DictModel> queryDictListBySql(@Param("dictCode") String dictCode, @Param("ew") Wrapper<?> wrapper);

    IPage<Map<String, Object>> selectPageBySql(Page<Map<String, Object>> page, @Param("tableSql") String tableSql, @Param("ew") Wrapper<?> wrapper);

    /** @deprecated */
    @Deprecated
    IPage<LinkedHashMap<String, Object>> executeParseSql(Page<Map<String, Object>> page, @Param("sqlStr") String sql);

    Map<String, Object> queryCgReportMainConfig(@Param("reportId") String reportId);

    List<Map<String, Object>> queryCgReportItems(@Param("cgrheadId") String reportId);

    List<OnlCgreportParam> queryCgReportParams(@Param("cgrheadId") String reportId);

    List<Map<String, Object>> selectByCondition(@Param("sqlStr") String sqlStr, @Param("param") Map<String, Object> param);

    IPage<Map<String, Object>> selectPageByCondition(Page<Map<String, Object>> page, @Param("sqlStr") String sqlStr, @Param("param") Map<String, Object> param);
}
