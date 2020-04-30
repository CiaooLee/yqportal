package com.yaqiu.controller;

import com.yaqiu.entity.DomainColumn;
import com.yaqiu.pojo.Page;
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

import static com.yaqiu.constant.GlobalConstant.*;

@RestController
@RequestMapping("content")
public class ContentController {
    @Resource
    private ContentService contentService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * @Description 获取所有的内容
     * @param sortType 排序方式
     * @param identifier domain标识符
     * @param pageIndex 指定页数
     * @author CiaoLee
     */
    @GetMapping("getSpecifiedPage")
    public Result getSpecifiedPage(String sortType, String identifier, int pageIndex) {
        /* 从Redis中获取domainColumn的主键 */
        String columnId = "";
        if(!"all".equals(identifier) && redisUtil.hasKey("domain-columns-map")) {
            DomainColumn domainColumn = (DomainColumn)redisUtil.hget("domain-columns-map", identifier);
            columnId = domainColumn.getId();
        }
        /* 查询此栏目下 当前页的内容 */
        Map<String, Object> params = new HashMap();
        if(!"all".equals(identifier)) params.put("columnId", columnId);
        params.put("pageIndex", pageIndex);
        params.put("beginIndex", (pageIndex-1)*PAGE_SIZE);
        params.put("pageSize", PAGE_SIZE);
        //查询数据库
        List<Map> currentPage = null;
        try {
            //排序方式：最新
            if("new".equals(sortType)) currentPage = contentService.getSpecifiedPageOfLatest(params);
            //排序方式：最热
            if("hot".equals(sortType)) currentPage = contentService.getSpecifiedPageOfHottest(params);
        } catch(Exception e) {
            System.err.println("查询指定页内容 连接数据库失败");
            return new Result(ERROR, null);
        }
        return new Result(SUCCESS, currentPage);
    }

    /**
     * @Description 获取指定栏目的 分页参数
     * @param identifier domain标识符
     * @author CiaoLee
     */
    @GetMapping("getSpecifiedPagination")
    public Result getSpecifiedPagination(String identifier) {
        /* 从Redis中获取domainColumn的主键 */
        String columnId = "";
        if(!"all".equals(identifier) && redisUtil.hasKey("domain-columns-map")) {
            DomainColumn domainColumn = (DomainColumn)redisUtil.hget("domain-columns-map", identifier);
            columnId = domainColumn.getId();
        }
        /* 查询此栏目下内容的 分页参数 */
        Map<String, Object> params = new HashMap();
        if(!"all".equals(identifier)) params.put("columnId", columnId);
        //查询数据库
        Page page = null;
        try {
            int specifiedContentsCount = contentService.countSpecifiedContents(params);
            int specifiedPageCount = specifiedContentsCount%PAGE_SIZE==0? specifiedContentsCount/PAGE_SIZE: specifiedContentsCount/PAGE_SIZE+1;
            page = new Page(specifiedPageCount);
        } catch(Exception e) {
            System.err.println("查询指定栏目的分页参数 连接数据库失败");
            return new Result(ERROR, null);
        }
        return new Result(SUCCESS, page);
    }
}
