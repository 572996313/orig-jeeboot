//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Generated;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.auth.service.IOnlAuthPageService;
import org.jeecg.modules.online.cgform.dira.a;
import org.jeecg.modules.online.cgform.dird.d;
import org.jeecg.modules.online.cgform.dird.e;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.model.HrefSlots;
import org.jeecg.modules.online.cgform.model.OnlColumn;
import org.jeecg.modules.online.cgform.model.TreeSelectColumn;
import org.jeecg.modules.online.cgform.model.b;
import org.jeecg.modules.online.cgform.model.c;
import org.jeecg.modules.online.cgform.model.g;
import org.jeecg.modules.online.cgform.service.IOnlCgformButtonService;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.cgform.service.IOnlineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service("onlineService")
public class j implements IOnlineService {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(j.class);
    @Autowired
    private IOnlCgformFieldService onlCgformFieldService;
    @Autowired
    private IOnlCgformHeadService onlCgformHeadService;
    @Autowired
    private IOnlCgformButtonService onlCgformButtonService;
    @Lazy
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private IOnlAuthPageService onlAuthPageService;

    public b queryOnlineConfig(OnlCgformHead head, String username) {
        String headId = head.getId();
        boolean isJoinQuery = d.a(head);
        List<OnlCgformField> fieldList = this.b(headId);
        List hideColumns = this.onlAuthPageService.queryHideCode(headId, true);
        List columns = new ArrayList();
        Map<String,List<DictModel>> dictOptions = new HashMap(5);
        List<HrefSlots> fieldHrefSlots = new ArrayList();
        List foreignKeys = new ArrayList();
        List excludeList = new ArrayList();
        Map<String,Integer> fieldRecord = new HashMap(5);
        List selectFieldList = head.getSelectFieldList();

        for(OnlCgformField item : fieldList) {
            String dbFieldName = item.getDbFieldName();
            String mainTable = item.getMainTable();
            String mainField = item.getMainField();
            if (oConvertUtils.isNotEmpty(mainField) && oConvertUtils.isNotEmpty(mainTable)) {
                c foreignKey = new c(dbFieldName, mainField);
                foreignKeys.add(foreignKey);
            }

            if (item.getIsShowList() != null && 1 == item.getIsShowList() && !"id".equals(dbFieldName) && !hideColumns.contains(dbFieldName) && !excludeList.contains(dbFieldName) && (selectFieldList == null || selectFieldList.size() <= 0 || selectFieldList.indexOf(dbFieldName) >= 0)) {
                OnlColumn column = this.a(item, dictOptions, fieldHrefSlots);
                fieldRecord.put(item.getDbFieldName(), 1);
                columns.add(column);
                String linkField = column.getLinkField();
                if (linkField != null && !"".equals(linkField)) {
                    this.a(fieldList, excludeList, columns, dbFieldName, linkField);
                }
            }
        }

        this.a(columns, excludeList);
        if (isJoinQuery) {
//            OnlCgformHead head, Map<String, List<DictModel>> dictOptions, List<HrefSlots> fieldHrefSlots, Map<String, Integer> fieldRecord) {
            List<OnlColumn> subColumns = this.a(head, dictOptions, fieldHrefSlots, fieldRecord);
            if (subColumns.size() > 0) {
                List duplicateFields = new ArrayList();

                for(String fieldName : fieldRecord.keySet()) {
                    if ((Integer)fieldRecord.get(fieldName) > 1) {
                        duplicateFields.add(fieldName);
                    }
                }

                for(OnlColumn column : subColumns) {
                    String fieldName = column.getDataIndex();
                    if (duplicateFields.contains(fieldName)) {
                        String var10001 = d.l(column.getTableName());
                        column.setDataIndex(var10001 + "_" + fieldName);
                    }

                    columns.add(column);
                }
            }
        }

        b model = new b();
        model.setCode(headId);
        model.setTableType(head.getTableType());
        model.setFormTemplate(head.getFormTemplate());
        model.setDescription(head.getTableTxt());
        model.setCurrentTableName(head.getTableName());
        model.setPaginationFlag(head.getIsPage());
        model.setCheckboxFlag(head.getIsCheckbox());
        model.setScrollFlag(head.getScroll());
        model.setRelationType(head.getRelationType());
        model.setColumns(columns);
        model.setDictOptions(dictOptions);
        model.setFieldHrefSlots(fieldHrefSlots);
        model.setForeignKeys(foreignKeys);
        model.setHideColumns(hideColumns);
        List<OnlCgformButton> cgButtonList = this.onlCgformHeadService.queryButtonList(headId, true);
        List cgFinalBtnList = new ArrayList();

        for(OnlCgformButton onlCgformButton : cgButtonList) {
            if (!hideColumns.contains(onlCgformButton.getButtonCode())) {
                cgFinalBtnList.add(onlCgformButton);
            }
        }

        List cgBIButtonList = this.onlCgformButtonService.queryBuiltInButtonList(headId);
        cgFinalBtnList.addAll(cgBIButtonList);
        model.setCgButtonList(cgFinalBtnList);
        OnlCgformEnhanceJs enhanceJs = this.onlCgformHeadService.queryEnhanceJs(headId, "list");
        if (enhanceJs != null && oConvertUtils.isNotEmpty(enhanceJs.getCgJs())) {
            String enhanceJsStr = e.b(enhanceJs.getCgJs(), cgButtonList);
            model.setEnhanceJs(enhanceJsStr);
        }

        if ("Y".equals(head.getIsTree())) {
            model.setPidField(head.getTreeParentIdField());
            model.setHasChildrenField(head.getTreeIdField());
            model.setTextField(head.getTreeFieldname());
        }

        return model;
    }

    private void a(List<OnlColumn> columns, List<String> excludeList) {
        Iterator it = columns.iterator();

        while(it.hasNext()) {
            OnlColumn temp = (OnlColumn)it.next();
            String field = temp.getDataIndex();
            if (excludeList != null && excludeList.indexOf(field) >= 0 && oConvertUtils.isEmpty(temp.getCustomRender())) {
                it.remove();
            }
        }

    }

    private String[] a(String str) {
        String[] arr = new String[]{"", ""};
        if (str != null && !"".equals(str)) {
            JSONObject json = JSON.parseObject(str);
            if (json.containsKey("store")) {
                arr[0] = oConvertUtils.camelToUnderline(json.getString("store"));
            }

            if (json.containsKey("text")) {
                arr[1] = oConvertUtils.camelToUnderline(json.getString("text"));
            }
        }

        return arr;
    }

    private void a(List<OnlCgformField> fieldList, List<String> excludeList, List<OnlColumn> columns, String customRender, String linkField) {
        if (oConvertUtils.isNotEmpty(linkField)) {
            String[] arr = linkField.split(",");

            for(String key : arr) {
                for(OnlCgformField item : fieldList) {
                    String dbFieldName = item.getDbFieldName();
                    if (1 == item.getIsShowList() && key.equals(dbFieldName)) {
                        excludeList.add(key);
                        OnlColumn column = new OnlColumn(item.getDbFieldTxt(), dbFieldName);
                        column.setCustomRender(customRender);
                        columns.add(column);
                        break;
                    }
                }
            }
        }

    }

    public JSONObject queryOnlineFormObj(OnlCgformHead head, OnlCgformEnhanceJs onlCgformEnhanceJs) {
        JSONObject obj = new JSONObject();
        String headId = head.getId();
        String taskId = head.getTaskId();
        List fieldList = this.onlCgformFieldService.queryAvailableFields(headId, head.getTableName(), taskId, false);
        List disabledFieldNameList = new ArrayList();
        if (oConvertUtils.isEmpty(taskId)) {
            List disabledOnlineList = this.onlAuthPageService.queryFormDisabledCode(head.getId());
            if (disabledOnlineList != null && disabledOnlineList.size() > 0 && disabledOnlineList.get(0) != null) {
                disabledFieldNameList.addAll(disabledOnlineList);
            }
        } else {
            List disabledFlowList = this.onlCgformFieldService.queryDisabledFields(head.getTableName(), taskId);
            if (disabledFlowList != null && disabledFlowList.size() > 0 && disabledFlowList.get(0) != null) {
                disabledFieldNameList.addAll(disabledFlowList);
            }
        }

        e.a(onlCgformEnhanceJs, head.getTableName(), fieldList);
        TreeSelectColumn treeColumn = null;
        if ("Y".equals(head.getIsTree())) {
            treeColumn = new TreeSelectColumn();
            treeColumn.setCodeField("id");
            treeColumn.setFieldName(head.getTreeParentIdField());
            treeColumn.setPidField(head.getTreeParentIdField());
            treeColumn.setPidValue("0");
            treeColumn.setHsaChildField(head.getTreeIdField());
            treeColumn.setTableName(d.f(head.getTableName()));
            treeColumn.setTextField(head.getTreeFieldname());
        }

        JSONObject schema = d.a(fieldList, disabledFieldNameList, treeColumn);
        schema.put("table", head.getTableName());
        schema.put("describe", head.getTableTxt());
        obj.put("schema", schema);
        obj.put("head", head);
        List cgButtonList = this.queryFormValidButton(headId);
        if (cgButtonList != null && cgButtonList.size() > 0) {
            obj.put("cgButtonList", cgButtonList);
        }

        if (onlCgformEnhanceJs != null && oConvertUtils.isNotEmpty(onlCgformEnhanceJs.getCgJs())) {
            String tempString = e.c(onlCgformEnhanceJs.getCgJs(), cgButtonList);
            onlCgformEnhanceJs.setCgJs(tempString);
            obj.put("enhanceJs", e.a(onlCgformEnhanceJs.getCgJs()));
        }

        return obj;
    }

    public JSONObject queryOnlineFormObj(OnlCgformHead head, String username) {
        OnlCgformEnhanceJs onlCgformEnhanceJs = this.onlCgformHeadService.queryEnhanceJs(head.getId(), "form");
        return this.queryOnlineFormObj(head, onlCgformEnhanceJs);
    }

    public List<OnlCgformButton> queryFormValidButton(String headId) {
        List<OnlCgformButton> cgButtonList = this.onlCgformHeadService.queryButtonList(headId, false);
        List ls = null;
        if (cgButtonList != null && cgButtonList.size() > 0) {
            LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
            String userId = sysUser.getId();
            List hide = this.onlAuthPageService.queryFormHideButton(userId, headId);
            ls = (List)cgButtonList.stream().filter((button) -> hide == null || hide.indexOf(button.getButtonCode()) < 0).collect(Collectors.toList());
        }

        return ls;
    }

    public JSONObject queryOnlineFormItem(OnlCgformHead head, String username) {
        head.setTaskId((String)null);
        return this.a(head);
    }

    public JSONObject queryFlowOnlineFormItem(OnlCgformHead head, String username, String taskId) {
        head.setTaskId(taskId);
        return this.a(head);
    }

    public String queryEnahcneJsString(String code, String type) {
        String enhanceJsStr = "";
        OnlCgformEnhanceJs enhanceJs = this.onlCgformHeadService.queryEnhanceJs(code, type);
        if (enhanceJs != null && oConvertUtils.isNotEmpty(enhanceJs.getCgJs())) {
            enhanceJsStr = e.b(enhanceJs.getCgJs(), (List)null);
        }

        return enhanceJsStr;
    }

    public JSONObject getOnlineVue3QueryInfo(String headId) {
        OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getById(headId);
        if (head == null) {
            return null;
        } else {
            boolean isJoinQuery = d.a(head);
            List searchFieldList = new ArrayList();
            JSONObject json = this.a(headId, searchFieldList, true, (String)null);
            JSONObject props = json.getJSONObject("properties");
            json.put("title", head.getTableTxt());
            json.put("table", head.getTableName());
            json.put("joinQuery", isJoinQuery);
            json.put("searchFieldList", searchFieldList);
            if (oConvertUtils.isNotEmpty(head.getExtConfigJson())) {
                json.put("extConfigJson", head.getExtConfigJson());
            }

            if (d.aH.equals(head.getTableType())) {
                String subTableString = head.getSubTableStr();
                if (subTableString != null && !"".equals(subTableString)) {
                    String[] arr = subTableString.split(",");

                    for(String subTableName : arr) {
                        OnlCgformHead subTable = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, subTableName));
                        if (subTable != null) {
                            JSONObject subJson = this.a(subTable.getId(), searchFieldList, false, subTableName);
                            subJson.put("title", subTable.getTableTxt());
                            subJson.put("view", "table");
                            props.put(subTableName, subJson);
                        }
                    }
                }
            }

            return json;
        }
    }

    public List<DictModel> getOnlineTableDictData(String table, String text, String code) {
        List<DictModel> textDictList = null;

        try {
            OnlCgformHead head = this.onlCgformHeadService.getTable(table);
            LambdaQueryWrapper query = (new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, head.getId()).eq(OnlCgformField::getDbFieldName, text);
            List list1 = this.onlCgformFieldService.list(query);
            if (list1 != null && list1.size() > 0) {
                OnlCgformField field = (OnlCgformField)list1.get(0);
                String textFieldDictTable = field.getDictTable();
                String textFieldDictCode = field.getDictField();
                String textFieldDictText = field.getDictText();
                if (oConvertUtils.isNotEmpty(textFieldDictTable) && oConvertUtils.isNotEmpty(textFieldDictCode) && oConvertUtils.isNotEmpty(textFieldDictText)) {
                    textDictList = this.sysBaseAPI.queryTableDictItemsByCode(textFieldDictTable, textFieldDictText, textFieldDictCode);
                } else if (oConvertUtils.isNotEmpty(textFieldDictCode)) {
                    textDictList = this.sysBaseAPI.queryDictItemsByCode(textFieldDictCode);
                }
            }
        } catch (Exception e) {
            a.error("他表字段获取字典数据失败", e.getMessage());
        }

        List<DictModel> dataList = this.sysBaseAPI.queryTableDictItemsByCode(table, text, code);
        if (textDictList != null && textDictList.size() > 0) {
            for(DictModel dict : dataList) {
                String replaceText = dict.getText();

                for(DictModel textDict : textDictList) {
                    if (textDict.getValue().equals(replaceText)) {
                        dict.setText(textDict.getText());
                        break;
                    }
                }
            }
        }

        return dataList;
    }

    private JSONObject a(String headId, List<String> searchFieldList, boolean isMain, String tableName) {
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, headId);
        query.and((i) -> i.eq(OnlCgformField::getIsShowList, 1).or().eq(OnlCgformField::getIsQuery, 1));
        query.eq(OnlCgformField::getDbIsPersist, org.jeecg.modules.online.cgform.dirb.b.b);
        query.orderByAsc(OnlCgformField::getOrderNum);
        List<OnlCgformField> list = this.onlCgformFieldService.list(query);

        for(OnlCgformField field : list) {
            field.setFieldDefaultValue((String)null);
            if ("1".equals(field.getQueryConfigFlag())) {
                field.setFieldDefaultValue(field.getQueryDefVal());
                field.setDictField(field.getQueryDictField());
                field.setDictTable(field.getQueryDictTable());
                field.setDictText(field.getQueryDictText());
                field.setFieldShowType(field.getQueryShowType());
            }

            if (1 == field.getIsQuery()) {
                if (isMain) {
                    searchFieldList.add(field.getDbFieldName());
                } else {
                    searchFieldList.add(tableName + "@" + field.getDbFieldName());
                }
            }
        }

        JSONObject json = d.a(list, (List)null, (TreeSelectColumn)null);
        d.b(json);
        return json;
    }

    private List<OnlCgformField> b(String headId) {
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, headId);
        query.orderByAsc(OnlCgformField::getOrderNum);
        return this.onlCgformFieldService.list(query);
    }

    private JSONObject a(OnlCgformHead head) {
        OnlCgformEnhanceJs onlCgformEnhanceJs = this.onlCgformHeadService.queryEnhanceJs(head.getId(), "form");
        JSONObject obj = this.queryOnlineFormObj(head, onlCgformEnhanceJs);
        obj.put("formTemplate", head.getFormTemplate());
        List hideColumns = this.onlAuthPageService.queryHideCode(head.getId(), true);
        if (hideColumns != null && hideColumns.indexOf("update") >= 0) {
            obj.put("form_disable_update", true);
        }

        if (head.getTableType() == 2) {
            JSONObject schema = obj.getJSONObject("schema");
            String subStr = head.getSubTableStr();
            if (oConvertUtils.isNotEmpty(subStr)) {
                List<OnlCgformHead> subHeadList = new ArrayList();

                for(String tbname : subStr.split(",")) {
                    OnlCgformHead tempTable = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tbname));
                    if (tempTable != null) {
                        subHeadList.add(tempTable);
                    }
                }

                if (subHeadList.size() > 0) {
                    Collections.sort(subHeadList, new Comparator<OnlCgformHead>() {
                        public int compare(OnlCgformHead o1, OnlCgformHead o2) {
                            Integer o1OrderNum = o1.getTabOrderNum();
                            if (o1OrderNum == null) {
                                o1OrderNum = 0;
                            }

                            Integer o2OrderNum = o2.getTabOrderNum();
                            if (o2OrderNum == null) {
                                o2OrderNum = 0;
                            }

                            return o1OrderNum.compareTo(o2OrderNum);
                        }
                    });

                    for(OnlCgformHead tempTable : subHeadList) {
                        List<OnlCgformField> subFieldList = this.onlCgformFieldService.queryAvailableFields(tempTable.getId(), tempTable.getTableName(), head.getTaskId(), false);
                        e.b(onlCgformEnhanceJs, tempTable.getTableName(), subFieldList);
                        JSONObject subJson = new JSONObject();
                        new ArrayList();
                        List subDisabledFieldNameList;
                        if (oConvertUtils.isNotEmpty(head.getTaskId())) {
                            subDisabledFieldNameList = this.onlCgformFieldService.queryDisabledFields(tempTable.getTableName(), head.getTaskId());
                        } else {
                            subDisabledFieldNameList = this.onlAuthPageService.queryFormDisabledCode(tempTable.getId());
                        }

                        if (1 == tempTable.getRelationType()) {
                            subJson = d.a(subFieldList, subDisabledFieldNameList, (TreeSelectColumn)null);
                        } else {
                            subJson.put("columns", d.a(subFieldList, subDisabledFieldNameList));
                            List subHideButtons = this.onlAuthPageService.queryListHideButton((String)null, tempTable.getId());
                            subJson.put("hideButtons", subHideButtons);
                        }

                        String foreignKey = this.onlCgformFieldService.queryForeignKey(tempTable.getId(), head.getTableName());
                        subJson.put("foreignKey", foreignKey);
                        subJson.put("id", tempTable.getId());
                        subJson.put("describe", tempTable.getTableTxt());
                        subJson.put("key", tempTable.getTableName());
                        subJson.put("view", "tab");
                        subJson.put("order", tempTable.getTabOrderNum());
                        subJson.put("relationType", tempTable.getRelationType());
                        subJson.put("formTemplate", tempTable.getFormTemplate());
                        schema.getJSONObject("properties").put(tempTable.getTableName(), subJson);
                    }
                }
            }

            if (onlCgformEnhanceJs != null && oConvertUtils.isNotEmpty(onlCgformEnhanceJs.getCgJs())) {
                obj.put("enhanceJs", e.a(onlCgformEnhanceJs.getCgJs()));
            }
        }

        return obj;
    }

    private OnlColumn a(OnlCgformField item, Map<String, List<DictModel>> dictOptions, List<HrefSlots> fieldHrefSlots) {
        String dbFieldName = item.getDbFieldName();
        OnlColumn column = new OnlColumn(item.getDbFieldTxt(), dbFieldName);
        column.setDbType(item.getDbType());
        String dictCode = item.getDictField();
        String view = item.getFieldShowType();
        if (view == null) {
            return column;
        } else {
            if (oConvertUtils.isNotEmpty(dictCode) && !"popup".equals(view) && !"popup_dict".equals(view) && !"link_table".equals(view)) {
                List ls = new ArrayList();
                if (oConvertUtils.isNotEmpty(item.getDictTable())) {
                    ls = this.sysBaseAPI.queryTableDictItemsByCode(item.getDictTable(), item.getDictText(), dictCode);
                } else if (oConvertUtils.isNotEmpty(item.getDictField())) {
                    ls = this.sysBaseAPI.queryDictItemsByCode(dictCode);
                }

                dictOptions.put(dbFieldName, ls);
                column.setCustomRender(dbFieldName);
            }

            if ("switch".equals(view)) {
                List ls = d.b(item);
                dictOptions.put(dbFieldName, ls);
                column.setCustomRender(dbFieldName);
            }

            if ("popup_dict".equals(view)) {
                column.setFieldType(view);
            }

            if ("link_table_field".equals(view)) {
                column.setFieldType(view);
            }

            if ("link_table".equals(view)) {
                column.setFieldType(view);
                column.setHrefSlotName(item.getDictTable());
            }

            if ("link_down".equals(view)) {
                String jsonStr = item.getDictTable();
                a json = (a)JSONObject.parseObject(jsonStr, a.class);

                try {
                    List ls = this.sysBaseAPI.queryTableDictItemsByCode(json.getTable(), json.getTxt(), json.getKey());
                    dictOptions.put(dbFieldName, ls);
                    column.setCustomRender(dbFieldName);
                    column.setLinkField(json.getLinkField());
                } catch (Exception e) {
                    a.warn("联动组件配置错误!", e.getMessage());
                }
            }

            if ("sel_tree".equals(view)) {
                String[] colsInfo = item.getDictText().split(",");
                List ls = this.sysBaseAPI.queryTableDictItemsByCode(item.getDictTable(), colsInfo[2], colsInfo[0]);
                dictOptions.put(dbFieldName, ls);
                column.setCustomRender(dbFieldName);
            }

            if ("cat_tree".equals(view)) {
                String dictText = item.getDictText();
                if (oConvertUtils.isEmpty(dictText)) {
                    String filterSql = d.e(item.getDictField());
                    List ls = this.sysBaseAPI.queryFilterTableDictInfo("SYS_CATEGORY", "NAME", "ID", filterSql);
                    dictOptions.put(dbFieldName, ls);
                    column.setCustomRender(dbFieldName);
                } else {
                    column.setCustomRender("_replace_text_" + dictText);
                }
            }

            if ("sel_depart".equals(view)) {
                String[] temp = this.a(item.getFieldExtendJson());
                String storeField = temp[0].length() > 0 ? temp[0] : "ID";
                String textField = temp[1].length() > 0 ? temp[1] : "DEPART_NAME";
                List ls = this.sysBaseAPI.queryTableDictItemsByCode("SYS_DEPART", textField, storeField);
                dictOptions.put(dbFieldName, ls);
                column.setCustomRender(dbFieldName);
            }

            if ("sel_user".equals(item.getFieldShowType())) {
                String[] temp = this.a(item.getFieldExtendJson());
                String storeField = temp[0].length() > 0 ? temp[0] : "USERNAME";
                String textField = temp[1].length() > 0 ? temp[1] : "REALNAME";
                List ls = this.sysBaseAPI.queryTableDictItemsByCode("SYS_USER", textField, storeField);
                dictOptions.put(dbFieldName, ls);
                column.setCustomRender(dbFieldName);
            }

            if (view.indexOf("file") >= 0) {
                column.setScopedSlots(new g("fileSlot"));
            } else if (view.indexOf("image") >= 0) {
                column.setScopedSlots(new g("imgSlot"));
            } else if (view.indexOf("editor") >= 0) {
                column.setScopedSlots(new g("htmlSlot"));
            } else if (view.equals("date")) {
                column.setScopedSlots(new g("dateSlot"));
            } else if (view.equals("pca")) {
                column.setScopedSlots(new g("pcaSlot"));
            }

            if (StringUtils.isNotBlank(item.getFieldHref())) {
                String slotName = "fieldHref_" + dbFieldName;
                column.setHrefSlotName(slotName);
                fieldHrefSlots.add(new HrefSlots(slotName, item.getFieldHref()));
            }

            if ("1".equals(item.getSortFlag())) {
                column.setSorter(true);
            }

            String fieldExtendJson = item.getFieldExtendJson();
            if (oConvertUtils.isNotEmpty(fieldExtendJson)) {
                column.setFieldExtendJson(fieldExtendJson);
                if (fieldExtendJson.indexOf("showLength") > 0) {
                    JSONObject extJson = JSON.parseObject(fieldExtendJson);
                    if (extJson != null && extJson.get("showLength") != null) {
                        column.setShowLength(oConvertUtils.getInt(extJson.get("showLength")));
                    }
                }
            }

            return column;
        }
    }

    private List<OnlColumn> a(OnlCgformHead head, Map<String, List<DictModel>> dictOptions, List<HrefSlots> fieldHrefSlots, Map<String, Integer> fieldRecord) {
        int tableType = head.getTableType();
        List ls = new ArrayList();
        if (tableType == 2) {
            String subTableString = head.getSubTableStr();
            if (subTableString != null && !"".equals(subTableString)) {
                String[] arr = subTableString.split(",");

                for(String tableName : arr) {
                    LambdaQueryWrapper query = (LambdaQueryWrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tableName);
                    OnlCgformHead sub = (OnlCgformHead)this.onlCgformHeadService.getOne(query);
                    if (sub != null) {
                        List hideColumns = this.onlAuthPageService.queryHideCode(sub.getId(), true);

                        for(OnlCgformField item : this.b(sub.getId())) {
                            if (1 == item.getIsShowList() || 1 == item.getIsQuery()) {
                                String dbFieldName = item.getDbFieldName();
                                if (!hideColumns.contains(dbFieldName) && !"id".equals(dbFieldName)) {
                                    Integer fieldNum = (Integer)fieldRecord.get(dbFieldName);
                                    if (fieldNum == null) {
                                        fieldRecord.put(dbFieldName, 1);
                                    } else {
                                        fieldRecord.put(dbFieldName, fieldNum + 1);
                                    }

                                    OnlColumn column = this.a(item, dictOptions, fieldHrefSlots);
                                    if (1 == item.getIsShowList()) {
                                        column.setTableName(sub.getTableName());
                                        ls.add(column);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return ls;
    }
}
