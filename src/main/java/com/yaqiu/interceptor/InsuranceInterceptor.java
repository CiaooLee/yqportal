package com.yaqiu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.yaqiu.constant.GlobalConstant.*;

/**
 * @Description 服务器安全保障拦截器
 * @author CiaoLee
 */
public class InsuranceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        /* 百度站点验证文件 */
        if(BAIDU_VALIDATION_FILE_URI.equals(uri)) return true;
        return false;
    }
}
