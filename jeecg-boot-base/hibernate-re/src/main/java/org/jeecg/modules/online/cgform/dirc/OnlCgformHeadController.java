//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dirc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import lombok.Generated;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.config.TenantContext;
import org.jeecg.common.constant.enums.CgformEnum;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.TokenUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.config.mybatis.MybatisPlusSaasConfig;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceSql;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.service.IOnlCgformEnhanceService;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.config.exception.a;
import org.jeecgframework.codegenerate.database.DbReadTableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("onlCgformHeadController")
@RequestMapping({"/online/cgform/head"})
public class OnlCgformHeadController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(OnlCgformHeadController.class);
    @Autowired
    private IOnlCgformHeadService onlCgformHeadService;
    @Autowired
    private IOnlCgformFieldService onlCgformFieldService;
    @Autowired
    private IOnlCgformEnhanceService onlCgformEnhanceService;

    private static List<String> b = null;

    @Autowired
    ResourceLoader resourceLoader;
    private static String c;

    @GetMapping({"/list"})
    @PermissionData
    public Result<IPage<OnlCgformHead>> a(OnlCgformHead onlCgformHead, @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result result = new Result();
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(onlCgformHead, req.getParameterMap());
        if (MybatisPlusSaasConfig.OPEN_SYSTEM_TENANT_CONTROL) {
            String tenantStr = TenantContext.getTenant();
            Integer tenantId = oConvertUtils.getInteger(tenantStr, oConvertUtils.getInt(TokenUtils.getTenantIdByRequest(req), -1));
            if (oConvertUtils.isNotEmpty(tenantId)) {
                queryWrapper.in("tenant_id", new Object[]{tenantId});
            }
        }

        Page page = new Page((long)pageNo, (long)pageSize);
        IPage pageList = this.onlCgformHeadService.page(page, queryWrapper);
        if (onlCgformHead.getCopyType() != null && onlCgformHead.getCopyType() == 0) {
            this.onlCgformHeadService.initCopyState(pageList.getRecords());
        }

        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @PostMapping({"/add"})
    @RequiresPermissions({"online:form:add"})
    public Result<OnlCgformHead> a(@RequestBody OnlCgformHead onlCgformHead) {
        Result result = new Result();

        try {
            this.onlCgformHeadService.save(onlCgformHead);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }

        return result;
    }

    @PutMapping({"/edit"})
    @RequiresPermissions({"online:form:edit"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<OnlCgformHead> b(@RequestBody OnlCgformHead onlCgformHead) {
        Result result = new Result();
        OnlCgformHead onlCgformHeadEntity = (OnlCgformHead)this.onlCgformHeadService.getById(onlCgformHead.getId());
        if (onlCgformHeadEntity == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = this.onlCgformHeadService.updateById(onlCgformHead);
            if (ok) {
                result.success("修改成功!");
            }
        }

        return result;
    }

    @RequiresPermissions({"online:form:delete"})
    @DeleteMapping({"/delete"})
    public Result<?> a(@RequestParam(name = "id",required = true) String id) {
        try {
            this.onlCgformHeadService.deleteRecordAndTable(id);
        } catch (a e) {
            return Result.error("删除失败" + e.getMessage());
        } catch (SQLException e) {
            return Result.error("删除失败" + e.getMessage());
        }

        return Result.ok("删除成功!");
    }

    @RequiresPermissions({"online:form:remove"})
    @DeleteMapping({"/removeRecord"})
    public Result<?> b(@RequestParam(name = "id",required = true) String id) {
        try {
            this.onlCgformHeadService.deleteRecord(id);
        } catch (a e) {
            return Result.error("移除失败" + e.getMessage());
        } catch (SQLException e) {
            return Result.error("移除失败" + e.getMessage());
        }

        return Result.ok("移除成功!");
    }

    @RequiresPermissions({"online:form:deleteBatch"})
    @DeleteMapping({"/deleteBatch"})
    public Result<OnlCgformHead> a(@RequestParam(name = "ids",required = true) String ids, @RequestParam(name = "flag") String flag) {
        Result result = new Result();
        if (ids != null && !"".equals(ids.trim())) {
            this.onlCgformHeadService.deleteBatch(ids, flag);
            if ("1".equals(flag)) {
                result.success("删除成功!");
            } else {
                result.success("移除成功!");
            }
        } else {
            result.error500("参数不识别！");
        }

        return result;
    }

    @GetMapping({"/queryById"})
    public Result<OnlCgformHead> c(@RequestParam(name = "id",required = true) String id) {
        Result result = new Result();
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.onlCgformHeadService.getById(id);
        if (onlCgformHead == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(onlCgformHead);
            result.setSuccess(true);
        }

        return result;
    }

    @GetMapping({"/queryByTableNames"})
    public Result<?> d(@RequestParam(name = "tableNames",required = true) String tableNames) {
        LambdaQueryWrapper<OnlCgformHead> wrapper = new LambdaQueryWrapper();
        String[] arr = tableNames.split(",");
        wrapper.in(OnlCgformHead::getTableName, Arrays.asList(arr));
        List heads = this.onlCgformHeadService.list(wrapper);
        return heads == null ? Result.error("未找到对应实体") : Result.ok(heads);
    }

    @PostMapping({"/enhanceJs/{code}"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> a(@PathVariable("code") String code, @RequestBody OnlCgformEnhanceJs onlCgformEnhanceJs) {
        try {
            onlCgformEnhanceJs.setCgformHeadId(code);
            this.onlCgformHeadService.saveEnhance(onlCgformEnhanceJs);
            return Result.ok("保存成功!");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("保存失败!");
        }
    }

    @GetMapping({"/enhanceJs/{code}"})
    public Result<?> a(@PathVariable("code") String code, HttpServletRequest req) {
        try {
            String type = req.getParameter("type");
            OnlCgformEnhanceJs obj = this.onlCgformHeadService.queryEnhance(code, type);
            return obj == null ? Result.error("查询为空") : Result.ok(obj);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("查询失败!");
        }
    }

    @PutMapping({"/enhanceJs/{code}"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> b(@PathVariable("code") String code, @RequestBody OnlCgformEnhanceJs onlCgformEnhanceJs) {
        try {
            onlCgformEnhanceJs.setCgformHeadId(code);
            this.onlCgformHeadService.editEnhance(onlCgformEnhanceJs);
            return Result.ok("保存成功!");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("保存失败!");
        }
    }

    @GetMapping({"/enhanceButton/{formId}"})
    public Result<?> b(@PathVariable("formId") String formId, HttpServletRequest req) {
        try {
            List list = this.onlCgformHeadService.queryButtonList(formId);
            return list != null && list.size() != 0 ? Result.ok(list) : Result.error("查询为空");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("查询失败!");
        }
    }

    @GetMapping({"/enhanceSql/{formId}"})
    public Result<?> c(@PathVariable("formId") String formId, HttpServletRequest req) {
        List ls = this.onlCgformEnhanceService.queryEnhanceSqlList(formId);
        return Result.OK(ls);
    }

    @RequiresPermissions({"online:form:enhanceSql:save"})
    @PostMapping({"/enhanceSql/{formId}"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> a(@PathVariable("formId") String formId, @RequestBody OnlCgformEnhanceSql onlCgformEnhanceSql) {
        try {
            onlCgformEnhanceSql.setCgformHeadId(formId);
            if (this.onlCgformEnhanceService.checkOnlyEnhance(onlCgformEnhanceSql)) {
                this.onlCgformEnhanceService.saveEnhanceSql(onlCgformEnhanceSql);
                return Result.ok("保存成功!");
            } else {
                return Result.error("保存失败,该按钮已存在增强配置!");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("保存失败!");
        }
    }

    @RequiresPermissions({"online:form:enhanceSql:edit"})
    @PutMapping({"/enhanceSql/{formId}"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> b(@PathVariable("formId") String formId, @RequestBody OnlCgformEnhanceSql onlCgformEnhanceSql) {
        try {
            onlCgformEnhanceSql.setCgformHeadId(formId);
            if (this.onlCgformEnhanceService.checkOnlyEnhance(onlCgformEnhanceSql)) {
                this.onlCgformEnhanceService.updateEnhanceSql(onlCgformEnhanceSql);
                return Result.ok("保存成功!");
            } else {
                return Result.error("保存失败,该按钮已存在增强配置!");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("保存失败!");
        }
    }

    @RequiresPermissions({"online:form:enhanceSql:delete"})
    @DeleteMapping({"/enhanceSql"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> e(@RequestParam(name = "id",required = true) String id) {
        try {
            this.onlCgformEnhanceService.deleteEnhanceSql(id);
            return Result.ok("删除成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("删除失败!");
        }
    }

    @RequiresPermissions({"online:form:enhanceSql:batchDelete"})
    @DeleteMapping({"/deletebatchEnhanceSql"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> f(@RequestParam(name = "ids",required = true) String ids) {
        try {
            List idList = Arrays.asList(ids.split(","));
            this.onlCgformEnhanceService.deleteBatchEnhanceSql(idList);
            return Result.ok("删除成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("删除失败!");
        }
    }

    @GetMapping({"/enhanceJava/{formId}"})
    public Result<?> a(@PathVariable("formId") String formId, OnlCgformEnhanceJava onlCgformEnhanceJava) {
        List ls = this.onlCgformEnhanceService.queryEnhanceJavaList(formId);
        return Result.OK(ls);
    }

    @RequiresPermissions({"online:form:enhanceJava:save"})
    @PostMapping({"/enhanceJava/{formId}"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> b(@PathVariable("formId") String formId, @RequestBody OnlCgformEnhanceJava onlCgformEnhanceJava) {
        try {
            if ("1".equals(onlCgformEnhanceJava.getActiveStatus()) && !org.jeecg.modules.online.cgform.dird.d.a(onlCgformEnhanceJava)) {
                return Result.error("类实例化失败，请检查!");
            } else {
                onlCgformEnhanceJava.setCgformHeadId(formId);
                String buttonCode = onlCgformEnhanceJava.getButtonCode();
                if ("import".equals(buttonCode) || "export".equals(buttonCode) || "query".equals(buttonCode)) {
                    onlCgformEnhanceJava.setEvent("start");
                }

                if (this.onlCgformEnhanceService.checkOnlyEnhance(onlCgformEnhanceJava)) {
                    this.onlCgformEnhanceService.saveEnhanceJava(onlCgformEnhanceJava);
                    return Result.ok("保存成功!");
                } else {
                    return Result.error("保存失败：一个按钮、事件只能有一个增强！");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("保存失败!");
        }
    }

    @RequiresPermissions({"online:form:enhanceJava:edit"})
    @PutMapping({"/enhanceJava/{formId}"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> c(@PathVariable("formId") String formId, @RequestBody OnlCgformEnhanceJava onlCgformEnhanceJava) {
        try {
            if ("1".equals(onlCgformEnhanceJava.getActiveStatus()) && !org.jeecg.modules.online.cgform.dird.d.a(onlCgformEnhanceJava)) {
                return Result.error("类实例化失败，请检查!");
            } else {
                onlCgformEnhanceJava.setCgformHeadId(formId);
                String buttonCode = onlCgformEnhanceJava.getButtonCode();
                if ("import".equals(buttonCode) || "export".equals(buttonCode) || "query".equals(buttonCode)) {
                    onlCgformEnhanceJava.setEvent("start");
                }

                if (this.onlCgformEnhanceService.checkOnlyEnhance(onlCgformEnhanceJava)) {
                    this.onlCgformEnhanceService.updateEnhanceJava(onlCgformEnhanceJava);
                    return Result.ok("保存成功!");
                } else {
                    return Result.error("保存失败：一个按钮、事件只能有一个增强！");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("保存失败!");
        }
    }

    @DeleteMapping({"/enhanceJava"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> g(@RequestParam(name = "id",required = true) String id) {
        try {
            this.onlCgformEnhanceService.deleteEnhanceJava(id);
            return Result.ok("删除成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("删除失败!");
        }
    }

    @DeleteMapping({"/deleteBatchEnhanceJava"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> h(@RequestParam(name = "ids",required = true) String ids) {
        try {
            List idList = Arrays.asList(ids.split(","));
            this.onlCgformEnhanceService.deleteBatchEnhanceJava(idList);
            return Result.ok("删除成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("删除失败!");
        }
    }

    @RequiresPermissions({"online:form:queryTables"})
    @GetMapping({"/queryTables"})
    public Result<?> a(@RequestParam(name = "tableName",required = false) String tableName, @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        String username = JwtUtil.getUserNameByToken(req);
        new ArrayList();

        List<String> list;
        try {
            list = DbReadTableUtil.readAllTableNames();
        } catch (SQLException e1) {
            log.error(e1.getMessage(), e1);
            return Result.error("同步失败，未获取数据库表信息");
        }

        org.jeecg.modules.online.cgform.dird.d.b(list);
        list = org.jeecg.modules.online.cgform.dird.d.f(list);
        List onlineTables = this.onlCgformHeadService.queryOnlinetables();
        this.b();
        list.removeAll(onlineTables);
        List result = new ArrayList();

        for(String str : list) {
            if (!this.l(str)) {
                Map map = new HashMap(5);
                map.put("id", str);
                result.add(map);
            }
        }

        return Result.ok(result);
    }

    @RequiresPermissions({"online:form:importTable"})
    @PostMapping({"/transTables/{tbnames}"})
    public Result<?> d(@PathVariable("tbnames") String tbnames, HttpServletRequest req) {
        String username = JwtUtil.getUserNameByToken(req);
        if (oConvertUtils.isEmpty(tbnames)) {
            return Result.error("未识别的表名信息");
        } else if (c != null && c.equals(tbnames)) {
            return Result.error("不允许重复生成!");
        } else {
            c = tbnames;
            StringBuilder tips = new StringBuilder();
            boolean hasError = false;
            String[] arr = tbnames.split(",");

            for(int i = 0; i < arr.length; ++i) {
                String tbName = arr[i];
                if (oConvertUtils.isNotEmpty(tbName)) {
                    Long count = this.onlCgformHeadService.count((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tbName));
                    if (count <= 0L) {
                        log.info("[IP] [online数据库导入表]   --表名：" + tbName);

                        try {
                            this.onlCgformHeadService.saveDbTable2Online(tbName);
                            tips.append("表[").append(tbName).append("]导入成功。<br>");
                        } catch (Exception e) {
                            hasError = true;
                            tips.append("表[").append(tbName).append("]导入失败：").append(e.getMessage()).append("<br>");
                        }
                    }
                }
            }

            c = null;
            if (hasError) {
                return Result.error("导入完成，但有错误：<br>" + tips);
            } else {
                return Result.ok("全部导入完成！");
            }
        }
    }

    @RequiresPermissions({"online:codeGenerate:projectPath"})
    @GetMapping({"/rootFile"})
    public Result<?> a() {
        JSONArray array = new JSONArray();
        File[] roots = File.listRoots();

        for(File f : roots) {
            JSONObject item = new JSONObject();
            if (f.isDirectory()) {
                item.put("key", f.getAbsolutePath());
                item.put("title", f.getPath());
                item.put("opened", false);
                JSONObject scopedSlots = new JSONObject();
                scopedSlots.put("icon", "custom");
                item.put("scopedSlots", scopedSlots);
                item.put("isLeaf", f.listFiles() == null || f.listFiles().length == 0);
                array.add(item);
            }
        }

        return Result.ok(array);
    }

    @RequiresPermissions({"online:codeGenerate:projectPath"})
    @GetMapping({"/fileTree"})
    public Result<?> i(@RequestParam(name = "parentPath",required = true) String parentPath) {
        JSONArray array = new JSONArray();
        File file = new File(parentPath);
        File[] roots = file.listFiles();

        for(File f : roots) {
            if (f.isDirectory() && !f.isHidden() && oConvertUtils.isNotEmpty(f.getPath())) {
                JSONObject item = new JSONObject();
                item.put("key", f.getAbsolutePath());
                item.put("title", f.getPath().substring(f.getPath().lastIndexOf(File.separator) + 1));
                item.put("isLeaf", f.listFiles() == null || f.listFiles().length == 0);
                item.put("opened", false);
                JSONObject scopedSlots = new JSONObject();
                scopedSlots.put("icon", "custom");
                item.put("scopedSlots", scopedSlots);
                array.add(item);
            }
        }

        return Result.ok(array);
    }

    @GetMapping({"/tableInfo"})
    public Result<?> j(@RequestParam(name = "code",required = true) String code) {
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.onlCgformHeadService.getById(code);
        if (onlCgformHead == null) {
            return Result.error("未找到对应实体");
        } else {
            Map map = new HashMap(5);
            map.put("main", onlCgformHead);
            if (onlCgformHead.getTableType() == 2) {
                String subtable = onlCgformHead.getSubTableStr();
                if (oConvertUtils.isNotEmpty(subtable)) {
                    List subList = new ArrayList();
                    String[] arr = subtable.split(",");

                    for(String string : arr) {
                        LambdaQueryWrapper<OnlCgformHead> query = new LambdaQueryWrapper();
                        query.eq(OnlCgformHead::getTableName, string);
                        OnlCgformHead sub = (OnlCgformHead)this.onlCgformHeadService.getOne(query);
                        subList.add(sub);
                    }

                    Collections.sort(subList, new Comparator<OnlCgformHead>() {
                        public int compare(OnlCgformHead o1, OnlCgformHead o2) {
                            Integer o1OrderNum = o1.getTabOrderNum();
                            if (o1OrderNum == null) {
                                o1OrderNum = 0;
                            }

                            Integer o2OrderNum = o2.getTabOrderNum();
                            if (o2OrderNum == null) {
                                o2OrderNum = 0;
                            }

                            return o1OrderNum.compareTo(o2OrderNum);
                        }
                    });
                    map.put("sub", subList);
                }
            }

            Integer codeType = onlCgformHead.getTableType();
            if ("Y".equals(onlCgformHead.getIsTree())) {
                codeType = 3;
            }

            List ls = CgformEnum.getJspModelList(codeType);
            map.put("jspModeList", ls);
            map.put("projectPath", DbReadTableUtil.getProjectPath());
            return Result.ok(map);
        }
    }

    @PostMapping({"/copyOnline"})
    public Result<?> k(@RequestParam(name = "code",required = true) String id) {
        try {
            OnlCgformHead physicTable = (OnlCgformHead)this.onlCgformHeadService.getById(id);
            if (physicTable == null) {
                return Result.error("未找到对应实体");
            }

            this.onlCgformHeadService.copyOnlineTableConfig(physicTable);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.ok();
    }

    @GetMapping({"/copyOnlineTable/{id}"})
    public Result<?> b(@PathVariable("id") String id, @RequestParam(name = "tableName") String tableName) {
        try {
            this.onlCgformHeadService.copyOnlineTable(id, tableName);
        } catch (JeecgBootException e) {
            return Result.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }

        return Result.ok();
    }

    private boolean l(String tableName) {
        if (b != null) {
            for(String pre : b) {
                if (tableName.startsWith(pre) || tableName.startsWith(pre.toUpperCase())) {
                    return true;
                }
            }
        }

        return false;
    }

    private void b() {
        if (b == null) {
            ResourceBundle generateConfigResourceBundle = org.jeecg.modules.online.config.dirc.d.d("jeecg/jeecg_config");
            if (generateConfigResourceBundle != null && generateConfigResourceBundle.containsKey("exclude_table")) {
                String excludeTable = generateConfigResourceBundle.getString("exclude_table");
                if (excludeTable != null) {
                    b = Arrays.asList(excludeTable.split(","));
                    return;
                }
            }

            InputStream is = null;

            try {
                Resource resource = this.resourceLoader.getResource("classpath:jeecg" + File.separator + "jeecg_config.properties");
                is = resource.getInputStream();
                Properties properties = new Properties();
                properties.load(is);
                String excludeTable = properties.getProperty("exclude_table");
                if (excludeTable != null) {
                    b = Arrays.asList(excludeTable.split(","));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }
}
