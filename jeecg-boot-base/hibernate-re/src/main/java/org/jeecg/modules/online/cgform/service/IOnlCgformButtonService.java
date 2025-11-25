//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;

public interface IOnlCgformButtonService extends IService<OnlCgformButton> {
    void saveButton(OnlCgformButton onlCgformButton);

    Result<OnlCgformButton> editButton(OnlCgformButton onlCgformButton);

    List<OnlCgformButton> queryBuiltInButtonList(String formId);
}
