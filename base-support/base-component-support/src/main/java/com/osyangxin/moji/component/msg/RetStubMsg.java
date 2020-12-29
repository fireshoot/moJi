package com.osyangxin.moji.component.msg;


import com.osyangxin.moji.common.enums.BaseStub;

/**
 * 方法实现说明
 * @
 * @author      yangxin
 * @date        2020/12/29 21:15
*/
public enum RetStubMsg implements BaseStub {

    /**
     *
     * */
    FILE_PATH_ILLEGAL(100100, "file path illegal"),
    FILE_IS_EMPTY(100102, "file is empty"),
    FILE_FORMAT_NOT_SUPPORT(100103, "file format not support"),

    SMS_CLIENT_INIT_ERR(100200, "sms client init error"),
    CAPTCHA_SEND_ERR(100201, "captcha failed to send."),
    ;


    private int code;
    private String msg;

    RetStubMsg(int code, String msg) {
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
