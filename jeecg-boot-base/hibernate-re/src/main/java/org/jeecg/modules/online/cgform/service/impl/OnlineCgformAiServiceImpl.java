//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Generated;
import org.apache.commons.lang.StringUtils;
import org.jeecg.chatgpt.dto.chat.MultiChatMessage;
import org.jeecg.chatgpt.dto.chat.MultiChatMessage.Role;
import org.jeecg.chatgpt.service.AiChatService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.exception.JeecgBootBizTipException;
import org.jeecg.common.util.AssertUtils;
import org.jeecg.common.util.UUIDGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.dird.d;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.mapper.OnlCgformHeadMapper;
import org.jeecg.modules.online.cgform.model.a;
import org.jeecg.modules.online.cgform.service.IOnlCgformAiService;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service("onlineCgformAiServiceImpl")
public class OnlineCgformAiServiceImpl implements IOnlCgformAiService {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(OnlineCgformAiServiceImpl.class);
    @Autowired
    AiChatService aiChatService;
    @Autowired
    IOnlCgformHeadService onlCgformHeadService;
    @Autowired
    IOnlCgformFieldService onlCgformFieldService;
    @Autowired
    OnlCgformHeadMapper onlCgformHeadMapper;
    private static final List<String> b = Arrays.asList("String", "Datetime", "BigDecimal", "Date", "Text", "int", "Double");
    private static final List<String> c = Arrays.asList("text", "textarea", "password", "date", "datetime", "time", "file", "image");

    public Result<?> genSchema4Modules(String prompt) {
        if (StringUtils.isEmpty(prompt)) {
            return Result.error("请输入提示内容");
        } else {
            String sysMsgContent = "你会根据业务需求设计与需求相关的表单；只输出json数据不要有其他描述。\n输出一个json对象，该对象由表集合（tables)和字典数据（dictData）组成。表集合是一个数组，每个表是一个json对象，属性包含：中文名（tableTxt)、英文名(tableName)、字段列表(fields);字典数据是一个json对象，比如：{\"字典编码\":[{\"value\":\"字典值\",\"text\":\"展示值\"}]};字段列表是一个json数组，包含字段英文名(dbFieldName)、字段中文名(dbFieldTxt)、字段数据库类型(dbType)、字段数据库类型长度(dbLength),字段数据库小数类型长度(dbPointLength)、字段显示类型(fieldShowType)、字段是否必填(fieldMustInput)、字段是否查询字段(isQuery)。字段英文名和表英文名使用下划线命名法(UnderScoreCase);不要生成当前表的主键字段或ID字段;可用的字段数据库类型(dbType)包含：String、Datetime、BigDecimal、Date、Text、int、Double。可用的字段显示类型(fieldShowType)包含：text、textarea、password、date、datetime、time、file、image。字段数据库类型(dbType)和字段显示类型(fieldShowType)只能使用上述选项。密码的显示类型是:password参考json：{\"tables\":[{\"tableName\":\"order\",\"tableTxt\":\"订单表\",\"fields\":[{\"dbFieldName\":\"name\",\"dbFieldTxt\":\"姓名\",\"dbType\":\"string\",\"dbLength\":20,\"dbPointLength\":0,\"fieldShowType\":\"input\",\"fieldMustInput\":\"1\",\"isQuery\":0}]}]}。";
            String schemaJsonStr = this.a(sysMsgContent, "业务需求如下:" + prompt);
            JSONObject schemaJson = null;

            try {
                schemaJson = JSONArray.parseObject(schemaJsonStr);
            } catch (JSONException var10) {
                throw new JeecgBootBizTipException("ai开小差了,请稍后再试.");
            }

            JSONArray tablesJsonArr = schemaJson.getJSONArray("tables");
            if (null != tablesJsonArr && !tablesJsonArr.isEmpty()) {
                List<a> models = new ArrayList();

                for(Object o : tablesJsonArr) {
                    if (o != null) {
                        JSONObject tableJsonObj = (JSONObject)o;
                        models = this.a(tableJsonObj);
                    }
                }

                IOnlCgformHeadService var10001 = this.onlCgformHeadService;
                Objects.requireNonNull(var10001);
                models.forEach(var10001::addAll);
            }

            return Result.ok("生成成功");
        }
    }

    private List<a> a(JSONObject tableJsonObj) {
        List models = new ArrayList();
        List lowerFieldDbTypes = (List)b.stream().map(String::toLowerCase).collect(Collectors.toList());
        List lowerFieldShowTypes = (List)c.stream().map(String::toLowerCase).collect(Collectors.toList());
        a model = new a();
        OnlCgformHead head = this.b(tableJsonObj);
        String tableName = head.getTableName();
        if (null != tableName && !tableName.isEmpty()) {
            head.setTableName(this.a(tableName, (Integer)null));
            model.setHead(head);
            model.setIndexs(Collections.emptyList());
            model.setDeleteFieldIds(Collections.emptyList());
            model.setDeleteIndexIds(Collections.emptyList());
            models.add(model);
            List fields = new ArrayList();
            model.setFields(fields);
            AtomicInteger order = new AtomicInteger(0);
            OnlCgformField idField = this.a("id", "主键", "text", "string", true, true, order.getAndIncrement());
            idField.setDbIsKey(1);
            fields.add(idField);
            fields.add(this.a("create_by", "创建人", "text", "string", false, false, order.getAndIncrement()));
            fields.add(this.a("create_time", "创建日期", "datetime", "Datetime", false, false, order.getAndIncrement()));
            fields.add(this.a("update_by", "更新人", "text", "string", false, false, order.getAndIncrement()));
            fields.add(this.a("update_time", "更新日期", "datetime", "Datetime", false, false, order.getAndIncrement()));
            List fieldNames = new ArrayList(Arrays.asList("id", "create_by", "create_time", "update_by", "update_time"));
            JSONArray fieldsJsonArr = tableJsonObj.getJSONArray("fields");
            fieldsJsonArr.stream().filter(Objects::nonNull).map((o) -> (JSONObject)o).forEach((fieldJsonObj) -> {
                OnlCgformField field = (OnlCgformField)fieldJsonObj.toJavaObject(OnlCgformField.class);
                if (null != field.getDbFieldName() && !field.getDbFieldName().isEmpty()) {
                    if (!field.getDbFieldName().trim().equalsIgnoreCase("id")) {
                        if (!fieldNames.contains(field.getDbFieldName().toLowerCase().trim())) {
                            field.setDbFieldName(a(field.getDbFieldName()));
                            if (null != field.getDbType() && !field.getDbType().isEmpty() && lowerFieldDbTypes.contains(field.getDbType().toLowerCase().trim())) {
                                field.setDbType((String)b.get(lowerFieldDbTypes.indexOf(field.getDbType().toLowerCase().trim())));
                            } else {
                                field.setDbType("String");
                            }

                            if (null != field.getFieldShowType() && !field.getFieldShowType().isEmpty() && lowerFieldShowTypes.contains(field.getFieldShowType().toLowerCase().trim())) {
                                field.setFieldShowType((String)c.get(lowerFieldShowTypes.indexOf(field.getFieldShowType().toLowerCase().trim())));
                            } else {
                                field.setFieldShowType("text");
                            }

                            this.a(field, order.getAndIncrement());
                            fields.add(field);
                            fieldNames.add(field.getDbFieldName().toLowerCase());
                        }
                    }
                }
            });
            return models;
        } else {
            return models;
        }
    }

    public Result<?> genSingleSchema4Modules(String prompt) {
        String sysMsgContent = "严格按照参考json数组的格式输出，不要有其他任何描述，应以[开头，以]结尾\n根据我的业务需求帮我生成一个业务表单，表单参考JSON如下：[\"student\",\"学生表\",[[\"name\",\"姓名\",\"String\",50,0,\"text\"]]]\n在该JSON数组中：\n- 下标0为表单的英文code，使用下划线命名法\n- 下标1为中文名称\n- 下标2为字段数组\n    - 下标0为字段的英文code，使用下划线命名法\n    - 下标1为中文名称\n    - 下标2为字段类型，限定为：String、Datetime、BigDecimal、Date、Text、int、Double\n    - 下标3为字段长度\n    - 下标4为控件类型，限定为：text、textarea、password、date、datetime、time、file、image\n特别注意：\n- 禁止生成主键或 ID 字段。\n- 禁止生成中文标点和引号。\n- 至少生成一个字段。";
        JSONObject aiGenHead = new JSONObject();

        try {
            String schemaJsonStr = this.a(sysMsgContent, "业务需求如下:" + prompt);
            JSONArray schemaJson = JSONArray.parseArray(schemaJsonStr);
            aiGenHead.put("tableName", schemaJson.get(0));
            aiGenHead.put("tableTxt", schemaJson.get(1));
            JSONArray fields = new JSONArray();
            aiGenHead.put("fields", fields);
            JSONArray fieldArrays = schemaJson.getJSONArray(2);
            if (null == fieldArrays || fieldArrays.isEmpty()) {
                return Result.error("AI开小差了,请稍后再试");
            }

            for(Object fieldArray : fieldArrays) {
                JSONArray fieldJson = (JSONArray)fieldArray;
                JSONObject field = new JSONObject();
                field.put("dbFieldName", fieldJson.get(0));
                field.put("dbFieldTxt", fieldJson.get(1));
                field.put("dbType", fieldJson.get(2));
                field.put("dbLength", fieldJson.get(3));
                field.put("fieldShowType", fieldJson.get(4));
                fields.add(field);
            }
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            return Result.error("AI开小差了,请稍后再试");
        }

        List<a> models = this.a(aiGenHead);
        IOnlCgformHeadService var10001 = this.onlCgformHeadService;
        Objects.requireNonNull(var10001);
        models.forEach(var10001::addAll);
        return Result.ok("生成成功");
    }

    public Result<?> aiGenFields(String code, String prompt) {
        AssertUtils.assertNotEmpty(prompt, "业务需求不能为空");
        List existsFieldNames = new ArrayList();
        if (oConvertUtils.isNotEmpty(code)) {
            try {
                OnlCgformHead table = this.onlCgformHeadService.getTable(code);
                String tableName = table.getTableName();
                AssertUtils.assertNotEmpty(tableName, "表名不存在");
                List<OnlCgformField> onlCgformFields = this.onlCgformFieldService.queryFormFieldsByTableName(tableName);
                if (oConvertUtils.isObjectNotEmpty(onlCgformFields)) {
                    existsFieldNames = (List)onlCgformFields.stream().map(OnlCgformField::getDbFieldName).collect(Collectors.toList());
                }
            } catch (org.jeecg.modules.online.config.exception.a var15) {
                throw new JeecgBootBizTipException("获取表信息失败");
            }
        }

        String sysMsgContent = "严格按照参考json数组的格式输出，不要有其他任何描述，应以[开头，以]结尾\n根据我的业务需求以及现有的字段帮我建议一套业务字段，表单参考JSON如下：[[\"name\",\"姓名\",\"String\",50,0,\"text\"]]\n在该JSON数组中：\n- 下标0为字段的英文code，使用下划线命名法\n- 下标1为中文名称\n- 下标2为字段类型，限定为：String、Datetime、BigDecimal、Date、Text、int、Double\n- 下标3为字段长度\n- 下标4为控件类型，限定为：text、textarea、password、date、datetime、time、file、image\n以下字段已经存在,不要重复生成:\n%s\n特别注意：\n- 禁止生成主键或 ID 字段。\n- 禁止生成中文标点和引号。\n- 至少生成三个字段。";
        sysMsgContent = String.format(sysMsgContent, JSON.toJSONString(existsFieldNames));
        String schemaJsonStr = this.a(sysMsgContent, "业务需求如下:" + prompt);

        JSONArray fieldsJson;
        try {
            fieldsJson = JSONArray.parseArray(schemaJsonStr);
        } catch (JSONException var14) {
            throw new JeecgBootBizTipException("AI开小差了,请稍后再试");
        }

        if (fieldsJson != null && !fieldsJson.isEmpty()) {
            List newFields = new ArrayList();
            AtomicInteger orderNum = new AtomicInteger(100);

            for(Object obj : fieldsJson) {
                JSONArray fieldJson = (JSONArray)obj;
                if (fieldJson.size() >= 6) {
                    String fieldName = a(fieldJson.getString(0));
                    if (!existsFieldNames.contains(fieldName)) {
                        existsFieldNames.add(fieldName);
                        OnlCgformField field = new OnlCgformField();
                        field.setDbFieldName(fieldName);
                        field.setDbFieldTxt(fieldJson.getString(1));
                        field.setDbType(fieldJson.getString(2));
                        field.setDbLength(fieldJson.getInteger(3));
                        field.setDbPointLength(fieldJson.getInteger(4));
                        field.setFieldShowType(fieldJson.getString(5));
                        this.a(field, orderNum.getAndIncrement());
                        field.setId((String)null);
                        newFields.add(field);
                    }
                }
            }

            if (newFields.isEmpty()) {
                a.error("[AIGC]生成字段:未能生成有效字段");
                return Result.error("AI开小差了,请稍后再试");
            } else {
                return Result.OK("生成成功", newFields);
            }
        } else {
            a.error("[AIGC]生成字段:未能生成有效字段");
            return Result.error("AI开小差了,请稍后再试");
        }
    }

    public Result<?> aiGenMockData(String code, Integer count) {
        AssertUtils.assertNotEmpty(code, "表code不能为空");

        OnlCgformHead table;
        try {
            table = this.onlCgformHeadService.getTable(code);
            if (table == null) {
                return Result.error("表不存在");
            }
        } catch (org.jeecg.modules.online.config.exception.a var19) {
            throw new JeecgBootBizTipException("获取表信息失败");
        }

        JSONArray mainTableData = this.a(table, (List)null, count);
        if (CollectionUtils.isEmpty(mainTableData)) {
            return Result.error("AI开小差了,请稍后再试");
        } else {
            Map<String,Object> subTableSchemas = new HashMap();
            boolean hasSubTable = org.jeecg.modules.online.cgform.enums.a.e.equals(table.getTableType()) && oConvertUtils.isNotEmpty(table.getSubTableStr());
            if (hasSubTable) {
                for(String subTBName : table.getSubTableStr().split(",")) {
                    OnlCgformHead subTable = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, subTBName));
                    if (subTable == null) {
                        a.warn("【Ai生成Online测试数据】子表{}不存在", subTBName);
                    } else {
                        LambdaQueryWrapper subFieldQuery = (LambdaQueryWrapper)(new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, subTable.getId());
                        List<OnlCgformField> subFieldList = this.onlCgformFieldService.list(subFieldQuery);
                        if (subFieldList == null) {
                            a.warn("【Ai生成Online测试数据】子表{}没有字段", subTBName);
                        } else {
                            List subFieldListFilter = new ArrayList();

                            for(OnlCgformField field : subFieldList) {
                                if (!oConvertUtils.isNotEmpty(field.getMainTable()) || !oConvertUtils.isNotEmpty(field.getMainField())) {
                                    subFieldListFilter.add(field);
                                }
                            }

                            boolean isO2O = org.jeecg.modules.online.cgform.enums.a.g.equals(subTable.getRelationType());
                            int subCount = isO2O ? 1 : 3;
                            a.info("【Ai生成Online测试数据】开始子表{}生成{}条数据", subTBName, mainTableData.size() * subCount);
                            JSONArray subTableData = this.a(subTable, subFieldListFilter, mainTableData.size() * subCount);
                            JSONObject subTableSchema = new JSONObject();
                            subTableSchema.put("data", subTableData);
                            subTableSchema.put("subCount", subCount);
                            subTableSchemas.put(subTBName, subTableSchema);
                        }
                    }
                }
            }

            mainTableData.stream().map((o) -> (JSONObject)o).forEach((data) -> {
                try {
                    String mainId = IdWorker.getIdStr();
                    data.put("id", mainId);
                    if (hasSubTable) {
                        for(String subTBName : subTableSchemas.keySet()) {
                            JSONObject subTableSchema = (JSONObject)subTableSchemas.get(subTBName);
                            int subCount = subTableSchema.getIntValue("subCount");
                            List subTableData = subTableSchema.getJSONArray("data").toJavaList(Object.class);
                            if (subTableData.size() > subCount) {
                                List subTableDataSubList = subTableData.subList(0, subCount);
                                JSONArray subTableDataSubJSONArray = new JSONArray();
                                subTableDataSubJSONArray.addAll(subTableDataSubList);
                                subTableDataSubList.clear();
                                subTableSchema.put("data", subTableData);
                                subTableData = subTableDataSubJSONArray;
                            }

                            data.put(subTBName, subTableData);
                        }
                    }

                    this.onlCgformHeadService.saveManyFormData(code, data, (String)null);
                } catch (BusinessException | org.jeecg.modules.online.config.exception.a e) {
                    a.error("【Ai生成Online测试数据】插入数据失败：" + ((Exception)e).getMessage());
                }

            });
            return Result.ok("生成成功");
        }
    }

    private JSONArray a(OnlCgformHead table, List<OnlCgformField> onlCgformFields, Integer count) {
        if (table == null) {
            throw new JeecgBootBizTipException("表不存在");
        } else {
            if (count == null || count <= 0) {
                count = 3;
            }

            String tableDesign;
            try {
                String tableName = table.getTableName();
                AssertUtils.assertNotEmpty(tableName, "表名不存在");
                JSONArray designJson = new JSONArray();
                designJson.add(tableName);
                designJson.add(table.getTableTxt());
                JSONArray fieldsJson = new JSONArray();
                designJson.add(fieldsJson);
                if (onlCgformFields == null) {
                    onlCgformFields = this.onlCgformFieldService.queryFormFieldsByTableName(tableName);
                }

                if (oConvertUtils.isObjectNotEmpty(onlCgformFields)) {
                    for(OnlCgformField onlCgformField : onlCgformFields) {
                        String dbFieldName = onlCgformField.getDbFieldName();
                        if (!d.i(dbFieldName) && !"bpm_status".equalsIgnoreCase(dbFieldName)) {
                            JSONArray fieldJson = new JSONArray();
                            fieldJson.add(dbFieldName);
                            fieldJson.add(onlCgformField.getDbFieldTxt());
                            fieldJson.add(onlCgformField.getDbType());
                            fieldJson.add(onlCgformField.getDbLength());
                            fieldJson.add(onlCgformField.getDbPointLength());
                            fieldJson.add(onlCgformField.getFieldShowType());
                            fieldsJson.add(fieldJson);
                        }
                    }
                }

                tableDesign = designJson.toJSONString();
            } catch (Exception var13) {
                throw new JeecgBootBizTipException("获取表信息失败");
            }

            String sysMsgContent = "严格按照参考json数组的格式输出，不要有其他任何描述，应以[开头，以]结尾 \n根据我的表设计，生成测试数据，参考json如下：[{\"fieldName\":\"val\"},{\"fieldName\":\"val\"}] \n表设计格式说明: \n- 下标0为表单的英文code，使用下划线命名法 \n- 下标1为中文名称 \n- 下标2为字段数组 \n    - 下标0为字段的英文code，使用下划线命名法 \n    - 下标1为中文名称 \n    - 下标2为字段类型，限定为：String、Datetime、BigDecimal、Date、Text、int、Double \n    - 下标3为字段长度 \n    - 下标4为控件类型，限定为：text、textarea、password、date、datetime、time、file、image \n要求： \n- 生成" + count + "条以上数据。 \n- 数据类型要与字段设计相匹配。 \n- 所有字段都要生成测试数据（如涉及姓名，尽量避免使用“张三”、“李四”等，要尽量生成看起来更真实的姓名。 \n- date控件要生成日期格式(2023-10-01),datetime控件生成日期时间(2023-10-01 10:00:00),time控件生成时间(10:00:00)- 生成的数据要与表设计的业务相关。 ";
            String schemaJsonStr = this.a(sysMsgContent, "表设计如下： \n" + tableDesign);
            a.info(schemaJsonStr);

            JSONArray datasJson;
            try {
                datasJson = JSONArray.parseArray(schemaJsonStr);
            } catch (JSONException var12) {
                a.error("[AIGC]生成测试数据,未能生成有效数据");
                throw new JeecgBootBizTipException("AI开小差了,请稍后再试");
            }

            if (datasJson != null && !datasJson.isEmpty()) {
                JSONArray jsonArray = new JSONArray((List)datasJson.stream().filter(Objects::nonNull).collect(Collectors.toList()));
                return jsonArray;
            } else {
                a.error("[AIGC]生成测试数据,未能生成有效数据");
                throw new JeecgBootBizTipException("AI开小差了,请稍后再试");
            }
        }
    }

    private OnlCgformField a(String fieldName, String fieldTxt, String showType, String dbType, boolean must, boolean readOnly, int orderNum) {
        OnlCgformField field = this.a(new OnlCgformField(), orderNum);
        fieldName = a(fieldName);
        field.setDbFieldName(fieldName);
        field.setDbFieldTxt(fieldTxt);
        field.setFieldMustInput(must ? "1" : "0");
        field.setIsShowForm(0);
        field.setIsShowList(0);
        field.setIsReadOnly(readOnly ? 1 : 0);
        field.setFieldShowType(showType);
        field.setIsQuery(0);
        field.setDbPointLength(0);
        field.setDbType(dbType);
        field.setDbIsNull(must ? 0 : 1);
        return field;
    }

    private OnlCgformField a(OnlCgformField field, int orderNum) {
        field.setId(UUIDGenerator.generate());
        a(field);
        if (null == field.getDbPointLength()) {
            field.setDbPointLength(0);
        }

        field.setFieldLength(120);
        field.setQueryConfigFlag("0");
        field.setQueryMode("single");
        field.setOrderNum(orderNum);
        field.setIsReadOnly(0);
        field.setIsShowForm(1);
        field.setIsShowList(1);
        field.setDbIsNull(1);
        field.setDbIsKey(0);
        if (null == field.getIsQuery()) {
            field.setIsQuery(0);
        }

        if (null == field.getFieldMustInput()) {
            field.setFieldMustInput("0");
        }

        return field;
    }

    private static void a(OnlCgformField field) {
        if (null == field.getDbLength() || 0 == field.getDbLength() || field.getDbLength() < 0) {
            String dbType = field.getDbType();
            if (dbType != null) {
                dbType = dbType.toLowerCase();
                if (!"int".equals(dbType) && !"double".equals(dbType) && !"bigdecimal".equals(dbType) && !"integer".equals(dbType) && !"decimal".equals(dbType)) {
                    if (!"string".equals(dbType) && !"password".equals(dbType)) {
                        if (!"datetime".equals(dbType) && !"date".equals(dbType)) {
                            if (!"text".equals(dbType) && !"blob".equals(dbType) && !"image".equals(dbType)) {
                                field.setDbLength(50);
                            } else {
                                field.setDbLength(0);
                            }
                        } else {
                            field.setDbLength(0);
                        }
                    } else {
                        field.setDbLength(32);
                    }
                } else {
                    field.setDbLength(10);
                }
            } else {
                field.setDbLength(50);
            }
        }

    }

    public static String a(String para) {
        int length = 3;
        if (null == para) {
            return para;
        } else if (para.length() < length) {
            return para.toLowerCase();
        } else {
            StringBuilder sb = new StringBuilder(para);
            int temp = 0;

            for(int i = 2; i < para.length(); ++i) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, "_");
                    ++temp;
                }
            }

            return sb.toString().toLowerCase();
        }
    }

    private OnlCgformHead b(JSONObject tableJsonObj) {
        OnlCgformHead head = new OnlCgformHead();
        head.setTableName(tableJsonObj.getString("tableName"));
        head.setTableName(a(head.getTableName()));
        head.setTableTxt(tableJsonObj.getString("tableTxt"));
        head.setTableVersion(1);
        head.setTableType(org.jeecg.modules.online.cgform.enums.a.d);
        head.setFormCategory("temp");
        head.setIdType("UUID");
        head.setIsCheckbox("Y");
        head.setThemeTemplate("normal");
        head.setFormTemplate("1");
        head.setScroll(1);
        head.setIsPage("Y");
        head.setIsTree("N");
        head.setExtConfigJson("{\"reportPrintShow\":0,\"reportPrintUrl\":\"\",\"joinQuery\":0,\"modelFullscreen\":0,\"modalMinWidth\":\"\",\"commentStatus\":0,\"tableFixedAction\":1,\"tableFixedActionType\":\"right\"}");
        head.setIsDesForm("N");
        head.setDesFormCode("");
        return head;
    }

    private String a(String tableName, Integer count) {
        String originTableName = tableName;
        if (null != count && count >= 0) {
            count = count + 1;
            tableName = tableName + "_" + count;
        } else {
            count = 0;
        }

        if (null != tableName && !tableName.isEmpty()) {
            return this.onlCgformHeadMapper.exists((Wrapper)Wrappers.lambdaQuery(OnlCgformHead.class).eq(OnlCgformHead::getTableName, tableName)) ? this.a(originTableName, count) : tableName;
        } else {
            return "ai_table_" + System.currentTimeMillis();
        }
    }

    private String a(String sysMsgContent, String prompt) {
        List messages = new LinkedList();
        messages.add(MultiChatMessage.builder().role(Role.SYSTEM).content(sysMsgContent).build());
        messages.add(MultiChatMessage.builder().role(Role.USER).content(prompt).build());
        String gptResp = this.aiChatService.multiCompletions(messages);
        if (StringUtils.isEmpty(gptResp)) {
            throw new JeecgBootBizTipException("如果您想使用AI助手，请先设置相应配置!");
        } else {
            a.debug("ai返回结果" + gptResp);
            if (gptResp.contains("</think>")) {
                String[] thinkSplit = gptResp.split("</think>");
                gptResp = thinkSplit[thinkSplit.length - 1];
            }

            Pattern pattern = Pattern.compile("\\{.*}|\\[.*]", 32);
            Matcher matcher = pattern.matcher(gptResp);
            String returnData = "";
            if (matcher.find()) {
                returnData = matcher.group(0);
            }

            return returnData;
        }
    }
}
