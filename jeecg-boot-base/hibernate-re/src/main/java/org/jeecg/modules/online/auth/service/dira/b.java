//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.service.dira;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.online.auth.entity.OnlAuthPage;
import org.jeecg.modules.online.auth.entity.OnlAuthRelation;
import org.jeecg.modules.online.auth.mapper.OnlAuthPageMapper;
import org.jeecg.modules.online.auth.mapper.OnlAuthRelationMapper;
import org.jeecg.modules.online.auth.service.IOnlAuthPageService;
import org.jeecg.modules.online.auth.vo.AuthColumnVO;
import org.jeecg.modules.online.auth.vo.AuthPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("onlAuthPageServiceImpl")
public class b extends ServiceImpl<OnlAuthPageMapper, OnlAuthPage> implements IOnlAuthPageService {
    @Autowired
    private OnlAuthRelationMapper onlAuthRelationMapper;

    public void disableAuthColumn(AuthColumnVO authColumnVO) {
        LambdaUpdateWrapper updateWrapper = (new UpdateWrapper<OnlAuthPage>()).lambda().eq(OnlAuthPage::getCgformId, authColumnVO.getCgformId()).eq(OnlAuthPage::getCode, authColumnVO.getCode()).eq(OnlAuthPage::getType, 1).set(OnlAuthPage::getStatus, 0);
        this.update(updateWrapper);
    }

    @Transactional
    public void enableAuthColumn(AuthColumnVO authColumnVO) {
        String cgFormId = authColumnVO.getCgformId();
        String code = authColumnVO.getCode();
        LambdaQueryWrapper query = (new LambdaQueryWrapper<OnlAuthPage>()).eq(OnlAuthPage::getCgformId, cgFormId).eq(OnlAuthPage::getCode, code).eq(OnlAuthPage::getType, 1);
        List ls = this.list(query);
        if (ls != null && ls.size() > 0) {
            LambdaUpdateWrapper updateWrapper = (new UpdateWrapper<OnlAuthPage>()).lambda().eq(OnlAuthPage::getCgformId, cgFormId).eq(OnlAuthPage::getCode, code).eq(OnlAuthPage::getType, 1).set(OnlAuthPage::getStatus, 1);
            this.update(updateWrapper);
        } else {
            ls = new ArrayList();
            ls.add(new OnlAuthPage(cgFormId, code, 3, 5));
            ls.add(new OnlAuthPage(cgFormId, code, 5, 5));
            ls.add(new OnlAuthPage(cgFormId, code, 5, 3));
            this.saveBatch(ls);
        }

    }

    @Transactional
    public void updateAuthColumnBatch(List<AuthColumnVO> authColumnVOList) {
        for(AuthColumnVO authColumnVO : authColumnVOList) {
            if (authColumnVO.getStatus() == 1) {
                this.enableAuthColumn(authColumnVO);
            } else {
                this.disableAuthColumn(authColumnVO);
            }
        }

    }

    @Transactional
    public void switchAuthColumn(AuthColumnVO authColumnVO) {
        String cgformId = authColumnVO.getCgformId();
        String code = authColumnVO.getCode();
        int switchFlag = authColumnVO.getSwitchFlag();
        if (switchFlag == 1) {
            this.switchListShow(cgformId, code, authColumnVO.isListShow());
        } else if (switchFlag == 2) {
            this.switchFormShow(cgformId, code, authColumnVO.isFormShow());
        } else if (switchFlag == 3) {
            this.switchFormEditable(cgformId, code, authColumnVO.isFormEditable());
        } else if (switchFlag == 4) {
            this.switchFormEditable(cgformId, code, authColumnVO.isFormEditable());
            this.switchFormShow(cgformId, code, authColumnVO.isFormShow());
        }

    }

    @Transactional
    public void switchAuthColumnBatch(List<AuthColumnVO> authColumnVOList) {
        for(AuthColumnVO authColumnVO : authColumnVOList) {
            this.switchAuthColumn(authColumnVO);
        }

    }

    @Transactional
    public void switchFormShow(String cgformId, String code, boolean flag) {
        this.a(cgformId, code, 5, 5, flag);
    }

    @Transactional
    public void switchFormEditable(String cgformId, String code, boolean flag) {
        this.a(cgformId, code, 3, 5, flag);
    }

    @Transactional
    public void switchListShow(String cgformId, String code, boolean flag) {
        this.a(cgformId, code, 5, 3, flag);
    }

    public List<AuthPageVO> queryRoleAuthByFormId(String roleId, String cgformId, int type) {
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleAuthByFormId(roleId, cgformId, type);
    }

    public List<AuthPageVO> queryRoleDataAuth(String roleId, String cgformId) {
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleDataAuth(roleId, cgformId);
    }

    public List<AuthPageVO> queryAuthByFormId(String cgformId, int type) {
        return type == 1 ? ((OnlAuthPageMapper)this.baseMapper).queryAuthColumnByFormId(cgformId) : ((OnlAuthPageMapper)this.baseMapper).queryAuthButtonByFormId(cgformId);
    }

    public List<String> queryRoleNoAuthCode(String cgformId, Integer control, Integer page) {
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String userId = sysUser.getId();
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(userId, cgformId, control, page, (Integer)null);
    }

    public List<String> queryFormDisabledCode(String cgformId) {
        return this.queryRoleNoAuthCode(cgformId, 3, 5);
    }

    public List<String> queryHideCode(String userId, String cgformId, boolean isList) {
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(userId, cgformId, 5, isList ? 3 : 5, (Integer)null);
    }

    public List<String> queryListHideColumn(String userId, String cgformId) {
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(userId, cgformId, 5, 3, 1);
    }

    public List<String> queryFormHideColumn(String userId, String cgformId) {
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(userId, cgformId, 5, 5, 1);
    }

    public List<String> queryFormHideButton(String userId, String cgformId) {
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(userId, cgformId, 5, 5, 2);
    }

    public List<String> queryHideCode(String cgformId, boolean isList) {
        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String userId = sysUser.getId();
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(userId, cgformId, 5, isList ? 3 : 5, (Integer)null);
    }

    public List<String> queryListHideButton(String userId, String cgformId) {
        if (userId == null) {
            LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
            userId = sysUser.getId();
        }

        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(userId, cgformId, 5, 3, 2);
    }

    private void a(String cgformId, String code, int control, int page, boolean flag) {
        LambdaQueryWrapper query = (LambdaQueryWrapper) (new LambdaQueryWrapper<OnlAuthPage>()).eq(OnlAuthPage::getCgformId, cgformId).eq(OnlAuthPage::getCode, code).eq(OnlAuthPage::getControl, control).eq(OnlAuthPage::getPage, page).eq(OnlAuthPage::getType, 1);
        OnlAuthPage auth = (OnlAuthPage)((OnlAuthPageMapper)this.baseMapper).selectOne(query);
        if (flag) {
            if (auth == null) {
                OnlAuthPage entity = new OnlAuthPage();
                entity.setCgformId(cgformId);
                entity.setCode(code);
                entity.setControl(control);
                entity.setPage(page);
                entity.setType(1);
                entity.setStatus(1);
                ((OnlAuthPageMapper)this.baseMapper).insert(entity);
            } else if (auth.getStatus() == 0) {
                auth.setStatus(1);
                ((OnlAuthPageMapper)this.baseMapper).updateById(auth);
            }
        } else if (!flag && auth != null) {
            String authId = auth.getId();
            ((OnlAuthPageMapper)this.baseMapper).deleteById(authId);
            this.onlAuthRelationMapper.delete((Wrapper)(new LambdaQueryWrapper<OnlAuthRelation>()).eq(OnlAuthRelation::getAuthId, authId));
        }

    }
}
