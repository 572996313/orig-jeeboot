//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.dira;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportHeadService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
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

@RestController("onlCgreportItemController")
@RequestMapping({"/online/cgreport/item"})
public class c {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(c.class);
    @Autowired
    private IOnlCgreportItemService onlCgreportItemService;
    @Autowired
    private IOnlCgreportHeadService onlCgreportHeadService;

    @GetMapping({"/listByHeadId"})
    public Result<?> a(@RequestParam("headId") String headId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("cgrhead_id", headId);
        queryWrapper.orderByAsc("order_num");
        List list = this.onlCgreportItemService.list(queryWrapper);
        Result result = new Result();
        result.setSuccess(true);
        result.setResult(list);
        return result;
    }

    @GetMapping({"/listByHeadCode"})
    public Result<?> b(@RequestParam("headCode") String headCode) {
        LambdaQueryWrapper<OnlCgreportHead> headQueryWrapper = new LambdaQueryWrapper();
        headQueryWrapper.eq(OnlCgreportHead::getCode, headCode);
        OnlCgreportHead head = (OnlCgreportHead)this.onlCgreportHeadService.getOne(headQueryWrapper);
        if (head == null) {
            throw new JeecgBootException("该报表不存在");
        } else {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("cgrhead_id", head.getId());
            queryWrapper.orderByAsc("order_num");
            List list = this.onlCgreportItemService.list(queryWrapper);
            Result result = new Result();
            result.setSuccess(true);
            result.setResult(list);
            return result;
        }
    }

    @GetMapping({"/list"})
    public Result<IPage<OnlCgreportItem>> a(OnlCgreportItem onlCgreportItem, @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result result = new Result();
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(onlCgreportItem, req.getParameterMap());
        Page page = new Page((long)pageNo, (long)pageSize);
        IPage pageList = this.onlCgreportItemService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @PostMapping({"/add"})
    public Result<?> a(@RequestBody OnlCgreportItem onlCgreportItem) {
        this.onlCgreportItemService.save(onlCgreportItem);
        return Result.ok("添加成功!");
    }

    @PutMapping({"/edit"})
    public Result<?> b(@RequestBody OnlCgreportItem onlCgreportItem) {
        this.onlCgreportItemService.updateById(onlCgreportItem);
        return Result.ok("编辑成功!");
    }

    @DeleteMapping({"/delete"})
    public Result<?> c(@RequestParam(name = "id",required = true) String id) {
        this.onlCgreportItemService.removeById(id);
        return Result.ok("删除成功!");
    }

    @DeleteMapping({"/deleteBatch"})
    public Result<?> d(@RequestParam(name = "ids",required = true) String ids) {
        this.onlCgreportItemService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功!");
    }

    @GetMapping({"/queryById"})
    public Result<OnlCgreportItem> e(@RequestParam(name = "id",required = true) String id) {
        Result result = new Result();
        OnlCgreportItem onlCgreportItem = (OnlCgreportItem)this.onlCgreportItemService.getById(id);
        result.setResult(onlCgreportItem);
        result.setSuccess(true);
        return result;
    }
}
