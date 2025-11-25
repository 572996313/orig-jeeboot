//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter.dira;

import java.util.List;
import java.util.Map;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.FieldCommentConverter;

public class a implements FieldCommentConverter {
    protected ISysBaseAPI a;
    protected String b;
    protected String c;
    protected String d;
    protected String e;

    public a() {
        this.a = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);
    }

    public a(String table, String code, String text) {
        this();
        this.c = table;
        this.d = code;
        this.e = text;
    }

    public String getField() {
        return this.b;
    }

    public void setField(String field) {
        this.b = field;
    }

    public String getTable() {
        return this.c;
    }

    public void setTable(String table) {
        this.c = table;
    }

    public String getCode() {
        return this.d;
    }

    public void setCode(String code) {
        this.d = code;
    }

    public String getText() {
        return this.e;
    }

    public void setText(String text) {
        this.e = text;
    }

    public String converterToVal(String txt) {
        if (oConvertUtils.isNotEmpty(txt)) {
            String filterSql = this.e + "= '" + txt + "'";
            String tableName = null;
            int index = this.c.indexOf("where");
            if (index > 0) {
                tableName = this.c.substring(0, index).trim();
                filterSql = filterSql + " and " + this.c.substring(index + 5);
            } else {
                tableName = this.c;
            }

            List dictList = this.a.queryFilterTableDictInfo(tableName, this.e, this.d, filterSql);
            if (dictList != null && dictList.size() > 0) {
                return ((DictModel)dictList.get(0)).getValue();
            }
        }

        return null;
    }

    public String converterToTxt(String val) {
        if (oConvertUtils.isNotEmpty(val)) {
            String filterSql = this.d + "= '" + val + "'";
            String tableName = null;
            int index = this.c.indexOf("where");
            if (index > 0) {
                tableName = this.c.substring(0, index).trim();
                filterSql = filterSql + " and " + this.c.substring(index + 5);
            } else {
                tableName = this.c;
            }

            List dictList = this.a.queryFilterTableDictInfo(tableName, this.e, this.d, filterSql);
            if (dictList != null && dictList.size() > 0) {
                return ((DictModel)dictList.get(0)).getText();
            }
        }

        return null;
    }

    public Map<String, String> getConfig() {
        return null;
    }
}
