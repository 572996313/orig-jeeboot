//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dirc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import lombok.Generated;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.online.cgform.entity.OnlCgformIndex;
import org.jeecg.modules.online.cgform.service.IOnlCgformIndexService;
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

@RestController("onlCgformIndexController")
@RequestMapping({"/online/cgform/index"})
public class e {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(e.class);
    @Autowired
    private IOnlCgformIndexService onlCgformIndexService;

    @GetMapping({"/listByHeadId"})
    public Result<?> a(@RequestParam("headId") String headId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("cgform_head_id", headId);
        queryWrapper.eq("del_flag", CommonConstant.DEL_FLAG_0);
        queryWrapper.orderByDesc("create_time");
        List list = this.onlCgformIndexService.list(queryWrapper);
        return Result.ok(list);
    }

    @GetMapping({"/list"})
    public Result<IPage<OnlCgformIndex>> a(OnlCgformIndex onlCgformIndex, @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result result = new Result();
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(onlCgformIndex, req.getParameterMap());
        Page page = new Page((long)pageNo, (long)pageSize);
        IPage pageList = this.onlCgformIndexService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @PostMapping({"/add"})
    public Result<OnlCgformIndex> a(@RequestBody OnlCgformIndex onlCgformIndex) {
        Result result = new Result();

        try {
            this.onlCgformIndexService.save(onlCgformIndex);
            result.success("添加成功！");
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            result.error500("操作失败");
        }

        return result;
    }

    @PutMapping({"/edit"})
    public Result<OnlCgformIndex> b(@RequestBody OnlCgformIndex onlCgformIndex) {
        Result result = new Result();
        OnlCgformIndex onlCgformIndexEntity = (OnlCgformIndex)this.onlCgformIndexService.getById(onlCgformIndex.getId());
        if (onlCgformIndexEntity == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = this.onlCgformIndexService.updateById(onlCgformIndex);
            if (ok) {
                result.success("修改成功!");
            }
        }

        return result;
    }

    @DeleteMapping({"/delete"})
    public Result<OnlCgformIndex> b(@RequestParam(name = "id",required = true) String id) {
        Result result = new Result();
        OnlCgformIndex onlCgformIndex = (OnlCgformIndex)this.onlCgformIndexService.getById(id);
        if (onlCgformIndex == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = this.onlCgformIndexService.removeById(id);
            if (ok) {
                result.success("删除成功!");
            }
        }

        return result;
    }

    @DeleteMapping({"/deleteBatch"})
    public Result<OnlCgformIndex> c(@RequestParam(name = "ids",required = true) String ids) {
        Result result = new Result();
        if (ids != null && !"".equals(ids.trim())) {
            this.onlCgformIndexService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        } else {
            result.error500("参数不识别！");
        }

        return result;
    }

    @GetMapping({"/queryById"})
    public Result<OnlCgformIndex> d(@RequestParam(name = "id",required = true) String id) {
        Result result = new Result();
        OnlCgformIndex onlCgformIndex = (OnlCgformIndex)this.onlCgformIndexService.getById(id);
        if (onlCgformIndex == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(onlCgformIndex);
            result.setSuccess(true);
        }

        return result;
    }
}
