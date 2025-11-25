//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.List;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.mapper.OnlCgformFieldMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformHeadMapper;
import org.jeecg.modules.online.cgform.service.IOnlineBaseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("onlineBaseAPI")
public class g implements IOnlineBaseAPI {
    @Autowired
    private OnlCgformHeadMapper onlCgformHeadMapper;
    @Autowired
    private OnlCgformFieldMapper onlCgformFieldMapper;

    public String getOnlineErpCode(String code, String tableType) {
        if ("3".equals(tableType)) {
            String tableId = code.substring(1);
            OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadMapper.selectById(tableId);
            if (head != null && head.getTableType() == 3) {
                LambdaQueryWrapper queryFields = (LambdaQueryWrapper)(new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, tableId);
                List<OnlCgformField> ls = this.onlCgformFieldMapper.selectList(queryFields);
                if (ls != null && ls.size() > 0) {
                    String mainTableName = null;

                    for(OnlCgformField temp : ls) {
                        if (oConvertUtils.isNotEmpty(temp.getMainTable())) {
                            mainTableName = temp.getMainTable();
                            break;
                        }
                    }

                    LambdaQueryWrapper queryTable = (new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, mainTableName);
                    OnlCgformHead mainTb = this.onlCgformHeadMapper.selectOne(queryTable);
                    if (mainTb != null) {
                        code = "/" + mainTb.getId();
                    }
                }
            }
        }

        return code;
    }
}
