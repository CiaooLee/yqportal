package com.yaqiu.mapper;

import com.yaqiu.entity.WebsiteColumn;
import com.yaqiu.entity.WebsiteColumnExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebsiteColumnMapper {
    int countByExample(WebsiteColumnExample example);

    int deleteByExample(WebsiteColumnExample example);

    int deleteByPrimaryKey(String id);

    int insert(WebsiteColumn record);

    int insertSelective(WebsiteColumn record);

    List<WebsiteColumn> selectByExample(WebsiteColumnExample example);

    WebsiteColumn selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WebsiteColumn record, @Param("example") WebsiteColumnExample example);

    int updateByExample(@Param("record") WebsiteColumn record, @Param("example") WebsiteColumnExample example);

    int updateByPrimaryKeySelective(WebsiteColumn record);

    int updateByPrimaryKey(WebsiteColumn record);
}