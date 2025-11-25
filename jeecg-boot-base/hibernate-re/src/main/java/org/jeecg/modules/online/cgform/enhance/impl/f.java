//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.enhance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Generated;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaListInter;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("cgformEnhanceQueryDemo")
public class f implements CgformEnhanceJavaListInter {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(f.class);

    public void execute(String tableName, List<Map<String, Object>> data) throws BusinessException {
        List<a> dict = this.a();
        if (data != null) {
            for(Map map : data) {
                Object db = map.get("province");
                if (db != null) {
//                    String text = (String)dict.stream()
//                            .filter((p) -> db.toString().equals(p.a())).map(a::getB).findAny().orElse("");
//                    map.put("province", text);
                    map.put("province", "看不懂  先注释");

                }
            }

        }
    }

    private List<a> a() {
        List dict = new ArrayList();
        dict.add(new a("bj", "北京"));
        dict.add(new a("sd", "山东"));
        dict.add(new a("ah", "安徽"));
        return dict;
    }

    @Data
    static class a {
        String a;
        String b;

        public a(String value, String text) {
            this.a = value;
            this.b = text;
        }

    }
}
