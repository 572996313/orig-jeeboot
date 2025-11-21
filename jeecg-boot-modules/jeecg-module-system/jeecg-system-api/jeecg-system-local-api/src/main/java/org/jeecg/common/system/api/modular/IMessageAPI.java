package org.jeecg.common.system.api.modular;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.dto.message.*;
import org.jeecg.common.communication.sms.enums.DySmsEnum;
import org.jeecg.common.system.vo.*;
import org.jeecg.common.constant.enums.EmailTemplateEnum;

/**
 * @description 消息服务API
 *
 * 提供统一的消息发送原子服务。
 * 包括发送系统通知、业务消息、邮件、短信、WebSocket消息等功能。
 *
 * @author jeecg-boot
 * @date 2025-11-21
 */
public interface IMessageAPI {

    /**
     * 发送系统公告
     * @param message 消息DTO
     */
    void sendSysAnnouncement(MessageDTO message);

    /**
     * 发送业务公告
     * @param message 业务消息DTO
     */
    void sendBusAnnouncement(BusMessageDTO message);

    /**
     * 发送模板公告
     * @param message 模板消息DTO
     */
    void sendTemplateAnnouncement(TemplateMessageDTO message);

    /**
     * 发送业务模板公告
     * @param message 业务模板消息DTO
     */
    void sendBusTemplateAnnouncement(BusTemplateMessageDTO message);

    /**
     * 通过模板编码解析模板
     * @param templateDTO 模板DTO
     * @return 解析后的模板
     */
    String parseTemplateByCode(TemplateDTO templateDTO);

    /**
     * 发送模板消息
     * @param message 消息DTO
     */
    void sendTemplateMessage(MessageDTO message);

    /**
     * 获取模板内容
     * @param templateCode 模板编码
     * @return 模板内容
     */
    String getTemplateContent(String templateCode);

    /**
     * 更新系统公告阅读状态
     * @param busType 业务类型
     * @param busId 业务ID
     */
    void updateSysAnnounReadFlag(String busType, String busId);

    /**
     * 发送WebSocket消息
     * @param userIds 用户ID数组
     * @param cmd 命令
     */
    void sendWebSocketMsg(String[] userIds, String cmd);

    /**
     * 会议签到WebSocket
     * @param userId 用户ID
     */
    void meetingSignWebsocket(String userId);

    /**
     * 发送邮件消息
     * @param email 邮箱
     * @param title 标题
     * @param content 内容
     */
    void sendEmailMsg(String email, String title, String content);

    /**
     * 发送HTML模板邮件
     * @param email 邮箱
     * @param title 标题
     * @param emailTemplateEnum 邮件模板枚举
     * @param params 参数
     */
    void sendHtmlTemplateEmail(String email, String title, EmailTemplateEnum emailTemplateEnum, JSONObject params);

    /**
     * 发送短信消息
     * @param phone 手机号
     * @param param 参数
     * @param dySmsEnum 短信枚举
     */
    void sendSmsMsg(String phone, JSONObject param, DySmsEnum dySmsEnum);

    /**
     * 发送APP聊天Socket
     * @param userId 用户ID
     */
    void sendAppChatSocket(String userId);

    /**
     * 公告自动发布
     * @param dataId 数据ID
     * @param currentUserName 当前用户名
     */
    void announcementAutoRelease(String dataId, String currentUserName);
}