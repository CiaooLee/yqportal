package com.yaqiu.service;

import com.yaqiu.entity.OperationLog;

import java.util.List;

public interface OperationLogService {
    List<OperationLog> getOperationLogsToday();
}
