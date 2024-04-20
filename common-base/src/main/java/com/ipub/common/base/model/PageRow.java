package com.ipub.common.base.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

/**
 * PageRow
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class PageRow<T> {
    /**
     * 当前页数据
     */
    private final Collection<T> list;
    /**
     * 总数
     */
    private final long total;
    /**
     * 页码
     */
    private final int num;
    /**
     * 页大小
     */
    private final int size;

    /**
     * build
     *
     * @param list     当前页数据
     * @param total    总数
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return PageRow<T>
     */
    public static <E> PageRow<E> build(Collection<E> list, long total, int pageNum, int pageSize) {
        return new PageRow<>(list, total, pageNum, pageSize);
    }
}
