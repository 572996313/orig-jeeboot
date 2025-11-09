package org.jeecg.common.communication.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.communication.config.JeecgCommunicationProperties;
import org.jeecg.common.communication.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮件服务实现类
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Autowired(required = false)
    private Configuration freemarkerConfig;

    @Autowired
    private JeecgCommunicationProperties properties;

    @Override
    public boolean sendText(String to, String subject, String content) {
        return sendText(new String[]{to}, subject, content);
    }

    @Override
    public boolean sendText(String[] to, String subject, String content) {
        if (mailSender == null) {
            log.warn("邮件服务未配置，无法发送邮件");
            return false;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(properties.getEmail().getFrom());
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);

            mailSender.send(message);
            log.info("文本邮件发送成功，收件人: {}", String.join(",", to));
            return true;
        } catch (Exception e) {
            log.error("文本邮件发送失败", e);
            return false;
        }
    }

    @Override
    public boolean sendHtml(String to, String subject, String content) {
        return sendHtml(new String[]{to}, subject, content);
    }

    @Override
    public boolean sendHtml(String[] to, String subject, String content) {
        if (mailSender == null) {
            log.warn("邮件服务未配置，无法发送邮件");
            return false;
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, properties.getEmail().getEncoding());

            helper.setFrom(properties.getEmail().getFrom(), properties.getEmail().getFromName());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            log.info("HTML邮件发送成功，收件人: {}", String.join(",", to));
            return true;
        } catch (Exception e) {
            log.error("HTML邮件发送失败", e);
            return false;
        }
    }

    @Override
    public boolean sendWithAttachment(String to, String subject, String content, File[] attachments) {
        if (mailSender == null) {
            log.warn("邮件服务未配置，无法发送邮件");
            return false;
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, properties.getEmail().getEncoding());

            helper.setFrom(properties.getEmail().getFrom(), properties.getEmail().getFromName());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, properties.getEmail().getHtml());

            // 添加附件
            if (attachments != null && attachments.length > 0) {
                for (File file : attachments) {
                    if (file != null && file.exists()) {
                        helper.addAttachment(file.getName(), file);
                    }
                }
            }

            mailSender.send(message);
            log.info("带附件邮件发送成功，收件人: {}, 附件数: {}", to, attachments != null ? attachments.length : 0);
            return true;
        } catch (Exception e) {
            log.error("带附件邮件发送失败", e);
            return false;
        }
    }

    @Override
    public boolean sendTemplate(String to, String subject, String templateName, Map<String, Object> model) {
        return sendTemplate(new String[]{to}, subject, templateName, model);
    }

    @Override
    public boolean sendTemplate(String[] to, String subject, String templateName, Map<String, Object> model) {
        if (freemarkerConfig == null) {
            log.warn("Freemarker未配置，无法使用模板发送邮件");
            return false;
        }

        try {
            // 加载模板
            Template template = freemarkerConfig.getTemplate(templateName);
            
            // 渲染模板
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            // 发送HTML邮件
            return sendHtml(to, subject, content);
        } catch (Exception e) {
            log.error("模板邮件发送失败，模板: {}", templateName, e);
            return false;
        }
    }

    @Override
    public boolean sendVerifyCode(String to, String code, int expireMinutes) {
        String subject = "验证码 - " + properties.getEmail().getFromName();
        
        Map<String, Object> model = new HashMap<>();
        model.put("code", code);
        model.put("expireMinutes", expireMinutes);
        model.put("systemName", properties.getEmail().getFromName());

        // 如果有验证码模板，使用模板
        if (freemarkerConfig != null) {
            try {
                return sendTemplate(to, subject, "verify_code.ftl", model);
            } catch (Exception e) {
                log.warn("验证码模板不存在，使用默认格式");
            }
        }

        // 默认格式
        String content = String.format(
            "<html><body>" +
            "<h2>验证码</h2>" +
            "<p>您的验证码是：<strong style='font-size:24px;color:#ff6600;'>%s</strong></p>" +
            "<p>验证码有效期：%d分钟</p>" +
            "<p>如非本人操作，请忽略此邮件。</p>" +
            "</body></html>",
            code, expireMinutes
        );

        return sendHtml(to, subject, content);
    }

    @Override
    public boolean sendNotice(String to, String title, String message) {
        String subject = title + " - " + properties.getEmail().getFromName();
        
        Map<String, Object> model = new HashMap<>();
        model.put("title", title);
        model.put("message", message);
        model.put("systemName", properties.getEmail().getFromName());

        // 如果有通知模板，使用模板
        if (freemarkerConfig != null) {
            try {
                return sendTemplate(to, subject, "notice.ftl", model);
            } catch (Exception e) {
                log.warn("通知模板不存在，使用默认格式");
            }
        }

        // 默认格式
        String content = String.format(
            "<html><body>" +
            "<h2>%s</h2>" +
            "<div style='padding:15px;background:#f5f5f5;border-left:4px solid #1890ff;'>%s</div>" +
            "<p style='color:#999;margin-top:20px;'>此邮件由系统自动发送，请勿回复。</p>" +
            "</body></html>",
            title, message
        );

        return sendHtml(to, subject, content);
    }
}