package com.osyangxin.moji.common.constants;

public class Constants {

    public static final String HEART_BEAT_TIMESTAMP = "ws:login";

    // 验证码有效时间
    public static final long CAPTCHA_EXPIRE_TIME = 12000L;

    // 乘客token过期时间，暂定1天
    public static final long PASSENGER_TOKEN_EXPIRE_TIME = 24 * 60 * 60L;


    /**
     * 用户登录CACHE_前缀 token -> userid，key前缀：login:token:user:token
     */
    public static final String CACHE_TOKEN_USER_PREFIX = "login:token:user:%s";

    /**
     * 用户登录态CACHE_前缀 userid-->token，key前缀: login:user:token:accountType:loginType:userid
     */
    public static final String CACHE_USER_TOKEN_LOGIN_PREFIX = "login:user:token:%s:%s";

    //后台管理图形验证码
    public static final String CACHE_LOGIN_CAPTCHA_PREFIX = "captcha:login:%s";



    public static final String SESSION_USER_INFO = "user";
    public static final String HEADER_MEMBER_TOKEN = "token";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String REQUEST_HEADER_USER = "userId";
    public static final String REQUEST_HEADER_USER_AGENT = "userAgent";
    public static final String REQUEST_HEADER_ACCOUNT_TYPE = "accountType";
    public static final String REQUEST_HEADER_LOGIN_TYPE = "loginType";
    public static final String REQUEST_HEADER_TRACEID = "traceId";

    public static final String CONDITION_LOCATE = "locate";
    public static final String CONDITION_BETWEEN = "between";
    public static final String CONDITION_IN = "in";
    public static final String CONDITION_GE = ">=";
    public static final String CONDITION_LE = "<=";
    public static final String DOT = ",";

}
