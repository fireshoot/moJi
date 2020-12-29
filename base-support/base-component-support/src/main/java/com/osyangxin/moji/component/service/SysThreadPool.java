package com.osyangxin.moji.component.service;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.MDC;

/**
 * 方法实现说明
 * @author      yangxin
 * @date        2020/12/29 21:11
*/
public class SysThreadPool {

    private ThreadPoolExecutor threadPoolExecutor;

    private SysThreadPool(ThreadPoolExecutor threadPoolExecutor) {
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public static void doExecute(Runnable task) {
        Map<String, String> context = MDC.getCopyOfContextMap();
        getInstance().threadPoolExecutor.execute(() -> {
            // 将父线程的MDC内容传给子线程
            if(context != null){
                MDC.setContextMap(context);
            }
            try {
                task.run();
            } finally {
                // 清空MDC内容
                MDC.clear();
            }
        });
    }

    private static SysThreadPool getInstance() {
        return InstanceHolder.SINGLE_POOL;
    }

    private static class InstanceHolder {

        private static final Integer CORE_POOL_SIZE = 50;
        private static final Integer MAX_POOL_SIZE = 20;
        private static final Long KEEP_ALIVE_TIME = 2L;
        private static final Integer QUEUE_CAPACITY = 50;
        private static final TimeUnit TIME_UNIT = TimeUnit.MINUTES;
        private static final LinkedBlockingQueue QUEUE = new LinkedBlockingQueue(QUEUE_CAPACITY);
        private static final ThreadFactory FACTORY = new ThreadFactory() {
            private final AtomicInteger integer = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "SysThreadPool thread: " + integer.getAndIncrement());
            }
        };
        public static final SysThreadPool SINGLE_POOL = new SysThreadPool(
                new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT, QUEUE, FACTORY));
    }
}
