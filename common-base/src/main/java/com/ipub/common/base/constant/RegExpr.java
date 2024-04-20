package com.ipub.common.base.constant;

/**
 * 正则表达式
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
public final class RegExpr {
    /**
     * 六位数字
     */
    public static final String SIX_DIGITS = "^\\d{6}";

    /**
     * 私有构造
     */
    private RegExpr() {
        throw new IllegalStateException("Constant class cannot be instantiated");
    }
}
