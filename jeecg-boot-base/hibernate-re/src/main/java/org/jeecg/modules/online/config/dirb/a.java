//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.dirb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jeecg.common.util.security.AbstractQueryBlackListHandler;
import org.jeecgframework.minidao.util.MiniDaoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component("onlReportQueryBlackListHandler")
public class a extends AbstractQueryBlackListHandler {
    protected List<AbstractQueryBlackListHandler.QueryTable> getQueryTableInfo(String sql) {
        List tables = MiniDaoUtil.getQueryTableInfo(sql);
        if (CollectionUtils.isEmpty(tables)) {
            return Collections.emptyList();
        } else {
            List list = new ArrayList(tables.size());

            for(Object obj : tables) {
                AbstractQueryBlackListHandler.QueryTable item = new AbstractQueryBlackListHandler.QueryTable();
//                AbstractQueryBlackListHandler.QueryTable item = new AbstractQueryBlackListHandler.QueryTable(this);
                BeanUtils.copyProperties(obj, item);
                list.add(item);
            }

            return list;
        }
    }
}
