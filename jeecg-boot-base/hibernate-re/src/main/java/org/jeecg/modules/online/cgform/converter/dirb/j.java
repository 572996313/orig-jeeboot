//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter.dirb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.online.cgform.converter.dira.b;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class j extends b {
    public j(OnlCgformField onlCgformField) {
        String extData = onlCgformField.getFieldExtendJson();
        String y = "Y";
        String n = "N";
        if (extData != null && !"".equals(extData)) {
            JSONArray array = null;

            try {
                array = JSONArray.parseArray(extData);
            } catch (Exception var8) {
                JSONObject jsonObject = JSONArray.parseObject(extData);
                if (jsonObject.containsKey("switchOptions")) {
                    array = jsonObject.getJSONArray("switchOptions");
                }
            }

            if (array != null && array.size() == 2) {
                y = array.get(0).toString();
                n = array.get(1).toString();
            }
        }

        List dictList = new ArrayList();
        DictModel yDict = new DictModel(y, "是");
        DictModel nDict = new DictModel(n, "否");
        dictList.add(yDict);
        dictList.add(nDict);
        this.b = dictList;
        this.a = onlCgformField.getDbFieldName();
    }
}
