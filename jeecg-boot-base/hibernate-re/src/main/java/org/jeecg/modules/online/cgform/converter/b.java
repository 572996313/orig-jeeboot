//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class b {
    public static final int a = 2;
    public static final int b = 1;

    public static void a(int flag, List<Map<String, Object>> dataList, List<OnlCgformField> fieldList) {
        Map converterMap = org.jeecg.modules.online.cgform.converter.a.a(fieldList);

        for(Map map : dataList) {
            Iterator it = map.entrySet().iterator();
            Map<String,Object> otherValue = new HashMap(5);

            while(it.hasNext()) {
                Map.Entry entry = (Map.Entry)it.next();
                Object temp = entry.getValue();
                if (temp != null) {
                    String key = (String)entry.getKey();
                    FieldCommentConverter converter = (FieldCommentConverter)converterMap.get(key.toLowerCase());
                    if (converter != null) {
                        String tempString = temp.toString();
                        String real = flag == 1 ? converter.converterToTxt(tempString) : converter.converterToVal(tempString);
                        if (real == null) {
                            real = tempString;
                        }

                        a(converter, map, flag);
                        a(converter, otherValue, tempString);
                        map.put(key, real);
                    }
                }
            }

            for(String key : otherValue.keySet()) {
                map.put(key, otherValue.get(key));
            }
        }

    }

    private static void a(FieldCommentConverter converter, Map<String, Object> data, int flag) {
        Map special = converter.getConfig();
        if (special != null) {
            String linkField = (String)special.get("linkField");
            if (oConvertUtils.isNotEmpty(linkField)) {
                for(String lf : linkField.split(",")) {
                    Object temp = data.get(lf);
                    if (temp != null) {
                        String tempString = temp.toString();
                        String real = flag == 1 ? converter.converterToTxt(tempString) : converter.converterToVal(tempString);
                        data.put(lf, real);
                    }
                }
            }
        }

    }

    private static void a(FieldCommentConverter converter, Map<String, Object> otherValue, String currValue) {
        Map special = converter.getConfig();
        if (special != null) {
            String treeText = (String)special.get("treeText");
            if (oConvertUtils.isNotEmpty(treeText)) {
                otherValue.put(treeText, currValue);
            }
        }

    }
}
