//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.enhance.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Generated;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.SysCategoryModel;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaListInter;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("cgformEnhanceExportDemo")
public class b implements CgformEnhanceJavaListInter {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(b.class);
    @Lazy
    @Autowired
    ISysBaseAPI sysBaseAPI;
    @Autowired
    IOnlCgformFieldService onlCgformFieldService;

    public void execute(String tableName, List<Map<String, Object>> data) throws BusinessException {
        List<SysCategoryModel> ls = this.sysBaseAPI.queryAllSysCategory();

        for(Map map : data) {
            String val = oConvertUtils.getString(map.get("fen_tree"));
            if (!oConvertUtils.isEmpty(val)) {
                List collect = (List)ls.stream().filter((item) -> item.getId().equals(val)).collect(Collectors.toList());
                if (collect != null && collect.size() != 0) {
                    map.put("fen_tree", ((SysCategoryModel)collect.get(0)).getName());
                }

                String sel_search = oConvertUtils.getString(map.get("sel_search"));
                if (!oConvertUtils.isEmpty(sel_search)) {
                    OnlCgformField onlCgformField = this.onlCgformFieldService.queryFormFieldByTableNameAndField(tableName, "sel_search");
                    if (onlCgformField != null && !oConvertUtils.isEmpty(onlCgformField.getDictTable())) {
                        List dictTableName = this.sysBaseAPI.queryTableDictByKeys(onlCgformField.getDictTable(), onlCgformField.getDictText(), onlCgformField.getDictField(), new String[]{sel_search});
                        if (dictTableName != null && dictTableName.size() > 0) {
                            map.put("sel_search", dictTableName.get(0));
                        }
                    }
                }
            }
        }

    }
}
