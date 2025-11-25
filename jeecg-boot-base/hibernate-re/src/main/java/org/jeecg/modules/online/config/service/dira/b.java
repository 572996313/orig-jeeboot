//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.service.dira;

import org.apache.commons.lang.StringUtils;
import org.jeecg.modules.online.config.dirc.a;
import org.jeecg.modules.online.config.service.DbTableHandleI;

public class b implements DbTableHandleI {
    public String getAddColumnSql(a columnMeta) {
        String var10000 = this.a(columnMeta);
        return " ADD COLUMN " + var10000;
    }

    public String getReNameFieldName(a columnMeta) {
        String var10000 = columnMeta.getOldColumnName();
        return "RENAME COLUMN " + var10000 + " TO " + columnMeta.getColumnName();
    }

    public String getUpdateColumnSql(a cgformcolumnMeta, a datacolumnMeta) {
        String var10000 = this.a(cgformcolumnMeta, datacolumnMeta);
        return " MODIFY " + var10000;
    }

    public String getMatchClassTypeByDataType(String dataType, int digits) {
        String result = "";
        if ("varchar2".equalsIgnoreCase(dataType)) {
            result = "string";
        } else if ("varchar".equalsIgnoreCase(dataType)) {
            result = "string";
        } else if ("nvarchar2".equalsIgnoreCase(dataType)) {
            result = "string";
        } else if ("double".equalsIgnoreCase(dataType)) {
            result = "double";
        } else if ("number".equalsIgnoreCase(dataType) && digits == 0) {
            result = "int";
        } else if ("number".equalsIgnoreCase(dataType) && digits != 0) {
            result = "double";
        } else if ("int".equalsIgnoreCase(dataType)) {
            result = "int";
        } else if ("Date".equalsIgnoreCase(dataType)) {
            result = "date";
        } else if ("timestamp".equalsIgnoreCase(dataType)) {
            result = "datetime";
        } else if ("datetime".equalsIgnoreCase(dataType)) {
            result = "datetime";
        } else if ("blob".equalsIgnoreCase(dataType)) {
            result = "blob";
        } else if ("clob".equalsIgnoreCase(dataType)) {
            result = "text";
        }

        return result;
    }

    public String dropTableSQL(String tableName) {
        return " DROP TABLE IF EXISTS " + tableName.toLowerCase() + " ";
    }

    public String getDropColumnSql(String fieldName) {
        return " DROP COLUMN " + fieldName.toUpperCase();
    }

    private String a(a columnMeta) {
        String result = "(\"" + columnMeta.getColumnName() + "\"";
        if ("string".equalsIgnoreCase(columnMeta.getColunmType())) {
            result = result + " varchar2(" + columnMeta.getColumnSize() + ")";
        } else if ("date".equalsIgnoreCase(columnMeta.getColunmType())) {
            result = result + " date";
        } else if ("datetime".equalsIgnoreCase(columnMeta.getColunmType())) {
            result = result + " datetime";
        } else if ("int".equalsIgnoreCase(columnMeta.getColunmType())) {
            result = result + " INT";
        } else if ("double".equalsIgnoreCase(columnMeta.getColunmType())) {
            result = result + " NUMBER(" + columnMeta.getColumnSize() + "," + columnMeta.getDecimalDigits() + ")";
        } else if ("bigdecimal".equalsIgnoreCase(columnMeta.getColunmType())) {
            result = result + " DECIMAL(" + columnMeta.getColumnSize() + "," + columnMeta.getDecimalDigits() + ")";
        } else if ("text".equalsIgnoreCase(columnMeta.getColunmType())) {
            result = result + " CLOB ";
        } else if ("blob".equalsIgnoreCase(columnMeta.getColunmType())) {
            result = result + " BLOB ";
        }

        result = result + (StringUtils.isNotEmpty(columnMeta.getFieldDefault()) ? " DEFAULT " + columnMeta.getFieldDefault() : " ");
        result = result + ("Y".equals(columnMeta.getIsNullable()) ? " NULL" : " NOT NULL");
        result = result + ")";
        return result;
    }

    private String a(a cgformcolumnMeta, a datacolumnMeta) {
        String result = "";
        String isnull = "";
        if (!datacolumnMeta.getIsNullable().equals(cgformcolumnMeta.getIsNullable())) {
            isnull = "Y".equals(cgformcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL";
        }

        if ("string".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            String var10000 = cgformcolumnMeta.getColumnName();
            result = var10000 + " varchar2(" + cgformcolumnMeta.getColumnSize() + ")";
        } else if ("date".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            result = cgformcolumnMeta.getColumnName() + " date ";
        } else if ("datetime".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            result = cgformcolumnMeta.getColumnName() + " datetime ";
        } else if ("int".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            result = cgformcolumnMeta.getColumnName() + " INT ";
        } else if ("double".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            String var7 = cgformcolumnMeta.getColumnName();
            result = var7 + " NUMBER(" + cgformcolumnMeta.getColumnSize() + "," + cgformcolumnMeta.getDecimalDigits() + ") ";
        } else if ("bigdecimal".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            String var8 = cgformcolumnMeta.getColumnName();
            result = var8 + " DECIMAL(" + cgformcolumnMeta.getColumnSize() + "," + cgformcolumnMeta.getDecimalDigits() + ") ";
        } else if ("blob".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            result = cgformcolumnMeta.getColumnName() + " BLOB ";
        } else if ("text".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            result = cgformcolumnMeta.getColumnName() + " CLOB ";
        }

        result = result + (StringUtils.isNotEmpty(cgformcolumnMeta.getFieldDefault()) ? " DEFAULT " + cgformcolumnMeta.getFieldDefault() : " ");
        result = result + isnull;
        return result;
    }

    public String getCommentSql(a columnMeta) {
        String var10000 = columnMeta.getTableName();
        return "COMMENT ON COLUMN " + var10000 + "." + columnMeta.getColumnName() + " IS '" + columnMeta.getComment() + "'";
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
}
