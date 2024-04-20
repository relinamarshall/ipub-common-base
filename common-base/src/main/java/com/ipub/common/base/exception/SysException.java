package com.ipub.common.base.exception;

import lombok.Getter;

/**
 * 系统级异常
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Getter
public class SysException extends BaseException {
    /**
     * 构造函数
     *
     * @param errorCode 错误码
     */
    public SysException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     * @param cause     异常
     */
    public SysException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getInfo(), cause);
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     * @param message   补充异常信息
     * @param cause     异常
     */
    public SysException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode.fmtMsg(message), cause);
    }

    /**
     * 构造函数
     *
     * @param message 补充异常信息
     */
    public SysException(String message) {
        super(ErrorCode.BIZ_ERROR.fmtMsg(message));
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码
     * @param message   补充异常信息
     */
    public SysException(ErrorCode errorCode, String message) {
        super(errorCode.fmtMsg(message));
    }

    /**
     * 构造函数
     *
     * @param message 补充异常信息
     * @param cause   异常
     */
    public SysException(String message, Throwable cause) {
        super(ErrorCode.BIZ_ERROR.fmtMsg(message), cause);
    }
}
