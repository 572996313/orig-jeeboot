//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import org.jeecg.common.system.vo.SysPermissionDataRuleModel;
import org.jeecg.modules.online.auth.entity.OnlAuthData;

public interface IOnlAuthDataService extends IService<OnlAuthData> {
    void deleteOne(String id);

    List<SysPermissionDataRuleModel> queryUserOnlineAuthData(String userId, String cgformId);

    void createAiTestAuthData(JSONObject json);
}
