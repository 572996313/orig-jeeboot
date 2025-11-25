//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.service.dira;

import java.util.List;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.config.service.DbTableHandleI;

public class a implements DbTableHandleI {
    public String getAddColumnSql(org.jeecg.modules.online.config.dirc.a columnMeta) {
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
        switch (temp) {
            case "varchar":
                javaType = "string";
                break;
            case "date":
            case "time":
                javaType = "date";
                break;
            case "timestamp":
                javaType = "datetime";
                break;
            case "integer":
                javaType = "int";
                break;
            case "double":
                javaType = "double";
                break;
            case "decimal":
                javaType = "bigdecimal";
                break;
            case "long varchar":
                javaType = "text";
                break;
            case "blob":
                javaType = "blob";
                break;
            default:
                javaType = "string";
        }

        return javaType;
    }

    public String dropTableSQL(String tableName) {
        return " DROP TABLE  " + tableName.toLowerCase() + " ";
    }

    public String getDropColumnSql(String fieldName) {
        return " DROP COLUMN " + fieldName.toUpperCase();
    }

    private String a(org.jeecg.modules.online.config.dirc.a columnMeta) {
        String javaType = columnMeta.getColunmType().toLowerCase();
        String dbType = "";
        switch (javaType) {
            case "string" -> dbType = String.format("varchar(%s)", columnMeta.getColumnSize());
            case "date" -> dbType = "DATE";
            case "datetime" -> dbType = "TIMESTAMP";
            case "int" -> dbType = "INTEGER";
            case "double" -> dbType = "double";
            case "bigdecimal" -> dbType = String.format("DECIMAL(%s, %s)", columnMeta.getColumnSize(), columnMeta.getDecimalDigits());
            case "text" -> dbType = "LONG VARCHAR";
            case "blob" -> dbType = "BLOB";
            default -> dbType = String.format("varchar(%s)", columnMeta.getColumnSize());
        }

        return dbType;
    }

    public String getReNameFieldName(org.jeecg.modules.online.config.dirc.a columnMeta) {
        String var10000 = columnMeta.getOldColumnName();
        return "RENAME COLUMN  " + var10000 + " TO " + columnMeta.getColumnName();
    }

    public String getCommentSql(org.jeecg.modules.online.config.dirc.a columnMeta) {
        String var10000 = columnMeta.getTableName();
        return "COMMENT ON COLUMN " + var10000 + "." + columnMeta.getColumnName() + " IS '" + columnMeta.getComment() + "'";
    }

    public String getUpdateColumnSql(org.jeecg.modules.online.config.dirc.a cgformcolumnMeta, org.jeecg.modules.online.config.dirc.a datacolumnMeta) {
        return null;
    }

    public String getSpecialHandle(org.jeecg.modules.online.config.dirc.a cgformcolumnMeta, org.jeecg.modules.online.config.dirc.a datacolumnMeta) {
        return null;
    }

    public String dropIndexs(String indexName, String tableName) {
        return "DROP INDEX " + indexName;
    }

    public String countIndex(String indexName, String tableName) {
        return "select count(*) from user_ind_columns where index_name=upper('" + indexName + "')";
    }

    private boolean a(String type) {
        String[] arr = new String[]{"blob", "text", "double", "int", "date"};
        boolean flag = false;

        for(int i = 0; i < arr.length; ++i) {
            if (arr[i].equals(type)) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    public void handleUpdateMultiSql(org.jeecg.modules.online.config.dirc.a meta, org.jeecg.modules.online.config.dirc.a config, String tableName, List<String> sqlList) {
        String column = config.getColumnName();
        String metaType = meta.getColunmType();
        String configType = config.getColunmType();
        if ((!metaType.equals(configType) || meta.getColumnSize() != config.getColumnSize() || meta.getDecimalDigits() != config.getDecimalDigits()) && (!metaType.equals(configType) || !this.a(configType))) {
            String dbType = this.a(config);
            sqlList.add("alter table " + tableName + " alter column " + column + " set data type " + dbType);
        }

        if ("Y".equals(config.getIsNullable()) && !config.getIsNullable().equals(meta.getIsNullable())) {
            String sql = String.format("alter table %s alter column %s drop not null", tableName, column);
            sqlList.add(sql);
        }

        if ("N".equals(config.getIsNullable()) && !config.getIsNullable().equals(meta.getIsNullable())) {
            String sql = String.format("alter table %s alter column %s set not null", tableName, column);
            sqlList.add(sql);
        }

        String metaDef = meta.getFieldDefault();
        String configDef = config.getFieldDefault();
        if ((!oConvertUtils.isEmpty(metaDef) || !oConvertUtils.isEmpty(configDef)) && !configDef.equals(metaDef)) {
            String def = oConvertUtils.isEmpty(configDef) ? "NULL" : configDef;
            String sql = String.format("alter table %s alter column %s set default %s", tableName, column, def);
            sqlList.add(sql);
        }

        if (!meta.b(config)) {
            String sql = String.format("COMMENT ON COLUMN %s.%s IS '%s'", tableName, column, config.getComment());
            sqlList.add(sql);
        }

    }
}
