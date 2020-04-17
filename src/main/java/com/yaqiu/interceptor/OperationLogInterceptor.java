package com.yaqiu.interceptor;

import com.yaqiu.util.SessionUtil;
import org.springframework.web.servlet.HandlerInterceptor;

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
        /* 将session存入本地线程池 以便在本次请求链中随时获取 */
        SessionUtil.set(request.getSession());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /* 记录用户操作日志 */
        // 获取session
        HttpSession session = SessionUtil.get();
        
    }
}
