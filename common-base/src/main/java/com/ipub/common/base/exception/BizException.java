package com.ipub.common.base.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Getter
public class BizException extends BaseException {
    /**
     * 构造函数
     *
     * @param errorCode 错误码
     */
    public BizException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     * @param cause     异常
     */
    public BizException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getInfo(), cause);
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     * @param message   补充异常信息
     * @param cause     异常
     */
    public BizException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode.fmtMsg(message), cause);
    }

    /**
     * 构造函数
     *
     * @param message 补充异常信息
     */
    public BizException(String message) {
        super(ErrorCode.BIZ_ERROR.fmtMsg(message));
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     * @param message   补充异常信息
     */
    public BizException(ErrorCode errorCode, String message) {
        super(errorCode.fmtMsg(message));
    }

    /**
     * 构造函数
     *
     * @param message 补充异常信息
     * @param cause   异常
     */
    public BizException(String message, Throwable cause) {
        super(ErrorCode.BIZ_ERROR.fmtMsg(message), cause);
    }
}