//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.enhance.impl.http;

import com.alibaba.fastjson.JSONObject;
import lombok.Generated;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.enhance.impl.http.base.CgformEnhanceHttpInter;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("cgformEnhanceJavaHttpImpl")
public class a implements CgformEnhanceHttpInter {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(a.class);

    public void execute(String tableName, JSONObject record, OnlCgformEnhanceJava enhance) {
        JSONObject params = new JSONObject();
        params.put("tableName", tableName);
        params.put("record", record);
        Object res = this.sendPost(params, enhance);
        Integer code = null;
        if (res != null) {
            code = oConvertUtils.getInt(res);
            if (code == null && res instanceof JSONObject) {
                JSONObject json = (JSONObject)res;
                code = oConvertUtils.getInt(json.get("code"));
                JSONObject newRecord = json.getJSONObject("record");
                if (newRecord != null) {
                    record.putAll(newRecord);
                }
            }
        }

    }
}
