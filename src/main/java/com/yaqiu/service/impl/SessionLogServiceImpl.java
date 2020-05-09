package com.yaqiu.service.impl;

import com.yaqiu.entity.SessionLog;
import com.yaqiu.mapper.SessionLogMapper;
import com.yaqiu.service.SessionLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SessionLogServiceImpl implements SessionLogService {
    @Resource
    private SessionLogMapper sessionLogMapper;

    /**
     * @Description 获取今日的所有SessionLog
     * @author CiaoLee
     */
    @Override
    public List<SessionLog> getSessionLogsToday() {
        return sessionLogMapper.getSessionLogsToday();
    }
}
