package com.osyangxin.moji.facade.log;


/**
 * 方法实现说明
 * @author      yangxin
 * @date        2020/12/29 18:22
*/
public class LogTrace {

    private static final ThreadLocal<HttpTraceLog> LOG = new ThreadLocal<>();

    public static void set(HttpTraceLog ioLog) {
        LOG.set(ioLog);
    }

    public static HttpTraceLog get() {
        HttpTraceLog ioLog = LOG.get();
        if (ioLog == null) {
            HttpTraceLog ioLogItem = new HttpTraceLog();
            LOG.set(ioLogItem);
        }
        return LOG.get();
    }

    public static void clearAll() {
        LOG.remove();
    }

}
