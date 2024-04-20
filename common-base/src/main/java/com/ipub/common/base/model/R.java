package com.ipub.common.base.model;

import com.ipub.common.base.exception.BaseException;
import com.ipub.common.base.exception.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * R
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Getter
public final class R<T> implements Serializable {
    /**
     * 操作成功的空数据
     */
    static final R<?> OK_EMPTY = success(null);
    /**
     * 代码
     */
    private final String code;
    /**
     * 信息
     */
    private final String info;
    /**
     * 数据
     */
    private final T data;
    /**
     * 服务名
     */
    @Setter
    private String svc;

    R(T data, String code, String info) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    /**
     * 成功结果
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return RestResult
     */
    public static <T> R<T> success(T data) {
        return R.buildR(data, ErrorCode.OK.getCode(), ErrorCode.OK.getInfo());
    }

    /**
     * 成功结果
     *
     * @return RestResult
     */
    public static R<?> success() {
        return R.OK_EMPTY;
    }

    /**
     * 失败结果
     *
     * @param exception 错误异常
     */
    public static R<?> fail(BaseException exception) {
        return fail(exception.getError());
    }

    /**
     * 失败结果
     *
     * @param error 错误信息
     */
    public static R<?> fail(ErrorCode error) {
        return R.buildR(null, error.getCode(), error.getInfo());
    }

    /**
     * 失败结果
     *
     * @param code 错误码
     * @param msg  错误信息
     * @return RestResult
     */
    public static R<?> fail(ErrorCode code, String msg) {
        return R.buildR(null, code.getCode(), code.fmtMsg(msg));
    }

    /**
     * 构建结果
     *
     * @param data    数据
     * @param code    返回码
     * @param message 返回信息
     * @param <T>     数据类型
     * @return 结果
     */
    static <T> R<T> buildR(T data, String code, String message) {
        return new R<>(data, code, message);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof R) {
            return this.code.equals(((R) obj).code);
        }
        return false;
    }
}
