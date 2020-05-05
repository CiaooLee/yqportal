package com.yaqiu.service.impl;

import com.yaqiu.entity.Comment;
import com.yaqiu.entity.CommentExample;
import com.yaqiu.mapper.CommentMapper;
import com.yaqiu.service.CommentService;
import com.yaqiu.util.DateUtil;
import com.yaqiu.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

import static com.yaqiu.constant.GlobalConstant.VISITOR_PROFILE_PICTURE_COUNT;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    /**
     * @Description 游客发表评论
     * @param contentId 文章主键
     * @param creatorNickname 发布人自定义昵称
     * @param mainContent 评论内容
     * @param phoneNumber 手机（选填）
     * @author CiaoLee
     */
    @Override
    public void visitorCommentPublish(String contentId, String creatorNickname, String mainContent, String phoneNumber) {
        /* 获取随机头像 */
        Random random = new Random();
        String profilePictureUrl = "visitor" + random.nextInt(VISITOR_PROFILE_PICTURE_COUNT);
        /* 执行新增 */
        Comment comment = new Comment(UUIDUtil.getUUID(), mainContent, contentId, creatorNickname, DateUtil.getCurrentDateTime(), phoneNumber, profilePictureUrl);
        commentMapper.insertSelective(comment);
    }

    /**
     *@Description 获取文章的评论
     *@param contentId 文章主键
     *@author CiaoLee
     */
    @Override
    public List<Comment> getSpecifiedComments(String contentId) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria commentExampleCriteria = commentExample.createCriteria();
        commentExampleCriteria.andContentIdEqualTo(contentId);
        return commentMapper.selectByExample(commentExample);
    }
}
