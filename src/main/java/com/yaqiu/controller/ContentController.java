package com.yaqiu.controller;

import com.yaqiu.entity.DomainColumn;
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
    @GetMapping("getCurrentPage")
    public Result getCurrentPage(String identifier, int pageIndex) {
        /* 从Redis中获取domainColumn的主键 */
        String columnId = "";
        if(redisUtil.hasKey("domain-columns-map")) {
            DomainColumn domainColumn = (DomainColumn)redisUtil.hget("domain-columns-map", identifier);
            columnId = domainColumn.getId();
        } else {
          return null;
        }
        /* 查询此栏目下 当前页的内容 */
        Map<String, Object> params = new HashMap();
        params.put("columnId", columnId);
        params.put("pageIndex", pageIndex);
        params.put("beginIndex", (pageIndex-1)*PAGE_SIZE);
        params.put("pageSize", PAGE_SIZE);
        List<Map> currentPage = contentService.getCurrentPage(params);
        return new Result(SUCCESS, currentPage);
    }
}
