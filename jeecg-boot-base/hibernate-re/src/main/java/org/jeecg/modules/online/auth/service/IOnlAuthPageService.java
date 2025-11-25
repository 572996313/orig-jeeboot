//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import org.jeecg.modules.online.auth.entity.OnlAuthPage;
import org.jeecg.modules.online.auth.vo.AuthColumnVO;
import org.jeecg.modules.online.auth.vo.AuthPageVO;

public interface IOnlAuthPageService extends IService<OnlAuthPage> {
    void disableAuthColumn(AuthColumnVO authColumnVO);

    void enableAuthColumn(AuthColumnVO authColumnVO);

    void updateAuthColumnBatch(List<AuthColumnVO> authColumnVO);

    void switchAuthColumn(AuthColumnVO authColumnVO);

    void switchAuthColumnBatch(List<AuthColumnVO> authColumnVOList);

    void switchFormShow(String cgformId, String code, boolean flag);

    void switchFormEditable(String cgformId, String code, boolean flag);

    void switchListShow(String cgformId, String code, boolean flag);

    List<AuthPageVO> queryRoleAuthByFormId(String roleId, String cgformId, int type);

    List<AuthPageVO> queryRoleDataAuth(String roleId, String cgformId);

    List<AuthPageVO> queryAuthByFormId(String cgformId, int type);

    List<String> queryRoleNoAuthCode(String cgformId, Integer control, Integer page);

    List<String> queryFormDisabledCode(String cgformId);

    List<String> queryHideCode(String userId, String cgformId, boolean isList);

    List<String> queryListHideColumn(String userId, String cgformId);

    List<String> queryFormHideColumn(String userId, String cgformId);

    List<String> queryFormHideButton(String userId, String cgformId);

    List<String> queryHideCode(String cgformId, boolean isList);

    List<String> queryListHideButton(String userId, String cgformId);
}
