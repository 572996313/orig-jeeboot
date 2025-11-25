//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter.dirb;

import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.dira.b;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class i extends b {
    ProvinceCityArea c;

    public i(OnlCgformField onlCgformField) {
        this.a = onlCgformField.getDbFieldName();
        this.c = (ProvinceCityArea)SpringContextUtils.getBean(ProvinceCityArea.class);
    }

    public String converterToVal(String txt) {
        return oConvertUtils.isEmpty(txt) ? null : this.c.getCode(txt);
    }

    public String converterToTxt(String val) {
        return oConvertUtils.isEmpty(val) ? null : this.c.getText(val);
    }
}
