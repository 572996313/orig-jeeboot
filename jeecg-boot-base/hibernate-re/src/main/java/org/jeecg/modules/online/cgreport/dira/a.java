//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.dira;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.OnlineAuth;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.DynamicDataSourceModel;
import org.jeecg.common.util.BrowserUtils;
import org.jeecg.common.util.SqlInjectionUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.common.util.security.JdbcSecurityUtil;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;
import org.jeecg.modules.online.cgreport.mapper.OnlCgreportHeadMapper;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
import org.jeecg.modules.online.cgreport.service.dira.b;
import org.jeecg.modules.online.cgreport.service.dira.d;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("onlCgreportAPI")
@RequestMapping({"/online/cgreport/api"})
public class a {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(a.class);
    @Autowired
    private b cgreportAPIService;
    @Autowired
    private d onlCgreportHeadService;
    @Autowired
    private IOnlCgreportItemService onlCgreportItemService;
    @Lazy
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private IOnlCgreportParamService onlCgreportParamService;
    @Autowired
    private org.jeecg.modules.online.config.dirb.a onlReportQueryBlackListHandler;

    @OnlineAuth("getColumnsAndData")
    @GetMapping({"/getColumnsAndData/{code}"})
    @PermissionData
    public Result<?> a(@PathVariable("code") String code, HttpServletRequest request) {
        OnlCgreportHead head = (OnlCgreportHead)this.onlCgreportHeadService.getById(code);
        if (head == null) {
            return Result.error("实体不存在");
        } else {
            Result result = this.b(code, request);
            if (!result.getCode().equals(200)) {
                return result;
            } else {
                Map res = (Map)result.getResult();
                List records = (List)res.get("records");
                Map resultMap = this.onlCgreportHeadService.queryColumnInfo(code, false);
                JSONArray columns = (JSONArray)resultMap.get("columns");
                Map dictOptions = new HashMap(5);
                if (columns != null) {
                    for(int i = 0; i < columns.size(); ++i) {
                        JSONObject column = columns.getJSONObject(i);
                        Object obj = column.get("dictCode");
                        if (obj != null) {
                            String dictCode = obj.toString();
                            String fieldName = columns.getJSONObject(i).getString("dataIndex");
                            List ls = this.onlCgreportHeadService.queryColumnDictList(dictCode, records, fieldName);
                            if (ls != null) {
                                dictOptions.put(fieldName, ls);
                                column.put("customRender", fieldName);
                            }
                        }
                    }
                }

                resultMap.put("cgreportHeadName", head.getName());
                resultMap.put("data", result.getResult());
                resultMap.put("dictOptions", dictOptions);
                return Result.ok(resultMap);
            }
        }
    }

    /** @deprecated */
    @OnlineAuth("getColumns")
    @Deprecated
    @GetMapping({"/getColumns/{code}"})
    public Result<?> a(@PathVariable("code") String code) {
        OnlCgreportHead head = (OnlCgreportHead)this.onlCgreportHeadService.getById(code);
        if (head == null) {
            return Result.error("实体不存在");
        } else {
            QueryWrapper<OnlCgreportItem> queryWrapper = new QueryWrapper();
            queryWrapper.eq("cgrhead_id", code);
            queryWrapper.eq("is_show", 1);
            queryWrapper.orderByAsc("order_num");
            List<OnlCgreportItem> list = this.onlCgreportItemService.list(queryWrapper);
            List array = new ArrayList();
            Map dictOptions = new HashMap(5);

            for(OnlCgreportItem item : list) {
                Map column = new HashMap(5);
                column.put("title", item.getFieldTxt());
                column.put("dataIndex", item.getFieldName());
                column.put("align", "center");
                column.put("sorter", "true");
                array.add(column);
                String dictCode = item.getDictCode();
                if (oConvertUtils.isNotEmpty(dictCode)) {
                    List ls = null;
                    if (dictCode.toLowerCase().indexOf("select ") == 0) {
                        this.sysBaseAPI.dictTableWhiteListCheckByDict(dictCode, new String[0]);
                        SqlInjectionUtil.specialFilterContentForOnlineReport(dictCode);
                        List queryDictList = ((OnlCgreportHeadMapper)this.onlCgreportHeadService.getBaseMapper()).executeSqlDict(dictCode);
                        if (queryDictList != null && queryDictList.size() != 0) {
                            String queryDictJson = JSON.toJSONString(queryDictList);
                            ls = JSON.parseArray(queryDictJson, DictModel.class);
                        }
                    } else {
                        ls = this.sysBaseAPI.queryDictItemsByCode(dictCode);
                    }

                    if (ls != null) {
                        dictOptions.put(item.getFieldName(), ls);
                        column.put("customRender", item.getFieldName());
                    }
                }
            }

            Map result = new HashMap(1);
            result.put("columns", array);
            result.put("dictOptions", dictOptions);
            result.put("cgreportHeadName", head.getName());
            return Result.ok(result);
        }
    }

    @OnlineAuth("getData")
    @GetMapping({"/getData/{code}"})
    @PermissionData
    public Result<?> b(@PathVariable("code") String code, HttpServletRequest request) {
        Map params = org.jeecg.modules.online.cgreport.dirc.a.a(request);
        params.put("getAll", request.getAttribute("getAll"));

        try {
            return Result.OK(this.cgreportAPIService.getDataById(code, params));
        } catch (JeecgBootException e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping({"/getDataOrderByValue/{code}"})
    @PermissionData
    public Result<?> c(@PathVariable("code") String code, HttpServletRequest request) {
        OnlCgreportHead head = (OnlCgreportHead)this.onlCgreportHeadService.getById(code);
        if (head == null) {
            return Result.error("实体不存在");
        } else {
            String cgrSql = head.getCgrSql().trim();
            String dbKey = head.getDbSource();

            try {
                Map params = org.jeecg.modules.online.cgreport.dirc.a.a(request);
                Object order_field = params.get("order_field");
                Object order_value = params.get("order_value");
                if (!oConvertUtils.isEmpty(order_field) && !oConvertUtils.isEmpty(order_value)) {
                    String orderFieldKey = "force_" + order_field;
                    params.put(orderFieldKey, order_value);
                    params.put("getAll", true);
                    Map firstMap = this.cgreportAPIService.executeSelectSqlRoute(dbKey, cgrSql, params, head.getId());
                    JSONArray firstRecords = JSON.parseArray(JSON.toJSONString(firstMap.get("records")));
                    params.remove(order_field.toString());
                    params.remove(orderFieldKey);
                    params.remove("order_field");
                    params.remove("order_value");
                    params.put("getAll", request.getAttribute("getAll"));
                    Map resultMap = this.cgreportAPIService.executeSelectSqlRoute(dbKey, cgrSql, params, head.getId());
                    JSONArray records = JSON.parseArray(JSON.toJSONString(resultMap.get("records")));
                    this.a(firstRecords, records, order_field.toString());
                    resultMap.put("records", records);
                    return Result.ok(resultMap);
                } else {
                    return Result.error("order_field 和 order_value 参数不能为空！");
                }
            } catch (Exception e) {
                a.error(e.getMessage(), e);
                return Result.error("SQL执行失败：" + e.getMessage());
            }
        }
    }

    private void a(JSONArray source, JSONArray target, String orderField) {
        for(int i = 0; i < source.size(); ++i) {
            JSONObject sourceItem = source.getJSONObject(i);
            String id = sourceItem.getString(orderField);
            if (id != null) {
                int size = (int)target.stream().filter((item) -> id.equals(((JSONObject)item).getString(orderField))).count();
                if (size == 0) {
                    target.add(0, sourceItem);
                }
            }
        }

    }

    @OnlineAuth("getQueryInfo")
    @GetMapping({"/getQueryInfo/{code}"})
    public Result<?> b(@PathVariable("code") String cgrheadId) {
        try {
            List list = this.onlCgreportItemService.getAutoListQueryInfo(cgrheadId);
            return Result.ok(list);
        } catch (Exception var3) {
            return Result.error("查询失败");
        }
    }

    @GetMapping({"/getParamsInfo/{code}"})
    public Result<?> c(@PathVariable("code") String cgrheadId) {
        try {
            LambdaQueryWrapper<OnlCgreportParam> query = new LambdaQueryWrapper();
            query.eq(OnlCgreportParam::getCgrheadId, cgrheadId);
            query.orderByAsc(OnlCgreportParam::getOrderNum);
            List list = this.onlCgreportParamService.list(query);
            return Result.ok(list);
        } catch (Exception var4) {
            return Result.error("查询失败");
        }
    }

    @OnlineAuth("exportManySheetXls")
    @PermissionData
    @RequestMapping({"/exportManySheetXls/{reportId}"})
    public void a(@PathVariable("reportId") String reportId, HttpServletRequest request, HttpServletResponse response) {
        if (oConvertUtils.isEmpty(reportId)) {
            throw new JeecgBootException("参数错误");
        } else {
            Map params = org.jeecg.modules.online.cgreport.dirc.a.a(request);
            Workbook workbook = this.cgreportAPIService.getReportWorkbook(reportId, params);
            String codedFileName = "报表";
            response.setContentType("application/vnd.ms-excel");
            OutputStream fOut = null;

            try {
                String browse = BrowserUtils.checkBrowse(request);
                if ("MSIE".equalsIgnoreCase(browse.substring(0, 4))) {
                    response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
                } else {
                    String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                    response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
                }

                fOut = response.getOutputStream();
                workbook.write(fOut);
            } catch (Exception e) {
                a.warn("导出失败", e.getMessage());
            } finally {
                try {
                    fOut.flush();
                    fOut.close();
                } catch (Exception var17) {
                }

            }

        }
    }

    /** @deprecated */
    @Deprecated
    @OnlineAuth("exportXls")
    @PermissionData
    @RequestMapping({"/exportXls/{reportId}"})
    public void b(@PathVariable("reportId") String reportId, HttpServletRequest request, HttpServletResponse response) {
        String codedFileName = "报表";
        String sheetName = "导出信息";
        if (!oConvertUtils.isNotEmpty(reportId)) {
            throw new JeecgBootException("参数错误");
        } else {
            Map cgReportMap = null;

            try {
                cgReportMap = this.onlCgreportHeadService.queryCgReportConfig(reportId);
            } catch (Exception var38) {
                throw new JeecgBootException("动态报表配置不存在!");
            }

            List fieldList = (List)cgReportMap.get("items");
            request.setAttribute("getAll", true);
            Result res = this.b(reportId, request);
            List<Map<String,Object>> records = null;
            if (res.getCode().equals(200)) {
                Map reslutMap = (Map)res.getResult();
                records = (List<Map<String,Object>>)reslutMap.get("records");
            }

            List<String> totalList = new ArrayList();
            Map totalMap = new HashMap(5);
            Map<String,Object> groupMap = new HashMap(5);
            List<ExcelExportEntity> entityList = new ArrayList();

            for(int i = 0; i < fieldList.size(); ++i) {
                Map field = (Map)fieldList.get(i);
                String fieldType = (String)field.get("field_type");
                if ("1".equals(oConvertUtils.getString(((Map)fieldList.get(i)).get("is_show")))) {
                    String exportFieldName = ((Map)fieldList.get(i)).get("field_name").toString();
                    ExcelExportEntity expEntity = new ExcelExportEntity(((Map)fieldList.get(i)).get("field_txt").toString(), exportFieldName, 15);
                    String EXCEL_SPLIT_TAG = "_";
                    String TEMP_EXCEL_SPLIT_TAG = "---";
                    Object dictCode = ((Map)fieldList.get(i)).get("dict_code");
                    List<DictModel> ls = this.onlCgreportHeadService.queryColumnDictList(oConvertUtils.getString(dictCode), records, exportFieldName);
                    if (ls != null && ls.size() > 0) {
                        List dictReplaces = new ArrayList();

                        for(DictModel d : ls) {
                            if (d.getValue().contains(EXCEL_SPLIT_TAG)) {
                                String val = d.getValue().replace(EXCEL_SPLIT_TAG, TEMP_EXCEL_SPLIT_TAG);
                                dictReplaces.add(d.getText() + EXCEL_SPLIT_TAG + val);
                            } else {
                                dictReplaces.add(d.getText() + EXCEL_SPLIT_TAG + d.getValue());
                            }
                        }

                        expEntity.setReplace((String[])dictReplaces.toArray(new String[dictReplaces.size()]));
                    }

                    Object replace_val = ((Map)fieldList.get(i)).get("replace_val");
                    if (oConvertUtils.isNotEmpty(replace_val)) {
                        expEntity.setReplace(replace_val.toString().split(","));
                    }

                    if (oConvertUtils.isNotEmpty(((Map)fieldList.get(i)).get("group_title"))) {
                        String group_title = ((Map)fieldList.get(i)).get("group_title").toString();
                        List groupList = new ArrayList();
                        if (groupMap.containsKey(group_title)) {
                            groupList = (List)groupMap.get(group_title);
                            groupList.add(exportFieldName);
                        } else {
                            ExcelExportEntity subEntity = new ExcelExportEntity(group_title, group_title, true);
                            entityList.add(subEntity);
                            groupList.add(exportFieldName);
                        }

                        groupMap.put(group_title, groupList);
                        expEntity.setColspan(true);
                    }

                    if (oConvertUtils.isNotEmpty(fieldType) && oConvertUtils.isEmpty(dictCode) && ("Integer".equals(fieldType) || "Long".equals(fieldType))) {
                        expEntity.setType(4);
                    }

                    entityList.add(expEntity);
                }

                if ("1".equals(oConvertUtils.getString(((Map)fieldList.get(i)).get("is_total")))) {
                    totalList.add(((Map)fieldList.get(i)).get("field_name").toString());
                }
            }

            for(Map.Entry group : groupMap.entrySet()) {
                String key = (String)group.getKey();
                List values = (List)group.getValue();

                for(ExcelExportEntity subEntity : entityList) {
                    if (key.equals(subEntity.getName()) && subEntity.isColspan()) {
                        subEntity.setSubColumnList(values);
                    }
                }
            }

            if (totalList != null && totalList.size() > 0) {
                for(String fieldKey : totalList) {
                    BigDecimal count = new BigDecimal((double)0.0F);

                    for(Map map : records) {
                        String fieldVal = map.get(fieldKey).toString();
                        if (fieldVal.matches("\\d+(.\\d+)?")) {
                            BigDecimal val = new BigDecimal(fieldVal);
                            count = count.add(val);
                        }
                    }

                    totalMap.put(fieldKey, count);
                }

                records.add(totalMap);
            }

            response.setContentType("application/vnd.ms-excel");
            OutputStream fOut = null;

            try {
                String browse = BrowserUtils.checkBrowse(request);
                if ("MSIE".equalsIgnoreCase(browse.substring(0, 4))) {
                    response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
                } else {
                    String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                    response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
                }

                Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams((String)null, sheetName), entityList, records);
                fOut = response.getOutputStream();
                workbook.write(fOut);
            } catch (Exception var36) {
            } finally {
                try {
                    fOut.flush();
                    fOut.close();
                } catch (Exception var35) {
                }

            }

        }
    }

    @GetMapping({"/getRpColumns/{code}"})
    public Result<?> d(@PathVariable("code") String code) {
        LambdaQueryWrapper<OnlCgreportHead> query = new LambdaQueryWrapper();
        query.eq(OnlCgreportHead::getCode, code);
        OnlCgreportHead head = (OnlCgreportHead)this.onlCgreportHeadService.getOne(query);
        if (head == null) {
            return Result.error("实体不存在");
        } else {
            Map result = this.onlCgreportHeadService.queryColumnInfo(head.getId(), true);
            result.put("cgRpConfigId", head.getId());
            result.put("cgRpConfigName", head.getName());
            return Result.ok(result);
        }
    }

    @RequiresPermissions({"online:report:testConnection"})
    @PostMapping({"/testConnection"})
    public Result a(@RequestBody DynamicDataSourceModel dbSource) {
        Connection conn = null;

        Result var3;
        try {
            JdbcSecurityUtil.validate(dbSource.getDbUrl());
            Class.forName(dbSource.getDbDriver());
            conn = DriverManager.getConnection(dbSource.getDbUrl(), dbSource.getDbUsername(), dbSource.getDbPassword());
            if (conn == null) {
                var3 = Result.ok("数据库连接失败：错误未知");
                return var3;
            }

            var3 = Result.ok("数据库连接成功");
        } catch (ClassNotFoundException e) {
            a.error(e.toString());
            Result e1 = Result.error("数据库连接失败：驱动类不存在");
            return e1;
        } catch (Exception e) {
            a.error(e.toString());
            Result e1 = Result.error("数据库连接失败：" + e.getMessage());
            return e1;
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                a.error(e.toString());
            }

        }

        return var3;
    }

    @GetMapping({"/getReportDictList"})
    @RequiresPermissions({"online:report:getDictList"})
    public Result<?> a(@RequestParam("fieldId") String fieldId, @RequestParam(name = "keyword",required = false) String keyword, @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize) {
        OnlCgreportItem fieldItem = (OnlCgreportItem)this.onlCgreportItemService.getById(fieldId);
        if (fieldItem == null) {
            throw new JeecgBootException("指定字段不存在");
        } else {
            String sql = fieldItem.getDictCode();
            List ls = this.onlCgreportHeadService.queryDictSelectData(sql, keyword, pageNo, pageSize);
            return Result.ok(ls);
        }
    }
}
