//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.jeecg.common.util.MyClassLoader;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.dirb.b;
import org.jeecg.modules.online.cgform.converter.dirb.c;
import org.jeecg.modules.online.cgform.converter.dirb.d;
import org.jeecg.modules.online.cgform.converter.dirb.e;
import org.jeecg.modules.online.cgform.converter.dirb.f;
import org.jeecg.modules.online.cgform.converter.dirb.h;
import org.jeecg.modules.online.cgform.converter.dirb.i;
import org.jeecg.modules.online.cgform.converter.dirb.j;
import org.jeecg.modules.online.cgform.converter.dirb.k;
import org.jeecg.modules.online.cgform.converter.dirb.l;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class a {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(a.class);
    private static final String b = "list";
    private static final String c = "radio";
    private static final String d = "checkbox";
    private static final String e = "list_multi";
    private static final String f = "sel_search";
    private static final String g = "sel_tree";
    private static final String h = "cat_tree";
    private static final String i = "link_down";
    private static final String j = "sel_depart";
    private static final String k = "sel_user";
    private static final String l = "pca";
    private static final String m = "switch";
    private static final String n = "input";
    private static final String o = "date";

    public static FieldCommentConverter a(OnlCgformField field) {
        String showType = field.getFieldShowType();
        FieldCommentConverter obj = null;
        switch (showType) {
            case "list":
            case "radio":
                obj = new d(field);
                break;
            case "list_multi":
            case "checkbox":
                obj = new h(field);
                break;
            case "sel_search":
                obj = new e(field);
                break;
            case "sel_tree":
                obj = new k(field);
                break;
            case "cat_tree":
                obj = new org.jeecg.modules.online.cgform.converter.dirb.a(field);
                break;
            case "link_down":
                obj = new f(field);
                break;
            case "sel_depart":
                obj = new c(field);
                break;
            case "sel_user":
                obj = new l(field);
                break;
            case "pca":
                obj = new i(field);
                break;
            case "switch":
                obj = new j(field);
                break;
            case "input":
                String dictCode = field.getDictField();
                if (dictCode != null && !"".equals(dictCode)) {
                    obj = new d(field);
                } else {
                    obj = null;
                }
                break;
            case "date":
                obj = new b(field);
                break;
            default:
                obj = null;
        }

        return obj;
    }

    public static Map<String, FieldCommentConverter> a(List<OnlCgformField> fieldList) {
        Map map = new HashMap(5);

        for(OnlCgformField field : fieldList) {
            FieldCommentConverter converter = null;
            if (oConvertUtils.isNotEmpty(field.getConverter())) {
                converter = a(field.getConverter().trim());
            } else {
                converter = a(field);
            }

            if (converter != null) {
                map.put(field.getDbFieldName().toLowerCase(), converter);
            }
        }

        return map;
    }

    private static FieldCommentConverter a(String converter) {
        Object obj = null;
        if (converter.indexOf(".") > 0) {
            try {
                obj = MyClassLoader.getClassByScn(converter).newInstance();
            } catch (InstantiationException e) {
                a.error(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                a.error(e.getMessage(), e);
            }
        } else {
            obj = SpringContextUtils.getBean(converter);
        }

        if (obj != null && obj instanceof FieldCommentConverter bean) {
            return bean;
        } else {
            return null;
        }
    }
}
