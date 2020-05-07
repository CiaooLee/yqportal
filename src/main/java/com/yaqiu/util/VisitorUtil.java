package com.yaqiu.util;

import com.alibaba.fastjson.JSONObject;
import com.yaqiu.entity.OperationLog;
import com.yaqiu.entity.SessionLog;
import com.yaqiu.mapper.OperationLogMapper;
import com.yaqiu.mapper.SessionLogMapper;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.yaqiu.constant.GlobalConstant.ALIYUN_IP_APPCODE;
import static com.yaqiu.constant.GlobalConstant.ALIYUN_IP_SERVER_HOST;

@Component
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
        //获取参数
        String deviceType = operatingSystem.getDeviceType()==null?null:operatingSystem.getDeviceType().getName();
        String browserGroup = browser.getGroup()==null?null:browser.getGroup().getName();
        String browserVersion = userAgent.getBrowserVersion()==null?null:userAgent.getBrowserVersion().getVersion();
        String osGroup = operatingSystem.getGroup()==null?null:operatingSystem.getGroup().getName();
        //组装参数
        visitorInfo.put("deviceType", deviceType); //访问设备类型
        visitorInfo.put("browserName", browser.getName()); //浏览器名
        visitorInfo.put("browserGroup", browserGroup); //浏览器家族
        visitorInfo.put("browserVersion", browserVersion); //浏览器版本
        visitorInfo.put("osName", operatingSystem.getName()); //操作系统名
        visitorInfo.put("osGroup", osGroup); //操作系统家族
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

    /**
     * @Description 新增[session_log]表记录
     * @author CiaoLee
     */
    public static void generateOperationLog() {
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 从session中取出数据 */
        String sessionLogId = (String)session.getAttribute("sessionLogId");
        byte type = (byte)session.getAttribute("operationLogType");
        String content = (String)session.getAttribute("operationLogContent");
        String createTime = (String)session.getAttribute("operationLogCreateTime");
        OperationLog operationLog = new OperationLog();
        operationLog.setId(UUIDUtil.getUUID());
        operationLog.setSessionLogId(sessionLogId);
        operationLog.setType(type);
        operationLog.setContent(content);
        operationLog.setCreateTime(createTime);
        visitorUtil.operationLogMapper.insertSelective(operationLog);
    }

    /**
     * @Description 查询ip归属信息
     * @author CiaoLee
     */
    public static void ipAnalyse() {
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 从session中取出数据 */
        String id = (String)session.getAttribute("sessionLogId");
        String ip = (String)session.getAttribute("ip");
        /* 初始化参数 */
        boolean shallUpdate = true;
        SessionLog sessionLog = new SessionLog();
        sessionLog.setId(id);
        String province = "";
        String city = "";
        String isp = "";
        /* 从阿里云查询ip归属信息 */
        String path = "/ip";
        String method = "GET";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + ALIYUN_IP_APPCODE);
        Map<String, String> querys = new HashMap<>();
        querys.put("ip", ip);
        try {
            HttpResponse response = HttpUtil.doGet(ALIYUN_IP_SERVER_HOST, path, method, headers, querys);
            //获取response的body
            JSONObject jsonObj = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            JSONObject data = JSONObject.parseObject(jsonObj.get("data").toString());
            province = data.get("region").toString();
            city = data.get("city").toString();
            isp = data.get("isp").toString();
        } catch (Exception e) {
            shallUpdate = false;
            System.err.println("查询IP归属信息失败");
        }
        if(!shallUpdate) return;
        /* 修改SessionLog记录 */
        try {
            sessionLog.setProvince(province);
            sessionLog.setCity(city);
            sessionLog.setIsp(isp);
            visitorUtil.sessionLogMapper.updateByPrimaryKeySelective(sessionLog);
        } catch(Exception e) {
            System.err.println("修改IP归属信息失败");
        }
    }
}
