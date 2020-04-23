package com.yaqiu.service;

import java.util.List;
import java.util.Map;

public interface ContentService {
    List<Map> getCurrentPage(Map<String, Object> params);
}
