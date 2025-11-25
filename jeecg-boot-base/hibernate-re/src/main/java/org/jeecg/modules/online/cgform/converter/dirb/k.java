//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter.dirb;

import org.jeecg.modules.online.cgform.converter.dira.a;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class k extends a {
    public k(OnlCgformField onlCgformField) {
        String dictText = onlCgformField.getDictText();
        String[] arr = dictText.split(",");
        this.setTable(onlCgformField.getDictTable());
        this.setCode(arr[0]);
        this.setText(arr[2]);
    }
}
