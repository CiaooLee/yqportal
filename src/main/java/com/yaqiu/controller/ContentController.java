package com.yaqiu.controller;

import com.yaqiu.pojo.Result;
import com.yaqiu.service.ContentService;
import com.yaqiu.util.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yaqiu.constant.GlobalConstant.PAGE_SIZE;
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
    public Result getList(String identifier, int pageIndex) {
        /* 从Redis中获取domainColumn的主键 */
        String domainColumnId = "";
        if(redisUtil.hasKey("domain-columns-map")) {
            Map<String, String> domainColumn = (Map<String, String>)redisUtil.hget("domain-columns-map", identifier);
            domainColumnId = domainColumn.get("id");
        }
        /* 查询此栏目下 当前页的内容 */
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("")
        params.put("pageIndex", pageIndex);
        params.put("beginIndex", (pageIndex-1)*PAGE_SIZE);
        params.put("pageSize", PAGE_SIZE);
        return new Result(SUCCESS, );
    }
}
