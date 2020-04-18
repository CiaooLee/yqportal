package com.yaqiu.util;

import com.yaqiu.entity.SessionLog;
import com.yaqiu.mapper.OperationLogMapper;
import com.yaqiu.mapper.SessionLogMapper;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class VisitorUtil {
    @Resource
    private SessionLogMapper sessionLogMapper;

    @Resource
    private OperationLogMapper operationLogMapper;

    /**
     * @Description 维护一个本类的静态变量
     * @author CiaoLee
     */
    public static VisitorUtil visitorUtil;

    //初始化的时候 将本类中的sessionLogMapper和operationLogMapper赋值给静态的本类变量
    @PostConstruct
    public void init() {
        visitorUtil = this;
        visitorUtil.sessionLogMapper = this.sessionLogMapper;
        visitorUtil.operationLogMapper = this.operationLogMapper;
    }

    /**
     * @Description 从User-Agent字符串中分析出访问者的信息
     * @author CiaoLee
     */
    public static Map<String, String> visitorInfoAnalyse() {
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 从session中取出并分析User-Agent对象 */
        //获取请求头的user-agent对象
        String userAgentStr = (String)session.getAttribute("userAgentStr");
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
        //获取浏览器对象
        Browser browser = userAgent.getBrowser();
        //获取操作系统对象
        Map<String, String> visitorInfo = new HashMap<>();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        visitorInfo.put("deviceType", operatingSystem.getDeviceType().getName()); //访问设备类型
        visitorInfo.put("browserName", browser.getName()); //浏览器名
        visitorInfo.put("browserGroup", browser.getGroup().getName()); //浏览器家族
        visitorInfo.put("browserVersion", userAgent.getBrowserVersion().getVersion()); //浏览器版本
        visitorInfo.put("osName", operatingSystem.getName()); //操作系统名
        visitorInfo.put("osGroup", operatingSystem.getGroup().getName()); //操作系统家族
        /* 获取当前时间 */
        visitorInfo.put("createTime", DateUtil.getCurrentDateTime());
        return visitorInfo;
    }

    /**
     * @Description 新增[session_log]表记录
     * @author CiaoLee
     */
    public static void generateSessionLog() {
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 从session中取出数据 */
        String id = (String)session.getAttribute("sessionLogId");
        String ip = (String)session.getAttribute("ip");
        String userId = (String)session.getAttribute("userId");
        Map<String, String> visitorInfo = (Map<String, String>)session.getAttribute("visitorInfo");
        /* 生成[session_log]新记录对象 */
        SessionLog sessionLog = new SessionLog();
        sessionLog.setId(id);
        sessionLog.setIp(ip);
        sessionLog.setDeviceType(visitorInfo.get("deviceType"));
        sessionLog.setBrowserName(visitorInfo.get("browserName"));
        sessionLog.setBrowserGroup(visitorInfo.get("browserGroup"));
        sessionLog.setBrowserVersion(visitorInfo.get("browserVersion"));
        sessionLog.setOsName(visitorInfo.get("osName"));
        sessionLog.setOsGroup(visitorInfo.get("osGroup"));
        sessionLog.setUserId(userId);
        sessionLog.setCreateTime(visitorInfo.get("createTime"));
        visitorUtil.sessionLogMapper.insertSelective(sessionLog);
    }
}
