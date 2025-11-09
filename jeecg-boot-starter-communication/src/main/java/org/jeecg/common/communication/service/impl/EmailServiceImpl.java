package org.jeecg.common.communication.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.communication.config.JeecgCommunicationProperties;
import org.jeecg.common.communication.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

/**
 * 邮件服务实现（简化版占位符）
 * 
 * @author jeecg-boot
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JeecgCommunicationProperties properties;

    @Override
    public boolean sendText(String to, String subject, String content) {
        log.info("========== 发送简单文本邮件（简化版） ==========");
        log.info("收件人: {}, 主题: {}", to, subject);
        return true;
    }

    @Override
    public boolean sendText(String[] to, String subject, String content) {
        log.info("========== 批量发送文本邮件（简化版） ==========");
        log.info("收件人数量: {}, 主题: {}", to.length, subject);
        return true;
    }

    @Override
    public boolean sendHtml(String to, String subject, String content) {
        log.info("========== 发送HTML邮件（简化版） ==========");
        log.info("收件人: {}, 主题: {}", to, subject);
        return true;
    }

    @Override
    public boolean sendHtml(String[] to, String subject, String content) {
        log.info("========== 批量发送HTML邮件（简化版） ==========");
        log.info("收件人数量: {}, 主题: {}", to.length, subject);
        return true;
    }

    @Override
    public boolean sendWithAttachment(String to, String subject, String content, File[] attachments) {
        log.info("========== 发送带附件邮件（简化版） ==========");
        log.info("收件人: {}, 主题: {}, 附件数量: {}", to, subject, attachments != null ? attachments.length : 0);
        return true;
    }

    @Override
    public boolean sendTemplate(String to, String subject, String templateName, Map<String, Object> model) {
        log.info("========== 发送模板邮件（简化版） ==========");
        log.info("收件人: {}, 主题: {}, 模板: {}", to, subject, templateName);
        return true;
    }

    @Override
    public boolean sendTemplate(String[] to, String subject, String templateName, Map<String, Object> model) {
        log.info("========== 批量发送模板邮件（简化版） ==========");
        log.info("收件人数量: {}, 主题: {}, 模板: {}", to.length, subject, templateName);
        return true;
    }

    @Override
    public boolean sendVerifyCode(String to, String code, int expireMinutes) {
        log.info("========== 发送验证码邮件（简化版） ==========");
        log.info("收件人: {}, 验证码: {}, 过期时间: {}分钟", to, code, expireMinutes);
        return true;
    }

    @Override
    public boolean sendNotice(String to, String title, String message) {
        log.info("========== 发送通知邮件（简化版） ==========");
        log.info("收件人: {}, 标题: {}, 消息: {}", to, title, message);
        return true;
    }
}