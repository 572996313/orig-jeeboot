package org.jeecg.common.system.base.controller;
//
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
//import org.jeecg.common.controller.JeecgExcelController;
import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * @Description: Controller基类
// * @Author: dangzhenghui@163.com
// * @Date: 2019-4-21 8:13
// * @Version: 1.0
// */
//@Slf4j
public class JeecgController<T, S extends IService<T>> {
    /**issues/2933 JeecgController注入service时改用protected修饰，能避免重复引用service*/
//    @Autowired
//    protected S service;
}
