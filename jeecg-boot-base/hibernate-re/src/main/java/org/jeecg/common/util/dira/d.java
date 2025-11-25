//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.common.util.dira;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class d {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(d.class);

    public static JSONObject a(c descrip, List<b> propertyList) {
        JSONObject obj = new JSONObject();
        obj.put("$schema", descrip.get$schema());
        obj.put("type", descrip.getType());
        obj.put("title", descrip.getTitle());
        List requiredArr = descrip.getRequired();
        obj.put("required", requiredArr);
        JSONObject properties = new JSONObject();

        for(b commonProperty : propertyList) {
            Map map = commonProperty.getPropertyJson();
            properties.put(map.get("key").toString(), map.get("prop"));
        }

        obj.put("properties", properties);
        return obj;
    }

    public static JSONObject a(String title, List<String> requiredArr, List<b> propertyList) {
        JSONObject obj = new JSONObject();
        obj.put("type", "object");
        obj.put("view", "tab");
        obj.put("title", title);
        if (requiredArr == null) {
            requiredArr = new ArrayList();
        }

        obj.put("required", requiredArr);
        JSONObject properties = new JSONObject();

        for(b commonProperty : propertyList) {
            Map map = commonProperty.getPropertyJson();
            properties.put(map.get("key").toString(), map.get("prop"));
        }

        obj.put("properties", properties);
        return obj;
    }
}
