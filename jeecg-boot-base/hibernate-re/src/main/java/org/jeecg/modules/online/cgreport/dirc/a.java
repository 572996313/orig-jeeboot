//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.dirc;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.jeecg.common.system.vo.DynamicDataSourceModel;
import org.jeecg.common.util.ReflectHelper;
import org.jeecg.common.util.dynamic.db.DataSourceCachePool;
import org.jeecg.common.util.dynamic.db.DynamicDBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class a {
    @Generated
    private static final Logger d = LoggerFactory.getLogger(a.class);
    public static final String a = " where ";
    public static final String b = " and ";
    public static final String c = " or ";

    public static String a(String sql) {
        sql = sql.replaceAll("(?i) where ", " where ");
        sql = sql.replaceAll("(?i) and ", " and ");
        sql = sql.replaceAll("(?i) or ", " or ");
        String regex = "(,\\s*|\\s*(\\w|\\.)+\\s*[^, ]+ *\\S*)\\$\\{\\w+\\}\\S*";
        Pattern p = Pattern.compile(regex);

        for(Matcher m = p.matcher(sql); m.find(); d.debug("${}替换后结果 ==>" + sql)) {
            String whereParam = m.group();
            d.debug("${}匹配带参SQL片段 ==>" + whereParam);
            if (whereParam.indexOf(" where ") != -1) {
                String cloumn = whereParam.substring(0, whereParam.indexOf(" where "));
                sql = sql.replace(whereParam, cloumn + " where 1=1");
            } else if (whereParam.indexOf(" and ") != -1) {
                whereParam = whereParam.substring(whereParam.indexOf("and"));
                if (whereParam.indexOf("(") > 0) {
                    whereParam = whereParam.substring(whereParam.indexOf("(") + 1);
                    sql = sql.replace(whereParam, " 1=1 ");
                } else {
                    sql = sql.replace(whereParam, "and 1=1");
                }
            } else if (whereParam.indexOf(" or ") != -1) {
                whereParam = whereParam.substring(whereParam.indexOf("or"));
                if (whereParam.indexOf("(") > 0) {
                    whereParam = whereParam.substring(whereParam.indexOf("(") + 1);
                    sql = sql.replace(whereParam, " 1=1 ");
                } else {
                    sql = sql.replace(whereParam, "or 1=1");
                }
            } else if (whereParam.startsWith(",")) {
                sql = sql.replace(whereParam, " ,1 ");
            } else {
                sql = sql.replace(whereParam, " 1=1 ");
            }
        }

        sql = sql.replaceAll("(?i)\\(\\s*1=1\\s*(AND|OR)", "(");
        sql = sql.replaceAll("(?i)(AND|OR)\\s*1=1", "");
        return sql;
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

    public static boolean b(String sql) {
        String temp = sql.toLowerCase();
        return temp.indexOf("select") == 0;
    }

    public static String c(String sql) {
        return String.format("SELECT COUNT(1) \"total\" FROM ( %s ) temp_count", sql);
    }

    public static Map<String, Object> a(String dbKey, String sql) {
        Map objectMap = null;
        DynamicDataSourceModel dbSource = DataSourceCachePool.getCacheDynamicDataSourceModel(dbKey);
        DbType dbType = JdbcUtils.getDbType(dbSource.getDbUrl());
        if (dbType == DbType.SQL_SERVER) {
            dbType = DbType.SQL_SERVER2005;
        }

        IDialect dialect = DialectFactory.getDialect(dbType);
        if (!sql.toUpperCase().contains("LIMIT") && !sql.toUpperCase().contains("OFFSET")) {
            Page page = new Page(1L, 1L);
            DialectModel model = dialect.buildPaginationSql(sql, page.offset(), page.getSize());
            String pageSQL = model.getDialectSql();
            if (pageSQL.contains("?")) {
                long firstParam = (Long)ReflectHelper.getFieldVal("firstParam", model);
                long secondParam = (Long)ReflectHelper.getFieldVal("secondParam", model);
                int n = pageSQL.length() - pageSQL.replaceAll("\\?", "").length();
                if (n == 1) {
                    objectMap = (Map)DynamicDBUtil.findOne(dbKey, pageSQL, new Object[]{firstParam});
                } else {
                    objectMap = (Map)DynamicDBUtil.findOne(dbKey, pageSQL, new Object[]{firstParam, secondParam});
                }
            } else {
                objectMap = (Map)DynamicDBUtil.findOne(dbKey, pageSQL, new Object[0]);
            }
        } else {
            List dataList = DynamicDBUtil.findList(dbKey, sql, new Object[0]);
            if (dataList != null && dataList.size() > 0) {
                objectMap = (Map)dataList.get(0);
            }
        }

        return objectMap;
    }

    public static List<Map<String, Object>> a(String isGetAll, String dbKey, String querySql, int pageNo, int pageSize, Map<String, Object> sqlParam) {
        DynamicDataSourceModel dbSource = DataSourceCachePool.getCacheDynamicDataSourceModel(dbKey);
        String pageSQL = querySql;
        DialectModel model = null;
        List pageList = null;
        if (!Boolean.valueOf(isGetAll)) {
            DbType dbType = JdbcUtils.getDbType(dbSource.getDbUrl());
            IDialect dialect = DialectFactory.getDialect(dbType);
            Page page = new Page((long)pageNo, (long)pageSize);
            model = dialect.buildPaginationSql(querySql, page.offset(), page.getSize());
            pageSQL = model.getDialectSql();
        }

        if (pageSQL.contains("?")) {
            long firstParam = (Long)ReflectHelper.getFieldVal("firstParam", model);
            long secondParam = (Long)ReflectHelper.getFieldVal("secondParam", model);
            int n = pageSQL.length() - pageSQL.replaceAll("\\?", "").length();
            if (n == 1) {
                pageSQL = org.jeecg.modules.online.dirb.a.a(pageSQL, firstParam);
            } else {
                pageSQL = org.jeecg.modules.online.dirb.a.a(pageSQL, firstParam, secondParam);
            }
        }

        pageList = DynamicDBUtil.findListByNamedParam(dbKey, pageSQL, sqlParam);
        return pageList;
    }
}
