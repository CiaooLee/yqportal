package com.yaqiu.controller;

import com.yaqiu.util.DateTimeUtil;
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
        map.addAttribute("domain", "case");
        return "content/index.html";
    }

    /**
     *@Description “新闻”面板跳转映射
     *@author CiaoLee
     */
    @RequestMapping("news")
    public String toNews(ModelMap map) {
        map.addAttribute("domain", "news");
        return "content/index.html";
    }

    /**
     *@Description “文章”面板跳转映射
     *@author CiaoLee
     */
    @RequestMapping("detail")
    public String toDetail(String type, String id) {
        return "content/show.html";
    }
}
