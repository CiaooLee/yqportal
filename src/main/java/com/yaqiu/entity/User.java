package com.yaqiu.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String id;
    private String username;
    private String password;
    private String nickname;
    private String profilePictureUrl;
}