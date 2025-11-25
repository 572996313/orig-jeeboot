//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.dir.dira;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Map;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.online.cgform.service.impl.OnlineBaseExtApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("onlineBaseExtAPIController")
@RequestMapping({"/online/api"})
public class a {
    @Autowired
    OnlineBaseExtApiImpl onlineBaseExtApi;

    @PostMapping({"/cgform/crazyForm/{name}"})
    String a(@PathVariable("name") String tableName, @RequestBody JSONObject jsonObject) throws Exception {
        return this.onlineBaseExtApi.cgformPostCrazyForm(tableName, jsonObject);
    }

    @PutMapping({"/cgform/crazyForm/{name}"})
    String b(@PathVariable("name") String tableName, @RequestBody JSONObject jsonObject) throws Exception {
        return this.onlineBaseExtApi.cgformPutCrazyForm(tableName, jsonObject);
    }

    @GetMapping({"/cgform/queryAllDataByTableName"})
    JSONObject a(@RequestParam("tableName") String tableName, @RequestParam("dataIds") String dataIds) {
        return this.onlineBaseExtApi.cgformQueryAllDataByTableName(tableName, dataIds);
    }

    @DeleteMapping({"/cgform/cgformDeleteDataByCode"})
    String b(@RequestParam("cgformCode") String cgformCode, @RequestParam("dataIds") String dataIds) {
        return this.onlineBaseExtApi.cgformDeleteDataByCode(cgformCode, dataIds);
    }

    @GetMapping({"/cgreportGetData"})
    Map<String, Object> a(@RequestParam("code") String code, @RequestParam("forceKey") String forceKey, @RequestParam("dataList") String dataList) {
        return this.onlineBaseExtApi.cgreportGetData(code, forceKey, dataList);
    }

    @GetMapping({"/cgreportGetDataPackage"})
    List<DictModel> a(@RequestParam("code") String code, @RequestParam("dictText") String dictText, @RequestParam("dictCode") String dictCode, @RequestParam("dataList") String dataList) {
        return this.onlineBaseExtApi.cgreportGetDataPackage(code, dictText, dictCode, dataList);
    }
}
