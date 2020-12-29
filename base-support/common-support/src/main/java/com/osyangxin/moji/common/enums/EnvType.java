package com.osyangxin.moji.common.enums;

/**
 * 环境信息
 */
public enum EnvType {

    /**
     * 开发
     */
    DEV("dev"),

    /**
     * 测试
     */
    TEST("test"),

    /**
     * 自动化测试
     */
    AUTO("auto"),

    /**
     * 压测
     */
    PERF("perf"),

    /**
     * 生产
     */
    PROD("prod"),

    ;

    private String identify;

    private EnvType(String identify) {
        this.identify = identify;
    }

    public String getIdentify() {
        return identify;
    }

}
