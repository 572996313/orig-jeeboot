//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dird;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class e {
    @Generated
    private static final Logger e = LoggerFactory.getLogger(e.class);
    private static final String f = "setup,beforeSubmit,beforeAdd,beforeEdit,afterAdd,afterEdit,beforeDelete,afterDelete,mounted,created,show,loaded";
    private static final String g = "\\}\\s*\r*\n*\\s*";
    private static final String h = ",";
    public static final Pattern a = Pattern.compile("^import\\s+(.*)\\s+from\\s+(['\"].*['\"])[;]?$");
    public static final String b = "import";
    public static final String c = "customImport";
    public static final String d = "_hook";

    public static String a(String enhanceJs, String buttonCode) {
        String patternStr = "(" + buttonCode + "\\s*\\(row\\)\\s*\\{)";
        String replaceStr = buttonCode + ":function(that,row){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction,useMessage = this._useMessage;";
        String temp = b(enhanceJs, "\\}\\s*\r*\n*\\s*" + patternStr, "}," + replaceStr);
        if (temp == null) {
            enhanceJs = c(enhanceJs, patternStr, replaceStr);
        } else {
            enhanceJs = temp;
        }

        enhanceJs = a((String)enhanceJs, buttonCode, (String)null);
        return enhanceJs;
    }

    public static String a(String enhanceJs, String buttonCode, String prefixPattern) {
        String var10000 = oConvertUtils.getString(prefixPattern);
        String patternStr = "(" + var10000 + buttonCode + "\\s*\\(\\)\\s*\\{)";
        String replaceStr = buttonCode + ":function(that){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction,useMessage = this._useMessage;";
        String temp = b(enhanceJs, "\\}\\s*\r*\n*\\s*" + patternStr, "}," + replaceStr);
        if (temp == null) {
            enhanceJs = c(enhanceJs, patternStr, replaceStr);
        } else {
            enhanceJs = temp;
        }

        return enhanceJs;
    }

    public static String b(String enhanceJs, String patternStr, String replaceStr) {
        Pattern p = Pattern.compile(patternStr);
        Matcher m = p.matcher(enhanceJs);
        if (m.find()) {
            enhanceJs = enhanceJs.replace(m.group(0), replaceStr);
            return enhanceJs;
        } else {
            return null;
        }
    }

    public static String c(String enhanceJs, String patternStr, String replaceStr) {
        String temp = b(enhanceJs, patternStr, replaceStr);
        return temp != null ? temp : enhanceJs;
    }

    public static String a(String enhanceJs, List<OnlCgformButton> cgButtonList) {
        return "class OnlineEnhanceJs{constructor(getAction,postAction,deleteAction){this._getAction=getAction;this._postAction=postAction;this._deleteAction=deleteAction;}" + enhanceJs + "}";
    }

    public static String b(String enhanceJs, String fieldName) {
        String patternStr = "([ \\t]+" + fieldName + "\\s*\\(\\)\\s*\\{)";
        String replaceStr = fieldName + ":function(that,event){";
        String temp = b(enhanceJs, "\\}\\s*\r*\n*\\s*" + patternStr, "}," + replaceStr);
        if (temp == null) {
            enhanceJs = c(enhanceJs, patternStr, replaceStr);
        } else {
            enhanceJs = temp;
        }

        return enhanceJs;
    }

    public static String a(String enhanceJs) {
        String theLastString = "function OnlineEnhanceJs(getAction,postAction,deleteAction){return {_getAction:getAction,_postAction:postAction,_deleteAction:deleteAction," + enhanceJs + "}}";
        return theLastString;
    }

    public static String b(String enhanceJs, List<OnlCgformButton> cgButtonList) {
        enhanceJs = c(enhanceJs, cgButtonList);
        String theLastString = "function OnlineEnhanceJs(getAction,postAction,deleteAction){return {_getAction:getAction,_postAction:postAction,_deleteAction:deleteAction," + enhanceJs + "}}";
        return theLastString;
    }

    public static String c(String enhanceJs, List<OnlCgformButton> cgButtonList) {
        enhanceJs = b(enhanceJs);
        if (cgButtonList != null) {
            for(OnlCgformButton button : cgButtonList) {
                String buttonCode = button.getButtonCode();
                if ("link".equals(button.getButtonStyle())) {
                    enhanceJs = a(enhanceJs, buttonCode);
                    enhanceJs = a(enhanceJs, buttonCode + "_hook");
                } else if ("button".equals(button.getButtonStyle()) || "form".equals(button.getButtonStyle())) {
                    enhanceJs = a((String)enhanceJs, buttonCode, (String)null);
                    enhanceJs = a((String)enhanceJs, buttonCode + "_hook", (String)null);
                }
            }
        }

        for(String temp : "setup,beforeSubmit,beforeAdd,beforeEdit,afterAdd,afterEdit,beforeDelete,afterDelete,mounted,created,show,loaded".split(",")) {
            if ("setup,beforeAdd,afterAdd,mounted,created,show,loaded".indexOf(temp) >= 0) {
                enhanceJs = a((String)enhanceJs, temp, (String)null);
            } else {
                enhanceJs = a(enhanceJs, temp);
            }
        }

        return enhanceJs;
    }

    public static void a(OnlCgformEnhanceJs onlCgformEnhanceJs, String tbname, List<OnlCgformField> fieldList) {
        if (onlCgformEnhanceJs != null && !oConvertUtils.isEmpty(onlCgformEnhanceJs.getCgJs())) {
            String enhanceJs = " " + onlCgformEnhanceJs.getCgJs();
            e.debug("one enhanceJs begin==> " + enhanceJs);
            Pattern p = Pattern.compile("(\\s{1}onlChange\\s*\\(\\)\\s*\\{)");
            Matcher m = p.matcher(enhanceJs);
            if (m.find()) {
                e.debug("---JS 增强转换-main--enhanceJsFunctionName----onlChange");
                enhanceJs = a(enhanceJs, "onlChange", "\\s{1}");

                for(OnlCgformField field : fieldList) {
                    enhanceJs = b(enhanceJs, field.getDbFieldName());
                }
            }

            e.debug("one enhanceJs end==> " + enhanceJs);
            onlCgformEnhanceJs.setCgJs(enhanceJs);
        }
    }

    public static void b(OnlCgformEnhanceJs onlCgformEnhanceJs, String tbname, List<OnlCgformField> fieldList) {
        if (onlCgformEnhanceJs != null && !oConvertUtils.isEmpty(onlCgformEnhanceJs.getCgJs())) {
            String enhanceJs = onlCgformEnhanceJs.getCgJs();
            String enhanceJsFunctionName = tbname + "_onlChange";
            Pattern p = Pattern.compile("(" + enhanceJsFunctionName + "\\s*\\(\\)\\s*\\{)");
            Matcher m = p.matcher(enhanceJs);
            if (m.find()) {
                enhanceJs = a((String)enhanceJs, enhanceJsFunctionName, (String)null);

                for(OnlCgformField field : fieldList) {
                    enhanceJs = b(enhanceJs, field.getDbFieldName());
                }
            }

            onlCgformEnhanceJs.setCgJs(enhanceJs);
        }
    }

    private static String b(String js) {
        String lineBreak = "\n";
        String[] arr = js.split(lineBreak);

        for(int i = 0; i < arr.length; ++i) {
            String line = arr[i].trim();
            if (line.startsWith("import")) {
                Matcher matcher = a.matcher(line);
                if (matcher.find()) {
                    String str = String.format("const %s = %s(%s)", matcher.group(1), "customImport", matcher.group(2));
                    arr[i] = arr[i].replace(line, str);
                }
            }
        }

        return String.join(lineBreak, arr);
    }
}
