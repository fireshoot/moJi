package com.osyangxin.moji.facade.exception;


import com.osyangxin.moji.common.enums.SysErrorCodeEnum;
import com.osyangxin.moji.common.exception.ApplicationException;
import com.osyangxin.moji.facade.log.LogTrace;
import com.osyangxin.moji.facade.view.JsonView;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 方法实现说明 ： 统一异常处理逻辑
 * @author      yangxin
 * @date        2020/12/29 18:25
*/
@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    private static final Map EMPTY_DATA = null;


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object exceptionGet(Exception e) {
        logger.error("Exception for handle ", e);
        JsonView<Map> jsonView;
        if (e instanceof ApplicationException) {
            jsonView = new JsonView<>();
            jsonView.setData(EMPTY_DATA);

            ApplicationException applicationException = (ApplicationException) e;
            jsonView.setCode(applicationException.getRetStub().getCode());
            jsonView.setMessage(applicationException.getRetStub().getMsg());

        } else if (e instanceof IllegalArgumentException) {
            jsonView = new JsonView<>();
            jsonView.setData(EMPTY_DATA);

            jsonView.setCode(SysErrorCodeEnum.PARAMETER_TYPE_INVALID.getCode());
            jsonView.setMessage(SysErrorCodeEnum.PARAMETER_TYPE_INVALID.getMsg());

        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException applicationException = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = applicationException.getConstraintViolations();
            StringBuilder stringBuilder = new StringBuilder();
            for (ConstraintViolation<?> item : violations) {
                stringBuilder.append("[").append(item.getMessage()).append("]");
            }
            String msg = stringBuilder.toString();
            logger.error("ConstraintViolation msg is : " + msg);
            jsonView = new JsonView<>(SysErrorCodeEnum.PARAMETER_TYPE_INVALID, msg, EMPTY_DATA);

        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException applicationException = (MethodArgumentNotValidException) e;
            List<ObjectError> allErrors = applicationException.getBindingResult().getAllErrors();
            StringBuilder stringBuilder = new StringBuilder();
            for (ObjectError error : allErrors) {
                stringBuilder.append("[").append(error.getDefaultMessage()).append("]");
            }
            String msg = stringBuilder.toString();
            logger.error("ArgumentNotValid  msg is : " + msg);
            jsonView = new JsonView<>(SysErrorCodeEnum.PARAMETER_TYPE_INVALID, msg, EMPTY_DATA);

        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException applicationException = (MissingServletRequestParameterException) e;
            String parameterName = applicationException.getParameterName();
            String parameterType = applicationException.getParameterType();
            String msg = ("parameter " + parameterName + " is null " + " , expect: " + parameterType);
            jsonView = new JsonView<>(SysErrorCodeEnum.PARAMETER_IS_NULL, msg, EMPTY_DATA);

        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            HttpMediaTypeNotSupportedException applicationException = (HttpMediaTypeNotSupportedException) e;
            String msg = applicationException.getContentType().getSubtype();
            jsonView = new JsonView<>(SysErrorCodeEnum.CONTENT_TYPE_INVALID, msg, EMPTY_DATA);

        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException applicationException = (HttpRequestMethodNotSupportedException) e;
            String msg = applicationException.getMethod();
            jsonView = new JsonView<>(SysErrorCodeEnum.METHOD_INVALID, msg, EMPTY_DATA);

        } else if (e instanceof NoHandlerFoundException) {
            NoHandlerFoundException applicationException = (NoHandlerFoundException) e;
            String msg = (applicationException.getHttpMethod() + " --> " + applicationException.getRequestURL());
            jsonView = new JsonView<>(SysErrorCodeEnum.RESOURCE_INVALID, msg, EMPTY_DATA);

        } else if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException applicationException = (MethodArgumentTypeMismatchException) e;
            String msg = ("parameter " + applicationException.getName() + " is not type of " + applicationException
                    .getRequiredType().getSimpleName());
            jsonView = new JsonView<>(SysErrorCodeEnum.PARAMETER_TYPE_INVALID, msg, EMPTY_DATA);

        } else if (e instanceof HttpMessageNotReadableException) {
            HttpMessageNotReadableException applicationException = (HttpMessageNotReadableException) e;
            String msg = applicationException.getMessage();
            jsonView = new JsonView<>(SysErrorCodeEnum.REQUEST_BODY_INVALID, msg, EMPTY_DATA);

        } else if (e instanceof BindException) {
            BindException applicationException = (BindException) e;
            BindingResult bindingResult = applicationException.getBindingResult();
            FieldError fieldError = bindingResult.getFieldError();
            String field = fieldError.getField();
            jsonView = new JsonView<>(SysErrorCodeEnum.PARAMETER_TYPE_INVALID, field, EMPTY_DATA);

        } else {
            jsonView = new JsonView<>(SysErrorCodeEnum.DEFAULT_FAIL, EMPTY_DATA);
        }

        /*Other API统一响应模板*/
        HandlerMethod handlerMethod = (HandlerMethod) LogTrace.get().getHandlerMethod();
        if (handlerMethod != null){
            // todo : 如果其他系统对接，此处自定义返回类型
        }
        return jsonView;
    }

}



