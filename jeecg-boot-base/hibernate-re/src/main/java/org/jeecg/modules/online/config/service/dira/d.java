//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.service.dira;

import org.apache.commons.lang.StringUtils;
import org.jeecg.modules.online.config.dirc.a;
import org.jeecg.modules.online.config.service.DbTableHandleI;

public class d implements DbTableHandleI {
    public String getAddColumnSql(a columnMeta) {
        String var10000 = this.a(columnMeta);
        return " ADD COLUMN " + var10000 + ";";
    }

    public String getReNameFieldName(a columnMeta) {
        String var10000 = columnMeta.getOldColumnName();
        return "CHANGE COLUMN " + var10000 + " " + this.b(columnMeta) + " ;";
    }

    public String getUpdateColumnSql(a cgformcolumnMeta, a datacolumnMeta) {
        String var10000 = this.b(cgformcolumnMeta, datacolumnMeta);
        return " MODIFY COLUMN " + var10000 + ";";
    }

    public String getMatchClassTypeByDataType(String dataType, int digits) {
        String result = "";
        if ("varchar".equalsIgnoreCase(dataType)) {
            result = "string";
        } else if ("double".equalsIgnoreCase(dataType)) {
            result = "double";
        } else if ("int".equalsIgnoreCase(dataType)) {
            result = "int";
        } else if ("Date".equalsIgnoreCase(dataType)) {
            result = "date";
        } else if ("Datetime".equalsIgnoreCase(dataType)) {
            result = "datetime";
        } else if ("decimal".equalsIgnoreCase(dataType)) {
            result = "bigdecimal";
        } else if ("text".equalsIgnoreCase(dataType)) {
            result = "text";
        } else if ("blob".equalsIgnoreCase(dataType)) {
            result = "blob";
        }

        return result;
    }

    public String dropTableSQL(String tableName) {
        return " DROP TABLE IF EXISTS " + tableName + " ;";
    }

    public String getDropColumnSql(String fieldName) {
        return " DROP COLUMN " + fieldName + ";";
    }

    private String a(a cgfromcolumnMeta, a datacolumnMeta) {
        String result = "";
        if ("string".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var10000 = cgfromcolumnMeta.getColumnName();
            result = var10000 + " varchar(" + cgfromcolumnMeta.getColumnSize() + ") " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("date".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var7 = cgfromcolumnMeta.getColumnName();
            result = var7 + " date " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("datetime".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var8 = cgfromcolumnMeta.getColumnName();
            result = var8 + " datetime " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("int".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var9 = cgfromcolumnMeta.getColumnName();
            result = var9 + " int(" + cgfromcolumnMeta.getColumnSize() + ") " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("double".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var10 = cgfromcolumnMeta.getColumnName();
            result = var10 + " double(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ") " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("bigdecimal".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var11 = cgfromcolumnMeta.getColumnName();
            result = var11 + " decimal(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ") " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("text".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var12 = cgfromcolumnMeta.getColumnName();
            result = var12 + " text " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("blob".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var13 = cgfromcolumnMeta.getColumnName();
            result = var13 + " blob " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        }

        result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getComment()) ? " COMMENT '" + cgfromcolumnMeta.getComment() + "'" : " ");
        result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " ");
        String pkType = cgfromcolumnMeta.getPkType();
        if ("id".equalsIgnoreCase(cgfromcolumnMeta.getColumnName()) && pkType != null && ("SEQUENCE".equalsIgnoreCase(pkType) || "NATIVE".equalsIgnoreCase(pkType))) {
            result = result + " AUTO_INCREMENT ";
        }

        return result;
    }

    private String b(a cgfromcolumnMeta, a datacolumnMeta) {
        String result = this.a(cgfromcolumnMeta, datacolumnMeta);
        return result;
    }

    private String a(a cgfromcolumnMeta) {
        String result = this.a(cgfromcolumnMeta, (a)null);
        return result;
    }

    private String b(a cgfromcolumnMeta) {
        String result = this.a(cgfromcolumnMeta, (a)null);
        return result;
    }

    public String getCommentSql(a columnMeta) {
        return "";
    }

    public String getSpecialHandle(a cgformcolumnMeta, a datacolumnMeta) {
        return null;
    }

    public String dropIndexs(String indexName, String tableName) {
        return "DROP INDEX " + indexName + " ON " + tableName;
    }

    public String countIndex(String indexName, String tableName) {
        return "select COUNT(*) from information_schema.statistics where table_name = '" + tableName + "'  AND index_name = '" + indexName + "'";
    }
}
