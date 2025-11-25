//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jeecg.common.system.vo.SysPermissionDataRuleModel;
import org.jeecg.modules.online.auth.entity.OnlAuthData;

public interface OnlAuthDataMapper extends BaseMapper<OnlAuthData> {
    List<SysPermissionDataRuleModel> queryRoleAuthData(@Param("userId") String userId, @Param("cgformId") String cgformId);

    List<SysPermissionDataRuleModel> queryDepartAuthData(@Param("userId") String userId, @Param("cgformId") String cgformId);

    List<SysPermissionDataRuleModel> queryUserAuthData(@Param("userId") String userId, @Param("cgformId") String cgformId);
}
