package org.jeecg.common.communication.service;

import java.io.File;
import java.util.Map;

/**
 * 邮件服务接口
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
public interface EmailService {

    /**
     * 发送简单文本邮件
     * 
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @return 是否发送成功
     */
    boolean sendText(String to, String subject, String content);

    /**
     * 发送简单文本邮件（多个收件人）
     * 
     * @param to 收件人数组
     * @param subject 主题
     * @param content 内容
     * @return 是否发送成功
     */
    boolean sendText(String[] to, String subject, String content);

    /**
     * 发送HTML邮件
     * 
     * @param to 收件人
     * @param subject 主题
     * @param content HTML内容
     * @return 是否发送成功
     */
    boolean sendHtml(String to, String subject, String content);

    /**
     * 发送HTML邮件（多个收件人）
     * 
     * @param to 收件人数组
     * @param subject 主题
     * @param content HTML内容
     * @return 是否发送成功
     */
    boolean sendHtml(String[] to, String subject, String content);

    /**
     * 发送带附件的邮件
     * 
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param attachments 附件文件数组
     * @return 是否发送成功
     */
    boolean sendWithAttachment(String to, String subject, String content, File[] attachments);

    /**
     * 使用模板发送邮件
     * 
     * @param to 收件人
     * @param subject 主题
     * @param templateName 模板名称
     * @param model 模板数据
     * @return 是否发送成功
     */
    boolean sendTemplate(String to, String subject, String templateName, Map<String, Object> model);

    /**
     * 使用模板发送邮件（多个收件人）
     * 
     * @param to 收件人数组
     * @param subject 主题
     * @param templateName 模板名称
     * @param model 模板数据
     * @return 是否发送成功
     */
    boolean sendTemplate(String[] to, String subject, String templateName, Map<String, Object> model);

    /**
     * 发送验证码邮件
     * 
     * @param to 收件人
     * @param code 验证码
     * @param expireMinutes 过期时间(分钟)
     * @return 是否发送成功
     */
    boolean sendVerifyCode(String to, String code, int expireMinutes);

    /**
     * 发送通知邮件
     * 
     * @param to 收件人
     * @param title 通知标题
     * @param message 通知消息
     * @return 是否发送成功
     */
    boolean sendNotice(String to, String title, String message);
}