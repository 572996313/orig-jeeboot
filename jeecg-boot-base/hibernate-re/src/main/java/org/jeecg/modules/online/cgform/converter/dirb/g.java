//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter.dirb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.online.cgform.converter.dira.b;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class g extends b {
    @Generated
    private static final Logger d = LoggerFactory.getLogger(g.class);
    protected IOnlCgformFieldService c;

    public g(OnlCgformField onlCgformField) {
        String table = onlCgformField.getDictTable();
        String text = onlCgformField.getDictText();
        String code = onlCgformField.getDictField();
        List list = new ArrayList();

        try {
            String fieldName = text.split(",")[0];
            this.c = (IOnlCgformFieldService)SpringContextUtils.getBean(IOnlCgformFieldService.class);
            List<Map<String, Object>> dataList = this.c.queryLinkTableDictList(table, text, code);
            if (dataList != null && dataList.size() > 0) {
                for(Map map : dataList) {
                    String dictText = org.jeecg.modules.online.cgform.dird.d.a(map, fieldName);
                    String dictValue = org.jeecg.modules.online.cgform.dird.d.a(map, code);
                    list.add(new DictModel(dictValue, dictText));
                }
            }
        } catch (Exception e) {
            d.error("关联记录组件 导入导出数据翻译失败", e.getMessage());
        }

        this.b = list;
    }
}
