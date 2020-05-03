package com.yaqiu.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Content {
    private String id;
    private String title;
    private Integer hitsShow;
    private Integer hitsReal;
    private Integer commentNum;
    private String creatorId;
    private String creatorName;
    private String createTime;
    private String columnId;
    private Byte status;
    private Byte weight;
    private String mainContent;
}