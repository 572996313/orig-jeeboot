//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.enhance.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.Generated;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("cgformEnhanceBeanDemo")
public class a implements CgformEnhanceJavaInter {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(a.class);

    public void execute(String tableName, JSONObject json) throws BusinessException {
        if (json.containsKey("phone")) {
            json.put("phone", "18611100000");
        }

    }
}
