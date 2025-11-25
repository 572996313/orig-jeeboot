//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dird;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecgframework.poi.handler.impl.ExcelDataHandlerDefaultImpl;
import org.jeecgframework.poi.util.PoiPublicUtil;

public class c extends ExcelDataHandlerDefaultImpl {
    Map<String, OnlCgformField> a;
    ISysBaseAPI b;
    String c;
    String d;
    String e;

    public c(List<OnlCgformField> lists, String uploadBasePath, String uploadType) {
        this.a = this.a(lists);
        this.c = uploadBasePath;
        this.d = "online";
        this.e = uploadType;
        this.b = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);
    }

    private Map<String, OnlCgformField> a(List<OnlCgformField> lists) {
        Map maps = new HashMap();

        for(OnlCgformField cgFormFieldEntity : lists) {
            maps.put(cgFormFieldEntity.getDbFieldTxt(), cgFormFieldEntity);
        }

        return maps;
    }

    public void setMapValue(Map<String, Object> map, String originKey, Object value) {
        String realKey = this.a(originKey);
        if (value instanceof Double) {
            map.put(realKey, PoiPublicUtil.doubleToString((Double)value));
        } else if (value instanceof byte[]) {
            byte[] arr = (byte[])value;
            String path = org.jeecg.modules.online.cgform.dird.d.a(arr, this.c, this.d, this.e);
            if (path != null) {
                map.put(realKey, path);
            }
        } else {
            map.put(realKey, value == null ? "" : value.toString());
        }

    }

    private String a(String originKey) {
        if (this.a.containsKey(originKey)) {
            Object var10000 = this.a.get(originKey);
            return "$mainTable$" + ((OnlCgformField)var10000).getDbFieldName();
        } else {
            return "$subTable$" + originKey;
        }
    }
}
