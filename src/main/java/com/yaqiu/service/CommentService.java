package com.yaqiu.service;

import com.yaqiu.entity.Comment;

import java.util.List;

public interface CommentService {
    void visitorCommentPublish(String contentId, String creatorNickname, String mainContent, String phoneNumber);

    List<Comment> getSpecifiedComments(String contentId);
}
