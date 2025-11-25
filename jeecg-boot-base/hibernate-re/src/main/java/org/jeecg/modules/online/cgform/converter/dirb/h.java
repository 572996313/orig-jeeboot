//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter.dirb;

import java.util.ArrayList;
import java.util.List;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.dira.b;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class h extends b {
    public h(OnlCgformField onlCgformField) {
        ISysBaseAPI sysBaseAPI = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);
        String table = onlCgformField.getDictTable();
        String txt = onlCgformField.getDictText();
        String code = onlCgformField.getDictField();
        List dictList = new ArrayList();
        if (oConvertUtils.isNotEmpty(table)) {
            dictList = sysBaseAPI.queryTableDictItemsByCode(table, txt, code);
        } else if (oConvertUtils.isNotEmpty(code)) {
            dictList = sysBaseAPI.queryDictItemsByCode(code);
        }

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
