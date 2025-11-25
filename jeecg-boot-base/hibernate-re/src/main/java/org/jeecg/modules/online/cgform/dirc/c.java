//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dirc;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Generated;
import org.apache.commons.io.FilenameUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.FileDownloadUtils;
import org.jeecg.common.util.SqlInjectionUtil;
import org.jeecg.common.util.UUIDGenerator;
import org.jeecg.modules.online.cgform.dird.d;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.config.exception.a;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("onlCgformFieldController")
@RequestMapping({"/online/cgform/field"})
public class c {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(c.class);
    @Autowired
    private IOnlCgformHeadService onlCgformHeadService;
    @Autowired
    private IOnlCgformFieldService onlCgformFieldService;
    @Value("${jeecg.path.upload}")
    private String uploadpath;

    @GetMapping({"/listByHeadCode"})
    public Result<?> a(@RequestParam("headCode") String headCode) {
        LambdaQueryWrapper<OnlCgformHead> queryHeadWrapper = new LambdaQueryWrapper();
        queryHeadWrapper.eq(OnlCgformHead::getTableName, headCode);
        OnlCgformHead head = (OnlCgformHead)this.onlCgformHeadService.getOne(queryHeadWrapper);
        return head == null ? Result.error("表名[" + headCode + "]不存在！") : this.b(head.getId());
    }

    @GetMapping({"/listByHeadId"})
    public Result<?> b(@RequestParam("headId") String headId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("cgform_head_id", headId);
        queryWrapper.orderByAsc("order_num");
        List list = this.onlCgformFieldService.list(queryWrapper);
        return Result.ok(list);
    }

    @GetMapping({"/list"})
    public Result<IPage<OnlCgformField>> a(OnlCgformField onlCgformField, @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result result = new Result();
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(onlCgformField, req.getParameterMap());
        Page page = new Page((long)pageNo, (long)pageSize);
        IPage pageList = this.onlCgformFieldService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @PostMapping({"/add"})
    public Result<OnlCgformField> a(@RequestBody OnlCgformField onlCgformField) {
        Result result = new Result();

        try {
            this.onlCgformFieldService.save(onlCgformField);
            result.success("添加成功！");
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            result.error500("操作失败");
        }

        return result;
    }

    @PutMapping({"/edit"})
    public Result<OnlCgformField> b(@RequestBody OnlCgformField onlCgformField) {
        Result result = new Result();
        OnlCgformField onlCgformFieldEntity = (OnlCgformField)this.onlCgformFieldService.getById(onlCgformField.getId());
        if (onlCgformFieldEntity == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = this.onlCgformFieldService.updateById(onlCgformField);
            if (ok) {
                result.success("修改成功!");
            }
        }

        return result;
    }

    @DeleteMapping({"/delete"})
    public Result<OnlCgformField> c(@RequestParam(name = "id",required = true) String id) {
        Result result = new Result();
        OnlCgformField onlCgformField = (OnlCgformField)this.onlCgformFieldService.getById(id);
        if (onlCgformField == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = this.onlCgformFieldService.removeById(id);
            if (ok) {
                result.success("删除成功!");
            }
        }

        return result;
    }

    @DeleteMapping({"/deleteBatch"})
    public Result<OnlCgformField> d(@RequestParam(name = "ids",required = true) String ids) {
        Result result = new Result();
        if (ids != null && !"".equals(ids.trim())) {
            this.onlCgformFieldService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        } else {
            result.error500("参数不识别！");
        }

        return result;
    }

    @GetMapping({"/queryById"})
    public Result<OnlCgformField> e(@RequestParam(name = "id",required = true) String id) {
        Result result = new Result();
        OnlCgformField onlCgformField = (OnlCgformField)this.onlCgformFieldService.getById(id);
        if (onlCgformField == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(onlCgformField);
            result.setSuccess(true);
        }

        return result;
    }

    @AutoLog(
        operateType = 1,
        value = "online表单批量单字段下载文件"
    )
    @GetMapping({"/download/{code}/{id}/{field}"})
    public void a(@PathVariable("code") String code, @PathVariable("id") String id, @PathVariable("field") String field, HttpServletResponse response) {
        try {
            a.info("[批量下载文件]开始批量下载文件:code=" + code + ",id=" + id + ",field=" + field);
            SqlInjectionUtil.filterContent(id, "'");
            OnlCgformHead head = this.onlCgformHeadService.getTable(code);
            List<OnlCgformField> fieldList = this.onlCgformFieldService.queryFormFields(head.getId(), true);
            if (fieldList != null && !fieldList.isEmpty()) {
                List fileField = (List)fieldList.stream().filter((onlField) -> onlField.getDbFieldName().equals(field)).collect(Collectors.toList());
                if (fileField.isEmpty()) {
                    throw new a("找不到字段!");
                } else {
                    String tableName = d.f(head.getTableName());
                    Map map = this.onlCgformFieldService.queryFormData(fileField, tableName, id);
                    String fieldsPath = d.a(map, field);
                    if (null != fieldsPath && !fieldsPath.isEmpty()) {
                        List<String> fieldPathArr = new ArrayList();
                        if (fieldsPath.contains(",")) {
                            fieldPathArr.addAll(Arrays.asList(fieldsPath.split(",")));
                        } else {
                            fieldPathArr.add(fieldsPath);
                        }

                        String var10000 = this.uploadpath;
                        String tempFilePath = var10000 + File.separator + "tmp" + File.separator + UUIDGenerator.generate() + File.separator;
                        fieldPathArr = (List)fieldPathArr.stream().map((path) -> {
                            Pattern pattern = Pattern.compile("^(http|https)://.*");
                            Matcher matcher = pattern.matcher(path);
                            if (matcher.matches()) {
                                String fileName = path;
                                if (path.contains("?")) {
                                    fileName = path.substring(0, path.indexOf("?"));
                                }

                                fileName = FilenameUtils.getName(fileName);
                                return FileDownloadUtils.download2DiskFromNet(path, tempFilePath + fileName);
                            } else {
                                return this.uploadpath + File.separator + path;
                            }
                        }).collect(Collectors.toList());
                        var10000 = ((OnlCgformField)fileField.get(0)).getDbFieldTxt();
                        String zipName = var10000 + "_" + id;
                        FileDownloadUtils.downloadFileMulti(response, fieldPathArr, zipName);
                        (new Thread(() -> {
                            try {
                                Thread.sleep(10000L);
                                FileUtil.del(tempFilePath);
                            } catch (InterruptedException e) {
                                a.error(e.getMessage(), e);
                            }

                        })).start();
                    }
                }
            } else {
                throw new a("找不到字段，请确认配置是否正确!");
            }
        } catch (Exception e) {
            a.error("online表单批量单字段下载文件：" + e.getMessage(), e);
            throw new JeecgBootException(e);
        }
    }
}
