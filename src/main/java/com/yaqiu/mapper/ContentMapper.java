package com.yaqiu.mapper;

import com.yaqiu.entity.Content;
import com.yaqiu.entity.ContentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ContentMapper {
    int countByExample(ContentExample example);

    int deleteByExample(ContentExample example);

    int deleteByPrimaryKey(String id);

    int insert(Content record);

    int insertSelective(Content record);

    List<Content> selectByExampleWithBLOBs(ContentExample example);

    List<Content> selectByExample(ContentExample example);

    Content selectByPrimaryKey(String id);

    List<Map> getSpecifiedPageOfLatest(Map<String, Object> params);

    List<Map> getSpecifiedPageOfHottest(Map<String, Object> params);

    int updateByExampleSelective(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByExampleWithBLOBs(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByExample(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKeyWithBLOBs(Content record);

    int updateByPrimaryKey(Content record);
}