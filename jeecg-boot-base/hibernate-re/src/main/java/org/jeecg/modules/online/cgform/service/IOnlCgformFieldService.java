//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;
import org.jeecg.modules.online.cgform.dira.a;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.model.TreeModel;
import org.jeecg.modules.online.cgform.model.e;

public interface IOnlCgformFieldService extends IService<OnlCgformField> {
    Map<String, Object> queryAutolistPage(OnlCgformHead head, Map<String, Object> params, List<String> needList);

    Map<String, Object> queryAutoTreeNoPage(String tbname, String headId, Map<String, Object> params, List<String> needList, String pidField);

    void deleteAutoListMainAndSub(OnlCgformHead head, String ids);

    void deleteAutoListById(String tbname, String ids);

    void deleteAutoList(String tbname, String linkField, String linkValue);

    void saveFormData(String code, String tbname, JSONObject json, boolean isCrazy);

    void saveTreeFormData(String code, String tbname, JSONObject json, String hasChildField, String pidField);

    void saveFormData(List<OnlCgformField> fieldList, String tbname, JSONObject json);

    List<OnlCgformField> queryFormFields(String code, boolean isform);

    List<OnlCgformField> queryFormFieldsByTableName(String tableName);

    OnlCgformField queryFormFieldByTableNameAndField(String tableName, String fieldName);

    void editTreeFormData(String code, String tbname, JSONObject json, String hasChildField, String pidField);

    void editFormData(String code, String tbname, JSONObject json, boolean isCrazy);

    Map<String, Object> queryFormData(String code, String tbname, String id);

    Map<String, Object> queryFormData(List<OnlCgformField> fieldList, String tbname, String id);

    Map<String, Object> generateMockData(String tableName);

    List<Map<String, Object>> querySubFormData(List<OnlCgformField> fieldList, String tbname, String linkField, String value);

    List<Map<String, String>> getAutoListQueryInfo(String code);

    List<String> selectOnlineHideColumns(String tbname);

    List<OnlCgformField> queryAvailableFields(String cgFormId, String tbname, String taskId, boolean isList);

    List<String> queryDisabledFields(String tbname);

    List<String> queryDisabledFields(String tbname, String taskId);

    List<OnlCgformField> queryAvailableFields(String tbname, boolean isList, List<OnlCgformField> List, List<String> needList);

    List<OnlCgformField> queryAvailableFields(String cgformId, String tbname, boolean isList, List<OnlCgformField> List, List<String> needList);

    void executeInsertSQL(Map<String, Object> params);

    void executeUpdatetSQL(Map<String, Object> params);

    List<TreeModel> queryDataListByLinkDown(a linkDown);

    void updateTreeNodeNoChild(String tableName, String filed, String id);

    String queryTreeChildIds(OnlCgformHead head, String ids);

    String queryTreePids(OnlCgformHead head, String ids);

    String queryForeignKey(String cgFormId, String mainTable);

    List<Map<String, Object>> queryListBySql(String tableName, String fields, String pidField, String metaPid, String inIds);

    void clearCacheOnlineConfig();

    e getQueryInfo(OnlCgformHead head, Map<String, Object> params, List<String> needList);

    void addOnlineInsertDataLog(String tableName, String dataId);

    void addOnlineUpdateDataLog(String tableName, String dataId, List<OnlCgformField> fieldList, JSONObject json);

    List<Map<String, Object>> queryLinkTableDictList(String table, String textString, String code);

    void handleLinkTableDictData(String headId, List<Map<String, Object>> dataList);

    Integer queryCountBySql(String tableName, String pidField, String metaPid);
}
