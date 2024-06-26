package com.ipub.common.base.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * AssertUtil
 *
 * @author wen.zhou
 * @since 2024/4/19
 */
public final class AssertUtil {
    /**
     * 私有构造
     */
    private AssertUtil() {
        throw new IllegalStateException("Constant class cannot be instantiated");
    }

    /**
     * assertTrue
     *
     * @param condition boolean
     * @param message   String
     */
    public static void assertTrue(boolean condition, String message) {
        checkTrue(condition, () -> new IllegalArgumentException(message));
    }

    /**
     * notNull
     *
     * @param object  Object
     * @param message String
     */
    public static void notNull(Object object, String message) {
        checkTrue(null != object, () -> new IllegalArgumentException(message));
    }

    /**
     * notBlank
     *
     * @param input   String
     * @param message String
     */
    public static void notBlank(String input, String message) {
        checkTrue(StringUtils.isNotBlank(input), () -> new IllegalArgumentException(message));
    }

    /**
     * notEmpty
     *
     * @param input   String
     * @param message String
     */
    public static void notEmpty(String input, String message) {
        checkTrue(StringUtils.isNotEmpty(input), () -> new IllegalArgumentException(message));
    }

    /**
     * notEmptyList
     *
     * @param list    List
     * @param message String
     */
    public static void notEmptyColl(Collection<?> list, String message) {
        checkTrue(!(null == list || list.isEmpty()), () -> new IllegalArgumentException(message));
    }

    /**
     * notBlank
     *
     * @param check    boolean
     * @param supplier Supplier
     */
    public static void checkTrue(boolean check, Supplier<? extends RuntimeException> supplier) {
        if (!check) throw supplier.get();
    }
}
