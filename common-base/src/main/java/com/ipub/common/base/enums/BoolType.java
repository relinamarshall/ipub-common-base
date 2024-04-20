package com.ipub.common.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * BoolType
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Getter
@AllArgsConstructor
public enum BoolType {
    /**
     * 否
     */
    NO("0"),
    /**
     * 是
     */
    YES("1"),
    ;

    /**
     * 类型
     */
    private final String type;

    /**
     * 解析
     *
     * @param type 类型
     * @return BooleanType
     */
    public static BoolType of(String type) {
        for (BoolType item : values()) {
            if (item.type.equals(type)) {
                return item;
            }
        }
        return null;
    }
}
