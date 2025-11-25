//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter;

import java.util.Map;

public interface FieldCommentConverter {
    String converterToVal(String txt);

    String converterToTxt(String val);

    Map<String, String> getConfig();
}
