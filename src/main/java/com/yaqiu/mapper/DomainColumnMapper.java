package com.yaqiu.mapper;

import com.yaqiu.entity.DomainColumn;
import com.yaqiu.entity.DomainColumnExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DomainColumnMapper {
    int countByExample(DomainColumnExample example);

    int deleteByExample(DomainColumnExample example);

    int deleteByPrimaryKey(String id);

    int insert(DomainColumn record);

    int insertSelective(DomainColumn record);

    List<DomainColumn> selectByExample(DomainColumnExample example);

    DomainColumn selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DomainColumn record, @Param("example") DomainColumnExample example);

    int updateByExample(@Param("record") DomainColumn record, @Param("example") DomainColumnExample example);

    int updateByPrimaryKeySelective(DomainColumn record);

    int updateByPrimaryKey(DomainColumn record);
}