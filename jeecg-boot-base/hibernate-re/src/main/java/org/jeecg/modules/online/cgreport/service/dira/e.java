//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.service.dira;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgreport.dirc.a;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.mapper.OnlCgreportItemMapper;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.springframework.stereotype.Service;

@Service("onlCgreportItemServiceImpl")
public class e extends ServiceImpl<OnlCgreportItemMapper, OnlCgreportItem> implements IOnlCgreportItemService {
    public List<Map<String, String>> getAutoListQueryInfo(String cgrheadId) {
        LambdaQueryWrapper<OnlCgreportItem> query = new LambdaQueryWrapper();
        query.eq(OnlCgreportItem::getCgrheadId, cgrheadId);
        query.eq(OnlCgreportItem::getIsSearch, 1);
        List<OnlCgreportItem> fieldList = this.list(query);
        List list = new ArrayList();
        int i = 0;

        for(OnlCgreportItem item : fieldList) {
            Map map = new HashMap(5);
            map.put("label", item.getFieldTxt());
            String dictCode = item.getDictCode();
            if (oConvertUtils.isNotEmpty(dictCode)) {
                if (a.b(dictCode)) {
                    map.put("view", "search");
                    map.put("fieldId", item.getId());
                } else {
                    map.put("view", "list");
                }
            } else {
                map.put("view", item.getFieldType().toLowerCase());
            }

            map.put("mode", oConvertUtils.isEmpty(item.getSearchMode()) ? "single" : item.getSearchMode());
            map.put("field", item.getFieldName());
            ++i;
            if (i > 2) {
                map.put("hidden", "1");
            }

            list.add(map);
        }

        return list;
    }
}
