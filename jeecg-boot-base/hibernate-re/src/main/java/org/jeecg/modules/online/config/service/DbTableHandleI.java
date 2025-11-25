//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.service;

import java.util.List;
import org.jeecg.modules.online.config.dirc.a;

public interface DbTableHandleI {
    String getAddColumnSql(a columnMeta);

    String getReNameFieldName(a columnMeta);

    String getUpdateColumnSql(a cgformcolumnMeta, a datacolumnMeta) throws org.jeecg.modules.online.config.exception.a;

    String getMatchClassTypeByDataType(String dataType, int digits);

    String dropTableSQL(String tableName);

    String getDropColumnSql(String fieldName);

    String getCommentSql(a columnMeta);

    String getSpecialHandle(a cgformcolumnMeta, a datacolumnMeta);

    String dropIndexs(String indexName, String tableName);

    String countIndex(String indexName, String tableName);

    default void handleUpdateMultiSql(a meta, a config, String tableName, List<String> sqlList) {
    }
}
