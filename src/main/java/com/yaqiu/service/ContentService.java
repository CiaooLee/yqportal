package com.yaqiu.service;

import java.util.List;
import java.util.Map;

public interface ContentService {
    List<Map> getSpecifiedPageOfLatest(Map<String, Object> params);

    List<Map> getSpecifiedPageOfHottest(Map<String, Object> params);

    int countSpecifiedContents(Map<String, Object> params);

    Map<String, Object> getSpecifiedContent(Map<String, Object> params);
}
