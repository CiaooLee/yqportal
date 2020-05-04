package com.yaqiu.service;

import java.util.List;
import java.util.Map;

public interface ContentService {
    List<Map> getSpecifiedPageOfLatest(Map<String, Object> params);

    List<Map> getSpecifiedPageOfHottest(Map<String, Object> params);

    int countSpecifiedContents(Map<String, Object> params);

    Map<String, Object> getSpecifiedContent(Map<String, Object> params);

    void adminPublish(String title, String mainContent, String columnId, Byte weight);

    List<Map> getCaseTopNine(Map<String, Object> params);

    List<Map> getNewsTopFour(Map<String, Object> params);

    List<Map> getForumTopNine(Map<String, Object> params);
}
