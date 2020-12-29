package com.osyangxin.moji.facade.utils;

import java.util.regex.Pattern;

public class Platform {
    private static final Pattern ANDROID_PATTERN = Pattern.compile("android", Pattern.CASE_INSENSITIVE);
    private static final Pattern IPAD_PATTERN = Pattern.compile("ipad", Pattern.CASE_INSENSITIVE);
    private static final Pattern IPHONE_PATTERN = Pattern.compile("iphone", Pattern.CASE_INSENSITIVE);
    private static final Pattern MINI_PROGRAM_PATTERN = Pattern.compile("micromessenger", Pattern.CASE_INSENSITIVE);


    public static String parse(String userAgentString) {
        if (matches(MINI_PROGRAM_PATTERN,userAgentString)){
            return PlatformType.MINI_PROGRAM.name();
        }else if (matches(ANDROID_PATTERN,userAgentString)){
            return PlatformType.ANDROID.name();
        }else if (matches(IPAD_PATTERN,userAgentString)||matches(IPHONE_PATTERN,userAgentString)){
            return PlatformType.IOS.name();
        }else {
            return PlatformType.UNKNOWN.name();
        }
    }


    private static boolean matches(Pattern pattern, String userAgentStr) {
        return pattern.matcher(userAgentStr).find();
    }
}