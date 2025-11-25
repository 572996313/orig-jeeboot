//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.service.dira;

import org.apache.commons.lang.StringUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.config.dirc.a;
import org.jeecg.modules.online.config.service.DbTableHandleI;

public class f implements DbTableHandleI {
    public String getAddColumnSql(a columnMeta) {
        String var10000 = this.a(columnMeta);
        return " ADD COLUMN " + var10000 + ";";
    }

    public String getReNameFieldName(a columnMeta) {
        String var10000 = columnMeta.getOldColumnName();
        return " RENAME  COLUMN  " + var10000 + " to " + columnMeta.getColumnName() + ";";
    }

    public String getUpdateColumnSql(a cgformcolumnMeta, a datacolumnMeta) throws org.jeecg.modules.online.config.exception.a {
        return this.c(cgformcolumnMeta, datacolumnMeta);
    }

    public String getSpecialHandle(a cgformcolumnMeta, a datacolumnMeta) {
        String updateFieldDefault = this.d(cgformcolumnMeta, datacolumnMeta);
        return oConvertUtils.isNotEmpty(updateFieldDefault) ? "  ALTER  COLUMN   " + updateFieldDefault + ";" : null;
    }

    public String getMatchClassTypeByDataType(String dataType, int digits) {
        String result = "";
        if ("varchar".equalsIgnoreCase(dataType)) {
            result = "string";
        } else if ("double".equalsIgnoreCase(dataType)) {
            result = "double";
        } else if (dataType.contains("int")) {
            result = "int";
        } else if ("Date".equalsIgnoreCase(dataType)) {
            result = "date";
        } else if ("timestamp".equalsIgnoreCase(dataType)) {
            result = "datetime";
        } else if ("bytea".equalsIgnoreCase(dataType)) {
            result = "blob";
        } else if ("text".equalsIgnoreCase(dataType)) {
            result = "text";
        } else if ("decimal".equalsIgnoreCase(dataType)) {
            result = "bigdecimal";
        } else if ("numeric".equalsIgnoreCase(dataType)) {
            result = "bigdecimal";
        }

        return result;
    }

    public String dropTableSQL(String tableName) {
        return " DROP TABLE IF EXISTS " + tableName + " ;";
    }

    public String getDropColumnSql(String fieldName) {
        return " DROP COLUMN " + fieldName + ";";
    }

    private boolean a(String oldType, String newType) {
        oldType = oldType == null ? "" : oldType.toLowerCase();
        newType = newType == null ? "" : newType.toLowerCase();
        String numberTypes = "int,double,bigdecimal";
        return numberTypes.indexOf(oldType) >= 0 && numberTypes.indexOf(newType) >= 0;
    }

    private String a(a cgfromcolumnMeta, a datacolumnMeta) {
        String dropSql = this.getDropColumnSql(datacolumnMeta.getColumnName());
        String tableName = cgfromcolumnMeta.getTableName();
        String alterSql = String.format("alter table %s", tableName);
        String addSql = alterSql + this.getAddColumnSql(cgfromcolumnMeta);
        return dropSql + addSql;
    }

    private String b(a cgfromcolumnMeta, a datacolumnMeta) {
        String nullable = cgfromcolumnMeta.getIsNullable();
        String oldNullable = datacolumnMeta.getIsNullable();
        nullable = nullable == null ? "Y" : nullable;
        oldNullable = oldNullable == null ? "Y" : oldNullable;
        if (!nullable.equals(oldNullable)) {
            String tableName = cgfromcolumnMeta.getTableName();
            String columnName = cgfromcolumnMeta.getColumnName();
            String alterSql = "ALTER table %s ALTER COLUMN %s %s not null;";
            if ("Y".equals(nullable)) {
                return String.format(alterSql, tableName, columnName, "drop");
            }

            if ("N".equals(nullable)) {
                return String.format(alterSql, tableName, columnName, "set");
            }
        }

        return "";
    }

    private String c(a cgfromcolumnMeta, a datacolumnMeta) throws org.jeecg.modules.online.config.exception.a {
        String result = "  ALTER  COLUMN   ";
        if ("string".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = result + cgfromcolumnMeta.getColumnName() + "  type character varying(" + cgfromcolumnMeta.getColumnSize() + ") ";
        } else if ("date".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            if (datacolumnMeta.getColunmType().toLowerCase().indexOf("date") >= 0) {
                result = result + cgfromcolumnMeta.getColumnName() + "  type date ";
            } else {
                result = this.a(cgfromcolumnMeta, datacolumnMeta);
            }
        } else if ("datetime".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            if (datacolumnMeta.getColunmType().toLowerCase().indexOf("date") >= 0) {
                result = result + cgfromcolumnMeta.getColumnName() + "  type timestamp ";
            } else {
                result = this.a(cgfromcolumnMeta, datacolumnMeta);
            }
        } else if ("int".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            if (this.a(cgfromcolumnMeta.getColunmType(), datacolumnMeta.getColunmType())) {
                result = result + cgfromcolumnMeta.getColumnName() + " type int4";
            } else {
                result = this.a(cgfromcolumnMeta, datacolumnMeta);
            }
        } else if ("double".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            if (this.a(cgfromcolumnMeta.getColunmType(), datacolumnMeta.getColunmType())) {
                result = result + cgfromcolumnMeta.getColumnName() + " type  numeric(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ") ";
            } else {
                result = this.a(cgfromcolumnMeta, datacolumnMeta);
            }
        } else if ("BigDecimal".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            if (this.a(cgfromcolumnMeta.getColunmType(), datacolumnMeta.getColunmType())) {
                result = result + cgfromcolumnMeta.getColumnName() + " type  decimal(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ") ";
            } else {
                result = this.a(cgfromcolumnMeta, datacolumnMeta);
            }
        } else if ("text".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = result + cgfromcolumnMeta.getColumnName() + " type text ";
        } else if ("blob".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            throw new org.jeecg.modules.online.config.exception.a("blob类型不可修改");
        }

        if (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault())) {
            result = result + ",  ALTER  COLUMN   " + cgfromcolumnMeta.getColumnName() + " set DEFAULT " + cgfromcolumnMeta.getFieldDefault() + " ";
        } else if (StringUtils.isNotEmpty(datacolumnMeta.getFieldDefault())) {
            result = result + ",  ALTER  COLUMN   " + cgfromcolumnMeta.getColumnName() + " DROP DEFAULT ";
        }

        if (!result.endsWith(";")) {
            result = result + ";";
        }

        String nullableSql = this.b(cgfromcolumnMeta, datacolumnMeta);
        result = result + nullableSql;
        return result;
    }

    private String d(a cgfromcolumnMeta, a datacolumnMeta) {
        String result = "";
        if (!cgfromcolumnMeta.a(datacolumnMeta)) {
            if ("string".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
                result = cgfromcolumnMeta.getColumnName();
                result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " SET DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " DROP DEFAULT");
            } else if (!"date".equalsIgnoreCase(cgfromcolumnMeta.getColunmType()) && !"datetime".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
                if ("int".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
                    result = cgfromcolumnMeta.getColumnName();
                    result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " SET DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " DROP DEFAULT");
                } else if ("double".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
                    result = cgfromcolumnMeta.getColumnName();
                    result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " SET DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " DROP DEFAULT");
                } else if ("bigdecimal".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
                    result = cgfromcolumnMeta.getColumnName();
                    result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " SET DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " DROP DEFAULT");
                } else if ("text".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
                    result = cgfromcolumnMeta.getColumnName();
                    result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " SET DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " DROP DEFAULT");
                }
            } else {
                result = cgfromcolumnMeta.getColumnName();
                result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " SET DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " DROP DEFAULT");
            }
        }

        return result;
    }

    private String a(a cgfromcolumnMeta) {
        String result = "";
        if ("string".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var10000 = cgfromcolumnMeta.getColumnName();
            result = var10000 + " character varying(" + cgfromcolumnMeta.getColumnSize() + ") ";
        } else if ("date".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " date ";
        } else if ("datetime".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " timestamp ";
        } else if ("int".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " int4";
        } else if ("double".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var4 = cgfromcolumnMeta.getColumnName();
            result = var4 + " numeric(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ") ";
        } else if ("bigdecimal".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var5 = cgfromcolumnMeta.getColumnName();
            result = var5 + " decimal(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ") ";
        } else if ("blob".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " bytea ";
        } else if ("text".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " text ";
        }

        result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " ");
        if ("N".equals(cgfromcolumnMeta.getIsNullable())) {
            result = result + " NOT NULL ";
        }

        return result;
    }

    private String b(a cgfromcolumnMeta) {
        String result = "";
        if ("string".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var10000 = cgfromcolumnMeta.getColumnName();
            result = var10000 + " character varying(" + cgfromcolumnMeta.getColumnSize() + ") ";
        } else if ("date".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " date ";
        } else if ("datetime".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " timestamp ";
        } else if ("int".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var3 = cgfromcolumnMeta.getColumnName();
            result = var3 + " int(" + cgfromcolumnMeta.getColumnSize() + ") ";
        } else if ("double".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            String var4 = cgfromcolumnMeta.getColumnName();
            result = var4 + " numeric(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ") ";
        }

        return result;
    }

    public String getCommentSql(a columnMeta) {
        String var10000 = columnMeta.getTableName();
        return "COMMENT ON COLUMN " + var10000 + "." + columnMeta.getColumnName() + " IS '" + columnMeta.getComment() + "'";
    }

    public String dropIndexs(String indexName, String tableName) {
        return "DROP INDEX " + indexName;
    }

    public String countIndex(String indexName, String tableName) {
        return "SELECT count(*) FROM pg_indexes WHERE indexname = '" + indexName + "' and tablename = '" + tableName + "'";
    }
}
