package com.dorm.bean.req;

import lombok.Data;

@Data
public class BasePageReq {

    private Long pageIndex = 1L;

    private Long pageSize = 10L;

    public Long getStart() {
        return (pageIndex - 1) * pageSize;
    }
}
