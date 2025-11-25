//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.dirc;

import freemarker.core.TemplateClassResolver;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.Map;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class g {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(g.class);
    private static Configuration b;

    private static String a(String tplName, String encoding, Map<String, Object> paras) {
        try {
            StringWriter swriter = new StringWriter();
            Template mytpl = null;
            mytpl = b.getTemplate(tplName, encoding);
            mytpl.process(paras, swriter);
            return swriter.toString();
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            return e.toString();
        }
    }

    public static String a(String tplName, Map<String, Object> paras) {
        return a(tplName, "utf-8", paras);
    }

    static {
        b = new Configuration(Configuration.VERSION_2_3_28);
        b.setNumberFormat("0.#####################");
        b.setClassForTemplateLoading(g.class, "/");
        b.setNewBuiltinClassResolver(TemplateClassResolver.SAFER_RESOLVER);
    }
}
