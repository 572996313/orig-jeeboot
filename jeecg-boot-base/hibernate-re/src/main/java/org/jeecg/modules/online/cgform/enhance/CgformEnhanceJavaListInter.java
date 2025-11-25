//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.enhance;

import java.util.List;
import java.util.Map;
import org.jeecg.modules.online.config.exception.BusinessException;

public interface CgformEnhanceJavaListInter {
    void execute(String tableName, List<Map<String, Object>> dataList) throws BusinessException;
}
