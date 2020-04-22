package com.yaqiu.controller;

import com.yaqiu.pojo.Result;
import com.yaqiu.service.ContentService;
import com.yaqiu.util.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.yaqiu.constant.GlobalConstant.SUCCESS;

@RestController
@RequestMapping("content")
public class ContentController {
    @Resource
    private ContentService contentService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * @Description 获取所有的内容
     * @param identifier domain标识符
     * @author CiaoLee
     */
    @GetMapping("getList")
    public Result getList(String identifier) {
        /* 从Redis中获取domainColumn的主键 */
        String domainColumnId = "";
        if(redisUtil.hasKey("domain-columns-map")) {
            Map<String, String> domainColumn = (Map<String, String>)redisUtil.hget("domain-columns-map", identifier);
            domainColumnId = domainColumn.get("id");
        }
        /* 读取此栏目下的内容 */
        
        return new Result(SUCCESS, );
    }
}
