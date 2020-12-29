package com.osyangxin.moji.common.constants;

/**
 * 方法实现说明
 * websocket 错误信息枚举
 * @author      yangxin
 * @date        2020/12/29 16:55
*/
public enum WsCommand {

    /**
     * comment
     * */
    RESPONSE_LINK_ERROR_TOKEN_INVALID(-1, "auth fail"),
    RESPONSE_LINK_ERROR_DUPLICATED_LOGIN(-2, "duplicate login"),
    ;

    WsCommand(int sn, String detail) {
        this.sn = sn;
        this.detail = detail;
    }

    private int sn;

    private String detail;

    public int getSn() {
        return sn;
    }

    public String getDetail() {
        return detail;
    }

    public Object getCmdMark() {
        return detail;
    }
}
