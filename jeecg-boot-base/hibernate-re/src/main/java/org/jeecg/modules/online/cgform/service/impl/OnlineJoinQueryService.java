//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Generated;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.MatchTypeEnum;
import org.jeecg.common.system.util.JeecgDataAutorUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.common.util.dynamic.db.DbTypeUtils;
import org.jeecg.modules.online.auth.service.IOnlAuthDataService;
import org.jeecg.modules.online.dirb.a;
import org.jeecg.modules.online.cgform.dirb.b;
import org.jeecg.modules.online.cgform.dird.d;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.mapper.OnlineMapper;
import org.jeecg.modules.online.cgform.model.e;
import org.jeecg.modules.online.cgform.model.f;
import org.jeecg.modules.online.cgform.model.h;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.cgform.service.IOnlineJoinQueryService;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.jeecg.modules.online.config.model.OnlineFieldConfig;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.excel.export.ExcelExportServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service("onlineJoinQueryService")
public class OnlineJoinQueryService implements IOnlineJoinQueryService {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(OnlineJoinQueryService.class);
    @Autowired
    IOnlCgformFieldService onlCgformFieldService;
    @Autowired
    IOnlCgformHeadService onlCgformHeadService;
    @Autowired
    private IOnlAuthDataService onlAuthDataService;
    @Lazy
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private OnlineMapper onlineMapper;
    @Value("${jeecg.path.upload}")
    private String upLoadPath;

    public Map<String, Object> pageList(OnlCgformHead head, Map<String, Object> params, boolean ignoreSelectSubField) {
        e oqm = this.getQueryInfo(head, params, ignoreSelectSubField);
        String finalSql = oqm.getSql();
        Map sqlParams = oqm.getParams();
        Map tableAliasMap = oqm.getTableAliasMap();
        Map result = new HashMap(5);
        Integer pageSize = params.get("pageSize") == null ? 10 : Integer.parseInt(params.get("pageSize").toString());
        if (pageSize == -521) {
            List dataList = this.onlineMapper.selectByCondition(finalSql, sqlParams);
            if (dataList != null && dataList.size() != 0) {
                result.put("total", dataList.size());
                if (ignoreSelectSubField) {
                    dataList = this.b(dataList);
                }

                result.put("records", d.a(dataList, tableAliasMap.values()));
            } else {
                result.put("total", 0);
            }

            if (ignoreSelectSubField) {
                result.put("fieldList", oqm.getFieldList());
            }
        } else {
            Integer pageNo = params.get("pageNo") == null ? 1 : Integer.parseInt(params.get("pageNo").toString());
            Page page = new Page((long)pageNo, (long)pageSize);
            page.setOptimizeCountSql(false);
            IPage pageList = this.onlineMapper.selectPageByCondition(page, finalSql, sqlParams);
            result.put("total", pageList.getTotal());
            List dataList = pageList.getRecords();
            if (ignoreSelectSubField) {
                dataList = this.b(dataList);
            }

            result.put("records", d.a(dataList, tableAliasMap.values()));
        }

        return result;
    }

    private String a(f table, String mainTableAlias, String pageQuerySql, String superQuerySql) {
        String alias = table.getAlias();
        String tableName = table.getTableName();
        String realTableName = d.f(tableName);
        String fieldAlias = alias + ".";
        StringBuffer sb = new StringBuffer();
        sb.append(" AND EXISTS (");
        sb.append("SELECT ");
        sb.append(fieldAlias + "id");
        sb.append(" FROM ");
        sb.append(realTableName);
        sb.append(" " + alias);
        sb.append(" where  ");
        sb.append(fieldAlias);
        sb.append(table.getJoinField());
        sb.append("=");
        sb.append(mainTableAlias);
        sb.append(table.getMainField());
        if (pageQuerySql != null && pageQuerySql.length() > 0) {
            sb.append(pageQuerySql);
        }

        if (superQuerySql != null && superQuerySql.length() > 0) {
            sb.append(" AND (").append(superQuerySql).append(") ");
        }

        sb.append(")");
        return sb.toString();
    }

    public Map<String, Object> pageList(OnlCgformHead head, Map<String, Object> params) {
        return this.pageList(head, params, false);
    }

    private String a(List<String> sqlFieldList, Map<String, Integer> fieldRecord, Map<String, String> tableAliasMap) {
        List ls = new ArrayList();

        for(String sql : sqlFieldList) {
            String[] arr = sql.split("\\.");
            String tableAlias = arr[0];
            if ("a".equals(tableAlias)) {
                ls.add(sql);
            } else {
                String fieldName = arr[1];
                int fieldNum = (Integer)fieldRecord.get(fieldName);
                if (fieldNum > 1) {
                    String tableName = (String)tableAliasMap.get(tableAlias);
                    ls.add(sql + " " + d.l(tableName) + "_" + fieldName);
                } else {
                    ls.add(sql);
                }
            }
        }

        return String.join(",", ls);
    }

    private void a(String fieldAlias, boolean isMain, List<OnlCgformField> selectFieldList, List<String> sqlFieldList, Map<String, Integer> fieldRecord) {
        if (selectFieldList != null && selectFieldList.size() != 0) {
            int size = selectFieldList.size();

            for(int i = 0; i < size; ++i) {
                OnlCgformField item = (OnlCgformField)selectFieldList.get(i);
                String fieldName = item.getDbFieldName();
                if (!"id".equals(fieldName) && 1 == item.getIsShowList()) {
                    if ("cat_tree".equals(item.getFieldShowType()) && oConvertUtils.isNotEmpty(item.getDictText())) {
                        sqlFieldList.add(fieldAlias + item.getDictText());
                    }

                    sqlFieldList.add(fieldAlias + fieldName);
                    Integer fieldNum = (Integer)fieldRecord.get(fieldName);
                    if (fieldNum == null) {
                        fieldRecord.put(fieldName, 1);
                    } else {
                        fieldRecord.put(fieldName, 1 + fieldNum);
                    }
                }
            }

            sqlFieldList.add(fieldAlias + "id");
            fieldRecord.put("id", 2);
        } else if (isMain) {
            sqlFieldList.add(fieldAlias + "id");
        }

    }

    private f a(OnlCgformHead head, int index, boolean isMain) {
        String headId = head.getId();
        String tableName = head.getTableName();
        f onlTable = new f(tableName, headId, isMain);
        List<OnlCgformField> allFieldList = this.a(headId);
        List selectFieldList = this.onlCgformFieldService.queryAvailableFields(headId, tableName, true, allFieldList, (List)null);
        onlTable.setAllFieldList(allFieldList);
        onlTable.setSelectFieldList(selectFieldList);
        onlTable.setAliasByIntValue(index);
        if (!isMain) {
            for(OnlCgformField item : allFieldList) {
                if (oConvertUtils.isNotEmpty(item.getMainField()) && oConvertUtils.isNotEmpty(item.getMainTable())) {
                    onlTable.setMainField(item.getMainField());
                    onlTable.setJoinField(item.getDbFieldName());
                    break;
                }
            }
        }

        return onlTable;
    }

    private List<f> a(OnlCgformHead head, String userId) {
        int index = 97;
        List list = new ArrayList();
        f mainTable = this.a(head, index++, true);
        List mainTableAuthList = this.onlAuthDataService.queryUserOnlineAuthData(userId, head.getId());
        mainTable.setAuthList(mainTableAuthList);
        list.add(mainTable);
        Integer tableType = head.getTableType();
        if (tableType != null && tableType == 2) {
            String subTableStr = head.getSubTableStr();
            if (subTableStr != null && !"".equals(subTableStr)) {
                String[] arr = subTableStr.split(",");

                for(String subTableName : arr) {
                    OnlCgformHead subTable = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, subTableName));
                    if (subTable != null) {
                        f onlTable = this.a(subTable, index++, false);
                        List authList = this.onlAuthDataService.queryUserOnlineAuthData(userId, subTable.getId());
                        onlTable.setAuthList(authList);
                        list.add(onlTable);
                    }
                }
            }
        }

        return list;
    }

    private Map<String, List<OnlCgformField>> a(OnlCgformHead head, Map<String, String> tableNameAndId) {
        Map map = new HashMap(5);
        tableNameAndId.put(head.getTableName(), head.getId());
        List allFieldList = this.a(head.getId());
        map.put(head.getTableName(), allFieldList);
        Integer tableType = head.getTableType();
        if (tableType != null && tableType == 2) {
            String subTableStr = head.getSubTableStr();
            if (subTableStr != null && !"".equals(subTableStr)) {
                String[] arr = subTableStr.split(",");

                for(String subTableName : arr) {
                    OnlCgformHead subTable = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, subTableName));
                    if (subTable != null) {
                        tableNameAndId.put(subTable.getTableName(), subTable.getId());
                        List subFieldList = this.a(subTable.getId());
                        map.put(subTable.getTableName(), subFieldList);
                    }
                }
            }
        }

        return map;
    }

    private List<OnlCgformField> a(String id) {
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, id);
        query.eq(OnlCgformField::getDbIsPersist, b.b);
        query.orderByAsc(OnlCgformField::getOrderNum);
        return this.onlCgformFieldService.list(query);
    }

    private boolean a(Map<String, Object> params, boolean isMain, String tableName, String alias, List<OnlCgformField> allFieldList, List<h> orderList) {
        boolean hasOrderBy = isMain;
        Object columnParam = params.get("column");
        if (columnParam != null && !"id".equals(columnParam.toString())) {
            String column = columnParam.toString();
            Object ruleObj = params.get("order");
            String rule = "desc";
            if (ruleObj != null) {
                rule = ruleObj.toString();
            }

            if (isMain) {
                if (d.c(column, allFieldList)) {
                    h sqlOrder = new h(column, rule);
                    sqlOrder.setAlias(alias);
                    orderList.add(sqlOrder);
                }
            } else if (column.startsWith(tableName)) {
                String realColumn = column.replaceFirst(tableName + "_", "");
                if (d.c(realColumn, allFieldList)) {
                    h sqlOrder = new h(realColumn, rule);
                    sqlOrder.setAlias(alias);
                    orderList.add(sqlOrder);
                    hasOrderBy = true;
                }
            }
        } else {
            for(OnlCgformField field : allFieldList) {
                if ("1".equals(field.getSortFlag())) {
                    String extJson = field.getFieldExtendJson();
                    h sqlOrder = new h(field.getDbFieldName());
                    sqlOrder.setAlias(alias);
                    if (extJson != null && !"".equals(extJson)) {
                        JSONObject json = JSON.parseObject(extJson);
                        String orderRule = json.getString("orderRule");
                        if (orderRule != null && !"".equals(orderRule)) {
                            sqlOrder.setRule(orderRule);
                            orderList.add(sqlOrder);
                            hasOrderBy = true;
                        }
                    }
                }
            }
        }

        return hasOrderBy;
    }

    private String a(List<h> ls) {
        if (ls.size() == 0) {
            h sqlOrder = h.a("a.");
            ls.add(sqlOrder);
        }

        List sqlList = new ArrayList();

        for(h sqlOrder : ls) {
            String temp = sqlOrder.getRealSql();
            sqlList.add(temp);
        }

        return " ORDER BY " + String.join(",", sqlList);
    }

    private String a(StringBuilder builder) {
        String sql = builder.toString();
        return sql != null && !"".equals(sql) ? " AND (" + sql + ") " : "";
    }

    private boolean a(StringBuilder builder, JSONArray superQueryParams, MatchTypeEnum matchType, String alias, String tableName, List<OnlCgformField> allFieldList, boolean isMain, boolean isFirst) {
        boolean temp = isFirst;
        if (superQueryParams != null) {
            for(int i = 0; i < superQueryParams.size(); ++i) {
                JSONObject json = superQueryParams.getJSONObject(i);
                String field = json.getString("field");
                String[] fieldArray = field.split(",");
                if (fieldArray.length == 1) {
                    if (isMain && d.c(field, allFieldList)) {
                        String aliasField = alias + field;
                        d.a(builder, aliasField, json, matchType, (JSONObject)null, temp);
                        temp = false;
                    }
                } else {
                    String realField = fieldArray[1];
                    if (tableName.equalsIgnoreCase(fieldArray[0]) && d.c(realField, allFieldList)) {
                        String aliasField = alias + realField;
                        d.a(builder, aliasField, json, matchType, (JSONObject)null, temp);
                        temp = false;
                    }
                }
            }
        }

        return temp;
    }

    private List<Map<String, Object>> b(List<Map<String, Object>> records) {
        Map map = new HashMap(5);

        for(Map temp : records) {
            String id = "";
            if (temp.containsKey("id")) {
                id = temp.get("id").toString();
            } else if (temp.containsKey("ID")) {
                id = temp.get("ID").toString();
            }

            map.putIfAbsent(id, temp);
        }

        return new ArrayList(map.values());
    }

    private boolean a(f table, JSONArray superQueryParams) {
        if (table.a()) {
            return true;
        } else {
            String tableName = table.getTableName();
            if (superQueryParams != null && superQueryParams.size() > 0) {
                for(int i = 0; i < superQueryParams.size(); ++i) {
                    JSONObject json = superQueryParams.getJSONObject(i);
                    String field = json.getString("field");
                    String[] fieldArray = field.split(",");
                    if (fieldArray.length == 2 && fieldArray[0] != null && fieldArray[0].equals(tableName)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    private boolean a(f table) {
        if (table.a()) {
            return true;
        } else {
            List<OnlCgformField> selectFieldList = table.getSelectFieldList();
            if (selectFieldList != null && selectFieldList.size() > 0) {
                for(OnlCgformField field : selectFieldList) {
                    String mainTable = field.getMainTable();
                    if (mainTable == null || "".equals(mainTable)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    private a a(f table, JSONArray array, String matchType, a baseHandler) {
        String tableName = table.getTableName();
        boolean isMain = table.a();
        List allFieldList = table.getAllFieldList();
        List fieldList = new ArrayList();
        if (array != null) {
            for(int i = 0; i < array.size(); ++i) {
                JSONObject parameter = array.getJSONObject(i);
                String field = parameter.getString("field");
                String[] fieldArray = field.split(",");
                if (fieldArray.length == 1) {
                    if (isMain && d.c(field, allFieldList)) {
                        OnlineFieldConfig temp = new OnlineFieldConfig(parameter);
                        fieldList.add(temp);
                    }
                } else {
                    String realField = fieldArray[1];
                    if (tableName.equalsIgnoreCase(fieldArray[0]) && d.c(realField, allFieldList)) {
                        OnlineFieldConfig temp = new OnlineFieldConfig(parameter);
                        fieldList.add(temp);
                    }
                }
            }

            if (fieldList.size() > 0) {
                String alias = table.getAlias() + ".";
                a handler = new a(alias, true, matchType);
                handler.setDuplicateSqlNameRecord(baseHandler.getDuplicateSqlNameRecord());
                handler.setDuplicateParamNameRecord(baseHandler.getDuplicateParamNameRecord());
                handler.a(fieldList);
                return handler;
            }
        }

        return null;
    }

    public e getQueryInfo(OnlCgformHead head, Map<String, Object> params, boolean ignoreSelectSubField) {
        return this.getQueryInfo(head, params, ignoreSelectSubField, false);
    }

    public e getQueryInfo(OnlCgformHead head, Map<String, Object> params, boolean ignoreSelectSubField, boolean isNewExport) {
        DbType dbType = org.jeecg.modules.online.config.dirc.d.c((org.jeecg.modules.online.config.model.b)null);
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        List<f> list = this.a(head, sysUser.getId());
        JSONArray superQueryParams = d.b(params);
        MatchTypeEnum matchType = d.c(params);
        StringBuilder builder = new StringBuilder();
        boolean isFirstSuperQuery = true;
        String mainTableAlias = "";
        boolean loadCacheUser = false;
        StringBuffer fromJoinSql = new StringBuffer();
        StringBuffer conditionSql = new StringBuffer();
        List sqlFieldList = new ArrayList();
        List orderList = new ArrayList();
        Map fieldRecord = new HashMap(5);
        Map tableAliasMap = new HashMap(5);
        List exportFieldList = new ArrayList();
        Map sqlParams = new HashMap(5);

        for(f table : list) {
            List selectFieldList = table.getSelectFieldList();
            String alias = table.getAlias();
            String fieldAlias = alias + ".";
            String tableAlias = " " + alias + " ";
            String tableName = table.getTableName();
            List allFieldList = table.getAllFieldList();
            List authList = table.getAuthList();
            if (!loadCacheUser && authList != null && authList.size() > 0) {
                JeecgDataAutorUtils.installUserInfo(this.sysBaseAPI.getCacheUser(sysUser.getUsername()));
                loadCacheUser = true;
            }

            a handler = new a(fieldAlias, dbType.getDb());
            handler.setTableName(tableName);
            handler.setNeedList((List)null);
            handler.setFirst(false);
            List fieldConfigs = d.g(allFieldList);
            String tempConditionSql = handler.a(fieldConfigs, params, authList, tableName + "@");
            Map sqlParam = handler.getSqlParams();
            sqlParams.putAll(sqlParam);
            boolean hasOrderBy = this.a(params, table.a(), tableName, fieldAlias, allFieldList, orderList);
            boolean hasSelectField = this.a(table);
            boolean hasSuperQueryParam = this.a(table, superQueryParams);
            boolean hasSearch = tempConditionSql.length() > 0;
            boolean isJoinTable = hasSelectField || hasSuperQueryParam || hasSearch || hasOrderBy;
            if (isJoinTable) {
                boolean isExistSql = !hasSelectField && (hasSuperQueryParam || hasSearch);
                if (hasOrderBy) {
                    isExistSql = false;
                }

                if (ignoreSelectSubField && table.a() || !ignoreSelectSubField && hasSelectField) {
                    this.a(fieldAlias, table.a(), selectFieldList, sqlFieldList, fieldRecord);
                }

                String superQuerySql = "";
                a superQueryConditionHandler = this.a(table, superQueryParams, matchType.getValue(), handler);
                if (superQueryConditionHandler != null) {
                    superQuerySql = superQueryConditionHandler.getSql().toString();
                    if (superQuerySql.length() > 0) {
                        sqlParams.putAll(superQueryConditionHandler.getSqlParams());
                    }
                }

                if (table.a()) {
                    String var10001 = d.f(tableName);
                    fromJoinSql.append(" FROM " + var10001 + tableAlias);
                    mainTableAlias = fieldAlias;
                } else {
                    tableAliasMap.put(alias, tableName);
                    if (isExistSql) {
                        String existsSql = this.a(table, mainTableAlias, tempConditionSql, superQuerySql);
                        conditionSql.append(existsSql);
                    } else {
                        fromJoinSql.append(" LEFT JOIN ");
                        fromJoinSql.append(d.f(tableName));
                        fromJoinSql.append(tableAlias);
                        fromJoinSql.append(" ON ");
                        fromJoinSql.append(fieldAlias);
                        fromJoinSql.append(table.getJoinField());
                        fromJoinSql.append("=");
                        fromJoinSql.append(mainTableAlias);
                        fromJoinSql.append(table.getMainField());
                    }
                }

                if (!isExistSql) {
                    conditionSql.append(tempConditionSql);
                    if (superQuerySql.length() > 0) {
                        if (isFirstSuperQuery) {
                            builder.append(superQuerySql);
                            isFirstSuperQuery = false;
                        } else {
                            builder.append(" ").append(matchType.getValue()).append(" ").append(superQuerySql);
                        }
                    }
                }

                String replaceTableName = tableName + ".";

                while(conditionSql.toString().toUpperCase().contains(replaceTableName.toUpperCase())) {
                    int indexBegin = conditionSql.toString().toUpperCase().indexOf(replaceTableName.toUpperCase());
                    conditionSql.replace(indexBegin, indexBegin + tableName.length(), alias);
                }
            }
        }

        String selectFieldSql = this.a(sqlFieldList, fieldRecord, tableAliasMap);
        String superQuerySql = this.a(builder);
        String orderBySql = this.a(orderList);
        String finalSql = "SELECT " + selectFieldSql + fromJoinSql.toString() + " where 1=1  " + conditionSql.toString() + superQuerySql;
        if (!DbTypeUtils.dbTypeIsSqlServer(dbType)) {
            finalSql = finalSql + orderBySql;
        }

        a.info("---Online联合查询sql :>> " + finalSql);
        a.info("---Online联合查询sqlParams :>> " + sqlParams);
        e oqm = new e(finalSql, sqlParams);
        oqm.setTableAliasMap(tableAliasMap);

        for(f table : list) {
            List<OnlCgformField> selectFieldList = table.getSelectFieldList();
            if (isNewExport) {
                for(OnlCgformField field : selectFieldList) {
                    String tempFieldName = field.getDbFieldName();
                    Integer tempCount = (Integer)fieldRecord.get(tempFieldName);
                    if (tempCount != null && tempCount > 1 && !table.a()) {
                        String var58 = table.getTableName();
                        field.setDbFieldName(var58 + "_" + tempFieldName);
                    }

                    exportFieldList.add(field);
                }
            } else if (ignoreSelectSubField && table.a()) {
                exportFieldList = selectFieldList;
            }
        }

        oqm.setFieldList(exportFieldList);
        return oqm;
    }

    public XSSFWorkbook handleOnlineExport(OnlCgformHead head, Map<String, Object> params) {
        XSSFWorkbook wb = new XSSFWorkbook();
        boolean isJoinQuery = d.a(head);
        e oqm = null;
        if (isJoinQuery) {
            oqm = this.getQueryInfo(head, params, false, true);
        } else {
            oqm = this.onlCgformFieldService.getQueryInfo(head, params, (List)null);
        }

        boolean hasNext = true;
        Integer pageSize = 50000;
        Integer pageNo = 1;
        String sql = oqm.getSql();
        Map sqlParam = oqm.getParams();
        List fieldList = oqm.getFieldList();
        List entityList = d.b(fieldList, "id", this.upLoadPath);
        boolean subEntityExist = false;

        while(hasNext) {
            Page page = new Page((long)pageNo, (long)pageSize);
            page.setOptimizeCountSql(false);
            page.setSearchCount(false);
            Integer var15 = pageNo;
            pageNo = pageNo + 1;
            params.put("pageNo", var15);
            a.info("---Online表单导出-查询sql: >>  " + sql);
            a.info("---Online表单导出-查询sqlParam: >>  " + sqlParam.toString());
            IPage<Map<String, Object>> pageList = this.onlineMapper.selectPageByCondition(page, sql, sqlParam);
            List<Map<String, Object>> dataList = d.d(pageList.getRecords());
            if (dataList != null && dataList.size() != 0) {
                List result = new ArrayList();
                String selections = params.get("selections") == null ? null : params.get("selections").toString();
                if (oConvertUtils.isNotEmpty(selections)) {
                    hasNext = false;
                    if (isJoinQuery) {
                        Map tableAliasMap = oqm.getTableAliasMap();
                        List subTables = new ArrayList(tableAliasMap.values());
                        Map selectIdsMap = d.f(selections, subTables);
                        result = (List)dataList.stream().filter((item) -> this.a(item, selectIdsMap)).collect(Collectors.toList());
                    } else {
                        List selectionList = d.h(selections);
                        result = (List)dataList.stream().filter((item) -> selectionList.contains(item.get("id"))).collect(Collectors.toList());
                    }
                } else {
                    if (dataList == null) {
                        dataList = new ArrayList();
                    }

                    result.addAll(dataList);
                }

                org.jeecg.modules.online.cgform.converter.b.a(1, result, fieldList);

                try {
                    this.onlCgformHeadService.executeEnhanceExport(head, result);
                } catch (BusinessException e) {
                    a.error("导出java增强处理出错", e.getMessage());
                }

                if (head.getTableType() == 2 && !isJoinQuery && oConvertUtils.isEmpty(params.get("exportSingleOnly"))) {
                    String subTableStr = head.getSubTableStr();
                    if (oConvertUtils.isNotEmpty(subTableStr)) {
                        String[] subTables = subTableStr.split(",");

                        for(String subTable : subTables) {
                            this.addAllSubTableDate(subTable, params, result, entityList, subEntityExist);
                        }

                        subEntityExist = true;
                    }
                }

                ExcelExportServer server = new ExcelExportServer();
                ExportParams exportParams = new ExportParams();
                exportParams.setType(ExcelType.XSSF);
                server.createSheetForMap(wb, exportParams, entityList, result);
            } else {
                hasNext = false;
            }
        }

        return wb;
    }

    public void addAllSubTableDate(String subTable, Map<String, Object> params, List<Map<String, Object>> result, List<ExcelExportEntity> entityList, boolean subEntityExist) {
        if (!oConvertUtils.isEmpty(subTable)) {
            OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, subTable));
            LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
            query.eq(OnlCgformField::getCgformHeadId, head.getId());
            query.orderByAsc(OnlCgformField::getOrderNum);
            List<OnlCgformField> fieldList = this.onlCgformFieldService.list(query);
            String mainField = "";
            String linkField = "";

            for(OnlCgformField onlCgformField : fieldList) {
                if (oConvertUtils.isNotEmpty(onlCgformField.getMainField())) {
                    mainField = onlCgformField.getMainField();
                    linkField = onlCgformField.getDbFieldName();
                    break;
                }
            }

            if (!subEntityExist) {
                ExcelExportEntity tableEntity = new ExcelExportEntity(head.getTableTxt(), subTable);
                tableEntity.setList(d.b(fieldList, "id", this.upLoadPath));
                entityList.add(tableEntity);
            }

            for(int i = 0; i < result.size(); ++i) {
                params.put(linkField, ((Map)result.get(i)).get(mainField));
                String sql = d.a(head.getTableName(), fieldList, params);
                a.info("-----------动态列表查询子表sql》》" + sql);
                List subResult = this.onlCgformHeadService.queryListData(sql);
                org.jeecg.modules.online.cgform.converter.b.a(1, subResult, fieldList);
                ((Map)result.get(i)).put(subTable, d.d(subResult));
            }

        }
    }

    private boolean a(Map<String, Object> item, Map<String, List<String>> selectIdsMap) {
        boolean flag = true;

        for(String key : selectIdsMap.keySet()) {
            List selected = (List)selectIdsMap.get(key);
            flag = flag && selected.contains(item.get(key));
        }

        return flag;
    }
}
