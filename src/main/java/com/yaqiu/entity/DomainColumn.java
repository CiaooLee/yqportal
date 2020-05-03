package com.yaqiu.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DomainColumn {
    private String id;
    private String name;
    private String identifier;
    private Byte sequenceNumber;
    private Boolean status;
}