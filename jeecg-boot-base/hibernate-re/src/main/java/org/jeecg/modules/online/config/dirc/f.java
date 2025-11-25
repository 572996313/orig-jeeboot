//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.dirc;

import java.util.Comparator;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class f implements Comparator<OnlCgformField> {
//    public int a(OnlCgformField o1, OnlCgformField o2) {
//        if (o1 != null && o1.getOrderNum() != null && o2 != null && o2.getOrderNum() != null) {
//            Integer thisVal = o1.getOrderNum();
//            Integer anotherVal = o2.getOrderNum();
//            return thisVal < anotherVal ? -1 : (thisVal.equals(anotherVal) ? 0 : 1);
//        } else {
//            return -1;
//        }
//    }

    @Override
    public int compare(OnlCgformField o1, OnlCgformField o2) {
        if (o1 != null && o1.getOrderNum() != null && o2 != null && o2.getOrderNum() != null) {
            Integer thisVal = o1.getOrderNum();
            Integer anotherVal = o2.getOrderNum();
            return thisVal < anotherVal ? -1 : (thisVal.equals(anotherVal) ? 0 : 1);
        } else {
            return -1;
        }
    }
}
