//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.dira;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.exception.JeecgSqlInjectionException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.DynamicDataSourceModel;
import org.jeecg.common.util.SqlInjectionUtil;
import org.jeecg.common.util.TokenUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.config.mybatis.MybatisPlusSaasConfig;
import org.jeecg.modules.base.service.BaseCommonService;
import org.jeecg.modules.online.cgform.dird.d;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;
import org.jeecg.modules.online.cgreport.model.OnlCgreportModel;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportAiService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportHeadService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
import org.jeecg.modules.online.config.dirb.a;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("onlCgreportHeadController")
@RequestMapping({"/online/cgreport/head"})
public class b {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(b.class);
    @Lazy
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private IOnlCgreportHeadService onlCgreportHeadService;
    @Autowired
    private IOnlCgreportParamService onlCgreportParamService;
    @Autowired
    private IOnlCgreportItemService onlCgreportItemService;
    @Autowired
    private BaseCommonService baseCommonService;
    @Autowired
    private a onlReportQueryBlackListHandler;
    @Autowired
    IOnlCgreportAiService aiService;

    @GetMapping({"/parseSql"})
    @RequiresPermissions({"online:report:parseSql"})
    public Result<?> a(@RequestParam(name = "sql") String sql, @RequestParam(name = "dbKey",required = false) String dbKey) {
        if (StringUtils.isNotBlank(dbKey)) {
            DynamicDataSourceModel dbSource = this.sysBaseAPI.getDynamicDbSourceByCode(dbKey);
            if (dbSource == null) {
                return Result.error("数据源不存在");
            }
        }

        Map resultJson = new HashMap(5);
        List onlCgreportItemList = new ArrayList();
        List onlCgreportParamList = new ArrayList();
        List<String> fields = null;
        List<String> params = null;

        try {
            this.baseCommonService.addLog("Online报表，sql解析：" + sql, 2, 2);
            if (!this.onlReportQueryBlackListHandler.isPass(sql)) {
                return Result.error(this.onlReportQueryBlackListHandler.getError());
            }

            SqlInjectionUtil.specialFilterContentForOnlineReport(sql);
            fields = this.onlCgreportHeadService.getSqlFields(sql, dbKey);
            params = this.onlCgreportHeadService.getSqlParams(sql);
            int i = 1;

            for(String f : fields) {
                OnlCgreportItem t = new OnlCgreportItem();
                t.setFieldName(f.toLowerCase());
                t.setFieldTxt(f);
                t.setIsShow(1);
                t.setOrderNum(i);
                t.setId(d.a());
                t.setFieldType("String");
                onlCgreportItemList.add(t);
                ++i;
            }

            for(String p : params) {
                OnlCgreportParam param = new OnlCgreportParam();
                param.setParamName(p);
                param.setParamTxt(p);
                onlCgreportParamList.add(param);
            }

            resultJson.put("fields", onlCgreportItemList);
            resultJson.put("params", onlCgreportParamList);
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            String msg = "解析失败，";
            int i = e.getMessage().indexOf("Connection refused: connect");
            if (i == -1 && !e.getMessage().contains("Failed to obtain JDBC Connection")) {
                if (e.getMessage().indexOf("值可能存在SQL注入风险") != -1) {
                    msg = msg + "SQL可能存在SQL注入风险.";
                } else if (e.getMessage().indexOf("该报表sql没有数据") != -1) {
                    msg = msg + "报表sql查询数据为空，无法解析字段.";
                } else if (e.getMessage().indexOf("SqlServer不支持SQL内排序") != -1) {
                    msg = msg + "SqlServer不支持SQL内排序.";
                } else if (e.getMessage().contains("Unknown column")) {
                    msg = msg + "未知的字段名.";
                } else if (e instanceof JeecgSqlInjectionException) {
                    msg = msg + e.getMessage();
                } else if (e instanceof JeecgBootException) {
                    msg = msg + e.getMessage();
                } else {
                    msg = msg + "SQL语法错误.";
                }
            } else {
                msg = msg + "数据源连接失败.";
            }

            return Result.error(msg);
        }

        return Result.ok(resultJson);
    }

    @GetMapping({"/list"})
    public Result<IPage<OnlCgreportHead>> a(OnlCgreportHead onlCgreportHead, @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize, @RequestParam(name = "keywords",required = false) String keywords, HttpServletRequest req) {
        Result result = new Result();
        QueryWrapper<OnlCgreportHead> queryWrapper = QueryGenerator.initQueryWrapper(onlCgreportHead, req.getParameterMap());
        if (MybatisPlusSaasConfig.OPEN_SYSTEM_TENANT_CONTROL) {
            String tenantId = TokenUtils.getTenantIdByRequest(req);
            if (oConvertUtils.isNotEmpty(tenantId)) {
                queryWrapper.in("tenant_id", new Object[]{tenantId});
            }
        }

        if (oConvertUtils.isNotEmpty(keywords)) {
            queryWrapper.and((i) -> ((QueryWrapper)((QueryWrapper)i.like("code", keywords)).or()).like("name", keywords));
        }

        Page page = new Page((long)pageNo, (long)pageSize);
        IPage pageList = this.onlCgreportHeadService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @RequiresPermissions({"online:report:add"})
    @PostMapping({"/add"})
    public Result<?> a(@RequestBody OnlCgreportModel values) {
        Result result = new Result();

        try {
            String mainId = d.a();
            OnlCgreportHead head = values.getHead();
            List<OnlCgreportParam> params = values.getParams();
            List<OnlCgreportItem> items = values.getItems();
            head.setId(mainId);

            for(OnlCgreportParam param : params) {
                param.setId((String)null);
                param.setCgrheadId(mainId);
            }

            for(OnlCgreportItem item : items) {
                item.setId((String)null);
                item.setFieldName(item.getFieldName().trim().toLowerCase());
                item.setCgrheadId(mainId);
            }

            this.onlCgreportHeadService.save(head);
            this.onlCgreportParamService.saveBatch(params);
            this.onlCgreportItemService.saveBatch(items);
            result.success("添加成功！");
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            result.error500("操作失败");
        }

        return result;
    }

    @PutMapping({"/editAll"})
    @RequiresPermissions({"online:report:edit"})
    @CacheEvict(
        value = {"sys:cache:online:rp"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> b(@RequestBody OnlCgreportModel values) {
        try {
            return this.onlCgreportHeadService.editAll(values);
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            return Result.error("操作失败");
        }
    }

    @RequiresPermissions({"online:report:delete"})
    @DeleteMapping({"/delete"})
    public Result<?> a(@RequestParam(name = "id",required = true) String id) {
        return this.onlCgreportHeadService.delete(id);
    }

    @RequiresPermissions({"online:report:deleteBatch"})
    @DeleteMapping({"/deleteBatch"})
    public Result<?> b(@RequestParam(name = "ids",required = true) String ids) {
        return this.onlCgreportHeadService.bathDelete(ids.split(","));
    }

    @GetMapping({"/queryById"})
    public Result<OnlCgreportHead> c(@RequestParam(name = "id",required = true) String id) {
        Result result = new Result();
        OnlCgreportHead onlCgreportHead = (OnlCgreportHead)this.onlCgreportHeadService.getById(id);
        result.setResult(onlCgreportHead);
        return result;
    }

    @PostMapping({"/api/aigc"})
    public Result<?> b(@RequestParam(name = "cgformTableName") String cgformTableName, @RequestParam(name = "prompt") String prompt) {
        return this.aiService.genCgreportByCgform(cgformTableName, prompt);
    }
}
