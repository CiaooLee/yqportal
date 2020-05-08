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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.yaqiu.constant.GlobalConstant.ALIYUN_IP_APPCODE;
import static com.yaqiu.constant.GlobalConstant.ALIYUN_IP_SERVER_HOST;

@Component
public class VisitorUtil {
    private final Logger logger = LoggerFactory.getLogger(VisitorUtil.class);

    @Resource
    private SessionLogMapper sessionLogMapper;

    @Resource
    private OperationLogMapper operationLogMapper;

    /**
     * @Description 新增[session_log]表记录
     * @author CiaoLee
     */
    public void generateSessionLog() {
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 从session中取出数据 */
        String id = (String)session.getAttribute("sessionLogId");
        String ip = (String)session.getAttribute("ip");
        /* 生成[session_log]新记录对象 */
        SessionLog sessionLog = new SessionLog();
        sessionLog.setId(id);
        sessionLog.setIp(ip);
        /* 分析游客UserAgent信息 */
        try {
            Map<String, String> visitorInfo = visitorInfoAnalyse();
            sessionLog.setDeviceType(visitorInfo.get("deviceType"));
            sessionLog.setBrowserName(visitorInfo.get("browserName"));
            sessionLog.setBrowserGroup(visitorInfo.get("browserGroup"));
            sessionLog.setBrowserVersion(visitorInfo.get("browserVersion"));
            sessionLog.setOsName(visitorInfo.get("osName"));
            sessionLog.setOsGroup(visitorInfo.get("osGroup"));
            sessionLog.setCreateTime(visitorInfo.get("createTime"));
        } catch(Exception e) {
            logger.error("分析IP["+ ip +"]的UserAgent信息失败 Compromise=>新增不完全[SessionLog访问记录]");
        }
        /* 分析游客IP归属信息 */
        try{
            Map<String, String> ipInfo = ipAnalyse();
            sessionLog.setProvince(ipInfo.get("province"));
            sessionLog.setCity(ipInfo.get("city"));
            sessionLog.setIsp(ipInfo.get("isp"));
        } catch(Exception e) {
            logger.error("分析IP["+ ip +"]归属信息失败 Compromise=>新增不完全[SessionLog访问记录]");
        }
        /* 执行新增 */
        try {
            sessionLogMapper.insertSelective(sessionLog);
        } catch(Exception e) {
            logger.error("数据库错误=>记录IP["+ ip +"]的[SessionLog访问记录]失败 此次会话未能生成[SessionLog访问记录]");
        }
    }

    /**
     * @Description 新增[session_log]表记录
     * @author CiaoLee
     */
    public void generateOperationLog() {
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 从session中取出数据 */
        try {
            OperationLog operationLog = new OperationLog();
            operationLog.setId(UUIDUtil.getUUID());
            operationLog.setSessionLogId((String)session.getAttribute("sessionLogId"));
            operationLog.setType((byte)session.getAttribute("operationLogType"));
            operationLog.setContent((String)session.getAttribute("operationLogContent"));
            operationLog.setCreateTime((String)session.getAttribute("operationLogCreateTime"));
            operationLogMapper.insertSelective(operationLog);
        } catch(Exception e) {
            logger.error("数据库错误=>记录IP["+ session.getAttribute("ip") +"]的[OperationLog操作记录]失败 操作内容: "+ session.getAttribute("operationLogContent"));
        }
    }

    /**
     * @Description 从User-Agent字符串中分析出访问者的信息
     * @author CiaoLee
     */
    public Map<String, String> visitorInfoAnalyse() {
        /* 初始化返回值 */
        Map<String, String> visitorInfo = new HashMap<>();
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 从session中取出并分析User-Agent对象 */
        //获取请求头的user-agent对象
        UserAgent userAgent = (UserAgent)session.getAttribute("userAgent");
        //获取浏览器对象
        Browser browser = userAgent.getBrowser();
        //获取操作系统对象
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
     * @Description 查询ip归属信息
     * @author CiaoLee
     */
    public Map<String, String> ipAnalyse() throws Exception {
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 从session中取出数据 */
        String ip = (String)session.getAttribute("ip");
        /* 初始化返回集 */
        Map<String, String> rtnMap = new HashMap<>();
        /* 从阿里云查询ip归属信息 */
        //初始化请求参数
        String path = "/ip";
        String method = "GET";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + ALIYUN_IP_APPCODE);
        Map<String, String> querys = new HashMap<>();
        querys.put("ip", ip);
        //发送请求
        HttpResponse response = HttpUtil.doGet(ALIYUN_IP_SERVER_HOST, path, method, headers, querys);
        //获取response的body
        JSONObject jsonObj = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
        JSONObject data = JSONObject.parseObject(jsonObj.get("data").toString());
        //获取省份
        rtnMap.put("province", (String)data.get("region"));
        //获取城市
        rtnMap.put("city", (String)data.get("city"));
        //获取网络运营商
        rtnMap.put("isp", (String)data.get("isp"));
        /* 返回参数 */
        return rtnMap;
    }
}
