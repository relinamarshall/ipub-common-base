package com.ipub.common.base.util;

import com.ipub.common.base.exception.BizException;
import com.ipub.common.base.exception.ErrorCode;
import com.ipub.common.base.exception.NotLoginException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * AssertUtilTest
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
class AssertUtilTest {
    @Test
    void notEmpty() {
        AssertUtil.notEmpty("123", "");

        try {
            AssertUtil.notEmpty(null, "notEmpty");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("notEmpty", e.getMessage());
        }
    }

    @Test
    void notEmptyColl() {
        AssertUtil.notEmptyColl(List.of("notEmpty"), "");
        try {
            AssertUtil.notEmptyColl(null, "notEmpty");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("notEmpty", e.getMessage());
        }
        try {
            AssertUtil.notEmptyColl(List.of(), "notEmpty");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("notEmpty", e.getMessage());
        }
    }


    @Test
    void notBlank() {
        AssertUtil.notBlank("123", "");

        try {
            AssertUtil.notBlank(" ", "notBlank");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("notBlank", e.getMessage());
        }
    }

    @Test
    void notNull() {
        AssertUtil.notNull(new Object(), "");

        try {
            AssertUtil.notNull(null, "notNull");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("notNull", e.getMessage());
        }
    }

    @Test
    void asserTrue() {
        AssertUtil.assertTrue(true, "");

        try {
            AssertUtil.assertTrue(false, "assertTrue");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("assertTrue", e.getMessage());
        }
    }

    @Test
    void checkTrue() {
        AssertUtil.checkTrue(true, NotLoginException::new);
        try {
            AssertUtil.checkTrue(false, NotLoginException::new);
        } catch (BizException e) {
            Assertions.assertEquals(ErrorCode.NOT_LOGIN, e.getError());
        }
    }
}
