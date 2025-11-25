//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.converter.dira;

import java.util.List;
import java.util.Map;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.FieldCommentConverter;

public class b implements FieldCommentConverter {
    protected String a;
    protected List<DictModel> b;

    public String getFiled() {
        return this.a;
    }

    public void setFiled(String filed) {
        this.a = filed;
    }

    public List<DictModel> getDictList() {
        return this.b;
    }

    public void setDictList(List<DictModel> dictList) {
        this.b = dictList;
    }

    public String converterToVal(String txt) {
        if (oConvertUtils.isNotEmpty(txt)) {
            for(DictModel model : this.b) {
                if (model.getText().equals(txt)) {
                    return model.getValue();
                }
            }
        }

        return null;
    }

    public String converterToTxt(String val) {
        if (oConvertUtils.isNotEmpty(val)) {
            for(DictModel model : this.b) {
                if (model.getValue() != null && model.getValue().equals(val)) {
                    return model.getText();
                }
            }
        }

        return null;
    }

    public Map<String, String> getConfig() {
        return null;
    }
}
