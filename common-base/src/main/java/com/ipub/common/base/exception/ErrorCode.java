package com.ipub.common.base.exception;

import com.ipub.common.base.util.AssertUtil;
import lombok.Getter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ipub.common.base.constant.RegExpr.SIX_DIGITS;

/**
 * 错误码
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Getter
public final class ErrorCode implements Serializable {
    /**
     * errorMap
     */
    private static final Map<String, ErrorCode> errorMap = new LinkedHashMap<>(256);
    /**
     * success
     */
    public static final ErrorCode OK = regError("000000", "success", 200);
    /**
     * 系统相关
     */
    public static final ErrorCode UNKNOWN = regError("001000", "未知异常", 500);
    public static final ErrorCode SERVICE_INNER_ERROR = regError("001001", "系统内部错误,请稍后再试", 500);
    /**
     * 业务相关
     */
    public static final ErrorCode BIZ_WARN = regError("002001", "%s");
    public static final ErrorCode BIZ_ERROR = regError("002002", "%s");
    public static final ErrorCode INVALID_INPUT_ARG = regError("002003", "无效的输入参数:%s");
    public static final ErrorCode TOO_MANY_ROWS = regError("002004", "返回记录数超过限制");
    public static final ErrorCode FAILED_QUERY_DATA = regError("002005", "数据查询异常");
    public static final ErrorCode NOT_FOUND = regError("002006", "未找到资源:%s", 404);
    /**
     * 用户相关
     */
    public static final ErrorCode INVALID_APP = regError("003000", "无效应用会话");
    public static final ErrorCode NOT_LOGIN = regError("003001", "用户未登录");
    public static final ErrorCode FORBID = regError("003002", "没有操作权限");
    /**
     * ES中间件相关
     */
    public static final ErrorCode RPC_ES_ERROR = regError("005000", "【ES】调用搜索服务异常");
    public static final ErrorCode ES_EXCEED_QPS_ERROR = regError("005001", "【ES】超过QPS限制");

    /**
     * 错误码
     */
    private final String code;
    /**
     * 错误信息
     */
    private final String info;
    /**
     * 响应状态
     */
    private final int httpCode;

    /**
     * 构造函数
     *
     * @param code     错误码
     * @param info     错误提示
     * @param httpCode 响应状态码
     */
    ErrorCode(String code, String info, int httpCode) {
        AssertUtil.assertTrue(null != code && code.matches(SIX_DIGITS), "错误码必须为6位数字");
        this.code = code;
        this.info = info;
        this.httpCode = httpCode;
    }

    /**
     * 注册错误码
     *
     * @param code    错误码
     * @param message 错误提示
     * @return ErrorCode
     */
    public static ErrorCode regError(String code, String message) {
        return regError(code, message, 200);
    }

    /**
     * 注册错误码
     *
     * @param code     错误码
     * @param message  错误提示
     * @param httpCode 响应状态码
     * @return ErrorCode
     */
    public static ErrorCode regError(String code, String message, int httpCode) {
        return new ErrorCode(code, message, httpCode).regToMap();
    }


    /**
     * 获得所有错误码表
     *
     * @return Collection<ErrorCode>
     */
    public static Collection<ErrorCode> getErrorList() {
        return Collections.unmodifiableCollection(errorMap.values());
    }

    /**
     * 查找错误码
     *
     * @param code errorCode
     * @return ErrorCode
     */
    public static ErrorCode codeOf(String code) {
        ErrorCode error = errorMap.get(code);
        AssertUtil.notNull(error, "未找到该错误码:" + code);
        return error;
    }

    /**
     * 格式化错误信息
     *
     * @param input input
     * @return String
     */
    public String fmtMsg(String input) {
        return this.info.contains("%s") ? String.format(this.info, input) : this.info + ":" + input;
    }

    /**
     * regToMap
     *
     * @return ErrorCode
     */
    private ErrorCode regToMap() {
        AssertUtil.assertTrue(!errorMap.containsKey(this.code), "请勿重复注册错误码:" + this.code);
        errorMap.put(this.code, this);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ErrorCode) {
            return this.code.equals(((ErrorCode) obj).code);
        }
        return false;
    }
}
