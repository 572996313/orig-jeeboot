package org.jeecg.config.mybatis.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Param;
import org.jeecg.config.mybatis.LogDTO;

/**
 * @Description: BaseCommonMapper
 * @author: jeecg-boot
 */
public interface BaseCommonMapper {

    /**
     * 保存日志
     * @param dto
     */
    @InterceptorIgnore(illegalSql = "true", tenantLine = "true")
    void saveLog(@Param("dto") LogDTO dto);

}
