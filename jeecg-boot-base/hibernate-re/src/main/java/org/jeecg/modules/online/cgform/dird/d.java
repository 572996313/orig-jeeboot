//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dird;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Generated;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.MatchTypeEnum;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.system.vo.SysUserCacheInfo;
import org.jeecg.common.util.*;
import org.jeecg.common.util.dira.dira.*;
import org.jeecg.config.mybatis.MybatisPlusSaasConfig;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.entity.OnlCgformIndex;
import org.jeecg.modules.online.cgform.enums.CgformValidPatternEnum;
import org.jeecg.modules.online.cgform.mapper.OnlCgformHeadMapper;
import org.jeecg.modules.online.cgform.model.TreeSelectColumn;
import org.jeecg.modules.online.config.model.OnlineFieldConfig;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class d {
    @Generated
    private static final Logger aZ = LoggerFactory.getLogger(d.class);
    public static final String a = "SELECT ";
    public static final String b = " FROM ";
    public static final String c = " JOIN ";
    public static final String d = " ON ";
    public static final String e = " AND ";
    public static final String f = " like ";
    public static final String g = " COUNT(*) ";
    public static final String h = " where 1=1  ";
    public static final String i = " where  ";
    public static final String j = " ORDER BY ";
    public static final String k = "asc";
    public static final String l = "desc";
    public static final String m = "=";
    public static final String n = "!=";
    public static final String o = ">=";
    public static final String p = ">";
    public static final String q = "<=";
    public static final String r = "<";
    public static final String s = " or ";
    public static final String t = "jeecg_row_key";
    public static final String u = "Y";
    public static final String v = "$";
    public static final String w = "CREATE_TIME";
    public static final String x = "CREATE_BY";
    public static final String y = "UPDATE_TIME";
    public static final String z = "UPDATE_BY";
    public static final String A = "SYS_ORG_CODE";
    public static final int B = 2;
    public static final String C = "'";
    public static final String D = "N";
    public static final String E = ",";
    public static final String F = "single";
    public static final String G = "id";
    public static final String H = "bpm_status";
    public static final String I = "1";
    public static final String J = "force";
    public static final String K = "normal";
    public static final String L = "switch";
    public static final String M = "popup";
    public static final String N = "popup_dict";
    public static final String O = "sel_search";
    public static final String P = "image";
    public static final String Q = "file";
    public static final String R = "sel_tree";
    public static final String S = "cat_tree";
    public static final String T = "link_down";
    public static final String U = "date";
    public static final String V = "SYS_USER";
    public static final String W = "REALNAME";
    public static final String X = "USERNAME";
    public static final String Y = "SYS_DEPART";
    public static final String Z = "DEPART_NAME";
    public static final String aa = "ID";
    public static final String ab = "SYS_CATEGORY";
    public static final String ac = "NAME";
    public static final String ad = "CODE";
    public static final String ae = "ID";
    public static final String af = "PID";
    public static final String ag = "HAS_CHILD";
    public static final String ah = "sel_search";
    public static final String ai = "link_table";
    public static final String aj = "link_table_field";
    public static final String ak = "sub_table_design_";
    public static final String al = "sub-table-design_";
    public static final String am = "sub-table-one2one_";
    public static final String an = "import";
    public static final String ao = "export";
    public static final String ap = "query";
    public static final String aq = "form";
    public static final String ar = "list";
    public static final String as = "1";
    public static final String at = "start";
    public static final String au = "erp";
    public static final String av = "innerTable";
    public static final String aw = "exportSingleOnly";
    public static final String ax = "isSingleTableImport";
    public static final String ay = "validateStatus";
    public static final String az = "1";
    public static final String aA = "foreignKeys";
    public static final int aB = 1;
    public static final int aC = 2;
    public static final int aD = 0;
    public static final int aE = 1;
    public static final int aF = 1;
    public static final String aG = "1";
    public static final Integer aH = 2;
    public static final String aI = "1";
    public static final String aJ = "id";
    public static final String aK = "center";
    public static final String aL = "modules/bpm/task/form/OnlineFormDetail";
    public static final String aM = "check/onlineForm/detail";
    public static final String aN = "onl_";
    public static final String aO = "jeecg_submit_form_and_flow";
    public static final String aP = "joinQuery";
    public static final String aQ = "properties";
    public static final String aR = "title";
    public static final String aS = "view";
    public static final String aT = "table";
    public static final String aU = "searchFieldList";
    public static final String aV = "switchOptions";
    public static final String aW = "extConfigJson";
    public static final String aX = "0";
    public static final String aY = "1";
    private static final String ba = "beforeAdd,beforeEdit,afterAdd,afterEdit,beforeDelete,afterDelete,mounted,created";
    private static String bb;

    public static boolean a(OnlCgformHead head) {
        if (head != null && aH.equals(head.getTableType())) {
            String theme = head.getThemeTemplate();
            if ("erp".equals(theme) || "innerTable".equals(theme) || "Y".equals(head.getIsTree())) {
                return false;
            }

            String extConfigJson = head.getExtConfigJson();
            if (extConfigJson != null && !"".equals(extConfigJson)) {
                JSONObject json = JSON.parseObject(extConfigJson);
                if (json.containsKey("joinQuery") && 1 == json.getInteger("joinQuery")) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void a(String tbname, List<OnlCgformField> fieldList, StringBuffer sb) {
        if (fieldList != null && fieldList.size() != 0) {
            sb.append("SELECT ");
            int size = fieldList.size();
            boolean has = false;

            for(int i = 0; i < size; ++i) {
                OnlCgformField item = (OnlCgformField)fieldList.get(i);
                item.setDbFieldName(SqlInjectionUtil.getSqlInjectField(item.getDbFieldName()));
                if (org.jeecg.modules.online.cgform.dirb.b.b.equals(item.getDbIsPersist())) {
                    if ("id".equals(item.getDbFieldName())) {
                        has = true;
                    }

                    if ("cat_tree".equals(item.getFieldShowType()) && oConvertUtils.isNotEmpty(item.getDictText())) {
                        sb.append(item.getDictText() + ",");
                    }

                    if (i == size - 1) {
                        sb.append(item.getDbFieldName() + " ");
                    } else {
                        sb.append(item.getDbFieldName() + ",");
                    }
                }
            }

            String lastString = sb.substring(sb.length() - 1);
            if (",".equals(lastString)) {
                sb.deleteCharAt(sb.length() - 1);
            }

            if (!has) {
                sb.append(",id");
            }
        } else {
            sb.append("SELECT id");
        }

        sb.append(" FROM " + f(tbname));
    }

    public static String a(String value) {
        return " to_date('" + value + "','yyyy-MM-dd HH24:mi:ss')";
    }

    public static String b(String value) {
        return " to_date('" + value + "','yyyy-MM-dd')";
    }

    public static boolean c(String filedShowType) {
        if ("list".equals(filedShowType)) {
            return true;
        } else if ("radio".equals(filedShowType)) {
            return true;
        } else if ("checkbox".equals(filedShowType)) {
            return true;
        } else {
            return "list_multi".equals(filedShowType);
        }
    }

    public static boolean a(OnlCgformField item) {
        if (oConvertUtils.isNotEmpty(item.getMainField()) && oConvertUtils.isNotEmpty(item.getMainTable())) {
            String ext = item.getFieldExtendJson();
            if (oConvertUtils.isNotEmpty(ext) && ext.indexOf("textField") > 0) {
                item.setDictTable(item.getMainTable());
                item.setDictField(item.getMainField());
                item.setFieldShowType("sel_search");
                JSONObject json = JSON.parseObject(ext);
                item.setDictText(json.getString("textField"));
                return true;
            }
        }

        return false;
    }

    public static void a(StringBuilder builder, String fieldName, JSONObject parameter, MatchTypeEnum matchType, JSONObject subTable, boolean isFirst) {
        if (!isFirst) {
            builder.append(" ").append(matchType.getValue()).append(" ");
        }

        String type = parameter.getString("type");
        String nativeValue = parameter.getString("val");
        String value = org.jeecg.modules.online.cgform.dird.l.a(type, nativeValue);
        QueryRuleEnum rule = QueryRuleEnum.getByValue(parameter.getString("rule"));
        if (rule == null) {
            rule = QueryRuleEnum.EQ;
        }

        if (subTable != null) {
            String subTableName = subTable.getString("subTableName");
            String subField = subTable.getString("subField");
            String mainTable = subTable.getString("mainTable");
            String mainField = subTable.getString("mainField");
            builder.append("(").append(mainField).append(" IN (SELECT ").append(subField).append(" FROM ").append(subTableName).append(" WHERE ");
            if ("popup".equals(type)) {
                builder.append(b(fieldName, nativeValue));
            } else {
                builder.append(fieldName);
                org.jeecg.modules.online.cgform.dird.l.a(builder, rule, nativeValue, value, type);
            }

            builder.append("))");
        } else if ("popup".equals(type)) {
            builder.append(b(fieldName, nativeValue));
        } else {
            builder.append(fieldName);
            org.jeecg.modules.online.cgform.dird.l.a(builder, rule, nativeValue, value, type);
        }

    }

    public static Map<String, Object> a(HttpServletRequest request) {
        Map properties = request.getParameterMap();
        Map returnMap = new HashMap(5);
        Iterator entries = properties.entrySet().iterator();
        String name = "";
        String value = "";

        for(Object valueObj = null; entries.hasNext(); returnMap.put(name, value)) {
            Map.Entry entry = (Map.Entry)entries.next();
            name = (String)entry.getKey();
            valueObj = entry.getValue();
            if (!"_t".equals(name) && null != valueObj) {
                if (!(valueObj instanceof String[])) {
                    value = valueObj.toString();
                } else {
                    String[] values = (String[])valueObj;

                    for(int i = 0; i < values.length; ++i) {
                        value = values[i] + ",";
                    }

                    value = value.substring(0, value.length() - 1);
                }
            } else {
                value = "";
            }
        }

        return returnMap;
    }

    public static boolean a(String fieldName, List<OnlCgformField> fieldList) {
        for(OnlCgformField f : fieldList) {
            if (fieldName.equals(f.getDbFieldName())) {
                return true;
            }
        }

        return false;
    }

    public static JSONObject a(List<OnlCgformField> fieldList, List<String> disabledFieldNameList, TreeSelectColumn treeColumn) {
        new JSONObject();
        List required = new ArrayList();
        List props = new ArrayList();
        ISysBaseAPI sysBaseAPI = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);
        OnlCgformHeadMapper mapper = (OnlCgformHeadMapper)SpringContextUtils.getBean(OnlCgformHeadMapper.class);
        List excludeFields = new ArrayList();

        for(OnlCgformField item : fieldList) {
            String field = item.getDbFieldName();
            if (!"id".equals(field) && !excludeFields.contains(field)) {
                String title = item.getDbFieldTxt();
                if ("1".equals(item.getFieldMustInput())) {
                    required.add(field);
                }

                String filedShowType = item.getFieldShowType();
                org.jeecg.common.util.dira.b prop = null;
                if ("switch".equals(filedShowType)) {
                    prop = new Classh(field, title, item.getFieldExtendJson());
                } else if (!c(filedShowType) && !"link_table_field".equals(filedShowType)) {
                    if (!"sel_search".equals(filedShowType) && !a(item)) {
                        if ("link_table".equals(filedShowType)) {
                            prop = new Classa(field, title, item.getDictTable(), item.getDictField(), item.getDictText());
                            prop.setView("link_table");
                        } else if (org.jeecg.modules.online.cgform.dird.j.a(item.getDbType())) {
                            Classd numProp = new Classd(field, title, "number");
                            if (CgformValidPatternEnum.INTEGER.getType().equals(item.getFieldValidType())) {
                                numProp.setPattern(CgformValidPatternEnum.INTEGER.getPattern());
                            }

                            prop = numProp;
                        } else if (!"popup".equals(filedShowType)) {
                            if ("popup_dict".equals(filedShowType)) {
                                Classe popProp = new Classe(field, title, item.getDictTable(), item.getDictText(), item.getDictField());
                                String extJsonString = item.getFieldExtendJson();
                                if (extJsonString != null && !"".equals(extJsonString)) {
                                    JSONObject extJson = JSONObject.parseObject(extJsonString);
                                    if (extJson.containsKey("popupMulti")) {
                                        popProp.setPopupMulti(extJson.getBoolean("popupMulti"));
                                    }
                                }

                                prop = popProp;
                            } else if ("link_down".equals(filedShowType)) {
                                Classc linkProp = new Classc(field, title, item.getDictTable());
                                a(linkProp, fieldList, excludeFields, required);
                                prop = linkProp;
                            } else if ("sel_tree".equals(filedShowType)) {
                                String dictText = item.getDictText();
                                String[] cols = dictText.split(",");
                                String var46 = item.getDictTable();
                                String dict = var46 + "," + cols[2] + "," + cols[0];
                                Classi treeProp = new Classi(field, title, dict, cols[1], item.getDictField());
                                if (cols.length > 3) {
                                    treeProp.setHasChildField(cols[3]);
                                }

                                prop = treeProp;
                            } else if ("cat_tree".equals(filedShowType)) {
                                String dictText = item.getDictText();
                                String dictCode = item.getDictField();
                                String pid = "0";
                                if (oConvertUtils.isNotEmpty(dictCode) && !"0".equals(dictCode)) {
                                    pid = mapper.queryCategoryIdByCode(dictCode);
                                }

                                if (oConvertUtils.isEmpty(dictText)) {
                                    prop = new Classi(field, title, pid);
                                } else {
                                    prop = new Classi(field, title, pid, dictText);
                                    org.jeecg.common.util.dira.b prop2 = new Classb(dictText, dictText);
                                    props.add(prop2);
                                }
                            } else if (treeColumn != null && field.equals(treeColumn.getFieldName())) {
                                String var10000 = treeColumn.getTableName();
                                String dict = var10000 + "," + treeColumn.getTextField() + "," + treeColumn.getCodeField();
                                Classi treeProp = new Classi(field, title, dict, treeColumn.getPidField(), treeColumn.getPidValue());
                                treeProp.setHasChildField(treeColumn.getHsaChildField());
                                treeProp.setPidComponent(1);
                                prop = treeProp;
                            } else {
                                Classg stringProp = new Classg(field, title, filedShowType, item.getDbLength());
                                prop = stringProp;
                            }
                        } else {
                            Classf popProp = new Classf(field, title, item.getDictTable(), item.getDictText(), item.getDictField());
                            String backFields = item.getDictText();
                            if (backFields != null && !"".equals(backFields)) {
                                String[] tempArr = backFields.split(",");

                                for(String str : tempArr) {
                                    if (!a(str, fieldList)) {
                                        org.jeecg.common.util.dira.dira.Classb prop2 = new Classb(str, str);
                                        prop2.setOrder(item.getOrderNum());
                                        props.add(prop2);
                                    }
                                }
                            }

                            String extJsonString = item.getFieldExtendJson();
                            if (extJsonString != null && !"".equals(extJsonString)) {
                                JSONObject extJson = JSONObject.parseObject(extJsonString);
                                if (extJson.containsKey("popupMulti")) {
                                    popProp.setPopupMulti(extJson.getBoolean("popupMulti"));
                                }
                            }

                            prop = popProp;
                        }
                    } else {
                        prop = new Classa(field, title, item.getDictTable(), item.getDictField(), item.getDictText());
                    }
                } else {
                    prop = new Classa(field, filedShowType, title, item.getDictTable(), item.getDictField(), item.getDictText());
                    if (org.jeecg.modules.online.cgform.dird.j.a(item.getDbType())) {
                        prop.setType("number");
                    }
                }

                if (oConvertUtils.isNotEmpty(item.getFieldValidType())) {
                    CgformValidPatternEnum patternInfo = CgformValidPatternEnum.getPatternInfoByType(item.getFieldValidType());
                    String validateError = a("validateError", item.getFieldExtendJson());
                    if (patternInfo != null) {
                        if (CgformValidPatternEnum.NOTNULL == patternInfo) {
                            required.add(field);
                        } else {
                            prop.setPattern(patternInfo.getPattern());
                            if (oConvertUtils.isEmpty(validateError)) {
                                prop.setErrorInfo(patternInfo.getMsg());
                            } else {
                                prop.setErrorInfo(validateError);
                            }
                        }
                    } else {
                        prop.setPattern(item.getFieldValidType());
                        if (oConvertUtils.isEmpty(validateError)) {
                            prop.setErrorInfo("输入的值不合法");
                        } else {
                            prop.setErrorInfo(validateError);
                        }
                    }
                }

                if (item.getIsReadOnly() == 1 || disabledFieldNameList != null && disabledFieldNameList.indexOf(field) >= 0) {
                    prop.setDisabled(true);
                }

                prop.setOrder(item.getOrderNum());
                prop.setDefVal(item.getFieldDefaultValue());
                prop.setFieldExtendJson(item.getFieldExtendJson());
                prop.setDbPointLength(item.getDbPointLength());
                prop.setMode(item.getQueryMode());
                props.add(prop);
            }
        }

        JSONObject json;
        if (required.size() > 0) {
            org.jeecg.common.util.dira.c descrip = new org.jeecg.common.util.dira.c(required);
            json = org.jeecg.common.util.dira.d.a(descrip, props);
        } else {
            org.jeecg.common.util.dira.c descrip = new org.jeecg.common.util.dira.c();
            json = org.jeecg.common.util.dira.d.a(descrip, props);
        }

        return json;
    }

    public static String a(String key, String extJsonString) {
        String value = "";
        if (extJsonString != null && !"".equals(extJsonString)) {
            JSONObject extJson = JSONObject.parseObject(extJsonString);
            if (extJson.containsKey(key)) {
                value = extJson.getString(key);
            }
        }

        return value;
    }

    public static JSONObject b(String entityDescrib, List<OnlCgformField> fieldList) {
        new JSONObject();
        List required = new ArrayList();
        List props = new ArrayList();
        ISysBaseAPI sysBaseAPI = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);

        for(OnlCgformField item : fieldList) {
            String field = item.getDbFieldName();
            if (!"id".equals(field)) {
                String title = item.getDbFieldTxt();
                if ("1".equals(item.getFieldMustInput())) {
                    required.add(field);
                }

                String filedShowType = item.getFieldShowType();
                String dictCode = item.getDictField();
                org.jeecg.common.util.dira.b prop = null;
                if (org.jeecg.modules.online.cgform.dird.j.a(item.getDbType())) {
                    prop = new Classd(field, title, "number");
                } else if (c(filedShowType)) {
                    List include = sysBaseAPI.queryDictItemsByCode(dictCode);
                    prop = new Classg(field, title, filedShowType, item.getDbLength(), include);
                } else {
                    prop = new Classg(field, title, filedShowType, item.getDbLength());
                }

                prop.setOrder(item.getOrderNum());
                props.add(prop);
            }
        }

        JSONObject json = org.jeecg.common.util.dira.d.a(entityDescrib, required, props);
        return json;
    }

    public static Set<String> a(List<OnlCgformField> fieldList) {
        Set set = new HashSet();

        for(OnlCgformField item : fieldList) {
            if ("popup".equals(item.getFieldShowType())) {
                String dictText = item.getDictText();
                if (dictText != null && !"".equals(dictText)) {
                    set.addAll((Collection)Arrays.stream(dictText.split(",")).collect(Collectors.toSet()));
                }
            }

            if ("cat_tree".equals(item.getFieldShowType())) {
                String dictText = item.getDictText();
                if (oConvertUtils.isNotEmpty(dictText)) {
                    set.add(dictText);
                }
            }
        }

        for(OnlCgformField item : fieldList) {
            String key = item.getDbFieldName();
            if (item.getIsShowForm() == 1 && set.contains(key)) {
                set.remove(key);
            }
        }

        return set;
    }

    public static Map<String, Object> a(String tbname, List<OnlCgformField> fieldList, JSONObject json) {
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        String dataBase = "";

        try {
            dataBase = org.jeecg.modules.online.config.dirc.d.getDatabaseType();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (org.jeecg.modules.online.config.exception.a e) {
            e.printStackTrace();
        }

        Map params = new HashMap(5);
        boolean hasKey = false;
        String keyFieldValue = null;
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        if (sysUser == null) {
            throw new JeecgBootException("online保存表单数据异常:系统未找到当前登陆用户信息");
        } else {
            Set needFields = a(fieldList);
            String tenantTableField = "tenant_id";
            String realTableName = f(tbname);
            boolean isTenantTable = j(realTableName);

            for(OnlCgformField item : fieldList) {
                if (org.jeecg.modules.online.cgform.dirb.b.b.equals(item.getDbIsPersist())) {
                    String key = item.getDbFieldName();
                    if (null != key) {
                        if ("id".equals(key.toLowerCase())) {
                            hasKey = true;
                            keyFieldValue = json.getString(key);
                        } else if (!isTenantTable || !tenantTableField.equalsIgnoreCase(key)) {
                            a(item, sysUser, json, "CREATE_BY", "CREATE_TIME", "SYS_ORG_CODE");
                            if ("bpm_status".equals(key.toLowerCase())) {
                                sb1.append("," + key);
                                sb2.append(",'1'");
                            } else if (needFields.contains(key)) {
                                sb1.append("," + key);
                                String sqlValue = org.jeecg.modules.online.cgform.dird.j.a(dataBase, item, json, params);
                                sb2.append("," + sqlValue);
                            } else if (item.getIsShowForm() == 1 || !oConvertUtils.isEmpty(item.getMainField()) || !oConvertUtils.isEmpty(item.getDbDefaultVal())) {
                                if (oConvertUtils.isEmpty(json.get(key))) {
                                    if (oConvertUtils.isEmpty(item.getDbDefaultVal())) {
                                        continue;
                                    }

                                    json.put(key, item.getDbDefaultVal());
                                }

                                if ("".equals(json.get(key))) {
                                    String dbType = item.getDbType();
                                    if (org.jeecg.modules.online.cgform.dird.j.a(dbType) || org.jeecg.modules.online.cgform.dird.j.b(dbType)) {
                                        continue;
                                    }
                                }

                                sb1.append("," + key);
                                String sqlValue = org.jeecg.modules.online.cgform.dird.j.a(dataBase, item, json, params);
                                sb2.append("," + sqlValue);
                            }
                        }
                    }
                }
            }

            if (hasKey) {
                if (oConvertUtils.isEmpty(keyFieldValue)) {
                    keyFieldValue = a();
                }
            } else {
                keyFieldValue = a();
            }

            if (isTenantTable) {
                sb1.append("," + tenantTableField);
                sb2.append(",#{" + tenantTableField + "}");
                String tenantId = SpringContextUtils.getHttpServletRequest().getHeader("X-Tenant-Id");
                params.put(tenantTableField, tenantId);
            }

            String sql = "insert into " + realTableName + "(id" + sb1.toString() + ") values(#{id,jdbcType=VARCHAR}" + sb2.toString() + ")";
            params.put("execute_sql_string", sql);
            params.put("id", keyFieldValue);
            return params;
        }
    }

    public static Map<String, Object> b(String tbname, List<OnlCgformField> fieldList, JSONObject json) {
        StringBuffer sb = new StringBuffer();
        Map params = new HashMap(5);
        String dataBase = "";

        try {
            dataBase = org.jeecg.modules.online.config.dirc.d.getDatabaseType();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (org.jeecg.modules.online.config.exception.a e) {
            e.printStackTrace();
        }

        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        if (sysUser == null) {
            throw new JeecgBootException("online修改表单数据异常:系统未找到当前登陆用户信息");
        } else {
            Set needFields = a(fieldList);

            for(OnlCgformField item : fieldList) {
                if (org.jeecg.modules.online.cgform.dirb.b.b.equals(item.getDbIsPersist())) {
                    String key = item.getDbFieldName();
                    if (null != key) {
                        a(item, sysUser, json, "UPDATE_BY", "UPDATE_TIME");
                        if (needFields.contains(key) && json.get(key) != null && !"".equals(json.getString(key))) {
                            String sqlValue = org.jeecg.modules.online.cgform.dird.j.a(dataBase, item, json, params);
                            sb.append(key + "=" + sqlValue + ",");
                        } else if (item.getIsShowForm() == 1 && !"id".equals(key)) {
                            if ("".equals(json.get(key))) {
                                String dbType = item.getDbType();
                                if (org.jeecg.modules.online.cgform.dird.j.a(dbType) || org.jeecg.modules.online.cgform.dird.j.b(dbType)) {
                                    continue;
                                }
                            }

                            if (!oConvertUtils.isNotEmpty(item.getMainTable()) || !oConvertUtils.isNotEmpty(item.getMainField()) || !oConvertUtils.isEmpty(json.get(key))) {
                                String sqlValue = org.jeecg.modules.online.cgform.dird.j.a(dataBase, item, json, params);
                                sb.append(key + "=" + sqlValue + ",");
                            }
                        }
                    }
                }
            }

            String condition = sb.toString();
            if (condition.endsWith(",")) {
                condition = condition.substring(0, condition.length() - 1);
            }

            String sql = "update " + f(tbname) + " set " + condition + " where  id='" + json.getString("id") + "'";
            params.put("execute_sql_string", sql);
            params.put("id", json.getString("id"));
            return params;
        }
    }

    public static QueryWrapper<?> a(List<OnlCgformField> fieldList, String id) {
        return a(fieldList, "id", id);
    }

    public static QueryWrapper<?> a(List<OnlCgformField> fieldList, String linkField, String value) {
        linkField = SqlInjectionUtil.getSqlInjectField(linkField);
        QueryWrapper queryWrapper = new QueryWrapper();
        List selectFields = new ArrayList();
        boolean isQueryFieldHasForeignKey = false;

        for(OnlCgformField item : fieldList) {
            if (org.jeecg.modules.online.cgform.dirb.b.b.equals(item.getDbIsPersist())) {
                String key = item.getDbFieldName();
                if ("id".equals(key)) {
                    isQueryFieldHasForeignKey = true;
                }

                key = SqlInjectionUtil.getSqlInjectField(key);
                selectFields.add(key);
            }
        }

        if (!isQueryFieldHasForeignKey) {
            selectFields.add("id");
        }

        if (!selectFields.isEmpty()) {
            queryWrapper.select((String[])selectFields.toArray(new String[0]));
        }

        queryWrapper.eq(linkField, value);
        return queryWrapper;
    }

    public static void a(OnlCgformField item, LoginUser sysUser, JSONObject json, String... systemFields) {
        String key = item.getDbFieldName();
        boolean need = false;

        for(String field : systemFields) {
            if (key.toUpperCase().equals(field)) {
                if (item.getIsShowForm() == 1) {
                    if (json.get(key) == null) {
                        need = true;
                    }
                } else {
                    item.setIsShowForm(1);
                    need = true;
                }

                if (need) {
                    if (field.equals("CREATE_BY")) {
                        json.put(key, sysUser.getUsername());
                    } else if (field.equals("CREATE_TIME")) {
                        item.setFieldShowType("datetime");
                        json.put(key, DateUtils.formatDateTime());
                    } else if (field.equals("UPDATE_BY")) {
                        json.put(key, sysUser.getUsername());
                    } else if (field.equals("UPDATE_TIME")) {
                        item.setFieldShowType("datetime");
                        json.put(key, DateUtils.formatDateTime());
                    } else if (field.equals("SYS_ORG_CODE")) {
                        json.put(key, sysUser.getOrgCode());
                    }
                }
                break;
            }
        }

    }

    public static boolean a(Object oldvalue, Object newvalue) {
        if (oConvertUtils.isEmpty(oldvalue) && oConvertUtils.isEmpty(newvalue)) {
            return true;
        } else {
            return oConvertUtils.isNotEmpty(oldvalue) && oldvalue.equals(newvalue);
        }
    }

    public static boolean a(OnlCgformField oldColumn, OnlCgformField newColumn) {
        if (!org.jeecg.modules.online.cgform.dirb.b.b.equals(newColumn.getDbIsPersist()) && !org.jeecg.modules.online.cgform.dirb.b.b.equals(oldColumn.getDbIsPersist())) {
            return false;
        } else {
            return !a((Object)oldColumn.getDbFieldName(), newColumn.getDbFieldName()) || !a((Object)oldColumn.getDbFieldTxt(), newColumn.getDbFieldTxt()) || !a((Object)oldColumn.getDbLength(), newColumn.getDbLength()) || !a((Object)oldColumn.getDbPointLength(), newColumn.getDbPointLength()) || !a((Object)oldColumn.getDbType(), newColumn.getDbType()) || !a((Object)oldColumn.getDbIsNull(), newColumn.getDbIsNull()) || !a((Object)oldColumn.getDbIsPersist(), newColumn.getDbIsPersist()) || !a((Object)oldColumn.getDbIsKey(), newColumn.getDbIsKey()) || !a((Object)oldColumn.getDbDefaultVal(), newColumn.getDbDefaultVal());
        }
    }

    public static boolean a(OnlCgformIndex oldIndex, OnlCgformIndex newIndex) {
        return !a((Object)oldIndex.getIndexName(), newIndex.getIndexName()) || !a((Object)oldIndex.getIndexField(), newIndex.getIndexField()) || !a((Object)oldIndex.getIndexType(), newIndex.getIndexType());
    }

    public static boolean a(OnlCgformHead oldTable, OnlCgformHead newTable) {
        return !a((Object)oldTable.getTableName(), newTable.getTableName()) || !a((Object)oldTable.getTableTxt(), newTable.getTableTxt());
    }

    public static String a(String tbname, List<OnlCgformField> fieldList, Map<String, Object> metaParams) {
        StringBuffer sb = new StringBuffer();
        StringBuffer selectField = new StringBuffer();
        String paramPrefix = tbname + "@";
        Map params = new HashMap(5);

        for(String key : metaParams.keySet()) {
            if (key.startsWith(paramPrefix)) {
                params.put(key.replace(paramPrefix, ""), metaParams.get(key));
            } else {
                params.put(key, metaParams.get(key));
            }
        }

        for(OnlCgformField item : fieldList) {
            String field = item.getDbFieldName();
            String dbtype = item.getDbType();
            if (item.getIsShowList() == 1) {
                selectField.append("," + field);
            }

            if (oConvertUtils.isNotEmpty(item.getMainField())) {
                boolean isString = !org.jeecg.modules.online.cgform.dird.j.a(dbtype);
                String fieldCondition = QueryGenerator.getSingleQueryConditionSql(field, "", params.get(field), isString);
                if (!"".equals(fieldCondition)) {
                    sb.append(" AND " + fieldCondition);
                }
            }

            if (item.getIsQuery() == 1) {
                if ("single".equals(item.getQueryMode())) {
                    if (params.get(field) != null) {
                        boolean isString = !org.jeecg.modules.online.cgform.dird.j.a(dbtype);
                        String fieldCondition = QueryGenerator.getSingleQueryConditionSql(field, "", params.get(field), isString);
                        if (!"".equals(fieldCondition)) {
                            sb.append(" AND " + fieldCondition);
                        }
                    }
                } else {
                    Object beginVal = params.get(field + "_begin");
                    if (beginVal != null) {
                        sb.append(" AND " + field + ">=");
                        if (org.jeecg.modules.online.cgform.dird.j.a(dbtype)) {
                            sb.append(beginVal.toString());
                        } else {
                            sb.append("'" + beginVal.toString() + "'");
                        }
                    }

                    Object endVal = params.get(field + "_end");
                    if (endVal != null) {
                        sb.append(" AND " + field + "<=");
                        if (org.jeecg.modules.online.cgform.dird.j.a(dbtype)) {
                            sb.append(endVal.toString());
                        } else {
                            sb.append("'" + endVal.toString() + "'");
                        }
                    }
                }
            }
        }

        String superQuerySql = b(tbname, fieldList, params);
        String var10000 = selectField.toString();
        return "SELECT id" + var10000 + " FROM " + f(tbname) + " where 1=1  " + sb.toString() + superQuerySql;
    }

    public static String b(String tableName, List<OnlCgformField> allFieldList, Map<String, Object> params) {
        boolean isFirst = true;
        JSONArray superQueryParams = b(params);
        MatchTypeEnum matchType = c(params);
        StringBuilder builder = new StringBuilder();
        if (superQueryParams != null) {
            for(int i = 0; i < superQueryParams.size(); ++i) {
                JSONObject json = superQueryParams.getJSONObject(i);
                String field = json.getString("field");
                String[] fieldArray = field.split(",");
                if (fieldArray.length != 1) {
                    String realField = fieldArray[1];
                    if (tableName.equalsIgnoreCase(fieldArray[0]) && c(realField, allFieldList)) {
                        a(builder, realField, json, matchType, (JSONObject)null, isFirst);
                        isFirst = false;
                    }
                }
            }
        }

        String sql = builder.toString();
        return sql != null && !"".equals(sql) ? " AND (" + sql + ") " : "";
    }

    public static boolean c(String fieldName, List<OnlCgformField> List) {
        boolean flag = false;

        for(OnlCgformField field : List) {
            if (oConvertUtils.camelToUnderline(fieldName).equalsIgnoreCase(field.getDbFieldName())) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    /** @deprecated */
    @Deprecated
    public static List<ExcelExportEntity> b(List<OnlCgformField> lists, String pkField) {
        List entityList = new ArrayList();

        for(int i = 0; i < lists.size(); ++i) {
            if ((null == pkField || !pkField.equals(((OnlCgformField)lists.get(i)).getDbFieldName())) && ((OnlCgformField)lists.get(i)).getIsShowList() == 1) {
                ExcelExportEntity entity = new ExcelExportEntity(((OnlCgformField)lists.get(i)).getDbFieldTxt(), ((OnlCgformField)lists.get(i)).getDbFieldName());
                int columnWidth = ((OnlCgformField)lists.get(i)).getDbLength() == 0 ? 12 : (((OnlCgformField)lists.get(i)).getDbLength() > 30 ? 30 : ((OnlCgformField)lists.get(i)).getDbLength());
                if ("date".equals(((OnlCgformField)lists.get(i)).getFieldShowType())) {
                    entity.setFormat("yyyy-MM-dd");
                } else if ("datetime".equals(((OnlCgformField)lists.get(i)).getFieldShowType())) {
                    entity.setFormat("yyyy-MM-dd HH:mm:ss");
                }

                if (columnWidth < 10) {
                    columnWidth = 10;
                }

                entity.setWidth((double)columnWidth);
                entityList.add(entity);
            }
        }

        return entityList;
    }

    public static boolean a(OnlCgformEnhanceJava onlCgformEnhanceJava) {
        String cgJavaType = onlCgformEnhanceJava.getCgJavaType();
        String cgJavaValue = onlCgformEnhanceJava.getCgJavaValue();
        if (oConvertUtils.isNotEmpty(cgJavaValue)) {
            try {
                if ("class".equals(cgJavaType)) {
                    Class clazz = Class.forName(cgJavaValue);
                    if (clazz == null || clazz.newInstance() == null) {
                        return false;
                    }
                }

                if ("spring".equals(cgJavaType)) {
                    Object obj = SpringContextUtils.getBean(cgJavaValue);
                    if (obj == null) {
                        return false;
                    }
                }
            } catch (Exception e) {
                aZ.error(e.getMessage(), e);
                return false;
            }
        }

        return true;
    }

    public static void b(List<String> list) {
        Collections.sort(list, new b());
    }

    public static void c(List<String> list) {
        Collections.sort(list, new a());
    }

    public static String a(String sql, JSONObject json) {
        if (json == null) {
            return sql;
        } else {
            sql = sql.replace("#{UUID}", UUIDGenerator.generate());

            for(String key : QueryGenerator.getSqlRuleParams(sql)) {
                if (json.get(key.toUpperCase()) == null && json.get(key.toLowerCase()) == null) {
                    String tempValue = JwtUtil.getUserSystemData(key, (SysUserCacheInfo)null);
                    if (tempValue == null) {
                        sql = sql.replace("'#{" + key + "}'", "NULL");
                        sql = sql.replace("#{" + key + "}", "NULL");
                    } else {
                        sql = sql.replace("#{" + key + "}", tempValue);
                    }
                } else {
                    String jsonValue = null;
                    if (json.containsKey(key.toLowerCase())) {
                        jsonValue = json.getString(key.toLowerCase());
                    } else if (json.containsKey(key.toUpperCase())) {
                        jsonValue = json.getString(key.toUpperCase());
                    }

                    sql = sql.replace("#{" + key + "}", jsonValue);
                }
            }

            return sql;
        }
    }

    public static String d(String enhanceJs, List<OnlCgformButton> cgButtonList) {
        enhanceJs = e(enhanceJs, cgButtonList);

        for(String temp : "beforeAdd,beforeEdit,afterAdd,afterEdit,beforeDelete,afterDelete,mounted,created".split(",")) {
            if ("beforeAdd,afterAdd,mounted,created".indexOf(temp) >= 0) {
                Pattern p = Pattern.compile("(" + temp + "\\s*\\(\\)\\s*\\{)");
                Matcher m = p.matcher(enhanceJs);
                if (m.find()) {
                    enhanceJs = enhanceJs.replace(m.group(0), temp + "(that){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
                }
            } else {
                Pattern p = Pattern.compile("(" + temp + "\\s*\\(row\\)\\s*\\{)");
                Matcher m = p.matcher(enhanceJs);
                if (m.find()) {
                    enhanceJs = enhanceJs.replace(m.group(0), temp + "(that,row){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
                } else {
                    Pattern p2 = Pattern.compile("(" + temp + "\\s*\\(\\)\\s*\\{)");
                    Matcher m2 = p2.matcher(enhanceJs);
                    if (m2.find()) {
                        enhanceJs = enhanceJs.replace(m2.group(0), temp + "(that){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
                    }
                }
            }
        }

        return d(enhanceJs);
    }

    public static void a(OnlCgformEnhanceJs onlCgformEnhanceJs, String tbname, List<OnlCgformField> fieldList) {
        if (onlCgformEnhanceJs != null && !oConvertUtils.isEmpty(onlCgformEnhanceJs.getCgJs())) {
            String enhanceJs = onlCgformEnhanceJs.getCgJs();
            String onlChange = "onlChange";
            Pattern p = Pattern.compile("(" + tbname + "_" + onlChange + "\\s*\\(\\)\\s*\\{)");
            Matcher m = p.matcher(enhanceJs);
            if (m.find()) {
                enhanceJs = enhanceJs.replace(m.group(0), tbname + "_" + onlChange + "(){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");

                for(OnlCgformField field : fieldList) {
                    Pattern p1 = Pattern.compile("(" + field.getDbFieldName() + "\\s*\\(\\))");
                    Matcher m1 = p1.matcher(enhanceJs);
                    if (m1.find()) {
                        enhanceJs = enhanceJs.replace(m1.group(0), field.getDbFieldName() + "(that,event)");
                    }
                }
            }

            onlCgformEnhanceJs.setCgJs(enhanceJs);
        }
    }

    public static void a(OnlCgformEnhanceJs onlCgformEnhanceJs, String tbname, List<OnlCgformField> fieldList, boolean isMain) {
        if (onlCgformEnhanceJs != null && !oConvertUtils.isEmpty(onlCgformEnhanceJs.getCgJs())) {
            String enhanceJs = onlCgformEnhanceJs.getCgJs();
            String onlChange = "onlChange";
            Pattern p = Pattern.compile("([^_]" + onlChange + "\\s*\\(\\)\\s*\\{)");
            Matcher m = p.matcher(enhanceJs);
            if (m.find()) {
                enhanceJs = enhanceJs.replace(m.group(0), onlChange + "(){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");

                for(OnlCgformField field : fieldList) {
                    Pattern p1 = Pattern.compile("(" + field.getDbFieldName() + "\\s*\\(\\))");
                    Matcher m1 = p1.matcher(enhanceJs);
                    if (m1.find()) {
                        enhanceJs = enhanceJs.replace(m1.group(0), field.getDbFieldName() + "(that,event)");
                    }
                }
            }

            onlCgformEnhanceJs.setCgJs(enhanceJs);
            a(onlCgformEnhanceJs);
            a(onlCgformEnhanceJs, tbname, fieldList);
        }
    }

    public static void a(OnlCgformEnhanceJs onlCgformEnhanceJs) {
        String enhanceJs = onlCgformEnhanceJs.getCgJs();
        String temp = "show";
        Pattern p = Pattern.compile("(" + temp + "\\s*\\(\\)\\s*\\{)");
        Matcher m = p.matcher(enhanceJs);
        if (m.find()) {
            enhanceJs = enhanceJs.replace(m.group(0), temp + "(that){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
        }

        onlCgformEnhanceJs.setCgJs(enhanceJs);
    }

    public static String d(String enhanceJs) {
        return "class OnlineEnhanceJs{constructor(getAction,postAction,deleteAction){this._getAction=getAction;this._postAction=postAction;this._deleteAction=deleteAction;}" + enhanceJs + "}";
    }

    public static String e(String enhanceJs, List<OnlCgformButton> cgButtonList) {
        if (cgButtonList != null) {
            for(OnlCgformButton button : cgButtonList) {
                String temp = button.getButtonCode();
                if ("link".equals(button.getButtonStyle())) {
                    Pattern p = Pattern.compile("(" + temp + "\\s*\\(row\\)\\s*\\{)");
                    Matcher m = p.matcher(enhanceJs);
                    if (m.find()) {
                        enhanceJs = enhanceJs.replace(m.group(0), temp + "(that,row){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
                    } else {
                        Pattern p2 = Pattern.compile("(" + temp + "\\s*\\(\\)\\s*\\{)");
                        Matcher m2 = p2.matcher(enhanceJs);
                        if (m2.find()) {
                            enhanceJs = enhanceJs.replace(m2.group(0), temp + "(that){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
                        }
                    }
                } else if ("button".equals(button.getButtonStyle()) || "form".equals(button.getButtonStyle())) {
                    Pattern p = Pattern.compile("(" + temp + "\\s*\\(\\)\\s*\\{)");
                    Matcher m = p.matcher(enhanceJs);
                    if (m.find()) {
                        enhanceJs = enhanceJs.replace(m.group(0), temp + "(that){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
                    }
                }
            }
        }

        return enhanceJs;
    }

    public static JSONArray a(List<OnlCgformField> fieldList, List<String> disabledList) {
        JSONArray array = new JSONArray();
        ISysBaseAPI sysBaseAPI = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);

        for(OnlCgformField item : fieldList) {
            String field = item.getDbFieldName();
            if (!"id".equals(field)) {
                JSONObject obj = new JSONObject();
                if (disabledList != null && disabledList.indexOf(field) >= 0) {
                    obj.put("disabled", true);
                }

                if (item.getIsReadOnly() != null && 1 == item.getIsReadOnly()) {
                    obj.put("disabled", true);
                }

                obj.put("title", item.getDbFieldTxt());
                obj.put("key", field);
                String viewType = d(item);
                obj.put("type", viewType);
                if (item.getFieldLength() == null) {
                    item.setFieldLength(186);
                }

                if (("sel_depart".equals(viewType) || "sel_user".equals(viewType)) && item.getFieldLength() < 170) {
                    obj.put("width", "170px");
                } else if ("date".equals(viewType) && item.getFieldLength() < 140) {
                    obj.put("width", "140px");
                } else if ("datetime".equals(viewType) && item.getFieldLength() < 190) {
                    obj.put("width", "190px");
                } else {
                    obj.put("width", item.getFieldLength() + "px");
                }

                if ("file".equals(viewType) || "image".equals(viewType)) {
                    obj.put("responseName", "message");
                    obj.put("token", true);
                }

                if ("switch".equals(viewType)) {
                    obj.put("type", "checkbox");
                    JSONArray customValue = new JSONArray();
                    customValue.add("Y");
                    customValue.add("N");
                    if (oConvertUtils.isNotEmpty(item.getFieldExtendJson())) {
                        try {
                            customValue = JSONArray.parseArray(item.getFieldExtendJson());
                        } catch (Exception var17) {
                            JSONObject jsonObject = JSONArray.parseObject(item.getFieldExtendJson());
                            if (jsonObject.containsKey("switchOptions")) {
                                customValue = jsonObject.getJSONArray("switchOptions");
                            }
                        }
                    }

                    obj.put("customValue", customValue);
                }

                if ("popup".equals(viewType)) {
                    obj.put("popupCode", item.getDictTable());
                    obj.put("orgFields", item.getDictField());
                    obj.put("destFields", item.getDictText());
                    String backFields = item.getDictText();
                    if (backFields != null && !"".equals(backFields)) {
                        List hiddenFields = new ArrayList();
                        String[] tempArr = backFields.split(",");

                        for(String str : tempArr) {
                            if (!a(str, fieldList)) {
                                hiddenFields.add(str);
                                JSONObject obj2 = new JSONObject();
                                obj2.put("title", str);
                                obj2.put("key", str);
                                obj2.put("type", "hidden");
                                array.add(obj2);
                            }
                        }
                    }
                }

                obj.put("defaultValue", item.getDbDefaultVal());
                obj.put("fieldDefaultValue", item.getFieldDefaultValue());
                obj.put("placeholder", "请输入" + item.getDbFieldTxt());
                obj.put("validateRules", c(item));
                if ("list".equals(item.getFieldShowType()) || "radio".equals(item.getFieldShowType()) || "checkbox_meta".equals(item.getFieldShowType()) || "list_multi".equals(item.getFieldShowType()) || "sel_search".equals(item.getFieldShowType())) {
                    obj.put("view", item.getFieldShowType());
                    obj.put("dictTable", item.getDictTable());
                    obj.put("dictText", item.getDictText());
                    obj.put("dictCode", item.getDictField());
                    if ("list_multi".equals(item.getFieldShowType())) {
                        obj.put("width", "230px");
                    }
                }

                obj.put("fieldExtendJson", item.getFieldExtendJson());
                array.add(obj);
            }
        }

        return array;
    }

    private static JSONArray c(OnlCgformField item) {
        JSONArray array = new JSONArray();
        if (item.getDbIsNull() == 0 || "1".equals(item.getFieldMustInput())) {
            JSONObject json = new JSONObject();
            json.put("required", true);
            json.put("message", item.getDbFieldTxt() + "不能为空!");
            array.add(json);
        }

        if (oConvertUtils.isNotEmpty(item.getFieldValidType())) {
            JSONObject json2 = new JSONObject();
            if ("only".equals(item.getFieldValidType())) {
                json2.put("unique", true);
                json2.put("message", item.getDbFieldTxt() + "不能重复");
            } else {
                json2.put("pattern", item.getFieldValidType());
                String validateError = a("validateError", item.getFieldExtendJson());
                if (oConvertUtils.isEmpty(validateError)) {
                    CgformValidPatternEnum patternInfoByType = CgformValidPatternEnum.getPatternInfoByType(item.getFieldValidType());
                    if (null == patternInfoByType) {
                        json2.put("message", item.getDbFieldTxt() + "格式不正确");
                    } else {
                        json2.put("message", patternInfoByType.getMsg());
                    }
                } else {
                    json2.put("message", validateError);
                }
            }

            array.add(json2);
        }

        return array;
    }

    public static Map<String, Object> a(Map<String, Object> orgMap) {
        Map resultMap = new HashMap(5);
        if (orgMap != null && !orgMap.isEmpty()) {
            for(String key : orgMap.keySet()) {
                Object val = orgMap.get(key);
                if (val instanceof Clob) {
                    val = a((Clob)val);
                } else if (val instanceof byte[]) {
                    val = new String((byte[])val);
                } else if (val instanceof Blob) {
                    try {
                        if (val != null) {
                            Blob val_b = (Blob)val;
                            val = new String(val_b.getBytes(1L, (int)val_b.length()), "UTF-8");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                String newKey = key.toLowerCase();
                resultMap.put(newKey, val == null ? "" : val);
            }

            return resultMap;
        } else {
            return resultMap;
        }
    }

    public static JSONObject a(JSONObject json) {
        if (!org.jeecg.modules.online.config.dirc.d.a()) {
            return json;
        } else {
            JSONObject resultJson = new JSONObject();
            if (json != null && !json.isEmpty()) {
                for(String key : json.keySet()) {
                    String newKey = key.toLowerCase();
                    resultJson.put(newKey, json.get(key));
                }

                return resultJson;
            } else {
                return resultJson;
            }
        }
    }

    public static List<Map<String, Object>> a(JSONArray array) {
        List list = (List)array.stream().map((item) -> (JSONObject)item).collect(Collectors.toList());
        return a((List)list, (Collection)null);
    }

    public static List<Map<String, Object>> d(List<Map<String, Object>> list) {
        return a((List)list, (Collection)null);
    }

    public static List<Map<String, Object>> a(List<Map<String, Object>> list, Collection<String> subTableCollection) {
        List<Map<String,Object>> select = new ArrayList();

        for(Map<String,Object> row : list) {
            if (row != null) {
                Map<String,Object> resultMap = new HashMap(5);

                for(String key : row.keySet()) {
                    Object val = row.get(key);
                    if (val instanceof Clob) {
                        val = a((Clob)val);
                    } else if (val instanceof byte[]) {
                        val = new String((byte[])val);
                    } else if (val instanceof Long) {
                        if (val != null) {
                            val = String.valueOf(val);
                        }
                    } else if (val instanceof Blob) {
                        try {
                            if (val != null) {
                                Blob val_b = (Blob)val;
                                val = new String(val_b.getBytes(1L, (int)val_b.length()), "UTF-8");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    String newKey = key.toLowerCase();
                    resultMap.put(newKey, val == null ? "" : val);
                }

                String rowKey = a(resultMap, subTableCollection);
                resultMap.put("jeecg_row_key", rowKey);
                select.add(resultMap);
            }
        }

        return select;
    }

    private static String a(Map<String, Object> map, Collection<String> subTableCollection) {
        String rowKey = map.containsKey("id") ? map.get("id").toString() : null;
        if (oConvertUtils.isNotEmpty(rowKey) && subTableCollection != null) {
            for(String tableName : subTableCollection) {
                String newKey = tableName.toLowerCase() + "_id";
                Object temp = map.get(newKey);
                if (temp == null) {
                    rowKey = rowKey + "@";
                } else {
                    rowKey = rowKey + "@" + temp.toString();
                }
            }
        }

        return rowKey;
    }

    public static String a(Clob clob) {
        String clobStr = "";

        try {
            Reader inStream = clob.getCharacterStream();
            char[] c = new char[(int)clob.length()];
            inStream.read(c);
            clobStr = new String(c);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clobStr;
    }

    public static Map<String, Object> c(String tbname, List<OnlCgformField> fieldList, JSONObject json) {
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        String dataBase = "";

        try {
            dataBase = org.jeecg.modules.online.config.dirc.d.getDatabaseType();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (org.jeecg.modules.online.config.exception.a e) {
            e.printStackTrace();
        }

        Map params = new HashMap(5);
        boolean hasKey = false;
        String keyFieldValue = null;
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        if (sysUser == null) {
            String username = JwtUtil.getUserNameByToken(SpringContextUtils.getHttpServletRequest());
            if (!oConvertUtils.isNotEmpty(username)) {
                throw new JeecgBootException("online保存表单数据异常:系统未找到当前登陆用户信息");
            }

            sysUser = new LoginUser();
            sysUser.setUsername(username);
        }

        String tenantTableField = "tenant_id";
        String realTableName = SqlInjectionUtil.getSqlInjectTableName(f(tbname));
        boolean isTenantTable = j(realTableName);

        for(OnlCgformField item : fieldList) {
            String key = SqlInjectionUtil.getSqlInjectField(item.getDbFieldName());
            if (null != key && (json.get(key) != null || "CREATE_BY".equalsIgnoreCase(key) || "CREATE_TIME".equalsIgnoreCase(key) || "SYS_ORG_CODE".equalsIgnoreCase(key)) && (!isTenantTable || !tenantTableField.equalsIgnoreCase(key))) {
                a(item, sysUser, json, "CREATE_BY", "CREATE_TIME", "SYS_ORG_CODE");
                if ("".equals(json.get(key))) {
                    String dbType = item.getDbType();
                    if (org.jeecg.modules.online.cgform.dird.j.a(dbType) || org.jeecg.modules.online.cgform.dird.j.b(dbType)) {
                        continue;
                    }
                }

                if ("id".equals(key.toLowerCase())) {
                    hasKey = true;
                    keyFieldValue = json.getString(key);
                } else if (!"link_table_field".equals(item.getFieldShowType()) || org.jeecg.modules.online.cgform.dirb.b.b.equals(item.getDbIsPersist())) {
                    sb1.append("," + key);
                    String sqlValue = org.jeecg.modules.online.cgform.dird.j.a(dataBase, item, json, params);
                    sb2.append("," + sqlValue);
                }
            }
        }

        if (!hasKey || oConvertUtils.isEmpty(keyFieldValue)) {
            keyFieldValue = a();
        }

        if (isTenantTable) {
            sb1.append("," + tenantTableField);
            sb2.append(",#{" + tenantTableField + "}");
            String tenantId = SpringContextUtils.getHttpServletRequest().getHeader("X-Tenant-Id");
            params.put(tenantTableField, tenantId);
        }

        String sql = "insert into " + realTableName + "(id" + sb1.toString() + ") values('" + keyFieldValue + "'" + sb2.toString() + ")";
        params.put("execute_sql_string", sql);
        aZ.info("--表单设计器表单保存sql-->" + sql);
        params.put("id", keyFieldValue);
        return params;
    }

    public static Map<String, Object> d(String tbname, List<OnlCgformField> fieldList, JSONObject json) {
        StringBuffer sb = new StringBuffer();
        Map params = new HashMap(5);
        String dataBase = "";

        try {
            dataBase = org.jeecg.modules.online.config.dirc.d.getDatabaseType();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (org.jeecg.modules.online.config.exception.a e) {
            e.printStackTrace();
        }

        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        if (sysUser == null) {
            String username = JwtUtil.getUserNameByToken(SpringContextUtils.getHttpServletRequest());
            if (!oConvertUtils.isNotEmpty(username)) {
                throw new JeecgBootException("online保存表单数据异常:系统未找到当前登陆用户信息");
            }

            sysUser = new LoginUser();
            sysUser.setUsername(username);
        }

        for(OnlCgformField item : fieldList) {
            if (org.jeecg.modules.online.cgform.dirb.b.b.equals(item.getDbIsPersist())) {
                String key = item.getDbFieldName();
                if (null == key) {
                    aZ.info("--------online修改表单数据遇见空名称的字段------->>" + item.getId());
                } else if (!"id".equals(key) && (json.get(key) != null || "UPDATE_BY".equalsIgnoreCase(key) || "UPDATE_TIME".equalsIgnoreCase(key) || "SYS_ORG_CODE".equalsIgnoreCase(key))) {
                    a(item, sysUser, json, "UPDATE_BY", "UPDATE_TIME", "SYS_ORG_CODE");
                    if ("".equals(json.get(key))) {
                        String dbType = item.getDbType();
                        if (org.jeecg.modules.online.cgform.dird.j.a(dbType) || org.jeecg.modules.online.cgform.dird.j.b(dbType)) {
                            continue;
                        }
                    }

                    String sqlValue = org.jeecg.modules.online.cgform.dird.j.a(dataBase, item, json, params);
                    sb.append(key + "=" + sqlValue + ",");
                }
            }
        }

        String condition = sb.toString();
        if (condition.endsWith(",")) {
            condition = condition.substring(0, condition.length() - 1);
        }

        String sql = "update " + f(tbname) + " set " + condition + " where  id='" + json.getString("id") + "'";
        aZ.info("--表单设计器表单编辑sql-->" + sql);
        params.put("execute_sql_string", sql);
        params.put("id", json.getString("id"));
        return params;
    }

    public static Map<String, Object> a(String tbname, String filed, String id) {
        Map params = new HashMap(5);
        String sql = "update " + f(tbname) + " set " + filed + "='0' where  id='" + id + "'";
        aZ.info("--修改树节点状态：为无子节点sql-->" + sql);
        params.put("execute_sql_string", sql);
        return params;
    }

    public static String e(String dictCode) {
        return dictCode != null && !"".equals(dictCode) && !"0".equals(dictCode) ? "CODE like '" + dictCode + "%'" : "";
    }

    public static String f(String tablename) {
        String realTableName;
        if (Pattern.matches("^[a-zA-z].*\\$\\d+$", tablename)) {
            realTableName = tablename.substring(0, tablename.lastIndexOf("$"));
        } else {
            realTableName = tablename;
        }

        return SqlInjectionUtil.getSqlInjectTableName(realTableName);
    }

    public static void a(Classc prop, List<OnlCgformField> fieldList, List<String> excludeFields, List<String> required) {
        String str = prop.getDictTable();
        JSONObject json = JSONObject.parseObject(str);
        String linkField = json.getString("linkField");
        List list = new ArrayList();
        if (oConvertUtils.isNotEmpty(linkField)) {
            String[] arr = linkField.split(",");

            for(OnlCgformField item : fieldList) {
                String field = item.getDbFieldName();

                for(String key : arr) {
                    if (key.equals(field)) {
                        excludeFields.add(field);
                        if ("1".equals(item.getFieldMustInput())) {
                            required.add(field);
                        }

                        list.add(new org.jeecg.common.util.dira.a(item.getDbFieldTxt(), field, item.getOrderNum(), item.getFieldDefaultValue()));
                        break;
                    }
                }
            }
        }

        prop.setOtherColumns(list);
    }

    public static String a(byte[] data, String basePath, String bizPath, String uploadType) {
        return OSSCommonUtils.uploadOnlineImage(data, basePath, bizPath, uploadType);
    }

    public static List<String> e(List<OnlCgformField> fieldList) {
        List ls = new ArrayList();

        for(OnlCgformField field : fieldList) {
            if ("image".equals(field.getFieldShowType())) {
                ls.add(field.getDbFieldTxt());
            }
        }

        return ls;
    }

    public static List<String> c(List<OnlCgformField> fieldList, String pre) {
        List ls = new ArrayList();

        for(OnlCgformField field : fieldList) {
            if ("image".equals(field.getFieldShowType())) {
                ls.add(pre + "_" + field.getDbFieldTxt());
            }
        }

        return ls;
    }

    public static String a() {
        long id = IdWorker.getId();
        return String.valueOf(id);
    }

    public static String a(Exception e) {
        String errorMsg = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
        if (errorMsg == null) {
            return "未知错误";
        } else {
            if (errorMsg.indexOf("ORA-01452") != -1) {
                errorMsg = "ORA-01452: 无法 CREATE UNIQUE INDEX; 找到重复的关键字";
            } else if (errorMsg.indexOf("duplicate key") != -1) {
                errorMsg = "无法 CREATE UNIQUE INDEX; 找到重复的关键字";
            }

            return errorMsg;
        }
    }

    public static List<DictModel> b(OnlCgformField item) {
        List ls = new ArrayList();
        String extendStr = item.getFieldExtendJson();
        String textYes = "是";
        String textNo = "否";
        JSONArray array = JSONArray.parseArray("[\"Y\",\"N\"]");
        if (oConvertUtils.isNotEmpty(extendStr)) {
            try {
                array = JSONArray.parseArray(extendStr);
            } catch (JSONException var8) {
                JSONObject jsonObject = JSONArray.parseObject(extendStr);
                if (jsonObject.containsKey("switchOptions")) {
                    array = jsonObject.getJSONArray("switchOptions");
                }
            }
        }

        DictModel y = new DictModel(array.getString(0), textYes);
        DictModel n = new DictModel(array.getString(1), textNo);
        ls.add(y);
        ls.add(n);
        return ls;
    }

    private static String d(OnlCgformField item) {
        if ("checkbox".equals(item.getFieldShowType())) {
            return "checkbox";
        } else if ("pca".equals(item.getFieldShowType())) {
            return "pca";
        } else if ("list".equals(item.getFieldShowType())) {
            return "select";
        } else if ("switch".equals(item.getFieldShowType())) {
            return "switch";
        } else if ("sel_user".equals(item.getFieldShowType())) {
            return "sel_user";
        } else if ("sel_depart".equals(item.getFieldShowType())) {
            return "sel_depart";
        } else if ("textarea".equals(item.getFieldShowType())) {
            return "textarea";
        } else if (!"image".equals(item.getFieldShowType()) && !"file".equals(item.getFieldShowType()) && !"radio".equals(item.getFieldShowType()) && !"popup".equals(item.getFieldShowType()) && !"list_multi".equals(item.getFieldShowType()) && !"sel_search".equals(item.getFieldShowType())) {
            if ("datetime".equals(item.getFieldShowType())) {
                return "datetime";
            } else if ("date".equals(item.getFieldShowType())) {
                return "date";
            } else if ("time".equals(item.getFieldShowType())) {
                return "time";
            } else if ("int".equals(item.getDbType())) {
                return "inputNumber";
            } else {
                return !"double".equals(item.getDbType()) && !"BigDecimal".equals(item.getDbType()) ? "input" : "inputNumber";
            }
        } else {
            return item.getFieldShowType();
        }
    }

    public static String getDatabseType() {
        if (oConvertUtils.isNotEmpty(bb)) {
            return bb;
        } else {
            try {
                bb = org.jeecg.modules.online.config.dirc.d.getDatabaseType();
                return bb;
            } catch (Exception e) {
                e.printStackTrace();
                return bb;
            }
        }
    }

    public static List<String> f(List<String> list) {
        List arr = new ArrayList();

        for(String name : list) {
            arr.add(name.toLowerCase());
        }

        return arr;
    }

    private static String b(String fieldName, String value) {
        String sql = "";
        if (value != null && !"".equals(value)) {
            String[] arr = value.split(",");

            for(int i = 0; i < arr.length; ++i) {
                if (i > 0) {
                    sql = sql + " AND ";
                }

                sql = sql + fieldName + " like ";
                if ("SQLSERVER".equals(getDatabseType())) {
                    sql = sql + "N";
                }

                sql = sql + "'%" + arr[i] + "%'";
            }

            aZ.info(" POPUP fieldSql: " + sql);
            return sql;
        } else {
            return sql;
        }
    }

    public static String a(String basePath, String tableTxt, StringBuffer error) {
        String var10000 = File.separator;
        String saveDir = "logs" + var10000 + ((SimpleDateFormat)DateUtils.yyyyMMdd.get()).format(new Date()) + File.separator;
        String saveFullDir = basePath + File.separator + saveDir;
        File saveFile = new File(saveFullDir);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }

        String fileName = tableTxt + Math.round(Math.random() * (double)10000.0F);
        String saveFilePath = saveFullDir + fileName + ".txt";

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(saveFilePath));
            output.write(error.toString());
            output.flush();
            output.close();
        } catch (Exception e) {
            aZ.info("excel导入生成错误日志文件异常:" + e.getMessage());
        }

        return "/sys/common/static/" + saveDir + fileName + ".txt";
    }

    public static JSONObject b(JSONObject json) {
        JSONObject properties;
        if (json.containsKey("properties")) {
            properties = json.getJSONObject("properties");
        } else {
            JSONObject schema = json.getJSONObject("schema");
            properties = schema.getJSONObject("properties");
        }

        ISysBaseAPI sysBaseAPI = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);

        for(String key : properties.keySet()) {
            JSONObject temp = properties.getJSONObject(key);
            String view = temp.getString("view");
            if (c(view)) {
                String dictCode = temp.getString("dictCode");
                String dictText = temp.getString("dictText");
                String dictTable = temp.getString("dictTable");
                List include = new ArrayList();
                if (oConvertUtils.isNotEmpty(dictTable)) {
                    include = sysBaseAPI.queryTableDictItemsByCode(dictTable, dictText, dictCode);
                } else if (oConvertUtils.isNotEmpty(dictCode)) {
                    include = sysBaseAPI.queryEnableDictItemsByCode(dictCode);
                }

                if (include != null && include.size() > 0) {
                    temp.put("enum", include.stream().filter(Objects::nonNull).collect(Collectors.toList()));
                }
            } else if ("tab".equals(view)) {
                String relationType = temp.getString("relationType");
                if ("1".equals(relationType)) {
                    b(temp);
                } else {
                    JSONArray array = temp.getJSONArray("columns");

                    for(int i = 0; i < array.size(); ++i) {
                        JSONObject subItem = array.getJSONObject(i);
                        if (c(subItem)) {
                            String dictCode = subItem.getString("dictCode");
                            String dictText = subItem.getString("dictText");
                            String dictTable = subItem.getString("dictTable");
                            List include = new ArrayList();
                            if (oConvertUtils.isNotEmpty(dictTable)) {
                                include = sysBaseAPI.queryTableDictItemsByCode(dictTable, dictText, dictCode);
                            } else if (oConvertUtils.isNotEmpty(dictCode)) {
                                include = sysBaseAPI.queryEnableDictItemsByCode(dictCode);
                            }

                            if (include != null && include.size() > 0) {
                                subItem.put("options", include);
                            }
                        }
                    }
                }
            }
        }

        return json;
    }

    private static boolean c(JSONObject subItem) {
        Object view = subItem.get("view");
        if (view != null) {
            String viewString = view.toString();
            if ("list".equals(viewString) || "radio".equals(viewString) || "checkbox_meta".equals(viewString) || "list_multi".equals(viewString) || "sel_search".equals(viewString)) {
                return true;
            }
        }

        return false;
    }

    public static JSONArray b(Map<String, Object> params) {
        Object superQueryParamsObject = params.get("superQueryParams");
        if (superQueryParamsObject != null) {
            try {
                String paramString = URLDecoder.decode(superQueryParamsObject.toString(), "UTF-8");
                JSONArray array = JSONArray.parseArray(paramString);
                return array;
            } catch (UnsupportedEncodingException e) {
                aZ.error("高级查询json参数转换失败" + e.getMessage());
            }
        }

        return null;
    }

    public static MatchTypeEnum c(Map<String, Object> params) {
        Object superQueryMatchTypeObject = params.get("superQueryMatchType");
        MatchTypeEnum matchType = MatchTypeEnum.getByValue(superQueryMatchTypeObject);
        if (matchType == null) {
            matchType = MatchTypeEnum.AND;
        }

        return matchType;
    }

    public static boolean g(String str) {
        for(int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c != '.' && c != '-' && c != '+' && !Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public static List<OnlineFieldConfig> g(List<OnlCgformField> allFieldList) {
        List fieldConfigs = new ArrayList();

        for(OnlCgformField item : allFieldList) {
            if (org.jeecg.modules.online.cgform.dirb.b.b.equals(item.getDbIsPersist())) {
                fieldConfigs.add(new OnlineFieldConfig(item));
            }
        }

        return fieldConfigs;
    }

    public static String a(Map<String, Object> map, String key) {
        Object temp = b(map, key);
        return temp != null ? temp.toString() : null;
    }

    public static Object b(Map<String, Object> map, String key) {
        if (map != null && !oConvertUtils.isEmpty(key)) {
            Object temp1 = map.get(key);
            if (temp1 != null) {
                return temp1;
            } else {
                Object temp2 = map.get(key.toUpperCase());
                if (temp2 != null) {
                    return temp2;
                } else {
                    Object temp3 = map.get(key.toLowerCase());
                    return temp3 != null ? temp3 : null;
                }
            }
        } else {
            return null;
        }
    }

    public static List<String> h(String selections) {
        List list = new ArrayList();
        if (oConvertUtils.isNotEmpty(selections)) {
            String[] arr = selections.split(",");

            for(String id : arr) {
                int index = id.indexOf("@");
                if (index > 0) {
                    String temp = id.substring(0, index);
                    list.add(temp);
                } else {
                    list.add(id);
                }
            }
        }

        return list;
    }

    public static Map<String, List<String>> f(String selections, List<String> subTables) {
        Map map = new HashMap(5);
        map.put("id", new ArrayList());
        if (subTables != null) {
            for(int i = 0; i < subTables.size(); ++i) {
                String tableName = (String)subTables.get(i);
                map.put(tableName + "_id", new ArrayList());
            }
        }

        if (oConvertUtils.isNotEmpty(selections)) {
            String[] arr = selections.split(",");

            for(String rowKey : arr) {
                String[] arr2 = rowKey.split("@");
                if (arr2.length > 0) {
                    ((List)map.get("id")).add(arr2[0]);
                }

                if (arr2.length > 1 && subTables != null && subTables.size() > 0) {
                    for(int i = 1; i < arr2.length; ++i) {
                        int subTableIndex = i - 1;
                        String tableName = (String)subTables.get(subTableIndex);
                        ((List)map.get(tableName + "_id")).add(arr2[i]);
                    }
                }
            }
        }

        return map;
    }

    public static List<ExcelExportEntity> b(List<OnlCgformField> lists, String pkField, String upLoadPath) {
        List entityList = new ArrayList();

        for(int i = 0; i < lists.size(); ++i) {
            if ((null == pkField || !pkField.equals(((OnlCgformField)lists.get(i)).getDbFieldName())) && ((OnlCgformField)lists.get(i)).getIsShowList() == 1) {
                String fieldName = ((OnlCgformField)lists.get(i)).getDbFieldName();
                ExcelExportEntity entity = new ExcelExportEntity(((OnlCgformField)lists.get(i)).getDbFieldTxt(), fieldName);
                if ("image".equals(((OnlCgformField)lists.get(i)).getFieldShowType())) {
                    entity.setType(1);
                    entity.setExportImageType(3);
                    entity.setImageBasePath(upLoadPath);
                    entity.setHeight((double)10.0F);
                    entity.setWidth((double)60.0F);
                } else {
                    int columnWidth = ((OnlCgformField)lists.get(i)).getDbLength() == 0 ? 12 : (((OnlCgformField)lists.get(i)).getDbLength() > 30 ? 30 : ((OnlCgformField)lists.get(i)).getDbLength());
                    if ("date".equals(((OnlCgformField)lists.get(i)).getFieldShowType())) {
                        entity.setFormat("yyyy-MM-dd");
                    } else if ("datetime".equals(((OnlCgformField)lists.get(i)).getFieldShowType())) {
                        entity.setFormat("yyyy-MM-dd HH:mm:ss");
                    }

                    if (columnWidth < 10) {
                        columnWidth = 10;
                    }

                    entity.setWidth((double)columnWidth);
                }

                entityList.add(entity);
            }
        }

        return entityList;
    }

    public static <T> List<T> a(Object obj, Class<T> clazz) {
        List result = new ArrayList();
        if (!(obj instanceof List)) {
            return null;
        } else {
            for(Object o : (List)obj) {
                result.add(clazz.cast(o));
            }

            return result;
        }
    }

    public static boolean i(String fieldName) {
        return "CREATE_TIME".equalsIgnoreCase(fieldName) || "CREATE_BY".equalsIgnoreCase(fieldName) || "UPDATE_TIME".equalsIgnoreCase(fieldName) || "UPDATE_BY".equalsIgnoreCase(fieldName) || "SYS_ORG_CODE".equalsIgnoreCase(fieldName) || "id".equalsIgnoreCase(fieldName);
    }

    public static boolean j(String tableName) {
        boolean flag = false;

        for(String key : MybatisPlusSaasConfig.TENANT_TABLE) {
            if (key.equalsIgnoreCase(tableName)) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    public static String k(String id) {
        if (id.indexOf("@") > 0) {
            id = id.substring(0, id.indexOf("@"));
        }

        return id;
    }

    public static String l(String tableName) {
        if (oConvertUtils.isEmpty(tableName)) {
            return "";
        } else {
            String[] words = tableName.split("_");
            StringBuilder abbreviation = new StringBuilder();

            for(String word : words) {
                if (!word.isEmpty()) {
                    abbreviation.append(word.charAt(0));
                }
            }

            String result = abbreviation.toString();
            if (result.length() >= 3) {
                result = result.substring(0, 3);
            } else {
                result = String.format("%-4s", result).replace(' ', 'X');
            }

            String hashStr = Md5Util.md5Encode(tableName, "UTF-8");
            result = result + "_" + hashStr.substring(0, 3);
            return result.toLowerCase();
        }
    }

    public static class b implements Comparator<String> {
        public int compare(String o1, String o2) {
            if (o1 != null && o2 != null) {
                if (o1.compareTo(o2) > 0) {
                    return 1;
                } else if (o1.compareTo(o2) < 0) {
                    return -1;
                } else {
                    return o1.compareTo(o2) == 0 ? 0 : 0;
                }
            } else {
                return -1;
            }
        }

    }

    public static class a implements Comparator<String> {
        public int compare(String o1, String o2) {
            if (o1 != null && o2 != null) {
                if (o1.length() > o2.length()) {
                    return 1;
                } else if (o1.length() < o2.length()) {
                    return -1;
                } else if (o1.compareTo(o2) > 0) {
                    return 1;
                } else if (o1.compareTo(o2) < 0) {
                    return -1;
                } else {
                    return o1.compareTo(o2) == 0 ? 0 : 0;
                }
            } else {
                return -1;
            }
        }
    }
}
