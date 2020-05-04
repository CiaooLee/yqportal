package com.yaqiu.controller;

import com.yaqiu.entity.DomainColumn;
import com.yaqiu.pojo.Page;
import com.yaqiu.pojo.Result;
import com.yaqiu.service.ContentService;
import com.yaqiu.util.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
     * @Description 获取指定内容
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
        Map<String, Object> params = new HashMap<>();
        if(!"all".equals(identifier)) params.put("columnId", columnId);
        params.put("pageIndex", pageIndex);
        params.put("beginIndex", (pageIndex-1)*PAGE_SIZE);
        params.put("pageSize", PAGE_SIZE);
        params.put("status", ACTIVE_STATUS);
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
        Map<String, Object> params = new HashMap<>();
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

    /**
     * @Description 获取指定内容
     * @param id ContentId
     * @author CiaoLee
     */
    @GetMapping("getSpecifiedContent")
    public Result getSpecifiedContent(String id) {
        /* 初始化参数/返回集 */
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> specifiedContent = null;
        params.put("id", id);
        try {
            specifiedContent = contentService.getSpecifiedContent(params);
        } catch(Exception e) {
            System.err.println("查询Content：'"+id+"'连接数据库失败");
            return new Result(ERROR, null);
        }
        return new Result(SUCCESS, specifiedContent);
    }

    /**
     * @Description 管理员发布文章
     * @param title 标题
     * @param mainContent 内容
     * @param columnId 栏目Id
     * @param weight 权重
     * @author CiaoLee
     */
    @PostMapping("adminPublish")
    public Result adminPublish(String title, String mainContent, String columnId, Byte weight) {
        /* 处理mainContent=> 将制表符转为HTML代码 */
        mainContent = mainContent.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        mainContent = mainContent.replaceAll("\n", "<br/>");
        mainContent = mainContent.replaceAll(" ", "&nbsp;");
        /* 执行新增 */
        try {
            contentService.adminPublish(title, mainContent, columnId, weight);
        } catch(Exception e) {
            System.err.println("管理员新增Content错误，连接数据库失败");
            return new Result(ERROR, null);
        }
        return new Result(SUCCESS, null);
    }

    /**
     * @Description 获取首页内容-各版块TopX
     * @author CiaoLee
     */
    @GetMapping("portalContentOnload")
    public Result portalContentOnload() {
        /* 初始化参数 */
        Map<String, Object> params = new HashMap<>();
        params.put("status", ACTIVE_STATUS);
        /* 初始化结果集 */
        Map<String, Object> rtnMap = new HashMap<>();
        try{
            /* 查询“经典案例”板块Top9 */
            rtnMap.put("case", contentService.getCaseTopNine(params));
            /* 查询”实时信息”板块Top4 */
            rtnMap.put("news", contentService.getNewsTopFour(params));
            /* 查询“讨论交流”板块Top4 */
            rtnMap.put("forum", contentService.getForumTopNine(params));
        } catch(Exception e) {
            System.err.println("获取首页内容错误，连接数据库失败");
            return new Result(ERROR, null);
        }
        return new Result(SUCCESS, rtnMap);
    }

    /**
     * @Description 文章点击量增加
     * @param contentId Content表主键
     * @author CiaoLee
     */
    @PostMapping("contentHitsUp")
    public void contentHitsUp(String contentId) {
        /* 初始化参数 */
        Map<String, Object> params = new HashMap<>();
        params.put("id", contentId);
        /* 执行修改 */
        try{
            contentService.contentHitsUp(params);
        } catch(Exception e) {
            System.err.println("文章点击量增加错误，连接数据库失败");
        }
    }
}
