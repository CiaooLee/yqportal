package com.yaqiu.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Page {
    private String identifier;
    private String domainColumnId;
    private long contentCount;
    private long pageCount;
    private int pageIndex;
    private int pageSize;
}
