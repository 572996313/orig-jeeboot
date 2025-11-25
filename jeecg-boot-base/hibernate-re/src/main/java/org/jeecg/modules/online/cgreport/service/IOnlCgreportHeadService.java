//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import net.sf.jsqlparser.JSQLParserException;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.model.OnlCgreportModel;
import org.jeecg.modules.online.config.exception.a;

public interface IOnlCgreportHeadService extends IService<OnlCgreportHead> {
    Result<?> editAll(OnlCgreportModel values);

    Result<?> delete(String id);

    Result<?> bathDelete(String[] ids);

    Map<String, Object> executeSelectSql(String sql, String onlCgreportHeadId, Map<String, Object> params) throws SQLException;

    Map<String, Object> executeSelectSqlDynamic(String dbKey, String sql, Map<String, Object> params, String onlCgreportHeadId);

    List<String> getSqlFields(String sql, String dbKey) throws SQLException, a, JSQLParserException;

    List<String> getSqlParams(String sql);

    Map<String, Object> queryCgReportConfig(String reportCode);

    List<DictModel> queryDictSelectData(String sql, String keyword, int pageNo, int pageSize);

    Map<String, Object> queryColumnInfo(String code, boolean queryDict);

    List<DictModel> queryColumnDict(String dictCode, JSONArray records, String fieldName);

    List<DictModel> queryColumnDictList(String dictCode, List<Map<String, Object>> records, String fieldName);
}
