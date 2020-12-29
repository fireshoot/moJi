package com.osyangxin.moji.common.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author xieqiaofu
 * @date 2020/8/24
 */
public class StringTools {

    /**
     * 判断是否是中文字符
     * @param ch
     * @return
     */
    public static boolean isHan(char ch) {
        Character.UnicodeScript of = Character.UnicodeScript.of(ch);
        if (Character.UnicodeScript.HAN == of) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串中是否包含汉字
     * @param str
     * @return
     */
    public static boolean isHan(String str) {
        if (null == str) {
            return false;
        }
        for (char ch : str.toCharArray()) {
            if(isHan(ch)) {
               return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否只由字母和数字组成
     * @param str
     * @return
     */
    public static boolean isAlphanumericSpace(String str) {
        if (null == str || isHan(str)) {
            return false;
        }
        return StringUtils.isAlphanumericSpace(str);
    }


}
