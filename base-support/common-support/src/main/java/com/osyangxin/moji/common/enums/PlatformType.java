package com.osyangxin.moji.common.enums;

public enum PlatformType {

    /**
     * comment
     * */
    ANDROID("Android"),
    IOS("IOS"),
    MINI_PROGRAM("MiniProgram"),
    UNKNOWN("Unknown"),
    ;
    private String value;

    PlatformType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
