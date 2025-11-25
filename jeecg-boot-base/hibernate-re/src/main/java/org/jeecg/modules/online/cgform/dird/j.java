//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dird;

import com.alibaba.fastjson.JSONObject;
import java.math.BigDecimal;
import java.util.Map;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class j {
    public static final String a = "int";
    public static final String b = "Integer";
    public static final String c = "string";
    public static final String d = "double";
    public static final String e = "BigDecimal";
    public static final String f = "Blob";
    public static final String g = "Date";
    public static final String h = "datetime";
    public static final String i = "Timestamp";
    public static final String j = "Long";

    public static boolean a(String dbtype) {
        return "int".equals(dbtype) || "double".equals(dbtype) || "BigDecimal".equals(dbtype) || "Integer".equals(dbtype) || "Long".equals(dbtype);
    }

    public static boolean b(String dbtype) {
        return "Date".equalsIgnoreCase(dbtype) || "datetime".equalsIgnoreCase(dbtype) || "Timestamp".equalsIgnoreCase(dbtype);
    }

    public static String a(String dataBase, OnlCgformField item, JSONObject json, Map<String, Object> params) {
        String dbType = item.getDbType();
        String field = item.getDbFieldName();
        String pageType = item.getFieldShowType();
        if (json.get(field) == null) {
            return "null";
        } else {
            a(item, json);
            if ("int".equals(dbType)) {
                String valueStr = json.getString(field);
                double doubleVal = Double.parseDouble(valueStr);
                params.put(field, Math.floor(doubleVal));
                return "#{" + field + ",jdbcType=INTEGER}";
            } else if ("double".equals(dbType)) {
                params.put(field, json.getDoubleValue(field));
                return "#{" + field + ",jdbcType=DOUBLE}";
            } else if ("BigDecimal".equals(dbType)) {
                params.put(field, new BigDecimal(json.getString(field)));
                return "#{" + field + ",jdbcType=DECIMAL}";
            } else if ("Blob".equals(dbType)) {
                params.put(field, json.getString(field) != null ? json.getString(field).getBytes() : null);
                return "#{" + field + ",jdbcType=BLOB}";
            } else if (!"Date".equals(dbType) && !"datetime".equalsIgnoreCase(dbType)) {
                params.put(field, json.getString(field));
                return "#{" + field + ",jdbcType=VARCHAR}";
            } else {
                String fieldVal = json.getString(field);
                if ("ORACLE".equals(dataBase)) {
                    if ("date".equals(pageType)) {
                        params.put(field, fieldVal.length() > 10 ? fieldVal.substring(0, 10) : fieldVal);
                        return "to_date(#{" + field + "},'yyyy-MM-dd')";
                    } else {
                        params.put(field, fieldVal.length() == 10 ? json.getString(field) + " 00:00:00" : fieldVal);
                        return "to_date(#{" + field + "},'yyyy-MM-dd HH24:mi:ss')";
                    }
                } else if ("POSTGRESQL".equals(dataBase)) {
                    if ("date".equals(pageType)) {
                        params.put(field, fieldVal.length() > 10 ? fieldVal.substring(0, 10) : fieldVal);
                        return "CAST(#{" + field + "} as DATE)";
                    } else {
                        params.put(field, fieldVal.length() == 10 ? json.getString(field) + " 00:00:00" : fieldVal);
                        return "CAST(#{" + field + "} as TIMESTAMP)";
                    }
                } else {
                    params.put(field, json.getString(field));
                    return "#{" + field + "}";
                }
            }
        }
    }

    private static void a(OnlCgformField item, JSONObject json) {
        String dbType = item.getDbType().toLowerCase();
        String field = item.getDbFieldName();
        String fieldTxt = item.getDbFieldTxt();
        Integer dbLength = item.getDbLength();
        Integer dbPointLength = item.getDbPointLength();
        if (json.get(field) != null) {
            boolean flag = false;
            if (!dbType.equalsIgnoreCase("double") && !dbType.equalsIgnoreCase("BigDecimal")) {
                if (dbType.equalsIgnoreCase("string")) {
                    flag = String.valueOf(json.get(field)).length() > dbLength;
                } else if (dbType.equalsIgnoreCase("Integer") || dbType.equalsIgnoreCase("int")) {
                    flag = String.valueOf(json.get(field)).length() > dbLength;
                }
            } else {
                String[] parts = json.getBigDecimal(field).toPlainString().split("\\.");
                int integerLength = parts[0].length();
                int fractionalLength = parts.length > 1 ? parts[1].length() : 0;
                flag = integerLength > dbLength - dbPointLength || fractionalLength > dbPointLength;
            }

            if (flag) {
                throw new JeecgBootException(String.format("%s的长度超出最大长度。", fieldTxt));
            }
        }
    }
}
