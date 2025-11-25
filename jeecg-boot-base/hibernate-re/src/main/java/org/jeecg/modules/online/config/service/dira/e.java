//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.service.dira;

import org.apache.commons.lang.StringUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.config.dirc.a;
import org.jeecg.modules.online.config.service.DbTableHandleI;

public class e implements DbTableHandleI {
    public String getAddColumnSql(a columnMeta) {
        String var10000 = this.a(columnMeta);
        return " ADD  " + var10000;
    }

    public String getReNameFieldName(a columnMeta) {
        String var10000 = columnMeta.getOldColumnName();
        return "RENAME COLUMN  " + var10000 + " TO " + columnMeta.getColumnName();
    }

    public String getUpdateColumnSql(a cgformcolumnMeta, a datacolumnMeta) {
        String var10000 = this.a(cgformcolumnMeta, datacolumnMeta);
        return " MODIFY   " + var10000;
    }

    public String getMatchClassTypeByDataType(String dataType, int digits) {
        String result = "";
        if ("varchar2".equalsIgnoreCase(dataType)) {
            result = "string";
        }

        if ("nvarchar2".equalsIgnoreCase(dataType)) {
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
        } else if ("TIMESTAMP".equalsIgnoreCase(dataType)) {
            result = "datetime";
        } else if ("blob".equalsIgnoreCase(dataType)) {
            result = "blob";
        } else if ("clob".equalsIgnoreCase(dataType)) {
            result = "text";
        }

        return result;
    }

    public String dropTableSQL(String tableName) {
        return " DROP TABLE  " + tableName.toLowerCase() + " ";
    }

    public String getDropColumnSql(String fieldName) {
        return " DROP COLUMN " + fieldName.toUpperCase();
    }

    private String a(a columnMeta) {
        String result = "";
        if ("string".equalsIgnoreCase(columnMeta.getColunmType())) {
            String var10000 = columnMeta.getColumnName();
            result = var10000 + " varchar2(" + columnMeta.getColumnSize() + ")";
        } else if ("date".equalsIgnoreCase(columnMeta.getColunmType())) {
            result = columnMeta.getColumnName() + " date";
        } else if ("datetime".equalsIgnoreCase(columnMeta.getColunmType())) {
            result = columnMeta.getColumnName() + " date";
        } else if ("int".equalsIgnoreCase(columnMeta.getColunmType())) {
            String var5 = columnMeta.getColumnName();
            result = var5 + " NUMBER(" + columnMeta.getColumnSize() + ")";
        } else if ("double".equalsIgnoreCase(columnMeta.getColunmType())) {
            String var6 = columnMeta.getColumnName();
            result = var6 + " NUMBER(" + columnMeta.getColumnSize() + "," + columnMeta.getDecimalDigits() + ")";
        } else if ("bigdecimal".equalsIgnoreCase(columnMeta.getColunmType())) {
            String var7 = columnMeta.getColumnName();
            result = var7 + " NUMBER(" + columnMeta.getColumnSize() + "," + columnMeta.getDecimalDigits() + ")";
        } else if ("text".equalsIgnoreCase(columnMeta.getColunmType())) {
            result = columnMeta.getColumnName() + " CLOB ";
        } else if ("blob".equalsIgnoreCase(columnMeta.getColunmType())) {
            result = columnMeta.getColumnName() + " BLOB ";
        }

        result = result + (StringUtils.isNotEmpty(columnMeta.getFieldDefault()) ? " DEFAULT " + columnMeta.getFieldDefault() : " ");
        result = result + ("Y".equals(columnMeta.getIsNullable()) ? " NULL" : " NOT NULL");
        return result;
    }

    private String a(a cgformcolumnMeta, a datacolumnMeta) {
        String result = "";
        String isnull = "";
        String realDbType = datacolumnMeta.getRealDbType();
        if (!datacolumnMeta.getIsNullable().equals(cgformcolumnMeta.getIsNullable())) {
            isnull = "Y".equals(cgformcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL";
        }

        if ("string".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            if (oConvertUtils.isEmpty(realDbType) || realDbType.toLowerCase().indexOf("varchar") < 0) {
                realDbType = "varchar2";
            }

            result = cgformcolumnMeta.getColumnName() + " " + realDbType + "(" + cgformcolumnMeta.getColumnSize() + ") ";
        } else if ("date".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            result = cgformcolumnMeta.getColumnName() + " date ";
        } else if ("datetime".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            result = cgformcolumnMeta.getColumnName() + " date ";
        } else if ("int".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            String var10000 = cgformcolumnMeta.getColumnName();
            result = var10000 + " NUMBER(" + cgformcolumnMeta.getColumnSize() + ") ";
        } else if ("double".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            String var8 = cgformcolumnMeta.getColumnName();
            result = var8 + " NUMBER(" + cgformcolumnMeta.getColumnSize() + "," + cgformcolumnMeta.getDecimalDigits() + ") ";
        } else if ("bigdecimal".equalsIgnoreCase(cgformcolumnMeta.getColunmType())) {
            String var9 = cgformcolumnMeta.getColumnName();
            result = var9 + " NUMBER(" + cgformcolumnMeta.getColumnSize() + "," + cgformcolumnMeta.getDecimalDigits() + ") ";
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
