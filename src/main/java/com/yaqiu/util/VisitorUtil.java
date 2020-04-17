package com.yaqiu.util;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class VisitorUtil {
    /**
     *@Description foreground/index.html 前台-首页跳转映射
     *@author CiaoLee
     */
    public static Map visitorInfoAnalyse() {
        /* 初始化返回结果集 */
        Map<String, String> visitorInfo = new HashMap<>();
        /* 获取request对象 */
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        /* 从request中取出并分析User-Agent对象 */
        //获取请求头的user-agent对象
        String userAgentStr = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        //获取浏览器对象
        Browser browser = userAgent.getBrowser();
        //获取操作系统对象
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        System.out.println("浏览器名:"+browser.getName());
        System.out.println("浏览器类型:"+browser.getBrowserType());
        System.out.println("浏览器家族:"+browser.getGroup());
        System.out.println("浏览器生产厂商:"+browser.getManufacturer());
        System.out.println("浏览器使用的渲染引擎:"+browser.getRenderingEngine());
        System.out.println("浏览器版本:"+userAgent.getBrowserVersion());
        System.out.println("操作系统名:"+operatingSystem.getName());
        System.out.println("访问设备类型:"+operatingSystem.getDeviceType());
        System.out.println("操作系统家族:"+operatingSystem.getGroup());
        System.out.println("操作系统生产厂商:"+operatingSystem.getManufacturer());
        return null;
    }
}
