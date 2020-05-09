package com.yaqiu.service;

import com.yaqiu.entity.SessionLog;

import java.util.List;

public interface SessionLogService {
    List<SessionLog> getSessionLogsToday();
}
