//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.enums;

import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum DataBaseEnum {
    MYSQL("MYSQL", "1"),
    MYSQL_57("MYSQL5.7+", "4"),
    ORACLE("ORACLE", "2"),
    SQLSERVER("SQLSERVER", "3"),
    MARIADB("MARIADB", "5"),
    POSTGRESQL("POSTGRESQL", "6"),
    DA_MENG("DA_MENG", "7"),
    REN_DA_JIN_CANG("REN_DA_JIN_CANG", "8"),
    SHEN_TONG("SHEN_TONG", "9"),
    SQL_LITE("SQL_LITE", "10");

    @Generated
    private static final Logger log = LoggerFactory.getLogger(DataBaseEnum.class);
    private String name;
    private String value;

    private DataBaseEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static String getDataBaseNameByValue(String value) {
        for(DataBaseEnum e : values()) {
            if (e.value.equals(value)) {
                return e.name;
            }
        }

        log.warn("不识别的数据库类型:{}，已自动转为默认数据库类型:{}", value, MYSQL.name);
        return MYSQL.name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
