package com.yaqiu.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Comment {
    private String id;
    private String mainContent;
    private String contentId;
    private String creatorNickname;
    private String createTime;
    private String phoneNumber;
    private String profilePictureUrl;
}