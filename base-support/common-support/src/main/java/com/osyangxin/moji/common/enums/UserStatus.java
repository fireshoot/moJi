package com.osyangxin.moji.common.enums;

public enum UserStatus {

    /**
     * 启用
     */
    STATUS_ENABLE((short) 0),
    /**
     * 停用
     */
    STATUS_DISABLE((short) 1);

    private short status;

    UserStatus(short status) {
        this.status = status;
    }

    public short getStatus() {
        return status;
    }
}