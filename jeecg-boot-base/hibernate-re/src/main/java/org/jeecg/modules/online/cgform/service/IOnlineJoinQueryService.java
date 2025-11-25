//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service;

import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.model.e;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;

public interface IOnlineJoinQueryService {
    Map<String, Object> pageList(OnlCgformHead head, Map<String, Object> params, boolean ignoreSelectSubField);

    Map<String, Object> pageList(OnlCgformHead head, Map<String, Object> params);

    e getQueryInfo(OnlCgformHead head, Map<String, Object> params, boolean ignoreSelectSubField);

    e getQueryInfo(OnlCgformHead head, Map<String, Object> params, boolean ignoreSelectSubField, boolean isNewExport);

    XSSFWorkbook handleOnlineExport(OnlCgformHead head, Map<String, Object> params);

    void addAllSubTableDate(String subTable, Map<String, Object> params, List<Map<String, Object>> result, List<ExcelExportEntity> entityList, boolean subEntityExist);
}
