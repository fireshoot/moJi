package com.osyangxin.moji.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
    private static final Logger log = LoggerFactory.getLogger(StringUtil.class);
    /**
     * 拼装sql in查询条件
     */
    public static String join2SqlInStr(Object... objects) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        stringBuilder.append(StringUtils.join(objects, ","));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /**
     * 拼装sql in查询条件
     */
    public static String join2SqlInStr(List<?> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        stringBuilder.append(StringUtils.join(list, ","));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /**
     * 随机加密串生成器
     */
    public static String generatorRandomEncryptSequence() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date nowDate = new Date();
        String nowTime = sdf.format(nowDate);
        UUID uuId = UUID.randomUUID();
        return encryption(nowTime + (uuId.toString().replaceAll("-", "")));
    }

    /**
     * @param plainText 明文
     * @return 32位密文
     */
    private static String encryption(String plainText) {
        String re_md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());

            byte[] b = md.digest();
            StringBuilder buf = new StringBuilder();
            for (byte value : b) {
                int i = value;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            log.error("错误信息为：", e);
        }
        return re_md5;
    }

    /**
     * 根据指定长度返回随机数, 一般用作验证码使用.
     *
     * @author alexzhou
     */
    public static String generateRandomNumber(int length) {
        if (length < 1) {
            return "";
        }
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    /**
     * 判断字符串是否为空（包括null、""、"null"、"undefined"）
     *
     * @param s 需检查的字符串
     * @return
     */
    public static boolean isNull(String s) {
        return s == null || s.equals("") || s.equalsIgnoreCase("null")
                || s.equalsIgnoreCase("undefined");
    }

    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return
     * @author OL
     */
    public static boolean isEmpty(String value) {
        value = trim(value);
        return value == null || value.equals("") ? true : false;
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static String trim(String value) {
        return StringUtils.trim(value);
    }

    /**
     * 日期转字符串
     *
     * @param date       日期
     * @param dateFormat 转换格式，yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dateToString(Date date, String dateFormat) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * 获取电话后四位
     * @param phone
     * @return
     */
    public static String getPhone4Tail(String phone) {
        return phone.substring(phone.length()-4);
    }

}
