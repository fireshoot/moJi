package com.osyangxin.moji.common.enums;

/**
 * @author yangxin
 * @类描述
 * @time 2021/2/27  13:26
 */
public enum YesNoEnum {
    /**
     * 启用,未删除
     */
    Y( 0),
    /**
     * 停用， 删除
     */
    N(1),

    ;

    private Integer status;

    YesNoEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}

