//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.online.cgform.converter.b;
import org.jeecg.modules.online.cgform.dird.d;
import org.jeecg.modules.online.cgform.dird.k;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.enums.EnhanceDataEnum;
import org.jeecg.modules.online.cgform.mapper.OnlCgformFieldMapper;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.cgform.service.IOnlCgformSqlService;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("onlCgformSqlServiceImpl")
public class OnlCgformSqlServiceImpl implements IOnlCgformSqlService {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(OnlCgformSqlServiceImpl.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    private IOnlCgformHeadService onlCgformHeadService;

    public void saveBatchOnlineTable(OnlCgformHead head, List<OnlCgformField> fieldList, List<Map<String, Object>> dataList) throws BusinessException {
        SqlSession batchSqlSession = null;
        int errorLine = 0;

        try {
            b.a(2, dataList, fieldList);
            batchSqlSession = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            OnlCgformFieldMapper mapper = (OnlCgformFieldMapper)batchSqlSession.getMapper(OnlCgformFieldMapper.class);
            int batchCount = 1000;
            if (batchCount >= dataList.size()) {
                for(int index = 0; index < dataList.size(); ++errorLine) {
                    String jsonStr = JSON.toJSONString(dataList.get(index));
                    this.a(jsonStr, head, fieldList, mapper);
                    ++index;
                }
            } else {
                for(int index = 0; index < dataList.size(); ++errorLine) {
                    String jsonStr = JSON.toJSONString(dataList.get(index));
                    this.a(jsonStr, head, fieldList, mapper);
                    if (index % batchCount == 0) {
                        batchSqlSession.commit();
                        batchSqlSession.clearCache();
                    }

                    ++index;
                }
            }

            batchSqlSession.commit();
        } catch (Exception e) {
            batchSqlSession.rollback();
            String errorMsg = "第" + errorLine + "行" + this.a((Throwable)e);
            throw new BusinessException(errorMsg);
        } finally {
            batchSqlSession.close();
        }

    }

    private String a(Throwable e) {
        return e.getCause() != null ? this.a(e.getCause()) : e.getMessage();
    }

    public void saveOrUpdateSubData(String subDataJsonStr, OnlCgformHead head, List<OnlCgformField> subFiledList) throws BusinessException {
        OnlCgformFieldMapper mapper = (OnlCgformFieldMapper)SpringContextUtils.getBean(OnlCgformFieldMapper.class);
        this.a(subDataJsonStr, head, subFiledList, mapper);
    }

    public Map<String, String> saveOnlineImportDataWithValidate(OnlCgformHead head, List<OnlCgformField> fieldList, List<Map<String, Object>> dataList) {
        StringBuffer errorMessage = new StringBuffer();
        k validator = new k(fieldList);
        OnlCgformFieldMapper mapper = (OnlCgformFieldMapper)SpringContextUtils.getBean(OnlCgformFieldMapper.class);
        int rowIndex = 0;
        int errorLength = 0;
        b.a(2, dataList, fieldList);
        int all = dataList.size();

        for(int index = 0; index < all; ++index) {
            String jsonStr = JSON.toJSONString(dataList.get(index));
            ++rowIndex;
            String error = validator.a(jsonStr, rowIndex);
            if (error == null) {
                try {
                    this.a(jsonStr, head, fieldList, mapper);
                } catch (Exception e) {
                    ++errorLength;
                    String tempErrorMsg = null;
                    if (e.getCause() != null) {
                        tempErrorMsg = this.a(e.getCause().getMessage());
                    } else {
                        tempErrorMsg = this.a(e.getMessage());
                    }

                    String rowError = k.b(tempErrorMsg, rowIndex);
                    errorMessage.append(rowError);
                }
            } else {
                ++errorLength;
                errorMessage.append(error);
            }
        }

        Map map = new HashMap(5);
        map.put("error", errorMessage.toString());
        map.put("tip", k.a(all, errorLength));
        return map;
    }

    private void a(String dataJsonStr, OnlCgformHead head, List<OnlCgformField> fieldList, OnlCgformFieldMapper mapper) throws BusinessException {
        JSONObject importData = JSONObject.parseObject(dataJsonStr);
        EnhanceDataEnum flag = this.onlCgformHeadService.executeEnhanceImport(head, importData);
        String tableName = head.getTableName();
        if (EnhanceDataEnum.INSERT == flag) {
            Map map = d.a(tableName, fieldList, importData);
            mapper.executeInsertSQL(map);
        } else if (EnhanceDataEnum.UPDATE == flag) {
            Map map = d.b(tableName, fieldList, importData);
            mapper.executeUpdatetSQL(map);
        } else if (EnhanceDataEnum.ABANDON == flag) {
        }

    }

    private String a(String meta) {
        String regex = "^Duplicate entry \\'(.*)\\' for key .*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(meta);
        return m.find() ? "重复数据" + m.group(1) : meta;
    }
}
