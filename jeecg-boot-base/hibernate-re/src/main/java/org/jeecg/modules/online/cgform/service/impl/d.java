//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Generated;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.constant.enums.CgformEnum;
import org.jeecg.common.exception.JeecgBootBizTipException;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.util.*;
import org.jeecg.modules.online.auth.entity.OnlAuthData;
import org.jeecg.modules.online.auth.entity.OnlAuthPage;
import org.jeecg.modules.online.auth.entity.OnlAuthRelation;
import org.jeecg.modules.online.auth.service.IOnlAuthDataService;
import org.jeecg.modules.online.auth.service.IOnlAuthPageService;
import org.jeecg.modules.online.auth.service.IOnlAuthRelationService;
import org.jeecg.modules.online.cgform.dird.e;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaImportInter;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaListInter;
import org.jeecg.modules.online.cgform.enhance.impl.http.a;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceSql;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.entity.OnlCgformIndex;
import org.jeecg.modules.online.cgform.enums.EnhanceDataEnum;
import org.jeecg.modules.online.cgform.mapper.OnlCgformButtonMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformEnhanceJavaMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformEnhanceJsMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformEnhanceSqlMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformHeadMapper;
import org.jeecg.modules.online.cgform.model.TreeSelectColumn;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.cgform.service.IOnlCgformIndexService;
import org.jeecg.modules.online.config.dirc.c;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.jeecg.modules.online.config.model.b;
import org.jeecg.modules.online.config.service.DbTableHandleI;
import org.jeecgframework.codegenerate.database.DbReadTableUtil;
import org.jeecgframework.codegenerate.generate.impl.CodeGenerateOne;
import org.jeecgframework.codegenerate.generate.impl.CodeGenerateOneToMany;
import org.jeecgframework.codegenerate.generate.pojo.ColumnVo;
import org.jeecgframework.codegenerate.generate.pojo.TableVo;
import org.jeecgframework.codegenerate.generate.pojo.onetomany.MainTableVo;
import org.jeecgframework.codegenerate.generate.pojo.onetomany.SubTableVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service("onlCgformHeadServiceImpl")
public class d extends ServiceImpl<OnlCgformHeadMapper, OnlCgformHead> implements IOnlCgformHeadService {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(d.class);
    @Autowired
    private IOnlCgformFieldService fieldService;
    @Autowired
    private IOnlCgformIndexService indexService;
    @Autowired
    private OnlCgformEnhanceJsMapper onlCgformEnhanceJsMapper;
    @Autowired
    private OnlCgformButtonMapper onlCgformButtonMapper;
    @Autowired
    private OnlCgformEnhanceJavaMapper onlCgformEnhanceJavaMapper;
    @Autowired
    private OnlCgformEnhanceSqlMapper onlCgformEnhanceSqlMapper;
    @Autowired
    private IOnlCgformFieldService onlCgformFieldService;
    @Autowired
    private b dataBaseConfig;
    @Autowired
    private IOnlAuthPageService onlAuthPageService;
    @Autowired
    private IOnlAuthDataService onlAuthDataService;
    @Autowired
    private IOnlAuthRelationService onlAuthRelationService;
    @Autowired
    private a cgformEnhanceJavaHttp;
    @Autowired
    private org.jeecg.modules.online.cgform.enhance.impl.http.b cgformEnhanceJavaListHttp;
    @Value("${jeecg.online.datasource:}")
    private String onlineDatasource;
    @Lazy
    @Autowired
    private ISysBaseAPI sysBaseApi;

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public Result<?> addAll(org.jeecg.modules.online.cgform.model.a model) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        OnlCgformHead head = model.getHead();
        List fields = model.getFields();
        List<OnlCgformIndex> indexs = model.getIndexs();
        head.setId(uuid);
        boolean hasForeignKey = false;

        for(int i = 0; i < fields.size(); ++i) {
            OnlCgformField field = (OnlCgformField)fields.get(i);
            field.setId((String)null);
            field.setCgformHeadId(uuid);
            if (field.getOrderNum() == null) {
                field.setOrderNum(i);
            }

            if (oConvertUtils.isNotEmpty(field.getMainTable()) && oConvertUtils.isNotEmpty(field.getMainField())) {
                hasForeignKey = true;
            }

            this.a(field);
            if (field.getDbIsPersist() == null) {
                field.setDbIsPersist(org.jeecg.modules.online.cgform.dirb.b.b);
            }
        }

        for(OnlCgformIndex index : indexs) {
            index.setId((String)null);
            index.setCgformHeadId(uuid);
            index.setIsDbSynch("N");
            index.setDelFlag(CommonConstant.DEL_FLAG_0);
        }

        head.setIsDbSynch("N");
        head.setQueryMode("single");
        head.setTableVersion(1);
        head.setCopyType(0);
        if (head.getTableType() == 3 && head.getTabOrderNum() == null) {
            head.setTabOrderNum(1);
        }

        super.save(head);
        this.fieldService.saveBatch(fields);
        this.indexService.saveBatch(indexs);
        this.a(head, fields);
        if (head.getTableType() == 3 && hasForeignKey) {
            this.onlCgformFieldService.clearCacheOnlineConfig();
        }

        return Result.ok("添加成功");
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public Result<?> editAll(org.jeecg.modules.online.cgform.model.a model) {
        OnlCgformHead head = model.getHead();
        OnlCgformHead headEntity = (OnlCgformHead)super.getById(head.getId());
        if (headEntity == null) {
            return Result.error("未找到对应实体");
        } else {
            String isDbSync = headEntity.getIsDbSynch();
            if (org.jeecg.modules.online.cgform.dird.d.a(headEntity, head)) {
                isDbSync = "N";
            }

            Integer version = headEntity.getTableVersion();
            if (version == null) {
                version = 1;
            }

            head.setTableVersion(version + 1);
            List<OnlCgformField> fields = model.getFields();
            List<OnlCgformIndex> indexs = model.getIndexs();
            List<OnlCgformField> addFields = new ArrayList();
            List<OnlCgformField> editFields = new ArrayList();

            for(OnlCgformField field : fields) {
                String id = String.valueOf(field.getId());
                this.a(field);
                if (id.length() == 32) {
                    editFields.add(field);
                } else {
                    String primaryKey = "_pk";
                    if (!primaryKey.equals(id)) {
                        field.setId((String)null);
                        field.setCgformHeadId(head.getId());
                        addFields.add(field);
                    }
                }

                if (field.getDbIsPersist() == null) {
                    field.setDbIsPersist(org.jeecg.modules.online.cgform.dirb.b.b);
                }
            }

            if (addFields.size() > 0 && this.a(addFields)) {
                isDbSync = "N";
            }

            int maxOrderNum = 0;

            for(OnlCgformField field : editFields) {
                OnlCgformField dbField = (OnlCgformField)this.fieldService.getById(field.getId());
                this.a(dbField.getMainTable(), head.getTableName());
                boolean ischange = org.jeecg.modules.online.cgform.dird.d.a(dbField, field);
                if (ischange) {
                    isDbSync = "N";
                }

                if ((dbField.getOrderNum() == null ? 0 : dbField.getOrderNum()) > maxOrderNum) {
                    maxOrderNum = dbField.getOrderNum();
                }

                if ("Y".equals(headEntity.getIsDbSynch()) && !field.getDbFieldName().equals(dbField.getDbFieldName())) {
                    field.setDbFieldNameOld(dbField.getDbFieldName());
                }

                UpdateWrapper<OnlCgformField> wrapper = new UpdateWrapper();
                wrapper.lambda().eq(OnlCgformField::getId, field.getId());
                if (field.getFieldValidType() == null) {
                    wrapper.lambda().set(OnlCgformField::getFieldValidType, "");
                }

                this.fieldService.update(field, wrapper);
            }

            for(OnlCgformField onlCgformField : addFields) {
                if (onlCgformField.getOrderNum() == null) {
                    ++maxOrderNum;
                    onlCgformField.setOrderNum(maxOrderNum);
                }

                this.fieldService.save(onlCgformField);
            }

            List<OnlCgformIndex> dbCgformIndex = this.indexService.getCgformIndexsByCgformId(head.getId());
            List addIndex = new ArrayList();
            List<OnlCgformIndex> editIndex = new ArrayList();

            for(OnlCgformIndex index : indexs) {
                String id = String.valueOf(index.getId());
                if (id.length() == 32) {
                    editIndex.add(index);
                } else {
                    index.setId((String)null);
                    index.setIsDbSynch("N");
                    index.setDelFlag(CommonConstant.DEL_FLAG_0);
                    index.setCgformHeadId(head.getId());
                    addIndex.add(index);
                }
            }

            for(OnlCgformIndex index : dbCgformIndex) {
                boolean bool = indexs.stream().anyMatch((a) -> index.getId().equals(a.getId()));
                if (!bool) {
                    index.setDelFlag(CommonConstant.DEL_FLAG_1);
                    editIndex.add(index);
                    isDbSync = "N";
                }
            }

            if (addIndex.size() > 0) {
                isDbSync = "N";
                this.indexService.saveBatch(addIndex);
            }

            for(OnlCgformIndex index : editIndex) {
                OnlCgformIndex dbIndex = (OnlCgformIndex)this.indexService.getById(index.getId());
                boolean ischange = org.jeecg.modules.online.cgform.dird.d.a(dbIndex, index);
                if (ischange) {
                    isDbSync = "N";
                    index.setIsDbSynch("N");
                    if (!dbIndex.getIndexName().trim().equalsIgnoreCase(index.getIndexName().trim())) {
                        index.setIndexNameOld(dbIndex.getIndexName());
                    }
                }

                this.indexService.updateById(index);
            }

            if (model.getDeleteFieldIds().size() > 0) {
                for(String deleteId : model.getDeleteFieldIds()) {
                    OnlCgformField deleteField = (OnlCgformField)this.fieldService.getById(deleteId);
                    if (deleteField != null) {
                        if (org.jeecg.modules.online.cgform.dirb.b.b.equals(deleteField.getDbIsPersist())) {
                            isDbSync = "N";
                        }

                        this.a(deleteField.getMainTable(), head.getTableName());
                        this.fieldService.removeById(deleteId);
                    }
                }
            }

            head.setIsDbSynch(isDbSync);
            super.updateById(head);
            this.a(head, fields);
            this.b(head, fields);
            return Result.ok("全部修改成功");
        }
    }

    private boolean a(List<OnlCgformField> addFields) {
        if (addFields != null && addFields.size() != 0) {
            boolean flag = false;

            for(OnlCgformField field : addFields) {
                if (org.jeecg.modules.online.cgform.dirb.b.b.equals(field.getDbIsPersist())) {
                    flag = true;
                    break;
                }
            }

            return flag;
        } else {
            return false;
        }
    }

    private void a(String mainTableName, String currentTableName) {
        if (oConvertUtils.isNotEmpty(mainTableName)) {
            LambdaQueryWrapper mainTableQuery = (LambdaQueryWrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, mainTableName);
            OnlCgformHead mainTable = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne(mainTableQuery);
            if (mainTable != null && oConvertUtils.isNotEmpty(mainTable.getSubTableStr())) {
                String subTableString = mainTable.getSubTableStr();
                String[] arr = subTableString.split(",");
                List ls = new ArrayList();

                for(String a : arr) {
                    if (!a.equals(currentTableName)) {
                        ls.add(a);
                    }
                }

                mainTable.setSubTableStr(String.join(",", ls));
                ((OnlCgformHeadMapper)this.baseMapper).updateById(mainTable);
            }
        }

    }

    public void doDbSynch(String code, String synMethod) throws HibernateException, IOException, TemplateException, SQLException, org.jeecg.modules.online.config.exception.a {
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getById(code);
        if (onlCgformHead == null) {
            throw new org.jeecg.modules.online.config.exception.a("实体配置不存在");
        } else {
            String tbname = onlCgformHead.getTableName();
            LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
            query.eq(OnlCgformField::getCgformHeadId, code);
            query.eq(OnlCgformField::getDbIsPersist, org.jeecg.modules.online.cgform.dirb.b.b);
            query.orderByAsc(OnlCgformField::getOrderNum);
            List fieldList = this.fieldService.list(query);
            org.jeecg.modules.online.config.model.a model = new org.jeecg.modules.online.config.model.a();
            model.setTableName(tbname);
            model.setJformPkType(onlCgformHead.getIdType());
            model.setJformPkSequence(onlCgformHead.getIdSequence());
            model.setContent(onlCgformHead.getTableTxt());
            model.setColumns(fieldList);
            b dbc = this.getOnlineDataBaseConfig();
            model.setDbConfig(dbc);
            DbType dbTypeEnum = org.jeecg.modules.online.config.dirc.d.c(dbc);
            if ("normal".equals(synMethod) && !dbTypeEnum.equals(DbType.SQLITE)) {
                long startTime = System.currentTimeMillis();
                boolean isExist = org.jeecg.modules.online.config.dirc.d.a(tbname, dbc);
                if (isExist) {
                    c process = new c(dbc);

                    for(String sql : process.b(model)) {
                        if (!oConvertUtils.isEmpty(sql) && !oConvertUtils.isEmpty(sql.trim())) {
                            String[] sqlArr = sql.split(";");
                            if (sqlArr != null && sqlArr.length > 1) {
                            }

                            for(String singleSql : sqlArr) {
                                if (!oConvertUtils.isEmpty(singleSql) && !oConvertUtils.isEmpty(singleSql.trim())) {
                                    ((OnlCgformHeadMapper)this.baseMapper).executeDDL(singleSql);
                                }
                            }
                        }
                    }

                    List<OnlCgformIndex> list = this.indexService.list((new LambdaQueryWrapper<OnlCgformIndex>()).eq(OnlCgformIndex::getCgformHeadId, code));
                    for(OnlCgformIndex configIndex : list) {
                        if ("N".equals(configIndex.getIsDbSynch()) || CommonConstant.DEL_FLAG_1.equals(configIndex.getDelFlag())) {
                            String indexName = oConvertUtils.getString(configIndex.getIndexNameOld(), configIndex.getIndexName());
                            String countIndexSql = process.b(indexName, tbname);
                            if (this.indexService.isExistIndex(countIndexSql)) {
                                String delIndexSql = process.a(indexName, tbname);

                                try {
                                    this.baseMapper.executeDDL(delIndexSql);
                                    if (CommonConstant.DEL_FLAG_1.equals(configIndex.getDelFlag())) {
                                        this.indexService.removeById(configIndex.getId());
                                    } else {
                                        configIndex.setIndexNameOld("");
                                        this.indexService.updateById(configIndex);
                                    }
                                } catch (Exception e) {
                                    a.error("删除表【" + tbname + "】索引(" + configIndex.getIndexName() + ")失败!", e);
                                }
                            } else if (CommonConstant.DEL_FLAG_1.equals(configIndex.getDelFlag())) {
                                this.indexService.removeById(configIndex.getId());
                            }
                        }
                    }
                } else {
                    c.a(model);
                }
            } else if ("force".equals(synMethod) || dbTypeEnum.equals(DbType.SQLITE)) {
                DbTableHandleI handler = org.jeecg.modules.online.config.dirc.d.getTableHandle();
                String sql = handler.dropTableSQL(tbname);
                ((OnlCgformHeadMapper)this.baseMapper).executeDDL(sql);
                c.a(model);
            }

            this.indexService.createIndex(code, org.jeecg.modules.online.config.dirc.d.getDatabaseType(), tbname, synMethod);
            onlCgformHead.setIsDbSynch("Y");
            if (onlCgformHead.getTableVersion() == 1) {
                onlCgformHead.setTableVersion(2);
            }

            this.updateById(onlCgformHead);
        }
    }

    public void deleteRecordAndTable(String id) throws org.jeecg.modules.online.config.exception.a, SQLException {
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getById(id);
        if (onlCgformHead == null) {
            throw new org.jeecg.modules.online.config.exception.a("实体配置不存在");
        } else {
            long startTime = System.currentTimeMillis();
            boolean isExist = org.jeecg.modules.online.config.dirc.d.a(onlCgformHead.getTableName());
            if (isExist) {
                String sql = org.jeecg.modules.online.config.dirc.d.getTableHandle().dropTableSQL(onlCgformHead.getTableName());
                ((OnlCgformHeadMapper)this.baseMapper).executeDDL(sql);
            }

            this.deleteRecord(id);
        }
    }

    public void deleteRecord(String id) throws org.jeecg.modules.online.config.exception.a, SQLException {
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getById(id);
        if (onlCgformHead == null) {
            throw new org.jeecg.modules.online.config.exception.a("实体配置不存在");
        } else {
            LambdaQueryWrapper<OnlCgformHead> query = new LambdaQueryWrapper();
            query.eq(OnlCgformHead::getPhysicId, id);
            List<OnlCgformHead> ls = this.baseMapper.selectList(query);
            if (ls != null && ls.size() > 0) {
                for(OnlCgformHead head : ls) {
                    this.a(head.getId());
                }
            }

            this.a(onlCgformHead);
            this.a(id);
            if (onlCgformHead.getTableType() == 3) {
                this.onlCgformFieldService.clearCacheOnlineConfig();
            }

        }
    }

    private void a(String id) {
        ((OnlCgformHeadMapper)this.baseMapper).deleteById(id);
        LambdaQueryWrapper<OnlCgformField> fieldQuery = new LambdaQueryWrapper();
        fieldQuery.eq(OnlCgformField::getCgformHeadId, id);
        this.fieldService.remove(fieldQuery);
        LambdaQueryWrapper<OnlCgformIndex> indexQuery = new LambdaQueryWrapper();
        indexQuery.eq(OnlCgformIndex::getCgformHeadId, id);
        this.indexService.remove(indexQuery);
        LambdaQueryWrapper<OnlAuthRelation> authRelationQuery = new LambdaQueryWrapper();
        authRelationQuery.eq(OnlAuthRelation::getCgformId, id);
        this.onlAuthRelationService.remove(authRelationQuery);
        LambdaQueryWrapper<OnlAuthData> authDataQuery = new LambdaQueryWrapper();
        authDataQuery.eq(OnlAuthData::getCgformId, id);
        this.onlAuthDataService.remove(authDataQuery);
        LambdaQueryWrapper<OnlAuthPage> authQuery = new LambdaQueryWrapper();
        authQuery.eq(OnlAuthPage::getCgformId, id);
        this.onlAuthPageService.remove(authQuery);
    }

    private void a(OnlCgformHead onlCgformHead) {
        if (onlCgformHead.getTableType() == 3) {
            LambdaQueryWrapper query = (LambdaQueryWrapper)(new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, onlCgformHead.getId());
            List<OnlCgformField> list = this.fieldService.list(query);
            String mainTableName = null;

            for(OnlCgformField field : list) {
                mainTableName = field.getMainTable();
                if (oConvertUtils.isNotEmpty(mainTableName)) {
                    break;
                }
            }

            if (oConvertUtils.isNotEmpty(mainTableName)) {
                OnlCgformHead mainTable = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, mainTableName));
                if (mainTable != null) {
                    String str = mainTable.getSubTableStr();
                    if (oConvertUtils.isNotEmpty(str)) {
                        List strList = (List)Arrays.asList(str.split(",")).stream().collect(Collectors.toList());
                        strList.remove(onlCgformHead.getTableName());
                        mainTable.setSubTableStr(String.join(",", strList));
                        ((OnlCgformHeadMapper)this.baseMapper).updateById(mainTable);
                    }
                }
            }
        }

    }

    public List<Map<String, Object>> queryListData(String sql) {
        return ((OnlCgformHeadMapper)this.baseMapper).queryList(sql);
    }

    public void saveEnhance(OnlCgformEnhanceJs onlCgformEnhanceJs) {
        this.onlCgformEnhanceJsMapper.insert(onlCgformEnhanceJs);
    }

    public OnlCgformEnhanceJs queryEnhance(String code, String type) {
        return (OnlCgformEnhanceJs)this.onlCgformEnhanceJsMapper.selectOne((Wrapper)((LambdaQueryWrapper<OnlCgformEnhanceJs>)(new LambdaQueryWrapper<OnlCgformEnhanceJs>()).eq(OnlCgformEnhanceJs::getCgJsType, type)).eq(OnlCgformEnhanceJs::getCgformHeadId, code));
    }

    public void editEnhance(OnlCgformEnhanceJs onlCgformEnhanceJs) {
        this.onlCgformEnhanceJsMapper.updateById(onlCgformEnhanceJs);
    }

    public OnlCgformEnhanceSql queryEnhanceSql(String formId, String buttonCode) {
        return (OnlCgformEnhanceSql)this.onlCgformEnhanceSqlMapper.selectOne((Wrapper)((LambdaQueryWrapper<OnlCgformEnhanceSql>)(new LambdaQueryWrapper<OnlCgformEnhanceSql>()).eq(OnlCgformEnhanceSql::getCgformHeadId, formId)).eq(OnlCgformEnhanceSql::getButtonCode, buttonCode));
    }

    public OnlCgformEnhanceJava queryEnhanceJava(OnlCgformEnhanceJava onlCgformEnhanceJava) {
        LambdaQueryWrapper<OnlCgformEnhanceJava> query = new LambdaQueryWrapper();
        query.eq(OnlCgformEnhanceJava::getButtonCode, onlCgformEnhanceJava.getButtonCode());
        query.eq(OnlCgformEnhanceJava::getCgformHeadId, onlCgformEnhanceJava.getCgformHeadId());
        query.eq(OnlCgformEnhanceJava::getCgJavaType, onlCgformEnhanceJava.getCgJavaType());
        query.eq(OnlCgformEnhanceJava::getEvent, onlCgformEnhanceJava.getEvent());
        return (OnlCgformEnhanceJava)this.onlCgformEnhanceJavaMapper.selectOne(query);
    }

    public List<OnlCgformButton> queryButtonList(String code, boolean isListButton) {
        LambdaQueryWrapper<OnlCgformButton> query = new LambdaQueryWrapper();
        query.eq(OnlCgformButton::getButtonStatus, "1");
        query.eq(OnlCgformButton::getCgformHeadId, code);
        if (isListButton) {
            query.in(OnlCgformButton::getButtonStyle, new Object[]{"link", "button"});
        } else {
            query.eq(OnlCgformButton::getButtonStyle, "form");
        }

        query.orderByAsc(OnlCgformButton::getOrderNum);
        return this.onlCgformButtonMapper.selectList(query);
    }

    public List<OnlCgformButton> queryButtonList(String code) {
        LambdaQueryWrapper<OnlCgformButton> query = new LambdaQueryWrapper();
        query.eq(OnlCgformButton::getButtonStatus, "1");
        query.eq(OnlCgformButton::getCgformHeadId, code);
        query.orderByAsc(OnlCgformButton::getOrderNum);
        return this.onlCgformButtonMapper.selectList(query);
    }

    public List<String> queryOnlinetables() {
        return ((OnlCgformHeadMapper)this.baseMapper).queryOnlinetables();
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public void saveDbTable2Online(String tbname) throws Exception {
        OnlCgformHead cgFormHead = new OnlCgformHead();
        cgFormHead.setTableType(1);
        cgFormHead.setIsCheckbox("Y");
        cgFormHead.setIsDbSynch(this.checkTableExist(tbname) ? "Y" : "N");
        cgFormHead.setIsTree("N");
        cgFormHead.setIsPage("Y");
        cgFormHead.setQueryMode("group");
        cgFormHead.setTableName(tbname.toLowerCase());
        cgFormHead.setTableTxt(tbname);
        cgFormHead.setTableVersion(1);
        cgFormHead.setFormTemplate("1");
        cgFormHead.setCopyType(0);
        cgFormHead.setIsDesForm("N");
        cgFormHead.setScroll(1);
        cgFormHead.setThemeTemplate("normal");
        String cgFormId = UUIDGenerator.generate();
        cgFormHead.setId(cgFormId);
        JSONObject extConfig = new JSONObject();
        extConfig.put("tableFixedAction", 1);
        extConfig.put("tableFixedActionType", "right");
        cgFormHead.setExtConfigJson(extConfig.toJSONString());
        List columnsList = new ArrayList();

        try {
            List<ColumnVo> list = DbReadTableUtil.readOriginalTableColumn(tbname);
            boolean hasId = list.stream().anyMatch((columnVo) -> columnVo.getFieldName().equalsIgnoreCase("id"));
            if (!hasId) {
                throw new JeecgBootBizTipException("缺少ID字段，不能同步");
            }

            for(int k = 0; k < list.size(); ++k) {
                ColumnVo columnt = (ColumnVo)list.get(k);
                String fieldName = columnt.getFieldDbName();
                OnlCgformField cgFormField = new OnlCgformField();
                cgFormField.setCgformHeadId(cgFormId);
                cgFormField.setDbFieldNameOld(columnt.getFieldDbName().toLowerCase());
                cgFormField.setDbFieldName(columnt.getFieldDbName().toLowerCase());
                if (oConvertUtils.isNotEmpty(columnt.getFiledComment())) {
                    cgFormField.setDbFieldTxt(columnt.getFiledComment());
                } else {
                    cgFormField.setDbFieldTxt(columnt.getFieldName());
                }

                cgFormField.setDbIsKey(0);
                cgFormField.setIsShowForm(1);
                cgFormField.setIsQuery(0);
                cgFormField.setFieldMustInput("0");
                cgFormField.setIsShowList(1);
                cgFormField.setOrderNum(k + 1);
                cgFormField.setQueryMode("single");
                cgFormField.setDbLength(oConvertUtils.getInt(columnt.getPrecision()));
                cgFormField.setFieldLength(120);
                cgFormField.setDbPointLength(oConvertUtils.getInt(columnt.getScale()));
                cgFormField.setFieldShowType("text");
                cgFormField.setDbIsNull("Y".equals(columnt.getNullable()) ? 1 : 0);
                cgFormField.setIsReadOnly(0);
                if ("id".equalsIgnoreCase(fieldName)) {
                    String[] pkTypeArr = new String[]{"java.lang.Integer", "java.lang.Long"};
                    String idFiledType = columnt.getFieldType();
                    if (Arrays.asList(pkTypeArr).contains(idFiledType)) {
                        cgFormHead.setIdType("NATIVE");
                    } else {
                        cgFormHead.setIdType("UUID");
                    }

                    cgFormField.setOrderNum(-1);
                    cgFormField.setDbIsKey(1);
                    cgFormField.setIsShowForm(0);
                    cgFormField.setIsShowList(0);
                    cgFormField.setIsReadOnly(1);
                }

                if ("create_by".equalsIgnoreCase(fieldName) || "create_time".equalsIgnoreCase(fieldName) || "update_by".equalsIgnoreCase(fieldName) || "update_time".equalsIgnoreCase(fieldName) || "sys_org_code".equalsIgnoreCase(fieldName)) {
                    cgFormField.setIsShowForm(0);
                    cgFormField.setIsShowList(0);
                }

                if ("java.lang.Integer".equalsIgnoreCase(columnt.getFieldType())) {
                    cgFormField.setDbType("int");
                } else if ("java.lang.Long".equalsIgnoreCase(columnt.getFieldType())) {
                    cgFormField.setDbType("int");
                } else if ("java.util.Date".equalsIgnoreCase(columnt.getFieldType())) {
                    if ("datetime".equals(columnt.getFieldDbType())) {
                        cgFormField.setDbType("Datetime");
                        cgFormField.setFieldShowType("datetime");
                    } else {
                        cgFormField.setDbType("Date");
                        cgFormField.setFieldShowType("date");
                    }
                } else if (!"java.lang.Double".equalsIgnoreCase(columnt.getFieldType()) && !"java.lang.Float".equalsIgnoreCase(columnt.getFieldType())) {
                    if (!"java.math.BigDecimal".equalsIgnoreCase(columnt.getFieldType()) && !"BigDecimal".equalsIgnoreCase(columnt.getFieldType())) {
                        if (!"byte[]".equalsIgnoreCase(columnt.getFieldType()) && !columnt.getFieldType().contains("blob")) {
                            if (!"java.lang.Object".equals(columnt.getFieldType()) || !"text".equalsIgnoreCase(columnt.getFieldDbType()) && !"ntext".equalsIgnoreCase(columnt.getFieldDbType())) {
                                if ("java.lang.Object".equals(columnt.getFieldType()) && "image".equalsIgnoreCase(columnt.getFieldDbType())) {
                                    cgFormField.setDbType("Blob");
                                } else {
                                    cgFormField.setDbType("string");
                                }
                            } else {
                                cgFormField.setDbType("Text");
                                cgFormField.setFieldShowType("textarea");
                            }
                        } else {
                            cgFormField.setDbType("Blob");
                            columnt.setCharmaxLength((String)null);
                        }
                    } else {
                        cgFormField.setDbType("BigDecimal");
                    }
                } else {
                    cgFormField.setDbType("double");
                }

                if (oConvertUtils.isEmpty(columnt.getPrecision()) && oConvertUtils.isNotEmpty(columnt.getCharmaxLength())) {
                    if (Long.valueOf(columnt.getCharmaxLength()) >= 3000L) {
                        cgFormField.setDbType("Text");
                        cgFormField.setFieldShowType("textarea");

                        try {
                            cgFormField.setDbLength(Integer.valueOf(columnt.getCharmaxLength()));
                        } catch (Exception e) {
                            a.error(e.getMessage(), e);
                        }
                    } else {
                        cgFormField.setDbLength(Integer.valueOf(columnt.getCharmaxLength()));
                    }
                } else {
                    if (oConvertUtils.isNotEmpty(columnt.getPrecision())) {
                        cgFormField.setDbLength(Integer.valueOf(columnt.getPrecision()));
                    } else if (cgFormField.getDbType().equals("int")) {
                        cgFormField.setDbLength(10);
                    }

                    if (oConvertUtils.isNotEmpty(columnt.getScale())) {
                        cgFormField.setDbPointLength(Integer.valueOf(columnt.getScale()));
                    }
                }

                if (oConvertUtils.getInt(columnt.getPrecision()) == -1 && oConvertUtils.getInt(columnt.getScale()) == 0) {
                    cgFormField.setDbType("Text");
                }

                if ("Blob".equals(cgFormField.getDbType()) || "Text".equals(cgFormField.getDbType()) || "Date".equals(cgFormField.getDbType())) {
                    cgFormField.setDbLength(0);
                    cgFormField.setDbPointLength(0);
                }

                cgFormField.setDbIsPersist(org.jeecg.modules.online.cgform.dirb.b.b);
                columnsList.add(cgFormField);
            }
        } catch (JeecgBootBizTipException e) {
            throw e;
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            throw e;
        }

        if (oConvertUtils.isEmpty(cgFormHead.getFormCategory())) {
            cgFormHead.setFormCategory("bdfl_include");
        }

        this.save(cgFormHead);
        this.fieldService.saveBatch(columnsList);
    }

    public boolean checkTableExist(String tbname) {
        tbname = SqlInjectionUtil.getSqlInjectTableName(tbname);

        try {
            ((OnlCgformHeadMapper)this.baseMapper).queryCountByTableName(tbname);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    private boolean b(String tableName, String subTableStr) {
        if (oConvertUtils.isEmpty(subTableStr)) {
            return false;
        } else {
            String[] subTableNameArray = subTableStr.split(",");

            for(String temp : subTableNameArray) {
                if (temp.equalsIgnoreCase(tableName)) {
                    return true;
                }
            }

            return false;
        }
    }

    private void a(OnlCgformHead head, List<OnlCgformField> fields) {
        if (head.getTableType() == 3) {
            head = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectById(head.getId());

            for(int i = 0; i < fields.size(); ++i) {
                OnlCgformField field = (OnlCgformField)fields.get(i);
                String tbName = field.getMainTable();
                if (!oConvertUtils.isEmpty(tbName)) {
                    OnlCgformHead mainTable = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tbName));
                    if (mainTable != null) {
                        String allSubTable = mainTable.getSubTableStr();
                        if (oConvertUtils.isEmpty(allSubTable)) {
                            allSubTable = head.getTableName();
                        } else if (!this.b(head.getTableName(), allSubTable)) {
                            List arr = new ArrayList(Arrays.asList(allSubTable.split(",")));

                            for(int k = 0; k < arr.size(); ++k) {
                                String tempTbname = (String)arr.get(k);
                                OnlCgformHead tempTable = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tempTbname));
                                if (tempTable != null && head.getTabOrderNum() < oConvertUtils.getInt(tempTable.getTabOrderNum(), 0)) {
                                    arr.add(k, head.getTableName());
                                    break;
                                }
                            }

                            if (arr.indexOf(head.getTableName()) < 0) {
                                arr.add(head.getTableName());
                            }

                            allSubTable = String.join(",", arr);
                        }

                        mainTable.setSubTableStr(allSubTable);
                        ((OnlCgformHeadMapper)this.baseMapper).updateById(mainTable);
                        break;
                    }
                }
            }
        } else {
            List<OnlCgformHead> mainTableList = ((OnlCgformHeadMapper)this.baseMapper).selectList((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).like(OnlCgformHead::getSubTableStr, head.getTableName()));
            if (mainTableList != null && mainTableList.size() > 0) {
                for(OnlCgformHead h : mainTableList) {
                    String subTableStr = h.getSubTableStr();
                    if (h.getSubTableStr().equals(head.getTableName())) {
                        subTableStr = "";
                    } else if (h.getSubTableStr().startsWith(head.getTableName() + ",")) {
                        subTableStr = subTableStr.replace(head.getTableName() + ",", "");
                    } else if (h.getSubTableStr().endsWith("," + head.getTableName())) {
                        subTableStr = subTableStr.replace("," + head.getTableName(), "");
                    } else if (h.getSubTableStr().indexOf("," + head.getTableName() + ",") != -1) {
                        subTableStr = subTableStr.replace("," + head.getTableName() + ",", ",");
                    }

                    h.setSubTableStr(subTableStr);
                    ((OnlCgformHeadMapper)this.baseMapper).updateById(h);
                }
            }
        }

    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public String saveManyFormData(String code, JSONObject json, String xAccessToken) throws org.jeecg.modules.online.config.exception.a, BusinessException {
        OnlCgformHead head = this.getTable(code);
        String currButtonCode = "add";
        this.executeEnhanceJava(currButtonCode, "start", head, json);
        String tbname = org.jeecg.modules.online.cgform.dird.d.f(head.getTableName());
        if (head.getTableType() == 2) {
            String subTables = head.getSubTableStr();
            if (oConvertUtils.isNotEmpty(subTables)) {
                String[] arr = subTables.split(",");

                for(String tb : arr) {
                    JSONArray jsonArray = json.getJSONArray(tb);
                    if (jsonArray != null && jsonArray.size() != 0) {
                        OnlCgformHead temp = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tb));
                        if (temp != null) {
                            List<OnlCgformField> subFieldList = this.fieldService.list((Wrapper)(new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, temp.getId()));
                            String sublinkField = "";
                            String subLinkValue = null;

                            for(OnlCgformField field : subFieldList) {
                                if (!oConvertUtils.isEmpty(field.getMainField())) {
                                    sublinkField = field.getDbFieldName();
                                    String mainField = field.getMainField();
                                    if (json.get(mainField.toLowerCase()) != null) {
                                        subLinkValue = json.getString(mainField.toLowerCase());
                                    }

                                    if (json.get(mainField.toUpperCase()) != null) {
                                        subLinkValue = json.getString(mainField.toUpperCase());
                                    }
                                }
                            }

                            for(int i = 0; i < jsonArray.size(); ++i) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if (subLinkValue != null) {
                                    jsonObject.put(sublinkField, subLinkValue);
                                }

                                this.fieldService.saveFormData(subFieldList, tb, jsonObject);
                            }
                        }
                    }
                }
            }
        } else if (org.jeecg.modules.online.cgform.enums.a.c.equals(head.getTableType()) && head.getRelationType() == 1) {
            Integer count = this.fieldService.queryCountBySql(tbname, (String)null, (String)null);
            if (null != count && count > 1) {
                throw new JeecgBootException("一对一的表只能新增一条数据");
            }
        }

        if ("Y".equals(head.getIsTree())) {
            this.fieldService.saveTreeFormData(head.getId(), tbname, json, head.getTreeIdField(), head.getTreeParentIdField());
        } else {
            this.fieldService.saveFormData(head.getId(), tbname, json, false);
        }

        this.executeEnhanceSql(currButtonCode, head.getId(), json);
        this.executeEnhanceJava(currButtonCode, "end", head, json);
        return head.getTableName();
    }

    public Map<String, Object> querySubFormData(String table, String mainId) throws org.jeecg.modules.online.config.exception.a {
        new HashMap(5);
        OnlCgformHead head = (OnlCgformHead)this.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, table));
        if (head == null) {
            throw new org.jeecg.modules.online.config.exception.a("数据库子表[" + table + "]不存在");
        } else {
            List<OnlCgformField> subFieldList = this.fieldService.queryFormFields(head.getId(), false);
            String sublinkField = null;

            for(OnlCgformField field : subFieldList) {
                if (oConvertUtils.isNotEmpty(field.getMainField())) {
                    sublinkField = field.getDbFieldName();
                    break;
                }
            }

            List subList = this.fieldService.querySubFormData(subFieldList, table, sublinkField, mainId);
            if (subList != null && subList.size() == 0) {
                throw new org.jeecg.modules.online.config.exception.a("数据库子表[" + table + "]未找到相关信息, 主表ID为" + mainId);
            } else if (subList.size() > 1) {
                throw new org.jeecg.modules.online.config.exception.a("数据库子表[" + table + "]存在多条记录, 主表ID为" + mainId);
            } else {
                Map map = (Map)subList.get(0);
                return map;
            }
        }
    }

    public List<Map<String, Object>> queryManySubFormData(String table, String mainId) throws org.jeecg.modules.online.config.exception.a {
        OnlCgformHead head = (OnlCgformHead)this.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, table));
        if (head == null) {
            throw new org.jeecg.modules.online.config.exception.a("数据库子表[" + table + "]不存在");
        } else {
            List<OnlCgformField> subFieldList = this.fieldService.queryFormFields(head.getId(), false);
            if (subFieldList != null && subFieldList.size() != 0) {
                String sublinkField = null;
                String mainLinkTable = null;
                String mainLinkField = null;

                for(OnlCgformField field : subFieldList) {
                    if (oConvertUtils.isNotEmpty(field.getMainField())) {
                        sublinkField = field.getDbFieldName();
                        mainLinkTable = field.getMainTable();
                        mainLinkField = field.getMainField();
                        break;
                    }
                }

                List mainFieldList = new ArrayList();
                OnlCgformField mainField = new OnlCgformField();
                mainField.setDbFieldName(mainLinkField);
                mainFieldList.add(mainField);
                Map mainMap = this.fieldService.queryFormData(mainFieldList, mainLinkTable, mainId);
                String value = oConvertUtils.getString(oConvertUtils.getString(mainMap.get(mainLinkField)), oConvertUtils.getString(mainMap.get(mainLinkField.toUpperCase())));
                List<Map<String, Object>>  subList = this.fieldService.querySubFormData(subFieldList, table, sublinkField, value);
                if (subList != null && subList.size() == 0) {
                    return Arrays.asList();
                } else {
                    List result = new ArrayList(subList.size());

                    for(Map map : subList) {
                        result.add(org.jeecg.modules.online.cgform.dird.d.a(map));
                    }

                    return result;
                }
            } else {
                throw new org.jeecg.modules.online.config.exception.a("找不到子表字段，请确认配置是否正确!");
            }
        }
    }

    public Map<String, Object> queryManyFormData(String code, String id) throws org.jeecg.modules.online.config.exception.a {
        OnlCgformHead head = this.getTable(code);
        List fieldList = this.fieldService.queryFormFields(head.getId(), true);
        if (fieldList != null && fieldList.size() != 0) {
            String tbname = org.jeecg.modules.online.cgform.dird.d.f(head.getTableName());
            Map map = this.fieldService.queryFormData(fieldList, tbname, id);
            if (head.getTableType() == 2) {
                String subTables = head.getSubTableStr();
                if (oConvertUtils.isNotEmpty(subTables)) {
                    String[] arr = subTables.split(",");

                    for(String tb : arr) {
                        OnlCgformHead temp = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tb));
                        if (temp != null) {
                            List<OnlCgformField> subFieldList = this.fieldService.queryFormFields(temp.getId(), false);
                            String sublinkField = "";
                            String subLinkValue = null;

                            for(OnlCgformField field : subFieldList) {
                                if (!oConvertUtils.isEmpty(field.getMainField())) {
                                    sublinkField = field.getDbFieldName();
                                    String mainField = field.getMainField();
                                    subLinkValue = org.jeecg.modules.online.cgform.dird.d.a(map, mainField);
                                }
                            }

                            List subList = this.fieldService.querySubFormData(subFieldList, tb, sublinkField, subLinkValue);
                            if (subList != null && subList.size() != 0) {
                                map.put(tb, org.jeecg.modules.online.cgform.dird.d.d(subList));
                            } else {
                                map.put(tb, new String[0]);
                            }
                        }
                    }
                }
            }

            return map;
        } else {
            throw new org.jeecg.modules.online.config.exception.a("找不到字段，请确认配置是否正确!");
        }
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public String editManyFormData(String code, JSONObject json) throws org.jeecg.modules.online.config.exception.a, BusinessException {
        OnlCgformHead head = this.getTable(code);
        String currButtonCode = "edit";
        this.executeEnhanceJava(currButtonCode, "start", head, json);
        Map beforeEditData = this.queryManyFormData(code, json.getString("id"));
        if (oConvertUtils.isEmpty(beforeEditData)) {
            a.error("待编辑数据不存在" + json.getString("id"));
            throw new JeecgBootBizTipException("待编辑数据不存在");
        } else {
            String tbname = head.getTableName();
            if ("Y".equals(head.getIsTree())) {
                this.fieldService.editTreeFormData(head.getId(), tbname, json, head.getTreeIdField(), head.getTreeParentIdField());
            } else {
                this.fieldService.editFormData(head.getId(), tbname, json, false);
            }

            if (head.getTableType() == 2 && !"erp".equals(head.getThemeTemplate())) {
                String subTables = head.getSubTableStr();
                if (oConvertUtils.isNotEmpty(subTables)) {
                    String[] arr = subTables.split(",");

                    for(String tb : arr) {
                        OnlCgformHead temp = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tb));
                        if (temp != null) {
                            List<OnlCgformField> subFieldList = this.fieldService.list((Wrapper)(new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, temp.getId()));
                            String sublinkField = "";
                            String subLinkValue = null;
                            String oldSubLinkValue = null;

                            for(OnlCgformField field : subFieldList) {
                                if (!oConvertUtils.isEmpty(field.getMainField())) {
                                    sublinkField = field.getDbFieldName();
                                    String mainField = field.getMainField();
                                    if (json.get(mainField.toLowerCase()) != null) {
                                        subLinkValue = json.getString(mainField.toLowerCase());
                                        oldSubLinkValue = oConvertUtils.getString(beforeEditData.get(mainField.toLowerCase()), subLinkValue);
                                    }

                                    if (json.get(mainField.toUpperCase()) != null) {
                                        subLinkValue = json.getString(mainField.toUpperCase());
                                        oldSubLinkValue = oConvertUtils.getString(beforeEditData.get(mainField.toUpperCase()), subLinkValue);
                                    }
                                }
                            }

                            if (!oConvertUtils.isEmpty(subLinkValue)) {
                                this.fieldService.deleteAutoList(tb, sublinkField, oldSubLinkValue);
                                JSONArray jsonArray = json.getJSONArray(tb);
                                if (jsonArray != null && jsonArray.size() != 0) {
                                    for(int i = 0; i < jsonArray.size(); ++i) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        if (subLinkValue != null) {
                                            jsonObject.put(sublinkField, subLinkValue);
                                        }

                                        this.fieldService.saveFormData(subFieldList, tb, jsonObject);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            this.executeEnhanceJava(currButtonCode, "end", head, json);
            this.executeEnhanceSql(currButtonCode, head.getId(), json);
            return tbname;
        }
    }

    private OnlCgformEnhanceJava a(String buttonCode, String eventType, String headId) {
        LambdaQueryWrapper<OnlCgformEnhanceJava> query = new LambdaQueryWrapper();
        query.eq(OnlCgformEnhanceJava::getActiveStatus, "1");
        query.eq(OnlCgformEnhanceJava::getButtonCode, buttonCode);
        query.eq(OnlCgformEnhanceJava::getEvent, eventType);
        query.eq(OnlCgformEnhanceJava::getCgformHeadId, headId);
        return (OnlCgformEnhanceJava)this.onlCgformEnhanceJavaMapper.selectOne(query);
    }

    private Object b(String buttonCode, String eventType, String headId) {
        LambdaQueryWrapper<OnlCgformEnhanceJava> query = new LambdaQueryWrapper();
        query.eq(OnlCgformEnhanceJava::getActiveStatus, "1");
        query.eq(OnlCgformEnhanceJava::getButtonCode, buttonCode);
        query.eq(OnlCgformEnhanceJava::getEvent, eventType);
        query.eq(OnlCgformEnhanceJava::getCgformHeadId, headId);
        OnlCgformEnhanceJava enhance = (OnlCgformEnhanceJava)this.onlCgformEnhanceJavaMapper.selectOne(query);
        Object obj = this.a(enhance);
        return obj;
    }

    private void a(JSONObject json, Object obj, String tableName, OnlCgformEnhanceJava enhance) throws BusinessException {
        if (obj != null && obj instanceof CgformEnhanceJavaInter javaInter) {
            javaInter.execute(tableName, json);
        } else if (obj != null && obj instanceof a) {
            ((a)obj).execute(tableName, json, enhance);
        }

    }

    public void executeEnhanceJava(String buttonCode, String eventType, OnlCgformHead head, JSONObject json) throws BusinessException {
        OnlCgformEnhanceJava enhance = this.a(buttonCode, eventType, head.getId());
        Object obj = this.a(enhance);
        this.a(json, obj, head.getTableName(), enhance);
    }

    public void executeEnhanceExport(OnlCgformHead head, List<Map<String, Object>> dataList) throws BusinessException {
        this.executeEnhanceList(head, "export", dataList);
    }

    public EnhanceDataEnum executeEnhanceImport(OnlCgformHead head, JSONObject json) throws BusinessException {
        OnlCgformEnhanceJava enhance = this.a("import", "start", head.getId());
        Object obj = this.a(enhance);
        if (obj != null && obj instanceof CgformEnhanceJavaImportInter javaInter) {
            return javaInter.execute(head.getTableName(), json);
        } else {
            return EnhanceDataEnum.INSERT;
        }
    }

    public void executeEnhanceList(OnlCgformHead head, String buttonCode, List<Map<String, Object>> dataList) throws BusinessException {
        LambdaQueryWrapper<OnlCgformEnhanceJava> query = new LambdaQueryWrapper();
        query.eq(OnlCgformEnhanceJava::getActiveStatus, "1");
        query.eq(OnlCgformEnhanceJava::getButtonCode, buttonCode);
        query.eq(OnlCgformEnhanceJava::getCgformHeadId, head.getId());
        List enhances = this.onlCgformEnhanceJavaMapper.selectList(query);
        if (enhances != null && enhances.size() > 0) {
            Object obj = this.a((OnlCgformEnhanceJava)enhances.get(0));
            if (obj != null && obj instanceof CgformEnhanceJavaListInter) {
                CgformEnhanceJavaListInter javaInter = (CgformEnhanceJavaListInter)obj;
                javaInter.execute(head.getTableName(), dataList);
            } else if (obj != null && obj instanceof org.jeecg.modules.online.cgform.enhance.impl.http.b) {
                ((org.jeecg.modules.online.cgform.enhance.impl.http.b)obj).execute(head.getTableName(), dataList, (OnlCgformEnhanceJava)enhances.get(0));
            }
        }

    }

    private Object a(OnlCgformEnhanceJava enhance) {
        if (enhance != null) {
            String javaType = enhance.getCgJavaType();
            String javaValue = enhance.getCgJavaValue();
            if (oConvertUtils.isNotEmpty(javaValue)) {
                Object obj = null;
                if ("class".equals(javaType)) {
                    try {
                        obj = MyClassLoader.getClassByScn(javaValue).newInstance();
                    } catch (InstantiationException e) {
                        a.error(e.getMessage(), e);
                    } catch (IllegalAccessException e) {
                        a.error(e.getMessage(), e);
                    }
                } else if ("spring".equals(javaType)) {
                    obj = SpringContextUtils.getBean(javaValue);
                } else if ("http".equals(javaType)) {
                    obj = this.b(enhance);
                }

                return obj;
            }
        }

        return null;
    }

    private Object b(OnlCgformEnhanceJava enhance) {
        switch (enhance.getButtonCode()) {
            case "add":
            case "edit":
            case "delete":
            case "import":
                return this.cgformEnhanceJavaHttp;
            case "export":
            case "query":
                return this.cgformEnhanceJavaListHttp;
            default:
                return this.cgformEnhanceJavaHttp;
        }
    }

    private OnlCgformEnhanceSql c(String buttonCode, String headId) {
        LambdaQueryWrapper<OnlCgformEnhanceSql> query = new LambdaQueryWrapper();
        query.eq(OnlCgformEnhanceSql::getButtonCode, buttonCode);
        query.eq(OnlCgformEnhanceSql::getCgformHeadId, headId);
        OnlCgformEnhanceSql enhance = (OnlCgformEnhanceSql)this.onlCgformEnhanceSqlMapper.selectOne(query);
        return enhance;
    }

    private void a(JSONObject json, OnlCgformEnhanceSql enhance) {
        if (enhance != null && oConvertUtils.isNotEmpty(enhance.getCgbSql())) {
            String sqls = org.jeecg.modules.online.cgform.dird.d.a(enhance.getCgbSql(), json);
            String[] arr = sqls.split(";");

            for(String sql : arr) {
                if (sql != null && !"".equals(sql.toLowerCase().trim())) {
                    ((OnlCgformHeadMapper)this.baseMapper).executeDDL(sql);
                }
            }
        }

    }

    public void executeEnhanceSql(String buttonCode, String formId, JSONObject json) {
        OnlCgformEnhanceSql enhance = this.c(buttonCode, formId);
        this.a(json, enhance);
    }

    public void executeCustomerButton(String buttonCode, String formId, String dataId) throws BusinessException {
        OnlCgformHead head = (OnlCgformHead)this.getById(formId);
        if (head == null) {
            throw new BusinessException("未找到表配置信息");
        } else {
            OnlCgformEnhanceJava startEnhanceJava = this.a(buttonCode, "start", formId);
            OnlCgformEnhanceJava endEnhanceJava = this.a(buttonCode, "end", formId);
            Object startEnhanceJavaObj = this.a(startEnhanceJava);
            Object endEnhanceJavaObj = this.a(endEnhanceJava);
            OnlCgformEnhanceSql enhanceSql = this.c(buttonCode, formId);
            String tableName = head.getTableName();
            String[] arr = dataId.split(",");
            LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
            query.eq(OnlCgformField::getCgformHeadId, formId);
            List fieldList = this.onlCgformFieldService.list(query);

            for(String id : arr) {
                Map data = this.d(org.jeecg.modules.online.cgform.dird.d.f(head.getTableName()), org.jeecg.modules.online.cgform.dird.d.k(id));
                data = this.a(fieldList, data);
                JSONObject json = JSONObject.parseObject(JSON.toJSONString(data));
                this.a(json, startEnhanceJavaObj, tableName, startEnhanceJava);
                this.a(json, enhanceSql);
                this.a(json, endEnhanceJavaObj, tableName, endEnhanceJava);
            }

        }
    }

    private Map<String, Object> d(String tbname, String dataId) {
        tbname = SqlInjectionUtil.getSqlInjectTableName(tbname);
        return ((OnlCgformHeadMapper)this.baseMapper).queryOneByTableNameAndId(tbname, dataId);
    }

    private Map<String, Object> a(List<OnlCgformField> fieldList, Map<String, Object> data) {
        Map result = new HashMap(5);

        for(OnlCgformField field : fieldList) {
            String type = field.getDbType();
            if (!"blob".equalsIgnoreCase(type) && !"text".equalsIgnoreCase(type)) {
                String key = field.getDbFieldName();
                Object value = org.jeecg.modules.online.cgform.dird.d.b(data, key);
                result.put(key, value);
            }
        }

        return result;
    }

    public List<OnlCgformButton> queryValidButtonList(String headId) {
        LambdaQueryWrapper<OnlCgformButton> query = new LambdaQueryWrapper();
        query.eq(OnlCgformButton::getCgformHeadId, headId);
        query.eq(OnlCgformButton::getButtonStatus, "1");
        query.orderByAsc(OnlCgformButton::getOrderNum);
        return this.onlCgformButtonMapper.selectList(query);
    }

    public OnlCgformEnhanceJs queryEnhanceJs(String formId, String cgJsType) {
        LambdaQueryWrapper<OnlCgformEnhanceJs> query = new LambdaQueryWrapper();
        query.eq(OnlCgformEnhanceJs::getCgformHeadId, formId);
        query.eq(OnlCgformEnhanceJs::getCgJsType, cgJsType);
        return (OnlCgformEnhanceJs)this.onlCgformEnhanceJsMapper.selectOne(query);
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public void deleteOneTableInfo(String formId, String dataId) throws BusinessException {
        OnlCgformHead head = (OnlCgformHead)this.getById(formId);
        if (head == null) {
            throw new BusinessException("未找到表配置信息");
        } else {
            String realTableName = org.jeecg.modules.online.cgform.dird.d.f(head.getTableName());
            Map record = this.d(realTableName, dataId);
            if (record != null) {
                Map newRecord = org.jeecg.modules.online.cgform.dird.d.a(record);
                String buttonCode = "delete";
                JSONObject json = JSONObject.parseObject(JSON.toJSONString(newRecord));
                this.executeEnhanceJava(buttonCode, "start", head, json);
                this.updateParentNode(head, dataId);
                if (head.getTableType() == 2) {
                    this.fieldService.deleteAutoListMainAndSub(head, dataId);
                } else {
                    realTableName = SqlInjectionUtil.getSqlInjectTableName(realTableName);
                    ((OnlCgformHeadMapper)this.baseMapper).deleteOne(realTableName, dataId);
                }

                this.executeEnhanceSql(buttonCode, formId, json);
                this.executeEnhanceJava(buttonCode, "end", head, json);
            }
        }
    }

    /** @deprecated */
    @Deprecated
    public JSONObject queryFormItem(OnlCgformHead head, String username) {
        List fieldList = this.fieldService.queryAvailableFields(head.getId(), head.getTableName(), head.getTaskId(), false);
        List disabledFieldNameList = new ArrayList();
        if (oConvertUtils.isEmpty(head.getTaskId())) {
            List disabledOnlineList = this.onlAuthPageService.queryFormDisabledCode(head.getId());
            if (disabledOnlineList != null && disabledOnlineList.size() > 0 && disabledOnlineList.get(0) != null) {
                disabledFieldNameList.addAll(disabledOnlineList);
            }
        } else {
            List disabledFlowList = this.fieldService.queryDisabledFields(head.getTableName(), head.getTaskId());
            if (disabledFlowList != null && disabledFlowList.size() > 0 && disabledFlowList.get(0) != null) {
                disabledFieldNameList.addAll(disabledFlowList);
            }
        }

        JSONObject schema = org.jeecg.modules.online.cgform.dird.d.a(fieldList, disabledFieldNameList, (TreeSelectColumn)null);
        if (head.getTableType() == 2) {
            String subStr = head.getSubTableStr();
            if (oConvertUtils.isNotEmpty(subStr)) {
                for(String tbname : subStr.split(",")) {
                    OnlCgformHead tempTable = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tbname));
                    if (tempTable != null) {
                        List<OnlCgformField> subFieldList = this.fieldService.queryAvailableFields(tempTable.getId(), tempTable.getTableName(), head.getTaskId(), false);
                        new ArrayList();
                        List<String> subDisabledFieldNameList;
                        if (oConvertUtils.isNotEmpty(head.getTaskId())) {
                            subDisabledFieldNameList = this.fieldService.queryDisabledFields(tempTable.getTableName(), head.getTaskId());
                        } else {
                            subDisabledFieldNameList = this.onlAuthPageService.queryFormDisabledCode(tempTable.getId());
                        }

                        JSONObject subJson = new JSONObject();
                        if (1 == tempTable.getRelationType()) {
                            subJson = org.jeecg.modules.online.cgform.dird.d.a(subFieldList, subDisabledFieldNameList, (TreeSelectColumn)null);
                        } else {
                            subJson.put("columns", org.jeecg.modules.online.cgform.dird.d.a(subFieldList, subDisabledFieldNameList));
                        }

                        subJson.put("relationType", tempTable.getRelationType());
                        subJson.put("view", "tab");
                        subJson.put("order", tempTable.getTabOrderNum());
                        subJson.put("formTemplate", tempTable.getFormTemplate());
                        subJson.put("describe", tempTable.getTableTxt());
                        subJson.put("key", tempTable.getTableName());
                        schema.getJSONObject("properties").put(tempTable.getTableName(), subJson);
                    }
                }
            }
        }

        return schema;
    }

    public List<String> generateCode(org.jeecg.modules.online.cgform.model.d model) throws Exception {
        TableVo tableVo = new TableVo();
        tableVo.setEntityName(model.getEntityName());
        tableVo.setEntityPackage(model.getEntityPackage());
        tableVo.setFtlDescription(model.getFtlDescription());
        tableVo.setTableName(model.getTableName());
        tableVo.setSearchFieldNum(-1);
        List columns = new ArrayList();
        List originalColumns = new ArrayList();
        this.a(model.getCode(), columns, originalColumns);
        OnlCgformHead tableConfig = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getId, model.getCode()));
        Map extendParams = new HashMap(5);
        extendParams.put("scroll", tableConfig.getScroll() == null ? "0" : tableConfig.getScroll().toString());
        String formTemplate = tableConfig.getFormTemplate();
        if (oConvertUtils.isEmpty(formTemplate)) {
            tableVo.setFieldRowNum(1);
        } else {
            tableVo.setFieldRowNum(Integer.parseInt(formTemplate));
        }

        if ("Y".equals(tableConfig.getIsTree())) {
            extendParams.put("pidField", tableConfig.getTreeParentIdField());
            extendParams.put("hasChildren", tableConfig.getTreeIdField());
            extendParams.put("textField", tableConfig.getTreeFieldname());
        }

        if (oConvertUtils.isNotEmpty(model.getVueStyle())) {
            extendParams.put("vueStyle", model.getVueStyle());
        }

        List cgButtonList = this.queryButtonList(model.getCode());
        if (cgButtonList != null && !cgButtonList.isEmpty()) {
            extendParams.put("cgButtonList", cgButtonList);
        }

        List<OnlCgformEnhanceJs> enhanceJsList = this.onlCgformEnhanceJsMapper.selectList((Wrapper)(new LambdaQueryWrapper<OnlCgformEnhanceJs>()).eq(OnlCgformEnhanceJs::getCgformHeadId, model.getCode()));
        if (oConvertUtils.isObjectNotEmpty(enhanceJsList)) {
            List jsList = new ArrayList();

            for(OnlCgformEnhanceJs enhanceJs : enhanceJsList) {
                if (enhanceJs != null && oConvertUtils.isNotEmpty(enhanceJs.getCgJs())) {
                    String enhanceJsStr = e.b(enhanceJs.getCgJs(), cgButtonList);
                    enhanceJs.setCgJs(enhanceJsStr);
                    jsList.add(enhanceJs);
                }
            }

            if (oConvertUtils.isObjectNotEmpty(jsList)) {
                extendParams.put("enhanceJsList", jsList);
            }
        }

        List enhanceJavaList = this.onlCgformEnhanceJavaMapper.selectList((Wrapper)(new LambdaQueryWrapper<OnlCgformEnhanceJava>()).eq(OnlCgformEnhanceJava::getCgformHeadId, model.getCode()));
        if (enhanceJavaList != null && !enhanceJavaList.isEmpty()) {
            extendParams.put("enhanceJavaList", enhanceJavaList);
        }

        tableVo.setExtendParams(extendParams);
        CgformEnum templateCgformEnum = CgformEnum.getCgformEnumByConfig(model.getJspMode());
        List generateList = (new CodeGenerateOne(tableVo, columns, originalColumns)).generateCodeFile(model.getProjectPath(), templateCgformEnum.getTemplatePath(), templateCgformEnum.getStylePath());
        if (generateList == null || generateList.size() == 0) {
            generateList = new ArrayList();
            generateList.add(" :::::: 生成失败ERROR提示 :::::: ");
            generateList.add("1.代码生成模板`code-template-online`目录是否存在");
            generateList.add("2.代码生成模板`code-template-online`目录是否含有中文或空格");
        }

        return generateList;
    }

    public List<String> generateOneToMany(org.jeecg.modules.online.cgform.model.d model) throws Exception {
        MainTableVo mainTableVo = new MainTableVo();
        mainTableVo.setEntityName(model.getEntityName());
        mainTableVo.setEntityPackage(model.getEntityPackage());
        mainTableVo.setFtlDescription(model.getFtlDescription());
        mainTableVo.setTableName(model.getTableName());
        OnlCgformHead tableConfig = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getId, model.getCode()));
        String formTemplate = tableConfig.getFormTemplate();
        if (oConvertUtils.isEmpty(formTemplate)) {
            mainTableVo.setFieldRowNum(1);
        } else {
            mainTableVo.setFieldRowNum(Integer.parseInt(formTemplate));
        }

        List mainColums = new ArrayList();
        List originalMainColumns = new ArrayList();
        this.a(model.getCode(), mainColums, originalMainColumns);
        List<org.jeecg.modules.online.cgform.model.d> subList = model.getSubList();
        List subTables = new ArrayList();

        for(org.jeecg.modules.online.cgform.model.d sub : subList) {
            OnlCgformHead subTableConfig = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, sub.getTableName()));
            if (subTableConfig != null) {
                SubTableVo subVo = new SubTableVo();
                subVo.setEntityName(sub.getEntityName());
                subVo.setEntityPackage(model.getEntityPackage());
                subVo.setTableName(sub.getTableName());
                subVo.setFtlDescription(sub.getFtlDescription());
                Integer manyOrone = subTableConfig.getRelationType();
                subVo.setForeignRelationType(manyOrone == 1 ? "1" : "0");
                List columns = new ArrayList();
                List originalColumns = new ArrayList();
                OnlCgformField foreignKeys = this.a(subTableConfig.getId(), columns, originalColumns);
                if (foreignKeys != null) {
                    subVo.setOriginalForeignKeys(new String[]{foreignKeys.getDbFieldName()});
                    subVo.setForeignKeys(new String[]{foreignKeys.getDbFieldName()});
                    subVo.setForeignMainKeys(new String[]{foreignKeys.getMainField()});
                    subVo.setColums(columns);
                    subVo.setOriginalColumns(originalColumns);
                    Map subExtendParams = new HashMap(1);
                    List subCgButtonList = this.queryButtonList(sub.getCode());
                    if (subCgButtonList != null && !subCgButtonList.isEmpty()) {
                        subExtendParams.put("cgButtonList", subCgButtonList);
                    }

                    List<OnlCgformEnhanceJs> enhanceJsList = this.onlCgformEnhanceJsMapper.selectList((Wrapper)(new LambdaQueryWrapper<OnlCgformEnhanceJs>()).eq(OnlCgformEnhanceJs::getCgformHeadId, model.getCode()));
                    if (oConvertUtils.isObjectNotEmpty(enhanceJsList)) {
                        List jsList = new ArrayList();

                        for(OnlCgformEnhanceJs enhanceJs : enhanceJsList) {
                            if (enhanceJs != null && oConvertUtils.isNotEmpty(enhanceJs.getCgJs())) {
                                String enhanceJsStr = e.b(enhanceJs.getCgJs(), subCgButtonList);
                                enhanceJs.setCgJs(enhanceJsStr);
                                jsList.add(enhanceJs);
                            }
                        }

                        if (oConvertUtils.isObjectNotEmpty(jsList)) {
                            subExtendParams.put("enhanceJsList", jsList);
                        }
                    }

                    List enhanceJavaList = this.onlCgformEnhanceJavaMapper.selectList((Wrapper)(new LambdaQueryWrapper<OnlCgformEnhanceJava>()).eq(OnlCgformEnhanceJava::getCgformHeadId, model.getCode()));
                    if (enhanceJavaList != null && !enhanceJavaList.isEmpty()) {
                        subExtendParams.put("enhanceJavaList", enhanceJavaList);
                    }

                    subVo.setExtendParams(subExtendParams);
                    subTables.add(subVo);
                }
            }
        }

        CgformEnum templateCgformEnum = CgformEnum.getCgformEnumByConfig(model.getJspMode());
        Map extendParams = new HashMap(3);
        List cgButtonList = this.queryButtonList(model.getCode());
        if (cgButtonList != null && !cgButtonList.isEmpty()) {
            extendParams.put("cgButtonList", cgButtonList);
        }

        List<OnlCgformEnhanceJs> enhanceJsList = this.onlCgformEnhanceJsMapper.selectList((Wrapper)(new LambdaQueryWrapper<OnlCgformEnhanceJs>()).eq(OnlCgformEnhanceJs::getCgformHeadId, model.getCode()));
        if (oConvertUtils.isObjectNotEmpty(enhanceJsList)) {
            List jsList = new ArrayList();

            for(OnlCgformEnhanceJs enhanceJs : enhanceJsList) {
                if (enhanceJs != null && oConvertUtils.isNotEmpty(enhanceJs.getCgJs())) {
                    String enhanceJsStr = e.b(enhanceJs.getCgJs(), cgButtonList);
                    enhanceJs.setCgJs(enhanceJsStr);
                    jsList.add(enhanceJs);
                }
            }

            if (oConvertUtils.isObjectNotEmpty(jsList)) {
                extendParams.put("enhanceJsList", jsList);
            }
        }

        List enhanceJavaList = this.onlCgformEnhanceJavaMapper.selectList((Wrapper)(new LambdaQueryWrapper<OnlCgformEnhanceJava>()).eq(OnlCgformEnhanceJava::getCgformHeadId, model.getCode()));
        if (enhanceJavaList != null && !enhanceJavaList.isEmpty()) {
            extendParams.put("enhanceJavaList", enhanceJavaList);
        }

        if (oConvertUtils.isNotEmpty(model.getVueStyle())) {
            List vueStyleList = Arrays.asList(templateCgformEnum.getVueStyle());
            if (vueStyleList.contains(model.getVueStyle())) {
                extendParams.put("vueStyle", model.getVueStyle());
            } else {
                Logger var10000 = a;
                String var10001 = model.getVueStyle();
                var10000.warn("你选择的页面代码类型：【" + var10001 + "】不支持，采用默认类型:" + (String)vueStyleList.get(0) + "生成代码！");
                extendParams.put("vueStyle", vueStyleList.get(0));
            }
        }

        mainTableVo.setExtendParams(extendParams);
        if (subTables != null && subTables.size() != 0) {
            List generateList = (new CodeGenerateOneToMany(mainTableVo, mainColums, originalMainColumns, subTables)).generateCodeFile(model.getProjectPath(), templateCgformEnum.getTemplatePath(), templateCgformEnum.getStylePath());
            return generateList;
        } else {
            a.error("你选择的表类型是【主表】，但是没有关联子表，导致生成代码报错！");
            throw new JeecgBootException("你选择的表类型是【主表】，但是没有关联子表，生成代码失败！");
        }
    }

    private OnlCgformField a(String cgConfigId, List<ColumnVo> columns, List<ColumnVo> originalColumns) {
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, cgConfigId);
        query.eq(OnlCgformField::getDbIsPersist, org.jeecg.modules.online.cgform.dirb.b.b);
        query.orderByAsc(OnlCgformField::getOrderNum);
        List<OnlCgformField> fieldList = this.fieldService.list(query);
        OnlCgformField foreignKeys = null;

        for(OnlCgformField f : fieldList) {
            if (oConvertUtils.isNotEmpty(f.getMainTable())) {
                foreignKeys = f;
            }

            ColumnVo vo = new ColumnVo();
            vo.setFieldLength(f.getFieldLength());
            vo.setFieldHref(f.getFieldHref());
            vo.setFieldValidType(f.getFieldValidType());
            vo.setFieldDefault(f.getDbDefaultVal());
            vo.setFieldShowType(f.getFieldShowType());
            vo.setFieldOrderNum(f.getOrderNum());
            vo.setIsKey(f.getDbIsKey() == 1 ? "Y" : "N");
            vo.setIsShow(f.getIsShowForm() == 1 ? "Y" : "N");
            vo.setIsShowList(f.getIsShowList() == 1 ? "Y" : "N");
            vo.setIsQuery(f.getIsQuery() == 1 ? "Y" : "N");
            vo.setQueryMode(f.getQueryMode());
            vo.setDictField(f.getDictField());
            vo.setDictTable(f.getDictTable());
            vo.setDictText(f.getDictText());
            vo.setFieldDbName(f.getDbFieldName());
            vo.setFieldName(oConvertUtils.camelName(f.getDbFieldName()));
            vo.setFiledComment(f.getDbFieldTxt());
            vo.setFieldDbType(f.getDbType());
            vo.setFieldType(this.b(f.getDbType()));
            vo.setClassType(f.getFieldShowType());
            vo.setClassType_row(f.getFieldShowType());
            if (f.getDbIsNull() != 0 && !"*".equals(f.getFieldValidType()) && !"1".equals(f.getFieldMustInput())) {
                vo.setNullable("Y");
            } else {
                vo.setNullable("N");
            }

            if ("switch".equals(f.getFieldShowType())) {
                if (oConvertUtils.isNotEmpty(f.getFieldExtendJson())) {
                    JSONArray switchOptions = JSONArray.parseArray("[\"Y\",\"N\"]");

                    try {
                        switchOptions = JSONArray.parseArray(f.getFieldExtendJson());
                    } catch (JSONException var14) {
                        JSONObject jsonObject = JSONArray.parseObject(f.getFieldExtendJson());
                        if (jsonObject.containsKey("switchOptions")) {
                            switchOptions = jsonObject.getJSONArray("switchOptions");
                        }
                    }

                    vo.setDictField(JSON.toJSONString(switchOptions, new SerializerFeature[]{SerializerFeature.UseSingleQuotes}));
                } else {
                    vo.setDictField("is_open");
                }
            }

            Map extendParams = new HashMap(5);
            if (StringUtils.isNotBlank(f.getFieldExtendJson())) {
                try {
                    JSONObject json = JSONObject.parseObject(f.getFieldExtendJson());
                    if (json != null) {
                        extendParams.putAll(json.getInnerMap());
                    }
                } catch (JSONException var13) {
                }
            }

            vo.setExtendParams(extendParams);
            if (!extendParams.isEmpty() && extendParams.containsKey("picker")) {
                String pickerVal = (String)extendParams.get("picker");
                if (oConvertUtils.isNotEmpty(pickerVal)) {
                    if (pickerVal.trim().equalsIgnoreCase("default")) {
                        extendParams.remove("picker");
                    } else if ("date".equalsIgnoreCase(f.getFieldShowType()) && oConvertUtils.isNotEmpty(f.getFieldDefaultValue())) {
                        org.jeecg.modules.online.cgform.converter.dirb.b dfc = new org.jeecg.modules.online.cgform.converter.dirb.b(f);
                        f.setFieldDefaultValue(dfc.converterToVal(f.getFieldDefaultValue()));
                    }
                }
            }

            if ("popup".equals(f.getFieldShowType()) || "popup_dict".equals(f.getFieldShowType())) {
                boolean popupMulti = true;
                Object popupMultiObj = extendParams.get("popupMulti");
                if (popupMultiObj != null) {
                    popupMulti = (Boolean)popupMultiObj;
                }

                extendParams.put("popupMulti", popupMulti);
            }

            vo.setSort("1".equals(f.getSortFlag()) ? "Y" : "N");
            vo.setReadonly(Integer.valueOf(1).equals(f.getIsReadOnly()) ? "Y" : "N");
            if (oConvertUtils.isNotEmpty(f.getFieldDefaultValue()) && !f.getFieldDefaultValue().trim().startsWith("${") && !f.getFieldDefaultValue().trim().startsWith("#{") && !f.getFieldDefaultValue().trim().startsWith("{{")) {
                vo.setDefaultVal(f.getFieldDefaultValue());
            }

            if (("file".equals(f.getFieldShowType()) || "image".equals(f.getFieldShowType())) && oConvertUtils.isNotEmpty(f.getFieldExtendJson())) {
                JSONObject json = JSONObject.parseObject(f.getFieldExtendJson());
                if (oConvertUtils.isNotEmpty(json.getString("uploadnum"))) {
                    vo.setUploadnum(json.getString("uploadnum"));
                }
            }

            originalColumns.add(vo);
            if (f.getIsShowForm() == 1 || f.getIsShowList() == 1 || f.getIsQuery() == 1) {
                columns.add(vo);
            }
        }

        return foreignKeys;
    }

    private String b(String dbType) {
        dbType = dbType.toLowerCase();
        if (dbType.indexOf("int") >= 0) {
            return "java.lang.Integer";
        } else if (dbType.indexOf("double") >= 0) {
            return "java.lang.Double";
        } else if (dbType.indexOf("decimal") >= 0) {
            return "java.math.BigDecimal";
        } else {
            return dbType.indexOf("date") >= 0 ? "java.util.Date" : "java.lang.String";
        }
    }

    public void addCrazyFormData(String tbname, JSONObject json) throws org.jeecg.modules.online.config.exception.a, UnsupportedEncodingException {
        OnlCgformHead head = (OnlCgformHead)this.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tbname));
        if (head == null) {
            throw new org.jeecg.modules.online.config.exception.a("数据库主表[" + tbname + "]不存在");
        } else {
            if (head.getTableType() == 2) {
                String subTables = head.getSubTableStr();
                if (subTables != null) {
                    String[] arr = subTables.split(",");

                    for(String tb : arr) {
                        JSONArray jsonArray = this.a(tb, json);
                        if (!CollectionUtils.isEmpty(jsonArray)) {
                            OnlCgformHead temp = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tb));
                            if (temp != null) {
                                List<OnlCgformField> subFieldList = this.fieldService.list((Wrapper)(new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, temp.getId()));
                                String sublinkField = "";
                                String subLinkValue = null;

                                for(OnlCgformField field : subFieldList) {
                                    if (!oConvertUtils.isEmpty(field.getMainField())) {
                                        sublinkField = field.getDbFieldName();
                                        String mainField = field.getMainField();
                                        subLinkValue = json.getString(mainField);
                                    }
                                }

                                for(int i = 0; i < jsonArray.size(); ++i) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    if (subLinkValue != null) {
                                        jsonObject.put(sublinkField, subLinkValue);
                                    }

                                    this.fieldService.executeInsertSQL(org.jeecg.modules.online.cgform.dird.d.c(tb, subFieldList, jsonObject));
                                }
                            }
                        }
                    }
                }
            }

            this.fieldService.saveFormData(head.getId(), tbname, json, true);
        }
    }

    public void editCrazyFormData(String tbname, JSONObject json) throws org.jeecg.modules.online.config.exception.a, UnsupportedEncodingException {
        OnlCgformHead head = (OnlCgformHead)this.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tbname));
        if (head == null) {
            throw new org.jeecg.modules.online.config.exception.a("数据库主表[" + tbname + "]不存在");
        } else {
            if (head.getTableType() == 2) {
                String subTables = head.getSubTableStr();
                if (oConvertUtils.isNotEmpty(subTables)) {
                    String[] arr = subTables.split(",");

                    for(String tb : arr) {
                        OnlCgformHead temp = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tb));
                        if (temp != null) {
                            List<OnlCgformField> subFieldList = this.fieldService.list((Wrapper)(new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, temp.getId()));
                            String sublinkField = "";
                            String subLinkValue = null;

                            for(OnlCgformField field : subFieldList) {
                                if (!oConvertUtils.isEmpty(field.getMainField())) {
                                    sublinkField = field.getDbFieldName();
                                    String mainField = field.getMainField();
                                    subLinkValue = json.getString(mainField);
                                }
                            }

                            if (!oConvertUtils.isEmpty(subLinkValue)) {
                                this.fieldService.deleteAutoList(tb, sublinkField, subLinkValue);
                                JSONArray jsonArray = this.a(tb, json);
                                if (!CollectionUtils.isEmpty(jsonArray)) {
                                    for(int i = 0; i < jsonArray.size(); ++i) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        if (subLinkValue != null) {
                                            jsonObject.put(sublinkField, subLinkValue);
                                        }

                                        this.fieldService.executeInsertSQL(org.jeecg.modules.online.cgform.dird.d.c(tb, subFieldList, jsonObject));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            this.fieldService.editFormData(head.getId(), tbname, json, true);
        }
    }

    private JSONArray a(String onlTb, JSONObject dataJson) {
        try {
            JSONArray subData = dataJson.getJSONArray("sub_table_design_" + onlTb);
            if (CollectionUtils.isEmpty(subData)) {
                subData = dataJson.getJSONArray("sub-table-design_" + onlTb);
                if (CollectionUtils.isEmpty(subData)) {
                    subData = dataJson.getJSONArray("sub-table-one2one_" + onlTb);
                    return subData;
                } else {
                    return subData;
                }
            } else {
                return subData;
            }
        } catch (Exception e) {
            a.error("表单设计器同步到Online，输入的子表数据不合法，已忽略", e);
            return null;
        }
    }

    public Integer getMaxCopyVersion(String physicId) {
        Integer v = ((OnlCgformHeadMapper)this.baseMapper).getMaxCopyVersion(physicId);
        return v == null ? 0 : v;
    }

    public void copyOnlineTableConfig(OnlCgformHead physicTable) throws Exception {
        String id = physicTable.getId();
        OnlCgformHead copy = new OnlCgformHead();
        String copyid = UUIDGenerator.generate();
        copy.setId(copyid);
        copy.setPhysicId(id);
        copy.setCopyType(1);
        copy.setCopyVersion(physicTable.getTableVersion());
        copy.setTableVersion(1);
        copy.setTableName(this.e(id, physicTable.getTableName()));
        copy.setTableTxt(physicTable.getTableTxt());
        copy.setFormCategory(physicTable.getFormCategory());
        copy.setFormTemplate(physicTable.getFormTemplate());
        copy.setFormTemplateMobile(physicTable.getFormTemplateMobile());
        copy.setIdSequence(physicTable.getIdSequence());
        copy.setIdType(physicTable.getIdType());
        copy.setIsCheckbox(physicTable.getIsCheckbox());
        copy.setIsPage(physicTable.getIsPage());
        copy.setIsTree(physicTable.getIsTree());
        copy.setQueryMode(physicTable.getQueryMode());
        copy.setTableType(1);
        copy.setIsDbSynch("N");
        copy.setIsDesForm(physicTable.getIsDesForm());
        copy.setDesFormCode(physicTable.getDesFormCode());
        copy.setTreeParentIdField(physicTable.getTreeParentIdField());
        copy.setTreeFieldname(physicTable.getTreeFieldname());
        copy.setTreeIdField(physicTable.getTreeIdField());
        copy.setRelationType((Integer)null);
        copy.setTabOrderNum((Integer)null);
        copy.setSubTableStr((String)null);
        copy.setThemeTemplate(physicTable.getThemeTemplate());
        copy.setScroll(physicTable.getScroll());
        copy.setExtConfigJson(physicTable.getExtConfigJson());

        List<OnlCgformField> list = this.fieldService.list((new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, id));
        for(OnlCgformField f : list) {
            OnlCgformField field = new OnlCgformField();
            field.setCgformHeadId(copyid);
            this.a(f, field);
            this.fieldService.save(field);
        }

        ((OnlCgformHeadMapper)this.baseMapper).insert(copy);
    }

    public void initCopyState(List<OnlCgformHead> headList) {
        List idList = ((OnlCgformHeadMapper)this.baseMapper).queryCopyPhysicId();

        for(OnlCgformHead temp : headList) {
            if (idList.contains(temp.getId())) {
                temp.setHascopy(1);
            } else {
                temp.setHascopy(0);
            }
        }

    }

    public void deleteBatch(String ids, String flag) {
        String[] arr = ids.split(",");
        if ("1".equals(flag)) {
            for(String id : arr) {
                try {
                    this.deleteRecordAndTable(id);
                } catch (org.jeecg.modules.online.config.exception.a e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            this.removeByIds(Arrays.asList(arr));
        }

    }

    public void updateParentNode(OnlCgformHead head, String dataId) {
        if ("Y".equals(head.getIsTree())) {
            String tbname = org.jeecg.modules.online.cgform.dird.d.f(head.getTableName());
            String pidField = head.getTreeParentIdField();
            Map param = this.d(tbname, dataId);
            String pid = null;
            if (param.get(pidField) != null && !"0".equals(param.get(pidField))) {
                pid = param.get(pidField).toString();
            } else if (param.get(pidField.toUpperCase()) != null && !"0".equals(param.get(pidField.toUpperCase()))) {
                pid = param.get(pidField.toUpperCase()).toString();
            }

            if (pid != null) {
                Integer cnt = ((OnlCgformHeadMapper)this.baseMapper).queryChildNode(tbname, pidField, pid);
                if (cnt == 1) {
                    String hasChild = head.getTreeIdField();
                    this.fieldService.updateTreeNodeNoChild(tbname, hasChild, pid);
                }
            }
        }

    }

    private void b(OnlCgformHead head, List<OnlCgformField> fields) {
        List<OnlCgformHead> copyList = this.list((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getPhysicId, head.getId()));
        if (copyList != null && copyList.size() > 0) {
            for(OnlCgformHead copy : copyList) {
                List<OnlCgformField> old_columns = this.fieldService.list((Wrapper)(new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, copy.getId()));
                if (old_columns != null && old_columns.size() != 0) {
                    Map<String,Object> oldMap = new HashMap(5);

                    for(OnlCgformField old : old_columns) {
                        oldMap.put(old.getDbFieldName(), 1);
                    }

                    Map<String,Object> newMap = new HashMap(5);

                    for(OnlCgformField temp : fields) {
                        newMap.put(temp.getDbFieldName(), 1);
                    }

                    List<String> common_columns = new ArrayList();
                    List new_columns = new ArrayList();

                    for(String fieldName : newMap.keySet()) {
                        if (oldMap.get(fieldName) == null) {
                            new_columns.add(fieldName);
                        } else {
                            common_columns.add(fieldName);
                        }
                    }

                    List del_columns = new ArrayList();

                    for(String fieldName : oldMap.keySet()) {
                        if (newMap.get(fieldName) == null) {
                            del_columns.add(fieldName);
                        }
                    }

                    if (del_columns.size() > 0) {
                        for(OnlCgformField temp : old_columns) {
                            if (del_columns.contains(temp.getDbFieldName())) {
                                this.fieldService.removeById(temp.getId());
                            }
                        }
                    }

                    if (new_columns.size() > 0) {
                        for(OnlCgformField temp : fields) {
                            if (new_columns.contains(temp.getDbFieldName())) {
                                OnlCgformField col = new OnlCgformField();
                                col.setCgformHeadId(copy.getId());
                                this.a(temp, col);
                                this.fieldService.save(col);
                            }
                        }
                    }

                    if (common_columns.size() > 0) {
                        for(String fieldName : common_columns) {
                            this.b(fieldName, fields, old_columns);
                        }
                    }
                } else {
                    for(OnlCgformField temp : fields) {
                        OnlCgformField col = new OnlCgformField();
                        col.setCgformHeadId(copy.getId());
                        this.a(temp, col);
                        this.fieldService.save(col);
                    }
                }
            }
        }

    }

    private void b(String fieldName, List<OnlCgformField> newCols, List<OnlCgformField> copyCols) {
        OnlCgformField newCol = null;

        for(OnlCgformField temp : newCols) {
            if (fieldName.equals(temp.getDbFieldName())) {
                newCol = temp;
            }
        }

        OnlCgformField copyCol = null;

        for(OnlCgformField temp : copyCols) {
            if (fieldName.equals(temp.getDbFieldName())) {
                copyCol = temp;
            }
        }

        if (newCol != null && copyCol != null) {
            boolean flag = false;
            if (!newCol.getDbType().equals(copyCol.getDbType())) {
                copyCol.setDbType(newCol.getDbType());
                flag = true;
            }

            if (newCol.getDbDefaultVal() != null && !newCol.getDbDefaultVal().equals(copyCol.getDbDefaultVal())) {
                copyCol.setDbDefaultVal(newCol.getDbDefaultVal());
                flag = true;
            }

            if (!newCol.getDbLength().equals(copyCol.getDbLength())) {
                copyCol.setDbLength(newCol.getDbLength());
                flag = true;
            }

            if (newCol.getDbIsNull() != copyCol.getDbIsNull()) {
                copyCol.setDbIsNull(newCol.getDbIsNull());
                flag = true;
            }

            if (flag) {
                this.fieldService.updateById(copyCol);
            }
        }

    }

    private void a(OnlCgformField meta, OnlCgformField dest) {
        dest.setDbDefaultVal(meta.getDbDefaultVal());
        dest.setDbFieldName(meta.getDbFieldName());
        dest.setDbFieldNameOld(meta.getDbFieldNameOld());
        dest.setDbFieldTxt(meta.getDbFieldTxt());
        dest.setDbIsKey(meta.getDbIsKey());
        dest.setDbIsNull(meta.getDbIsNull());
        dest.setDbLength(meta.getDbLength());
        dest.setDbPointLength(meta.getDbPointLength());
        dest.setDbType(meta.getDbType());
        dest.setDictField(meta.getDictField());
        dest.setDictTable(meta.getDictTable());
        dest.setDictText(meta.getDictText());
        dest.setFieldExtendJson(meta.getFieldExtendJson());
        dest.setFieldHref(meta.getFieldHref());
        dest.setFieldLength(meta.getFieldLength());
        dest.setFieldMustInput(meta.getFieldMustInput());
        dest.setFieldShowType(meta.getFieldShowType());
        dest.setFieldValidType(meta.getFieldValidType());
        dest.setFieldDefaultValue(meta.getFieldDefaultValue());
        dest.setIsQuery(meta.getIsQuery());
        dest.setIsShowForm(meta.getIsShowForm());
        dest.setIsShowList(meta.getIsShowList());
        dest.setMainField(meta.getMainField());
        dest.setMainTable(meta.getMainTable());
        dest.setOrderNum(meta.getOrderNum());
        dest.setQueryMode(meta.getQueryMode());
        dest.setIsReadOnly(meta.getIsReadOnly());
        dest.setSortFlag(meta.getSortFlag());
        dest.setQueryDefVal(meta.getQueryDefVal());
        dest.setQueryConfigFlag(meta.getQueryConfigFlag());
        dest.setQueryDictField(meta.getQueryDictField());
        dest.setQueryDictTable(meta.getQueryDictTable());
        dest.setQueryDictText(meta.getQueryDictText());
        dest.setQueryMustInput(meta.getQueryMustInput());
        dest.setQueryShowType(meta.getQueryShowType());
        dest.setQueryValidType(meta.getQueryValidType());
        dest.setConverter(meta.getConverter());
        dest.setDbIsPersist(meta.getDbIsPersist());
    }

    private void a(OnlCgformField field) {
        if ("Text".equals(field.getDbType()) || "Blob".equals(field.getDbType())) {
            field.setDbLength(0);
            field.setDbPointLength(0);
        }

    }

    private String e(String physicId, String metaName) {
        List ls = ((OnlCgformHeadMapper)this.baseMapper).queryAllCopyTableName(physicId);
        int max = 0;
        if (ls != null || ls.size() > 0) {
            for(int i = 0; i < ls.size(); ++i) {
                String name = (String)ls.get(i);
                int k = Integer.parseInt(name.split("\\$")[1]);
                if (k > max) {
                    max = k;
                }
            }
        }

        ++max;
        return metaName + "$" + max;
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public String deleteDataByCode(String cgformCode, String dataIds) {
        OnlCgformHead head = (OnlCgformHead)super.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, cgformCode));
        if (head == null) {
            throw new JeecgBootException("实体不存在");
        } else {
            String tableName = head.getTableName();

            try {
                if (dataIds.indexOf(",") > 0) {
                    this.onlCgformFieldService.deleteAutoListById(tableName, dataIds);
                } else {
                    this.deleteOneTableInfo(head.getId(), dataIds);
                }

                return tableName;
            } catch (Exception e) {
                a.error("OnlCgformApiController.formEdit()发生异常：" + e.getMessage(), e);
                throw new JeecgBootException("删除失败：" + e.getMessage());
            }
        }
    }

    public JSONObject queryAllDataByTableNameForDesform(String tableName, String dataIds) throws org.jeecg.modules.online.config.exception.a {
        JSONObject json = new JSONObject();
        LambdaQueryWrapper<OnlCgformHead> mainQueryWrapper = new LambdaQueryWrapper();
        mainQueryWrapper.eq(OnlCgformHead::getTableName, tableName);
        OnlCgformHead head = (OnlCgformHead)super.getOne(mainQueryWrapper);
        if (head == null) {
            throw new JeecgBootException("表单数据不存在！");
        } else {
            Map allDataMap = this.queryManyFormData(head.getId(), dataIds);
            if (allDataMap == null) {
                throw new JeecgBootException("表单数据查询失败！");
            } else {
                JSONObject allData = JSON.parseObject(JSON.toJSONString(allDataMap));
                String subTableStr = head.getSubTableStr();
                if (oConvertUtils.isNotEmpty(subTableStr)) {
                    List subTableNameList = new ArrayList(Arrays.asList(subTableStr.split(",")));
                    LambdaQueryWrapper<OnlCgformHead> subQueryWrapper = new LambdaQueryWrapper();
                    subQueryWrapper.in(OnlCgformHead::getTableName, subTableNameList);
                    List<OnlCgformHead> subTableList = super.list(subQueryWrapper);
                    JSONObject one2manyData = new JSONObject();
                    JSONObject one2oneData = new JSONObject();

                    for(OnlCgformHead subCgform : subTableList) {
                        JSONArray subDataList = allData.getJSONArray(subCgform.getTableName());
                        if (subDataList != null && subDataList.size() > 0) {
                            if (0 == subCgform.getRelationType()) {
                                one2manyData.put(subCgform.getTableName(), subDataList);
                            } else {
                                JSONObject subData = subDataList.getJSONObject(0);
                                one2oneData.put(subCgform.getTableName(), subData);
                            }
                        }

                        allData.remove(subCgform.getTableName());
                    }

                    json.put("one2one", one2oneData);
                    json.put("one2many", one2manyData);
                }

                json.put("main", allData);
                return json;
            }
        }
    }

    public OnlCgformHead copyOnlineTable(String id, String tableName) {
        LambdaQueryWrapper<OnlCgformHead> tableQuery = new LambdaQueryWrapper();
        tableQuery.eq(OnlCgformHead::getTableName, tableName);
        Long count = ((OnlCgformHeadMapper)this.baseMapper).selectCount(tableQuery);
        if (count != null && count >= 1L) {
            throw new JeecgBootException("表名已经存在!");
        } else {
            OnlCgformHead head = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectById(id);
            if (head == null) {
                throw new JeecgBootException("表不存在!");
            } else {
                OnlCgformHead copyObj = new OnlCgformHead();
                BeanUtils.copyProperties(head, copyObj);
                String newId = org.jeecg.modules.online.cgform.dird.d.a();
                copyObj.setId(newId);
                copyObj.setSubTableStr((String)null);
                copyObj.setTableName(tableName);
                copyObj.setTableVersion(1);
                copyObj.setIsDbSynch("N");
                copyObj.setCreateBy((String)null);
                copyObj.setCreateTime((Date)null);
                copyObj.setUpdateBy((String)null);
                copyObj.setUpdateTime((Date)null);
                LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
                query.eq(OnlCgformField::getCgformHeadId, id);
                List<OnlCgformField> list = this.fieldService.list(query);
                List fields = new ArrayList();
                if (list != null && list.size() > 0) {
                    for(OnlCgformField temp : list) {
                        OnlCgformField field = new OnlCgformField();
                        BeanUtils.copyProperties(temp, field);
                        field.setCgformHeadId(newId);
                        field.setMainField((String)null);
                        field.setMainTable((String)null);
                        field.setId((String)null);
                        field.setCreateBy((String)null);
                        field.setCreateTime((Date)null);
                        field.setUpdateBy((String)null);
                        field.setUpdateTime((Date)null);
                        fields.add(field);
                    }
                }

                LambdaQueryWrapper<OnlCgformIndex> indexQuery = new LambdaQueryWrapper();
                indexQuery.eq(OnlCgformIndex::getCgformHeadId, id);
                List<OnlCgformIndex> indexList = this.indexService.list(indexQuery);
                List indexArrayList = new ArrayList();
                if (indexList != null && indexList.size() > 0) {
                    for(OnlCgformIndex temp : indexList) {
                        OnlCgformIndex oi = new OnlCgformIndex();
                        BeanUtils.copyProperties(temp, oi);
                        oi.setCgformHeadId(newId);
                        oi.setId((String)null);
                        oi.setCreateBy((String)null);
                        oi.setCreateTime((Date)null);
                        oi.setUpdateBy((String)null);
                        oi.setUpdateTime((Date)null);
                        indexArrayList.add(oi);
                    }
                }

                this.save(copyObj);
                this.fieldService.saveBatch(fields);
                this.indexService.saveBatch(indexArrayList);
                return copyObj;
            }
        }
    }

    public OnlCgformHead getTable(String code) throws org.jeecg.modules.online.config.exception.a {
        OnlCgformHead head = (OnlCgformHead)this.getById(code);
        if (head == null) {
            LambdaQueryWrapper query = (LambdaQueryWrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, code);
            head = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne(query);
        }

        if (head == null) {
            throw new org.jeecg.modules.online.config.exception.a("online表[" + code + "]不存在");
        } else {
            return head;
        }
    }

    private b getOnlineDataBaseConfig() {
        if (oConvertUtils.isEmpty(this.onlineDatasource)) {
            return this.dataBaseConfig;
        } else {
            DataSourceProperty db = OSSCommonUtils.getDataSourceProperty(this.onlineDatasource);
            if (db == null) {
                a.error("jeecg.online.datasource配置错误,获取不到数据源返回master");
                return this.dataBaseConfig;
            } else {
                b config = new b();
                config.setDriverClassName(db.getDriverClassName());
                config.setPassword(db.getPassword());
                config.setUsername(db.getUsername());
                config.setUrl(db.getUrl());
                config.setDmDataBaseConfig(new org.jeecg.modules.online.config.model.c());
                return config;
            }
        }
    }
}
