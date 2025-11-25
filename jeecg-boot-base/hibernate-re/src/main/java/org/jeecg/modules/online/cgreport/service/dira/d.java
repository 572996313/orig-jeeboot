//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.service.dira;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Generated;
import net.sf.jsqlparser.JSQLParserException;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.DynamicDataSourceModel;
import org.jeecg.common.util.SqlInjectionUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.common.util.dynamic.db.DataSourceCachePool;
import org.jeecg.common.util.dynamic.db.DynamicDBUtil;
import org.jeecg.config.JeecgBaseConfig;
import org.jeecg.modules.online.dirb.b;
import org.jeecg.modules.online.cgform.enums.DataBaseEnum;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;
import org.jeecg.modules.online.cgreport.mapper.OnlCgreportHeadMapper;
import org.jeecg.modules.online.cgreport.model.OnlCgreportModel;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportHeadService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
import org.jeecg.modules.online.config.exception.a;
import org.jeecg.modules.online.config.model.OnlineFieldConfig;
import org.jeecgframework.minidao.sqlparser.impl.vo.SelectSqlInfo;
import org.jeecgframework.minidao.util.MiniDaoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("onlCgreportHeadServiceImpl")
public class d extends ServiceImpl<OnlCgreportHeadMapper, OnlCgreportHead> implements IOnlCgreportHeadService {
    @Generated
    private static final Logger b = LoggerFactory.getLogger(d.class);
    @Autowired
    private IOnlCgreportParamService onlCgreportParamService;
    @Autowired
    private IOnlCgreportItemService onlCgreportItemService;
    @Autowired
    private OnlCgreportHeadMapper mapper;
    @Lazy
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private JeecgBaseConfig jeecgBaseConfig;

    public Map<String, Object> executeSelectSql(String sql, String onlCgreportHeadId, Map<String, Object> params) throws SQLException {
        String dbType = null;

        try {
            dbType = org.jeecg.modules.online.config.dirc.d.getDatabaseType();
        } catch (a e) {
            e.printStackTrace();
        }

        LambdaQueryWrapper<OnlCgreportParam> paramQuery = new LambdaQueryWrapper();
        paramQuery.eq(OnlCgreportParam::getCgrheadId, onlCgreportHeadId);
        List paramList = this.onlCgreportParamService.list(paramQuery);
        b paramsHandler = new b(params, paramList);
        sql = paramsHandler.a(sql);
        Map selfSqlParams = paramsHandler.getSelfSqlParams();
        Map result = new HashMap(5);
        Integer pageSize = oConvertUtils.getInt(params.get("pageSize"), 10);
        Integer pageNo = oConvertUtils.getInt(params.get("pageNo"), 1);
        Page page = new Page((long)pageNo, (long)pageSize);
        LambdaQueryWrapper<OnlCgreportItem> query = new LambdaQueryWrapper();
        query.eq(OnlCgreportItem::getCgrheadId, onlCgreportHeadId);
        List<String> forceQuery = new ArrayList();

        for(String key : (String[])params.keySet().toArray(new String[0])) {
            if (key.startsWith("force_")) {
                String forceKey = key.substring("force_".length());
                forceQuery.add(forceKey);
                params.put(forceKey, params.get(key));
            }
        }

        List<OnlCgreportItem> fieldList;
        if (forceQuery.size() > 0) {
            query.in(OnlCgreportItem::getFieldName, forceQuery);
            fieldList = this.onlCgreportItemService.list(query);
            if (fieldList.size() < forceQuery.size()) {
                boolean hasForceId = forceQuery.stream().anyMatch("id"::equalsIgnoreCase);
                boolean hasFieldId = fieldList.stream().anyMatch((itemx) -> "id".equalsIgnoreCase(itemx.getFieldName()));
                if (hasForceId && !hasFieldId) {
                    OnlCgreportItem item = new OnlCgreportItem();
                    item.setFieldName("id");
                    item.setFieldType("String");
                    item.setSearchMode("single");
                    item.setIsSearch(1);
                    fieldList.add(item);
                }
            } else {
                fieldList.forEach((ix) -> ix.setIsSearch(1));
            }
        } else {
            query.eq(OnlCgreportItem::getIsSearch, 1);
            fieldList = this.onlCgreportItemService.list(query);
        }

        sql = QueryGenerator.convertSystemVariables(sql);
        List fieldConfigs = new ArrayList();

        for(OnlCgreportItem item : fieldList) {
            fieldConfigs.add(new OnlineFieldConfig(item));
        }

        String alias = "jeecg_rp_temp.";
        org.jeecg.modules.online.dirb.a handler = new org.jeecg.modules.online.dirb.a(alias, dbType);
        String condition = handler.a(fieldConfigs, params);
        Map sqlParam = handler.getSqlParams();
        if (ReUtil.contains(" order\\s+by ", sql.toLowerCase()) && "SQLSERVER".equalsIgnoreCase(dbType)) {
            throw new JeecgBootException("SqlServer不支持SQL内排序!");
        } else {
            String querySql = "select * from (" + sql + ") jeecg_rp_temp ";
            if (condition.trim().length() > 0) {
                querySql = querySql + " where " + condition;
            }

            Object orderColumn = params.get("column");
            if (orderColumn != null) {
                String order = String.valueOf(params.get("order"));
                String[] orderColumns = String.valueOf(orderColumn).split(",");

                for(int i = 0; i < orderColumns.length; ++i) {
                    orderColumns[i] = SqlInjectionUtil.getSqlInjectField(orderColumns[i]);
                }

                String joinColumn = String.join(" " + order + ", jeecg_rp_temp.", orderColumns);
                querySql = querySql + " order by jeecg_rp_temp." + joinColumn + " " + order;
            }

            SqlInjectionUtil.specialFilterContentForOnlineReport(querySql);
            if (!selfSqlParams.isEmpty()) {
                sqlParam.putAll(selfSqlParams);
            }

            b.info("报表查询sql=>\r\n" + querySql);
            b.info("报表查询sqlParam=>\r\n" + sqlParam.toString());
            IPage pageList;
            if (Boolean.valueOf(String.valueOf(params.get("getAll")))) {
                List resList = this.mapper.selectByCondition(querySql, sqlParam);
                pageList = new Page();
                pageList.setRecords(resList);
                pageList.setTotal((long)resList.size());
            } else {
                pageList = this.mapper.selectPageByCondition(page, querySql, sqlParam);
            }

            result.put("total", pageList.getTotal());
            result.put("records", org.jeecg.modules.online.cgform.dird.d.d(pageList.getRecords()));
            return result;
        }
    }

    public Map<String, Object> executeSelectSqlDynamic(String dbKey, String sql, Map<String, Object> params, String onlCgreportHeadId) {
        String order = (String)params.get("order");
        String column = (String)params.get("column");
        int pageNo = oConvertUtils.getInt(params.get("pageNo"), 1);
        int pageSize = oConvertUtils.getInt(params.get("pageSize"), 10);
        b.info("【Online多数据源逻辑】报表查询参数params: " + JSON.toJSONString(params));
        DynamicDataSourceModel dbSource = DataSourceCachePool.getCacheDynamicDataSourceModel(dbKey);
        if (ReUtil.contains(" order\\s+by ", sql.toLowerCase()) && "3".equalsIgnoreCase(dbSource.getDbType())) {
            throw new JeecgBootException("SqlServer不支持SQL内排序!");
        } else {
            LambdaQueryWrapper<OnlCgreportParam> paramQuery = new LambdaQueryWrapper();
            paramQuery.eq(OnlCgreportParam::getCgrheadId, onlCgreportHeadId);
            List paramList = this.onlCgreportParamService.list(paramQuery);
            b paramsHandler = new b(params, paramList);
            sql = paramsHandler.b(sql);
            Map selfSqlParams = paramsHandler.getSelfSqlParams();
            LambdaQueryWrapper<OnlCgreportItem> query = new LambdaQueryWrapper();
            query.eq(OnlCgreportItem::getCgrheadId, onlCgreportHeadId);
            query.eq(OnlCgreportItem::getIsSearch, 1);
            List<OnlCgreportItem> fieldList = this.onlCgreportItemService.list(query);
            sql = QueryGenerator.convertSystemVariables(sql);
            List fieldConfigs = new ArrayList();

            for(OnlCgreportItem item : fieldList) {
                fieldConfigs.add(new OnlineFieldConfig(item));
            }

            String alias = "jeecg_rp_temp.";
            String realDataBaseType = DataBaseEnum.getDataBaseNameByValue(dbSource.getDbType());
            org.jeecg.modules.online.dirb.a handler = new org.jeecg.modules.online.dirb.a(alias, realDataBaseType);
            handler.setDaoType("jdbcTemplate");
            String condition = handler.a(fieldConfigs, params);
            Map sqlParam = handler.getSqlParams();
            String querySql = "select * from (" + sql + ") jeecg_rp_temp ";
            if (condition.trim().length() > 0) {
                querySql = querySql + " where " + condition;
            }

            String countSql = org.jeecg.modules.online.cgreport.dirc.a.c(querySql);
            Object orderColumn = params.get("column");
            if (orderColumn != null) {
                querySql = querySql + " order by jeecg_rp_temp." + orderColumn.toString() + " " + params.get("order").toString();
            } else if ("3".equalsIgnoreCase(dbSource.getDbType())) {
                querySql = querySql + " ORDER BY (SELECT NULL)";
            }

            if (!selfSqlParams.isEmpty()) {
                sqlParam.putAll(selfSqlParams);
            }

            Map result = new HashMap(5);
            b.info("多数据源 报表查询sql=>querySql: " + querySql);
            b.info("多数据源 报表查询sql=>countSql: " + countSql);
            b.info("多数据源 报表查询sqlParam=>\r\n" + sqlParam.toString());
            Map queryCount = DynamicDBUtil.queryCount(dbKey, countSql, sqlParam);
            result.put("total", queryCount.get("total"));
            List dataList = org.jeecg.modules.online.cgreport.dirc.a.a(String.valueOf(params.get("getAll")), dbKey, querySql, pageNo, pageSize, sqlParam);
            result.put("records", org.jeecg.modules.online.cgform.dird.d.d(dataList));
            return result;
        }
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    @CacheEvict(
        value = {"sys:cache:online:rp"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> editAll(OnlCgreportModel values) {
        OnlCgreportHead head = values.getHead();
        OnlCgreportHead onlCgreportHeadEntity = (OnlCgreportHead)super.getById(head.getId());
        if (onlCgreportHeadEntity == null) {
            return Result.error("未找到对应实体");
        } else {
            super.updateById(head);
            LambdaQueryWrapper<OnlCgreportItem> queryWrapper1 = new LambdaQueryWrapper();
            queryWrapper1.eq(OnlCgreportItem::getCgrheadId, head.getId());
            this.onlCgreportItemService.remove(queryWrapper1);
            LambdaQueryWrapper<OnlCgreportParam> queryWrapper2 = new LambdaQueryWrapper();
            queryWrapper2.eq(OnlCgreportParam::getCgrheadId, head.getId());
            this.onlCgreportParamService.remove(queryWrapper2);

            for(OnlCgreportParam param : values.getParams()) {
                param.setCgrheadId(head.getId());
            }

            for(OnlCgreportItem item : values.getItems()) {
                item.setFieldName(item.getFieldName().trim().toLowerCase());
                item.setCgrheadId(head.getId());
            }

            this.onlCgreportItemService.saveBatch(values.getItems());
            this.onlCgreportParamService.saveBatch(values.getParams());
            return Result.ok("全部修改成功");
        }
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public Result<?> delete(String id) {
        boolean ok = super.removeById(id);
        if (ok) {
            LambdaQueryWrapper<OnlCgreportItem> queryWrapper1 = new LambdaQueryWrapper();
            queryWrapper1.eq(OnlCgreportItem::getCgrheadId, id);
            this.onlCgreportItemService.remove(queryWrapper1);
            LambdaQueryWrapper<OnlCgreportParam> queryWrapper2 = new LambdaQueryWrapper();
            queryWrapper2.eq(OnlCgreportParam::getCgrheadId, id);
            this.onlCgreportParamService.remove(queryWrapper2);
        }

        return Result.ok("删除成功");
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public Result<?> bathDelete(String[] ids) {
        for(String id : ids) {
            boolean ok = super.removeById(id);
            if (ok) {
                LambdaQueryWrapper<OnlCgreportItem> queryWrapper1 = new LambdaQueryWrapper();
                queryWrapper1.eq(OnlCgreportItem::getCgrheadId, id);
                this.onlCgreportItemService.remove(queryWrapper1);
                LambdaQueryWrapper<OnlCgreportParam> queryWrapper2 = new LambdaQueryWrapper();
                queryWrapper2.eq(OnlCgreportParam::getCgrheadId, id);
                this.onlCgreportParamService.remove(queryWrapper2);
            }
        }

        return Result.ok("删除成功");
    }

    public List<String> getSqlFields(String sql, String dbKey) throws SQLException, a, JSQLParserException {
        List fields = null;
        if (StringUtils.isNotBlank(dbKey)) {
            fields = this.a(sql, dbKey);
        } else {
            fields = this.a(sql, (String)null);
        }

        return fields;
    }

    public List<String> getSqlParams(String sql) {
        if (oConvertUtils.isEmpty(sql)) {
            return null;
        } else {
            List params = new ArrayList();
            String regex = "\\$\\{\\w+\\}";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(sql);

            while(m.find()) {
                String whereParam = m.group();
                params.add(whereParam.substring(whereParam.indexOf("{") + 1, whereParam.indexOf("}")));
            }

            return params;
        }
    }

    private List<String> a(String sql, String dbKey) throws SQLException, a, JSQLParserException {
        if (oConvertUtils.isEmpty(sql)) {
            return null;
        } else {
            sql = sql.replace("[^><]=", " = ");
            sql = sql.trim();
            if (sql.endsWith(";")) {
                sql = sql.substring(0, sql.length() - 1);
            }

            sql = QueryGenerator.convertSystemVariables(sql);
            sql = org.jeecg.modules.online.cgreport.dirc.a.a(sql);
            SelectSqlInfo selectSqlInfo = MiniDaoUtil.parseSelectSqlInfo(sql);

            assert selectSqlInfo != null;

            if (this.jeecgBaseConfig.getFirewall() != null && this.jeecgBaseConfig.getFirewall().getDisableSelectAll() && selectSqlInfo.isSelectAll()) {
                throw new JeecgBootException("不允许使用 *");
            } else {
                Set fieldsSet = null;
                if (StringUtils.isNotBlank(dbKey)) {
                    b.info("parse sql : " + sql);
                    DynamicDataSourceModel dbSource = DataSourceCachePool.getCacheDynamicDataSourceModel(dbKey);
                    if (ReUtil.contains(" order\\s+by ", sql.toLowerCase()) && "3".equalsIgnoreCase(dbSource.getDbType())) {
                        throw new JeecgBootException("SqlServer不支持SQL内排序!");
                    }

                    Map objectMap = org.jeecg.modules.online.cgreport.dirc.a.a(dbKey, sql);
                    if (objectMap == null) {
                        if (!sql.contains("*")) {
                            try {
                                objectMap = MiniDaoUtil.parsSqlField(sql);
                            } catch (Exception var10) {
                            }
                        }

                        if (objectMap == null) {
                            throw new JeecgBootException("该报表sql没有数据");
                        }
                    }

                    fieldsSet = objectMap.keySet();
                } else {
                    String dbType = org.jeecg.modules.online.config.dirc.d.getDatabaseType();
                    if (ReUtil.contains(" order\\s+by ", sql.toLowerCase()) && "SQLSERVER".equalsIgnoreCase(dbType)) {
                        throw new JeecgBootException("SqlServer不支持SQL内排序!");
                    }

                    IPage pageList = this.mapper.executeParseSql(new Page(1L, 1L), sql);
                    List result = pageList.getRecords();
                    if (result.isEmpty()) {
                        if (!sql.contains("*")) {
                            try {
                                fieldsSet = MiniDaoUtil.parsSqlField(sql).keySet();
                            } catch (Exception var9) {
                            }
                        }

                        if (fieldsSet == null) {
                            throw new JeecgBootException("该报表sql没有数据");
                        }
                    } else {
                        fieldsSet = ((LinkedHashMap)result.get(0)).keySet();
                    }
                }

                if (fieldsSet != null) {
                    fieldsSet.remove("ROW_ID");
                }

                return new ArrayList(fieldsSet);
            }
        }
    }

    public Map<String, Object> queryCgReportConfig(String reportId) {
        Map cgReportM = new HashMap(5);
        Map mainM = this.mapper.queryCgReportMainConfig(reportId);
        List itemsM = this.mapper.queryCgReportItems(reportId);
        List params = this.mapper.queryCgReportParams(reportId);
        if (org.jeecg.modules.online.config.dirc.d.a()) {
            cgReportM.put("main", org.jeecg.modules.online.cgform.dird.d.a(mainM));
            cgReportM.put("items", org.jeecg.modules.online.cgform.dird.d.d(itemsM));
        } else {
            cgReportM.put("main", mainM);
            cgReportM.put("items", itemsM);
        }

        cgReportM.put("params", params);
        return cgReportM;
    }

    /** @deprecated */
    @Deprecated
    public List<DictModel> queryDictSelectData(String sql, String keyword, int pageNo, int pageSize) {
        this.sysBaseAPI.dictTableWhiteListCheckBySql(sql);
        List ls = new ArrayList();
        Page page = new Page();
        page.setSearchCount(false);
        page.setCurrent((long)pageNo);
        page.setSize((long)pageSize);
        sql = sql.trim();
        int lastIndex = sql.lastIndexOf(";");
        if (lastIndex == sql.length() - 1) {
            sql = sql.substring(0, lastIndex);
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        if (keyword != null && !"".equals(keyword)) {
            String likeKeyword = "%" + keyword + "%";
            ((QueryWrapper)((QueryWrapper)queryWrapper.like("temp.value", likeKeyword)).or()).like("temp.text", likeKeyword);
        }

        IPage pageList = ((OnlCgreportHeadMapper)this.baseMapper).selectPageBySql(page, sql, queryWrapper);
        List queryDictList = pageList.getRecords();
        queryDictList = (List)queryDictList.stream().filter((x) -> x != null).collect(Collectors.toList());
        if (queryDictList != null && queryDictList.size() != 0) {
            String queryDictJson = JSON.toJSONString(queryDictList);
            ls = JSON.parseArray(queryDictJson, DictModel.class);
        }

        return ls;
    }

    public Map<String, Object> queryColumnInfo(String code, boolean queryDict) {
        Map resultMap = new HashMap(5);
        QueryWrapper queryWrapper = new QueryWrapper();
        ((QueryWrapper)((QueryWrapper)queryWrapper.eq("cgrhead_id", code)).eq("is_show", 1)).orderByAsc("order_num");
        List<OnlCgreportItem> list = this.onlCgreportItemService.list(queryWrapper);
        JSONArray fieldHrefSlots = new JSONArray();
        JSONArray columns = new JSONArray();
        Map dictOptions = new HashMap(5);
        boolean isGroupTitle = false;

        for(OnlCgreportItem item : list) {
            JSONObject column = new JSONObject(4);
            column.put("id", item.getId());
            column.put("title", item.getFieldTxt());
            column.put("dataIndex", item.getFieldName());
            column.put("fieldType", item.getFieldType());
            column.put("align", "center");
            column.put("sorter", "true");
            column.put("isTotal", item.getIsTotal());
            column.put("groupTitle", item.getGroupTitle());
            if (oConvertUtils.isNotEmpty(item.getGroupTitle())) {
                isGroupTitle = true;
            }

            String dbtype = item.getFieldType();
            if ("Integer".equals(dbtype) || "Date".equals(dbtype) || "Long".equals(dbtype)) {
                column.put("sorter", "true");
            }

            if (StringUtils.isNotBlank(item.getFieldHref())) {
                String slotName = "fieldHref_" + item.getFieldName();
                JSONObject scopedSlots = new JSONObject();
                scopedSlots.put("customRender", slotName);
                column.put("scopedSlots", scopedSlots);
                JSONObject slot = new JSONObject();
                slot.put("slotName", slotName);
                slot.put("href", item.getFieldHref());
                fieldHrefSlots.add(slot);
            }

            Integer fieldWidth = item.getFieldWidth();
            if (oConvertUtils.isNotEmpty(fieldWidth)) {
                column.put("fieldWidth", fieldWidth);
            }

            String dictCode = item.getDictCode();
            if (dictCode != null && !"".equals(dictCode)) {
                if (queryDict) {
                    List ls = this.queryColumnDict(item.getDictCode(), (JSONArray)null, (String)null);
                    dictOptions.put(item.getFieldName(), ls);
                    column.put("customRender", item.getFieldName());
                } else {
                    column.put("dictCode", dictCode);
                }
            }

            columns.add(column);
        }

        if (queryDict) {
            resultMap.put("dictOptions", dictOptions);
        }

        resultMap.put("columns", columns);
        resultMap.put("fieldHrefSlots", fieldHrefSlots);
        resultMap.put("isGroupTitle", isGroupTitle);
        return resultMap;
    }

    public List<DictModel> queryColumnDict(String dictCode, JSONArray records, String fieldName) {
        List ls = null;
        if (oConvertUtils.isNotEmpty(dictCode)) {
            if (dictCode.trim().toLowerCase().indexOf("select ") == 0 && (fieldName == null || records.size() > 0)) {
                dictCode = dictCode.trim();
                int lastIndex = dictCode.lastIndexOf(";");
                if (lastIndex == dictCode.length() - 1) {
                    dictCode = dictCode.substring(0, lastIndex);
                }

                this.sysBaseAPI.dictTableWhiteListCheckBySql(dictCode);
                String selectSql = "SELECT * FROM (" + dictCode + ") temp ";
                if (records != null) {
                    HashSet inValues = new HashSet();

                    for(int i = 0; i < records.size(); ++i) {
                        JSONObject data = records.getJSONObject(i);
                        String value = data.getString(fieldName);
                        if (StringUtils.isNotBlank(value)) {
                            inValues.add(value);
                        }
                    }

                    String inValueStr = "'" + StringUtils.join(inValues, "','") + "'";
                    selectSql = selectSql + "WHERE temp.value IN (" + inValueStr + ")";
                }

                List queryDictList = ((OnlCgreportHeadMapper)this.baseMapper).executeSqlDict(selectSql);
                if (queryDictList != null && queryDictList.size() != 0) {
                    String queryDictJson = JSON.toJSONString(queryDictList);
                    ls = JSON.parseArray(queryDictJson, DictModel.class);
                }
            } else {
                ls = this.sysBaseAPI.queryDictItemsByCode(dictCode);
            }
        }

        return ls;
    }

    public List<DictModel> queryColumnDictList(String dictCode, List<Map<String, Object>> records, String fieldName) {
        List ls = null;
        if (oConvertUtils.isNotEmpty(dictCode)) {
            dictCode = dictCode.trim();
            if (dictCode.toLowerCase().indexOf("select ") == 0 && (fieldName == null || records.size() > 0)) {
                if (dictCode.endsWith(";")) {
                    dictCode = dictCode.substring(0, dictCode.length() - 1);
                }

                this.sysBaseAPI.dictTableWhiteListCheckBySql(dictCode);
                QueryWrapper queryWrapper = new QueryWrapper();
                if (records != null && records.size() < 100) {
                    HashSet inValues = new HashSet();

                    for(int i = 0; i < records.size(); ++i) {
                        Map row = (Map)records.get(i);
                        if (row != null) {
                            Object data = org.jeecg.modules.online.cgform.dird.d.a(row, fieldName);
                            if (data != null) {
                                inValues.add(data.toString());
                            }
                        }
                    }

                    if (inValues.size() > 0) {
                        queryWrapper.in("temp.value", inValues);
                    }
                }

                ls = ((OnlCgreportHeadMapper)this.getBaseMapper()).queryDictListBySql(dictCode, queryWrapper);
            } else {
                ls = this.sysBaseAPI.queryDictItemsByCode(dictCode);
            }
        }

        return ls;
    }
}
