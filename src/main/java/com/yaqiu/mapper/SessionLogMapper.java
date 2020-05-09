package com.yaqiu.mapper;

import com.yaqiu.entity.SessionLog;
import com.yaqiu.entity.SessionLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SessionLogMapper {
    int countByExample(SessionLogExample example);

    int deleteByExample(SessionLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(SessionLog record);

    int insertSelective(SessionLog record);

    List<SessionLog> selectByExample(SessionLogExample example);

    SessionLog selectByPrimaryKey(String id);

    List<SessionLog> getSessionLogsToday();

    int updateByExampleSelective(@Param("record") SessionLog record, @Param("example") SessionLogExample example);

    int updateByExample(@Param("record") SessionLog record, @Param("example") SessionLogExample example);

    int updateByPrimaryKeySelective(SessionLog record);

    int updateByPrimaryKey(SessionLog record);
}