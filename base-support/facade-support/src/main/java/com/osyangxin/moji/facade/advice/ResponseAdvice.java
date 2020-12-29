package com.osyangxin.moji.facade.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osyangxin.moji.common.enums.SysErrorCodeEnum;
import com.osyangxin.moji.facade.annotation.RawType;
import com.osyangxin.moji.facade.log.LogTrace;
import com.osyangxin.moji.facade.view.JsonView;
import com.osyangxin.moji.facade.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 方法实现说明
 * @author      yangxin
 * @date        2020/12/29 18:22
*/
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    private static final Logger log = LoggerFactory.getLogger(ResponseAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }


    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        Object result = null;
        /*
         * 对Controller函数的返回内容做包装后再返回给前端
         */
        try {
            if (o instanceof View
                    || o instanceof String
                    || methodParameter.getMethodAnnotation(RawType.class) != null) {
                result = o;
                return result;
            }
            result = new JsonView<>(SysErrorCodeEnum.DEFAULT_SUCCESS, o);
            return result;
        } finally {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                LogTrace.get().setSpendTime(System.currentTimeMillis() - LogTrace.get().getStartTime());
                LogTrace.get().setRespParams(objectMapper.writeValueAsString(result));
                log.info("Trace log is ====>  " + objectMapper.writeValueAsString(LogTrace.get()));
            } catch (Exception e) {
                log.error("Trace log error : ", e);
            } finally {
                LogTrace.clearAll();
            }
        }
    }
}