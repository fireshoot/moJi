package com.osyangxin.moji.advice;


import com.osyangxin.moji.common.constants.Constants;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * 针对定时任务，添加 traceId
 */
@Component
@Aspect
@Slf4j
public class ScheduleAdvice {

    private static final String SCHEDULE_ABLE = "@annotation(org.springframework.scheduling.annotation.Scheduled)";

    @Around(SCHEDULE_ABLE)
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            // 添加traceId
            String traceId = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
            MDC.put(Constants.REQUEST_HEADER_TRACEID, traceId);
            log.info("-----------------start: {}",proceedingJoinPoint.getSignature().getName());
            Object obj = proceedingJoinPoint.proceed();
            log.info("-----------------end  : {}\n", proceedingJoinPoint.getSignature().getName());
            return obj;
        } finally {
            // 清除 traceId
            MDC.remove(Constants.REQUEST_HEADER_TRACEID);
        }
    }

}

