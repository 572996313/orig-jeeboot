//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.List;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceSql;
import org.jeecg.modules.online.cgform.mapper.OnlCgformEnhanceJavaMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformEnhanceSqlMapper;
import org.jeecg.modules.online.cgform.service.IOnlCgformEnhanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("onlCgformEnhanceService")
public class b implements IOnlCgformEnhanceService {
    @Autowired
    private OnlCgformEnhanceJavaMapper onlCgformEnhanceJavaMapper;
    @Autowired
    private OnlCgformEnhanceSqlMapper onlCgformEnhanceSqlMapper;

    public List<OnlCgformEnhanceJava> queryEnhanceJavaList(String cgformId) {
        LambdaQueryWrapper<OnlCgformEnhanceJava> query = (LambdaQueryWrapper)(new LambdaQueryWrapper<OnlCgformEnhanceJava>()).eq(OnlCgformEnhanceJava::getCgformHeadId, cgformId);
        List ls = this.onlCgformEnhanceJavaMapper.selectList(query);
        return ls;
    }

    public void saveEnhanceJava(OnlCgformEnhanceJava onlCgformEnhanceJava) {
        this.onlCgformEnhanceJavaMapper.insert(onlCgformEnhanceJava);
    }

    public void updateEnhanceJava(OnlCgformEnhanceJava onlCgformEnhanceJava) {
        this.onlCgformEnhanceJavaMapper.updateById(onlCgformEnhanceJava);
    }

    public void deleteEnhanceJava(String id) {
        this.onlCgformEnhanceJavaMapper.deleteById(id);
    }

    public void deleteBatchEnhanceJava(List<String> idList) {
        this.onlCgformEnhanceJavaMapper.deleteBatchIds(idList);
    }

    public boolean checkOnlyEnhance(OnlCgformEnhanceJava onlCgformEnhanceJava) {
        LambdaQueryWrapper<OnlCgformEnhanceJava> query = new LambdaQueryWrapper();
        query.eq(OnlCgformEnhanceJava::getButtonCode, onlCgformEnhanceJava.getButtonCode());
        query.eq(OnlCgformEnhanceJava::getCgformHeadId, onlCgformEnhanceJava.getCgformHeadId());
        query.eq(OnlCgformEnhanceJava::getEvent, onlCgformEnhanceJava.getEvent());
        Long count = this.onlCgformEnhanceJavaMapper.selectCount(query);
        if (count != null) {
            if (count == 1L && oConvertUtils.isEmpty(onlCgformEnhanceJava.getId())) {
                return false;
            }

            if (count == 2L) {
                return false;
            }
        }

        return true;
    }

    public boolean checkOnlyEnhance(OnlCgformEnhanceSql onlCgformEnhanceSql) {
        LambdaQueryWrapper<OnlCgformEnhanceSql> query = new LambdaQueryWrapper();
        query.eq(OnlCgformEnhanceSql::getButtonCode, onlCgformEnhanceSql.getButtonCode());
        query.eq(OnlCgformEnhanceSql::getCgformHeadId, onlCgformEnhanceSql.getCgformHeadId());
        Long count = this.onlCgformEnhanceSqlMapper.selectCount(query);
        if (count != null) {
            if (count == 1L && oConvertUtils.isEmpty(onlCgformEnhanceSql.getId())) {
                return false;
            }

            if (count > 1L) {
                return false;
            }
        }

        return true;
    }

    public List<OnlCgformEnhanceSql> queryEnhanceSqlList(String cgformId) {
        LambdaQueryWrapper<OnlCgformEnhanceSql> query = (LambdaQueryWrapper)(new LambdaQueryWrapper<OnlCgformEnhanceSql>()).eq(OnlCgformEnhanceSql::getCgformHeadId, cgformId);
        query.orderByDesc(OnlCgformEnhanceSql::getId);
        List ls = this.onlCgformEnhanceSqlMapper.selectList(query);
        return ls;
    }

    public void saveEnhanceSql(OnlCgformEnhanceSql onlCgformEnhanceSql) {
        this.onlCgformEnhanceSqlMapper.insert(onlCgformEnhanceSql);
    }

    public void updateEnhanceSql(OnlCgformEnhanceSql onlCgformEnhanceSql) {
        this.onlCgformEnhanceSqlMapper.updateById(onlCgformEnhanceSql);
    }

    public void deleteEnhanceSql(String id) {
        this.onlCgformEnhanceSqlMapper.deleteById(id);
    }

    public void deleteBatchEnhanceSql(List<String> idList) {
        this.onlCgformEnhanceSqlMapper.deleteBatchIds(idList);
    }
}
