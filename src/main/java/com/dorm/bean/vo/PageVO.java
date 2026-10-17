package com.dorm.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageVO<T> {

    private List<T> list;

    private Long total;

    public PageVO(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public static <T> PageVO<T> of(List<T> list, Long total) {
        return new PageVO<>(list, total);
    }
}
