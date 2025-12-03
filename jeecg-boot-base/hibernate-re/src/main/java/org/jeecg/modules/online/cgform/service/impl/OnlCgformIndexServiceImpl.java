//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import lombok.Generated;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.modules.online.cgform.entity.OnlCgformIndex;
import org.jeecg.modules.online.cgform.mapper.OnlCgformHeadMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformIndexMapper;
import org.jeecg.modules.online.cgform.service.IOnlCgformIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("onlCgformIndexServiceImpl")
public class OnlCgformIndexServiceImpl extends ServiceImpl<OnlCgformIndexMapper, OnlCgformIndex> implements IOnlCgformIndexService {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(OnlCgformIndexServiceImpl.class);
    @Autowired
    private OnlCgformHeadMapper onlCgformHeadMapper;

    public void createIndex(String code, String databaseType, String tbname, String synMethod) {
        LambdaQueryWrapper<OnlCgformIndex> query = new LambdaQueryWrapper();
        query.eq(OnlCgformIndex::getCgformHeadId, code);
        List<OnlCgformIndex> indexes = this.list(query);
        if (indexes != null && indexes.size() > 0) {
            for(OnlCgformIndex cgformIndex : indexes) {
                if (!CommonConstant.DEL_FLAG_1.equals(cgformIndex.getDelFlag()) && "N".equals(cgformIndex.getIsDbSynch()) || "force".equalsIgnoreCase(synMethod)) {
                    String sql = "";
                    String indexName = cgformIndex.getIndexName();
                    String indexField = cgformIndex.getIndexField();
                    String indexType = "normal".equals(cgformIndex.getIndexType()) ? " index " : cgformIndex.getIndexType() + " index ";
                    switch (databaseType) {
                        case "MYSQL" -> sql = "create " + indexType + indexName + " on " + tbname + "(" + indexField + ")";
                        case "ORACLE" -> sql = "create " + indexType + indexName + " on " + tbname + "(" + indexField + ")";
                        case "SQLSERVER" -> sql = "create " + indexType + indexName + " on " + tbname + "(" + indexField + ")";
                        case "POSTGRESQL" -> sql = "create " + indexType + indexName + " on " + tbname + "(" + indexField + ")";
                        default -> sql = "create " + indexType + indexName + " on " + tbname + "(" + indexField + ")";
                    }

                    this.onlCgformHeadMapper.executeDDL(sql);
                    cgformIndex.setIsDbSynch("Y");
                    this.updateById(cgformIndex);
                }
            }
        }

    }

    public boolean isExistIndex(String countSql) {
        if (countSql == null) {
            return true;
        } else {
            Integer count = ((OnlCgformIndexMapper)this.baseMapper).queryIndexCount(countSql);
            return count != null && count > 0;
        }
    }

    public List<OnlCgformIndex> getCgformIndexsByCgformId(String cgformId) {
        return ((OnlCgformIndexMapper)this.baseMapper).selectList((Wrapper)(new LambdaQueryWrapper<OnlCgformIndex>()).in(OnlCgformIndex::getCgformHeadId, new Object[]{cgformId}));
    }
}
