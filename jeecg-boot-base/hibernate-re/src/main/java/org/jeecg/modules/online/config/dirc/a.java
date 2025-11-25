//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.dirc;

import com.baomidou.mybatisplus.annotation.DbType;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.util.dynamic.db.DbTypeUtils;

public class a {
    private String a;
    private String b;
    private String c;
    private int d;
    private String e;
    private String f;
    private String g;
    private int h;
    private String i;
    private String j;
    private String k;
    private String l;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof a)) {
            return false;
        } else {
            a meta = (a)obj;
            if (!this.e.contains("date") && !this.e.contains("blob") && !this.e.contains("text")) {
                return this.e.equals(meta.getColunmType()) && this.i.equals(meta.i) && this.d == meta.getColumnSize() && this.a(this.f, meta.getComment()) && this.a(this.g, meta.getFieldDefault());
            } else {
                return this.c.equals(meta.getColumnName()) && this.i.equals(meta.i) && this.a(this.f, meta.getComment()) && this.a(this.g, meta.getFieldDefault());
            }
        }
    }

    public boolean a(DbType dbType, a meta) {
        String configType = meta.getColunmType();
        if (DbTypeUtils.dbTypeIf(dbType, new DbType[]{DbType.ORACLE, DbType.ORACLE_12C})) {
            if ("datetime".equalsIgnoreCase(configType) && "date".equalsIgnoreCase(this.e)) {
                return true;
            }
        } else if (DbTypeUtils.dbTypeIsSqlServer(dbType) && "date".equalsIgnoreCase(configType) && "datetime".equalsIgnoreCase(this.e)) {
            return true;
        }

        return this.e.equalsIgnoreCase(configType);
    }

    public boolean a(Object obj, DbType dbType) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof a)) {
            return false;
        } else {
            a meta = (a)obj;
            if (!this.a(dbType, meta)) {
                return false;
            } else if (DbTypeUtils.dbTypeIsSqlServer(dbType)) {
                if (!this.e.contains("date") && !this.e.contains("blob") && !this.e.contains("text")) {
                    return this.e.equals(meta.getColunmType()) && this.i.equals(meta.i) && this.d == meta.getColumnSize() && this.h == meta.getDecimalDigits() && this.a(this.g, meta.getFieldDefault());
                } else {
                    return this.c.equals(meta.getColumnName()) && this.i.equals(meta.i);
                }
            } else if (DbTypeUtils.dbTypeIsPostgre(dbType)) {
                if (!this.e.contains("date") && !this.e.contains("blob") && !this.e.contains("text")) {
                    return this.e.equals(meta.getColunmType()) && this.i.equals(meta.i) && this.d == meta.getColumnSize() && this.h == meta.getDecimalDigits() && this.a(this.g, meta.getFieldDefault());
                } else {
                    return this.c.equals(meta.getColumnName()) && this.i.equals(meta.i);
                }
            } else if (DbTypeUtils.dbTypeIsOracle(dbType)) {
                if (!this.e.contains("date") && !this.e.contains("blob") && !this.e.contains("text")) {
                    return this.e.equals(meta.getColunmType()) && this.i.equals(meta.i) && this.d == meta.getColumnSize() && this.h == meta.getDecimalDigits() && this.a(this.g, meta.getFieldDefault());
                } else {
                    return this.a(dbType, meta) && this.c.equals(meta.getColumnName()) && this.i.equals(meta.i);
                }
            } else if (!this.e.contains("date") && !this.e.contains("blob") && !this.e.contains("text")) {
                return this.e.equals(meta.getColunmType()) && this.i.equals(meta.i) && this.d == meta.getColumnSize() && this.h == meta.getDecimalDigits() && this.a(this.f, meta.getComment()) && this.a(this.g, meta.getFieldDefault());
            } else {
                return this.a(dbType, meta) && this.c.equals(meta.getColumnName()) && this.i.equals(meta.i) && this.a(this.f, meta.getComment()) && this.a(this.g, meta.getFieldDefault());
            }
        }
    }

    public boolean a(a meta) {
        return meta == this ? true : this.a(this.f, meta.getComment());
    }

    public boolean b(a meta) {
        return meta == this ? true : this.a(this.f, meta.getComment());
    }

    private boolean a(String newS, String oldS) {
        boolean booN = StringUtils.isNotEmpty(newS);
        boolean booO = StringUtils.isNotEmpty(oldS);
        if ("".equals(oldS)) {
            if (!booN) {
                return true;
            } else {
                return newS.toLowerCase().toString().indexOf("null") >= 0;
            }
        } else if (booN != booO) {
            return false;
        } else {
            return booN ? newS.equals(oldS) : true;
        }
    }

    public String getColumnName() {
        return this.c;
    }

    public int getColumnSize() {
        return this.d;
    }

    public String getColunmType() {
        return this.e;
    }

    public String getComment() {
        return this.f;
    }

    public int getDecimalDigits() {
        return this.h;
    }

    public String getIsNullable() {
        return this.i;
    }

    public String getOldColumnName() {
        return this.k;
    }

    public int hashCode() {
        return this.d + this.e.hashCode() * this.c.hashCode();
    }

    public void setColumnName(String columnName) {
        this.c = columnName;
    }

    public void setColumnSize(int columnSize) {
        this.d = columnSize;
    }

    public void setColunmType(String colunmType) {
        this.e = colunmType;
    }

    public void setComment(String comment) {
        this.f = comment;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.h = decimalDigits;
    }

    public void setIsNullable(String isNullable) {
        this.i = isNullable;
    }

    public void setOldColumnName(String oldColumnName) {
        this.k = oldColumnName;
    }

    public String toString() {
        return this.c + "," + this.e + "," + this.i + "," + this.d;
    }

    public String getColumnId() {
        return this.b;
    }

    public void setColumnId(String columnId) {
        this.b = columnId;
    }

    public String getTableName() {
        return this.a;
    }

    public void setTableName(String tableName) {
        this.a = tableName;
    }

    public String getFieldDefault() {
        return this.g;
    }

    public void setFieldDefault(String fieldDefault) {
        this.g = fieldDefault;
    }

    public String getPkType() {
        return this.j;
    }

    public void setPkType(String pkType) {
        this.j = pkType;
    }

    public String getRealDbType() {
        return this.l;
    }

    public void setRealDbType(String realDbType) {
        this.l = realDbType;
    }
}
