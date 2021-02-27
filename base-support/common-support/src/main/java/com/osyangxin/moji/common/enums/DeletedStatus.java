package com.osyangxin.moji.common.enums;

/**
 * 是否已被删除
 *
 * @author wangjialong
 */
public enum DeletedStatus {


    /**
     * 未被删除
     */
    DELETED_NO((short) 0),
    /**
     * 已被删除
     */
    DELETED_YES((short) 1);

    private short status;

    DeletedStatus(short status) {
        this.status = status;
    }

    public short getStatus() {
        return status;
    }
}
