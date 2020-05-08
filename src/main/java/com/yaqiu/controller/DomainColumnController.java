package com.yaqiu.controller;

import com.yaqiu.entity.DomainColumn;
import com.yaqiu.pojo.Result;
import com.yaqiu.service.DomainColumnService;
import com.yaqiu.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

import static com.yaqiu.constant.GlobalConstant.ERROR;
import static com.yaqiu.constant.GlobalConstant.SUCCESS;

@RestController
@RequestMapping("domainColumn")
public class DomainColumnController {
    private final Logger logger = LoggerFactory.getLogger(DomainColumnController.class);

    @Resource
    private DomainColumnService domainColumnService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * @Description 获取DomainColumns
     * @author CiaoLee
     */
    @GetMapping("getActiveList")
    public Result getActiveList() {
        /* 查询Redis */
        if(redisUtil.hasKey("domain-columns") && redisUtil.hasKey("domain-columns-map"))
            return new Result(SUCCESS, redisUtil.get("domain-columns"));
        /* Compromise 查库 */
        List<DomainColumn> domainColumns;
        try {
            domainColumns = domainColumnService.getActiveList();
            redisUtil.set("domain-columns", domainColumns, 0);
            for(DomainColumn domainColumn : domainColumns) {
                redisUtil.hset("domain-columns-map", domainColumn.getIdentifier(), domainColumn, 0);
            }
        } catch (Exception e) {
            logger.error("获取所有板块栏目失败");
            return new Result(ERROR, null);
        }
        return new Result(SUCCESS, domainColumns);
    }
}
