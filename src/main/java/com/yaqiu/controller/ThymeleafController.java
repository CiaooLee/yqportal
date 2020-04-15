package com.yaqiu.controller;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ThymeleafController {
    /**
     *@Description foreground/index.html 前台-首页跳转映射
     *@author CiaoLee
     */
    @RequestMapping("/")
    public String toPortal(HttpServletRequest request) {
        /* 获取请求头的user-agent对象 */
        String userAgentStr = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        /* 获取浏览器对象 */
        Browser browser = userAgent.getBrowser();
        /* 获取操作系统对象 */
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        //获取端类型(MOBILE/COMPUTER)
        String deviceType = operatingSystem.getDeviceType().toString();
        //获取操作系统家族(ANDROID/IOS/WINDOWS/MAC_OS_X)
        String group = operatingSystem.getGroup().toString();
        System.out.println("浏览器名:"+browser.getName());
        System.out.println("浏览器类型:"+browser.getBrowserType());
        System.out.println("浏览器家族:"+browser.getGroup());
        System.out.println("浏览器生产厂商:"+browser.getManufacturer());
        System.out.println("浏览器使用的渲染引擎:"+browser.getRenderingEngine());
        System.out.println("浏览器版本:"+userAgent.getBrowserVersion());

        System.out.println("操作系统名:"+operatingSystem.getName());
        //System.out.println("访问设备类型:"+);
        //System.out.println("操作系统家族:"+);
        System.out.println("操作系统生产厂商:"+operatingSystem.getManufacturer());
        /* 如果当前用户使用手机 则跳至手机主页 */
        /* 如果当前用户使用PC 则跳至PC端主页 */
        if("COMPUTER".equals(deviceType)) return "foreground/general/index.html";
        /* 如果不能识别端类型 则跳至通用界面 */
        return "error";
    }

    /**
     *@Description foreground/content/index.html 前台-各面板跳转映射
     *@author CiaoLee
     */
    @RequestMapping("domain")
    public String toCase(ModelMap map, String type) {
        map.addAttribute("identifier", type);
        return "foreground/general/content/index.html";
    }

    /**
     *@Description foreground/content/show.html 前台-“帖子详情”跳转映射
     *@author CiaoLee
     */
    @RequestMapping("content")
    public String toDetail(String id) {
        return "foreground/general/content/show.html";
    }
}
