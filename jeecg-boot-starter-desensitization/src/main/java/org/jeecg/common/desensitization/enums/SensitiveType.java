package org.jeecg.common.desensitization.enums;

/**
 * 敏感信息类型枚举
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
public enum SensitiveType {
    
    /**
     * 中文姓名
     */
    CHINESE_NAME,
    
    /**
     * 身份证号
     */
    ID_CARD,
    
    /**
     * 座机号
     */
    FIXED_PHONE,
    
    /**
     * 手机号
     */
    MOBILE_PHONE,
    
    /**
     * 地址
     */
    ADDRESS,
    
    /**
     * 电子邮件
     */
    EMAIL,
    
    /**
     * 银行卡号
     */
    BANK_CARD,
    
    /**
     * 密码
     */
    PASSWORD,
    
    /**
     * 车牌号
     */
    CAR_LICENSE,
    
    /**
     * 自定义（通过正则表达式）
     */
    CUSTOM
}