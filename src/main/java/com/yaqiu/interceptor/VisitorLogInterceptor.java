package com.yaqiu.interceptor;

import com.yaqiu.util.*;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description 操作日志拦截器
 * @author CiaoLee
 */
public class VisitorLogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /* 从请求中取出session */
        HttpSession session = request.getSession();
        /* 将session存入本地线程池 以便在本次请求链中随时获取 */
        SessionUtil.set(session);
        /* 将访问者ip和User-Agent存入session */
        session.setAttribute("ip", request.getRemoteAddr());
        session.setAttribute("userAgentStr", request.getHeader("User-Agent"));
        /* 根据session中的[sessionLogId]是否为空来判断是否为新会话 从而新增不同的操作记录 */
        boolean isNewSession = ObjectUtil.isEmpty(session.getAttribute("sessionLogId"));
        //若是一个全新的会话 则新建一个UUID作为本次会话记录[SESSION_LOG]表的主键 和 所有操作记录[OPERATION_LOG]表的外键
        if(isNewSession) {
            Map<String, String> visitorInfo = VisitorUtil.visitorInfoAnalyse();
            session.setAttribute("sessionLogId", UUIDUtil.getUUID());
            session.setAttribute("deviceType", visitorInfo.get("deviceType"));
            session.setAttribute("visitorInfo", visitorInfo);
        }
        /* 将isNewSession存入session 以便在afterCompletion中判断 是否生成[SESSION_LOG]记录 */
        session.setAttribute("isNewSession", isNewSession);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 根据[isNewSession]判断 如果是新会话 则生成[SESSION_LOG表]记录 */
        if((boolean) session.getAttribute("isNewSession")) {
            VisitorUtil.generateSessionLog();
        }
        /* 生成本次请求的[OPERATION_LOG]表操作记录 */
        if(ObjectUtil.isNotEmpty(session.getAttribute("operationLogType"))) {
            VisitorUtil.generateOperationLog();
        }
    }
}
