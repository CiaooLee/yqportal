package com.yaqiu.interceptor;

import com.yaqiu.util.*;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description 操作日志拦截器
 * @author CiaoLee
 */
public class VisitorLogInterceptor implements HandlerInterceptor {
    @Resource
    private VisitorUtil visitorUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /* 从请求中取出session */
        HttpSession session = request.getSession();
        /* 将session存入本地线程池 以便在本次请求链中随时获取 */
        SessionUtil.set(session);
        /* 将访问者ip和User-Agent存入session */
        String userAgentStr = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        session.setAttribute("ip", request.getRemoteAddr());
        session.setAttribute("userAgent", userAgent);
        /* 根据session中的[sessionLogId]是否为空来判断是否为新会话 从而新增不同的操作记录 */
        boolean isNewSession = ObjectUtil.isEmpty(session.getAttribute("sessionLogId"));
        /* 将isNewSession存入session 以便在afterCompletion中判断 是否生成[SESSION_LOG]记录 */
        session.setAttribute("isNewSession", isNewSession);
        //若是一个全新的会话 则新建一个UUID 作为本次会话记录[SESSION_LOG]表的主键 和 所有操作记录[OPERATION_LOG]表的外键
        if(isNewSession) {
            //获取端类型 以便Thymeleaf执行不同端的页面跳转
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            String deviceType = operatingSystem.getDeviceType()==null?null:operatingSystem.getDeviceType().getName();
            session.setAttribute("sessionLogId", UUIDUtil.getUUID());
            session.setAttribute("deviceType", deviceType);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 生成本次请求的[OPERATION_LOG]表操作记录 */
        if(ObjectUtil.isNotEmpty(session.getAttribute("operationLogType"))) visitorUtil.generateOperationLog();
        /* 根据[isNewSession]判断 如果是新会话 则生成[SESSION_LOG表]记录 */
        if((boolean)session.getAttribute("isNewSession")) visitorUtil.generateSessionLog();
    }
}
