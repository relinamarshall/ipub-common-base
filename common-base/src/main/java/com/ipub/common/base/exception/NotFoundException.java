package com.ipub.common.base.exception;

/**
 * NotFoundException
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
public class NotFoundException extends SysException {
    /**
     * 默认构造函数
     */
    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }

    /**
     * 构造函数
     *
     * @param message 错误信息
     */
    public NotFoundException(String message) {
        super(ErrorCode.NOT_FOUND, message);
    }

    /**
     * 构造函数
     *
     * @param message 错误信息
     * @param cause   异常
     */
    public NotFoundException(String message, Throwable cause) {
        super(ErrorCode.NOT_FOUND, message, cause);
    }

    /**
     * 构造函数
     *
     * @param cause 异常
     */
    public NotFoundException(Throwable cause) {
        super(ErrorCode.NOT_FOUND, cause);
    }
}
