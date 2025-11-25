//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.dirc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class e {
    protected static Map<String, String> a = new HashMap(5);

    private static String a(String field, int flag) {
        String change = field;
        Iterator it = a.keySet().iterator();

        while(it.hasNext()) {
            String key = String.valueOf(it.next());
            String value = String.valueOf(a.get(key));
            if (flag == 1) {
                change = field.replaceAll(key, value);
            } else if (flag == 2) {
                change = field.replaceAll(value, key);
            }
        }

        return change;
    }

    static {
        a.put("class", "clazz");
    }
}
