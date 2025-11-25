//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.dira;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import lombok.Generated;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("onlCgreportParamController")
@RequestMapping({"/online/cgreport/param"})
public class d {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(d.class);
    @Autowired
    private IOnlCgreportParamService onlCgreportParamService;

    @GetMapping({"/listByHeadId"})
    public Result<?> a(@RequestParam("headId") String headId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("cgrhead_id", headId);
        queryWrapper.orderByAsc("order_num");
        List list = this.onlCgreportParamService.list(queryWrapper);
        Result result = new Result();
        result.setSuccess(true);
        result.setResult(list);
        return result;
    }

    @GetMapping({"/list"})
    public Result<IPage<OnlCgreportParam>> a(OnlCgreportParam onlCgreportParam, @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result result = new Result();
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(onlCgreportParam, req.getParameterMap());
        Page page = new Page((long)pageNo, (long)pageSize);
        IPage pageList = this.onlCgreportParamService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @PostMapping({"/add"})
    public Result<?> a(@RequestBody OnlCgreportParam onlCgreportParam) {
        this.onlCgreportParamService.save(onlCgreportParam);
        return Result.ok("添加成功!");
    }

    @PutMapping({"/edit"})
    public Result<?> b(@RequestBody OnlCgreportParam onlCgreportParam) {
        this.onlCgreportParamService.updateById(onlCgreportParam);
        return Result.ok("编辑成功!");
    }

    @DeleteMapping({"/delete"})
    public Result<?> b(@RequestParam(name = "id",required = true) String id) {
        this.onlCgreportParamService.removeById(id);
        return Result.ok("删除成功!");
    }

    @DeleteMapping({"/deleteBatch"})
    public Result<?> c(@RequestParam(name = "ids",required = true) String ids) {
        this.onlCgreportParamService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    @GetMapping({"/queryById"})
    public Result<OnlCgreportParam> d(@RequestParam(name = "id",required = true) String id) {
        Result result = new Result();
        OnlCgreportParam onlCgreportParam = (OnlCgreportParam)this.onlCgreportParamService.getById(id);
        result.setResult(onlCgreportParam);
        return result;
    }
}
