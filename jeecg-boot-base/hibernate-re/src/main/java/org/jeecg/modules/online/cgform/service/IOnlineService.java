//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.model.b;

public interface IOnlineService {
    b queryOnlineConfig(OnlCgformHead head, String username);

    JSONObject queryOnlineFormObj(OnlCgformHead head, OnlCgformEnhanceJs onlCgformEnhanceJs);

    JSONObject queryOnlineFormObj(OnlCgformHead head, String username);

    List<OnlCgformButton> queryFormValidButton(String headId);

    JSONObject queryOnlineFormItem(OnlCgformHead head, String username);

    JSONObject queryFlowOnlineFormItem(OnlCgformHead head, String username, String taskId);

    String queryEnahcneJsString(String code, String type);

    JSONObject getOnlineVue3QueryInfo(String headId);

    List<DictModel> getOnlineTableDictData(String table, String dictCode, String dictText);
}
