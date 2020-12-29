package com.osyangxin.moji.common.enums;

public enum SysErrorCodeEnum implements BaseStub {

    /**
     * comment
     * */
    DEFAULT_SUCCESS(200, "success"),
    PARAMETER_IS_NULL(400, "parameter is null "),
    PARAMETER_TYPE_INVALID(401, "parameter type error"),
    REQUEST_BODY_INVALID(402, "request body invalid"),
    METHOD_INVALID(403, "unsupported method"),
    RESOURCE_INVALID(404, "source not exist"),
    CONTENT_TYPE_INVALID(405, "Content-Type invalid"),
    NEED_LOGIN(406, "need login"),
    API_AUTH_FAIL(407, "API auth fail"),
    API_REQUEST_ERROR(408, "API request error"),
    API_APP_ID_BLANK(409, "appId is blank"),
    API_SIGN_BLANK(410, "sign is blank"),
    API_APP_ID_ILLEGALITY(411, "appId is illegality"),
    API_REQUEST_CONTENT_TYPE_ERROR(412, "API request content-type error"),
    DEFAULT_FAIL(500, "base is busy");
    private int code;
    private String msg;

    SysErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
