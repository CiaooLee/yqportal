package com.yaqiu.service.impl;

import com.yaqiu.entity.OperationLog;
import com.yaqiu.mapper.OperationLogMapper;
import com.yaqiu.service.OperationLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OperationLogServiceImpl implements OperationLogService {
    @Resource
    private OperationLogMapper operationLogMapper;

    /**
     * @Description 获取今日的所有OperationLog
     * @author CiaoLee
     */
    @Override
    public List<OperationLog> getOperationLogsToday() {
        return operationLogMapper.getOperationLogsToday();
    }
}
