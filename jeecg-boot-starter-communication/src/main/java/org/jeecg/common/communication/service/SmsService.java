package org.jeecg.common.communication.service;

import java.util.Map;

/**
 * 短信服务接口
 * 
 * 支持功能：
 * 1. 验证码短信
 * 2. 通知短信
 * 3. 营销短信
 * 4. 模板短信
 * 5. 批量发送
 * 
 * 支持服务商：
 * - 阿里云短信
 * - 腾讯云短信
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
public interface SmsService {

    /**
     * 发送验证码短信
     * 
     * @param phone 手机号
     * @param code 验证码
     * @return 是否发送成功
     */
    boolean sendVerifyCode(String phone, String code);

    /**
     * 发送验证码短信（带过期时间）
     * 
     * @param phone 手机号
     * @param code 验证码
     * @param expireMinutes 过期时间（分钟）
     * @return 是否发送成功
     */
    boolean sendVerifyCode(String phone, String code, int expireMinutes);

    /**
     * 发送登录通知短信
     * 
     * @param phone 手机号
     * @param loginTime 登录时间
     * @param loginLocation 登录地点
     * @return 是否发送成功
     */
    boolean sendLoginNotice(String phone, String loginTime, String loginLocation);

    /**
     * 发送注册通知短信
     * 
     * @param phone 手机号
     * @param username 用户名
     * @return 是否发送成功
     */
    boolean sendRegisterNotice(String phone, String username);

    /**
     * 发送订单通知短信
     * 
     * @param phone 手机号
     * @param orderNo 订单号
     * @param status 订单状态
     * @return 是否发送成功
     */
    boolean sendOrderNotice(String phone, String orderNo, String status);

    /**
     * 发送系统通知短信
     * 
     * @param phone 手机号
     * @param message 通知内容
     * @return 是否发送成功
     */
    boolean sendSystemNotice(String phone, String message);

    /**
     * 发送模板短信
     * 
     * @param phone 手机号
     * @param templateCode 模板代码
     * @param params 模板参数
     * @return 是否发送成功
     */
    boolean sendTemplate(String phone, String templateCode, Map<String, String> params);

    /**
     * 批量发送模板短信
     * 
     * @param phones 手机号列表
     * @param templateCode 模板代码
     * @param params 模板参数
     * @return 是否发送成功
     */
    boolean sendTemplateBatch(String[] phones, String templateCode, Map<String, String> params);

    /**
     * 检查手机号是否达到发送限制
     * 
     * @param phone 手机号
     * @return true-已达限制，false-未达限制
     */
    boolean isRateLimited(String phone);

    /**
     * 获取手机号今日剩余发送次数
     * 
     * @param phone 手机号
     * @return 剩余发送次数
     */
    int getRemainingCount(String phone);

    /**
     * 查询短信发送状态
     * 
     * @param bizId 业务ID（发送时返回）
     * @return 发送状态
     */
    String queryStatus(String bizId);

    /**
     * 获取短信账户余额
     * 
     * @return 账户余额
     */
    String getBalance();
}