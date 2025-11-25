//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.service;

import org.jeecg.common.api.vo.Result;

public interface IOnlCgreportAiService {
    Result<?> genCgreportByCgform(String cgformTableName, String prompt);
}
