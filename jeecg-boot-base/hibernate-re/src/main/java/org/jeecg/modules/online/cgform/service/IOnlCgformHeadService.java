//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceSql;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.enums.EnhanceDataEnum;
import org.jeecg.modules.online.cgform.model.a;
import org.jeecg.modules.online.cgform.model.d;
import org.jeecg.modules.online.config.exception.BusinessException;

public interface IOnlCgformHeadService extends IService<OnlCgformHead> {
    Result<?> addAll(a model);

    Result<?> editAll(a model);

    void doDbSynch(String code, String synMethod) throws HibernateException, IOException, TemplateException, SQLException, org.jeecg.modules.online.config.exception.a;

    void deleteRecordAndTable(String id) throws org.jeecg.modules.online.config.exception.a, SQLException;

    void deleteRecord(String id) throws org.jeecg.modules.online.config.exception.a, SQLException;

    List<Map<String, Object>> queryListData(String sql);

    OnlCgformEnhanceJs queryEnhance(String code, String type);

    void saveEnhance(OnlCgformEnhanceJs onlCgformEnhanceJs);

    void editEnhance(OnlCgformEnhanceJs onlCgformEnhanceJs);

    OnlCgformEnhanceSql queryEnhanceSql(String formId, String buttonCode);

    OnlCgformEnhanceJava queryEnhanceJava(OnlCgformEnhanceJava onlCgformEnhanceJava);

    List<OnlCgformButton> queryButtonList(String code, boolean isListButton);

    List<OnlCgformButton> queryButtonList(String code);

    List<String> queryOnlinetables();

    void saveDbTable2Online(String tbname) throws Exception;

    boolean checkTableExist(String tbname);

    JSONObject queryFormItem(OnlCgformHead head, String username);

    String saveManyFormData(String code, JSONObject json, String xAccessToken) throws org.jeecg.modules.online.config.exception.a, BusinessException;

    Map<String, Object> queryManyFormData(String code, String id) throws org.jeecg.modules.online.config.exception.a;

    List<Map<String, Object>> queryManySubFormData(String table, String mainId) throws org.jeecg.modules.online.config.exception.a;

    Map<String, Object> querySubFormData(String table, String mainId) throws org.jeecg.modules.online.config.exception.a;

    String editManyFormData(String code, JSONObject json) throws org.jeecg.modules.online.config.exception.a, BusinessException;

    void executeEnhanceJava(String buttonCode, String eventType, OnlCgformHead head, JSONObject json) throws BusinessException;

    void executeEnhanceExport(OnlCgformHead head, List<Map<String, Object>> dataList) throws BusinessException;

    EnhanceDataEnum executeEnhanceImport(OnlCgformHead head, JSONObject json) throws BusinessException;

    void executeEnhanceList(OnlCgformHead head, String buttonCode, List<Map<String, Object>> dataList) throws BusinessException;

    void executeEnhanceSql(String buttonCode, String formId, JSONObject json);

    void executeCustomerButton(String buttonCode, String formId, String dataId) throws BusinessException;

    List<OnlCgformButton> queryValidButtonList(String headId);

    OnlCgformEnhanceJs queryEnhanceJs(String formId, String cgJsType);

    void deleteOneTableInfo(String formId, String dataId) throws BusinessException;

    List<String> generateCode(d model) throws Exception;

    List<String> generateOneToMany(d model) throws Exception;

    void addCrazyFormData(String tbname, JSONObject json) throws org.jeecg.modules.online.config.exception.a, UnsupportedEncodingException;

    void editCrazyFormData(String tbname, JSONObject json) throws org.jeecg.modules.online.config.exception.a, UnsupportedEncodingException;

    Integer getMaxCopyVersion(String physicId);

    void copyOnlineTableConfig(OnlCgformHead physicTable) throws Exception;

    void initCopyState(List<OnlCgformHead> headList);

    void deleteBatch(String ids, String flag);

    void updateParentNode(OnlCgformHead head, String dataId);

    String deleteDataByCode(String cgformCode, String dataIds);

    JSONObject queryAllDataByTableNameForDesform(String tableName, String dataIds) throws org.jeecg.modules.online.config.exception.a;

    OnlCgformHead copyOnlineTable(String id, String tableName);

    OnlCgformHead getTable(String code) throws org.jeecg.modules.online.config.exception.a;
}
