//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.dirb;

import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.DbType;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.system.query.MatchTypeEnum;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.system.util.JeecgDataAutorUtils;
import org.jeecg.common.system.vo.SysPermissionDataRuleModel;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.config.model.OnlineFieldConfig;
import org.jeecg.modules.online.config.model.b;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class a {
    @Generated
    private static final Logger d = LoggerFactory.getLogger(a.class);
    public static final String a = "jdbcTemplate";
    public static final String b = "mybatis";
    private String e;
    private String f;
    private String g;
    private boolean h;
    private List<OnlineFieldConfig> i;
    private List<String> j;
    private List<SysPermissionDataRuleModel> k;
    private Map<String, Object> l;
    private StringBuffer m;
    private StringBuffer n;
    private Map<String, Object> o;
    private String p;
    private boolean q;
    private String r;
    private int s;
    private boolean t;
    private String u;
    private Map<String, String> v;
    private Map<String, String> w;
    private String x;
    public String c;

    public a() {
    }

    public a(String alias, String dataBaseType) {
        this.e = alias;
        this.f = alias.replace(".", "");
        this.g = dataBaseType;
        if (this.g == null) {
            DbType dbType = org.jeecg.modules.online.config.dirc.d.c((b)null);
            if (dbType != null) {
                this.g = dbType.getDb();
            }
        }

        this.h = this.f(dataBaseType);
        this.m = new StringBuffer();
        this.o = new HashMap(5);
        this.k = null;
        this.j = null;
        this.r = " AND ";
        this.s = 1;
        this.t = true;
        this.u = "";
        this.v = new HashMap(5);
        this.w = new HashMap(5);
    }

    public a(String alias) {
        this(alias, (String)null);
        this.s = 2;
    }

    public a(String alias, boolean superQuery, String matchType) {
        this(alias, (String)null);
        this.q = superQuery;
        this.r = " " + matchType + " ";
        this.s = 2;
    }

    public String a(List<OnlineFieldConfig> fieldList, Map<String, Object> params) {
        this.c(fieldList, params);
        this.d();
        return this.m.toString();
    }

    public String a(List<OnlineFieldConfig> fieldList, Map<String, Object> params, List<SysPermissionDataRuleModel> authDatalist) {
        this.setAuthDatalist(authDatalist);
        this.c(fieldList, params);
        this.b(fieldList);
        this.a(params);
        this.e();
        return this.m.toString();
    }

    public String a(List<OnlineFieldConfig> fieldList, Map<String, Object> params, List<SysPermissionDataRuleModel> authDatalist, String paramPrefix) {
        this.setAuthDatalist(authDatalist);
        this.u = paramPrefix;
        this.c(fieldList, params);
        this.b(fieldList);
        this.e();
        return this.m.toString();
    }

    public String b(List<OnlineFieldConfig> fieldList, Map<String, Object> params) {
        this.c(fieldList, params);
        return this.m.toString();
    }

    public String a(List<OnlineFieldConfig> fieldList) {
        if (this.q) {
            for(OnlineFieldConfig item : fieldList) {
                String name = item.getName();
                String value = item.getVal();
                if (value != null) {
                    QueryRuleEnum rule = QueryRuleEnum.getByValue(item.getRule());
                    if (rule == null) {
                        rule = QueryRuleEnum.EQ;
                    }

                    String view = item.getView();
                    if (QueryRuleEnum.IN.equals(rule)) {
                        if (!"list_multi".equalsIgnoreCase(view) && !"checkbox".equalsIgnoreCase(view) && !"sel_depart".equalsIgnoreCase(view) && !"sel_user".equalsIgnoreCase(view)) {
                            if (!"popup".equalsIgnoreCase(view) && !"popup_dict".equalsIgnoreCase(view)) {
                                this.a(name, item.getType(), value, (QueryRuleEnum)rule);
                            } else {
                                this.f(name, value);
                            }
                        } else {
                            this.e(name, value);
                        }
                    } else {
                        this.a(name, item.getType(), value, (QueryRuleEnum)rule);
                    }
                }
            }
        }

        return this.m.toString();
    }

    public void c(List<OnlineFieldConfig> fieldList, Map<String, Object> params) {
        for(OnlineFieldConfig item : fieldList) {
            String name = item.getName();
            String type = item.getType();
            if (this.j != null && this.j.contains(name)) {
                item.setIsSearch(1);
                item.setMode("single");
            }

            if (oConvertUtils.isNotEmpty(item.getMainField()) && oConvertUtils.isNotEmpty(item.getMainTable())) {
                item.setIsSearch(1);
                item.setMode("single");
            }

            if (1 == item.getIsSearch()) {
                if ("time".equals(item.getView()) && !"group".equals(item.getMode())) {
                    item.setMode("single");
                }

                if ("group".equals(item.getMode())) {
                    String paramBegin = name + "_begin";
                    Object value_begin = params.get(this.u + paramBegin);
                    if (null != value_begin) {
                        this.b(name, " >= ", type);
                        this.b(paramBegin, type, value_begin);
                    }

                    String paramEnd = name + "_end";
                    Object value_end = params.get(this.u + paramEnd);
                    if (null != value_end) {
                        this.b(name, " <= ", type);
                        this.a(paramEnd, type, value_end, "end");
                    }
                } else {
                    Object value = params.get(this.u + name);
                    if (value != null) {
                        String view = item.getView();
                        if (!"list_multi".equals(view) && !"checkbox".equals(view) && !"sel_depart".equalsIgnoreCase(view) && !"sel_user".equalsIgnoreCase(view)) {
                            if (!"popup".equals(view) && !"popup_dict".equals(view)) {
                                if ("umeditor".equalsIgnoreCase(view)) {
                                    value = value.toString().startsWith("*") ? value.toString() : "*" + value.toString();
//                                    Object value = value.toString().startsWith("*") ? value.toString() : "*" + value.toString();
                                    value = value.toString().endsWith("*") ? value.toString() : value.toString() + "*";
                                }

                                this.a(name, type, value);
                            } else {
                                this.f(name, value);
                            }
                        } else {
                            this.e(name, value);
                        }
                    }
                }
            }
        }

        for(String key : params.keySet()) {
            if (key.startsWith("popup_param_pre__")) {
                Object value = params.get(key);
                if (value != null) {
                    String name = key.replace("popup_param_pre__", "");
                    this.a(name, "", value);
                }
            }
        }

    }

    public void setAuthList(List<SysPermissionDataRuleModel> authDatalist) {
        this.k = authDatalist;
    }

    private void d() {
        List list = JeecgDataAutorUtils.loadDataSearchConditon();
        if (list != null && list.size() > 0) {
            for(int i = 0; i < list.size(); ++i) {
                SysPermissionDataRuleModel dataRule = (SysPermissionDataRuleModel)list.get(i);
                if (dataRule == null) {
                    break;
                }

                String ruleValue = dataRule.getRuleValue();
                if (!oConvertUtils.isEmpty(ruleValue)) {
                    String condition = dataRule.getRuleConditions();
                    if (QueryRuleEnum.SQL_RULES.getValue().equals(condition)) {
                        this.b("", QueryGenerator.getSqlRuleValue(ruleValue));
                    } else {
                        QueryRuleEnum rule = QueryRuleEnum.getByValue(dataRule.getRuleConditions());
                        String type = "Integer";
                        ruleValue = ruleValue.trim();
                        if (ruleValue.startsWith("'") && ruleValue.endsWith("'")) {
                            type = "string";
                            ruleValue = ruleValue.substring(1, ruleValue.length() - 1);
                        } else if (ruleValue.startsWith("#{") && ruleValue.endsWith("}")) {
                            type = "string";
                        }

                        String value = this.a(ruleValue);
                        this.a(dataRule.getRuleColumn(), type, value, (QueryRuleEnum)rule);
                    }
                }
            }
        }

    }

    private String a(String ruleValue) {
        Pattern pattern = Pattern.compile("#\\{.*?}");
        Matcher matcher = pattern.matcher(ruleValue);

        while(matcher.find()) {
            String matchStr = matcher.group();
            if (oConvertUtils.isNotEmpty(matchStr)) {
                String replaceStr = QueryGenerator.converRuleValue(matchStr);
                ruleValue = matcher.replaceFirst(Matcher.quoteReplacement(replaceStr != null ? replaceStr : ""));
                matcher = pattern.matcher(ruleValue);
            }
        }

        return ruleValue;
    }

    private OnlineFieldConfig a(String name, List<OnlineFieldConfig> fieldList) {
        if (fieldList != null && name != null) {
            String underLineName = oConvertUtils.camelToUnderline(name);

            for(int i = 0; i < fieldList.size(); ++i) {
                OnlineFieldConfig onlineField = (OnlineFieldConfig)fieldList.get(i);
                String fieldName = onlineField.getName();
                if (name.equals(fieldName) || underLineName.equals(fieldName)) {
                    return onlineField;
                }
            }
        }

        return null;
    }

    private void b(List<OnlineFieldConfig> fieldList) {
        List list = this.k;
        if (list == null) {
            list = JeecgDataAutorUtils.loadDataSearchConditon();
        }

        if (list != null && list.size() > 0) {
            for(int i = 0; i < list.size(); ++i) {
                SysPermissionDataRuleModel dataRule = (SysPermissionDataRuleModel)list.get(i);
                if (dataRule == null) {
                    break;
                }

                String ruleValue = dataRule.getRuleValue();
                if (!oConvertUtils.isEmpty(ruleValue)) {
                    String condition = dataRule.getRuleConditions();
                    if (QueryRuleEnum.SQL_RULES.getValue().equals(condition)) {
                        this.b("", QueryGenerator.getSqlRuleValue(ruleValue));
                    } else {
                        String fieldName = dataRule.getRuleColumn();
                        OnlineFieldConfig item = this.a(fieldName, fieldList);
                        if (item != null) {
                            String value = QueryGenerator.converRuleValue(ruleValue);
                            QueryRuleEnum rule = QueryRuleEnum.getByValue(dataRule.getRuleConditions());
                            String view = item.getView();
                            if (QueryRuleEnum.IN.equals(rule)) {
                                if (!"list_multi".equalsIgnoreCase(view) && !"checkbox".equalsIgnoreCase(view) && !"sel_depart".equalsIgnoreCase(view) && !"sel_user".equalsIgnoreCase(view)) {
                                    if (!"popup".equalsIgnoreCase(view) && !"popup_dict".equalsIgnoreCase(view)) {
                                        this.a(item.getName(), item.getType(), value, (QueryRuleEnum)rule);
                                    } else {
                                        this.f(fieldName, value);
                                    }
                                } else {
                                    this.e(fieldName, value);
                                }
                            } else {
                                this.a(item.getName(), item.getType(), value, (QueryRuleEnum)rule);
                            }
                        }
                    }
                }
            }
        }

    }

    private void e() {
        String realTableName = org.jeecg.modules.online.cgform.dird.d.f(this.x);
        boolean isTenantTable = org.jeecg.modules.online.cgform.dird.d.j(realTableName);
        if (isTenantTable) {
            String tenantId = SpringContextUtils.getHttpServletRequest().getHeader("X-Tenant-Id");
            this.a("tenant_id", "int", tenantId, (QueryRuleEnum)QueryRuleEnum.EQ);
        }

    }

    private void a(String name, String type, Object value) {
        this.a(name, type, value, (QueryRuleEnum)null);
    }

    private void a(String name, String type, Object value, QueryRuleEnum rule) {
        if (value != null) {
            String str = value.toString();
            boolean isWarpValue = false;
            if (rule == null) {
                isWarpValue = true;
                rule = QueryGenerator.convert2Rule(value);
            }

            if (isWarpValue) {
                str = str.trim();
            }

            switch (rule) {
                case GT:
                case LT:
                    this.b(name, rule.getValue(), type);
                    if (isWarpValue) {
                        str = str.substring(1);
                    }

                    this.b(name, type, (Object)str);
                    break;
                case GE:
                case LE:
                    this.b(name, rule.getValue(), type);
                    if (isWarpValue) {
                        str = str.substring(2);
                    }

                    this.b(name, type, (Object)str);
                    break;
                case EQ:
                    this.b(name, rule.getValue(), type);
                    this.b(name, type, (Object)str);
                    break;
                case EQ_WITH_ADD:
                    this.b(name, rule.getValue(), type);
                    if (isWarpValue) {
                        str = str.replaceAll("\\+\\+", ",");
                    }

                    this.b(name, type, (Object)str);
                    break;
                case NE:
                    this.a(name, " <> ", this.r, "(");
                    if (isWarpValue) {
                        str = str.substring(1);
                    }

                    this.b(name, type, (Object)str);
                    this.a(name, " IS NULL ) ", MatchTypeEnum.OR.getValue(), type, (String)null);
                    break;
                case IN:
                    this.b(name, " in ", type);
                    this.a(name, type, str);
                    break;
                case LIKE:
                case RIGHT_LIKE:
                case LEFT_LIKE:
                    this.b(name, " like ", type);
                    if (isWarpValue) {
                        this.a(name, str);
                    } else {
                        this.a(name, str, rule);
                    }
                    break;
                case EMPTY:
                case NOT_EMPTY:
                    this.b(name, type, rule);
                    break;
                default:
                    this.b(name, " = ", type);
                    this.b(name, type, (Object)str);
            }
        }

    }

    private void a(String name, String type, String str) {
        String[] arr = str.split(",");
        if (arr.length == 0) {
            this.b("('')");
        } else {
            String paramKeyExp = "foreach_%s_%s";
            String content = "";

            for(int i = 0; i < arr.length; ++i) {
                String temp = arr[i].trim();
                String paramKey = String.format(paramKeyExp, name, i);
                if (i > 0) {
                    content = content + ",";
                }

                String realParamKey = this.g(paramKey);
                if ("jdbcTemplate".equals(this.p)) {
                    content = content + ":" + realParamKey;
                } else {
                    content = content + "#{" + this.c(realParamKey) + "}";
                }

                if (!"Long".equals(type) && !"Integer".equals(type)) {
                    this.a(paramKey, temp);
                } else {
                    this.a(paramKey, Integer.parseInt(temp));
                }
            }

            this.b("(" + content + ")");
        }

    }

    private void a(String name, String str) {
        String likeSql = this.d(name, "VARCHAR");
        this.b(likeSql);
        String temp = "";
        if ((!str.startsWith("*") || !str.endsWith("*")) && (!str.startsWith("%") || !str.endsWith("%"))) {
            if (!str.startsWith("*") && !str.startsWith("%")) {
                if (!str.endsWith("*") && !str.endsWith("%")) {
                    temp = "%" + str + "%";
                } else {
                    String var6 = str.substring(0, str.length() - 1);
                    temp = var6 + "%";
                }
            } else {
                temp = "%" + str.substring(1);
            }
        } else {
            String var10000 = str.substring(1, str.length() - 1);
            temp = "%" + var10000 + "%";
        }

        this.a(name, temp);
    }

    private void a(String name, String str, QueryRuleEnum rule) {
        String likeSql = this.d(name, "VARCHAR");
        this.b(likeSql);
        if (rule == QueryRuleEnum.LEFT_LIKE) {
            this.a(name, "%" + str);
        } else if (rule == QueryRuleEnum.RIGHT_LIKE) {
            this.a(name, str + "%");
        } else {
            this.a(name, "%" + str + "%");
        }

    }

    private void b(String name, String fieldType, QueryRuleEnum rule) {
        if (this.t) {
            this.t = false;
        } else {
            this.m.append(" ").append(this.r).append(" ");
        }

        String fieldName = this.c(name, fieldType);
        StringBuilder subSql = new StringBuilder();
        boolean checkEmptyString = "text".equalsIgnoreCase(fieldType) && (DbType.MYSQL.getDb().equalsIgnoreCase(this.g) || DbType.MARIADB.getDb().equalsIgnoreCase(this.g));
        if (QueryRuleEnum.EMPTY.equals(rule)) {
            subSql.append(fieldName).append(" IS NULL ");
            if (checkEmptyString) {
                subSql.append(" OR ").append(fieldName).append(" = '' ");
            }
        } else if (QueryRuleEnum.NOT_EMPTY.equals(rule)) {
            subSql.append(fieldName).append(" IS NOT NULL ");
            if (checkEmptyString) {
                subSql.append(" AND ").append(fieldName).append(" != '' ");
            }
        }

        this.m.append(" (").append(subSql).append(") ");
    }

    private void b(String name, String type, Object value) {
        this.a(name, type, value, (String)null);
    }

    private void a(String name, String type, Object value, String searchType) {
        String type_lowerCase = type.toLowerCase();
        if (this.e(type)) {
            if (org.jeecg.modules.online.cgform.dird.d.g(value.toString())) {
                this.b(value.toString());
            } else {
                this.b("''");
            }
        } else if ("datetime".equals(type_lowerCase)) {
            String valueStr = value.toString().trim();
            if (valueStr.length() <= 10) {
                if ("end".equals(searchType)) {
                    valueStr = valueStr + " 23:59:59";
                } else {
                    valueStr = valueStr + " 00:00:00";
                }
            }

            Date datetime = DateUtils.str2Date(valueStr, (SimpleDateFormat)DateUtils.datetimeFormat.get());
            this.b(name, datetime);
        } else if ("date".equals(type_lowerCase)) {
            String valueStr = value.toString().trim();
            if (valueStr.length() > 10) {
                valueStr = valueStr.substring(0, 10);
            }

            Date date = DateUtils.str2Date(valueStr, (SimpleDateFormat)DateUtils.date_sdf.get());
            this.c(name, date);
        } else {
            String temp = value.toString().trim();
            if (temp.startsWith("'") && temp.endsWith("'") && this.s == 1) {
                this.b(temp);
            } else {
                this.d(name, (Object)temp);
            }
        }

    }

    private void b(String name, String condition) {
        this.a(name, condition, this.r, (String)null);
    }

    private void b(String name, String condition, String type) {
        this.a(name, condition, this.r, type, (String)null);
    }

    private void a(String name, String condition, String matchType, String prefix) {
        this.a(name, condition, matchType, (String)null, prefix);
    }

    private String c(String name, String fieldType) {
        String fieldName = this.e + name;
        if (DbType.DM.getDb().equalsIgnoreCase(this.g) && "text".equalsIgnoreCase(fieldType)) {
            fieldName = "CONVERT(VARCHAR(5000)," + fieldName + ")";
        } else if (Validator.hasChinese(name)) {
            if (!DbType.MARIADB.getDb().equalsIgnoreCase(this.g) && !DbType.MYSQL.getDb().equalsIgnoreCase(this.g)) {
                if (DbType.ORACLE.getDb().equalsIgnoreCase(this.g) || DbType.POSTGRE_SQL.getDb().equalsIgnoreCase(this.g) || DbType.SQL_SERVER.getDb().equalsIgnoreCase(this.g)) {
                    fieldName = this.e + "\"" + name + "\"";
                }
            } else {
                fieldName = this.e + "`" + name + "`";
            }
        }

        return fieldName;
    }

    private void a(String name, String condition, String matchType, String fieldType, String prefix) {
        if (this.t) {
            this.t = false;
        } else {
            this.m.append(" ").append(matchType).append(" ");
        }

        if (null != prefix && !prefix.isEmpty()) {
            this.m.append(prefix);
        }

        if (!name.isEmpty()) {
            String fieldName = this.c(name, fieldType);
            this.m.append(fieldName).append(condition);
        } else {
            this.m.append(" ").append(condition).append(" ");
        }

    }

    private void b(String value) {
        this.m.append(value);
    }

    private String d(String key, String jdbcType) {
        key = this.g(key);
        if ("jdbcTemplate".equals(this.p)) {
            return ":" + key;
        } else {
            String paramKey = this.c(key);
            return jdbcType == null ? String.format("#{%s}", paramKey) : String.format("#{%s, jdbcType=%s}", paramKey, jdbcType);
        }
    }

    private String c(String key) {
        String var10000 = this.d(key);
        return "param." + var10000;
    }

    private void a(String key, Object paramValue) {
        key = this.h(key);
        this.o.put(this.d(key), paramValue);
    }

    private String d(String key) {
        return this.s == 1 ? key : this.f + "_" + key;
    }

    private void b(String key, Object paramValue) {
        if (paramValue != null) {
            String str = this.d(key, "TIMESTAMP");
            this.b(str);
            this.a(key, paramValue);
        }

    }

    private void c(String key, Object paramValue) {
        if (paramValue != null) {
            String str = this.d(key, "DATE");
            this.b(str);
            this.a(key, paramValue);
        }

    }

    private void d(String key, Object paramValue) {
        if (paramValue != null) {
            String str = this.d(key, (String)null);
            this.b(str);
            this.a(key, paramValue);
        }

    }

    private boolean e(String type) {
        return "Long".equals(type) || "Integer".equals(type) || "int".equals(type) || "double".equals(type) || "BigDecimal".equals(type) || "number".equals(type);
    }

    private boolean f(String dataBaseType) {
        return !"ORACLE".equals(dataBaseType);
    }

    public static String a(String sql, long firstParam) {
        return sql.replaceFirst("\\?", String.valueOf(firstParam));
    }

    public static String a(String sql, long firstParam, long secondParam) {
        sql = sql.replaceFirst("\\?", String.valueOf(firstParam));
        return sql.replaceFirst("\\?", String.valueOf(secondParam));
    }

    private void e(String name, Object value) {
        if (value != null) {
            String[] vals = value.toString().split(",");
            String multiSelectSql = "";
            String field = this.e + name;

            for(int i = 0; i < vals.length; ++i) {
                String temp = field + " like '%" + vals[i] + ",%' or " + field + " like '%," + vals[i] + "%' or " + field + " = '" + vals[i] + "'";
                if (multiSelectSql.length() == 0) {
                    multiSelectSql = temp;
                } else {
                    multiSelectSql = multiSelectSql + " or " + temp;
                }
            }

            if (multiSelectSql.length() > 0) {
                String str = "(" + multiSelectSql + ")";
                this.b("", str);
            }
        }

    }

    private void f(String name, Object value) {
        if (value != null) {
            String field = this.e + name;
            String popupSql = "";
            String paramKeyExp = "popup_%s_%s";
            String[] vals = value.toString().split(",");

            for(int i = 0; i < vals.length; ++i) {
                String paramKey = String.format(paramKeyExp, name, i);
                String likeSql = this.d(paramKey, "VARCHAR");
                String paramValue = "%" + vals[i] + "%";
                this.a(paramKey, paramValue);
                String temp = field + " like " + likeSql;
                if (popupSql.length() == 0) {
                    popupSql = temp;
                } else {
                    popupSql = popupSql + " or " + temp;
                }
            }

            if (popupSql.length() > 0) {
                String str = "(" + popupSql + ")";
                this.b("", str);
            }
        }

    }

    private void a(Map<String, Object> requestParams) {
        Object superQueryMatchTypeObject = requestParams.get("superQueryMatchType");
        MatchTypeEnum mte = MatchTypeEnum.getByValue(superQueryMatchTypeObject);
        if (mte == null) {
            mte = MatchTypeEnum.AND;
        }

        Object superQueryParamsObject = requestParams.get("superQueryParams");
        if (superQueryParamsObject != null && !StringUtils.isBlank(superQueryParamsObject.toString())) {
            String superQueryParams = null;

            try {
                superQueryParams = URLDecoder.decode(superQueryParamsObject.toString(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return;
            }

            JSONArray array = JSONArray.parseArray(superQueryParams);
            IOnlCgformFieldService service = (IOnlCgformFieldService)SpringContextUtils.getBean(IOnlCgformFieldService.class);
            List ls = new ArrayList();
            ls.add("JEECG_SUPER_QUERY_MAIN_TABLE");
            if (this.c != null && !"".equals(this.c)) {
                String[] arr = this.c.split(",");

                for(String str : arr) {
                    ls.add(str);
                }
            }

            Map fkeyInfo = new HashMap(5);
            StringBuffer sb = new StringBuffer();

            for(int j = 0; j < ls.size(); ++j) {
                String table = (String)ls.get(j);
                List fieldList = new ArrayList();

                for(int i = 0; i < array.size(); ++i) {
                    JSONObject parameter = array.getJSONObject(i);
                    String field = parameter.getString("field");
                    if (!oConvertUtils.isEmpty(field)) {
                        String[] fieldArray = field.split(",");
                        OnlineFieldConfig temp = new OnlineFieldConfig(parameter);
                        if ("JEECG_SUPER_QUERY_MAIN_TABLE".equals(table) && fieldArray.length == 1) {
                            boolean valIsNumber = org.jeecg.modules.online.cgform.dird.d.g(temp.getVal());
                            if (valIsNumber || !"bigdecimal".equalsIgnoreCase(temp.getType())) {
                                fieldList.add(temp);
                            }
                        } else if (fieldArray.length == 2 && fieldArray[0].equals(table)) {
                            fieldList.add(temp);
                            JSONObject subTable = (JSONObject)fkeyInfo.get(table);
                            if (subTable == null) {
                                List<OnlCgformField> subTableFields = service.queryFormFieldsByTableName(table);
                                subTable = new JSONObject();

                                for(OnlCgformField cgformField : subTableFields) {
                                    if (StringUtils.isNotBlank(cgformField.getMainTable())) {
                                        subTable.put("subTableName", table);
                                        subTable.put("subField", cgformField.getDbFieldName());
                                        subTable.put("mainTable", cgformField.getMainTable());
                                        subTable.put("mainField", cgformField.getMainField());
                                    }
                                }

                                fkeyInfo.put(table, subTable);
                            }
                        }
                    }
                }

                if (fieldList.size() > 0) {
                    String tempAlias = j == 0 ? this.e : this.f + j + ".";
                    a handler = new a(tempAlias, true, mte.getValue());
                    handler.setDuplicateParamNameRecord(this.getDuplicateParamNameRecord());
                    handler.setDuplicateSqlNameRecord(this.getDuplicateSqlNameRecord());
                    String tempSql = handler.a(fieldList);
                    Map tempParam = handler.getSqlParams();
                    if (tempSql != null && tempSql.length() > 0) {
                        if (j == 0) {
                            sb.append(" ").append(tempSql).append(" ");
                            this.o.putAll(tempParam);
                        } else {
                            JSONObject subTable = (JSONObject)fkeyInfo.get(table);
                            String subTableName = subTable.getString("subTableName");
                            String subField = subTable.getString("subField");
                            String mainField = subTable.getString("mainField");
                            String subTableSqlTemplate = " %s in (select %s from %s %s where ";
                            String subTableSql = String.format(subTableSqlTemplate, mainField, subField, subTableName, this.f + j);
                            this.o.putAll(tempParam);
                            sb.append(mte.getValue()).append(subTableSql).append(tempSql).append(") ");
                        }
                    }
                }
            }

            String finalSuperQuerySql = sb.toString();
            if (finalSuperQuerySql.length() > 0) {
                if (finalSuperQuerySql.startsWith("AND ")) {
                    finalSuperQuerySql = finalSuperQuerySql.substring(3);
                } else if (finalSuperQuerySql.startsWith("OR ")) {
                    finalSuperQuerySql = finalSuperQuerySql.substring(2);
                }

                this.b("", "(" + finalSuperQuerySql + ")");
            }

        }
    }

    private String g(String key) {
        return this.a(key, this.v);
    }

    private String h(String key) {
        return this.a(key, this.w);
    }

    private String a(String key, Map<String, String> map) {
        String realKey = (String)map.get(key);
        if (realKey == null) {
            realKey = key;
            map.put(key, key + "_1");
        } else {
            String numStr = realKey.substring(realKey.lastIndexOf("_") + 1);
            String nextKey = key + "_" + (Integer.parseInt(numStr) + 1);
            map.put(key, nextKey);
        }

        return realKey;
    }

    @Generated
    public String getAlias() {
        return this.e;
    }

    @Generated
    public String getAliasNoPoint() {
        return this.f;
    }

    @Generated
    public String getDataBaseType() {
        return this.g;
    }

    @Generated
    public boolean a() {
        return this.h;
    }

    @Generated
    public List<OnlineFieldConfig> getFieldList() {
        return this.i;
    }

    @Generated
    public List<String> getNeedList() {
        return this.j;
    }

    @Generated
    public List<SysPermissionDataRuleModel> getAuthDatalist() {
        return this.k;
    }

    @Generated
    public Map<String, Object> getReqParams() {
        return this.l;
    }

    @Generated
    public StringBuffer getSql() {
        return this.m;
    }

    @Generated
    public StringBuffer getSuperQuerySql() {
        return this.n;
    }

    @Generated
    public Map<String, Object> getSqlParams() {
        return this.o;
    }

    @Generated
    public String getDaoType() {
        return this.p;
    }

    @Generated
    public boolean b() {
        return this.q;
    }

    @Generated
    public String getMatchType() {
        return this.r;
    }

    @Generated
    public int getUsePage() {
        return this.s;
    }

    @Generated
    public boolean c() {
        return this.t;
    }

    @Generated
    public String getParamPrefix() {
        return this.u;
    }

    @Generated
    public Map<String, String> getDuplicateSqlNameRecord() {
        return this.v;
    }

    @Generated
    public Map<String, String> getDuplicateParamNameRecord() {
        return this.w;
    }

    @Generated
    public String getTableName() {
        return this.x;
    }

    @Generated
    public String getSubTableStr() {
        return this.c;
    }

    @Generated
    public void setAlias(final String alias) {
        this.e = alias;
    }

    @Generated
    public void setAliasNoPoint(final String aliasNoPoint) {
        this.f = aliasNoPoint;
    }

    @Generated
    public void setDataBaseType(final String dataBaseType) {
        this.g = dataBaseType;
    }

    @Generated
    public void setDateStringSearch(final boolean dateStringSearch) {
        this.h = dateStringSearch;
    }

    @Generated
    public void setFieldList(final List<OnlineFieldConfig> fieldList) {
        this.i = fieldList;
    }

    @Generated
    public void setNeedList(final List<String> needList) {
        this.j = needList;
    }

    @Generated
    public void setAuthDatalist(final List<SysPermissionDataRuleModel> authDatalist) {
        this.k = authDatalist;
    }

    @Generated
    public void setReqParams(final Map<String, Object> reqParams) {
        this.l = reqParams;
    }

    @Generated
    public void setSql(final StringBuffer sql) {
        this.m = sql;
    }

    @Generated
    public void setSuperQuerySql(final StringBuffer superQuerySql) {
        this.n = superQuerySql;
    }

    @Generated
    public void setSqlParams(final Map<String, Object> sqlParams) {
        this.o = sqlParams;
    }

    @Generated
    public void setDaoType(final String daoType) {
        this.p = daoType;
    }

    @Generated
    public void setSuperQuery(final boolean superQuery) {
        this.q = superQuery;
    }

    @Generated
    public void setMatchType(final String matchType) {
        this.r = matchType;
    }

    @Generated
    public void setUsePage(final int usePage) {
        this.s = usePage;
    }

    @Generated
    public void setFirst(final boolean first) {
        this.t = first;
    }

    @Generated
    public void setParamPrefix(final String paramPrefix) {
        this.u = paramPrefix;
    }

    @Generated
    public void setDuplicateSqlNameRecord(final Map<String, String> duplicateSqlNameRecord) {
        this.v = duplicateSqlNameRecord;
    }

    @Generated
    public void setDuplicateParamNameRecord(final Map<String, String> duplicateParamNameRecord) {
        this.w = duplicateParamNameRecord;
    }

    @Generated
    public void setTableName(final String tableName) {
        this.x = tableName;
    }

    @Generated
    public void setSubTableStr(final String subTableStr) {
        this.c = subTableStr;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof a)) {
            return false;
        } else {
            a other = (a)o;
            if (!other.a(this)) {
                return false;
            } else if (this.a() != other.a()) {
                return false;
            } else if (this.b() != other.b()) {
                return false;
            } else if (this.getUsePage() != other.getUsePage()) {
                return false;
            } else if (this.c() != other.c()) {
                return false;
            } else {
                Object this$alias = this.getAlias();
                Object other$alias = other.getAlias();
                if (this$alias == null) {
                    if (other$alias != null) {
                        return false;
                    }
                } else if (!this$alias.equals(other$alias)) {
                    return false;
                }

                Object this$aliasNoPoint = this.getAliasNoPoint();
                Object other$aliasNoPoint = other.getAliasNoPoint();
                if (this$aliasNoPoint == null) {
                    if (other$aliasNoPoint != null) {
                        return false;
                    }
                } else if (!this$aliasNoPoint.equals(other$aliasNoPoint)) {
                    return false;
                }

                Object this$dataBaseType = this.getDataBaseType();
                Object other$dataBaseType = other.getDataBaseType();
                if (this$dataBaseType == null) {
                    if (other$dataBaseType != null) {
                        return false;
                    }
                } else if (!this$dataBaseType.equals(other$dataBaseType)) {
                    return false;
                }

                Object this$fieldList = this.getFieldList();
                Object other$fieldList = other.getFieldList();
                if (this$fieldList == null) {
                    if (other$fieldList != null) {
                        return false;
                    }
                } else if (!this$fieldList.equals(other$fieldList)) {
                    return false;
                }

                Object this$needList = this.getNeedList();
                Object other$needList = other.getNeedList();
                if (this$needList == null) {
                    if (other$needList != null) {
                        return false;
                    }
                } else if (!this$needList.equals(other$needList)) {
                    return false;
                }

                Object this$authDatalist = this.getAuthDatalist();
                Object other$authDatalist = other.getAuthDatalist();
                if (this$authDatalist == null) {
                    if (other$authDatalist != null) {
                        return false;
                    }
                } else if (!this$authDatalist.equals(other$authDatalist)) {
                    return false;
                }

                Object this$reqParams = this.getReqParams();
                Object other$reqParams = other.getReqParams();
                if (this$reqParams == null) {
                    if (other$reqParams != null) {
                        return false;
                    }
                } else if (!this$reqParams.equals(other$reqParams)) {
                    return false;
                }

                Object this$sql = this.getSql();
                Object other$sql = other.getSql();
                if (this$sql == null) {
                    if (other$sql != null) {
                        return false;
                    }
                } else if (!this$sql.equals(other$sql)) {
                    return false;
                }

                Object this$superQuerySql = this.getSuperQuerySql();
                Object other$superQuerySql = other.getSuperQuerySql();
                if (this$superQuerySql == null) {
                    if (other$superQuerySql != null) {
                        return false;
                    }
                } else if (!this$superQuerySql.equals(other$superQuerySql)) {
                    return false;
                }

                Object this$sqlParams = this.getSqlParams();
                Object other$sqlParams = other.getSqlParams();
                if (this$sqlParams == null) {
                    if (other$sqlParams != null) {
                        return false;
                    }
                } else if (!this$sqlParams.equals(other$sqlParams)) {
                    return false;
                }

                Object this$daoType = this.getDaoType();
                Object other$daoType = other.getDaoType();
                if (this$daoType == null) {
                    if (other$daoType != null) {
                        return false;
                    }
                } else if (!this$daoType.equals(other$daoType)) {
                    return false;
                }

                Object this$matchType = this.getMatchType();
                Object other$matchType = other.getMatchType();
                if (this$matchType == null) {
                    if (other$matchType != null) {
                        return false;
                    }
                } else if (!this$matchType.equals(other$matchType)) {
                    return false;
                }

                Object this$paramPrefix = this.getParamPrefix();
                Object other$paramPrefix = other.getParamPrefix();
                if (this$paramPrefix == null) {
                    if (other$paramPrefix != null) {
                        return false;
                    }
                } else if (!this$paramPrefix.equals(other$paramPrefix)) {
                    return false;
                }

                Object this$duplicateSqlNameRecord = this.getDuplicateSqlNameRecord();
                Object other$duplicateSqlNameRecord = other.getDuplicateSqlNameRecord();
                if (this$duplicateSqlNameRecord == null) {
                    if (other$duplicateSqlNameRecord != null) {
                        return false;
                    }
                } else if (!this$duplicateSqlNameRecord.equals(other$duplicateSqlNameRecord)) {
                    return false;
                }

                Object this$duplicateParamNameRecord = this.getDuplicateParamNameRecord();
                Object other$duplicateParamNameRecord = other.getDuplicateParamNameRecord();
                if (this$duplicateParamNameRecord == null) {
                    if (other$duplicateParamNameRecord != null) {
                        return false;
                    }
                } else if (!this$duplicateParamNameRecord.equals(other$duplicateParamNameRecord)) {
                    return false;
                }

                Object this$tableName = this.getTableName();
                Object other$tableName = other.getTableName();
                if (this$tableName == null) {
                    if (other$tableName != null) {
                        return false;
                    }
                } else if (!this$tableName.equals(other$tableName)) {
                    return false;
                }

                Object this$subTableStr = this.getSubTableStr();
                Object other$subTableStr = other.getSubTableStr();
                if (this$subTableStr == null) {
                    if (other$subTableStr != null) {
                        return false;
                    }
                } else if (!this$subTableStr.equals(other$subTableStr)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean a(final Object other) {
        return other instanceof a;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.a() ? 79 : 97);
        result = result * 59 + (this.b() ? 79 : 97);
        result = result * 59 + this.getUsePage();
        result = result * 59 + (this.c() ? 79 : 97);
        Object $alias = this.getAlias();
        result = result * 59 + ($alias == null ? 43 : $alias.hashCode());
        Object $aliasNoPoint = this.getAliasNoPoint();
        result = result * 59 + ($aliasNoPoint == null ? 43 : $aliasNoPoint.hashCode());
        Object $dataBaseType = this.getDataBaseType();
        result = result * 59 + ($dataBaseType == null ? 43 : $dataBaseType.hashCode());
        Object $fieldList = this.getFieldList();
        result = result * 59 + ($fieldList == null ? 43 : $fieldList.hashCode());
        Object $needList = this.getNeedList();
        result = result * 59 + ($needList == null ? 43 : $needList.hashCode());
        Object $authDatalist = this.getAuthDatalist();
        result = result * 59 + ($authDatalist == null ? 43 : $authDatalist.hashCode());
        Object $reqParams = this.getReqParams();
        result = result * 59 + ($reqParams == null ? 43 : $reqParams.hashCode());
        Object $sql = this.getSql();
        result = result * 59 + ($sql == null ? 43 : $sql.hashCode());
        Object $superQuerySql = this.getSuperQuerySql();
        result = result * 59 + ($superQuerySql == null ? 43 : $superQuerySql.hashCode());
        Object $sqlParams = this.getSqlParams();
        result = result * 59 + ($sqlParams == null ? 43 : $sqlParams.hashCode());
        Object $daoType = this.getDaoType();
        result = result * 59 + ($daoType == null ? 43 : $daoType.hashCode());
        Object $matchType = this.getMatchType();
        result = result * 59 + ($matchType == null ? 43 : $matchType.hashCode());
        Object $paramPrefix = this.getParamPrefix();
        result = result * 59 + ($paramPrefix == null ? 43 : $paramPrefix.hashCode());
        Object $duplicateSqlNameRecord = this.getDuplicateSqlNameRecord();
        result = result * 59 + ($duplicateSqlNameRecord == null ? 43 : $duplicateSqlNameRecord.hashCode());
        Object $duplicateParamNameRecord = this.getDuplicateParamNameRecord();
        result = result * 59 + ($duplicateParamNameRecord == null ? 43 : $duplicateParamNameRecord.hashCode());
        Object $tableName = this.getTableName();
        result = result * 59 + ($tableName == null ? 43 : $tableName.hashCode());
        Object $subTableStr = this.getSubTableStr();
        result = result * 59 + ($subTableStr == null ? 43 : $subTableStr.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getAlias();
        return "ConditionHandler(alias=" + var10000 + ", aliasNoPoint=" + this.getAliasNoPoint() + ", dataBaseType=" + this.getDataBaseType() + ", dateStringSearch=" + this.a() + ", fieldList=" + this.getFieldList() + ", needList=" + this.getNeedList() + ", authDatalist=" + this.getAuthDatalist() + ", reqParams=" + this.getReqParams() + ", sql=" + this.getSql() + ", superQuerySql=" + this.getSuperQuerySql() + ", sqlParams=" + this.getSqlParams() + ", daoType=" + this.getDaoType() + ", superQuery=" + this.b() + ", matchType=" + this.getMatchType() + ", usePage=" + this.getUsePage() + ", first=" + this.c() + ", paramPrefix=" + this.getParamPrefix() + ", duplicateSqlNameRecord=" + this.getDuplicateSqlNameRecord() + ", duplicateParamNameRecord=" + this.getDuplicateParamNameRecord() + ", tableName=" + this.getTableName() + ", subTableStr=" + this.getSubTableStr() + ")";
    }
}
