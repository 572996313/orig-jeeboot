//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface OnlineMapper {
    List<Map<String, Object>> selectByCondition(@Param("sqlStr") String sqlStr, @Param("param") Map<String, Object> param);

    IPage<Map<String, Object>> selectPageByCondition(Page<Map<String, Object>> page, @Param("sqlStr") String sqlStr, @Param("param") Map<String, Object> param);
}
