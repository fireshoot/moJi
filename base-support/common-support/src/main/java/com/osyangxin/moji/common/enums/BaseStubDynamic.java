package com.osyangxin.moji.common.enums;

/**
 * 方法实现说明 ： 用于分组校验
 * @author      yangxin
 * @date        2020/12/29 16:56
*/
public class BaseStubDynamic implements BaseStub {

    private final int code;
    private final String msg;

    public BaseStubDynamic(int code, String msg) {
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
