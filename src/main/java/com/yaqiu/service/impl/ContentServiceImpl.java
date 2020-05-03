package com.yaqiu.service.impl;

import com.yaqiu.entity.ContentExample;
import com.yaqiu.mapper.ContentMapper;
import com.yaqiu.service.ContentService;
import com.yaqiu.util.ObjectUtil;
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

    /**
     * @Description 获取指定栏目的Contents记录数
     * @param params=> identifier domain标识符
     * @author CiaoLee
     */
    @Override
    public int countSpecifiedContents(Map<String, Object> params) {
        /* 查询“全部”内容总数 */
        if(ObjectUtil.isEmpty(params.get("columnId"))) {
            return contentMapper.countByExample(null);
        }
        /* 按栏目查询内容总数 */
        ContentExample contentExample = new ContentExample();
        ContentExample.Criteria contentExampleCriteria = contentExample.createCriteria();
        contentExampleCriteria.andColumnIdEqualTo((String)params.get("columnId"));
        return contentMapper.countByExample(contentExample);
    }

    /**
     * @Description 获取指定内容
     * @param params=> id Content表主键
     * @author CiaoLee
     */
    @Override
    public Map<String, Object> getSpecifiedContent(Map<String, Object> params) {
        return contentMapper.getSpecifiedContent(params);
    }
}
