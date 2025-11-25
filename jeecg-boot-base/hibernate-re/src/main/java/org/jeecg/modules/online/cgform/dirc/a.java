//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dirc;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import lombok.Generated;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.OnlineAuth;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.constant.enums.ModuleType;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.BrowserUtils;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.SqlInjectionUtil;
import org.jeecg.common.util.TokenUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.config.JeecgBaseConfig;
import org.jeecg.modules.online.auth.service.IOnlAuthPageService;
import org.jeecg.modules.online.cgform.dird.c;
import org.jeecg.modules.online.cgform.dird.g;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.model.TreeModel;
import org.jeecg.modules.online.cgform.model.b;
import org.jeecg.modules.online.cgform.service.IOnlCgformAiService;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.cgform.service.IOnlCgformSqlService;
import org.jeecg.modules.online.cgform.service.IOnlineJoinQueryService;
import org.jeecg.modules.online.cgform.service.IOnlineService;
import org.jeecg.modules.online.config.dirc.d;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.jeecgframework.codegenerate.database.DbReadTableUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.PostgresSequenceMaxValueIncrementer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Tag(
    name = "Online表单开发"
)
@RestController("onlCgformApiController")
@RequestMapping({"/online/cgform/api"})
public class a {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(a.class);
    @Autowired
    private IOnlCgformHeadService onlCgformHeadService;
    @Autowired
    IOnlineJoinQueryService onlineJoinQueryService;
    @Autowired
    private IOnlCgformFieldService onlCgformFieldService;
    @Autowired
    private IOnlCgformSqlService onlCgformSqlService;
    @Autowired
    private IOnlAuthPageService onlAuthPageService;
    @Lazy
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private IOnlineService onlineService;
    @Value("${jeecg.path.upload}")
    private String upLoadPath;
    @Value("${jeecg.uploadType}")
    private String uploadType;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JeecgBaseConfig jeecgBaseConfig;
    @Autowired
    private IOnlCgformAiService iOnlCgformAiService;

    @PostMapping({"/addAll"})
    public Result<?> a(@RequestBody org.jeecg.modules.online.cgform.model.a model) {
        try {
            String tbname = model.getHead().getTableName();
            if (d.a(tbname)) {
                return Result.error("数据库表[" + tbname + "]已存在,请从数据库导入表单");
            } else {
                if (model.getHead().getTableType() == 3) {
                    if (oConvertUtils.isEmpty(model.getHead().getRelationType())) {
                        return Result.error("附表必须选择映射关系！");
                    }

                    if (oConvertUtils.isEmpty(model.getHead().getTabOrderNum())) {
                        return Result.error("附表必须填写排序序号！");
                    }
                }

                return this.onlCgformHeadService.addAll(model);
            }
        } catch (Exception e) {
            a.error("OnlCgformApiController.addAll()发生异常：" + e.getMessage(), e);
            return Result.error("操作失败");
        }
    }

    @PostMapping({"/aigc"})
    @Operation(
        summary = "通过AI生成一个模块的表设计"
    )
    public Result<?> a(@RequestParam(name = "prompt",required = true) String prompt) {
        long startTime = System.currentTimeMillis();
        a.info("online生成表设计开始:{}", startTime);
        Result result = this.iOnlCgformAiService.genSingleSchema4Modules(prompt);
        a.info("online生成表设计结束,耗时{}", System.currentTimeMillis() - startTime);
        return result;
    }

    @PostMapping({"/aigc/fields"})
    public Result<?> a(@RequestParam(name = "code",required = false) String code, @RequestParam(name = "prompt",required = true) String prompt) {
        long startTime = System.currentTimeMillis();
        a.info("online生成表字段开始:{}", startTime);
        Result result = this.iOnlCgformAiService.aiGenFields(code, prompt);
        a.info("online生成表字段结束,耗时{}", System.currentTimeMillis() - startTime);
        return result;
    }

    @RequiresRoles(
        value = {"admin", "lowdeveloper"},
        logical = Logical.OR
    )
    @PostMapping({"/aigc/mock/data/{code}"})
    public Result<?> a(@PathVariable("code") String code, @RequestParam(value = "count",required = false,defaultValue = "3") Integer count) {
        long startTime = System.currentTimeMillis();
        a.info("online生成数据开始:{}", startTime);
        Result result = this.iOnlCgformAiService.aiGenMockData(code, count);
        a.info("online生成数据结束,耗时{}", System.currentTimeMillis() - startTime);
        return result;
    }

    @PutMapping({"/editAll"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<?> b(@RequestBody org.jeecg.modules.online.cgform.model.a model) {
        try {
            if (model.getHead().getTableType() == 3) {
                if (oConvertUtils.isEmpty(model.getHead().getRelationType())) {
                    return Result.error("附表必须选择映射关系！");
                }

                if (oConvertUtils.isEmpty(model.getHead().getTabOrderNum())) {
                    return Result.error("附表必须填写排序序号！");
                }
            }

            return this.onlCgformHeadService.editAll(model);
        } catch (Exception e) {
            a.error("OnlCgformApiController.editAll()发生异常：" + e.getMessage(), e);
            return Result.error("操作失败");
        }
    }

    @AutoLog(
        operateType = 1,
        value = "online列表加载",
        module = ModuleType.ONLINE
    )
    @OnlineAuth("getColumns")
    @GetMapping({"/getColumns/{code}"})
    public Result<b> a(@PathVariable("code") String code, HttpServletRequest request) {
        Result result = new Result();
        OnlCgformHead head = null;

        try {
            head = this.onlCgformHeadService.getTable(code);
        } catch (org.jeecg.modules.online.config.exception.a var8) {
            result.error500("Online表单不存在！");
            return result;
        }

        String selectFields = request.getParameter("linkTableSelectFields");
        if (oConvertUtils.isNotEmpty(selectFields)) {
            head.setSelectFieldString(selectFields);
        }

        LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        b model = this.onlineService.queryOnlineConfig(head, user.getUsername());
        model.setIsDesForm(head.getIsDesForm());
        model.setDesFormCode(head.getDesFormCode());
        result.setResult(model);
        result.setOnlTable(head.getTableName());
        return result;
    }

    @PermissionData
    @OnlineAuth("getData")
    @GetMapping({"/getData/{code}"})
    public Result<Map<String, Object>> b(@PathVariable("code") String code, HttpServletRequest request) {
        Result result = new Result();
        OnlCgformHead head = null;

        try {
            head = this.onlCgformHeadService.getTable(code);
        } catch (org.jeecg.modules.online.config.exception.a var10) {
            result.error500("实体不存在");
            return result;
        }

        if (oConvertUtils.isEmpty(head.getPhysicId()) && "N".equals(head.getIsDbSynch())) {
            result.error500("NO_DB_SYNC");
            return result;
        } else {
            String selectFields = request.getParameter("linkTableSelectFields");
            if (oConvertUtils.isNotEmpty(selectFields)) {
                head.setSelectFieldString(selectFields);
            }

            Map page = null;

            try {
                Map params = org.jeecg.modules.online.cgform.dird.d.a(request);
                boolean isJoinQuery = org.jeecg.modules.online.cgform.dird.d.a(head);
                if (isJoinQuery) {
                    page = this.onlineJoinQueryService.pageList(head, params);
                } else {
                    page = this.onlCgformFieldService.queryAutolistPage(head, params, (List)null);
                }

                this.a(head, page);
                result.setResult(page);
            } catch (Exception e) {
                a.error(e.getMessage(), e);
                result.error500("数据库查询失败，" + e.getMessage());
            }

            result.setOnlTable(head.getTableName());
            return result;
        }
    }

    @PermissionData
    @GetMapping({"/4jmbi/getLinkData"})
    public Result<Map<String, Object>> a(@RequestParam("code") String code, @RequestParam("field") String field, HttpServletRequest request) {
        OnlCgformHead head;
        try {
            head = this.onlCgformHeadService.getTable(code);
        } catch (org.jeecg.modules.online.config.exception.a var6) {
            Result.error("实体不存在");
            return Result.ok();
        }

        OnlCgformField onlCgformField = this.onlCgformFieldService.queryFormFieldByTableNameAndField(head.getTableName(), field);
        return oConvertUtils.isNotEmpty(onlCgformField) && oConvertUtils.isNotEmpty(onlCgformField.getDictTable()) && "link_table".equalsIgnoreCase(onlCgformField.getFieldShowType()) ? this.b(onlCgformField.getDictTable(), request) : Result.ok();
    }

    @AutoLog(
        operateType = 1,
        value = "online表单加载",
        module = ModuleType.ONLINE
    )
    @OnlineAuth("getFormItem")
    @GetMapping({"/getFormItem/{code}"})
    public Result<?> c(@PathVariable("code") String code, HttpServletRequest request) {
        OnlCgformHead head = null;

        try {
            head = this.onlCgformHeadService.getTable(code);
        } catch (org.jeecg.modules.online.config.exception.a var8) {
            return Result.error("Online表单不存在！");
        }

        Result result = new Result();
        LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String selectFields = request.getParameter("selectFields");
        if (oConvertUtils.isNotEmpty(selectFields)) {
            List var7 = Arrays.asList(selectFields.split(","));
        }

        JSONObject obj = this.onlineService.queryOnlineFormItem(head, user.getUsername());
        result.setResult(org.jeecg.modules.online.cgform.dird.d.b(obj));
        result.setOnlTable(head.getTableName());
        return result;
    }

    @AutoLog(
        operateType = 1,
        value = "online根据表名加载表单",
        module = ModuleType.ONLINE
    )
    @GetMapping({"/getFormItemBytbname/{table}"})
    public Result<?> b(@PathVariable("table") String table, @RequestParam(name = "taskId",required = false) String taskId) {
        Result result = new Result();
        LambdaQueryWrapper<OnlCgformHead> query = new LambdaQueryWrapper();
        query.eq(OnlCgformHead::getTableName, table);
        OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getOne(query);
        if (head == null) {
            Result.error("Online表单不存在！");
        }

        LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        JSONObject obj = this.onlineService.queryFlowOnlineFormItem(head, user.getUsername(), taskId);
        result.setResult(org.jeecg.modules.online.cgform.dird.d.b(obj));
        result.setOnlTable(table);
        return result;
    }

    @OnlineAuth("getEnhanceJs")
    @GetMapping({"/getEnhanceJs/{code}"})
    public Result<?> d(@PathVariable("code") String code, HttpServletRequest request) {
        String enhanceJsStr = this.onlineService.queryEnahcneJsString(code, "form");
        return Result.ok(enhanceJsStr);
    }

    @AutoLog(
        operateType = 1,
        value = "online表单数据查询"
    )
    @GetMapping({"/form/{code}/{id}"})
    public Result<?> c(@PathVariable("code") String code, @PathVariable("id") String id) {
        try {
            SqlInjectionUtil.filterContent(id, "'");
            Map map = this.onlCgformHeadService.queryManyFormData(code, id);
            return Result.ok(org.jeecg.modules.online.cgform.dird.d.a(map));
        } catch (Exception e) {
            a.error("Online表单查询异常：" + e.getMessage(), e);
            return Result.error("查询失败，" + e.getMessage());
        }
    }

    @AutoLog(
        operateType = 1,
        value = "online表单数据查询"
    )
    @GetMapping({"/detail/{code}/{id}"})
    public Result<?> d(@PathVariable("code") String code, @PathVariable("id") String id) {
        try {
            SqlInjectionUtil.filterContent(id, "'");
            Map map = this.onlCgformHeadService.queryManyFormData(code, id);
            List list = new ArrayList();
            list.add(org.jeecg.modules.online.cgform.dird.d.a(map));
            OnlCgformHead head = this.onlCgformHeadService.getTable(code);
            this.onlCgformFieldService.handleLinkTableDictData(head.getId(), list);
            return Result.ok((Map)list.get(0));
        } catch (Exception e) {
            a.error("Online表单查询异常：" + e.getMessage(), e);
            return Result.error("查询失败，" + e.getMessage());
        }
    }

    @GetMapping({"/subform/{table}/{mainId}"})
    public Result<?> e(@PathVariable("table") String table, @PathVariable("mainId") String mainId) {
        try {
            SqlInjectionUtil.filterContent(mainId, "'");
            Map map = this.onlCgformHeadService.querySubFormData(table, mainId);
            return Result.ok(org.jeecg.modules.online.cgform.dird.d.a(map));
        } catch (Exception e) {
            a.error("Online表单查询异常：" + e.getMessage(), e);
            return Result.error("查询失败，" + e.getMessage());
        }
    }

    @GetMapping({"/subform/list/{table}/{mainId}"})
    public Result<?> f(@PathVariable("table") String table, @PathVariable("mainId") String mainId) {
        try {
            SqlInjectionUtil.filterContent(mainId, "'");
            return Result.ok(this.onlCgformHeadService.queryManySubFormData(table, mainId));
        } catch (Exception e) {
            a.error("Online表单查询异常：" + e.getMessage(), e);
            return Result.error("查询失败，" + e.getMessage());
        }
    }

    @AutoLog(
        operateType = 1,
        value = "online根据表名查询表单数据",
        module = ModuleType.ONLINE
    )
    @GetMapping({"/form/table_name/{tableName}/{dataId}"})
    public Result<?> g(@PathVariable("tableName") String tableName, @PathVariable("dataId") String dataId) {
        try {
            LambdaQueryWrapper<OnlCgformHead> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(OnlCgformHead::getTableName, tableName);
            OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getOne(queryWrapper);
            if (head == null) {
                throw new Exception("OnlCgform tableName: " + tableName + " 不存在！");
            } else {
                SqlInjectionUtil.filterContent(dataId, "'");
                Result res = this.c(head.getId(), dataId);
                res.setOnlTable(tableName);
                return res;
            }
        } catch (Exception e) {
            a.error("Online表单查询异常，" + e.getMessage(), e);
            return Result.error("查询失败，" + e.getMessage());
        }
    }

    @AutoLog(
        operateType = 2,
        value = "online新增数据",
        module = ModuleType.ONLINE
    )
    @OnlineAuth("form")
    @PostMapping({"/form/{code}"})
    @CacheEvict(
        value = {"sys:cache:online:linkTable"},
        allEntries = true
    )
    public Result<String> a(@PathVariable("code") String code, @RequestBody JSONObject jsonObject, HttpServletRequest request) {
        Result result = new Result();

        try {
            String id = org.jeecg.modules.online.cgform.dird.d.a();
            jsonObject.put("id", id);
            String xAccessToken = TokenUtils.getTokenByRequest(request);
            String tableName = this.onlCgformHeadService.saveManyFormData(code, jsonObject, xAccessToken);
            result.setSuccess(true);
            result.setResult(id);
            result.setOnlTable(tableName);
            result.setMessage("添加成功!");
        } catch (Exception e) {
            a.error("OnlCgformApiController.formAdd()发生异常：", e);
            result.setSuccess(false);
            result.setMessage("保存失败，" + org.jeecg.modules.online.cgform.dird.d.a(e));
        }

        return result;
    }

    @AutoLog(
        operateType = 3,
        value = "online修改数据",
        module = ModuleType.ONLINE
    )
    @OnlineAuth("form")
    @PutMapping({"/form/{code}"})
    @CacheEvict(
        value = {"sys:cache:online:linkTable"},
        allEntries = true
    )
    public Result<?> a(@PathVariable("code") String code, @RequestBody JSONObject jsonObject) {
        try {
            String tableName = this.onlCgformHeadService.editManyFormData(code, jsonObject);
            Result res = Result.ok("修改成功！");
            res.setOnlTable(tableName);
            return res;
        } catch (Exception e) {
            a.error("OnlCgformApiController.formEdit()发生异常：" + e.getMessage(), e);
            return Result.error("修改失败，" + org.jeecg.modules.online.cgform.dird.d.a(e));
        }
    }

    @AutoLog(
        operateType = 4,
        value = "online删除数据",
        module = ModuleType.ONLINE
    )
    @OnlineAuth("form")
    @DeleteMapping({"/form/{code}/{id}"})
    public Result<?> h(@PathVariable("code") String code, @PathVariable("id") String id) {
        OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getById(code);
        if (head == null) {
            return Result.error("实体不存在");
        } else {
            try {
                String pids = "";
                if ("Y".equals(head.getIsTree())) {
                    id = this.onlCgformFieldService.queryTreeChildIds(head, id);
                    pids = this.onlCgformFieldService.queryTreePids(head, id);
                }

                if (id.indexOf(",") > 0) {
                    if (head.getTableType() == 2) {
                        this.onlCgformFieldService.deleteAutoListMainAndSub(head, id);
                    } else {
                        String tbname = head.getTableName();
                        this.onlCgformFieldService.deleteAutoListById(tbname, id);
                    }

                    if ("Y".equals(head.getIsTree())) {
                        String tbname = head.getTableName();
                        String hasChildrenField = head.getTreeIdField();
                        String[] idArr = pids.split(",");

                        for(String pid : idArr) {
                            this.onlCgformFieldService.updateTreeNodeNoChild(tbname, hasChildrenField, pid);
                        }
                    }
                } else {
                    this.onlCgformHeadService.deleteOneTableInfo(code, id);
                }

                if (oConvertUtils.isNotEmpty(head.getIsDesForm()) && "1".equals(head.getIsDesForm())) {
                }
            } catch (Exception e) {
                a.error("OnlCgformApiController.formEdit()发生异常：" + e.getMessage(), e);
                return Result.error("删除失败," + e.getMessage());
            }

            Result res = Result.ok("删除成功!");
            res.setOnlTable(head.getTableName());
            return res;
        }
    }

    @AutoLog(
        operateType = 4,
        value = "online删除数据",
        module = ModuleType.ONLINE
    )
    @DeleteMapping({"/formByCode/{code}/{id}"})
    public Result<?> i(@PathVariable("code") String code, @PathVariable("id") String id) {
        try {
            String tableName = this.onlCgformHeadService.deleteDataByCode(code, id);
            Result res = Result.OK("删除成功!", tableName);
            res.setOnlTable(tableName);
            return res;
        } catch (JeecgBootException e) {
            return Result.error(e.getMessage());
        }
    }

    @OnlineAuth("getQueryInfo")
    @GetMapping({"/getQueryInfo/{code}"})
    public Result<?> b(@PathVariable("code") String code) {
        try {
            List list = this.onlCgformFieldService.getAutoListQueryInfo(code);
            return Result.ok(list);
        } catch (Exception e) {
            a.error("OnlCgformApiController.getQueryInfo()发生异常：" + e.getMessage(), e);
            return Result.error("查询失败");
        }
    }

    @GetMapping({"/getQueryInfoVue3/{code}"})
    public Result<?> c(@PathVariable("code") String code) {
        try {
            JSONObject json = this.onlineService.getOnlineVue3QueryInfo(code);
            return Result.ok(json);
        } catch (Exception e) {
            a.error("获取online查询配置异常：" + e.getMessage(), e);
            return Result.error("获取Online表单的查询条件失败!");
        }
    }

    @PostMapping({"/doDbSynch/{code}/{synMethod}"})
    @RequiresPermissions({"online:form:syncDb"})
    public Result<?> j(@PathVariable("code") String code, @PathVariable("synMethod") String synMethod) {
        try {
            long starttime = System.currentTimeMillis();
            this.onlCgformHeadService.doDbSynch(code, synMethod);
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            return Result.error("同步数据库失败，" + org.jeecg.modules.online.cgform.dird.d.a(e));
        }

        return Result.ok("同步数据库成功!");
    }

    @OnlineAuth("exportXls")
    @PermissionData
    @GetMapping({"/exportXls/{code}"})
    public void a(@PathVariable("code") String code, HttpServletRequest request, HttpServletResponse response) {
        OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getById(code);
        if (head != null) {
            String sheetName = head.getTableTxt();
            String paramsStr = request.getParameter("paramsStr");
            Map params = new HashMap(5);
            String deString = null;
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                params = (Map)JSONObject.parseObject(paramsStr, Map.class);
            }

            XSSFWorkbook workbook = this.onlineJoinQueryService.handleOnlineExport(head, params);
            OutputStream outputStream = null;

            try {
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                String browse = BrowserUtils.checkBrowse(request);
                String var10000 = head.getTableTxt();
                String codedFileName = var10000 + "-v" + head.getTableVersion();
                if ("MSIE".equalsIgnoreCase(browse.substring(0, 4))) {
                    response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(codedFileName, "UTF-8") + ".xlsx");
                } else {
                    String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                    response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xlsx");
                }

                outputStream = response.getOutputStream();
                workbook.write(outputStream);
                response.flushBuffer();
            } catch (Exception e) {
                a.error("--通过流的方式获取文件异常--" + e.getMessage(), e);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        a.error(e.getMessage(), e);
                    }
                }

            }

        }
    }

    @OnlineAuth("exportXlsOld")
    @PermissionData
    @GetMapping({"/exportXlsOld/{code}"})
    public void b(@PathVariable("code") String code, HttpServletRequest request, HttpServletResponse response) {
        OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getById(code);
        if (head != null) {
            String sheetName = head.getTableTxt();
            String paramsStr = request.getParameter("paramsStr");
            Map params = new HashMap(5);
            String deString = null;
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                params = (Map)JSONObject.parseObject(paramsStr, Map.class);
            }

            params.put("pageSize", -521);
            boolean isJoinQuery = org.jeecg.modules.online.cgform.dird.d.a(head);
            Map<String, Object> pageData = null;
            if (isJoinQuery) {
                pageData = this.onlineJoinQueryService.pageList(head, params, true);
            } else {
                pageData = this.onlCgformFieldService.queryAutolistPage(head, params, (List)null);
            }

            List fieldList = (List)pageData.get("fieldList");
            List<Map<String,Object>> data = (List<Map<String,Object>>)pageData.get("records");
            List result = new ArrayList();
            String selections = params.get("selections") == null ? null : params.get("selections").toString();
            if (oConvertUtils.isNotEmpty(selections)) {
                List<String> selectionList = org.jeecg.modules.online.cgform.dird.d.h(selections);
                result = (List)data.stream().filter((item) -> selectionList.contains(item.get("id"))).collect(Collectors.toList());
            } else {
                if (data == null) {
                    data = new ArrayList();
                }

                result.addAll(data);
            }

            org.jeecg.modules.online.cgform.converter.b.a(1, result, fieldList);

            try {
                this.onlCgformHeadService.executeEnhanceExport(head, result);
            } catch (BusinessException e) {
                a.error("导出java增强处理出错", e.getMessage());
            }

            List entityList = org.jeecg.modules.online.cgform.dird.d.b(fieldList, "id", this.upLoadPath);
            if (head.getTableType() == 2 && oConvertUtils.isEmpty(params.get("exportSingleOnly"))) {
                String subTableStr = head.getSubTableStr();
                if (oConvertUtils.isNotEmpty(subTableStr)) {
                    String[] subTables = subTableStr.split(",");

                    for(String subTable : subTables) {
                        this.onlineJoinQueryService.addAllSubTableDate(subTable, params, result, entityList, false);
                    }
                }
            }

            sheetName = oConvertUtils.getNormalString(sheetName);
            ExportParams exportParams = new ExportParams((String)null, sheetName);
            exportParams.setType(ExcelType.XSSF);
            Workbook workbook = ExcelExportUtil.exportExcel(exportParams, entityList, result);
            OutputStream outputStream = null;

            try {
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                String browse = BrowserUtils.checkBrowse(request);
                String var10000 = head.getTableTxt();
                String codedFileName = var10000 + "-v" + head.getTableVersion();
                codedFileName = oConvertUtils.getNormalString(codedFileName);
                if ("MSIE".equalsIgnoreCase(browse.substring(0, 4))) {
                    response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(codedFileName, "UTF-8") + ".xlsx");
                } else {
                    String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                    response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xlsx");
                }

                outputStream = response.getOutputStream();
                workbook.write(outputStream);
                response.flushBuffer();
            } catch (Exception e) {
                a.error("--通过流的方式获取文件异常--" + e.getMessage(), e);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        a.error(e.getMessage(), e);
                    }
                }

            }

        }
    }

    @OnlineAuth("importXls")
    @PostMapping({"/importXls/{code}"})
    public Result<?> c(@PathVariable("code") String code, HttpServletRequest request, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        Result result = new Result();
        String message = "";
        String validateStatus = request.getParameter("validateStatus");
        StringBuffer error = new StringBuffer();

        try {
            OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getById(code);
            if (head == null) {
                return Result.error("数据库不存在该表记录");
            }

            LambdaQueryWrapper<OnlCgformField> query = new LambdaQueryWrapper();
            query.eq(OnlCgformField::getCgformHeadId, code);
            List fieldList = this.onlCgformFieldService.list(query);
            String importSingleOnly = request.getParameter("isSingleTableImport");
            List imgList = org.jeecg.modules.online.cgform.dird.d.e(fieldList);
            if (oConvertUtils.isEmpty(importSingleOnly) && head.getTableType() == 2 && oConvertUtils.isNotEmpty(head.getSubTableStr())) {
                for(String tbname : head.getSubTableStr().split(",")) {
                    OnlCgformHead subTable = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tbname));
                    if (subTable != null) {
                        List subFiledList = this.onlCgformFieldService.list((Wrapper)(new LambdaQueryWrapper<OnlCgformField>()).eq(OnlCgformField::getCgformHeadId, subTable.getId()));
                        List subImgList = org.jeecg.modules.online.cgform.dird.d.c(subFiledList, subTable.getTableTxt());
                        if (subImgList.size() > 0) {
                            imgList.addAll(subImgList);
                        }
                    }
                }
            }

            JSONObject foreignKeysJson = null;
            String foreignKeys = request.getParameter("foreignKeys");
            if (oConvertUtils.isNotEmpty(foreignKeys)) {
                foreignKeysJson = JSONObject.parseObject(foreignKeys);
            }

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
            Map fileMap = multipartRequest.getFileMap();
            DataSource dataSource = (DataSource)SpringContextUtils.getApplicationContext().getBean(DataSource.class);
            String databaseType = d.a(dataSource);
            Iterator errorFilePath = fileMap.entrySet().iterator();

            label512:
            while(true) {
                if (errorFilePath.hasNext()) {
                    Map.Entry entity = (Map.Entry)errorFilePath.next();
                    MultipartFile file = (MultipartFile)entity.getValue();
                    ImportParams params = new ImportParams();
                    params.setDataHanlder(new c(fieldList, this.upLoadPath, this.uploadType));
                    InputStream fileInputStream = file.getInputStream();

                    try {
                        List<Map> importExcelDataList = ExcelImportUtil.importExcel(fileInputStream, Map.class, params);
                        if (importExcelDataList == null) {
                            message = "识别模版数据错误";
                            a.error(message);
                            continue;
                        }

                        if (org.jeecg.modules.online.cgform.enums.a.c.equals(head.getTableType()) && head.getRelationType() == 1) {
                            if (importExcelDataList.size() > 1) {
                                Result var66 = Result.error("一对一的表只能导入一条数据!");
                                return var66;
                            }

                            Integer count = this.onlCgformFieldService.queryCountBySql(org.jeecg.modules.online.cgform.dird.d.f(head.getTableName()), (String)null, (String)null);
                            if (null != count && count > 1) {
                                throw new JeecgBootException("一对一的表只能导入一条数据!");
                            }
                        }

                        Object mainId = "";
                        List mainDataList = new ArrayList();

                        for(Map map : importExcelDataList) {
                            boolean isMainData = false;
                            Set<String> keySet = map.keySet();
                            Map mainData = new HashMap(5);

                            for(String key : keySet) {
                                if (key.indexOf("$subTable$") == -1) {
                                    if (key.indexOf("$mainTable$") != -1 && oConvertUtils.isNotEmpty(map.get(key).toString())) {
                                        isMainData = true;
                                        mainId = this.a(head, dataSource, databaseType);
                                    }

                                    mainData.put(key.replace("$mainTable$", ""), map.get(key));
                                }
                            }

                            if ("Y".equals(head.getIsTree())) {
                                if (oConvertUtils.isEmpty(mainData.get(head.getTreeParentIdField()))) {
                                    mainData.put(head.getTreeParentIdField(), "0");
                                }

                                if (oConvertUtils.isEmpty(mainData.get(head.getTreeIdField()))) {
                                    mainData.put(head.getTreeIdField(), "0");
                                }
                            }

                            if (isMainData) {
                                mainData.put("id", mainId);
                                mainDataList.add(mainData);
                                mainId = mainData.get("id");
                            }

                            if (foreignKeysJson != null) {
                                for(String key : foreignKeysJson.keySet()) {
                                    System.out.println(key + "=" + foreignKeysJson.getString(key));
                                    mainData.put(key, foreignKeysJson.getString(key));
                                }
                            }

                            map.put("$mainTable$id", mainId);
                        }

                        if (mainDataList != null && mainDataList.size() != 0) {
                            if ("1".equals(validateStatus)) {
                                Map map = this.onlCgformSqlService.saveOnlineImportDataWithValidate(head, fieldList, mainDataList);
                                String tempError = (String)map.get("error");
                                message = (String)map.get("tip");
                                if (tempError != null && tempError.length() > 0) {
                                    error.append(head.getTableTxt() + "导入校验," + message + ",详情如下:\r\n" + tempError);
                                }
                            } else {
                                this.onlCgformSqlService.saveBatchOnlineTable(head, fieldList, mainDataList);
                            }

                            if (!oConvertUtils.isEmpty(importSingleOnly) || head.getTableType() != 2 || !oConvertUtils.isNotEmpty(head.getSubTableStr())) {
                                continue;
                            }

                            String[] var69 = head.getSubTableStr().split(",");
                            int var71 = var69.length;
                            int var72 = 0;

                            while(true) {
                                if (var72 >= var71) {
                                    continue label512;
                                }

                                String tbname = var69[var72];
                                OnlCgformHead subTable = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tbname));
                                if (subTable != null) {
                                    LambdaQueryWrapper<OnlCgformField> subFieldQuery = new LambdaQueryWrapper();
                                    subFieldQuery.eq(OnlCgformField::getCgformHeadId, subTable.getId());
                                    List<OnlCgformField> subFiledList = this.onlCgformFieldService.list(subFieldQuery);
                                    List subDataList = new ArrayList();
                                    String configName = subTable.getTableTxt();

                                    for(Map map : importExcelDataList) {
                                        boolean isSubData = false;
                                        Map subData = new HashMap();

                                        for(OnlCgformField fieldEntity : subFiledList) {
                                            String mainTab = fieldEntity.getMainTable();
                                            String mainField = fieldEntity.getMainField();
                                            boolean isForeignKey = head.getTableName().equals(mainTab) && oConvertUtils.isNotEmpty(mainField);
                                            String tempKey = configName + "_" + fieldEntity.getDbFieldTxt();
                                            if (isForeignKey) {
                                                subData.put(fieldEntity.getDbFieldName(), map.get("$mainTable$" + mainField));
                                            }

                                            Object subObj = map.get("$subTable$" + tempKey);
                                            if (null != subObj && oConvertUtils.isNotEmpty(subObj.toString())) {
                                                isSubData = true;
                                                subData.put(fieldEntity.getDbFieldName(), subObj);
                                            }
                                        }

                                        if (isSubData) {
                                            subData.put("id", this.a(subTable, dataSource, databaseType));
                                            subDataList.add(subData);
                                        }
                                    }

                                    if (subDataList.size() > 0) {
                                        if ("1".equals(validateStatus)) {
                                            Map map = this.onlCgformSqlService.saveOnlineImportDataWithValidate(subTable, subFiledList, subDataList);
                                            String tempError = (String)map.get("error");
                                            String tempTip = (String)map.get("tip");
                                            if (tempError != null && tempError.length() > 0) {
                                                error.append(subTable.getTableTxt() + "导入校验," + tempTip + ",详情如下:\r\n" + tempError);
                                            }
                                        } else {
                                            this.onlCgformSqlService.saveBatchOnlineTable(subTable, subFiledList, subDataList);
                                        }
                                    }
                                }

                                ++var72;
                            }
                        }

                        result.setSuccess(false);
                        result.setMessage("导入失败，匹配的数据条数为零!");
                        Result var67 = result;
                        return var67;
                    } catch (Exception e) {
                        result.setSuccess(false);
                        result.setMessage("导入失败，" + e.getMessage());
                        Result count = result;
                        return count;
                    } finally {
                        if (fileInputStream != null) {
                            IOUtils.closeQuietly(fileInputStream);
                        }

                    }
                }

                result.setSuccess(true);
                if ("1".equals(validateStatus) && error.length() > 0) {
                    String errorFilePathS1 = org.jeecg.modules.online.cgform.dird.d.a(this.upLoadPath, head.getTableTxt(), error);
                    result.setResult(errorFilePathS1);
                    result.setMessage(message);
                    result.setCode(201);
                    break;
                }

                result.setMessage("导入成功!");
                break;
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            a.error(e.getMessage(), e);
        }

        a.info("=====online导入数据完成,耗时:" + (System.currentTimeMillis() - start) + "毫秒=====");
        return result;
    }

    @PostMapping({"/doButton"})
    public Result<?> a(@RequestBody JSONObject json) {
        String formId = json.getString("formId");
        String dataId = json.getString("dataId");
        String buttonCode = json.getString("buttonCode");
        JSONObject uiFormData = json.getJSONObject("uiFormData");

        try {
            this.onlCgformHeadService.executeCustomerButton(buttonCode, formId, dataId);
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            return Result.error("执行失败," + e.getMessage());
        }

        return Result.ok("执行成功!");
    }

    public Object a(OnlCgformHead cghead, DataSource dataSource, String dbType) throws SQLException, org.jeecg.modules.online.config.exception.a {
        Object pkValue = null;
        String pkType = cghead.getIdType();
        String pkSequence = cghead.getIdSequence();
        if (oConvertUtils.isNotEmpty(pkType) && "UUID".equalsIgnoreCase(pkType)) {
            pkValue = org.jeecg.modules.online.cgform.dird.d.a();
        } else if (oConvertUtils.isNotEmpty(pkType) && "NATIVE".equalsIgnoreCase(pkType)) {
            if (oConvertUtils.isNotEmpty(dbType) && "oracle".equalsIgnoreCase(dbType)) {
                OracleSequenceMaxValueIncrementer incr = new OracleSequenceMaxValueIncrementer(dataSource, "HIBERNATE_SEQUENCE");

                try {
                    pkValue = incr.nextLongValue();
                } catch (Exception e) {
                    a.error(e.getMessage(), e);
                }
            } else if (oConvertUtils.isNotEmpty(dbType) && "postgres".equalsIgnoreCase(dbType)) {
                PostgresSequenceMaxValueIncrementer incr = new PostgresSequenceMaxValueIncrementer(dataSource, "HIBERNATE_SEQUENCE");

                try {
                    pkValue = incr.nextLongValue();
                } catch (Exception e) {
                    a.error(e.getMessage(), e);
                }
            } else {
                pkValue = null;
            }
        } else if (oConvertUtils.isNotEmpty(pkType) && "SEQUENCE".equalsIgnoreCase(pkType)) {
            if (oConvertUtils.isNotEmpty(dbType) && "oracle".equalsIgnoreCase(dbType)) {
                OracleSequenceMaxValueIncrementer incr = new OracleSequenceMaxValueIncrementer(dataSource, pkSequence);

                try {
                    pkValue = incr.nextLongValue();
                } catch (Exception e) {
                    a.error(e.getMessage(), e);
                }
            } else if (oConvertUtils.isNotEmpty(dbType) && "postgres".equalsIgnoreCase(dbType)) {
                PostgresSequenceMaxValueIncrementer incr = new PostgresSequenceMaxValueIncrementer(dataSource, pkSequence);

                try {
                    pkValue = incr.nextLongValue();
                } catch (Exception e) {
                    a.error(e.getMessage(), e);
                }
            } else {
                pkValue = null;
            }
        } else {
            pkValue = org.jeecg.modules.online.cgform.dird.d.a();
        }

        return pkValue;
    }

    private void a(Map result, List<OnlCgformField> beans) {
        for(OnlCgformField bean : beans) {
            String dicTable = bean.getDictTable();
            String dicCode = bean.getDictField();
            String dicText = bean.getDictText();
            if ((!oConvertUtils.isEmpty(dicTable) || !oConvertUtils.isEmpty(dicCode)) && !"popup".equals(bean.getFieldShowType())) {
                String value = String.valueOf(result.get(bean.getDbFieldName()));
                List<DictModel> dicDataList;
                if (oConvertUtils.isEmpty(dicTable)) {
                    dicDataList = this.sysBaseAPI.queryDictItemsByCode(dicCode);
                } else {
                    dicDataList = this.sysBaseAPI.queryTableDictItemsByCode(dicTable, dicText, dicCode);
                }

                for(DictModel item : dicDataList) {
                    if (value.equals(item.getText())) {
                        result.put(bean.getDbFieldName(), item.getValue());
                    }
                }
            }
        }

    }

    @GetMapping({"/checkOnlyTable"})
    public Result<?> k(@RequestParam("tbname") String tbname, @RequestParam("id") String id) {
        if (oConvertUtils.isEmpty(id)) {
            if (d.a(tbname)) {
                return Result.ok(-1);
            }

            OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tbname));
            if (oConvertUtils.isNotEmpty(head)) {
                return Result.ok(-1);
            }
        } else {
            OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getById(id);
            if (!tbname.equals(head.getTableName()) && d.a(tbname)) {
                return Result.ok(-1);
            }
        }

        return Result.ok(1);
    }

    @RequiresPermissions({"online:form:generateCode"})
    @PostMapping({"/codeGenerate"})
    public Result<?> b(@RequestBody JSONObject json) {
        org.jeecg.modules.online.cgform.model.d model = (org.jeecg.modules.online.cgform.model.d)JSONObject.parseObject(json.toJSONString(), org.jeecg.modules.online.cgform.model.d.class);
        boolean dataSourceSafe = this.jeecgBaseConfig.getFirewall() != null ? this.jeecgBaseConfig.getFirewall().getDataSourceSafe() : false;
        if (dataSourceSafe && !DbReadTableUtil.getProjectPath().equals(model.getProjectPath())) {
            model.setProjectPath(DbReadTableUtil.getProjectPath());
            a.warn("数据源安全模式下，自定义代码生成路径无效，使用全局配置的路径 ::{}", DbReadTableUtil.getProjectPath());
        }

        List generateFileList = null;

        try {
            if ("1".equals(model.getJformType())) {
                generateFileList = this.onlCgformHeadService.generateCode(model);
            } else {
                generateFileList = this.onlCgformHeadService.generateOneToMany(model);
            }

            LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
            String var10000 = sysUser.getUsername();
            String pathKey = var10000 + model.getTableName() + oConvertUtils.randomGen(16);
            Result res = Result.ok(generateFileList);
            String rootPath = model.getProjectPath().replaceAll("\\\\", "/");
            this.redisUtil.set(pathKey, rootPath, 1800L);
            res.setMessage(pathKey);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping({"/codeView"})
    public void a(@RequestParam(name = "path") String path, @RequestParam(name = "pathKey") String pathKey, HttpServletResponse response) {
        String text = "";

        try {
            text = URLDecoder.decode(path, "UTF-8");
            if (text.indexOf("src/main/java") == -1 && text.indexOf("src%5Cmain%5Cjava") == -1 && text.indexOf("src\\main\\java") == -1) {
                String msg = "代码不在`src/main/java`目录中，不允许预览";
                a.error(msg);
                JwtUtil.responseError(response, 200, msg);
                return;
            }
        } catch (UnsupportedEncodingException e) {
            a.error(" path 不合法！！！", e.getMessage());
        }

        Object obj = this.redisUtil.get(pathKey);
        if (obj == null) {
            String msg = "路径失效，请重新操作!";
            a.error(msg);
            JwtUtil.responseError(response, 500, msg);
        } else {
            String rootPath = obj.toString();
            String temp = text.replaceAll("\\\\", "/");
            if (temp.indexOf(rootPath) < 0) {
                String msg = "非法的请求路径，请重新操作!";
                a.error(msg);
                JwtUtil.responseError(response, 500, msg);
            } else {
                String fileName = text.substring(text.lastIndexOf("/") + 1);
                File file = new File(text);
                if (file.exists()) {
                    response.setContentType("application/force-download");
                    response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                    byte[] buffer = new byte[1024];
                    FileInputStream fis = null;
                    BufferedInputStream bis = null;

                    try {
                        fis = new FileInputStream(file);
                        bis = new BufferedInputStream(fis);
                        OutputStream os = response.getOutputStream();

                        for(int i = bis.read(buffer); i != -1; i = bis.read(buffer)) {
                            os.write(buffer, 0, i);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (bis != null) {
                            try {
                                bis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        if (fis != null) {
                            try {
                                fis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }

            }
        }
    }

    @RequiresPermissions({"online:form:generateCode"})
    @PostMapping({"/downGenerateCode"})
    public void a(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) {
        String text = json.getString("fileList");
        String pathKey = json.getString("pathKey");

        try {
            text = URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            a.error(" fileList 不合法！！！", e.getMessage());
        }

        List<String> fileList = new ArrayList();

        for(String temp : text.split(",")) {
            fileList.add(temp);
        }

        List warningList = (List)fileList.stream().filter((s) -> s.indexOf("src/main/java") == -1 && s.indexOf("src%5Cmain%5Cjava") == -1 && s.indexOf("src\\main\\java") == -1).collect(Collectors.toList());
        if (fileList != null && (warningList == null || warningList.size() <= 0)) {
            Object obj = this.redisUtil.get(pathKey);
            if (obj == null) {
                String msg = "路径失效，请重新操作!";
                a.error(msg);
                JwtUtil.responseError(response, 500, msg);
            } else {
                String rootPath = obj.toString();

                for(String filePath : fileList) {
                    String temp = filePath.replaceAll("\\\\", "/");
                    if (temp.indexOf(rootPath) < 0) {
                        String msg = "非法的请求路径，请重新操作!";
                        a.error(msg);
                        JwtUtil.responseError(response, 500, msg);
                        return;
                    }
                }

                String zipName = "生成代码_" + System.currentTimeMillis() + ".zip";

                try {
                    zipName = URLEncoder.encode(zipName, "UTF-8");
                } catch (UnsupportedEncodingException var33) {
                }

                String zipPathName = "/opt/temp/codegenerate/";
                String osName = System.getProperty("os.name");
                if (null != osName && osName.startsWith("Mac OS")) {
                    zipPathName = "~/temp/codegenerate/";
                }

                zipPathName = zipPathName + zipName;
                File file = g.a(fileList, zipPathName);
                if (file.exists()) {
                    response.setContentType("application/force-download");
                    response.addHeader("Content-Disposition", "attachment;fileName=" + zipName);
                    byte[] buffer = new byte[1024];
                    FileInputStream fis = null;
                    BufferedInputStream bis = null;

                    try {
                        fis = new FileInputStream(file);
                        bis = new BufferedInputStream(fis);
                        OutputStream os = response.getOutputStream();

                        for(int i = bis.read(buffer); i != -1; i = bis.read(buffer)) {
                            os.write(buffer, 0, i);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (bis != null) {
                            try {
                                bis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        if (fis != null) {
                            try {
                                fis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        String finalZipPathName = zipPathName;
                        class NamelessClass_1 extends Thread {
                            public void run() {
                                try {
                                    Thread.sleep(10000L);
                                    FileUtil.del(finalZipPathName);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                        (new NamelessClass_1()).start();
                    }
                }

            }
        } else {
            a.error(" fileList 不合法！！！", fileList);
        }
    }

    @GetMapping({"/getTreeData/{code}"})
    @PermissionData
    public Result<Map<String, Object>> e(@PathVariable("code") String code, HttpServletRequest request) {
        Result result = new Result();
        OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getById(code);
        if (head == null) {
            result.error500("实体不存在");
            return result;
        } else {
            try {
                String tbname = head.getTableName();
                String hasChildrenField = head.getTreeIdField();
                String pidField = head.getTreeParentIdField();
                List needList = Lists.newArrayList(new String[]{hasChildrenField, pidField});
                Map params = org.jeecg.modules.online.cgform.dird.d.a(request);
                String hasChildValue = null;
                if (params.get(hasChildrenField) != null) {
                    hasChildValue = params.get(hasChildrenField).toString();
                }

                if (params.get("hasQuery") != null && "false".equals(params.get("hasQuery")) && params.get(pidField) == null) {
                    params.put(pidField, "0");
                } else {
                    params.put("pageSize", -521);
                    params.put(pidField, params.get(pidField));
                }

                params.put(hasChildrenField, (Object)null);
                Map page = this.onlCgformFieldService.queryAutoTreeNoPage(tbname, code, params, needList, pidField);
                this.a(head, page);
                result.setResult(page);
            } catch (Exception e) {
                a.error(e.getMessage(), e);
                result.error500("数据库查询失败" + e.getMessage());
            }

            result.setOnlTable(head.getTableName());
            return result;
        }
    }

    private void a(OnlCgformHead head, Map<String, Object> page) throws BusinessException {
        List dataList = (List)page.get("records");
        this.onlCgformHeadService.executeEnhanceList(head, "query", dataList);
    }

    @PostMapping({"/crazyForm/{name}"})
    public Result<?> b(@PathVariable("name") String tableName, @RequestBody JSONObject jsonObject) {
        Result result = new Result();

        try {
            String uuid = org.jeecg.modules.online.cgform.dird.d.a();
            jsonObject.put("id", uuid);
            this.onlCgformHeadService.addCrazyFormData(tableName, jsonObject);
            result.setResult(uuid);
            result.setMessage("保存成功");
            return result;
        } catch (Exception e) {
            a.error("OnlCgformApiController.formAddForDesigner()发生异常：" + e.getMessage(), e);
            return Result.error("保存失败");
        }
    }

    @PutMapping({"/crazyForm/{name}"})
    public Result<?> c(@PathVariable("name") String tableName, @RequestBody JSONObject jsonObject) {
        try {
            jsonObject.remove("create_by");
            jsonObject.remove("create_time");
            jsonObject.remove("update_by");
            jsonObject.remove("update_time");
            this.onlCgformHeadService.editCrazyFormData(tableName, jsonObject);
        } catch (Exception e) {
            a.error("OnlCgformApiController.formEditForDesigner()发生异常：" + e.getMessage(), e);
            return Result.error("保存失败");
        }

        return Result.ok("保存成功!");
    }

    @AutoLog(
        operateType = 1,
        value = "online列表加载",
        module = ModuleType.ONLINE
    )
    @GetMapping({"/getErpColumns/{code}"})
    public Result<Map<String, Object>> d(@PathVariable("code") String code) {
        Result result = new Result();
        OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getById(code);
        if (head == null) {
            result.error500("实体不存在");
            return result;
        } else {
            Map map = new HashMap(5);
            LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
            b main = this.onlineService.queryOnlineConfig(head, user.getUsername());
            map.put("main", main);
            if ("erp".equals(head.getThemeTemplate()) && head.getTableType() == 2) {
                String subStr = head.getSubTableStr();
                if (oConvertUtils.isNotEmpty(subStr)) {
                    List list = new ArrayList();

                    for(String tbname : subStr.split(",")) {
                        OnlCgformHead tempTable = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, tbname));
                        if (tempTable != null) {
                            list.add(this.onlineService.queryOnlineConfig(tempTable, user.getUsername()));
                        }
                    }

                    if (list.size() > 0) {
                        map.put("subList", list);
                    }
                }
            }

            result.setOnlTable(head.getTableName());
            result.setResult(map);
            result.setSuccess(true);
            return result;
        }
    }

    @AutoLog(
        operateType = 1,
        value = "online表单加载",
        module = ModuleType.ONLINE
    )
    @GetMapping({"/getErpFormItem/{code}"})
    public Result<?> f(@PathVariable("code") String code, HttpServletRequest request) {
        OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getById(code);
        if (head == null) {
            Result.error("表不存在");
        }

        Result result = new Result();
        LoginUser user = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        JSONObject obj = this.onlineService.queryOnlineFormObj(head, user.getUsername());
        result.setResult(org.jeecg.modules.online.cgform.dird.d.b(obj));
        result.setOnlTable(head.getTableName());
        return result;
    }

    @GetMapping({"/querySelectOptions"})
    public Result<List<TreeModel>> a(@ModelAttribute org.jeecg.modules.online.cgform.dira.a linkDown) {
        Result result = new Result();

        try {
            List list = this.onlCgformFieldService.queryDataListByLinkDown(linkDown);
            result.setResult(list);
            result.setSuccess(true);
        } catch (Exception e) {
            a.warn("online级联下拉数据加载失败：{}", e.getMessage());
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }

    @GetMapping({"/data/{tableName}/queryById"})
    public JSONObject a(@PathVariable("tableName") String tableName, @RequestParam(name = "mock",required = false) Boolean mock, HttpServletRequest request) {
        LambdaQueryWrapper<OnlCgformHead> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(OnlCgformHead::getTableName, tableName);
        OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getOne(queryWrapper);
        if (head == null) {
            throw new JeecgBootException("Online表单 " + tableName + " 不存在");
        } else {
            try {
                Map params = org.jeecg.modules.online.cgform.dird.d.a(request);
                List needList = new ArrayList();
                needList.add("id");
                Map page = this.onlCgformFieldService.queryAutolistPage(head, params, needList);
                this.a(head, page);
                List records = org.jeecg.modules.online.cgform.dird.d.a(page.get("records"), Object.class);
                if (Boolean.TRUE.equals(mock) && (records == null || records.size() == 0)) {
                    Object mockData = this.onlCgformFieldService.generateMockData(head.getTableName());
                    records = new ArrayList();
                    records.add(mockData);
                    page.put("records", records);
                }

                JSONObject result = new JSONObject();
                result.put("data", page.get("records"));
                return result;
            } catch (Exception e) {
                a.error(e.getMessage(), e);
                throw new JeecgBootException("数据库查询失败，" + e.getMessage());
            }
        }
    }
}
