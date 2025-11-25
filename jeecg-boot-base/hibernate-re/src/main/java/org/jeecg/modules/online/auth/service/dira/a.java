//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.service.dira;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecg.common.system.vo.SysPermissionDataRuleModel;
import org.jeecg.modules.online.auth.entity.OnlAuthData;
import org.jeecg.modules.online.auth.entity.OnlAuthRelation;
import org.jeecg.modules.online.auth.mapper.OnlAuthDataMapper;
import org.jeecg.modules.online.auth.mapper.OnlAuthRelationMapper;
import org.jeecg.modules.online.auth.service.IOnlAuthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("onlAuthDataServiceImpl")
public class a extends ServiceImpl<OnlAuthDataMapper, OnlAuthData> implements IOnlAuthDataService {
    @Autowired
    private OnlAuthRelationMapper onlAuthRelationMapper;

    public void deleteOne(String id) {
        this.removeById(id);
        LambdaQueryWrapper<OnlAuthRelation> query = new LambdaQueryWrapper();
        this.onlAuthRelationMapper.delete(query.eq(OnlAuthRelation::getAuthId, id));
    }

    public List<SysPermissionDataRuleModel> queryUserOnlineAuthData(String userId, String cgformId) {
        List<SysPermissionDataRuleModel> roleAuthList = this.baseMapper.queryRoleAuthData(userId, cgformId);
        List<SysPermissionDataRuleModel> depAuthList = this.baseMapper.queryDepartAuthData(userId, cgformId);
        List<SysPermissionDataRuleModel> userAuthList = this.baseMapper.queryUserAuthData(userId, cgformId);
        Map map = new HashMap(5);

        for(SysPermissionDataRuleModel temp : roleAuthList) {
            String id = temp.getId();
            if (map.get(id) == null) {
                map.put(id, temp);
            }
        }

        for(SysPermissionDataRuleModel temp : depAuthList) {
            String id = temp.getId();
            if (map.get(id) == null) {
                map.put(id, temp);
            }
        }

        for(SysPermissionDataRuleModel temp : userAuthList) {
            String id = temp.getId();
            if (map.get(id) == null) {
                map.put(id, temp);
            }
        }

        Collection ls = map.values();
        if (ls != null && ls.size() != 0) {
            return new ArrayList(ls);
        } else {
            return null;
        }
    }

    public void createAiTestAuthData(JSONObject json) {
        List list = new ArrayList();
        JSONArray array = json.getJSONArray("data");
        if (array != null && array.size() > 0) {
            for(int i = 0; i < array.size(); ++i) {
                JSONObject temp = array.getJSONObject(i);
                OnlAuthData obj = (OnlAuthData)JSONObject.toJavaObject(temp, OnlAuthData.class);
                list.add(obj);
            }
        }

        this.saveBatch(list);
    }
}
