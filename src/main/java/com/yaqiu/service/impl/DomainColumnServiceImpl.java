package com.yaqiu.service.impl;

import com.yaqiu.entity.DomainColumn;
import com.yaqiu.entity.DomainColumnExample;
import com.yaqiu.mapper.DomainColumnMapper;
import com.yaqiu.service.DomainColumnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DomainColumnServiceImpl implements DomainColumnService {
    @Resource
    private DomainColumnMapper domainColumnMapper;

    /**
     * @Description 获取DomainColumns
     * @author CiaoLee
     */
    @Override
    public List<DomainColumn> getActiveList() {
        DomainColumnExample domainColumnExample = new DomainColumnExample();
        DomainColumnExample.Criteria domainColumnExampleCriteria = domainColumnExample.createCriteria();
        domainColumnExampleCriteria.andStatusEqualTo(true);
        domainColumnExample.setOrderByClause("sequence_number asc");
        return domainColumnMapper.selectByExample(domainColumnExample);
    }
}
