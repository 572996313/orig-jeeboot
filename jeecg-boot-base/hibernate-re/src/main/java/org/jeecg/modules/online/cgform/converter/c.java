//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter;

import java.util.Map;
import org.springframework.stereotype.Component;

@Component("customDemoConverter")
public class c implements FieldCommentConverter {
    public String converterToVal(String txt) {
        return txt != null && "管理员1".equals(txt) ? "admin" : txt;
    }

    public String converterToTxt(String val) {
        if (val != null) {
            if ("admin".equals(val)) {
                return "管理员1";
            }

            if ("scott".equals(val)) {
                return "管理员2";
            }
        }

        return val;
    }

    public Map<String, String> getConfig() {
        return null;
    }
}
