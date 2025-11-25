//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.online.auth.entity.OnlAuthPage;
import org.jeecg.modules.online.auth.vo.AuthPageVO;

public interface OnlAuthPageMapper extends BaseMapper<OnlAuthPage> {
    List<AuthPageVO> queryRoleAuthByFormId(@Param("roleId") String roleId, @Param("cgformId") String cgformId, @Param("type") int type);

    List<AuthPageVO> queryAuthColumnByFormId(@Param("cgformId") String cgformId);

    List<AuthPageVO> queryAuthButtonByFormId(@Param("cgformId") String cgformId);

    List<AuthPageVO> queryRoleDataAuth(@Param("roleId") String roleId, @Param("cgformId") String cgformId);

    List<String> queryRoleNoAuthCode(@Param("userId") String userId, @Param("cgformId") String cgformId, @Param("control") Integer control, @Param("page") Integer page, @Param("type") Integer type);
}
