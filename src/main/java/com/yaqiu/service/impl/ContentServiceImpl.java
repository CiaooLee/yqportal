package com.yaqiu.service.impl;

import com.yaqiu.entity.ContentExample;
import com.yaqiu.mapper.ContentMapper;
import com.yaqiu.service.ContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {
    @Resource
    private ContentMapper contentMapper;

    /**
     * @Description 查询指定 页数&&栏目&&排序方式（最新） 的内容
     * @param params=> sortType 排序方式("new")
     * @param params=> identifier domain标识符
     * @param params=> pageIndex 指定页数
     * @author CiaoLee
     */
    @Override
    public List<Map> getSpecifiedPageOfLatest(Map<String, Object> params) {
       return contentMapper.getSpecifiedPageOfLatest(params);
    }

    /**
     * @Description 查询指定 页数&&栏目&&排序方式（最热） 的内容
     * @param params=> sortType 排序方式("hot")
     * @param params=> identifier domain标识符
     * @param params=> pageIndex 指定页数
     * @author CiaoLee
     */
    @Override
    public List<Map> getSpecifiedPageOfHottest(Map<String, Object> params) {
        return contentMapper.getSpecifiedPageOfHottest(params);
    }
}
