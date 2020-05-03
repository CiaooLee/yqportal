package com.yaqiu.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SessionLog {
    private String id;
    private String ip;
    private String deviceType;
    private String browserName;
    private String browserGroup;
    private String browserVersion;
    private String osName;
    private String osGroup;
    private String userId;
    private String createTime;
    private String province;
    private String city;
    private String isp;
}