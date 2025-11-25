//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import org.jeecg.modules.online.cgform.entity.OnlCgformIndex;

public interface IOnlCgformIndexService extends IService<OnlCgformIndex> {
    void createIndex(String code, String databaseType, String tbname, String synMethod);

    boolean isExistIndex(String countSql);

    List<OnlCgformIndex> getCgformIndexsByCgformId(String cgformId);
}
