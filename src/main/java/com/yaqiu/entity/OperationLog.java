package com.yaqiu.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OperationLog {
    private String id;
    private String sessionLogId;
    private Byte type;
    private String content;
    private String createTime;
}