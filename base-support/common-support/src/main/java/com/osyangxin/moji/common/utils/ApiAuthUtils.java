package com.osyangxin.moji.common.utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

@Slf4j
public class ApiAuthUtils {

    public static final String FIELD_SIGN = "sign";

    public static final String FIELD_APP_ID_MAAS = "appId";

    public static final String FIELD_APP_ID_MT = "channel";

    /**
     * 生成指定长度的随机字符串
     *
     * @param length 随机串长度
     * @return
     */
    public static String generateRandomNonceStr(int length) {
        if (length < 1) {
            return "";
        }
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(chars.charAt(random.nextInt(chars.length() - 1)));
        }
        return result.toString();
    }

    /**
     * 生成MAAS平台签名，MD5
     *
     * @param data 请求参数
     * @param key  appSecret
     * @return
     */
    public static String generateSignWithMd5(Map<String, String> data, String key) {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[0]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (StringUtils.isNotBlank(data.get(k))) {
                // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
            }
        }
        sb.append("key=").append(key);
        String sign = DigestUtils.md5DigestAsHex(sb.toString().getBytes(StandardCharsets.UTF_8)).toUpperCase();
        log.info("生成MAAS平台签名, sign_str: {}, sign: {}", sb.toString(), sign);
        return sign;
    }


    /**
     * 验证签名
     *
     * @param data 请求数据
     * @param key  appSecret
     * @return
     */
//    public static boolean verifySignature(Map<String, String> data, String key, ApiAuthType authType, String signField) {
//        if (!data.containsKey(signField)) {
//            throw new IllegalArgumentException("can not find 'sign' field!");
//        }
//        String sign = data.get(signField);
//        data.remove(signField);
//        log.info("请求签名sign: {}", sign);
//        boolean result = false;
//        switch (authType) {
//            case MAAS:
//                result = generateMaasSignWithMd5(data, key).equals(sign);
//                break;
//            case MT:
//                result = generateMtSignWithSha1(data, key).equals(sign);
//                break;
//            default:
//                log.info("unknown ApiAuthType: {}", authType.name());
//                result = false;
//                break;
//        }
//        return result;
//    }

}
