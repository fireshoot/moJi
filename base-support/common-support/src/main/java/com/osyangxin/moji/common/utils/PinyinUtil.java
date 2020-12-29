package com.osyangxin.moji.common.utils;

import java.util.regex.Pattern;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * 方法实现说明
 * @
 * @author      yangxin
 * @date        2020/12/29 17:57
*/
public class PinyinUtil {

    private final static HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();

    /**
     * ^[\u2E80-\u9FFF]+$ 匹配所有东亚区的语言
     * ^[\u4E00-\u9FFF]+$ 匹配简体和繁体
     * ^[\u4E00-\u9FA5]+$ 匹配简体
     */
    private final static Pattern PATTERN = Pattern.compile("^[\u4E00-\u9FFF]+$");

    /**
     * 将字符串第一个汉字转成拼音(取首字母或全拼)
     *
     * @param text 文本
     * @param full 是否全拼
     */
    public static String convertChinesePinyinByFirst(String text, boolean full, boolean upperCase) {
        if (text == null || "".equals(text.trim())) {
            return "";
        }
        String pinyin = convertSingleChinesePinyin(text.charAt(0));
        if (!full && pinyin.length() > 0) {
            pinyin = String.valueOf(pinyin.charAt(0));
        }
        if (pinyin == null) {
            pinyin = "";
        }
        if (upperCase) {
            return pinyin.toUpperCase();
        }
        return pinyin;
    }

    /**
     * 将汉字转成拼音(取首字母或全拼)
     *
     * @param text 文本
     * @param full 是否全拼
     */
    public static String convertChinesePinyin(String text, boolean full) {
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuilder sb = new StringBuilder();
        if (text == null || "".equals(text.trim())) {
            return "";
        }
        String pinyin;
        for (int i = 0; i < text.length(); i++) {
            char unit = text.charAt(i);
            if (PATTERN.matcher(String.valueOf(unit)).find())//是汉字，则转拼音
            {
                pinyin = convertSingleChinesePinyin(unit);
                if (full) {
                    sb.append(pinyin);
                } else {
                    if (pinyin.length() > 0) {
                        sb.append(pinyin.charAt(0));
                    }
                }
            } else {
                sb.append(unit);
            }
        }
        return sb.toString();
    }

    /**
     * 将单个汉字转成拼音
     */
    private static String convertSingleChinesePinyin(char chinese) {
        try {
            String[] res = PinyinHelper.toHanyuPinyinStringArray(chinese, outputFormat);
            if (res == null || res.length == 0) {
                return String.valueOf(chinese);
            }
            return res[0];//对于多音字，只用第一个拼音
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}
