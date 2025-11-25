//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.interceptor;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.jeecg.common.api.dto.OnlineAuthDTO;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.OnlineAuth;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class a implements HandlerInterceptor {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(a.class);
    private ISysBaseAPI b;
    private static final String c = "/online/cgreport";
    private static final String[] d = new String[]{"/online/cgreport"};

    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, Object handler) {
        boolean anno = handler.getClass().isAssignableFrom(HandlerMethod.class);
        if (anno) {
            OnlineAuth onlineAuth = (OnlineAuth)((HandlerMethod)handler).getMethodAnnotation(OnlineAuth.class);
            if (onlineAuth != null) {
                a.debug("===== online 菜单访问拦截器 =====");
                String requestPath = request.getRequestURI().substring(request.getContextPath().length());
                requestPath = this.a(requestPath);
                String authKey = onlineAuth.value();
                String code = requestPath.substring(requestPath.lastIndexOf(authKey) + authKey.length());
                Logger var10000 = a;
                String var10001 = request.getMethod();
                var10000.debug("拦截请求(" + var10001 + ")：" + requestPath + ",");
                List possibleUrl = new ArrayList();

                for(String pre : d) {
                    possibleUrl.add(pre + code);
                }

                if (this.b == null) {
                    this.b = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);
                }

                String username = JwtUtil.getUserNameByToken(request);
                OnlineAuthDTO dto = new OnlineAuthDTO(username, possibleUrl, "/online/cgreport");
                boolean hasPermission = this.b.hasOnlineAuth(dto);
                if (!hasPermission) {
                    var10000 = a;
                    var10001 = request.getMethod();
                    var10000.info("请求无权限(" + var10001 + ")：" + requestPath);
                    this.a(response, authKey);
                    return false;
                }
            }
        }

        return true;
    }

    private String a(String requestPath) {
        String url = "";
        if (oConvertUtils.isNotEmpty(requestPath)) {
            url = requestPath.replace("\\", "/");
            url = url.replace("//", "/");
            if (url.contains("//")) {
                url = this.a(url);
            }
        }

        return url;
    }

    private void a(HttpServletResponse response, String authKey) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("auth", "fail");

        try (PrintWriter writer = response.getWriter()) {
            if ("exportXls".equals(authKey)) {
                writer.print("");
            } else {
                Result result = Result.error("无权限访问(操作)");
                writer.print(JSON.toJSON(result));
            }
        } catch (IOException e) {
            a.error(e.getMessage());
        }

    }
}
