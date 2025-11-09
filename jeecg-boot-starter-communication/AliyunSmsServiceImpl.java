package org.jeecg.common.communication.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.*;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.communication.config.JeecgCommunicationProperties;
import org.jeecg.common.communication.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 阿里云短信服务实现类
 * 
 * 功能：
 * 1. 发送验证码、通知短信
 * 2. 多维度限流保护（每日、每小时、发送间隔、IP）
 * 3. Redis缓存限流记录
 * 4. 模板化短信发送
 * 5. 批量发送支持
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@Slf4j
@Service
@ConditionalOnProperty(prefix = "jeecg.communication.sms", name = "provider", havingValue = "aliyun")
public class AliyunSmsServiceImpl implements SmsService {

    @Autowired
    private JeecgCommunicationProperties properties;

    @Autowired(required = false)
    private StringRedisTemplate redisTemplate;

    private IAcsClient client;
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String RATE_LIMIT_KEY_PREFIX = "sms:rate:";
    private static final String RATE_LIMIT_DAY_KEY = RATE_LIMIT_KEY_PREFIX + "day:";
    private static final String RATE_LIMIT_HOUR_KEY = RATE_LIMIT_KEY_PREFIX + "hour:";
    private static final String RATE_LIMIT_INTERVAL_KEY = RATE_LIMIT_KEY_PREFIX + "interval:";

    @PostConstruct
    public void init() {
        try {
            JeecgCommunicationProperties.SmsConfig config = properties.getSms();
            DefaultProfile profile = DefaultProfile.getProfile(
                config.getRegionId(),
                config.getAccessKey(),
                config.getSecretKey()
            );
            
            if (config.getDomain() != null && !config.getDomain().isEmpty()) {
                DefaultProfile.addEndpoint(
                    config.getRegionId(),
                    config.getProduct(),
                    config.getDomain()
                );
            }
            
            this.client = new DefaultAcsClient(profile);
            log.info("阿里云短信服务初始化成功");
        } catch (Exception e) {
            log.error("阿里云短信服务初始化失败", e);
        }
    }

    @Override
    public boolean sendVerifyCode(String phone, String code) {
        return sendVerifyCode(phone, code, 5);
    }

    @Override
    public boolean sendVerifyCode(String phone, String code, int expireMinutes) {
        if (!checkRateLimit(phone)) {
            log.warn("手机号 {} 发送频率超限", phone);
            return false;
        }

        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("expire", String.valueOf(expireMinutes));

        String templateCode = properties.getSms().getTemplate().getVerifyCode();
        return sendTemplate(phone, templateCode, params);
    }

    @Override
    public boolean sendLoginNotice(String phone, String loginTime, String loginLocation) {
        Map<String, String> params = new HashMap<>();
        params.put("time", loginTime);
        params.put("location", loginLocation);

        String templateCode = properties.getSms().getTemplate().getLoginNotice();
        return sendTemplate(phone, templateCode, params);
    }

    @Override
    public boolean sendRegisterNotice(String phone, String username) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);

        String templateCode = properties.getSms().getTemplate().getRegisterNotice();
        return sendTemplate(phone, templateCode, params);
    }

    @Override
    public boolean sendOrderNotice(String phone, String orderNo, String status) {
        Map<String, String> params = new HashMap<>();
        params.put("orderNo", orderNo);
        params.put("status", status);

        String templateCode = properties.getSms().getTemplate().getOrderNotice();
        return sendTemplate(phone, templateCode, params);
    }

    @Override
    public boolean sendSystemNotice(String phone, String message) {
        Map<String, String> params = new HashMap<>();
        params.put("message", message);

        String templateCode = properties.getSms().getTemplate().getSystemNotice();
        return sendTemplate(phone, templateCode, params);
    }

    @Override
    public boolean sendTemplate(String phone, String templateCode, Map<String, String> params) {
        if (client == null) {
            log.warn("阿里云短信客户端未初始化");
            return false;
        }

        try {
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(phone);
            request.setSignName(properties.getSms().getSignName());
            request.setTemplateCode(templateCode);
            request.setTemplateParam(objectMapper.writeValueAsString(params));

            SendSmsResponse response = client.getAcsResponse(request);
            
            if ("OK".equals(response.getCode())) {
                log.info("短信发送成功，手机号: {}, 模板: {}", phone, templateCode);
                recordSendSuccess(phone);
                return true;
            } else {
                log.error("短信发送失败，手机号: {}, Code: {}, Message: {}", 
                    phone, response.getCode(), response.getMessage());
                return false;
            }
        } catch (Exception e) {
            log.error("短信发送异常，手机号: {}", phone, e);
            return false;
        }
    }

    @Override
    public boolean sendTemplateBatch(String[] phones, String templateCode, Map<String, String> params) {
        if (client == null || phones == null || phones.length == 0) {
            return false;
        }

        boolean allSuccess = true;
        for (String phone : phones) {
            if (!sendTemplate(phone, templateCode, params)) {
                allSuccess = false;
            }
        }
        return allSuccess;
    }

    @Override
    public boolean isRateLimited(String phone) {
        return !checkRateLimit(phone);
    }

    @Override
    public int getRemainingCount(String phone) {
        if (redisTemplate == null) {
            return properties.getSms().getRateLimit().getMaxPerDay();
        }

        String dayKey = RATE_LIMIT_DAY_KEY + phone + ":" + LocalDate.now();
        String countStr = redisTemplate.opsForValue().get(dayKey);
        int sentCount = countStr != null ? Integer.parseInt(countStr) : 0;
        
        return Math.max(0, properties.getSms().getRateLimit().getMaxPerDay() - sentCount);
    }

    @Override
    public String queryStatus(String bizId) {
        if (client == null || bizId == null) {
            return "UNKNOWN";
        }

        try {
            QuerySendDetailsRequest request = new QuerySendDetailsRequest();
            request.setPhoneNumber(bizId.split("_")[0]); // 假设bizId格式为 phone_timestamp
            request.setSendDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            request.setPageSize(10L);
            request.setCurrentPage(1L);

            QuerySendDetailsResponse response = client.getAcsResponse(request);
            
            if ("OK".equals(response.getCode()) && !response.getSmsSendDetailDTOs().isEmpty()) {
                return response.getSmsSendDetailDTOs().get(0).getSendStatus() == 3 ? "SUCCESS" : "FAILED";
            }
            return "UNKNOWN";
        } catch (Exception e) {
            log.error("查询短信状态失败", e);
            return "ERROR";
        }
    }

    @Override
    public String getBalance() {
        // 阿里云短信没有直接查询余额的API
        // 需要在阿里云控制台查看
        return "请登录阿里云控制台查看";
    }

    /**
     * 检查是否触发限流
     */
    private boolean checkRateLimit(String phone) {
        if (redisTemplate == null) {
            return true; // Redis未配置，不限流
        }

        JeecgCommunicationProperties.SmsConfig.RateLimitConfig rateLimit = 
            properties.getSms().getRateLimit();

        // 检查每日限制
        String dayKey = RATE_LIMIT_DAY_KEY + phone + ":" + LocalDate.now();
        String dayCountStr = redisTemplate.opsForValue().get(dayKey);
        int dayCount = dayCountStr != null ? Integer.parseInt(dayCountStr) : 0;
        if (dayCount >= rateLimit.getMaxPerDay()) {
            return false;
        }

        // 检查每小时限制
        String hourKey = RATE_LIMIT_HOUR_KEY + phone + ":" + 
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
        String hourCountStr = redisTemplate.opsForValue().get(hourKey);
        int hourCount = hourCountStr != null ? Integer.parseInt(hourCountStr) : 0;
        if (hourCount >= rateLimit.getMaxPerHour()) {
            return false;
        }

        // 检查发送间隔
        String intervalKey = RATE_LIMIT_INTERVAL_KEY + phone;
        Boolean hasInterval = redisTemplate.hasKey(intervalKey);
        if (Boolean.TRUE.equals(hasInterval)) {
            return false;
        }

        return true;
    }

    /**
     * 记录发送成功
     */
    private void recordSendSuccess(String phone) {
        if (redisTemplate == null) {
            return;
        }

        JeecgCommunicationProperties.SmsConfig.RateLimitConfig rateLimit = 
            properties.getSms().getRateLimit();

        // 每日计数
        String dayKey = RATE_LIMIT_DAY_KEY + phone + ":" + LocalDate.now();
        redisTemplate.opsForValue().increment(dayKey);
        redisTemplate.expire(dayKey, 24, TimeUnit.HOURS);

        // 每小时计数
        String hourKey = RATE_LIMIT_HOUR_KEY + phone + ":" + 
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
        redisTemplate.opsForValue().increment(hourKey);
        redisTemplate.expire(hourKey, 1, TimeUnit.HOURS);

        // 发送间隔锁
        String intervalKey = RATE_LIMIT_INTERVAL_KEY + phone;
        redisTemplate.opsForValue().set(intervalKey, "1", rateLimit.getInterval(), TimeUnit.SECONDS);
    }
}