package org.jeecg.config.firewall;

import org.jeecg.config.web.JeecgWebProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

/**
 * 低代码模式/防火墙拦截器
 *
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-01-08
 */
public class LowCodeModeInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LowCodeModeInterceptor.class);

    private final JeecgWebProperties.FirewallConfig config;

    /**
     * SQL注入关键字
     */
    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
            ".*(\\bor\\b|\\band\\b|\\bunion\\b|\\bselect\\b|\\bupdate\\b|\\bdelete\\b|\\binsert\\b|" +
            "\\bdrop\\b|\\bexec\\b|\\bexecute\\b|\\bscript\\b|\\bjavascript\\b|\\balert\\b).*",
            Pattern.CASE_INSENSITIVE
    );

    /**
     * XSS攻击关键字
     */
    private static final Pattern XSS_PATTERN = Pattern.compile(
            ".*(<script|<iframe|<object|<embed|javascript:|onerror=|onload=).*",
            Pattern.CASE_INSENSITIVE
    );

    public LowCodeModeInterceptor(JeecgWebProperties.FirewallConfig config) {
        this.config = config;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        
        // 检查黑名单
        if (isInBlacklist(uri)) {
            log.warn("访问被拒绝 - 黑名单URL: {}", uri);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"success\":false,\"message\":\"Access Denied\"}");
            return false;
        }
        
        // 低代码模式检查
        if (config.isLowCodeMode() && isLowCodeRequest(uri)) {
            log.warn("访问被拒绝 - 低代码模式限制: {}", uri);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"success\":false,\"message\":\"Low code mode is disabled\"}");
            return false;
        }
        
        // SQL注入检查
        if (config.isSqlInjectionCheck()) {
            String queryString = request.getQueryString();
            if (queryString != null && SQL_INJECTION_PATTERN.matcher(queryString).matches()) {
                log.warn("SQL注入攻击检测 - URI: {}, Query: {}", uri, queryString);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"success\":false,\"message\":\"Invalid request\"}");
                return false;
            }
        }
        
        // XSS攻击检查
        if (config.isXssCheck()) {
            String queryString = request.getQueryString();
            if (queryString != null && XSS_PATTERN.matcher(queryString).matches()) {
                log.warn("XSS攻击检测 - URI: {}, Query: {}", uri, queryString);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"success\":false,\"message\":\"Invalid request\"}");
                return false;
            }
        }
        
        return true;
    }

    /**
     * 是否在黑名单中
     */
    private boolean isInBlacklist(String uri) {
        return config.getBlacklistUrls().stream()
                .anyMatch(pattern -> uri.matches(pattern));
    }

    /**
     * 是否为低代码请求
     */
    private boolean isLowCodeRequest(String uri) {
        return uri.contains("/online/cgform/") || 
               uri.contains("/auto/") ||
               uri.contains("/drag/");
    }
}