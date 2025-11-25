//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.service.dira;

import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.config.dirc.a;
import org.jeecg.modules.online.config.service.DbTableHandleI;

public class g implements DbTableHandleI {
    public String getAddColumnSql(a columnMeta) {
        String var10000 = this.a(columnMeta);
        return " ADD  " + var10000 + ";";
    }

    public String getReNameFieldName(a columnMeta) {
        String var10000 = columnMeta.getTableName();
        return "  sp_rename '" + var10000 + "." + columnMeta.getOldColumnName() + "', '" + columnMeta.getColumnName() + "', 'COLUMN';";
    }

    public String getUpdateColumnSql(a cgformcolumnMeta, a datacolumnMeta) {
        String var10000 = this.a(cgformcolumnMeta, datacolumnMeta);
        return " ALTER COLUMN  " + var10000 + ";";
    }

    public String getMatchClassTypeByDataType(String dataType, int digits) {
        String result = "";
        if (!"varchar".equalsIgnoreCase(dataType) && !"nvarchar".equalsIgnoreCase(dataType)) {
            if ("float".equalsIgnoreCase(dataType)) {
                result = "double";
            } else if ("int".equalsIgnoreCase(dataType)) {
                result = "int";
            } else if ("Datetime".equalsIgnoreCase(dataType)) {
                result = "datetime";
            } else if ("numeric".equalsIgnoreCase(dataType)) {
                result = "bigdecimal";
            } else if (!"varbinary".equalsIgnoreCase(dataType) && !"image".equalsIgnoreCase(dataType)) {
                if ("text".equalsIgnoreCase(dataType) || "ntext".equalsIgnoreCase(dataType)) {
                    result = "text";
                }
            } else {
                result = "blob";
            }
        } else {
            result = "string";
        }

        return result;
    }

    public String dropTableSQL(String tableName) {
        return " DROP TABLE " + tableName + " ;";
    }

    public String getDropColumnSql(String fieldName) {
        return " DROP COLUMN " + fieldName + ";";
    }

    private String a(a cgfromcolumnMeta, a datacolumnMeta) {
        String result = "";
        if ("string".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var10000 = cgfromcolumnMeta.getColumnName();
            result = var10000 + " nvarchar(" + cgfromcolumnMeta.getColumnSize() + ") " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("date".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var4 = cgfromcolumnMeta.getColumnName();
            result = var4 + " datetime " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("datetime".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var5 = cgfromcolumnMeta.getColumnName();
            result = var5 + " datetime " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("int".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var6 = cgfromcolumnMeta.getColumnName();
            result = var6 + " int " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("double".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var7 = cgfromcolumnMeta.getColumnName();
            result = var7 + " float " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("bigdecimal".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var8 = cgfromcolumnMeta.getColumnName();
            result = var8 + " numeric(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ") " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("text".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var9 = cgfromcolumnMeta.getColumnName();
            result = var9 + " ntext " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("blob".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " image";
        }

        return result;
    }

    private String a(a cgfromcolumnMeta) {
        String result = "";
        if ("string".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var10000 = cgfromcolumnMeta.getColumnName();
            result = var10000 + " nvarchar(" + cgfromcolumnMeta.getColumnSize() + ") " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("date".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var3 = cgfromcolumnMeta.getColumnName();
            result = var3 + " datetime " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("datetime".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var4 = cgfromcolumnMeta.getColumnName();
            result = var4 + " datetime " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("int".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var5 = cgfromcolumnMeta.getColumnName();
            result = var5 + " int " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("double".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var6 = cgfromcolumnMeta.getColumnName();
            result = var6 + " float " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("bigdecimal".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var7 = cgfromcolumnMeta.getColumnName();
            result = var7 + " numeric(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ") " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("text".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var8 = cgfromcolumnMeta.getColumnName();
            result = var8 + " ntext " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("blob".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " image";
        }

        return result;
    }

    private String b(a cgfromcolumnMeta) {
        String result = "";
        if ("string".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var10000 = cgfromcolumnMeta.getColumnName();
            result = var10000 + " nvarchar(" + cgfromcolumnMeta.getColumnSize() + ") " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("date".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var3 = cgfromcolumnMeta.getColumnName();
            result = var3 + " datetime " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("datetime".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var4 = cgfromcolumnMeta.getColumnName();
            result = var4 + " datetime " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("int".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var5 = cgfromcolumnMeta.getColumnName();
            result = var5 + " int " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("double".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var6 = cgfromcolumnMeta.getColumnName();
            result = var6 + " float " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        }

        return result;
    }

    public String getCommentSql(a columnMeta) {
        StringBuffer commentSql = new StringBuffer("EXECUTE ");
        if (oConvertUtils.isEmpty(columnMeta.getOldColumnName())) {
            commentSql.append("sp_addextendedproperty");
        } else {
            commentSql.append("sp_updateextendedproperty");
        }

        commentSql.append(" N'MS_Description', '");
        commentSql.append(columnMeta.getComment());
        commentSql.append("', N'SCHEMA', N'dbo', N'TABLE', N'");
        commentSql.append(columnMeta.getTableName());
        commentSql.append("', N'COLUMN', N'");
        commentSql.append(columnMeta.getColumnName() + "'");
        return commentSql.toString();
    }

    public String getSpecialHandle(a cgformcolumnMeta, a datacolumnMeta) {
        return null;
    }

    public String dropIndexs(String indexName, String tableName) {
        return "DROP INDEX " + indexName + " ON " + tableName;
    }

    public String countIndex(String indexName, String tableName) {
        return "SELECT count(*) FROM sys.indexes WHERE object_id=OBJECT_ID('" + tableName + "') and NAME= '" + indexName + "'";
    }
}
