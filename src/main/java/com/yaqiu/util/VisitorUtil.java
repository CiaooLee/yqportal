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
     * @Description 从request对象中分析出访问者的信息
     * @param request 请求对象
     * @author CiaoLee
     */
    public static Map<String, String> visitorInfoAnalyse(HttpServletRequest request) {
        /* 初始化返回结果集 */
        Map<String, String> visitorInfo = new HashMap<>();
        /* 获取当前时间 */
        visitorInfo.put("createTime", DateUtil.getCurrentDateTime());
        /* 从request中取出ip地址 */
        visitorInfo.put("ip", request.getRemoteAddr());
        /* 从request中取出并分析User-Agent对象 */
        //获取请求头的user-agent对象
        String userAgentStr = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        //获取浏览器对象
        Browser browser = userAgent.getBrowser();
        //获取操作系统对象
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        visitorInfo.put("deviceType", operatingSystem.getDeviceType().getName()); //访问设备类型
        visitorInfo.put("browserName", browser.getName()); //浏览器名
        visitorInfo.put("browserGroup", browser.getGroup().getName()); //浏览器家族
        visitorInfo.put("browserVersion", userAgent.getBrowserVersion().getVersion()); //浏览器版本
        visitorInfo.put("operatingSystemName", operatingSystem.getName()); //操作系统名
        visitorInfo.put("operatingSystemGroup", operatingSystem.getGroup().getName()); //操作系统家族
        return visitorInfo;
    }
}
