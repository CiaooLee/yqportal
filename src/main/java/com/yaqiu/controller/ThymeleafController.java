package com.yaqiu.controller;

import com.yaqiu.util.SessionUtil;
import com.yaqiu.util.VisitorUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ThymeleafController {
    /**
     *@Description foreground/index.html 前台-首页跳转映射
     *@author CiaoLee
     */
    @RequestMapping("/")
    public String toPortal(HttpServletRequest request) {
        //获取端类型(MOBILE/COMPUTER)
        //String deviceType = .toString();
        //获取操作系统家族(ANDROID/IOS/WINDOWS/MAC_OS_X)
        //String group = .toString();
        //VisitorUtil.analyseVisitorInfo();
        /* 如果当前用户使用手机 则跳至手机主页 */
        /* 如果当前用户使用PC 则跳至PC端主页 */
        //if("COMPUTER".equals(deviceType)) return "foreground/general/index.html";
        /* 如果不能识别端类型 则跳至通用界面 */
        HttpSession session = SessionUtil.get();
        String id = session.getAttribute("id").toString();
        //System.out.println(id);

        return "foreground/general/index.html";
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
