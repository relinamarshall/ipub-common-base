package com.ipub.common.base.constant;

/**
 * DateFmt
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
public final class DateFmt {
    /**
     * 一秒的毫秒数
     */
    public static final long MS_OF_SECOND = 1000L;
    /**
     * 一分钟的毫秒数
     */
    public static final long MS_OF_MINUTE = MS_OF_SECOND * 60;
    /**
     * 一小时的毫秒数
     */
    public static final long MS_OF_HOUR = MS_OF_MINUTE * 60;
    /**
     * 一天的毫秒数
     */
    public static final long MS_OF_DAY = MS_OF_HOUR * 24;
    /**
     * 一天的秒数
     */
    public static final long SEC_OF_DAY = 24 * 3600L;
    /**
     * 时:分:秒
     */
    public static final String TM = "HH:mm:ss";
    /**
     * 年-月-日
     */
    public static final String DT = "yyyy-MM-dd";
    /**
     * 年-月-日 时:分:秒
     */
    public static final String DT_TM = "yyyy-MM-dd HH:mm:ss";
    /**
     * 年月日
     */
    public static final String DT_S = "yyyyMMdd";
    /**
     * 年月日时分秒
     */
    public static final String DT_TM_S = "yyyyMMddHHmmss";
    /**
     * 年月日时分秒毫秒
     */
    public static final String DT_TM_MS_S = "yyyyMMddHHmmssSSS";
}
