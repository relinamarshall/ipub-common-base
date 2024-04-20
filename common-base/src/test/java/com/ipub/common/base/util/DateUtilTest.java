package com.ipub.common.base.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * DateUtilTest
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
class DateUtilTest {
    @Test
    void toDate() {
        Date date = DateUtil.toDate(LocalDateTime.now());
        Assertions.assertNotNull(date);

        Date date2 = DateUtil.toDate(LocalDate.now());
        Assertions.assertNotNull(date2);
    }

    @Test
    public void belongCalendar() {
        long time = new Date().getTime();
        Assertions.assertFalse(DateUtil.between(new Date(time + 10000), new Date(time + 20000)));
        Assertions.assertFalse(DateUtil.between(new Date(time - 10000), new Date(time - 20000)));
        Assertions.assertTrue(DateUtil.between(new Date(time), new Date(time + 10000)));
    }
}
