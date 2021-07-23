package com.mutool.commonweb.enums;

import com.mutool.commonweb.exception.BaseError;


/**
 * <p>
 * 错误码
 * </p>
 *
 * @author jobob
 * @since 2018-09-23
 */
public enum ErrorCodeEnum implements BaseError {

    NOT_LOGIN("-2", "未登录"),
    ID_REQUIRED("100", "主键 ID 必须存在"),
    ID_NOT_FOUND("101", "主键 ID 数据不存在");

    /**
     * 错误码
     */
    private final String errorCode;
    /**
     * 错误描述
     */
    private final String errorMsg;

    ErrorCodeEnum(final String errorCode, final String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static BaseError getByCode(String code) {
        ErrorCodeEnum[] ecs = ErrorCodeEnum.values();
        for (ErrorCodeEnum ec : ecs) {
            if (ec.getErrorCode() == code) {
                return ec;
            }
        }
        return null;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}