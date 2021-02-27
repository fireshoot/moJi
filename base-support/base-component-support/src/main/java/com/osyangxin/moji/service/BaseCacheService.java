package com.osyangxin.moji.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface BaseCacheService {

    void setVal(String key, Object val);

    void setVal(String key, Object val, Long expire);

    <T> T getVal(String key, Class<T> clazz);

    String getVal(String key);

    void rightPush(String key, Object val);

    <T> void rightPushAll(String key, List<T> list);

    void rightPush(String key, Object val, Long expire);

    <T> List<T> range(String key, Integer start, Integer end, Class<T> clazz);

    List<String> range(String key, Integer start, Integer end);

    Long removeFromList(String key, Integer start, Integer end);

    <T> T pop(String key, Class<T> clazz);

    void putToMap(String key, Object itemKey, Object item);

    void putToMap(String key, Object itemKey, Object item, Long expire);

    void addToSet(String key, String value);

    Set<String> getSetMember(String key);

    <T> T getFromMap(String key, Object itemKey, Class<T> clazz);

    void deleteVal(String key);

    Long getCurrentTimeMilliForCache();

    Boolean setIfAbsent(String key, Object val);

    Boolean setIfAbsent(String key, Object val, long timeout, TimeUnit unit);

    <T> T getAndSet(String key, Object val, Class<T> clazz);

    <T> T getAndSet(String key, T val);

    void sendMessage(String channel, Object message);

    void batchSetVal(Map<String, String> cacheKeyValues);
}
