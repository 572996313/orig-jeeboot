package org.jeecg.common.api;

import org.jeecg.common.system.vo.*;

/**
 * @description 动态数据源服务API
 *
 * 提供与动态数据源查询相关的原子服务。
 *
 * @author jeecg-boot
 * @date 2025-11-21
 */
public interface IDynamicDataSourceAPI {

    /**
     * 根据数据源ID获取动态数据源
     * @param dbSourceId 数据源ID
     * @return 动态数据源
     */
    DynamicDataSourceModel getDynamicDbSourceById(String dbSourceId);

    /**
     * 根据数据源编码获取动态数据源
     * @param dbSourceCode 数据源编码
     * @return 动态数据源
     */
    DynamicDataSourceModel getDynamicDbSourceByCode(String dbSourceCode);
}