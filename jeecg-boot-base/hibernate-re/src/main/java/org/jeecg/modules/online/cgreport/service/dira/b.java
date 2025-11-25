//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.service.dira;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportAPIService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
import org.jeecg.modules.online.config.dirb.a;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.excel.export.ExcelExportServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service("onlCgreportAPIService")
public class b implements IOnlCgreportAPIService {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(b.class);
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
    private a onlReportQueryBlackListHandler;

    public Map<String, Object> getDataById(String id, Map<String, Object> params) {
        return this.getData(id, (String)null, params);
    }

    public Map<String, Object> getDataByCode(String code, Map<String, Object> params) {
        return this.getData((String)null, code, params);
    }

    public Map<String, Object> getData(String id, String code, Map<String, Object> params) {
        OnlCgreportHead head = null;
        if (oConvertUtils.isNotEmpty(id)) {
            head = (OnlCgreportHead)this.onlCgreportHeadService.getById(id);
        } else if (oConvertUtils.isNotEmpty(code)) {
            LambdaQueryWrapper<OnlCgreportHead> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(OnlCgreportHead::getCode, code);
            head = (OnlCgreportHead)this.onlCgreportHeadService.getOne(queryWrapper);
        }

        if (head == null) {
            throw new JeecgBootException("实体不存在");
        } else {
            try {
                String cgrSql = head.getCgrSql().trim();
                String dbKey = head.getDbSource();
                return this.executeSelectSqlRoute(dbKey, cgrSql, params, head.getId());
            } catch (Exception e) {
                a.error(e.getMessage(), e);
                throw new JeecgBootException("SQL执行失败：" + e.getMessage());
            }
        }
    }

    public Map<String, Object> executeSelectSqlRoute(String dbKey, String sql, Map<String, Object> params, String headId) throws Exception {
        if (!this.onlReportQueryBlackListHandler.isPass(sql)) {
            throw new JeecgBootException(this.onlReportQueryBlackListHandler.getError());
        } else if (StringUtils.isNotBlank(dbKey)) {
            a.debug("Online报表: 走了多数据源逻辑");
            return this.onlCgreportHeadService.executeSelectSqlDynamic(dbKey, sql, params, headId);
        } else {
            a.debug("Online报表: 走了稳定逻辑");
            return this.onlCgreportHeadService.executeSelectSql(sql, headId, params);
        }
    }

    public Workbook getReportWorkbook(String reportId, Map<String, Object> params) {
        LambdaQueryWrapper<OnlCgreportItem> itemsQuery = (LambdaQueryWrapper)(new LambdaQueryWrapper<OnlCgreportItem>()).eq(OnlCgreportItem::getCgrheadId, reportId);
        itemsQuery.orderByAsc(OnlCgreportItem::getOrderNum);
        List itemList = this.onlCgreportItemService.list(itemsQuery);
        List entityList = new ArrayList();
        Map groupMap = new HashMap(5);
        List totalList = new ArrayList();
        Map totalMap = new HashMap(5);

        for(Object fieldObj : itemList) {
            OnlCgreportItem field = ((OnlCgreportItem) fieldObj);
            String fieldType = field.getFieldType();
            String exportFieldName = field.getFieldName();
            if ("1".equals(oConvertUtils.getString(field.getIsShow()))) {
                ExcelExportEntity expEntity = new ExcelExportEntity(field.getFieldTxt(), exportFieldName, 15);
                this.a(field, expEntity);
                if ("date".equalsIgnoreCase(field.getFieldType())) {
                    expEntity.setFormat("yyyy-MM-dd");
                } else if ("datetime".equalsIgnoreCase(field.getFieldType())) {
                    expEntity.setFormat("yyyy-MM-dd HH:mm:ss");
                }

                String groupTitle = field.getGroupTitle();
                if (oConvertUtils.isNotEmpty(groupTitle)) {
                    List groupList = new ArrayList();
                    if (groupMap.containsKey(groupTitle)) {
                        groupList = (List)groupMap.get(groupTitle);
                        groupList.add(exportFieldName);
                    } else {
                        ExcelExportEntity subEntity = new ExcelExportEntity(groupTitle, groupTitle, true);
                        entityList.add(subEntity);
                        groupList.add(exportFieldName);
                    }

                    groupMap.put(groupTitle, groupList);
                    expEntity.setColspan(true);
                }

                if (oConvertUtils.isNotEmpty(fieldType) && oConvertUtils.isEmpty(field.getDictCode()) && ("Integer".equals(fieldType) || "Long".equals(fieldType))) {
                    expEntity.setType(4);
                }

                entityList.add(expEntity);
            }

            if ("1".equals(oConvertUtils.getString(field.getIsTotal()))) {
                totalList.add(exportFieldName);
            }
        }

        for(Object groupObj : groupMap.entrySet()) {
            Map.Entry group = (Map.Entry)groupObj;
            String key = (String)group.getKey();
            List values = (List)group.getValue();

            for(Object subEntityObj : entityList) {
                ExcelExportEntity subEntity = (ExcelExportEntity)subEntityObj;
                if (key.equals(subEntity.getName()) && subEntity.isColspan()) {
                    subEntity.setSubColumnList(values);
                }
            }
        }

        Workbook wb = new HSSFWorkbook();
        boolean hasNext = true;
        Integer pageNo = 1;
        params.put("pageSize", 10000);

        while(hasNext) {
            Integer reslutMap = pageNo;
            pageNo = pageNo + 1;
            params.put("pageNo", reslutMap);
            Map<String, Object> reslutMap2 = this.getDataById(reportId, params);
            List<Map<String,Object>> records = (List<Map<String,Object>>)reslutMap2.get("records");
            if (records != null && records.size() != 0) {
                if (totalList != null && totalList.size() > 0) {
                    for(Object fieldKeyObj : totalList) {
                        String fieldKey = (String)fieldKeyObj;
                        BigDecimal count = new BigDecimal((double)0.0F);

                        for(Map map : records) {
                            String fieldVal = map.get(fieldKey).toString();
                            if (fieldVal.matches("-?\\d+(.\\d+)?")) {
                                BigDecimal val = new BigDecimal(fieldVal);
                                count = count.add(val);
                            }
                        }

                        totalMap.put(fieldKey, count);
                    }

                    records.add(totalMap);
                }

                ExcelExportServer server = new ExcelExportServer();
                ExportParams exportParams = new ExportParams();
                server.createSheetForMap(wb, exportParams, entityList, records);
            } else {
                hasNext = false;
            }
        }

        return wb;
    }

    private void a(OnlCgreportItem field, ExcelExportEntity expEntity) {
        String EXCEL_SPLIT_TAG = "_";
        String TEMP_EXCEL_SPLIT_TAG = "---";
        String dictCode = field.getDictCode();
        List<DictModel> ls = this.onlCgreportHeadService.queryColumnDictList(oConvertUtils.getString(dictCode), (List)null, (String)null);
        if (ls != null && ls.size() > 0) {
            List dictReplaces = new ArrayList();

            for(DictModel d : ls) {
                if (d != null && d.getValue() != null) {
                    if (d.getValue().contains(EXCEL_SPLIT_TAG)) {
                        String val = d.getValue().replace(EXCEL_SPLIT_TAG, TEMP_EXCEL_SPLIT_TAG);
                        dictReplaces.add(d.getText() + EXCEL_SPLIT_TAG + val);
                    } else {
                        dictReplaces.add(d.getText() + EXCEL_SPLIT_TAG + d.getValue());
                    }
                }
            }

            expEntity.setReplace((String[])dictReplaces.toArray(new String[dictReplaces.size()]));
        }

        Object replace_val = field.getReplaceVal();
        if (oConvertUtils.isNotEmpty(replace_val)) {
            expEntity.setReplace(replace_val.toString().split(","));
        }

    }
}
