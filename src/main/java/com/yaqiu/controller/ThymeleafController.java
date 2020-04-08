package com.yaqiu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {
    @RequestMapping("article")
    public String toArticle(ModelMap map) {
        map.addAttribute("domain", "article");
        return "content/index.html";
    }
}
