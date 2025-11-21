package org.jeecg.common.system.api.modular;

import org.jeecg.common.api.dto.AiragFlowDTO;
import org.jeecg.common.api.dto.DataLogDTO;

/**
 * @description 系统通用服务API
 *
 * 提供不便于归类到其他模块的系统级原子服务。
 * 例如：调用AI流程、保存数据日志等。
 *
 * @author jeecg-boot
 * @date 2025-11-21
 */
public interface ISystemAPI {

    /**
     * 运行Airag流程
     * @param airagFlowDTO Airag流程DTO
     * @return 运行结果
     */
    Object runAiragFlow(AiragFlowDTO airagFlowDTO);

    /**
     * 保存数据日志
     * @param dataLogDto 数据日志DTO
     */
    void saveDataLog(DataLogDTO dataLogDto);
}