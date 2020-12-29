package com.osyangxin.moji.common.constants;

/**
 * @author yangxin
 * @类描述
 * @time 2019/12/16  10:11
 */
public enum GroupCheckFlag {

    /**
     * 校验字段的group为ValidData.class和Default.class标识的字段
     * */
    ValidDataFlag((short) 0),

    /**
     * 只校验字段的group为null或者为Default.class标识的字段
     * */
    DefaultFlag((short) 1);


    private short status;

    GroupCheckFlag(short status) {
        this.status = status;
    }

    public short getStatus() {
        return status;
    }
}
