package com.yaqiu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 服务器安全保障拦截器
 * @author CiaoLee
 */
public class InsuranceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        /* 百度站点验证文件 */
        if("/baidu_verify_LL4bi1KNwx.html".equals(uri)) return true;
        /* 搜狗站点验证工具 */
        if("/sogousiteverification.txt".equals(uri)) return true;
        /* 360站点验证工具 */
        if("/88c8c55531347c01a765008b814386b4.txt".equals(uri)) return true;
        return false;
    }
}
