//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.online.auth.entity.OnlAuthRelation;

public interface IOnlAuthRelationService extends IService<OnlAuthRelation> {
    Result<?> saveRoleAuth(String roleId, String cgformId, int type, String authMode, List<String> authIds);
}
