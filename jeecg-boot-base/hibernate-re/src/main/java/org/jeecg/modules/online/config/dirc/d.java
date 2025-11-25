//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.dirc;

import com.alibaba.druid.filter.config.ConfigTools;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import lombok.Generated;
import org.jeecg.common.util.CommonUtils;
import org.jeecg.common.util.CommonUtils1116;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.common.util.dynamic.db.DbTypeUtils;
import org.jeecg.modules.online.config.exception.a;
import org.jeecg.modules.online.config.model.b;
import org.jeecg.modules.online.config.service.DbTableHandleI;
import org.jeecg.modules.online.config.service.dira.c;
import org.jeecg.modules.online.config.service.dira.e;
import org.jeecg.modules.online.config.service.dira.f;
import org.jeecg.modules.online.config.service.dira.g;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class d {
    @Generated
    private static final Logger b = LoggerFactory.getLogger(d.class);
    public static String a = "";

    public static DbTableHandleI getTableHandle() throws SQLException, a {
        return a((b)null);
    }

    public static DbTableHandleI a(b dataBaseConfig) throws SQLException, a {
        DbTableHandleI dbTableHandle = null;
        DbType dbTypeEnum = c(dataBaseConfig);
        String dbType = DbTypeUtils.getDbTypeString(dbTypeEnum);
        if (DbType.DM.equals(dbTypeEnum)) {
            return new org.jeecg.modules.online.config.service.dira.b();
        } else {
            switch (dbType) {
                case "MYSQL" -> dbTableHandle = new org.jeecg.modules.online.config.service.dira.d();
                case "MARIADB" -> dbTableHandle = new org.jeecg.modules.online.config.service.dira.d();
                case "ORACLE" -> dbTableHandle = new e();
                case "DM" -> dbTableHandle = new org.jeecg.modules.online.config.service.dira.b();
                case "SQLSERVER" -> dbTableHandle = new g();
                case "POSTGRESQL" -> dbTableHandle = new f();
                case "DB2" -> dbTableHandle = new org.jeecg.modules.online.config.service.dira.a();
                case "HSQL" -> dbTableHandle = new c();
                default -> dbTableHandle = new org.jeecg.modules.online.config.service.dira.d();
            }

            return dbTableHandle;
        }
    }

    public static Connection getConnection() throws SQLException {
        DataSource dataSource = (DataSource)SpringContextUtils.getApplicationContext().getBean(DataSource.class);
        return dataSource.getConnection();
    }

    public static String getDatabaseType() throws SQLException, a {
        if (oConvertUtils.isNotEmpty(a)) {
            return a;
        } else {
            DataSource dataSource = (DataSource)SpringContextUtils.getApplicationContext().getBean(DataSource.class);
            return a(dataSource);
        }
    }

    public static boolean a() {
        try {
            return "ORACLE".equals(getDatabaseType());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (a e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String a(DataSource dataSource) throws SQLException, a {
        if ("".equals(a)) {
            Connection connection = dataSource.getConnection();

            try {
                DatabaseMetaData md = connection.getMetaData();
                String dbType = md.getDatabaseProductName().toLowerCase();
                if (dbType.indexOf("mysql") >= 0) {
                    a = "MYSQL";
                } else if (dbType.indexOf("oracle") >= 0) {
                    a = "ORACLE";
                } else if (dbType.indexOf("dm") >= 0) {
                    a = "DM";
                } else if (dbType.indexOf("sqlserver") < 0 && dbType.indexOf("sql server") < 0) {
                    if (dbType.indexOf("postgresql") < 0 && dbType.indexOf("kingbasees") < 0) {
                        if (dbType.indexOf("mariadb") >= 0) {
                            a = "MARIADB";
                        } else {
                            b.error("数据库类型:[" + dbType + "]不识别!");
                        }
                    } else {
                        a = "POSTGRESQL";
                    }
                } else {
                    a = "SQLSERVER";
                }
            } catch (Exception e) {
                b.error(e.getMessage(), e);
            } finally {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }

            }
        }

        return a;
    }

    public static String a(Connection connection) throws SQLException, a {
        if ("".equals(a)) {
            DatabaseMetaData md = connection.getMetaData();
            String dbType = md.getDatabaseProductName().toLowerCase();
            if (dbType.indexOf("mysql") >= 0) {
                a = "MYSQL";
            } else if (dbType.indexOf("oracle") >= 0) {
                a = "ORACLE";
            } else if (dbType.indexOf("sqlserver") < 0 && dbType.indexOf("sql server") < 0) {
                if (dbType.indexOf("postgresql") >= 0) {
                    a = "POSTGRESQL";
                } else if (dbType.indexOf("mariadb") >= 0) {
                    a = "MARIADB";
                } else {
                    b.error("数据库类型:[" + dbType + "]不识别!");
                }
            } else {
                a = "SQLSERVER";
            }
        }

        return a;
    }

    public static String a(String tableName, String dataBaseType) {
        switch (dataBaseType) {
            case "ORACLE":
            case "DB2":
                return tableName.toUpperCase();
            case "POSTGRESQL":
                return tableName.toLowerCase();
            default:
                return tableName;
        }
    }

    public static Boolean a(String tableName) {
        return a(tableName, (b)null);
    }

    public static Boolean a(String tableName, b dbc) {
        Connection conn = null;
        ResultSet rs = null;

        Boolean var23;
        try {
            String[] types = new String[]{"TABLE"};
            if (dbc == null) {
                conn = getConnection();
            } else {
                conn = b(dbc);
            }

            DatabaseMetaData dbMetaData = conn.getMetaData();
            DbType dbTypeEnum = c(dbc);
            String dbTypeString = DbTypeUtils.getDbTypeString(dbTypeEnum);
            String tableNamePattern = a(tableName, dbTypeString);
            String user = null;
            if (dbc != null) {
                user = dbc.getUsername();
            } else {
                b dataBaseConfig = (b)SpringContextUtils.getBean(b.class);
                user = dataBaseConfig.getUsername();
            }

            if (DbTypeUtils.dbTypeIsOracle(dbTypeEnum) || DbType.DB2.equals(dbTypeEnum)) {
                user = user != null ? user.toUpperCase() : null;
            }

            if (DbTypeUtils.dbTypeIsSqlServer(dbTypeEnum)) {
                rs = dbMetaData.getTables(conn.getCatalog(), (String)null, tableNamePattern, types);
            } else if (DbTypeUtils.dbTypeIsPostgre(dbTypeEnum)) {
                rs = dbMetaData.getTables(conn.getCatalog(), b(conn), tableNamePattern, types);
            } else if (DbType.HSQL.equals(dbTypeEnum)) {
                rs = dbMetaData.getTables(conn.getCatalog(), "PUBLIC", tableNamePattern.toUpperCase(), types);
            } else {
                rs = dbMetaData.getTables(conn.getCatalog(), user, tableNamePattern, types);
            }

            if (!rs.next()) {
                var23 = false;
                return var23;
            }

            var23 = true;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                b.error(e.getMessage(), e);
            }

        }

        return var23;
    }

    public static String b(Connection connection) {
        try {
            Statement stmtstmt = connection.createStatement();
            ResultSet rs = stmtstmt.executeQuery("SELECT current_schema()");
            if (rs.next()) {
                String currentSchema = rs.getString(1);
                return currentSchema;
            } else {
                return "public";
            }
        } catch (SQLException var4) {
            return "public";
        }
    }

    public static Map<String, Object> a(List<Map<String, Object>> queryForList) {
        Map columnMap = new HashMap(5);

        for(int i = 0; i < queryForList.size(); ++i) {
            columnMap.put(((Map)queryForList.get(i)).get("column_name").toString(), queryForList.get(i));
        }

        return columnMap;
    }

    public static String getDialect() throws SQLException, a {
        String databaseType = getDatabaseType();
        return b(databaseType);
    }

    public static String b(String databaseType) throws SQLException, a {
        String dialect = "org.hibernate.dialect.MySQL5InnoDBDialect";
        switch (databaseType) {
            case "SQLSERVER":
                dialect = "org.hibernate.dialect.SQLServerDialect";
                break;
            case "POSTGRESQL":
            case "KINGBASEES":
                dialect = "org.hibernate.dialect.PostgreSQLDialect";
                break;
            case "ORACLE":
                dialect = "org.hibernate.dialect.OracleDialect";
                break;
            case "DM":
                dialect = "org.hibernate.dialect.DmDialect";
        }

        return dialect;
    }

    public static String c(String fileName) {
        return fileName;
    }

    public static Connection b(b db) throws SQLException {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(db.getDriverClassName());
        ds.setUrl(db.getUrl());
        ds.setUsername(db.getUsername());
        String password = db.getPassword();
        if (password != null) {
            org.jeecg.modules.online.config.model.d druid = db.getDruid();
            if (druid != null && oConvertUtils.isNotEmpty(druid.getPublicKey())) {
                b.info("dbconfig.getDruid().getPublicKey() = {}", druid.getPublicKey());

                try {
                    String decryptPassword = ConfigTools.decrypt(druid.getPublicKey(), password);
                    b.debug("解密密码 decryptPassword = {}", decryptPassword);
                    password = decryptPassword;
                } catch (Exception e) {
                    b.error(e.getMessage(), e);
                }
            }
        }

        ds.setPassword(password);
        return ds.getConnection();
    }

    public static DbType c(b dbc) {
        return dbc == null ? CommonUtils1116.getDatabaseTypeEnum() : JdbcUtils.getDbType(dbc.getUrl());
    }

    public static ResourceBundle d(String key) {
        ResourceBundle rb = null;
        BufferedInputStream inputStream = null;
        String var10000 = System.getProperty("user.dir");
        String proFilePath = var10000 + File.separator + "config" + File.separator + key + ".properties";

        try {
            inputStream = new BufferedInputStream(new FileInputStream(proFilePath));
            rb = new PropertyResourceBundle(inputStream);
            inputStream.close();
            if (rb != null) {
            }
        } catch (IOException var13) {
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return rb;
    }
}
