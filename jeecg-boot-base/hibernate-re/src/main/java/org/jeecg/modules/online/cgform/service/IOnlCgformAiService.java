//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service;

import org.jeecg.common.api.vo.Result;

public interface IOnlCgformAiService {
    Result<?> genSchema4Modules(String prompt);

    Result<?> genSingleSchema4Modules(String prompt);

    Result<?> aiGenFields(String code, String prompt);

    Result<?> aiGenMockData(String code, Integer count);
}
