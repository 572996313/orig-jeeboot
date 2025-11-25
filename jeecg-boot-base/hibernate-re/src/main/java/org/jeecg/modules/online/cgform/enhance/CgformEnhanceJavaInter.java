//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.enhance;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import org.jeecg.modules.online.config.exception.BusinessException;

public interface CgformEnhanceJavaInter {
    void execute(String tableName, JSONObject json) throws BusinessException;

    /** @deprecated */
    @Deprecated
    default void execute(String tableName, Map<String, Object> map) throws BusinessException {
    }
}
