package com.yaqiu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {
    /**
     *@Description “案例”面板跳转映射
     *@author CiaoLee
     */
    @RequestMapping("case")
    public String toCase(ModelMap map) {
        map.addAttribute("identifier", "case");
        return "content/index.html";
    }

    /**
     *@Description “新闻”面板跳转映射
     *@author CiaoLee
     */
    @RequestMapping("news")
    public String toNews(ModelMap map) {
        map.addAttribute("identifier", "news");
        return "content/index.html";
    }

    /**
     *@Description “讨论区”面板跳转映射
     *@author CiaoLee
     */
    @RequestMapping("forum")
    public String toForum(ModelMap map) {
        map.addAttribute("identifier", "forum");
        return "content/index.html";
    }

    /**
     *@Description “帖子详情”跳转映射
     *@author CiaoLee
     */
    @RequestMapping("content")
    public String toDetail(String id) {
        return "content/show.html";
    }
}
