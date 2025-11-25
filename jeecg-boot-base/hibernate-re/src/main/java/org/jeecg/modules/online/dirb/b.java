//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.dirb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;

public class b<T> {
    private final String a = "url";
    private final Map<String, Object> b;
    private final List<T> c;
    private final Map<String, Object> d = new HashMap();
    private final Map<String, Object> e = new HashMap();

    public b(Map<String, Object> reqParams, List<T> paramList) {
        this.b = reqParams;
        this.c = paramList;
    }

    public String a(String sql) {
        return this.a(sql, "mybatis");
    }

    public String b(String sql) {
        return this.a(sql, "jdbcTemplate");
    }

    public String c(String url) {
        Objects.requireNonNull(this);
        return this.a(url, "url");
    }

    private String a(String sql, String daoType) {
        if (this.c != null && !this.c.isEmpty()) {
            for(Object paramItem : this.c) {
                String paramName = this.a((String) paramItem);
                String paramValue = this.b((String) paramItem);
                Object value = this.b.get("self_" + paramName);
                Object searchVal = this.b.get(paramName);
                String valueStr = "";
                if (oConvertUtils.isNotEmpty(value)) {
                    valueStr = value.toString();
                } else if (oConvertUtils.isNotEmpty(searchVal)) {
                    valueStr = searchVal.toString();
                } else if (oConvertUtils.isNotEmpty(paramValue)) {
                    valueStr = paramValue;
                }

                String paramExpress = "${" + paramName + "}";
                int paramIndex = sql.indexOf(paramExpress);
                if (paramIndex > 0) {
                    if (valueStr.startsWith("'") && valueStr.endsWith("'")) {
                        valueStr = valueStr.substring(1, valueStr.length() - 1);
                    }

                    Objects.requireNonNull(this);
                    if ("url".equals(daoType)) {
                        sql = sql.replace(paramExpress, valueStr);
                    } else {
                        String sqlParamName = "_sql_param_" + paramName;
                        String paramSpace;
                        if ("jdbcTemplate".equals(daoType)) {
                            paramSpace = ":" + sqlParamName;
                        } else {
                            paramSpace = "#{param." + sqlParamName + "}";
                        }

                        String regexp = "'([^']*)\\$\\{" + paramName + "}([^']*)'";
                        Pattern pattern = Pattern.compile(regexp);
                        Matcher matcher = pattern.matcher(sql);
                        if (matcher.find()) {
                            valueStr = matcher.group(1) + valueStr + matcher.group(2);
                            sql = sql.replace(matcher.group(0), paramSpace);
                        } else {
                            String regexp1 = "'?\\$\\{" + paramName + "}'?";
                            sql = sql.replaceAll(regexp1, paramSpace);
                        }

                        this.d.put(sqlParamName, valueStr);
                    }
                } else if (oConvertUtils.isNotEmpty(valueStr)) {
                    if (paramItem instanceof OnlCgreportParam) {
                        this.d.put(paramName, value);
                        this.b.put("popup_param_pre__" + paramName, valueStr);
                    } else if (paramItem instanceof OnlCgreportParam) {
                        Objects.requireNonNull(this);
                        if ("url".equals(daoType)) {
                            sql = sql.replace(paramExpress, "");
                        } else {
                            this.e.put(paramName, valueStr);
                        }
                    }
                }
            }

            return sql;
        } else {
            return sql;
        }
    }

    private String a(T item) {
        if (item instanceof OnlCgreportParam) {
            return ((OnlCgreportParam)item).getParamName();
        } else if (item instanceof OnlCgreportParam) {
            return ((OnlCgreportParam)item).getParamName();
        } else {
            throw new JeecgBootException("不支持的类型：" + item.getClass().getName());
        }
    }

    private String b(T item) {
        if (item instanceof OnlCgreportParam) {
            return ((OnlCgreportParam)item).getParamValue();
        } else if (item instanceof OnlCgreportParam) {
            return ((OnlCgreportParam)item).getParamValue();
        } else {
            throw new JeecgBootException("不支持的类型：" + item.getClass().getName());
        }
    }

    public Map<String, Object> getSelfSqlParams() {
        return this.d;
    }

    public Map<String, Object> getOtherParams() {
        return this.e;
    }
}
