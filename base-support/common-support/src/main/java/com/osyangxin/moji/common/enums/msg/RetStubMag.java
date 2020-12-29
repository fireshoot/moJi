package com.osyangxin.moji.common.enums.msg;

import com.osyangxin.moji.common.enums.BaseStub;

/**
 * @author yangxin
 * @类描述
 * @time 2020/12/29  17:02
 */
public enum RetStubMag implements BaseStub {
    /**
     * comment
     * */
    HASH_UTIL_ENCRYPT_FAILED (100201,"hash encrypt failed"),
    ;


    private int code;
    private String msg;

    RetStubMag(int code, String msg) {
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
