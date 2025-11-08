package org.jeecg.common.desensitization.util;

import org.jeecg.common.desensitization.enums.SensitiveType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 敏感信息脱敏工具类
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
public class SensitiveInfoUtil {

    /**
     * 手机号正则
     */
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(\\d{3})\\d{4}(\\d{4})$");
    
    /**
     * 身份证号正则
     */
    private static final Pattern ID_CARD_PATTERN = Pattern.compile("^(\\d{6})\\d{8}(\\d{4})$");
    
    /**
     * 邮箱正则
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(\\w{1,3})\\w+(\\w{1}@.*)$");
    
    /**
     * 银行卡号正则
     */
    private static final Pattern BANK_CARD_PATTERN = Pattern.compile("^(\\d{4})\\d+(\\d{4})$");

    /**
     * 根据敏感类型进行脱敏
     */
    public static String desensitize(String value, SensitiveType type) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        
        switch (type) {
            case CHINESE_NAME:
                return desensitizeName(value);
            case ID_CARD:
                return desensitizeIdCard(value);
            case MOBILE_PHONE:
                return desensitizePhone(value);
            case FIXED_PHONE:
                return desensitizeFixedPhone(value);
            case ADDRESS:
                return desensitizeAddress(value);
            case EMAIL:
                return desensitizeEmail(value);
            case BANK_CARD:
                return desensitizeBankCard(value);
            case PASSWORD:
                return "******";
            case CAR_LICENSE:
                return desensitizeCarLicense(value);
            default:
                return value;
        }
    }

    /**
     * 【中文姓名】只显示第一个汉字，其他隐藏为星号
     * 例如：李**
     */
    public static String desensitizeName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1) + "*".repeat(Math.max(0, name.length() - 1));
    }

    /**
     * 【身份证号】显示前6位和后4位，中间用*号隐藏
     * 例如：110000********1234
     */
    public static String desensitizeIdCard(String idCard) {
        if (idCard == null || idCard.length() < 15) {
            return idCard;
        }
        Matcher matcher = ID_CARD_PATTERN.matcher(idCard);
        if (matcher.find()) {
            return matcher.replaceAll("$1********$2");
        }
        return idCard;
    }

    /**
     * 【手机号码】显示前三位和后四位，中间用*号隐藏
     * 例如：138****1234
     */
    public static String desensitizePhone(String phone) {
        if (phone == null || phone.length() != 11) {
            return phone;
        }
        Matcher matcher = PHONE_PATTERN.matcher(phone);
        if (matcher.find()) {
            return matcher.replaceAll("$1****$2");
        }
        return phone;
    }

    /**
     * 【座机号】显示前四位和后两位，中间用*号隐藏
     * 例如：0755-****12
     */
    public static String desensitizeFixedPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return phone;
        }
        if (phone.length() < 7) {
            return phone;
        }
        return phone.substring(0, 4) + "****" + phone.substring(phone.length() - 2);
    }

    /**
     * 【地址】只显示前6位，其余用*号隐藏
     * 例如：北京市海淀区****
     */
    public static String desensitizeAddress(String address) {
        if (address == null || address.isEmpty()) {
            return address;
        }
        int length = address.length();
        if (length <= 6) {
            return address;
        }
        return address.substring(0, 6) + "****";
    }

    /**
     * 【电子邮箱】邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示
     * 例如：g**@163.com
     */
    public static String desensitizeEmail(String email) {
        if (email == null || email.isEmpty()) {
            return email;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (matcher.find()) {
            return matcher.replaceAll("$1***$2");
        }
        return email;
    }

    /**
     * 【银行卡号】显示前四位和后四位，中间用*号隐藏
     * 例如：6222 **** **** 1234
     */
    public static String desensitizeBankCard(String bankCard) {
        if (bankCard == null || bankCard.length() < 9) {
            return bankCard;
        }
        Matcher matcher = BANK_CARD_PATTERN.matcher(bankCard);
        if (matcher.find()) {
            return matcher.replaceAll("$1****$2");
        }
        return bankCard;
    }

    /**
     * 【车牌号】显示前2位和后1位，中间用*号隐藏
     * 例如：京A****D
     */
    public static String desensitizeCarLicense(String carLicense) {
        if (carLicense == null || carLicense.length() < 7) {
            return carLicense;
        }
        return carLicense.substring(0, 3) + "****" + carLicense.substring(carLicense.length() - 1);
    }

    /**
     * 【自定义】使用正则表达式进行脱敏
     */
    public static String desensitizeCustom(String value, String regex, String replacement) {
        if (value == null || value.isEmpty() || regex == null || replacement == null) {
            return value;
        }
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(value);
            if (matcher.find()) {
                return matcher.replaceAll(replacement);
            }
        } catch (Exception e) {
            // 正则表达式错误，返回原值
            return value;
        }
        return value;
    }
}