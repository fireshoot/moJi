package com.osyangxin.moji.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 *
 */
public class JsonUtils {
    static private Object convertJson(Object json) {
        if (json == null) {
            return null;
        } else if (json instanceof JSONArray) {
            List list = jsonToList((JSONArray) json);
            return list;
        } else if (json instanceof JSONObject) {
            Map map = jsonToMap((JSONObject) json);
            return map;
        } else {
            return json;
        }
    }

    /**
     * json对象转换为List对象
     *
     * @param json json对象
     * @return
     */
    static public List jsonToList(JSONArray json) {
        if (json == null) {
            return null;
        }
        List result = new ArrayList();
        for (int i = 0; i < json.size(); i++) {
            result.add(convertJson(json.get(i)));
        }
        return result;
    }

    /**
     * json对象转换为ParaMap对象
     *
     * @param json json对象
     * @return
     */
    static public Map jsonToMap(JSONObject json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        Map result = new HashMap();
        Iterator it = json.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            result.put(key, convertJson(json.get(key)));
        }
        return result;
    }

    /**
     * 从JSON字段串返回ParaMap对象
     *
     * @param json json对象，数组等不支持
     * @return
     */
    static public Map strToMap(String json) {
        if (StringUtil.isNull(json)) {
            return null;
        }
        try {
            return jsonToMap(JSONObject.parseObject(json));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从JSON字段串返回ParaMap对象
     *
     * @param json json对象，数组等不支持
     * @return
     */
    static public Map<String, String> json2StringMap(String json) {
        Map<String, Object> jsonMap =  strToMap(json);
        if(jsonMap == null){
            return null;
        }
        Map<String, String> jsonStringMap = new HashMap<>();
        for(Map.Entry<String, Object> e : jsonMap.entrySet()){
            String key = e.getKey();
            Object value = e.getValue();
            jsonStringMap.put(key, String.valueOf(value));
        }
        return jsonStringMap;
    }

}
