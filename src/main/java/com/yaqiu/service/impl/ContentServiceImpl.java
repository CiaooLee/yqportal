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
     * @Description 获取所有的内容
     * @param domainColumnId
     * @author CiaoLee
     */
    @Override
    public List<Map> getList(String domainColumnId) {
        ContentExample contentExample = new ContentExample();
        ContentExample.Criteria contentExampleCriteria = contentExample.createCriteria();
        contentExampleCriteria.and
        return contentMapper.selectByExample();
    }
}
