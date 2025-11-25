//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.service.dira;

import java.util.List;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.config.dirc.a;
import org.jeecg.modules.online.config.service.DbTableHandleI;

public class c implements DbTableHandleI {
    public String getAddColumnSql(a columnMeta) {
        String column = columnMeta.getColumnName();
        String type = this.a(columnMeta);
        String str = " ADD " + column + " " + type;
        if (oConvertUtils.isNotEmpty(columnMeta.getFieldDefault())) {
            str = str + " DEFAULT " + columnMeta.getFieldDefault();
            if (!"Y".equals(columnMeta.getIsNullable())) {
                str = str + " NOT NULL";
            }
        }

        return str;
    }

    public String getMatchClassTypeByDataType(String dataType, int digits) {
        String temp = dataType.toLowerCase();
        String javaType = "";
        if (!"date".equals(temp) && !"time".equals(temp)) {
            if ("timestamp".equals(temp)) {
                javaType = "datetime";
            } else if ("numeric".equals(temp)) {
                javaType = "bigdecimal";
            } else if ("double".equals(temp)) {
                javaType = "double";
            } else if ("integer".equals(temp)) {
                javaType = "int";
            } else if ("clob".equals(temp)) {
                javaType = "text";
            } else if ("blob".equals(temp)) {
                javaType = "blob";
            } else {
                javaType = "string";
            }
        } else {
            javaType = "date";
        }

        return javaType;
    }

    public String dropTableSQL(String tableName) {
        return " DROP TABLE  " + tableName.toUpperCase() + " ";
    }

    public String getDropColumnSql(String fieldName) {
        return " DROP COLUMN " + fieldName.toUpperCase();
    }

    private String a(a columnMeta) {
        String javaType = columnMeta.getColunmType().toLowerCase();
        String dbType = "";
        switch (javaType) {
            case "string" -> dbType = String.format("varchar(%s)", columnMeta.getColumnSize());
            case "date" -> dbType = "DATE";
            case "datetime" -> dbType = "TIMESTAMP";
            case "int" -> dbType = "INTEGER";
            case "double" -> dbType = "double";
            case "bigdecimal" -> dbType = String.format("NUMERIC(%s, %s)", columnMeta.getColumnSize(), columnMeta.getDecimalDigits());
            case "text" -> dbType = "CLOB";
            case "blob" -> dbType = "BLOB";
            default -> dbType = String.format("varchar(%s)", columnMeta.getColumnSize());
        }

        return dbType;
    }

    public String getReNameFieldName(a columnMeta) {
        String type = this.a(columnMeta);
        String var10000 = columnMeta.getOldColumnName();
        return " change " + var10000 + " " + columnMeta.getColumnName() + " " + type;
    }

    public String getCommentSql(a columnMeta) {
        String var10000 = columnMeta.getTableName();
        return "COMMENT ON COLUMN " + var10000 + "." + columnMeta.getColumnName() + " IS '" + columnMeta.getComment() + "'";
    }

    public String getUpdateColumnSql(a cgformcolumnMeta, a datacolumnMeta) {
        return null;
    }

    public String getSpecialHandle(a cgformcolumnMeta, a datacolumnMeta) {
        return null;
    }

    public String dropIndexs(String indexName, String tableName) {
        return "DROP INDEX " + indexName;
    }

    public String countIndex(String indexName, String tableName) {
        return "select count(*) from user_ind_columns where index_name=upper('" + indexName + "')";
    }

    private boolean a(String type) {
        String[] arr = new String[]{"clob", "blob", "text", "date", "double", "int"};
        boolean flag = false;

        for(int i = 0; i < arr.length; ++i) {
            if (arr[i].equals(type)) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    public void handleUpdateMultiSql(a meta, a config, String tableName, List<String> sqlList) {
        String column = config.getColumnName();
        String metaType = meta.getColunmType();
        String configType = config.getColunmType();
        boolean changeFlag = false;
        if ((!metaType.equals(configType) || meta.getColumnSize() != config.getColumnSize() || meta.getDecimalDigits() != config.getDecimalDigits()) && (!metaType.equals(configType) || !this.a(configType))) {
            changeFlag = true;
        }

        if ("Y".equals(config.getIsNullable()) && !config.getIsNullable().equals(meta.getIsNullable())) {
            changeFlag = true;
        }

        if ("N".equals(config.getIsNullable()) && !config.getIsNullable().equals(meta.getIsNullable())) {
            changeFlag = true;
        }

        String metaDef = meta.getFieldDefault();
        String configDef = config.getFieldDefault();
        if ((!oConvertUtils.isEmpty(metaDef) || !oConvertUtils.isEmpty(configDef)) && !configDef.equals(metaDef)) {
            changeFlag = true;
        }

        if (changeFlag) {
            String alterSql = String.format("alter table %s", tableName);
            String dropSql = alterSql + this.getDropColumnSql(meta.getColumnName());
            sqlList.add(dropSql);
            String addSql = alterSql + this.getAddColumnSql(config);
            sqlList.add(addSql);
        }

        if (!meta.b(config)) {
            String sql = String.format("COMMENT ON COLUMN %s.%s IS '%s'", tableName, column, config.getComment());
            sqlList.add(sql);
        }

    }
}
