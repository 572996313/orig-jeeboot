//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.service.dira;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.online.auth.entity.OnlAuthRelation;
import org.jeecg.modules.online.auth.mapper.OnlAuthRelationMapper;
import org.jeecg.modules.online.auth.service.IOnlAuthRelationService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service("onlAuthRelationServiceImpl")
public class c extends ServiceImpl<OnlAuthRelationMapper, OnlAuthRelation> implements IOnlAuthRelationService {
    public Result<?> saveRoleAuth(String roleId, String cgformId, int type, String authMode, List<String> authIds) {
        LambdaQueryWrapper<OnlAuthRelation> query = (new LambdaQueryWrapper<OnlAuthRelation>()).eq(OnlAuthRelation::getCgformId, cgformId).eq(OnlAuthRelation::getType, type).eq(OnlAuthRelation::getAuthMode, authMode).eq(OnlAuthRelation::getRoleId, roleId);
        this.baseMapper.delete(query);
        if (CollectionUtils.isEmpty(authIds)) {
            return Result.OK("保存成功");
        } else {
            List ls = new ArrayList();

            for(String authId : authIds) {
                OnlAuthRelation temp = new OnlAuthRelation();
                temp.setAuthId(authId);
                temp.setCgformId(cgformId);
                temp.setRoleId(roleId);
                temp.setType(type);
                temp.setAuthMode(authMode);
                ls.add(temp);
            }

            if (super.saveBatch(ls)) {
                if (2 == type) {
                    List names = ((OnlAuthRelationMapper)this.baseMapper).queryDisabledButtonNameById(authIds);
                    if (CollectionUtils.isEmpty(names)) {
                        return Result.OK("保存成功");
                    } else {
                        JSONObject json = new JSONObject();
                        json.put("disabledNames", names);
                        return Result.OK("保存成功", json);
                    }
                } else {
                    return Result.OK("保存成功");
                }
            } else {
                return Result.error("保存失败");
            }
        }
    }
}
