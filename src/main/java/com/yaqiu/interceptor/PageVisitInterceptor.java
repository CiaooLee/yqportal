package com.yaqiu.interceptor;

import com.yaqiu.util.SessionUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PageVisitInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /* 从本地线程池获取session 如果没有 就从request中取session存入本地线程池 这一步的目的是保证线程安全 */
        HttpSession mySession = SessionUtil.getMySession();
        if(mySession == null) {
            mySession = request.getSession();
            SessionUtil.setMySession(mySession);
        }
        /* 从session中取出当前登录用户信息 */
        /* 获取当前用户的信息 组成操作记录 */

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
