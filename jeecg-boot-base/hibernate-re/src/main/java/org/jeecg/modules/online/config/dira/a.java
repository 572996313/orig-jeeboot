//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.dira;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public final class a extends Dialect {
    public a() {
        this.registerColumnType(0, "null");
        this.registerColumnType(-7, "integer");
        this.registerColumnType(-6, "integer");
        this.registerColumnType(5, "integer");
        this.registerColumnType(4, "integer");
        this.registerColumnType(-5, "integer");
        this.registerColumnType(16, "integer");
        this.registerColumnType(7, "real");
        this.registerColumnType(8, "real");
        this.registerColumnType(6, "real");
        this.registerColumnType(2, "numeric");
        this.registerColumnType(3, "numeric");
        this.registerColumnType(1, "text");
        this.registerColumnType(12, "text");
        this.registerColumnType(2005, "text");
        this.registerColumnType(-1, "text");
        this.registerColumnType(2004, "blob");
        this.registerColumnType(2005, "clob");
        this.registerColumnType(91, "date");
        this.registerColumnType(93, "datetime");
        this.registerFunction("concat", new VarArgsSQLFunction(StandardBasicTypes.STRING, "", "||", ""));
        this.registerFunction("mod", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "?1 % ?2"));
        this.registerFunction("substr", new StandardSQLFunction("substr", StandardBasicTypes.STRING));
        this.registerFunction("substring", new StandardSQLFunction("substr", StandardBasicTypes.STRING));
    }

    public boolean a() {
        return true;
    }

    public boolean b() {
        return false;
    }

    public String getIdentityColumnString() {
        return "integer";
    }

    public String getIdentitySelectString() {
        return "select last_insert_rowid()";
    }

    public boolean supportsLimit() {
        return true;
    }

    protected String getLimitString(String query, boolean hasOffset) {
        return (new StringBuffer(query.length() + 20)).append(query).append(hasOffset ? " limit ? offset ?" : " limit ?").toString();
    }

    public boolean c() {
        return true;
    }

    public String getCreateTemporaryTableString() {
        return "create temporary table if not exists";
    }

    public boolean d() {
        return false;
    }

    public boolean supportsCurrentTimestampSelection() {
        return true;
    }

    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }

    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }

    public boolean supportsUnionAll() {
        return true;
    }

    public boolean hasAlterTable() {
        return false;
    }

    public boolean dropConstraints() {
        return false;
    }

    public String getAddColumnString() {
        return "add column";
    }

    public String getForUpdateString() {
        return "";
    }

    public boolean supportsOuterJoinForUpdate() {
        return false;
    }

    public String getDropForeignKeyString() {
        throw new UnsupportedOperationException("No drop foreign key syntax supported by SQLiteDialect");
    }

    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable, String[] primaryKey, boolean referencesPrimaryKey) {
        throw new UnsupportedOperationException("No add foreign key syntax supported by SQLiteDialect");
    }

    public String getAddPrimaryKeyConstraintString(String constraintName) {
        throw new UnsupportedOperationException("No add primary key syntax supported by SQLiteDialect");
    }

    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    public boolean supportsCascadeDelete() {
        return false;
    }
}
