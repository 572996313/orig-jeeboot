//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service;

import java.util.List;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceSql;

public interface IOnlCgformEnhanceService {
    List<OnlCgformEnhanceJava> queryEnhanceJavaList(String cgformId);

    void saveEnhanceJava(OnlCgformEnhanceJava onlCgformEnhanceJava);

    void updateEnhanceJava(OnlCgformEnhanceJava onlCgformEnhanceJava);

    void deleteEnhanceJava(String id);

    void deleteBatchEnhanceJava(List<String> id);

    boolean checkOnlyEnhance(OnlCgformEnhanceJava onlCgformEnhanceJava);

    boolean checkOnlyEnhance(OnlCgformEnhanceSql onlCgformEnhanceSql);

    List<OnlCgformEnhanceSql> queryEnhanceSqlList(String cgformId);

    void saveEnhanceSql(OnlCgformEnhanceSql onlCgformEnhanceSql);

    void updateEnhanceSql(OnlCgformEnhanceSql onlCgformEnhanceSql);

    void deleteEnhanceSql(String id);

    void deleteBatchEnhanceSql(List<String> id);
}
