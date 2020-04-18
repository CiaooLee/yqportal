package com.yaqiu.controller;

import com.yaqiu.constant.GlobalConstant;
import com.yaqiu.util.DateUtil;
import com.yaqiu.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ThymeleafController {
    /**
     * @Description foreground/index.html 前台-首页跳转映射
     * @author CiaoLee
     */
    @RequestMapping("/")
    public String toPortal() {
        /* 获取session */
        HttpSession session = SessionUtil.get();
        /* 初始化日志信息 */
        /* 从session中获取deviceType */
        Map<String, String> visitorInfo = (Map<String, String>) session.getAttribute("visitorInfo");
        String deviceType = visitorInfo.get("deviceType");
        /* 如果访问者使用手机 则跳至手机主页 */
        //if("Mobile".equals(deviceType)) return "foreground/mobile/index.html";
        /* 将[OPERATION_LOG]日志信息存入session 交给[VisitorLogInterceptor]的afterComplement处理 */
        session.setAttribute("operationLogType", GlobalConstant.PAGE_VISIT_OPERATION_LOG_TYPE);
        session.setAttribute("operationLogContent", "访问了[首页]");
        session.setAttribute("operationCreateTime", DateUtil.getCurrentDateTime());
        /* 如果访问者使用电脑 或不明类型设备 则跳至通用界面 */
        return "foreground/general/index.html";
    }

    /**
     * @Description foreground/content/index.html 前台-各面板跳转映射
     * @author CiaoLee
     */
    @RequestMapping("domain")
    public String toCase(ModelMap map, String type) {
        map.addAttribute("identifier", type);
        return "foreground/general/content/index.html";
    }

    /**
     * @Description foreground/content/show.html 前台-“帖子详情”跳转映射
     *@ author CiaoLee
     */
    @RequestMapping("content")
    public String toDetail(String id) {
        return "foreground/general/content/show.html";
    }
}
