//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.enhance.impl.http.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.RestUtil;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public interface CgformEnhanceHttpInter {
    Logger logger = LoggerFactory.getLogger(CgformEnhanceHttpInter.class);

    default void execute(String tableName, JSONObject json, OnlCgformEnhanceJava enhance) {
    }

    default void execute(String tableName, List<Map<String, Object>> dataList, OnlCgformEnhanceJava enhance) {
    }

    default Object sendPost(JSONObject params, OnlCgformEnhanceJava enhance) {
        if (enhance == null) {
            return null;
        } else {
            String url = enhance.getCgJavaValue();
            if (oConvertUtils.isEmpty(url)) {
                return null;
            } else {
                if (!url.startsWith("http") && !url.startsWith("https")) {
                    String baseUrl = RestUtil.getBaseUrl();
                    if (url.startsWith("/")) {
                        url = baseUrl + url;
                    } else {
                        url = baseUrl + "/" + url;
                    }
                }

                HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
                HttpHeaders headers = this.getHeaders(request);
                ResponseEntity response = RestUtil.request(url, HttpMethod.POST, headers, (JSONObject)null, params, String.class);
                if (response.getStatusCode() == HttpStatus.OK) {
                    String body = (String)response.getBody();
                    if (oConvertUtils.isNotEmpty(body)) {
                        try {
                            JSONObject res = JSON.parseObject(body);
                            if (res.getBoolean("success")) {
                                return res.get("result");
                            }

                            throw new JeecgBootException(res.getString("message"));
                        } catch (JeecgBootException e) {
                            throw e;
                        } catch (Exception e) {
                            Logger var10000 = logger;
                            String var10001 = e.getMessage();
                            var10000.warn("请求Online表单Java增强http接口时转换数据出错：" + var10001 + "\n body: " + body);
                            throw new JeecgBootException("Online表单Java增强http接口JSON转换失败！");
                        }
                    }
                }

                return null;
            }
        }
    }

    default HttpHeaders getHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        Enumeration headerNames = request.getHeaderNames();

        while(headerNames.hasMoreElements()) {
            String key = (String)headerNames.nextElement();
            Enumeration values = request.getHeaders(key);

            while(values.hasMoreElements()) {
                headers.set(key, (String)values.nextElement());
            }
        }

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.remove("Accept-Encoding");
        headers.remove("accept-encoding");
        headers.remove("Accept");
        headers.add("Accept", "application/json");
        return headers;
    }
}
