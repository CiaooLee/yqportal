package com.yaqiu.controller;

import com.yaqiu.pojo.Result;
import com.yaqiu.service.ContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("content")
public class ContentController {
    @Resource
    private ContentService contentService;

    /**
     * @Description 获取所有的内容
     * @param
     * @author CiaoLee
     */
    /*@GetMapping("getList")
    public Result getList();*/
}
