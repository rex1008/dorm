package com.dorm.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class PageUtil {

    public static <T> IPage<T> buildPage(Long pageIndex, Long pageSize) {
        return new Page<>(pageIndex, pageSize);
    }
}
