//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service.impl;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.jeecg.common.online.api.IOnlineBaseExtApi;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.online.cgform.dird.d;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportAPIService;
import org.jeecg.modules.online.config.exception.a;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("onlineBaseExtApiImpl")
public class OnlineBaseExtApiImpl implements IOnlineBaseExtApi {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(OnlineBaseExtApiImpl.class);
    @Autowired
    private IOnlCgformHeadService onlCgformHeadService;
    @Autowired
    IOnlCgreportAPIService onlCgreportAPIService;

    public String cgformPostCrazyForm(String tableName, JSONObject jsonObject) throws Exception {
        String uuid = d.a();
        jsonObject.put("id", uuid);
        this.onlCgformHeadService.addCrazyFormData(tableName, jsonObject);
        return uuid;
    }

    public String cgformPutCrazyForm(String tableName, JSONObject jsonObject) throws Exception {
        jsonObject.remove("create_by");
        jsonObject.remove("create_time");
        jsonObject.remove("update_by");
        jsonObject.remove("update_time");
        this.onlCgformHeadService.editCrazyFormData(tableName, jsonObject);
        return jsonObject.getString("id");
    }

    public String cgformDeleteDataByCode(String cgformCode, String dataIds) {
        return this.onlCgformHeadService.deleteDataByCode(cgformCode, dataIds);
    }

    public JSONObject cgformQueryAllDataByTableName(String tableName, String dataIds) {
        try {
            return this.onlCgformHeadService.queryAllDataByTableNameForDesform(tableName, dataIds);
        } catch (a e) {
            log.error("查询主子表数据失败：", e);
            return null;
        }
    }

    public Map<String, Object> cgreportGetData(String code, String forceKey, String dataList) {
        Map params = new HashMap(5);
        params.put(forceKey, dataList);
        params.put("getAll", true);
        return this.onlCgreportAPIService.getDataByCode(code, params);
    }

    public List<DictModel> cgreportGetDataPackage(String code, String dictText, String dictCode, String dataList) {
        String forceKey = "force_" + dictCode;
        Map<String, Object> result = this.cgreportGetData(code, forceKey, dataList);
        List<Map<String, Object>> records = (List)result.get("records");
        List dictModelList = new ArrayList();

        for(Map record : records) {
            String value = (String)record.get(dictCode);
            String text = (String)record.get(dictText);
            dictModelList.add(new DictModel(value, text));
        }

        return dictModelList;
    }
}
