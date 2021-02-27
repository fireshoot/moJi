package com.osyangxin.moji.constant;

/**
 * @author tongwenwu
 * @since 2019/07/22 15:03
 */
public class AccountConstants {

    public static final long CAPTCHA_EXPIRE_TIME = 300;

    public static final int CAPTCHA_LENGTH = 6;

    public static final String CACHE_LOGIN_CAPTCHA_PREFIX = "app:login:captcha:%s";

    public static final String PHONE_NUM_CHECK_REGEX =
            "^(13[0-9]|14[579]|15[0-3,5-9]|166|17[0135678]|18\\d{1}|19[89])\\d{8}$";
}
