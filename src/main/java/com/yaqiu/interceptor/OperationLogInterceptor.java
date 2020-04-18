package com.yaqiu.interceptor;

import com.yaqiu.util.SessionUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description 操作日志拦截器
 * @author CiaoLee
 */
public class OperationLogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /* 初始化[新会话]判断标志 */
        boolean isNewSession = true;
        /* 从请求中取出session */
        HttpSession session = request.getSession();
        /* 从请求中取出cookie */
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                //根据cookie中的[SESSION]判断此次请求是否是全新的请求
                if("SESSION".equals(cookie.getName())) {
                    isNewSession = false;
                }
            }
        }
        /* 根据[isNewSession]新增不同的记录 */
        //若是一个全新的会话 则需要新增[SESSION_LOG]表会话记录 和 [OPERATION_LOG]表操作记录
        if(isNewSession) {

        }
        //若不是一个新的会话 只需要新增[OPERATION_LOG]表操作记录
        if(!isNewSession) {

        }
        /* 将session存入本地线程池 以便在本次请求链中随时获取 */
        SessionUtil.set(session);
        System.out.println(request.isRequestedSessionIdValid());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /* 记录用户操作日志 */
        Cookie cookie = new Cookie("sessionId","CookieTestInfo");
        cookie.setDomain("192.168.31.110");
        cookie.setPath("/");
        cookie.setMaxAge(1800);
        response.addCookie(cookie);
    }
}
