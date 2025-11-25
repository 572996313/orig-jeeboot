//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dirc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import lombok.Generated;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.service.IOnlCgformButtonService;
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

@RestController("onlCgformButtonController")
@RequestMapping({"/online/cgform/button"})
public class b {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(b.class);
    @Autowired
    private IOnlCgformButtonService onlCgformButtonService;

    @GetMapping({"/list/{code}"})
    public Result<IPage<OnlCgformButton>> a(OnlCgformButton onlCgformButton, @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize, HttpServletRequest req, @PathVariable("code") String code) {
        Result result = new Result();
        onlCgformButton.setCgformHeadId(code);
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(onlCgformButton, req.getParameterMap());
        queryWrapper.notIn("button_code", org.jeecg.modules.online.cgform.dird.b.getButtonCodeSet());
        Page page = new Page((long)pageNo, (long)pageSize);
        IPage pageList = this.onlCgformButtonService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @PostMapping({"/add"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<OnlCgformButton> a(@RequestBody OnlCgformButton onlCgformButton) {
        Result result = new Result();

        try {
            this.onlCgformButtonService.saveButton(onlCgformButton);
            if (org.jeecg.modules.online.cgform.dird.b.getButtonCodeSet().contains(onlCgformButton.getButtonCode())) {
                result.success("修改成功！");
            } else {
                result.success("添加成功！");
            }
        } catch (JeecgBootException e) {
            result.error500(e.getMessage());
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            result.error500("操作失败");
        }

        return result;
    }

    @PostMapping({"/aitest"})
    public Result<OnlCgformButton> a(@RequestBody JSONArray jsonArray) {
        Result result = new Result();

        try {
            for(int i = 0; i < jsonArray.size(); ++i) {
                JSONObject json = jsonArray.getJSONObject(i);
                OnlCgformButton button = (OnlCgformButton)JSONObject.parseObject(json.toJSONString(), OnlCgformButton.class);
                this.onlCgformButtonService.saveButton(button);
            }

            result.success("添加成功！");
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            result.error500("操作失败");
        }

        return result;
    }

    @PutMapping({"/edit"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<OnlCgformButton> b(@RequestBody OnlCgformButton onlCgformButton) {
        return this.onlCgformButtonService.editButton(onlCgformButton);
    }

    @DeleteMapping({"/delete"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<OnlCgformButton> a(@RequestParam(name = "id",required = true) String id) {
        Result result = new Result();
        OnlCgformButton onlCgformButton = (OnlCgformButton)this.onlCgformButtonService.getById(id);
        if (onlCgformButton == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = this.onlCgformButtonService.removeById(id);
            if (ok) {
                result.success("删除成功!");
            }
        }

        return result;
    }

    @DeleteMapping({"/deleteBatch"})
    @CacheEvict(
        value = {"sys:cache:online:list", "sys:cache:online:form"},
        allEntries = true,
        beforeInvocation = true
    )
    public Result<OnlCgformButton> b(@RequestParam(name = "ids",required = true) String ids) {
        Result result = new Result();
        if (ids != null && !"".equals(ids.trim())) {
            this.onlCgformButtonService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        } else {
            result.error500("参数不识别！");
        }

        return result;
    }

    @GetMapping({"/queryById"})
    public Result<OnlCgformButton> c(@RequestParam(name = "id",required = true) String id) {
        Result result = new Result();
        OnlCgformButton onlCgformButton = (OnlCgformButton)this.onlCgformButtonService.getById(id);
        if (onlCgformButton == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(onlCgformButton);
            result.setSuccess(true);
        }

        return result;
    }

    @GetMapping({"/builtInList/{formId}"})
    public Result<List<OnlCgformButton>> d(@PathVariable("formId") String formId) {
        List list = this.onlCgformButtonService.queryBuiltInButtonList(formId);
        return Result.OK(list);
    }
}
