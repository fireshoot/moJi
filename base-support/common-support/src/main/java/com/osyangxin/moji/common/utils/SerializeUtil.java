package com.osyangxin.moji.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 序列化工具类
 */
public class SerializeUtil {

    public static String serialize(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T deserialize(String jsonVal, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonVal)) {
            return null;
        }
        return JSONObject.parseObject(jsonVal, clazz);
    }

//    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
//        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
//        ObjectOutputStream out = new ObjectOutputStream(byteOut);
//        try {
//            out.writeObject(src);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
//        ObjectInputStream in = new ObjectInputStream(byteIn);
//        @SuppressWarnings("unchecked")
//        List<T> dest = (List<T>) in.readObject();
//        return dest;
//    }

}