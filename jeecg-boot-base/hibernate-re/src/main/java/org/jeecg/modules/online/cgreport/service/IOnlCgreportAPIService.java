//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.service;

import java.util.Map;
import org.apache.poi.ss.usermodel.Workbook;

public interface IOnlCgreportAPIService {
    Map<String, Object> getDataById(String id, Map<String, Object> params);

    Map<String, Object> getDataByCode(String code, Map<String, Object> params);

    Map<String, Object> getData(String id, String code, Map<String, Object> params);

    Map<String, Object> executeSelectSqlRoute(String dbKey, String sql, Map<String, Object> params, String headId) throws Exception;

    Workbook getReportWorkbook(String reportId, Map<String, Object> params);
}
