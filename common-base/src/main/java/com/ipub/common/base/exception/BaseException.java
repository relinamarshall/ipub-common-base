package com.ipub.common.base.exception;

import lombok.Getter;

/**
 * BaseException
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Getter
public class BaseException extends RuntimeException {
    /**
     * 错误码
     */
    protected final ErrorCode error;

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     */
    public BaseException(ErrorCode errorCode) {
        super(errorCode.getInfo());
        this.error = errorCode;
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     * @param cause     异常
     */
    public BaseException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getInfo(), cause);
        this.error = errorCode;
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     * @param message   补充异常信息
     * @param cause     异常
     */
    public BaseException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode.fmtMsg(message), cause);
        this.error = errorCode;
    }

    /**
     * 构造函数
     *
     * @param message 补充异常信息
     */
    public BaseException(String message) {
        super(ErrorCode.SERVICE_INNER_ERROR.fmtMsg(message));
        this.error = ErrorCode.SERVICE_INNER_ERROR;
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     * @param message   补充异常信息
     */
    public BaseException(ErrorCode errorCode, String message) {
        super(errorCode.fmtMsg(message));
        this.error = errorCode;
    }

    /**
     * 构造函数
     *
     * @param message 补充异常信息
     * @param cause   异常
     */
    public BaseException(String message, Throwable cause) {
        super(ErrorCode.SERVICE_INNER_ERROR.fmtMsg(message), cause);
        this.error = ErrorCode.SERVICE_INNER_ERROR;
    }
}
