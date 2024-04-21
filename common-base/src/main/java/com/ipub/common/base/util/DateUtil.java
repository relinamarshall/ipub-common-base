package com.ipub.common.base.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * DateUtil
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@UtilityClass
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {
    /**
     * 将LocalDate转换为Date
     *
     * @param localDate LocalDate
     * @return Date
     */
    public static Date toDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        return Date.from(localDate.atStartOfDay().atZone(zoneId).toInstant());
    }

    /**
     * 将LocalDateTime转换为Date
     *
     * @param localDateTime LocalDateTime
     * @return Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }

    /**
     * 判断当前时间是否在时间段内
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return boolean
     **/
    public static boolean between(Date startTime, Date endTime) {
        Date nowTime = new Date();
        return nowTime.getTime() >= startTime.getTime() && nowTime.getTime() <= endTime.getTime();
    }
}
