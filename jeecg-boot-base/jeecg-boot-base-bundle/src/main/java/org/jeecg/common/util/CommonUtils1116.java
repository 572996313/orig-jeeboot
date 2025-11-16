package org.jeecg.common.util;

import com.alibaba.fastjson.JSONObject;
//import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
//import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.constant.DataBaseConstant;
import org.jeecg.common.constant.ServiceNameConstants;

import jakarta.servlet.http.HttpServletRequest;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 通用工具
 * @author: jeecg-boot
 */
@Slf4j
public class CommonUtils1116 {


    /** 当前系统数据库类型 */
    private static String DB_TYPE = "";
    private static DbType dbTypeEnum = null;
    /**
     * 全局获取平台数据库类型（作废了）
     * @return
     */
    @Deprecated
    public static String getDatabaseType() {
        if(oConvertUtils.isNotEmpty(DB_TYPE)){
            return DB_TYPE;
        }
        DataSource dataSource = SpringContextUtils.getApplicationContext().getBean(DataSource.class);
        try {
            return getDatabaseTypeByDataSource(dataSource);
        } catch (SQLException e) {
            //e.printStackTrace();
            log.warn(e.getMessage(),e);
            return "";
        }
    }
    /**
     * 获取数据库类型
     * @param dataSource
     * @return
     * @throws SQLException
     */
    private static String getDatabaseTypeByDataSource(DataSource dataSource) throws SQLException{
        if("".equals(DB_TYPE)) {
            Connection connection = dataSource.getConnection();
            try {
                DatabaseMetaData md = connection.getMetaData();
                String dbType = md.getDatabaseProductName().toUpperCase();
                String sqlserver= "SQL SERVER";
                if(dbType.indexOf(DataBaseConstant.DB_TYPE_MYSQL)>=0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_MYSQL;
                }else if(dbType.indexOf(DataBaseConstant.DB_TYPE_ORACLE)>=0 ||dbType.indexOf(DataBaseConstant.DB_TYPE_DM)>=0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_ORACLE;
                }else if(dbType.indexOf(DataBaseConstant.DB_TYPE_SQLSERVER)>=0||dbType.indexOf(sqlserver)>=0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_SQLSERVER;
                }else if(dbType.indexOf(DataBaseConstant.DB_TYPE_POSTGRESQL)>=0 || dbType.indexOf(DataBaseConstant.DB_TYPE_KINGBASEES)>=0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_POSTGRESQL;
                }else if(dbType.indexOf(DataBaseConstant.DB_TYPE_MARIADB)>=0) {
                    DB_TYPE = DataBaseConstant.DB_TYPE_MARIADB;
                }else {
                    log.error("数据库类型:[" + dbType + "]不识别!");
                    //throw new JeecgBootException("数据库类型:["+dbType+"]不识别!");
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }finally {
                connection.close();
            }
        }
        return DB_TYPE;

    }

    /**
     * 全局获取平台数据库类型（对应mybaisPlus枚举）
     * @return
     */
    public static DbType getDatabaseTypeEnum() {
        if (oConvertUtils.isNotEmpty(dbTypeEnum)) {
            return dbTypeEnum;
        }
        try {
            DataSource dataSource = SpringContextUtils.getApplicationContext().getBean(DataSource.class);
            dbTypeEnum = JdbcUtils.getDbType(dataSource.getConnection().getMetaData().getURL());
            //【采用SQL_SERVER2005引擎】QQYUN-13298 解决升级mybatisPlus后SqlServer分页使用OFFSET，无排序字段报错问题
            if (dbTypeEnum == DbType.SQL_SERVER) {
                dbTypeEnum = DbType.SQL_SERVER2005;
            }
            return dbTypeEnum;
        } catch (SQLException e) {
            log.warn(e.getMessage(), e);
            return null;
        }
    }

}