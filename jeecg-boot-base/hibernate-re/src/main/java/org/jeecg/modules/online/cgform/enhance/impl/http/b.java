//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.enhance.impl.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.jeecg.modules.online.cgform.enhance.impl.http.base.CgformEnhanceHttpInter;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("cgformEnhanceJavaListHttpImpl")
public class b implements CgformEnhanceHttpInter {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(b.class);

    public void execute(String tableName, List<Map<String, Object>> dataList, OnlCgformEnhanceJava enhance) {
        JSONObject params = new JSONObject();
        params.put("tableName", tableName);
        params.put("dataList", dataList);
        Object res = this.sendPost(params, enhance);
        if (res instanceof JSONArray newDataList) {
            for(int i = 0; i < dataList.size(); ++i) {
                Map record = (Map)dataList.get(i);
                Map newRecord = newDataList.getJSONObject(i);
                record.putAll(newRecord);
            }
        }

    }
}
