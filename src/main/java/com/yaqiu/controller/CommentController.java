package com.yaqiu.controller;

import com.yaqiu.entity.Comment;
import com.yaqiu.pojo.Result;
import com.yaqiu.service.CommentService;
import com.yaqiu.service.ContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yaqiu.constant.GlobalConstant.*;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @Resource
    private ContentService contentService;

    /**
     * @Description 游客发表评论
     * @param contentId 文章主键
     * @param creatorNickname 发布人自定义昵称
     * @param mainContent 评论内容
     * @param phoneNumber 手机（选填）
     * @author CiaoLee
     */
    @PostMapping("visitorCommentPublish")
    public Result visitorCommentPublish(String contentId, String creatorNickname, String mainContent, String phoneNumber) {
        /* 执行新增 */
        try {
            //新增评论
            commentService.visitorCommentPublish(contentId, creatorNickname, mainContent, phoneNumber);
            //文章评论数++
            Map<String, Object> params = new HashMap<>();
            params.put("id", contentId);
            contentService.contentCommentNumUp(params);
        } catch(Exception e) {
            System.err.println("游客评论错误，连接数据库失败");
            return new Result(ERROR, null);
        }
        return new Result(SUCCESS, null);
    }

    /**
     *@Description 获取文章的评论
     *@param contentId 文章主键
     *@author CiaoLee
     */
    @GetMapping("getSpecifiedComments")
    public Result getSpecifiedComments(String contentId) {
        /* 执行查询 */
        List<Comment> specifiedComments = null;
        try {
            specifiedComments = commentService.getSpecifiedComments(contentId);
        } catch(Exception e) {
            System.err.println("加载评论区失败");
            return new Result(ERROR, null);
        }
        return new Result(SUCCESS, specifiedComments);
    }
}
