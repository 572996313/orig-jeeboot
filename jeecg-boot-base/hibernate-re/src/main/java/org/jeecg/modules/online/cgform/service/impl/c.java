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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.Generated;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.dto.DataLogDTO;
import org.jeecg.common.config.mqtoken.UserTokenContext;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.util.JeecgDataAutorUtils;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.SqlInjectionUtil;
import org.jeecg.common.util.TokenUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.auth.service.IOnlAuthDataService;
import org.jeecg.modules.online.auth.service.IOnlAuthPageService;
import org.jeecg.modules.online.dirb.a;
import org.jeecg.modules.online.cgform.dird.d;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.mapper.OnlCgformFieldMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformHeadMapper;
import org.jeecg.modules.online.cgform.mapper.OnlineMapper;
import org.jeecg.modules.online.cgform.model.TreeModel;
import org.jeecg.modules.online.cgform.model.e;
import org.jeecg.modules.online.cgform.model.h;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service("onlCgformFieldServiceImpl")
public class c extends ServiceImpl<OnlCgformFieldMapper, OnlCgformField> implements IOnlCgformFieldService {
    @Generated
    private static final Logger b = LoggerFactory.getLogger(c.class);
    @Autowired
    IOnlCgreportAPIService onlCgreportAPIService;
    @Autowired
    private OnlCgformFieldMapper onlCgformFieldMapper;
    @Autowired
    private OnlCgformHeadMapper cgformHeadMapper;
    @Autowired
    private IOnlAuthDataService onlAuthDataService;
    @Autowired
    private IOnlAuthPageService onlAuthPageService;
    @Lazy
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private OnlineMapper onlineMapper;
    private static final String c = "0";
    public static ExecutorService a;

    public Map<String, Object> queryAutolistPage(OnlCgformHead head, Map<String, Object> params, List<String> needList) {
        e queryModel = this.getQueryInfo(head, params, needList);
        String sql = queryModel.getSql();
        Map sqlParam = queryModel.getParams();
        List fieldList = queryModel.getFieldList();
        Map result = new HashMap(5);
        Integer pageSize = params.get("pageSize") == null ? 10 : Integer.parseInt(params.get("pageSize").toString());
        if (pageSize == -521) {
            List dataList = this.onlineMapper.selectByCondition(sql, sqlParam);
            if (dataList != null && dataList.size() != 0) {
                result.put("total", dataList.size());
                result.put("fieldList", fieldList);
                result.put("records", d.d(dataList));
            } else {
                result.put("total", 0);
                result.put("fieldList", fieldList);
            }
        } else {
            Integer pageNo = params.get("pageNo") == null ? 1 : Integer.parseInt(params.get("pageNo").toString());
            Page page = new Page((long)pageNo, (long)pageSize);
            IPage pageList = this.onlineMapper.selectPageByCondition(page, sql, sqlParam);
            result.put("total", pageList.getTotal());
            List dataList = d.d(pageList.getRecords());
            this.handleLinkTableDictData(head.getId(), dataList);
            this.a(head, dataList);
            result.put("records", dataList);
        }

        return result;
    }

    public Map<String, Object> queryAutoTreeNoPage(String tbname, String headId, Map<String, Object> params, List<String> needList, String pidField) {
        Map result = new HashMap(5);
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, headId);
        query.orderByAsc(OnlCgformField::getOrderNum);
        List allFieldList = this.list(query);
        List fieldList = this.queryAvailableFields(headId, tbname, true, allFieldList, needList);
        StringBuffer sb = new StringBuffer();
        d.a(tbname, fieldList, sb);
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String userId = sysUser.getId();
        List authList = this.onlAuthDataService.queryUserOnlineAuthData(userId, headId);
        if (authList != null && authList.size() > 0) {
            JeecgDataAutorUtils.installUserInfo(this.sysBaseAPI.getCacheUser(sysUser.getUsername()));
        }

        a handler = new a("t.");
        handler.setTableName(tbname);
        handler.setNeedList(needList);
        handler.setSubTableStr("");
        List fieldConfigs = d.g(allFieldList);
        String conditionSql = handler.a(fieldConfigs, params, authList);
        Map sqlParam = handler.getSqlParams();
        if (conditionSql.trim().length() > 0) {
            sb.append(" t ").append(" where  ").append(conditionSql);
        }

        String orderBySql = this.a(allFieldList, params);
        sb.append(orderBySql);
        Integer pageSize = params.get("pageSize") == null ? 10 : Integer.parseInt(params.get("pageSize").toString());
        if (pageSize == -521) {
            List<Map<String,Object>> dataList = this.onlineMapper.selectByCondition(sb.toString(), sqlParam);
            if ("true".equals(params.get("hasQuery"))) {
                Map mapList = new HashMap();
                List pidValCache = new ArrayList();

                for(Map map : dataList) {
                    String pidVal = d.a(map, pidField);
                    if (pidVal != null && !"0".equals(pidVal)) {
                        if (!pidValCache.contains(pidVal)) {
                            Map rootVal = this.a(pidVal, tbname, headId, needList, pidField);
                            pidValCache.add(pidVal);
                            if (oConvertUtils.isNotEmpty(rootVal)) {
                                Object dataId = rootVal.containsKey("id") ? rootVal.get("id") : rootVal.get("ID");
                                if (oConvertUtils.isNotEmpty(dataId)) {
                                    mapList.put(dataId.toString(), rootVal);
                                }
                            }
                        }
                    } else {
                        Object dataId = map.containsKey("id") ? map.get("id") : map.get("ID");
                        if (oConvertUtils.isNotEmpty(dataId)) {
                            mapList.put(dataId.toString(), map);
                        }
                    }
                }

                dataList = new ArrayList(mapList.values());
            }

            if (dataList != null && dataList.size() != 0) {
                result.put("total", dataList.size());
                result.put("fieldList", fieldList);
                result.put("records", d.d(dataList));
            } else {
                result.put("total", 0);
                result.put("fieldList", fieldList);
            }
        } else {
            Integer pageNo = params.get("pageNo") == null ? 1 : Integer.parseInt(params.get("pageNo").toString());
            Page page = new Page((long)pageNo, (long)pageSize);
            IPage pageList = this.onlineMapper.selectPageByCondition(page, sb.toString(), sqlParam);
            result.put("total", pageList.getTotal());
            result.put("records", d.d(pageList.getRecords()));
        }

        return result;
    }

    private Map<String, Object> a(String pidVal, String tbname, String headId, List<String> needList, String pidField) {
        Map params = new HashMap(5);
        params.put("id", pidVal);
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, headId);
        query.orderByAsc(OnlCgformField::getOrderNum);
        List allFieldList = this.list(query);
        List fieldList = this.queryAvailableFields(headId, tbname, true, allFieldList, needList);
        StringBuffer sb = new StringBuffer();
        d.a(tbname, fieldList, sb);
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String userId = sysUser.getId();
        List authList = this.onlAuthDataService.queryUserOnlineAuthData(userId, headId);
        if (authList != null && authList.size() > 0) {
            JeecgDataAutorUtils.installUserInfo(this.sysBaseAPI.getCacheUser(sysUser.getUsername()));
        }

        a handler = new a("t.");
        handler.setTableName(tbname);
        handler.setNeedList(needList);
        handler.setSubTableStr("");
        List fieldConfigs = d.g(allFieldList);
        String conditionSql = handler.a(fieldConfigs, params, authList);
        Map sqlParam = handler.getSqlParams();
        sb.append(" t ").append(" where  ").append(" id = '").append(pidVal).append("' ");
        if (conditionSql.trim().length() > 0) {
            sb.append(" and ").append(conditionSql);
        }

        List dataList = this.onlineMapper.selectByCondition(sb.toString(), sqlParam);
        if (dataList != null && dataList.size() > 0) {
            Map mapVal = (Map)dataList.get(0);
            return mapVal != null && mapVal.get(pidField) != null && !"0".equals(mapVal.get(pidField)) ? this.a(mapVal.get(pidField).toString(), tbname, headId, needList, pidField) : mapVal;
        } else {
            return null;
        }
    }

    public void saveFormData(String code, String tbname, JSONObject json, boolean isCrazy) {
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, code);
        List fieldList = this.list(query);
        if (isCrazy) {
            Map map = d.c(tbname, fieldList, json);
            this.addOnlineInsertDataLog(tbname, map.get("id").toString());
            ((OnlCgformFieldMapper)this.baseMapper).executeInsertSQL(map);
        } else {
            Map map = d.a(tbname, fieldList, json);
            this.addOnlineInsertDataLog(tbname, map.get("id").toString());
            ((OnlCgformFieldMapper)this.baseMapper).executeInsertSQL(map);
        }

    }

    public void saveTreeFormData(String code, String tbname, JSONObject json, String hasChildField, String pidField) {
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, code);
        List<OnlCgformField> fieldList = this.list(query);

        for(OnlCgformField item : fieldList) {
            if (hasChildField.equals(item.getDbFieldName()) && item.getIsShowForm() != 1) {
                item.setIsShowForm(1);
                json.put(hasChildField, "0");
            } else if (pidField.equals(item.getDbFieldName()) && oConvertUtils.isEmpty(json.get(pidField))) {
                item.setIsShowForm(1);
                json.put(pidField, "0");
            }
        }

        Map params = d.a(tbname, fieldList, json);
        this.addOnlineInsertDataLog(tbname, params.get("id").toString());
        ((OnlCgformFieldMapper)this.baseMapper).executeInsertSQL(params);
        if (!"0".equals(json.getString(pidField))) {
            UpdateWrapper updateWrapper = new UpdateWrapper();
            JSONObject updateEntity = new JSONObject();
            updateEntity.put(hasChildField, "1");
            updateWrapper.eq("id", json.getString(pidField));
            this.a(tbname, updateEntity, updateWrapper);
        }

    }

    public void saveFormData(List<OnlCgformField> fieldList, String tbname, JSONObject json) {
        Map params = d.a(tbname, fieldList, json);
        this.onlCgformFieldMapper.executeInsertSQL(params);
    }

    public void editFormData(String code, String tbname, JSONObject json, boolean isCrazy) {
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, code);
        List fieldList = this.list(query);
        if (isCrazy) {
            Map map = d.d(tbname, fieldList, json);
            this.addOnlineUpdateDataLog(tbname, map.get("id").toString(), fieldList, json);
            this.onlCgformFieldMapper.executeUpdatetSQL(map);
        } else {
            Map map = d.b(tbname, fieldList, json);
            this.addOnlineUpdateDataLog(tbname, map.get("id").toString(), fieldList, json);
            this.onlCgformFieldMapper.executeUpdatetSQL(map);
        }

    }

    public void editTreeFormData(String code, String tbname, JSONObject json, String hasChildField, String pidField) {
        String realTableName = d.f(tbname);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", json.getString("id"));
        Map metaData = (Map)this.a(realTableName, (String)null, (QueryWrapper)queryWrapper, (Class)JSONObject.class);
        Map metaDataLower = d.a(metaData);
        String metaPid = metaDataLower.get(pidField).toString();
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, code);
        List<OnlCgformField> fieldList = this.list(query);

        for(OnlCgformField item : fieldList) {
            if (pidField.equals(item.getDbFieldName()) && oConvertUtils.isEmpty(json.get(pidField))) {
                item.setIsShowForm(1);
                json.put(pidField, "0");
            }
        }

        Map params = d.b(tbname, fieldList, json);
        this.addOnlineUpdateDataLog(tbname, params.get("id").toString(), fieldList, json);
        ((OnlCgformFieldMapper)this.baseMapper).executeUpdatetSQL(params);
        if (!metaPid.equals(json.getString(pidField))) {
            JSONObject updateEntity = new JSONObject();
            if (!"0".equals(metaPid)) {
                Integer count = this.queryCountBySql(realTableName, pidField, metaPid);
                if (count == null || count == 0) {
                    updateEntity.put(hasChildField, "0");
                    UpdateWrapper updateWrapper = new UpdateWrapper();
                    updateWrapper.eq("id", metaPid);
                    this.a(realTableName, updateEntity, updateWrapper);
                }
            }

            if (!"0".equals(json.getString(pidField))) {
                updateEntity.put(hasChildField, "1");
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.eq("id", json.getString(pidField));
                this.a(realTableName, updateEntity, updateWrapper);
            }
        }

    }

    public Map<String, Object> queryFormData(String code, String tbname, String id) {
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, code);
        query.eq(OnlCgformField::getIsShowForm, 1);
        List fieldList = this.list(query);
        QueryWrapper queryWrapper = d.a(fieldList, id);
        return (Map)this.a(tbname, queryWrapper, JSONObject.class);
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public void deleteAutoListMainAndSub(OnlCgformHead head, String ids) {
        if (head.getTableType() == 2) {
            String headId = head.getId();
            String headName = head.getTableName();
            String tableName = "tableName";
            String linkField = "linkField";
            String linkValueStr = "linkValueStr";
            String mainField = "mainField";
            List<Map<String,Object>> ls = new ArrayList();
            if (oConvertUtils.isNotEmpty(head.getSubTableStr())) {
                for(String tbname : head.getSubTableStr().split(",")) {
                    OnlCgformHead subTable = (OnlCgformHead)this.cgformHeadMapper.selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tbname));
                    if (subTable != null) {
                        LambdaQueryWrapper query = (LambdaQueryWrapper)((LambdaQueryWrapper<OnlCgformField>)(new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, subTable.getId())).eq(OnlCgformField::getMainTable, head.getTableName());
                        List subFieldList = this.list(query);
                        if (subFieldList != null && subFieldList.size() != 0) {
                            OnlCgformField subField = (OnlCgformField)subFieldList.get(0);
                            Map map = new HashMap(5);
                            map.put(linkField, subField.getDbFieldName());
                            map.put(mainField, subField.getMainField());
                            map.put(tableName, tbname);
                            map.put(linkValueStr, "");
                            ls.add(map);
                        }
                    }
                }

                LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
                query.eq(OnlCgformField::getCgformHeadId, headId);
                List fieldList = this.list(query);
                String[] idArr = ids.split(",");

                for(String id : idArr) {
                    if (id.indexOf("@") > 0) {
                        id = id.substring(0, id.indexOf("@"));
                    }

                    QueryWrapper queryWrapper = d.a(fieldList, id);
                    Map map = (Map)this.a(headName, queryWrapper, JSONObject.class);
                    new ArrayList();

                    for(Map temp : ls) {
                        Object linkValue = map.get(((String)temp.get(mainField)).toLowerCase());
                        if (linkValue == null) {
                            linkValue = map.get(((String)temp.get(mainField)).toUpperCase());
                        }

                        if (linkValue != null) {
                            String var10000 = (String)temp.get(linkValueStr);
                            String newLinkValue = var10000 + String.valueOf(linkValue) + ",";
                            temp.put(linkValueStr, newLinkValue);
                        }
                    }
                }

                for(Map temp : ls) {
                    this.deleteAutoList((String)temp.get(tableName), (String)temp.get(linkField), (String)temp.get(linkValueStr));
                }
            }

            this.deleteAutoListById(head.getTableName(), ids);
        }

    }

    public void deleteAutoListById(String tbname, String ids) {
        this.deleteAutoList(tbname, "id", ids);
    }

    public void deleteAutoList(String tbname, String linkField, String linkValue) {
        if (linkValue != null && !"".equals(linkValue)) {
            String[] arr = linkValue.split(",");
            List inValues = new ArrayList();

            for(String str : arr) {
                if (str != null && !"".equals(str)) {
                    if (str.indexOf("@") > 0) {
                        str = str.substring(0, str.indexOf("@"));
                    }

                    inValues.add(str);
                }
            }

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.in(SqlInjectionUtil.getSqlInjectField(linkField), inValues.toArray());
            this.a(d.f(tbname), queryWrapper);
        }

    }

    public List<Map<String, String>> getAutoListQueryInfo(String code) {
        int index = 0;
        OnlCgformHead head = (OnlCgformHead)this.cgformHeadMapper.selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getId, code));
        List list = new ArrayList();
        boolean isJoinQuery = d.a(head);
        index = this.a(head, list, index, isJoinQuery);
        Integer tableType = head.getTableType();
        if (isJoinQuery && tableType != null && 2 == tableType) {
            String subTableStr = head.getSubTableStr();
            if (subTableStr != null && !"".equals(subTableStr)) {
                String[] arr = subTableStr.split(",");

                for(String subTableName : arr) {
                    OnlCgformHead subTable = (OnlCgformHead)this.cgformHeadMapper.selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, subTableName));
                    if (subTable != null) {
                        index = this.a(subTable, list, index, true);
                    }
                }
            }
        }

        return list;
    }

    public List<OnlCgformField> queryFormFields(String code, boolean isform) {
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, code);
        if (isform) {
            query.eq(OnlCgformField::getIsShowForm, 1);
        }

        return this.list(query);
    }

    public List<OnlCgformField> queryFormFieldsByTableName(String tableName) {
        OnlCgformHead head = (OnlCgformHead)this.cgformHeadMapper.selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tableName));
        if (head != null) {
            LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
            query.eq(OnlCgformField::getCgformHeadId, head.getId());
            return this.list(query);
        } else {
            return null;
        }
    }

    public OnlCgformField queryFormFieldByTableNameAndField(String tableName, String fieldName) {
        OnlCgformHead head = (OnlCgformHead)this.cgformHeadMapper.selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tableName));
        if (head != null) {
            LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
            query.eq(OnlCgformField::getCgformHeadId, head.getId());
            query.eq(OnlCgformField::getDbFieldName, fieldName);
            if (this.list(query) != null && this.list(query).size() > 0) {
                return (OnlCgformField)this.list(query).get(0);
            }
        }

        return null;
    }

    public Map<String, Object> queryFormData(List<OnlCgformField> fieldList, String tbname, String id) {
        QueryWrapper queryWrapper = d.a(fieldList, id);
        return (Map)this.a(tbname, queryWrapper, JSONObject.class);
    }

    public Map<String, Object> generateMockData(String tableName) {
        List<OnlCgformField> fieldList = this.queryFormFieldsByTableName(tableName);
        Map<String,Object> map = new HashMap();
        if (fieldList != null && fieldList.size() != 0) {
            for(OnlCgformField field : fieldList) {
                String dbFieldName = field.getDbFieldName();
                map.put(dbFieldName, "");
            }

            return map;
        } else {
            return map;
        }
    }

    public List<Map<String, Object>> querySubFormData(List<OnlCgformField> fieldList, String tbname, String linkField, String value) {
        QueryWrapper queryWrapper = d.a(fieldList, linkField, value);
        Collection res = (Collection)this.a(tbname, queryWrapper, Collection.class);
        return (List)res.stream().map((item) -> (JSONObject)item).collect(Collectors.toList());
    }

    public List<String> selectOnlineHideColumns(String tbname) {
        String onlineTbname = "online:" + tbname + ":%";
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String userId = sysUser.getId();
        List list = ((OnlCgformFieldMapper)this.baseMapper).selectOnlineHideColumns(userId, onlineTbname);
        return this.a(list);
    }

    public List<OnlCgformField> queryAvailableFields(String cgFormId, String tbname, String taskId, boolean isList) {
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, cgFormId);
        if (isList) {
            query.eq(OnlCgformField::getIsShowList, 1);
        } else {
            query.eq(OnlCgformField::getIsShowForm, 1);
        }

        query.orderByAsc(OnlCgformField::getOrderNum);
        List<OnlCgformField> fieldList = this.list(query);
        String onlineTbname = "online:" + tbname + "%";
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String userId = sysUser.getId();
        List hideColumns = new ArrayList();
        if (oConvertUtils.isEmpty(taskId)) {
            List onlineHideConfigColumns = this.onlAuthPageService.queryHideCode(userId, cgFormId, isList);
            if (onlineHideConfigColumns != null && onlineHideConfigColumns.size() != 0 && onlineHideConfigColumns.get(0) != null) {
                hideColumns.addAll(onlineHideConfigColumns);
            }
        } else if (oConvertUtils.isNotEmpty(taskId)) {
            List flowHideConfigColumns = ((OnlCgformFieldMapper)this.baseMapper).selectFlowAuthColumns(tbname, taskId, "1");
            if (flowHideConfigColumns != null && flowHideConfigColumns.size() > 0 && flowHideConfigColumns.get(0) != null) {
                hideColumns.addAll(flowHideConfigColumns);
            }

            List requiredColumns = ((OnlCgformFieldMapper)this.baseMapper).selectFlowAuthColumns(tbname, taskId, "3");
            if (requiredColumns != null && !requiredColumns.isEmpty() && requiredColumns.get(0) != null) {
                for(OnlCgformField field : fieldList) {
                    if (!this.b(field.getDbFieldName(), requiredColumns)) {
                        field.setFieldMustInput("1");
                    }
                }
            }
        }

        if (hideColumns.size() == 0) {
            return fieldList;
        } else {
            List result = new ArrayList();

            for(int i = 0; i < fieldList.size(); ++i) {
                OnlCgformField field = (OnlCgformField)fieldList.get(i);
                if (this.b(field.getDbFieldName(), hideColumns)) {
                    result.add(field);
                }
            }

            return result;
        }
    }

    public List<String> queryDisabledFields(String tbname) {
        String onlineTbname = "online:" + tbname + "%";
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String userId = sysUser.getId();
        List disabledColumns = ((OnlCgformFieldMapper)this.baseMapper).selectOnlineDisabledColumns(userId, onlineTbname);
        return this.a(disabledColumns);
    }

    public List<String> queryDisabledFields(String tbname, String taskId) {
        if (oConvertUtils.isEmpty(taskId)) {
            return null;
        } else {
            List disabledColumns = ((OnlCgformFieldMapper)this.baseMapper).selectFlowAuthColumns(tbname, taskId, "2");
            return this.a(disabledColumns);
        }
    }

    private List<String> a(List<String> columns) {
        List result = new ArrayList();
        if (columns != null && columns.size() != 0 && columns.get(0) != null) {
            for(String combineStr : columns) {
                if (!oConvertUtils.isEmpty(combineStr)) {
                    String fieldName = combineStr.substring(combineStr.lastIndexOf(":") + 1);
                    if (!oConvertUtils.isEmpty(fieldName)) {
                        result.add(fieldName);
                    }
                }
            }

            return result;
        } else {
            return result;
        }
    }

    public List<OnlCgformField> queryAvailableFields(String tbname, boolean isList, List<OnlCgformField> List, List<String> needList) {
        String onlineTbname = "online:" + tbname + "%";
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String userId = sysUser.getId();
        List hideColumns = ((OnlCgformFieldMapper)this.baseMapper).selectOnlineHideColumns(userId, onlineTbname);
        return this.a(hideColumns, isList, List, needList);
    }

    public List<OnlCgformField> queryAvailableFields(String cgformId, String tbname, boolean isList, List<OnlCgformField> List, List<String> needList) {
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String userId = sysUser.getId();
        List hideColumns = this.onlAuthPageService.queryListHideColumn(userId, cgformId);
        return this.a(hideColumns, isList, List, needList);
    }

    private List<OnlCgformField> a(List<String> hideColumns, boolean isList, List<OnlCgformField> List, List<String> needList) {
        List result = new ArrayList();
        boolean hasConfig = true;
        if (hideColumns == null || hideColumns.size() == 0 || hideColumns.get(0) == null) {
            hasConfig = false;
        }

        for(OnlCgformField item : List) {
            String name = item.getDbFieldName();
            if (needList != null && needList.contains(name)) {
                item.setIsQuery(1);
                result.add(item);
            } else {
                if (isList) {
                    if (item.getIsShowList() != 1) {
                        if (oConvertUtils.isNotEmpty(item.getMainTable()) && oConvertUtils.isNotEmpty(item.getMainField())) {
                            result.add(item);
                        }
                        continue;
                    }
                } else if (item.getIsShowForm() != 1) {
                    continue;
                }

                if (hasConfig) {
                    if (this.b(name, hideColumns)) {
                        result.add(item);
                    }
                } else {
                    result.add(item);
                }
            }
        }

        return result;
    }

    private boolean b(String filed, List<String> ruleColumns) {
        boolean show = true;

        for(int j = 0; j < ruleColumns.size(); ++j) {
            String combineStr = (String)ruleColumns.get(j);
            if (!oConvertUtils.isEmpty(combineStr)) {
                String ruleField = combineStr.substring(combineStr.lastIndexOf(":") + 1);
                if (!oConvertUtils.isEmpty(ruleField) && ruleField.equals(filed)) {
                    show = false;
                }
            }
        }

        return show;
    }

    public boolean a(String fieldName, List<OnlCgformField> List) {
        boolean flag = false;

        for(OnlCgformField field : List) {
            if (oConvertUtils.camelToUnderline(fieldName).equals(field.getDbFieldName())) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    public void executeInsertSQL(Map<String, Object> params) {
        ((OnlCgformFieldMapper)this.baseMapper).executeInsertSQL(params);
    }

    public void executeUpdatetSQL(Map<String, Object> params) {
        ((OnlCgformFieldMapper)this.baseMapper).executeUpdatetSQL(params);
    }

    public List<TreeModel> queryDataListByLinkDown(org.jeecg.modules.online.cgform.dira.a linkDown) {
        linkDown.setTable(SqlInjectionUtil.getSqlInjectTableName(linkDown.getTable()));
        linkDown.setKey(SqlInjectionUtil.getSqlInjectField(linkDown.getKey()));
        linkDown.setTxt(SqlInjectionUtil.getSqlInjectField(linkDown.getTxt()));
        linkDown.setIdField(SqlInjectionUtil.getSqlInjectField(linkDown.getIdField()));
        linkDown.setPidField(SqlInjectionUtil.getSqlInjectField(linkDown.getPidField()));
        if (oConvertUtils.isNotEmpty(linkDown.getLinkField())) {
            linkDown.setLinkField(SqlInjectionUtil.getSqlInjectField(new String[0]));
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select(new String[]{linkDown.getKey() + " as store", linkDown.getTxt() + " as label", linkDown.getIdField() + " as id", linkDown.getPidField() + " as pid"});
        if (oConvertUtils.isNotEmpty(linkDown.getPidValue())) {
            queryWrapper.eq(linkDown.getPidField(), linkDown.getPidValue());
        } else if (oConvertUtils.isNotEmpty(linkDown.getCondition())) {
            SqlInjectionUtil.specialFilterContentForDictSql(linkDown.getCondition());
            queryWrapper.apply(linkDown.getCondition(), new Object[0]);
        }

        Class clazz = List.class;
        List res = (List)this.a(linkDown.getTable(), queryWrapper, clazz);
//        return (List)res.stream().map((item) -> (TreeModel)item.toJavaObject(TreeModel.class)).collect(Collectors.toList());
        return res;
    }

    public void updateTreeNodeNoChild(String tableName, String filed, String id) {
        Map params = d.a(tableName, filed, id);
        ((OnlCgformFieldMapper)this.baseMapper).executeUpdatetSQL(params);
    }

    public String queryTreeChildIds(OnlCgformHead head, String ids) {
        String pidField = head.getTreeParentIdField();
        String tableName = head.getTableName();
        String[] idArr = ids.split(",");
        StringBuffer sb = new StringBuffer();

        for(String pidVal : idArr) {
            if (pidVal != null && !sb.toString().contains(pidVal)) {
                if (sb.toString().length() > 0) {
                    sb.append(",");
                }

                sb.append(pidVal);
                this.a(pidVal, pidField, tableName, sb);
            }
        }

        return sb.toString();
    }

    public String queryTreePids(OnlCgformHead head, String ids) {
        String pidField = head.getTreeParentIdField();
        String tableName = head.getTableName();
        StringBuffer sb = new StringBuffer();
        String[] idArr = ids.split(",");

        for(String id : idArr) {
            if (id != null) {
                String realTableName = d.f(tableName);
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("id", id);
                Map metaData = (Map)this.a(realTableName, (String)null, (QueryWrapper)queryWrapper, (Class)JSONObject.class);
                Map metaDataLower = d.a(metaData);
                String metaPid = metaDataLower.get(pidField).toString();
                String inIds = "'" + String.join("','", idArr) + "'";
                List dataList = this.queryListBySql(realTableName, (String)null, pidField, metaPid, inIds);
                if ((dataList == null || dataList.size() == 0) && !Arrays.asList(idArr).contains(metaPid) && !sb.toString().contains(metaPid)) {
                    sb.append(metaPid).append(",");
                }
            }
        }

        return sb.toString();
    }

    public String queryForeignKey(String cgFormId, String mainTable) {
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, cgFormId);
        query.eq(OnlCgformField::getMainTable, mainTable);
        List fieldList = this.list(query);
        return fieldList != null && fieldList.size() > 0 ? ((OnlCgformField)fieldList.get(0)).getMainField() : null;
    }

    public List<Map<String, Object>> queryListBySql(String tableName, String fields, String pidField, String metaPid, String inIds) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (oConvertUtils.isNotEmpty(pidField)) {
            pidField = SqlInjectionUtil.getSqlInjectField(pidField);
            queryWrapper.eq(pidField, metaPid);
        }

        if (oConvertUtils.isNotEmpty(inIds)) {
            queryWrapper.in("id", Arrays.asList(inIds.split(",")));
        }

        Collection res = (Collection)this.a(tableName, fields, queryWrapper, Collection.class);
        return (List)res.stream().map((item) -> (JSONObject)item).collect(Collectors.toList());
    }

    private StringBuffer a(String pidVal, String pidField, String tableName, StringBuffer sb) {
        List<Map<String, Object>> dataList = this.queryListBySql(tableName, (String)null, pidField, pidVal, (String)null);
        if (dataList != null && dataList.size() > 0) {
            for(Map mapVal : dataList) {
                Map tempMap = d.a(mapVal);
                if (!sb.toString().contains(tempMap.get("id").toString())) {
                    sb.append(",").append(tempMap.get("id"));
                }

                this.a(tempMap.get("id").toString(), pidField, tableName, sb);
            }
        }

        return sb;
    }

    private String a(List<OnlCgformField> allFieldList, Map<String, Object> params) {
        Object columnParam = params.get("column");
        List ls = new ArrayList();
        if (columnParam != null && !"id".equals(columnParam.toString())) {
            String column = columnParam.toString();
            Object ruleString = params.get("order");
            String rule = "desc";
            if (ruleString != null) {
                rule = ruleString.toString();
            }

            h sqlOrder = new h(column, rule);
            ls.add(sqlOrder);
        } else {
            for(OnlCgformField field : allFieldList) {
                String extJson = field.getFieldExtendJson();
                h sqlOrder = new h(field.getDbFieldName());
                if (oConvertUtils.isNotEmpty(extJson) && extJson.trim().startsWith("{") && extJson.trim().endsWith("}")) {
                    JSONObject json = JSON.parseObject(extJson);
                    String orderRule = json.getString("orderRule");
                    if (orderRule != null && !"".equals(orderRule)) {
                        sqlOrder.setRule(orderRule);
                        ls.add(sqlOrder);
                    }
                }
            }

            if (ls.size() == 0) {
                h sqlOrder = h.a();
                ls.add(sqlOrder);
            }
        }

        List sqlList = new ArrayList();

        for(Object sqlOrderObj : ls) {
            h sqlOrder =(h)sqlOrderObj;
            if (this.a(sqlOrder.getColumn(), allFieldList)) {
                String temp = sqlOrder.getRealSql();
                sqlList.add(temp);
            }
        }

        return " ORDER BY " + String.join(",", sqlList);
    }

    private int a(OnlCgformHead head, List<Map<String, String>> list, int index, boolean isJoinQuery) {
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, head.getId());
        query.eq(OnlCgformField::getIsQuery, 1);
        query.eq(OnlCgformField::getDbIsPersist, org.jeecg.modules.online.cgform.dirb.b.b);
        query.orderByAsc(OnlCgformField::getOrderNum);

        for(OnlCgformField item : this.list(query)) {
            Map map = new HashMap(5);
            map.put("label", item.getDbFieldTxt());
            if (isJoinQuery) {
                String var10002 = head.getTableName();
                map.put("field", var10002 + "@" + item.getDbFieldName());
            } else {
                map.put("field", item.getDbFieldName());
            }

            map.put("dbField", item.getDbFieldName());
            map.put("mode", item.getQueryMode());
            if (oConvertUtils.isNotEmpty(item.getFieldExtendJson())) {
                map.put("fieldExtendJson", item.getFieldExtendJson());
            }

            String queryConfigFlag = item.getQueryConfigFlag();
            if ("1".equals(queryConfigFlag)) {
                String view = item.getQueryShowType();
                map.put("config", "1");
                map.put("view", view);
                map.put("defValue", item.getQueryDefVal());
                if ("cat_tree".equals(view)) {
                    map.put("pcode", item.getQueryDictField());
                } else if ("sel_tree".equals(view)) {
                    String[] arr = item.getQueryDictText().split(",");
                    String var10000 = item.getQueryDictTable();
                    String dict = var10000 + "," + arr[2] + "," + arr[0];
                    map.put("dict", dict);
                    map.put("pidField", arr[1]);
                    map.put("hasChildField", arr[3]);
                    map.put("pidValue", item.getQueryDictField());
                } else {
                    map.put("dictTable", item.getQueryDictTable());
                    map.put("dictCode", item.getQueryDictField());
                    map.put("dictText", item.getQueryDictText());
                }
            } else {
                String view = item.getFieldShowType();
                map.put("view", view);
                map.put("mode", item.getQueryMode());
                if ("cat_tree".equals(view)) {
                    map.put("pcode", item.getDictField());
                } else if ("sel_tree".equals(view)) {
                    String[] arr = item.getDictText().split(",");
                    String var17 = item.getDictTable();
                    String dict = var17 + "," + arr[2] + "," + arr[0];
                    map.put("dict", dict);
                    map.put("pidField", arr[1]);
                    map.put("hasChildField", arr[3]);
                    map.put("pidValue", item.getDictField());
                } else {
                    map.put("dictTable", item.getDictTable());
                    map.put("dictCode", item.getDictField());
                    map.put("dictText", item.getDictText());
                }
            }

            ++index;
            if (index > 2) {
                map.put("hidden", "1");
            }

            list.add(map);
        }

        return index;
    }

    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public void clearCacheOnlineConfig() {
    }

    public e getQueryInfo(OnlCgformHead head, Map<String, Object> params, List<String> needList) {
        String tbname = SqlInjectionUtil.getSqlInjectTableName(head.getTableName());
        String headId = head.getId();
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, headId);
        query.eq(OnlCgformField::getDbIsPersist, org.jeecg.modules.online.cgform.dirb.b.b);
        query.orderByAsc(OnlCgformField::getOrderNum);
        List allFieldList = this.list(query);
        new ArrayList();
        List selectFieldList = head.getSelectFieldList();
        List fieldList;
        if (selectFieldList != null && selectFieldList.size() > 0) {
            fieldList = this.a(headId, allFieldList, selectFieldList, needList);
        } else {
            fieldList = this.queryAvailableFields(headId, tbname, true, allFieldList, needList);
        }

        StringBuffer querySqlBuffer = new StringBuffer();
        d.a(tbname, fieldList, querySqlBuffer);
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String userId = sysUser.getId();
        List authList = this.onlAuthDataService.queryUserOnlineAuthData(userId, headId);
        if (authList != null && authList.size() > 0) {
            JeecgDataAutorUtils.installUserInfo(this.sysBaseAPI.getCacheUser(sysUser.getUsername()));
        }

        DbType dbType = org.jeecg.modules.online.config.dirc.d.c((org.jeecg.modules.online.config.model.b)null);
        a handler = new a("t.", dbType.getDb());
        handler.setTableName(tbname);
        handler.setNeedList(needList);
        handler.setSubTableStr(head.getSubTableStr());
        List fieldConfigs = d.g(allFieldList);
        String conditionSql = handler.a(fieldConfigs, params, authList);
        Map sqlParam = handler.getSqlParams();
        if (conditionSql.trim().length() > 0) {
            querySqlBuffer.append(" t ").append(" where  ").append(conditionSql);
        }

        String orderBySql = this.a(allFieldList, params);
        querySqlBuffer.append(orderBySql);
        e onlQueryModel = new e(querySqlBuffer.toString(), sqlParam);
        onlQueryModel.setFieldList(fieldList);
        return onlQueryModel;
    }

    public void addOnlineInsertDataLog(String tableName, String dataId) {
        String token = TokenUtils.getTokenByRequest();
        a.execute(() -> {
            UserTokenContext.setToken(token);
            String content = " 创建了记录";
            DataLogDTO dataLog = new DataLogDTO(tableName, dataId, content, "comment");
            this.sysBaseAPI.saveDataLog(dataLog);
        });
    }

    public void addOnlineUpdateDataLog(String tableName, String dataId, List<OnlCgformField> fieldList, JSONObject json) {
        String realTableName = d.f(tableName);
        LambdaQueryWrapper<OnlCgformHead> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.select(OnlCgformHead::getTableType, OnlCgformHead::getTableTxt, OnlCgformHead::getSubTableStr);
        queryWrapper.eq(OnlCgformHead::getTableName, realTableName);
        OnlCgformHead head = (OnlCgformHead)this.cgformHeadMapper.selectOne(queryWrapper);
        if (head != null) {
            Set<String> subTableSet = new HashSet();
            if (oConvertUtils.isNotEmpty(head.getSubTableStr())) {
                subTableSet.addAll((Collection)Arrays.stream(head.getSubTableStr().split(",")).collect(Collectors.toSet()));
            }

            Map data = this.a(realTableName, dataId);
            if (data != null) {
                StringBuilder subContent = new StringBuilder();
                if (subTableSet.size() > 0) {
                    for(String subTable : subTableSet) {
                        String subTableName = d.f(subTable);
                        JSONArray subDataList = json.getJSONArray(subTableName);
                        String sc = this.a(subTableName, subDataList, realTableName, dataId);
                        if (oConvertUtils.isNotEmpty(sc)) {
                            subContent.append(sc).append("；");
                        }
                    }
                }

                String token = TokenUtils.getTokenByRequest();
                a.execute(() -> {
                    UserTokenContext.setToken(token);
                    DataLogDTO dataLog = new DataLogDTO(realTableName, dataId, "comment");
                    StringBuilder content = new StringBuilder(this.a(fieldList, json, data));
                    if (subContent.length() > 0) {
                        content.append("；").append(subContent);
                    }

                    String contentStr = content.toString();
                    contentStr = (String)Arrays.stream(contentStr.split("；")).filter((s) -> oConvertUtils.isNotEmpty(s.trim())).collect(Collectors.joining("；"));
                    if (oConvertUtils.isNotEmpty(contentStr)) {
                        dataLog.setContent(contentStr);
                        this.sysBaseAPI.saveDataLog(dataLog);
                    }

                });
            }

        }
    }

    private String a(List<OnlCgformField> fieldList, JSONObject json, Map<String, Object> data) {
        StringBuffer sb = new StringBuffer();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for(OnlCgformField item : fieldList) {
            String key = item.getDbFieldName();
            if (null != key && !"id".equalsIgnoreCase(key) && !"SYS_ORG_CODE".equalsIgnoreCase(key) && !"CREATE_BY".equalsIgnoreCase(key) && !"UPDATE_BY".equalsIgnoreCase(key) && !"CREATE_TIME".equalsIgnoreCase(key) && !"UPDATE_TIME".equalsIgnoreCase(key)) {
                String dbType = item.getDbType();
                if (!"blob".equalsIgnoreCase(dbType) && !"text".equalsIgnoreCase(dbType) && (!oConvertUtils.isEmpty(json.get(key)) || !oConvertUtils.isEmpty(data.get(key)))) {
                    String oldValue = "空";
                    String newValue = "";
                    if (json.get(key) == null) {
                        newValue = "空";
                    } else {
                        String temp = json.get(key).toString();
                        if (temp.length() == 0) {
                            newValue = "空";
                        } else {
                            newValue = temp;
                        }
                    }

                    if ("Datetime".equalsIgnoreCase(dbType)) {
                        if (data.get(key) != null) {
                            LocalDateTime temp = null;
                            Object datetimeValue = data.get(key);
                            if (datetimeValue instanceof Timestamp) {
                                Timestamp timestamp = (Timestamp)datetimeValue;
                                temp = LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
                            } else {
                                temp = (LocalDateTime)data.get(key);
                            }

                            oldValue = dtf.format(temp);
                        }
                    } else if (!"BigDecimal".equalsIgnoreCase(dbType) && !"double".equalsIgnoreCase(dbType)) {
                        if (data.get(key) != null) {
                            oldValue = data.get(key).toString();
                            if (oldValue.length() == 0) {
                                oldValue = "空";
                            }
                        }
                    } else {
                        Integer len = item.getDbPointLength();
                        if (data.get(key) != null) {
                            oldValue = data.get(key).toString();
                        }

                        if (json.get(key) != null && json.get(key).toString().length() != 0) {
                            String temp = json.get(key).toString();
                            if (temp.indexOf(".") < 0) {
                                temp = temp + ".";
                            }

                            if (temp.length() - 1 - temp.indexOf(".") < len) {
                                int pointLen = 0;

                                do {
                                    temp = temp + "0";
                                    pointLen = temp.length() - 1 - temp.indexOf(".");
                                } while(pointLen < len);
                            }

                            newValue = temp;
                        } else {
                            newValue = "空";
                        }
                    }

                    if (!oldValue.equals(newValue)) {
                        if ("double".equalsIgnoreCase(dbType) && data.get(key) != null && json.get(key) != null) {
                            BigDecimal oldBd = new BigDecimal(data.get(key).toString());
                            BigDecimal newBd = new BigDecimal(json.get(key).toString());
                            if (oldBd.compareTo(newBd) == 0) {
                                continue;
                            }
                        }

                        if (oConvertUtils.isNotEmpty(item.getDictField()) && !"popup".equalsIgnoreCase(item.getFieldShowType())) {
                            String[] transedValues = this.a(item, oldValue, newValue);
                            if (transedValues.length == 2) {
                                oldValue = transedValues[0];
                                newValue = transedValues[1];
                            }
                        }

                        sb.append("  将名称为【" + item.getDbFieldTxt() + "】的字段内容 " + oldValue + " 修改为 " + newValue + "；  ");
                    }
                }
            }
        }

        return sb.toString();
    }

    private String[] a(OnlCgformField item, String oldValue, String newValue) {
        List<DictModel> dictItems = null;
        if ("popup_dict".equalsIgnoreCase(item.getFieldShowType())) {
            Map<Object, List<Map<String, Object>>> reportDicts = this.a(item.getDictTable(), item.getDictField(), item.getDictText(), oldValue + "," + newValue);
            System.out.println(reportDicts);
            if (null != reportDicts && !reportDicts.isEmpty()) {
                dictItems = (List)reportDicts.values().stream().filter(Objects::nonNull).map((reportDict) -> new DictModel("" + ((Map)reportDict.get(0)).get(item.getDictField()), "" + ((Map)reportDict.get(0)).get(item.getDictText()))).collect(Collectors.toList());
            }
        } else {
            String dictCode = "";
            if (oConvertUtils.isNotEmpty(item.getDictTable())) {
                String var10000 = item.getDictTable();
                dictCode = var10000 + "," + item.getDictText() + "," + item.getDictField();
            } else {
                dictCode = item.getDictField();
            }

            dictItems = this.sysBaseAPI.getDictItems(dictCode);
        }

        if (oConvertUtils.listIsNotEmpty(dictItems)) {
            List oldVals = new ArrayList();
            if (oldValue.contains(",")) {
                oldVals = Arrays.asList(oldValue.split(","));
            } else {
                oldVals.add(oldValue);
            }

            List newVals = new ArrayList();
            if (newValue.contains(",")) {
                newVals = Arrays.asList(newValue.split(","));
            } else {
                newVals.add(oldValue);
            }

            for(DictModel dictItem : dictItems) {
                a(dictItem, oldVals);
                a(dictItem, newVals);
            }

            if (oConvertUtils.listIsNotEmpty(oldVals)) {
                oldValue = String.join(",", oldVals);
            }

            if (oConvertUtils.listIsNotEmpty(newVals)) {
                newValue = String.join(",", newVals);
            }
        }

        return new String[]{oldValue, newValue};
    }

    private static void a(DictModel dictItem, List<String> vals) {
        String dictVal = dictItem.getValue();
        if (oConvertUtils.listIsNotEmpty(vals) && vals.contains(dictVal)) {
            vals.replaceAll((s) -> s.equalsIgnoreCase(dictVal) ? dictItem.getText() : s);
        }

    }

    private String a(String subTableName, JSONArray dataList, String mainTableName, String mainDataId) {
        if (dataList == null) {
            return "";
        } else {
            LambdaQueryWrapper<OnlCgformHead> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.select(OnlCgformHead::getId, OnlCgformHead::getTableTxt, OnlCgformHead::getRelationType);
            queryWrapper.eq(OnlCgformHead::getTableName, subTableName);
            OnlCgformHead head = (OnlCgformHead)this.cgformHeadMapper.selectOne(queryWrapper);
            if (head == null) {
                return "";
            } else {
                LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
                query.eq(OnlCgformField::getCgformHeadId, head.getId());
                List<OnlCgformField> fieldList = this.list(query);
                if (fieldList != null && fieldList.size() != 0) {
                    OnlCgformField fkField = fieldList.stream().filter((itemx) -> mainTableName.equals(itemx.getMainTable())).findFirst().orElse(null);
                    if (fkField == null) {
                        return "";
                    } else {
                        StringBuilder sb = new StringBuilder();
                        QueryWrapper queryWrapper1 = new QueryWrapper();
                        queryWrapper1.eq(fkField.getDbFieldName(), mainDataId);
                        List oldDataList = (List)this.a(subTableName, (String)null, (QueryWrapper)queryWrapper1, (Class)List.class);
                        if (oldDataList != null && !oldDataList.isEmpty()) {
                            if (dataList.isEmpty()) {
                                sb.append("删除了").append(oldDataList.size()).append("条数据，");
                            } else {
                                if (org.jeecg.modules.online.cgform.enums.a.g.equals(head.getRelationType())) {
                                    JSONObject newData = dataList.getJSONObject(0);
                                    JSONObject oldData = (JSONObject)oldDataList.get(0);
                                    fieldList = (List)fieldList.stream().filter((itemx) -> itemx != fkField).collect(Collectors.toList());
                                    String content = this.a(fieldList, newData, oldData.getInnerMap());
                                    if (oConvertUtils.isEmpty(content)) {
                                        return "";
                                    }

                                    sb.append("子表[").append(head.getTableTxt()).append("]：");
                                    return sb.append(content).toString();
                                }

                                AtomicInteger addCount = new AtomicInteger();
                                int editCount = 0;
                                int delCount = 0;
                                Set oldDataIds = (Set)oldDataList.stream().map((itemx) -> ((JSONObject)itemx).getString("id")).collect(Collectors.toSet());
                                Set newDataIds = new HashSet();

                                for(int i = 0; i < dataList.size(); ++i) {
                                    String id = dataList.getJSONObject(i).getString("id");
                                    if (oConvertUtils.isEmpty(id)) {
                                        addCount.getAndIncrement();
                                    } else {
                                        newDataIds.add(id);
                                    }
                                }

                                Set<String> allIds = new HashSet();
                                allIds.addAll(oldDataIds);
                                allIds.addAll(newDataIds);
                                allIds.removeIf(oConvertUtils::isEmpty);

                                for(String id : allIds) {
                                    if (oldDataIds.contains(id) && !newDataIds.contains(id)) {
                                        ++delCount;
                                    } else {
                                        JSONObject newData = null;

                                        for(int i = 0; i < dataList.size(); ++i) {
                                            JSONObject item = dataList.getJSONObject(i);
                                            if (id.equals(item.getString("id"))) {
                                                newData = item;
                                                break;
                                            }
                                        }

                                        JSONObject oldData = (JSONObject)oldDataList.stream().filter((itemx) -> id.equals(((JSONObject)itemx).getString("id"))).findFirst().orElse((Object)null);
                                        if (newData != null && oldData != null) {
                                            for(OnlCgformField field : fieldList) {
                                                if (field != fkField) {
                                                    String fieldName = field.getDbFieldName();
                                                    if (!"id".equalsIgnoreCase(fieldName) && !"SYS_ORG_CODE".equalsIgnoreCase(fieldName) && !"CREATE_BY".equalsIgnoreCase(fieldName) && !"UPDATE_BY".equalsIgnoreCase(fieldName) && !"CREATE_TIME".equalsIgnoreCase(fieldName) && !"UPDATE_TIME".equalsIgnoreCase(fieldName)) {
                                                        String oldValue = oldData.getString(fieldName);
                                                        String newValue = newData.getString(fieldName);
                                                        if (oConvertUtils.isNotEmpty(oldValue) && oConvertUtils.isNotEmpty(newValue) && !oldValue.equals(newValue)) {
                                                            ++editCount;
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                if (addCount.get() > 0) {
                                    sb.append("新增了").append(addCount.get()).append("条数据，");
                                }

                                if (editCount > 0) {
                                    sb.append("修改了").append(editCount).append("条数据，");
                                }

                                if (delCount > 0) {
                                    sb.append("删除了").append(delCount).append("条数据，");
                                }
                            }
                        } else {
                            if (dataList.isEmpty()) {
                                return "";
                            }

                            sb.append("新增了").append(dataList.size()).append("条数据，");
                        }

                        if (sb.length() > 0) {
                            sb.insert(0, "]：").insert(0, head.getTableTxt()).insert(0, "子表[");
                        }

                        String content = sb.toString();
                        if (content.endsWith("，")) {
                            content = content.substring(0, content.length() - 1);
                        }

                        return content;
                    }
                } else {
                    return "";
                }
            }
        }
    }

    private Map<String, Object> a(String tableName, String dataId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", dataId);
        return (Map)this.a(tableName, (String)null, (QueryWrapper)queryWrapper, (Class)JSONObject.class);
    }

    public List<Map<String, Object>> queryLinkTableDictList(String table, String textString, String code) {
        String fields = textString + "," + code;
        return this.queryListBySql(table, fields, (String)null, (String)null, (String)null);
    }

    public void handleLinkTableDictData(String headId, List<Map<String, Object>> dataList) {
        if (dataList != null && dataList.size() != 0) {
            String[] array = new String[]{"link_table_field", "link_table", "popup_dict"};
            LambdaQueryWrapper query = (new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, headId).in(OnlCgformField::getFieldShowType, array);
            List<OnlCgformField> fieldList = this.onlCgformFieldMapper.selectList(query);
            if (fieldList != null && fieldList.size() > 0) {
                Map tableFieldMap = new HashMap();

                for(OnlCgformField field : fieldList) {
                    if ("link_table_field".equals(field.getFieldShowType())) {
                        String dt = field.getDictTable();
                        List ls = (List)tableFieldMap.get(dt);
                        if (ls == null) {
                            ls = new ArrayList();
                        }

                        String var10000 = field.getDbFieldName();
                        String fieldNameAndTextName = var10000 + "," + field.getDictText();
                        ls.add(fieldNameAndTextName);
                        tableFieldMap.put(dt, ls);
                    }
                }

                for(OnlCgformField field : fieldList) {
                    if ("link_table".equals(field.getFieldShowType())) {
                        String dictTable = field.getDictTable();
                        String dictText = field.getDictText();
                        String dictCode = field.getDictField();
                        List linkTableFieldConfigFieldList = this.a(dictTable, dictText, dictCode);
                        Map configFieldDictData = this.b(linkTableFieldConfigFieldList);
                        LambdaQueryWrapper<OnlCgformHead> queryHeadWrapper = new LambdaQueryWrapper();
                        queryHeadWrapper.eq(OnlCgformHead::getTableName, dictTable);
                        OnlCgformHead dictTableHead = (OnlCgformHead)this.cgformHeadMapper.selectOne(queryHeadWrapper);
                        if (dictTableHead == null) {
                            throw new JeecgBootException("未找到关联表【" + dictTable + "】的配置信息");
                        }

                        List dictTableDataList = this.queryLinkTableDictList(dictTable, dictText, dictCode);
                        String fieldName = field.getDbFieldName().toLowerCase();
                        String textName = dictText.split(",")[0];
                        String codeName = dictCode;

                        for(Map temp : dataList) {
                            Object value = temp.get(fieldName);
                            if (value != null && !"".equals(value.toString())) {
                                String newValue = this.a(dictTableHead, configFieldDictData, dictTableDataList, textName, codeName, value);
                                temp.put(fieldName + "_dictText", newValue);
                                List<String> ls = (List)tableFieldMap.get(fieldName);
                                if (ls != null && ls.size() > 0) {
                                    for(String fieldNameAndTextName : ls) {
                                        String[] arr = fieldNameAndTextName.split(",");
                                        String tempFieldName = arr[0];
                                        String tempTextName = arr[1];
                                        String tempNewValue = this.a(dictTableHead, configFieldDictData, dictTableDataList, tempTextName, codeName, value);
                                        temp.put(tempFieldName.toLowerCase(), tempNewValue);
                                    }
                                }
                            }
                        }
                    } else if ("popup_dict".equalsIgnoreCase(field.getFieldShowType())) {
                        String dictTable = field.getDictTable();
                        String dictText = field.getDictText();
                        String dictCode = field.getDictField();
                        String queryVals = (String)dataList.stream().map((data) -> data.get(field.getDbFieldName().toLowerCase())).filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(","));
                        if (!queryVals.isEmpty()) {
                            queryVals = (String)Arrays.stream(queryVals.split(",")).distinct().collect(Collectors.joining(","));
                            Map dictData = this.a(dictTable, dictCode, dictText, queryVals);
                            dataList.forEach((data) -> {
                                Object valueStr = data.get(field.getDbFieldName().toLowerCase());
                                Object newValue = valueStr;
                                if (valueStr != null) {
                                    String[] valueArr = valueStr.toString().split(",");
                                    newValue = Arrays.stream(valueArr).map((value) -> {
                                        List maps = (List)dictData.get(value);
                                        return maps != null && !maps.isEmpty() ? "" + ((Map)maps.get(0)).get(dictText) : value;
                                    }).collect(Collectors.joining(","));
                                }

                                data.put(field.getDbFieldName().toLowerCase() + "_dictText", newValue);
                            });
                        }
                    }
                }
            }

        }
    }

    private Map<Object, List<Map<String, Object>>> a(String dictTable, String dictCode, String dictText, String queryVals) {
        Map queryParams = new HashMap();
        queryParams.put("force_" + dictCode, queryVals);
        b.info("popup字典字段翻译-查询online报表参数:" + JSONObject.toJSONString(queryParams));
        Map<String, Object> dictQueryResp = null;

        try {
            dictQueryResp = this.onlCgreportAPIService.getDataByCode(dictTable, queryParams);
        } catch (Exception e) {
            b.error("查询字典数据失败:{}", e.getMessage(), e);
        }

        Map<Object, List<Map<String, Object>>> dictData;
        if (null != dictQueryResp && !dictQueryResp.isEmpty()) {
            Long total = (Long)dictQueryResp.get("total");
            if (total > 0L) {
                List<Map<String,Object>> dictDatas = (List<Map<String,Object>>)dictQueryResp.get("records");
                System.out.println(dictDatas);
                dictData = dictDatas.stream().filter(Objects::nonNull).map((record) -> {
                    Map<String, Object> tempRecord = new HashMap<>(2);
                    tempRecord.put(dictCode, record.get(dictCode));
                    tempRecord.put(dictText, record.get(dictText));
                    return tempRecord;
                }).collect(Collectors.groupingBy((o) -> o.get(dictCode)));
            } else {
                dictData = new HashMap();
            }
        } else {
            dictData = new HashMap();
        }

        return dictData;
    }

    public void a(OnlCgformHead head, List<Map<String, Object>> dataList) {
        boolean isTree = "Y".equals(head.getIsTree());
        if (isTree) {
            String pid = head.getTreeParentIdField();
            List queryIds = new ArrayList();
            Map<String,Object> needQuery = new HashMap();

            for(Map item : dataList) {
                String val = d.a(item, pid);
                if (!oConvertUtils.isEmpty(val) && !"0".equals(val)) {
                    queryIds.add(val);
                    ((List)needQuery.computeIfAbsent(val, (k) -> new ArrayList())).add(item);
                } else {
                    String text = " ";
                    item.put(pid + "_dictText", text);
                }
            }

            if (!queryIds.isEmpty()) {
                String textField = head.getTreeFieldname();
                Map params = new HashMap();
                params.put("linkTableSelectFields", pid + "," + textField);
                params.put("pageNo", "1");
                params.put("pageSize", queryIds.size());
                params.put("superQueryMatchType", "and");
                String superQueryParams = "%5B%7B%22field%22:%22id%22,%22rule%22:%22in%22,%22val%22:%22";
                superQueryParams = superQueryParams + String.join(",", queryIds);
                superQueryParams = superQueryParams + "%22%7D%5D";
                params.put("superQueryParams", superQueryParams);
                Map res = this.queryAutolistPage(head, params, new ArrayList());
                if (res != null) {
                    JSONObject result = new JSONObject(res);
                    JSONArray records = result.getJSONArray("records");

                    for(int i = 0; i < records.size(); ++i) {
                        JSONObject record = records.getJSONObject(i);
                        String id = d.a(record, "id");
                        List<Map<String, Object>> items = (List<Map<String, Object>>)needQuery.get(id);
                        if (!CollectionUtils.isEmpty(items)) {
                            String text = d.a(record, textField);
                            if (oConvertUtils.isNotEmpty(text)) {
                                items.forEach((itemx) -> itemx.put(pid + "_dictText", text));
                            }
                        }
                    }

                }
            }
        }
    }

    private List<OnlCgformField> a(String table, String textString, String code) {
        LambdaQueryWrapper query = (LambdaQueryWrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, table);
        OnlCgformHead head = (OnlCgformHead)this.cgformHeadMapper.selectOne(query);
        if (head == null) {
            throw new JeecgBootException("实体未找到");
        } else if (!oConvertUtils.isEmpty(textString) && !oConvertUtils.isEmpty(code)) {
            String[] textArray = textString.split(",");
            List fields = new ArrayList();
            fields.add(code);

            for(String k : textArray) {
                fields.add(k);
            }

            LambdaQueryWrapper query2 = (LambdaQueryWrapper)((LambdaQueryWrapper<OnlCgformField>)(new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, head.getId())).in(OnlCgformField::getDbFieldName, fields);
            List fieldList = this.onlCgformFieldMapper.selectList(query2);
            return fieldList;
        } else {
            throw new JeecgBootException("关联记录字典参数不正确");
        }
    }

    private Map<String, List<DictModel>> b(List<OnlCgformField> fieldList) {
        Map map = new HashMap();

        for(OnlCgformField field : fieldList) {
            String dictTable = field.getDictTable();
            String dictText = field.getDictText();
            String dictCode = field.getDictField();
            if (d.c(field.getFieldShowType())) {
                if (oConvertUtils.isNotEmpty(dictTable) && oConvertUtils.isNotEmpty(dictText) && oConvertUtils.isNotEmpty(dictCode)) {
                    List list = this.sysBaseAPI.queryTableDictItemsByCode(dictTable, dictText, dictCode);
                    map.put(field.getDbFieldName(), list);
                } else if (oConvertUtils.isNotEmpty(dictCode)) {
                    List list = this.sysBaseAPI.queryDictItemsByCode(dictCode);
                    map.put(field.getDbFieldName(), list);
                }
            }
        }

        return map;
    }

    private List<OnlCgformField> a(String cgformId, List<OnlCgformField> all, List<String> selectFieldList, List<String> needList) {
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String userId = sysUser.getId();
        List temp = new ArrayList();

        for(OnlCgformField field : all) {
            String name = field.getDbFieldName();
            if (selectFieldList.indexOf(name) >= 0) {
                temp.add(field);
            }
        }

        List hideColumns = this.onlAuthPageService.queryListHideColumn(userId, cgformId);
        return this.a(hideColumns, true, temp, needList);
    }

    private String a(OnlCgformHead dictTableHead, Map<String, List<DictModel>> map, List<Map<String, Object>> dictTableDataList, String textName, String codeName, Object value) {
        String valueStr = value.toString();
        boolean isTree = "Y".equals(dictTableHead.getIsTree());
        if (isTree) {
            String treeParentIdField = dictTableHead.getTreeParentIdField();
            if (treeParentIdField.equals(textName)) {
                String fieldName = dictTableHead.getTreeFieldname();
                return this.a(dictTableDataList, textName, fieldName, valueStr);
            }
        }

        List newValueList = new ArrayList();
        String[] valueArray = valueStr.split(",");

        for(String tempValueStr : valueArray) {
            String newValue = "";

            for(Map dictMap : dictTableDataList) {
                String codeNameVal = d.a(dictMap, codeName);
                if (tempValueStr.equals(codeNameVal)) {
                    newValue = d.a(dictMap, textName);
                    if (newValue != null) {
                        List<DictModel> textFieldDict = (List)map.get(textName);
                        if (textFieldDict != null && textFieldDict.size() > 0) {
                            for(DictModel dm : textFieldDict) {
                                if (dm.getValue().equals(newValue)) {
                                    newValue = dm.getText();
                                }
                            }
                        }
                    }
                }
            }

            if (oConvertUtils.isNotEmpty(newValue)) {
                newValueList.add(newValue);
            }
        }

        return newValueList.size() > 0 ? String.join(",", newValueList) : "";
    }

    private String a(List<Map<String, Object>> dictTableDataList, String pidField, String fieldName, String value) {
        Map current = this.a(dictTableDataList, value);
        if (current == null) {
            return value;
        } else {
            String pid = d.a(current, pidField);
            if (!oConvertUtils.isEmpty(pid) && !"0".equals(pid)) {
                Map parent = this.a(dictTableDataList, pid);
                if (parent == null) {
                    return value;
                } else {
                    String text = d.a(parent, fieldName);
                    return text != null ? text : value;
                }
            } else {
                return "";
            }
        }
    }

    private Map<String, Object> a(List<Map<String, Object>> dictTableDataList, String id) {
        for(Map item : dictTableDataList) {
            String val = d.a(item, "id");
            if (val != null && val.equals(id)) {
                return item;
            }
        }

        return null;
    }

    public <T> T a(String tableName, QueryWrapper<?> queryWrapper, Class<T> clazz) {
        tableName = SqlInjectionUtil.getSqlInjectTableName(tableName);
        return (T)(clazz == JSONObject.class ? ((OnlCgformFieldMapper)this.baseMapper).doSelect(tableName, queryWrapper) : ((OnlCgformFieldMapper)this.baseMapper).doSelectList(tableName, queryWrapper));
    }

    public <T> T a(String tableName, String fields, QueryWrapper<?> queryWrapper, Class<T> clazz) {
        if (oConvertUtils.isNotEmpty(fields)) {
            fields = SqlInjectionUtil.getSqlInjectField(fields);
        } else {
            fields = "*";
        }

        queryWrapper.select(new String[]{fields});
        return (T)this.a(tableName, queryWrapper, clazz);
    }

    private int a(String tableName, JSONObject updateEntity, UpdateWrapper<?> wrapper) {
        tableName = SqlInjectionUtil.getSqlInjectTableName(tableName);

        for(String field : updateEntity.keySet()) {
            field = SqlInjectionUtil.getSqlInjectField(field);
            wrapper.set(field, updateEntity.get(field));
        }

        return ((OnlCgformFieldMapper)this.baseMapper).doUpdate(tableName, wrapper);
    }

    public Integer queryCountBySql(String tableName, String pidField, String metaPid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select(new String[]{"count(1) as COUNT"});
        if (oConvertUtils.isNotEmpty(pidField)) {
            pidField = SqlInjectionUtil.getSqlInjectField(pidField);
            queryWrapper.eq(pidField, metaPid);
        }

        JSONObject res = (JSONObject)this.a(tableName, queryWrapper, JSONObject.class);
        if (res == null) {
            return 0;
        } else {
            return res.containsKey("COUNT") ? res.getInteger("COUNT") : res.getInteger("count");
        }
    }

    private Integer a(String tableName, QueryWrapper<?> queryWrapper) {
        tableName = SqlInjectionUtil.getSqlInjectTableName(tableName);
        return ((OnlCgformFieldMapper)this.baseMapper).doDelete(tableName, queryWrapper);
    }

    static {
        a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue());
    }
}
