package com.ipub.common.base.exception;

/**
 * 没有操作权限
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
public class ForbidException extends BizException {

    /**
     * 默认构造函数
     */
    public ForbidException() {
        super(ErrorCode.FORBID);
    }

    /**
     * 构造函数
     *
     * @param cause 异常
     */
    public ForbidException(Throwable cause) {
        super(ErrorCode.FORBID, cause);
    }

    /**
     * 构造函数
     *
     * @param message 补充异常信息
     */
    public ForbidException(String message) {
        super(ErrorCode.FORBID, message);
    }

    /**
     * 构造函数
     *
     * @param message 补充异常信息
     * @param cause   异常
     */
    public ForbidException(String message, Throwable cause) {
        super(ErrorCode.FORBID, message, cause);
    }
}
