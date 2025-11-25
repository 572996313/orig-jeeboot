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

public class d extends b {
    public d(OnlCgformField onlCgformField) {
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
}
