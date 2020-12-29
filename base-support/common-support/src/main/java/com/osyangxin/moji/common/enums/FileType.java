package com.osyangxin.moji.common.enums;

/**
 * 文件类型
 */
public enum FileType {

    /**
     * 图片
     */
    IMAGE((short) 1),

    /**
     * apk文件
     */
    APK((short) 2),

    ALL((short) 3),

    ;

    private short type;

    FileType(short type) {
        this.type = type;
    }

    public short getType() {
        return type;
    }
}
