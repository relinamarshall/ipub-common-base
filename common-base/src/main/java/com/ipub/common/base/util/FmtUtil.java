package com.ipub.common.base.util;

import lombok.experimental.UtilityClass;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * FmtUtil
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@UtilityClass
public final class FmtUtil {
    /**
     * crtDtFmt
     *
     * @param pattern String
     * @return DateTimeFormatter
     */
    public static DateTimeFormatter crtDtFmt(String pattern) {
        return DateTimeFormatter.ofPattern(pattern, Locale.getDefault()).withZone(ZoneId.systemDefault());
    }
}
