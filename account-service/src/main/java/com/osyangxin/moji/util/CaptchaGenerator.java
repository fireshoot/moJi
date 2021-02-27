package com.osyangxin.moji.util;

import java.util.Random;

/**
 * @author tongwenwu
 * @since 2019/07/22 15:16
 */
public class CaptchaGenerator {
    public static String genDigitalCaptcha(int length) {
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; ++i) {
            int r = random.nextInt(10);
            code.append(r);
        }

        return code.toString();
    }
}
