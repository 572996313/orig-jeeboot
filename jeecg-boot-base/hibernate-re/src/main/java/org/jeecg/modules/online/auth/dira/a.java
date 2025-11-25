//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.auth.dira;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Generated;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.auth.entity.OnlAuthData;
import org.jeecg.modules.online.auth.entity.OnlAuthPage;
import org.jeecg.modules.online.auth.entity.OnlAuthRelation;
import org.jeecg.modules.online.auth.service.IOnlAuthDataService;
import org.jeecg.modules.online.auth.service.IOnlAuthPageService;
import org.jeecg.modules.online.auth.service.IOnlAuthRelationService;
import org.jeecg.modules.online.auth.vo.AuthColumnVO;
import org.jeecg.modules.online.auth.vo.AuthPageVO;
import org.jeecg.modules.online.cgform.dird.d;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.service.IOnlCgformButtonService;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("onlCgformAuthController")
@RequestMapping({"/online/cgform/api"})
public class a {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(a.class);
    @Autowired
    private IOnlCgformFieldService onlCgformFieldService;
    @Autowired
    private IOnlAuthDataService onlAuthDataService;
    @Autowired
    private IOnlAuthPageService onlAuthPageService;
    @Autowired
    private IOnlCgformButtonService onlCgformButtonService;
    @Autowired
    private IOnlAuthRelationService onlAuthRelationService;
    @Autowired
    private IOnlCgformHeadService onlCgformHeadService;

    @GetMapping({"/authData/{cgformId}"})
    public Result<List<OnlAuthData>> a(@PathVariable("cgformId") String cgformId) {
        Result result = new Result();
        LambdaQueryWrapper<OnlAuthData> query = new LambdaQueryWrapper();
        query.eq(OnlAuthData::getCgformId, cgformId);
        List ls = this.onlAuthDataService.list(query);
        result.setResult(ls);
        result.setSuccess(true);
        return result;
    }

    @PostMapping({"/authData"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<OnlAuthData> a(@RequestBody OnlAuthData onlAuthData) {
        Result result = new Result();

        try {
            this.onlAuthDataService.save(onlAuthData);
            result.success("添加成功！");
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            result.error500("操作失败");
        }

        return result;
    }

    @PutMapping({"/authData"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<OnlAuthData> b(@RequestBody OnlAuthData onlAuthData) {
        Result result = new Result();
        this.onlAuthDataService.updateById(onlAuthData);
        result.success("编辑成功！");
        return result;
    }

    @DeleteMapping({"/authData/{id}"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> b(@PathVariable("id") String id) {
        this.onlAuthDataService.deleteOne(id);
        return Result.ok("删除成功!");
    }

    @PostMapping({"/createAiTestAuthData"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> a(@RequestBody JSONObject json) {
        Result result = new Result();

        try {
            this.onlAuthDataService.createAiTestAuthData(json);
            result.success("添加成功！");
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            result.error500("操作失败");
        }

        return result;
    }

    @GetMapping({"/authButton/{cgformId}"})
    public Result<Map<String, Object>> c(@PathVariable("cgformId") String cgformId) {
        Result result = new Result();
        LambdaQueryWrapper<OnlCgformButton> buttonQuery = (new LambdaQueryWrapper<OnlCgformButton>()).eq(OnlCgformButton::getCgformHeadId, cgformId).eq(OnlCgformButton::getButtonStatus, "1").select(OnlCgformButton::getButtonCode, OnlCgformButton::getButtonName, OnlCgformButton::getButtonStyle);
        List buttonList = this.onlCgformButtonService.list(buttonQuery);
        LambdaQueryWrapper query = (new LambdaQueryWrapper<OnlAuthPage>()).eq(OnlAuthPage::getCgformId, cgformId).eq(OnlAuthPage::getType, 2);
        List authList = this.onlAuthPageService.list(query);
        Map map = new HashMap(5);
        OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getById(cgformId);
        Integer tableType = head.getTableType();
        if (org.jeecg.modules.online.cgform.enums.a.c.equals(tableType)) {
            LambdaQueryWrapper<OnlCgformField> fieldQuery = new LambdaQueryWrapper();
            fieldQuery.eq(OnlCgformField::getCgformHeadId, cgformId);
            fieldQuery.isNotNull(OnlCgformField::getMainTable);
            List<OnlCgformField> foreignFields = this.onlCgformFieldService.list(fieldQuery);
            if (oConvertUtils.listIsNotEmpty(foreignFields)) {
                List mainTableNames = (List)foreignFields.stream().map(OnlCgformField::getMainTable).collect(Collectors.toList());
                List mainTables = this.onlCgformHeadService.list((Wrapper)((LambdaQueryWrapper<OnlCgformHead>)Wrappers.lambdaQuery(OnlCgformHead.class).in(OnlCgformHead::getTableName, mainTableNames)).eq(OnlCgformHead::getTableType, org.jeecg.modules.online.cgform.enums.a.e));
                if (oConvertUtils.listIsNotEmpty(mainTables)) {
                    map.put("mainThemeTemplate", ((OnlCgformHead)mainTables.get(0)).getThemeTemplate());
                    map.put("mainRelationType", head.getRelationType());
                }
            }
        }

        map.put("buttonList", buttonList);
        map.put("authList", authList);
        result.setResult(map);
        result.setSuccess(true);
        return result;
    }

    @PostMapping({"/authButton"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<OnlAuthPage> a(@RequestBody OnlAuthPage onlAuthPage) {
        Result result = new Result();

        try {
            String id = onlAuthPage.getId();
            boolean exist = false;
            if (oConvertUtils.isNotEmpty(id)) {
                OnlAuthPage meta = (OnlAuthPage)this.onlAuthPageService.getById(id);
                if (meta != null) {
                    exist = true;
                    meta.setStatus(1);
                    this.onlAuthPageService.updateById(meta);
                }
            }

            if (!exist) {
                onlAuthPage.setStatus(1);
                this.onlAuthPageService.save(onlAuthPage);
            }

            result.setResult(onlAuthPage);
            result.success("操作成功！");
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            result.error500("操作失败");
        }

        return result;
    }

    @PutMapping({"/authButton/{id}"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> d(@PathVariable("id") String id) {
        LambdaUpdateWrapper updateWrapper = (LambdaUpdateWrapper)((LambdaUpdateWrapper<OnlAuthPage>)(new UpdateWrapper<OnlAuthPage>()).lambda().eq(OnlAuthPage::getId, id)).set(OnlAuthPage::getStatus, 0);
        this.onlAuthPageService.update(updateWrapper);
        return Result.ok("操作成功");
    }

    @GetMapping({"/authColumn/{cgformId}"})
    public Result<List<AuthColumnVO>> e(@PathVariable("cgformId") String cgformId) {
        Result result = new Result();
        LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
        query.eq(OnlCgformField::getCgformHeadId, cgformId);
        query.orderByAsc(OnlCgformField::getOrderNum);
        List<OnlCgformField> fieldList = this.onlCgformFieldService.list(query);
        if (fieldList == null || fieldList.size() == 0) {
            Result.error("未找到对应字段信息!");
        }

        LambdaQueryWrapper query2 = (new LambdaQueryWrapper<OnlAuthPage>()).eq(OnlAuthPage::getCgformId, cgformId).eq(OnlAuthPage::getType, 1);
        List authList = this.onlAuthPageService.list(query2);
        List ls = new ArrayList();
        List enableAuthCols = new ArrayList();

        for(OnlCgformField field : fieldList) {
            AuthColumnVO vo = new AuthColumnVO(field);
            Integer status = 0;
            boolean listShow = false;
            boolean formshow = false;
            boolean formEdit = false;

            for(int i = 0; i < authList.size(); ++i) {
                OnlAuthPage temp = (OnlAuthPage)authList.get(i);
                if (field.getDbFieldName().equals(temp.getCode())) {
                    status = temp.getStatus();
                    if (temp.getPage() == 3 && temp.getControl() == 5) {
                        listShow = true;
                    }

                    if (temp.getPage() == 5) {
                        if (temp.getControl() == 5) {
                            formshow = true;
                        } else if (temp.getControl() == 3) {
                            formEdit = true;
                        }
                    }
                }
            }

            vo.setStatus(status);
            vo.setListShow(listShow);
            vo.setFormShow(formshow);
            vo.setFormEditable(formEdit);
            if (status == 1) {
                enableAuthCols.add(vo);
            } else {
                ls.add(vo);
            }
        }

        enableAuthCols.addAll(ls);
        result.setResult(enableAuthCols);
        Result.ok("加载字段权限数据完成");
        return result;
    }

    @PutMapping({"/authColumn"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> a(@RequestBody AuthColumnVO authColumnVO) {
        Result result = new Result();

        try {
            if (authColumnVO.getStatus() == 1) {
                this.onlAuthPageService.enableAuthColumn(authColumnVO);
            } else {
                this.onlAuthPageService.disableAuthColumn(authColumnVO);
            }

            result.success("操作成功！");
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            result.error500("操作失败");
        }

        return result;
    }

    @PutMapping({"/authColumn/batch"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> a(@RequestBody List<AuthColumnVO> authColumnVOList) {
        Result result = new Result();

        try {
            this.onlAuthPageService.updateAuthColumnBatch(authColumnVOList);
            result.success("操作成功！");
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            result.error500("操作失败");
        }

        return result;
    }

    @PostMapping({"/authColumn"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> b(@RequestBody AuthColumnVO authColumnVO) {
        Result result = new Result();

        try {
            this.onlAuthPageService.switchAuthColumn(authColumnVO);
            result.success("操作成功！");
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            result.error500("操作失败");
        }

        return result;
    }

    @PostMapping({"/authColumn/batch"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> b(@RequestBody List<AuthColumnVO> authColumnVOList) {
        Result result = new Result();

        try {
            this.onlAuthPageService.switchAuthColumnBatch(authColumnVOList);
            result.success("操作成功！");
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            result.error500("操作失败");
        }

        return result;
    }

    @GetMapping({"/authPage/{cgformId}/{type}"})
    public Result<List<AuthPageVO>> a(@PathVariable("cgformId") String cgformId, @PathVariable("type") Integer type) {
        Result result = new Result();
        List authList = this.onlAuthPageService.queryAuthByFormId(cgformId, type);
        result.setResult(authList);
        result.setSuccess(true);
        return result;
    }

    @GetMapping({"/validAuthData/{cgformId}"})
    public Result<List<OnlAuthData>> f(@PathVariable("cgformId") String cgformId) {
        Result result = new Result();
        LambdaQueryWrapper<OnlAuthData> query = (new LambdaQueryWrapper<OnlAuthData>()).eq(OnlAuthData::getCgformId, cgformId).eq(OnlAuthData::getStatus, 1)
                .select(OnlAuthData::getId, OnlAuthData::getRuleName);
        List authList = this.onlAuthDataService.list(query);
        result.setResult(authList);
        result.setSuccess(true);
        return result;
    }

    @GetMapping({"/roleAuth"})
    public Result<List<OnlAuthRelation>> a(@RequestParam("roleId") String roleId, @RequestParam("cgformId") String cgformId, @RequestParam("type") Integer type, @RequestParam("authMode") String authMode) {
        Result result = new Result();
        LambdaQueryWrapper query = (new LambdaQueryWrapper<OnlAuthRelation>()).eq(OnlAuthRelation::getRoleId, roleId).eq(OnlAuthRelation::getCgformId, cgformId).eq(OnlAuthRelation::getType, type).eq(OnlAuthRelation::getAuthMode, authMode).select(OnlAuthRelation::getAuthId);
        List authList = this.onlAuthRelationService.list(query);
        result.setResult(authList);
        result.setSuccess(true);
        return result;
    }

    @PostMapping({"/roleColumnAuth/{roleId}/{cgformId}"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> a(@PathVariable("roleId") String roleId, @PathVariable("cgformId") String cgformId, @RequestBody JSONObject jsonObject) {
        Result result = new Result();
        JSONArray array = jsonObject.getJSONArray("authId");
        String authMode = jsonObject.getString("authMode");
        List authIds = array.toJavaList(String.class);
        this.onlAuthRelationService.saveRoleAuth(roleId, cgformId, 1, authMode, authIds);
        result.setSuccess(true);
        return result;
    }

    @PostMapping({"/roleButtonAuth/{roleId}/{cgformId}"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> b(@PathVariable("roleId") String roleId, @PathVariable("cgformId") String cgformId, @RequestBody JSONObject jsonObject) {
        JSONArray array = jsonObject.getJSONArray("authId");
        String authMode = jsonObject.getString("authMode");
        List authIds = array.toJavaList(String.class);
        return this.onlAuthRelationService.saveRoleAuth(roleId, cgformId, 2, authMode, authIds);
    }

    @PostMapping({"/roleDataAuth/{roleId}/{cgformId}"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> c(@PathVariable("roleId") String roleId, @PathVariable("cgformId") String cgformId, @RequestBody JSONObject jsonObject) {
        Result result = new Result();
        JSONArray array = jsonObject.getJSONArray("authId");
        String authMode = jsonObject.getString("authMode");
        List authIds = array.toJavaList(String.class);
        this.onlAuthRelationService.saveRoleAuth(roleId, cgformId, 3, authMode, authIds);
        result.setSuccess(true);
        return result;
    }

    @GetMapping({"/getAuthColumn/{desformCode}"})
    public Result<List<AuthColumnVO>> g(@PathVariable("desformCode") String desformCode) {
        OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, desformCode));
        if (head == null) {
            return Result.error("未找到对应字段信息!");
        } else {
            LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
            query.eq(OnlCgformField::getCgformHeadId, head.getId());
            query.orderByAsc(OnlCgformField::getOrderNum);
            List<OnlCgformField> fieldList = this.onlCgformFieldService.list(query);
            if (fieldList != null && !fieldList.isEmpty()) {
                List ls = new ArrayList();

                for(OnlCgformField field : fieldList) {
                    if (!d.i(field.getDbFieldName()) && !"bpm_status".equalsIgnoreCase(field.getDbFieldName())) {
                        AuthColumnVO vo = new AuthColumnVO(field);
                        vo.setTableName(head.getTableName());
                        vo.setTableNameTxt(head.getTableTxt());
                        vo.setIsMain(true);
                        ls.add(vo);
                    }
                }

                if (oConvertUtils.isNotEmpty(head.getSubTableStr())) {
                    for(String tbname : head.getSubTableStr().split(",")) {
                        OnlCgformHead subTable = this.onlCgformHeadService.getOne((new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tbname));
                        if (subTable != null) {
                            LambdaQueryWrapper subFieldQuery = (new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, subTable.getId());
                            List<OnlCgformField> subFieldList = this.onlCgformFieldService.list(subFieldQuery);
                            if (subFieldList != null) {
                                for(OnlCgformField field : subFieldList) {
                                    if (!d.i(field.getDbFieldName()) && (!oConvertUtils.isNotEmpty(field.getMainTable()) || !oConvertUtils.isNotEmpty(field.getMainField())) && !"bpm_status".equalsIgnoreCase(field.getDbFieldName())) {
                                        AuthColumnVO vo = new AuthColumnVO(field);
                                        vo.setTableName(subTable.getTableName());
                                        vo.setTableNameTxt(subTable.getTableTxt());
                                        vo.setIsMain(false);
                                        ls.add(vo);
                                    }
                                }
                            }
                        }
                    }
                }

                return Result.OK(ls);
            } else {
                return Result.error("未找到对应字段信息!");
            }
        }
    }
}
