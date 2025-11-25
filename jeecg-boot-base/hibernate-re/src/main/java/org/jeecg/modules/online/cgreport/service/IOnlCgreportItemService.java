//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;

public interface IOnlCgreportItemService extends IService<OnlCgreportItem> {
    List<Map<String, String>> getAutoListQueryInfo(String cgrheadId);
}
