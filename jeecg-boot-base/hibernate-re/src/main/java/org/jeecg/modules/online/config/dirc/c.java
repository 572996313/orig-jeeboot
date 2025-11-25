//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.dirc;

import com.alibaba.druid.filter.config.ConfigTools;
import com.baomidou.mybatisplus.annotation.DbType;
import freemarker.template.TemplateException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.jeecg.common.util.SqlInjectionUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.common.util.dynamic.db.DbTypeUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.config.exception.a;
import org.jeecg.modules.online.config.model.b;
import org.jeecg.modules.online.config.service.DbTableHandleI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class c {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(c.class);
    private static final String b = "org/jeecg/modules/online/config/engine/tableTemplate.ftl";
    private static DbTableHandleI c;
    private static ServiceRegistry d = null;

    public c(b dataBaseConfig) throws SQLException, a {
        c = org.jeecg.modules.online.config.dirc.d.a(dataBaseConfig);
    }

    public c() throws SQLException, a {
        c = org.jeecg.modules.online.config.dirc.d.a((b)null);
    }

    public static void a(org.jeecg.modules.online.config.model.a model) throws IOException, TemplateException, HibernateException, SQLException, a {
        DbType dbTypeEnum = org.jeecg.modules.online.config.dirc.d.c(model.getDbConfig());
        if (DbTypeUtils.dbTypeIsOracle(dbTypeEnum)) {
            List newFields = new ArrayList();

            for(OnlCgformField f : model.getColumns()) {
                if ("int".equals(f.getDbType())) {
                    f.setDbType("double");
                    f.setDbPointLength(0);
                }

                newFields.add(f);
            }

            model.setColumns(newFields);
        }

        String xml = g.a("org/jeecg/modules/online/config/engine/tableTemplate.ftl", a(model, dbTypeEnum));
        a.debug("xml：{}", xml);
        Map settings = new HashMap(5);
        b dbconfig = model.getDbConfig();
        if (d == null) {
            settings.put("hibernate.connection.driver_class", dbconfig.getDriverClassName());
            settings.put("hibernate.connection.url", dbconfig.getUrl());
            settings.put("hibernate.connection.username", dbconfig.getUsername());
            String password = dbconfig.getPassword();
            if (password != null) {
                if (dbconfig.getDruid() != null && oConvertUtils.isNotEmpty(dbconfig.getDruid().getPublicKey())) {
                    try {
                        String decryptPassword = ConfigTools.decrypt(dbconfig.getDruid().getPublicKey(), password);
                        settings.put("hibernate.connection.password", decryptPassword);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    settings.put("hibernate.connection.password", password);
                }
            }

            settings.put("hibernate.show_sql", true);
            settings.put("hibernate.format_sql", true);
            settings.put("hibernate.temp.use_jdbc_metadata_defaults", false);
            settings.put("hibernate.dialect", DbTypeUtils.getDbDialect(dbTypeEnum));
            settings.put("hibernate.hbm2ddl.auto", "create");
            settings.put("hibernate.connection.autocommit", false);
            settings.put("hibernate.current_session_context_class", "thread");
            d = (new StandardServiceRegistryBuilder()).applySettings(settings).build();
        }

        MetadataSources ms = new MetadataSources(d);
        InputStream xmlInputStream = new ByteArrayInputStream(xml.getBytes("utf-8"));
        ms.addInputStream(xmlInputStream);
        Metadata metadata = ms.buildMetadata();
        SchemaExport dbExport = new SchemaExport();
        dbExport.create(EnumSet.of(TargetType.DATABASE), metadata);
        xmlInputStream.close();

        for(Object exceptionObj : dbExport.getExceptions()) {
            Exception exception = (Exception)exceptionObj;
            if ("java.sql.SQLSyntaxErrorException".equals(exception.getCause().getClass().getName())) {
                SQLSyntaxErrorException e = (SQLSyntaxErrorException)exception.getCause();
                if ("42000".equals(e.getSQLState())) {
                    if (1064 != e.getErrorCode() && 903 != e.getErrorCode()) {
                        continue;
                    }

                    a.error(e.getMessage());
                    throw new a("请确认表名是否为关键字。");
                }
            } else {
                if ("com.microsoft.sqlserver.jdbc.SQLServerException".equals(exception.getCause().getClass().getName())) {
                    if (exception.getCause().toString().indexOf("Incorrect syntax near the keyword") != -1) {
                        exception.printStackTrace();
                        throw new a(exception.getCause().getMessage());
                    }

                    a.error(exception.getMessage());
                    continue;
                }

                if (DbType.DM.equals(dbTypeEnum) || DbType.DB2.equals(dbTypeEnum)) {
                    String exceptionMsg = exception.getMessage();
                    if (exceptionMsg != null && exceptionMsg.indexOf("Error executing DDL \"drop table") >= 0) {
                        a.error(exceptionMsg);
                        continue;
                    }
                }
            }

            throw new a(exception.getMessage());
        }

    }

    public List<String> b(org.jeecg.modules.online.config.model.a model) throws a, SQLException {
        DbType dbTypeEnum = org.jeecg.modules.online.config.dirc.d.c(model.getDbConfig());
        String dbTypeString = DbTypeUtils.getDbTypeString(dbTypeEnum);
        String tableName = org.jeecg.modules.online.config.dirc.d.a(model.getTableName(), dbTypeString);
        String alterSql = "alter table  " + tableName + " ";
        List alterList = new ArrayList();

        try {
            Map dbMetaCol = this.a((String)null, tableName, (b)model.getDbConfig());
            Map configCol = this.c(model);
            Map newAndOldFieldMap = this.a(model.getColumns());

            for(Object columnNameObj : configCol.keySet()) {
                String columnName = (String)columnNameObj;
                if (!"id".equalsIgnoreCase(columnName)) {
                    if (!dbMetaCol.containsKey(columnName)) {
                        org.jeecg.modules.online.config.dirc.a cgFormColumnMeta = (org.jeecg.modules.online.config.dirc.a)configCol.get(columnName);
                        String oldFieldName = (String)newAndOldFieldMap.get(columnName);
                        if (newAndOldFieldMap.containsKey(columnName) && dbMetaCol.containsKey(oldFieldName)) {
                            org.jeecg.modules.online.config.dirc.a dataColumnMeta = (org.jeecg.modules.online.config.dirc.a)dbMetaCol.get(oldFieldName);
                            if (DbType.HSQL.equals(dbTypeEnum)) {
                                this.a(dataColumnMeta, cgFormColumnMeta, tableName, alterList);
                            } else {
                                String changeSql = c.getReNameFieldName(cgFormColumnMeta);
                                if (DbTypeUtils.dbTypeIsSqlServer(dbTypeEnum)) {
                                    alterList.add(changeSql);
                                } else {
                                    alterList.add(alterSql + changeSql);
                                }

                                dbMetaCol.put(columnName, (org.jeecg.modules.online.config.dirc.a)dbMetaCol.remove(oldFieldName));
                                if (DbType.DB2.equals(dbTypeEnum)) {
                                    this.a(dataColumnMeta, cgFormColumnMeta, tableName, alterList);
                                } else {
                                    if (!dataColumnMeta.equals(cgFormColumnMeta)) {
                                        alterList.add(alterSql + this.a(cgFormColumnMeta, dataColumnMeta));
                                        if (DbTypeUtils.dbTypeIsPostgre(dbTypeEnum)) {
                                            String updateSpecialSql = this.b(cgFormColumnMeta, dataColumnMeta);
                                            if (oConvertUtils.isNotEmpty(updateSpecialSql)) {
                                                alterList.add(alterSql + updateSpecialSql);
                                            }
                                        }
                                    }

                                    if (!DbTypeUtils.dbTypeIsSqlServer(dbTypeEnum) && !dataColumnMeta.b(cgFormColumnMeta)) {
                                        alterList.add(this.c(cgFormColumnMeta));
                                    }
                                }
                            }

                            String oldFieldSql = this.c(columnName, cgFormColumnMeta.getColumnId());
                            alterList.add(oldFieldSql);
                        } else {
                            alterList.add(alterSql + this.b(cgFormColumnMeta));
                            if (!DbTypeUtils.dbTypeIsSqlServer(dbTypeEnum) && StringUtils.isNotEmpty(cgFormColumnMeta.getComment())) {
                                alterList.add(this.c(cgFormColumnMeta));
                            }
                        }
                    } else {
                        org.jeecg.modules.online.config.dirc.a dataColumnMeta = (org.jeecg.modules.online.config.dirc.a)dbMetaCol.get(columnName);
                        org.jeecg.modules.online.config.dirc.a cgFormColumnMeta = (org.jeecg.modules.online.config.dirc.a)configCol.get(columnName);
                        if (!DbType.DB2.equals(dbTypeEnum) && !DbType.HSQL.equals(dbTypeEnum)) {
                            if (!dataColumnMeta.a(cgFormColumnMeta, dbTypeEnum)) {
                                alterList.add(alterSql + this.a(cgFormColumnMeta, dataColumnMeta));
                            }

                            if (!DbTypeUtils.dbTypeIsSqlServer(dbTypeEnum) && !DbTypeUtils.dbTypeIsOracle(dbTypeEnum) && !dataColumnMeta.b(cgFormColumnMeta)) {
                                alterList.add(this.c(cgFormColumnMeta));
                            }
                        } else {
                            this.a(dataColumnMeta, cgFormColumnMeta, tableName, alterList);
                        }
                    }
                }
            }

            for(Object columnNameObj : dbMetaCol.keySet()) {
                String columnName = (String) columnNameObj;
                if (!configCol.containsKey(columnName.toLowerCase()) && !newAndOldFieldMap.containsValue(columnName.toLowerCase())) {
                    alterList.add(alterSql + this.b(columnName));
                }
            }

            if (DbType.DB2.equals(dbTypeEnum)) {
                alterList.add("CALL SYSPROC.ADMIN_CMD('reorg table " + tableName + "')");
            }

            return alterList;
        } catch (SQLException var17) {
            throw new RuntimeException();
        }
    }

    private static Map<String, Object> a(org.jeecg.modules.online.config.model.a model, DbType dbTypeEnum) {
        String dbTypeString = DbTypeUtils.getDbTypeString(dbTypeEnum);
        Map map = new HashMap(5);

        for(OnlCgformField field : model.getColumns()) {
            field.setDbDefaultVal(c(field.getDbDefaultVal()));
        }

        map.put("entity", model);
        map.put("dataType", dbTypeString);
        map.put("db", dbTypeEnum.getDb());
        return map;
    }

    private Map<String, org.jeecg.modules.online.config.dirc.a> a(String schemaName, String tableName, b dataBaseConfig) throws SQLException {
        Map columnMap = new HashMap(5);
        Connection conn = null;

        try {
            conn = org.jeecg.modules.online.config.dirc.d.b(dataBaseConfig);
        } catch (Exception e) {
            a.error(e.getMessage(), e);
        }

        DatabaseMetaData dbMetaData = conn.getMetaData();
        String user = dataBaseConfig.getUsername();
        DbType dbTypeEnum = org.jeecg.modules.online.config.dirc.d.c(dataBaseConfig);
        if (DbTypeUtils.dbTypeIsOracle(dbTypeEnum) || DbType.DB2.equals(dbTypeEnum)) {
            user = user.toUpperCase();
        }

        ResultSet rs = null;
        if (DbTypeUtils.dbTypeIsSqlServer(dbTypeEnum)) {
            rs = dbMetaData.getColumns(conn.getCatalog(), (String)null, tableName, "%");
        } else if (DbTypeUtils.dbTypeIsPostgre(dbTypeEnum)) {
            rs = dbMetaData.getColumns(conn.getCatalog(), org.jeecg.modules.online.config.dirc.d.b(conn), tableName, "%");
        } else if (DbType.HSQL.equals(dbTypeEnum)) {
            rs = dbMetaData.getColumns(conn.getCatalog(), "PUBLIC", tableName.toUpperCase(), "%");
        } else {
            rs = dbMetaData.getColumns(conn.getCatalog(), user, tableName, "%");
        }

        while(rs.next()) {
            org.jeecg.modules.online.config.dirc.a columnMeta = new org.jeecg.modules.online.config.dirc.a();
            columnMeta.setTableName(tableName);
            String columnName = rs.getString("COLUMN_NAME").toLowerCase();
            columnMeta.setColumnName(columnName);
            String typeName = rs.getString("TYPE_NAME");
            int decimalDigits = rs.getInt("DECIMAL_DIGITS");
            String colunmType = c.getMatchClassTypeByDataType(typeName, decimalDigits);
            columnMeta.setColunmType(colunmType);
            columnMeta.setRealDbType(typeName);
            int columnSize = rs.getInt("COLUMN_SIZE");
            columnMeta.setColumnSize(columnSize);
            columnMeta.setDecimalDigits(decimalDigits);
            String isNullable = rs.getInt("NULLABLE") == 1 ? "Y" : "N";
            columnMeta.setIsNullable(isNullable);
            String comment = rs.getString("REMARKS");
            columnMeta.setComment(comment);
            String columnDef = rs.getString("COLUMN_DEF");
            String fieldDefault = c(columnDef) == null ? "" : c(columnDef);
            columnMeta.setFieldDefault(fieldDefault);
            a.debug("getColumnMetadataFormDataBase --->COLUMN_NAME:" + columnName.toUpperCase() + " TYPE_NAME :" + typeName + " DECIMAL_DIGITS:" + decimalDigits + " COLUMN_SIZE:" + columnSize);
            columnMap.put(columnName, columnMeta);
        }

        return columnMap;
    }

    private Map<String, org.jeecg.modules.online.config.dirc.a> c(org.jeecg.modules.online.config.model.a model) {
        Map columnMap = new HashMap(5);

        for(OnlCgformField field : model.getColumns()) {
            org.jeecg.modules.online.config.dirc.a columnMeta = new org.jeecg.modules.online.config.dirc.a();
            columnMeta.setTableName(model.getTableName().toLowerCase());
            columnMeta.setColumnId(field.getId());
            columnMeta.setColumnName(field.getDbFieldName().toLowerCase());
            columnMeta.setColumnSize(field.getDbLength());
            columnMeta.setColunmType(field.getDbType().toLowerCase());
            columnMeta.setIsNullable(field.getDbIsNull() == 1 ? "Y" : "N");
            columnMeta.setComment(field.getDbFieldTxt());
            columnMeta.setDecimalDigits(field.getDbPointLength());
            columnMeta.setFieldDefault(c(field.getDbDefaultVal()));
            columnMeta.setPkType(model.getJformPkType() == null ? "UUID" : model.getJformPkType());
            columnMeta.setOldColumnName(field.getDbFieldNameOld() != null ? field.getDbFieldNameOld().toLowerCase() : null);
            Logger var10000 = a;
            String var10001 = field.getDbFieldName().toLowerCase();
            var10000.debug("getColumnMetadataFormCgForm ----> DbFieldName: " + var10001 + " | DbType: " + field.getDbType().toLowerCase() + " | DbPointLength:" + field.getDbPointLength() + " | DbLength:" + field.getDbLength());
            columnMap.put(field.getDbFieldName().toLowerCase(), columnMeta);
        }

        return columnMap;
    }

    private Map<String, String> a(List<OnlCgformField> fieldList) {
        Map map = new HashMap(5);

        for(OnlCgformField field : fieldList) {
            map.put(field.getDbFieldName(), field.getDbFieldNameOld());
        }

        return map;
    }

    private String b(String fieldName) {
        return c.getDropColumnSql(fieldName);
    }

    private String a(org.jeecg.modules.online.config.dirc.a cgformcolumnMeta, org.jeecg.modules.online.config.dirc.a datacolumnMeta) throws a {
        return c.getUpdateColumnSql(cgformcolumnMeta, datacolumnMeta);
    }

    private String b(org.jeecg.modules.online.config.dirc.a cgformcolumnMeta, org.jeecg.modules.online.config.dirc.a datacolumnMeta) {
        return c.getSpecialHandle(cgformcolumnMeta, datacolumnMeta);
    }

    private void a(org.jeecg.modules.online.config.dirc.a meta, org.jeecg.modules.online.config.dirc.a config, String tableName, List<String> sqlList) {
        c.handleUpdateMultiSql(meta, config, tableName, sqlList);
    }

    private String a(org.jeecg.modules.online.config.dirc.a columnMeta) {
        return c.getReNameFieldName(columnMeta);
    }

    private String b(org.jeecg.modules.online.config.dirc.a columnMeta) {
        return c.getAddColumnSql(columnMeta);
    }

    private String c(org.jeecg.modules.online.config.dirc.a columnMeta) {
        return c.getCommentSql(columnMeta);
    }

    private String c(String columnName, String id) {
        return "update onl_cgform_field set DB_FIELD_NAME_OLD = '" + columnName + "' where ID ='" + id + "'";
    }

    private int a(String columnName, String id, Session session) {
        return session.createSQLQuery("update onl_cgform_field set DB_FIELD_NAME_OLD= '" + columnName + "' where ID ='" + id + "'").executeUpdate();
    }

    private static String c(String text) {
        if (StringUtils.isNotEmpty(text)) {
            try {
                Double.valueOf(text);
            } catch (Exception var2) {
                if (!text.startsWith("'") || !text.endsWith("'")) {
                    text = "'" + text + "'";
                }
            }
        }

        return text;
    }

    public String a(String indexName, String tableName) {
        indexName = SqlInjectionUtil.getSqlInjectField(indexName);
        tableName = SqlInjectionUtil.getSqlInjectTableName(tableName);
        return c.dropIndexs(indexName, tableName);
    }

    public String b(String indexName, String tableName) {
        indexName = SqlInjectionUtil.getSqlInjectField(indexName);
        tableName = SqlInjectionUtil.getSqlInjectTableName(tableName);
        return c.countIndex(indexName, tableName);
    }

    public static List<String> a(String tbname) throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        List list = new ArrayList();

        try {
            conn = org.jeecg.modules.online.config.dirc.d.getConnection();
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getIndexInfo((String)null, (String)null, tbname, false, false);
            ResultSetMetaData md = rs.getMetaData();

            while(rs.next()) {
                String index_name = rs.getString("INDEX_NAME");
                if (oConvertUtils.isEmpty(index_name)) {
                    index_name = rs.getString("index_name");
                }

                if (oConvertUtils.isNotEmpty(index_name)) {
                    list.add(index_name);
                }
            }
        } catch (SQLException e) {
            a.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                conn.close();
            }

        }

        return list;
    }
}
