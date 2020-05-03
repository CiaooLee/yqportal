package com.yaqiu.service.impl;

import com.yaqiu.entity.Content;
import com.yaqiu.entity.ContentExample;
import com.yaqiu.mapper.ContentMapper;
import com.yaqiu.service.ContentService;
import com.yaqiu.util.DateUtil;
import com.yaqiu.util.ObjectUtil;
import com.yaqiu.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.yaqiu.constant.GlobalConstant.ACTIVE_STATUS;
import static com.yaqiu.constant.GlobalConstant.ADMIN_USERNAME;

@Service
public class ContentServiceImpl implements ContentService {
    @Resource
    private ContentMapper contentMapper;

    /**
     * @Description 查询指定 页数&&栏目&&排序方式（最新） 的内容
     * @param params=> sortType 排序方式("new")
     * @param params=> identifier domain标识符
     * @param params=> pageIndex 指定页数
     * @author CiaoLee
     */
    @Override
    public List<Map> getSpecifiedPageOfLatest(Map<String, Object> params) {
       return contentMapper.getSpecifiedPageOfLatest(params);
    }

    /**
     * @Description 查询指定 页数&&栏目&&排序方式（最热） 的内容
     * @param params=> sortType 排序方式("hot")
     * @param params=> identifier domain标识符
     * @param params=> pageIndex 指定页数
     * @author CiaoLee
     */
    @Override
    public List<Map> getSpecifiedPageOfHottest(Map<String, Object> params) {
        return contentMapper.getSpecifiedPageOfHottest(params);
    }

    /**
     * @Description 获取指定栏目的Contents记录数
     * @param params=> identifier domain标识符
     * @author CiaoLee
     */
    @Override
    public int countSpecifiedContents(Map<String, Object> params) {
        /* 查询“全部”内容总数 */
        if(ObjectUtil.isEmpty(params.get("columnId"))) {
            return contentMapper.countByExample(null);
        }
        /* 按栏目查询内容总数 */
        ContentExample contentExample = new ContentExample();
        ContentExample.Criteria contentExampleCriteria = contentExample.createCriteria();
        contentExampleCriteria.andColumnIdEqualTo((String)params.get("columnId"));
        return contentMapper.countByExample(contentExample);
    }

    /**
     * @Description 获取指定内容
     * @param params=> id Content表主键
     * @author CiaoLee
     */
    @Override
    public Map<String, Object> getSpecifiedContent(Map<String, Object> params) {
        return contentMapper.getSpecifiedContent(params);
    }

    /**
     * @Description 管理员发布文章
     * @param title 标题
     * @param mainContent 内容
     * @param columnId 栏目Id
     * @param weight 权重
     * @author CiaoLee
     */
    @Override
    public void adminPublish(String title, String mainContent, String columnId, Byte weight) {
        /* 初始化Content对象 */
        Content content = new Content(UUIDUtil.getUUID(), title, null, null, null, ADMIN_USERNAME, null, DateUtil.getCurrentDateTime(), columnId, ACTIVE_STATUS, weight, mainContent);
        contentMapper.insert(content);
    }
}
