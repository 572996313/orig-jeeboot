//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.service.dira;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.apache.commons.lang.StringUtils;
import org.jeecg.chatgpt.dto.chat.MultiChatMessage;
import org.jeecg.chatgpt.dto.chat.MultiChatMessage.Role;
import org.jeecg.chatgpt.service.AiChatService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.exception.JeecgBootBizTipException;
import org.jeecg.common.util.AssertUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.dirb.b;
import org.jeecg.modules.online.cgform.dird.d;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.mapper.OnlCgreportHeadMapper;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportAiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("onlCgreportAiServiceImpl")
public class c implements IOnlCgreportAiService {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(c.class);
    @Autowired
    AiChatService aiChatService;
    @Autowired
    IOnlCgformHeadService cgformHeadService;
    @Autowired
    IOnlCgformFieldService cgformFieldService;
    @Autowired
    OnlCgreportHeadMapper cgreportHeadMapper;

    public Result<?> genCgreportByCgform(String cgformTableName, String prompt) {
        AssertUtils.assertNotEmpty("请选择Online表单", cgformTableName);
        AssertUtils.assertNotEmpty("请输入报表需求", prompt);
        OnlCgformHead cgformTable = (OnlCgformHead)this.cgformHeadService.getOne((Wrapper)(new LambdaQueryWrapper<OnlCgformHead>()).eq(OnlCgformHead::getTableName, cgformTableName));
        AssertUtils.assertNotEmpty("Online表单不存在", cgformTable);
        List<OnlCgformField> fieldList = this.cgformFieldService.queryFormFields(cgformTable.getId(), false);
        AssertUtils.assertNotEmpty("Online表单没有字段", fieldList);
        JSONObject tableDesign = new JSONObject();
        tableDesign.put("table_name", cgformTableName);
        tableDesign.put("table_txt", cgformTable.getTableTxt());
        JSONArray tableFields = new JSONArray();

        for(OnlCgformField field : fieldList) {
            if (b.b.equals(field.getDbIsPersist())) {
                String dbFieldName = field.getDbFieldName();
                if (!d.i(dbFieldName) && !"bpm_status".equalsIgnoreCase(dbFieldName)) {
                    JSONObject fieldDesign = new JSONObject();
                    fieldDesign.put("name", dbFieldName);
                    fieldDesign.put("txt", field.getDbFieldTxt());
                    fieldDesign.put("type", field.getDbType());
                    tableFields.add(fieldDesign);
                }
            }
        }

        tableDesign.put("fields", tableFields);
        JeecgBootBizTipException aiException = new JeecgBootBizTipException("Ai开小差了，请稍后再试");
        String schemaStr = this.a(tableDesign.toJSONString(), prompt);

        JSONArray schemaLite;
        try {
            schemaLite = JSONArray.parseArray(schemaStr);
        } catch (JSONException var12) {
            throw aiException;
        } catch (Exception e) {
            a.error(e.getMessage(), e);
            throw aiException;
        }

        if (schemaLite != null && schemaLite.size() == 3) {
            String reportCode = schemaLite.getString(0);
            reportCode = oConvertUtils.camelToUnderline(reportCode);
            reportCode = this.a(reportCode, (Integer)null);
            JSONObject res = new JSONObject();
            res.put("code", reportCode);
            res.put("name", schemaLite.getString(1));
            res.put("cgrSql", schemaLite.getString(2));
            return Result.OK("ok", res);
        } else {
            throw aiException;
        }
    }

    private String a(String tableDDL, String prompt) {
        String demoJson = "[\"count_age\",\"统计年龄\",\"SELECT name,COUNT(age) cnt FROM demo\"]";
        String sysMsgContent = "请根据我的业务需求帮我生成一个业务报表，报表参考JSON如下：" + demoJson + "\n在该参考JSON中，下标0为报表的英文code，下标1为中文名称，下标2为报表MySQL语句。\n我会给你数据库表的具体设计，而用户会给你他的报表需求，你根据表设计生成对应需求的SQL语句。\n表设计如下：\n" + tableDDL + "\n特别注意：\n1. 请严格按照参考json数组的格式输出，不要有其他任何描述，应以[开头，以]结尾\n2. 报表英文code和字段英文code应使用下划线命名法(UnderScoreCase)\n3. 要生成能直接执行的SQL，不能有语法错误。\n4. 在 SELECT 的字段中，如果是函数或子查询，需要加上别名\n5. 在 SELECT 的字段中，不仅要SELECT报表需求中的字段，表设计中的字段如果有能标识数据的或其他有用的信息，也可以加上。\n6. 不需要LIMIT、ORDER BY等语句\n";
        List messages = new LinkedList();
        messages.add(MultiChatMessage.builder().role(Role.SYSTEM).content(sysMsgContent).build());
        messages.add(MultiChatMessage.builder().role(Role.USER).content("\n\n我的报表需求如下：\n> " + prompt + "\n").build());
        String gptResp = this.aiChatService.multiCompletions(messages);
        if (StringUtils.isEmpty(gptResp)) {
            throw new JeecgBootBizTipException("如果您想使用AI助手，请先设置相应配置!");
        } else {
            if (gptResp.contains("</think>")) {
                String[] thinkSplit = gptResp.split("</think>");
                gptResp = thinkSplit[thinkSplit.length - 1];
            }

            a.debug("Ai返回结果：" + gptResp);
            Pattern pattern = Pattern.compile("\\[.*]", 32);
            Matcher matcher = pattern.matcher(gptResp);
            String returnData = "";
            if (matcher.find()) {
                returnData = matcher.group(0);
            }

            return returnData;
        }
    }

    private String a(String code, Integer count) {
        String originCode = code;
        if (null != count && count >= 0) {
            count = count + 1;
            code = code + "_" + count;
        } else {
            count = 0;
        }

        if (null != code && !code.isEmpty()) {
            return this.cgreportHeadMapper.exists((Wrapper)Wrappers.lambdaQuery(OnlCgreportHead.class).eq(OnlCgreportHead::getCode, code)) ? this.a(originCode, count) : code;
        } else {
            return "ai_report_" + System.currentTimeMillis();
        }
    }
}
