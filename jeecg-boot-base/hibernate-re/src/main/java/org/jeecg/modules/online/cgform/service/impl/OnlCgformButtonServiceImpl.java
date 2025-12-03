//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.online.cgform.dird.b;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.mapper.OnlCgformButtonMapper;
import org.jeecg.modules.online.cgform.service.IOnlCgformButtonService;
import org.springframework.stereotype.Service;

@Service("onlCgformButtonServiceImpl")
public class OnlCgformButtonServiceImpl extends ServiceImpl<OnlCgformButtonMapper, OnlCgformButton> implements IOnlCgformButtonService {
    public void saveButton(OnlCgformButton onlCgformButton) {
        LambdaQueryWrapper<OnlCgformButton> query = (LambdaQueryWrapper<OnlCgformButton>)((LambdaQueryWrapper<OnlCgformButton>)(new LambdaQueryWrapper<OnlCgformButton>()).eq(OnlCgformButton::getButtonCode, onlCgformButton.getButtonCode())).eq(OnlCgformButton::getCgformHeadId, onlCgformButton.getCgformHeadId());
        Long count = ((OnlCgformButtonMapper)this.baseMapper).selectCount(query);
        if (count != null && count != 0L) {
            throw new JeecgBootException("按钮编码不能重复");
        } else {
            this.save(onlCgformButton);
        }
    }

    public Result<OnlCgformButton> editButton(OnlCgformButton onlCgformButton) {
        Result result = new Result();
        OnlCgformButton onlCgformButtonEntity = (OnlCgformButton)this.getById(onlCgformButton.getId());
        if (onlCgformButtonEntity == null) {
            result.error500("未找到对应实体");
        } else {
            Object eq = (new LambdaQueryWrapper<OnlCgformButton>()).eq(OnlCgformButton::getButtonCode, onlCgformButton.getButtonCode());
            Object eq1 = ((LambdaQueryWrapper<OnlCgformButton>) eq).eq(OnlCgformButton::getCgformHeadId, onlCgformButton.getCgformHeadId());
            Object ne = ((LambdaQueryWrapper<OnlCgformButton>) eq1).ne(OnlCgformButton::getId, onlCgformButton.getId());
            LambdaQueryWrapper query = (LambdaQueryWrapper) ne;
            Long count = ((OnlCgformButtonMapper)this.baseMapper).selectCount(query);
            if (count != null && count != 0L) {
                result.error500("按钮编码不能重复");
            } else {
                boolean ok = this.updateById(onlCgformButton);
                if (ok) {
                    result.success("修改成功!");
                } else {
                    result.error500("修改失败!");
                }
            }
        }

        return result;
    }

    public List<OnlCgformButton> queryBuiltInButtonList(String formId) {
        LambdaQueryWrapper<OnlCgformButton> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(OnlCgformButton::getCgformHeadId, formId);
        queryWrapper.in(OnlCgformButton::getButtonCode, b.getButtonCodeSet());
        List buttonList = super.list(queryWrapper);
        return b.a(formId, buttonList);
    }
}
