package org.jeecg.config.desensitization;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据脱敏配置属性
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
@ConfigurationProperties(prefix = "jeecg.desensitization")
public class JeecgDesensitizationProperties {

    /**
     * 是否启用数据脱敏功能
     */
    private Boolean enabled = true;

    /**
     * 脱敏策略：mask(掩码)、encrypt(加密)
     */
    private String strategy = "mask";

    /**
     * 是否启用JSON序列化脱敏
     */
    private Boolean jsonEnabled = true;

    /**
     * 是否启用方法返回值脱敏
     */
    private Boolean methodEnabled = true;

    /**
     * 加密配置
     */
    private EncryptConfig encrypt = new EncryptConfig();

    /**
     * 掩码配置
     */
    private MaskConfig mask = new MaskConfig();

    /**
     * 日志配置
     */
    private LogConfig log = new LogConfig();

    /**
     * 加密配置
     */
    public static class EncryptConfig {
        /**
         * 加密算法：AES、DES、SM4
         */
        private String algorithm = "AES";

        /**
         * 加密密钥（需要配置在环境变量中）
         */
        private String key;

        /**
         * 加密向量（AES CBC模式需要）
         */
        private String iv;

        /**
         * 是否使用Base64编码加密结果
         */
        private Boolean base64Encode = true;

        public String getAlgorithm() {
            return algorithm;
        }

        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getIv() {
            return iv;
        }

        public void setIv(String iv) {
            this.iv = iv;
        }

        public Boolean getBase64Encode() {
            return base64Encode;
        }

        public void setBase64Encode(Boolean base64Encode) {
            this.base64Encode = base64Encode;
        }
    }

    /**
     * 掩码配置
     */
    public static class MaskConfig {
        /**
         * 手机号掩码规则
         */
        private String phonePattern = "^(\\d{3})\\d{4}(\\d{4})$";
        private String phoneReplacement = "$1****$2";

        /**
         * 身份证号掩码规则
         */
        private String idCardPattern = "^(\\d{6})\\d{8}(\\d{4})$";
        private String idCardReplacement = "$1********$2";

        /**
         * 邮箱掩码规则
         */
        private String emailPattern = "^(\\w{1,3})\\w+(\\w{1}@.*)$";
        private String emailReplacement = "$1***$2";

        /**
         * 银行卡号掩码规则
         */
        private String bankCardPattern = "^(\\d{4})\\d+(\\d{4})$";
        private String bankCardReplacement = "$1****$2";

        /**
         * 姓名掩码规则（保留姓，隐藏名）
         */
        private String namePattern = "^(.)(.+)$";
        private String nameReplacement = "$1*";

        /**
         * 地址掩码规则（保留前6位）
         */
        private String addressPattern = "^(.{6})(.+)$";
        private String addressReplacement = "$1****";

        // Getters and Setters
        public String getPhonePattern() {
            return phonePattern;
        }

        public void setPhonePattern(String phonePattern) {
            this.phonePattern = phonePattern;
        }

        public String getPhoneReplacement() {
            return phoneReplacement;
        }

        public void setPhoneReplacement(String phoneReplacement) {
            this.phoneReplacement = phoneReplacement;
        }

        public String getIdCardPattern() {
            return idCardPattern;
        }

        public void setIdCardPattern(String idCardPattern) {
            this.idCardPattern = idCardPattern;
        }

        public String getIdCardReplacement() {
            return idCardReplacement;
        }

        public void setIdCardReplacement(String idCardReplacement) {
            this.idCardReplacement = idCardReplacement;
        }

        public String getEmailPattern() {
            return emailPattern;
        }

        public void setEmailPattern(String emailPattern) {
            this.emailPattern = emailPattern;
        }

        public String getEmailReplacement() {
            return emailReplacement;
        }

        public void setEmailReplacement(String emailReplacement) {
            this.emailReplacement = emailReplacement;
        }

        public String getBankCardPattern() {
            return bankCardPattern;
        }

        public void setBankCardPattern(String bankCardPattern) {
            this.bankCardPattern = bankCardPattern;
        }

        public String getBankCardReplacement() {
            return bankCardReplacement;
        }

        public void setBankCardReplacement(String bankCardReplacement) {
            this.bankCardReplacement = bankCardReplacement;
        }

        public String getNamePattern() {
            return namePattern;
        }

        public void setNamePattern(String namePattern) {
            this.namePattern = namePattern;
        }

        public String getNameReplacement() {
            return nameReplacement;
        }

        public void setNameReplacement(String nameReplacement) {
            this.nameReplacement = nameReplacement;
        }

        public String getAddressPattern() {
            return addressPattern;
        }

        public void setAddressPattern(String addressPattern) {
            this.addressPattern = addressPattern;
        }

        public String getAddressReplacement() {
            return addressReplacement;
        }

        public void setAddressReplacement(String addressReplacement) {
            this.addressReplacement = addressReplacement;
        }
    }

    /**
     * 日志配置
     */
    public static class LogConfig {
        /**
         * 是否打印脱敏日志
         */
        private Boolean enabled = false;

        /**
         * 日志级别：DEBUG、INFO、WARN
         */
        private String level = "DEBUG";

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }

    // Main Getters and Setters
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public Boolean getJsonEnabled() {
        return jsonEnabled;
    }

    public void setJsonEnabled(Boolean jsonEnabled) {
        this.jsonEnabled = jsonEnabled;
    }

    public Boolean getMethodEnabled() {
        return methodEnabled;
    }

    public void setMethodEnabled(Boolean methodEnabled) {
        this.methodEnabled = methodEnabled;
    }

    public EncryptConfig getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(EncryptConfig encrypt) {
        this.encrypt = encrypt;
    }

    public MaskConfig getMask() {
        return mask;
    }

    public void setMask(MaskConfig mask) {
        this.mask = mask;
    }

    public LogConfig getLog() {
        return log;
    }

    public void setLog(LogConfig log) {
        this.log = log;
    }
}