package com.yaqiu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {
    /**
     *@Description foreground/index.html 前台-首页跳转映射
     *@author CiaoLee
     */
    @RequestMapping("/")
    public String toPortal() {
        return "foreground/index.html";
    }

    /**
     *@Description foreground/content/index.html 前台-各面板跳转映射
     *@author CiaoLee
     */
    @RequestMapping("domain")
    public String toCase(ModelMap map, String type) {
        map.addAttribute("identifier", type);
        return "foreground/content/index.html";
    }

    /**
     *@Description foreground/content/show.html 前台-“帖子详情”跳转映射
     *@author CiaoLee
     */
    @RequestMapping("content")
    public String toDetail(String id) {
        return "foreground/content/show.html";
    }
}
