//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter.dirb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.dira.b;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class c extends b {
    public c(OnlCgformField onlCgformField) {
        ISysBaseAPI sysBaseAPI = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);
        String table = "SYS_DEPART";
        String txt = "DEPART_NAME";
        String code = "ID";
        String jsonStr = onlCgformField.getFieldExtendJson();
        if (oConvertUtils.isNotEmpty(jsonStr)) {
            JSONObject json = JSON.parseObject(jsonStr);
            if (json.containsKey("store")) {
                String camelCode = json.getString("store");
                code = oConvertUtils.camelToUnderline(camelCode);
            }

            if (json.containsKey("text")) {
                String camelTxt = json.getString("text");
                txt = oConvertUtils.camelToUnderline(camelTxt);
            }
        }

        List dictList = sysBaseAPI.queryTableDictItemsByCode(table, txt, code);
        this.b = dictList;
        this.a = onlCgformField.getDbFieldName();
    }

    public String converterToVal(String txt) {
        if (oConvertUtils.isEmpty(txt)) {
            return null;
        } else {
            List res = new ArrayList();

            for(String temp : txt.split(",")) {
                String switchvalue = super.converterToVal(temp);
                if (switchvalue != null) {
                    res.add(switchvalue);
                }
            }

            return String.join(",", res);
        }
    }

    public String converterToTxt(String val) {
        if (oConvertUtils.isEmpty(val)) {
            return null;
        } else {
            List res = new ArrayList();

            for(String temp : val.split(",")) {
                String switchTxt = super.converterToTxt(temp);
                if (switchTxt != null) {
                    res.add(switchTxt);
                }
            }

            return String.join(",", res);
        }
    }
}
