//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.online.auth.entity.OnlAuthRelation;

public interface OnlAuthRelationMapper extends BaseMapper<OnlAuthRelation> {
    List<String> queryDisabledButtonNameById(@Param("ids") List<String> ids);
}
