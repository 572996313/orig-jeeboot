package org.jeecg.common.communication.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.communication.config.JeecgCommunicationProperties;
import org.jeecg.common.communication.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 阿里云短信服务实现（简化版占位符）
 * 
 * @author jeecg-boot
 */
@Slf4j
@Service
public class AliyunSmsServiceImpl implements SmsService {

    @Autowired
    private JeecgCommunicationProperties properties;

    @Override
    public boolean sendVerifyCode(String phone, String code) {
        log.info("========== 发送验证码短信（简化版） ==========");
        log.info("手机号: {}, 验证码: {}", phone, code);
        return true;
    }

    @Override
    public boolean sendVerifyCode(String phone, String code, int expireMinutes) {
        log.info("========== 发送验证码短信（简化版，带过期时间） ==========");
        log.info("手机号: {}, 验证码: {}, 过期时间: {}分钟", phone, code, expireMinutes);
        return true;
    }

    @Override
    public boolean sendLoginNotice(String phone, String loginTime, String loginLocation) {
        log.info("========== 发送登录通知短信（简化版） ==========");
        log.info("手机号: {}, 登录时间: {}, 登录地点: {}", phone, loginTime, loginLocation);
        return true;
    }

    @Override
    public boolean sendRegisterNotice(String phone, String username) {
        log.info("========== 发送注册通知短信（简化版） ==========");
        log.info("手机号: {}, 用户名: {}", phone, username);
        return true;
    }

    @Override
    public boolean sendOrderNotice(String phone, String orderNo, String status) {
        log.info("========== 发送订单通知短信（简化版） ==========");
        log.info("手机号: {}, 订单号: {}, 状态: {}", phone, orderNo, status);
        return true;
    }

    @Override
    public boolean sendSystemNotice(String phone, String message) {
        log.info("========== 发送系统通知短信（简化版） ==========");
        log.info("手机号: {}, 消息: {}", phone, message);
        return true;
    }

    @Override
    public boolean sendTemplate(String phone, String templateCode, Map<String, String> params) {
        log.info("========== 发送模板短信（简化版） ==========");
        log.info("手机号: {}, 模板代码: {}", phone, templateCode);
        return true;
    }

    @Override
    public boolean sendTemplateBatch(String[] phones, String templateCode, Map<String, String> params) {
        log.info("========== 批量发送模板短信（简化版） ==========");
        log.info("手机号数量: {}, 模板代码: {}", phones.length, templateCode);
        return true;
    }

    @Override
    public boolean isRateLimited(String phone) {
        log.info("========== 检查发送限制（简化版） ==========");
        log.info("手机号: {}", phone);
        return false; // 简化版不限制
    }

    @Override
    public int getRemainingCount(String phone) {
        log.info("========== 获取剩余发送次数（简化版） ==========");
        log.info("手机号: {}", phone);
        return 999; // 简化版返回固定值
    }

    @Override
    public String queryStatus(String bizId) {
        log.info("========== 查询发送状态（简化版） ==========");
        log.info("业务ID: {}", bizId);
        return "SUCCESS"; // 简化版默认成功
    }

    @Override
    public String getBalance() {
        log.info("========== 获取账户余额（简化版） ==========");
        return "999999"; // 简化版返回固定值
    }
}