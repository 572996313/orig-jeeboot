//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dird;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.enums.CgformValidPatternEnum;

public class k {
    private Map<String, OnlCgformField> d;
    private Map<String, OnlCgformField> e;
    private static final String f = ",";
    private static final String g = "第%s行校验信息:";
    private static final String h = "总上传行数:%s,已导入行数:%s,错误行数:%s";
    public static final String a = "error";
    public static final String b = "tip";
    public static final String c = "filePath";

    public k() {
    }

    public k(List<OnlCgformField> fieldList) {
        this.d = new HashMap(5);
        this.e = new HashMap(5);

        for(OnlCgformField field : fieldList) {
            String validateType = field.getFieldValidType();
            if (validateType != null && !"".equals(validateType) && !CgformValidPatternEnum.ONLY.getType().equals(validateType)) {
                if (CgformValidPatternEnum.NOTNULL.getType().equals(validateType)) {
                    this.e.put(field.getDbFieldName(), field);
                } else {
                    this.d.put(field.getDbFieldName(), field);
                }
            }

            if ((field.getDbIsNull() == 0 || "1".equals(field.getFieldMustInput())) && oConvertUtils.isEmpty(field.getDbDefaultVal())) {
                this.e.put(field.getDbFieldName(), field);
            }
        }

    }

    public String a(String dataString, int index) {
        StringBuffer msg = new StringBuffer();
        JSONObject json = JSON.parseObject(dataString);

        for(String key : this.e.keySet()) {
            String value = json.getString(key);
            OnlCgformField field = (OnlCgformField)this.e.get(key);
            if (value == null || "".equals(value)) {
                String var10001 = field.getDbFieldTxt();
                msg.append(var10001 + CgformValidPatternEnum.NOTNULL.getMsg() + ",");
            }
        }

        for(String key : this.d.keySet()) {
            String value = json.getString(key);
            OnlCgformField field = (OnlCgformField)this.d.get(key);
            String validateType = field.getFieldValidType();
            if (value != null && !"".equals(value)) {
                String regex = null;
                String typeEnumMsg = null;
                if (CgformValidPatternEnum.INTEGER.getType().equals(validateType)) {
                    regex = "^-?[1-9]\\d*$";
                    typeEnumMsg = "请输入整数";
                } else {
                    CgformValidPatternEnum typeEnum = CgformValidPatternEnum.getPatternInfoByType(validateType);
                    if (typeEnum == null) {
                        regex = validateType;
                        typeEnumMsg = "校验【" + validateType + "】未通过";
                    } else {
                        regex = typeEnum.getPattern();
                        typeEnumMsg = typeEnum.getMsg();
                    }
                }

                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(value);
                if (!m.find()) {
                    String var21 = field.getDbFieldTxt();
                    msg.append(var21 + typeEnumMsg + ",");
                }
            }
        }

        if (msg.length() > 0) {
            return b(msg.toString(), index);
        } else {
            return null;
        }
    }

    public static String b(String msg, int index) {
        String var10000 = String.format("第%s行校验信息:", index);
        return var10000 + msg + "\r\n";
    }

    public static String a(int all, int error) {
        int success = all - error;
        return String.format("总上传行数:%s,已导入行数:%s,错误行数:%s", all, success, error);
    }
}
