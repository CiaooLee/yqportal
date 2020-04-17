package com.yaqiu.interceptor;

import com.yaqiu.util.SessionUtil;
import com.yaqiu.util.VisitorUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 *@Description 页面访问拦截器
 *@author CiaoLee
 */
public class PageVisitInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        System.out.println("token:"+token);
        // 根据token获取登录信息
        HttpSession session = request.getSession();
        session.setAttribute("id",request.getRemoteAddr());
        SessionUtil.set(session);
        System.out.println("请求方法方法前拦截");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Cookie cookie = new Cookie("dsa","asd");
        response.addCookie(cookie);
        SessionUtil.remove();
        System.out.println("请求方法方法后处理");
    }
}
