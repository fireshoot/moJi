package com.osyangxin.moji.facade.intercepter;


import com.alibaba.fastjson.JSON;
import com.osyangxin.moji.common.bean.User;
import com.osyangxin.moji.common.bean.UserContextTool;
import com.osyangxin.moji.common.constants.Constants;
import com.osyangxin.moji.common.enums.EnvType;
import com.osyangxin.moji.common.enums.SysErrorCodeEnum;
import com.osyangxin.moji.common.exception.ApplicationException;
import com.osyangxin.moji.service.CacheService;
import com.osyangxin.moji.facade.annotation.AnonymousSupport;
import com.osyangxin.moji.facade.filter.RequestWrapper;
import com.osyangxin.moji.facade.log.LogTrace;
import com.osyangxin.moji.facade.utils.Platform;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author fengjianshe 全局拦截器；在此类定义预处理，后处理等逻辑；
 */
@Component
public class UrlInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(UrlInterceptor.class);

    private static final String MANAGE_COOKIE_KEY = "Token";

    @Autowired
    private CacheService cacheService;

    /**
     * 当前环境信息
     * 参考 common.constans.EnvType 枚举类
     */
    @Value("${spring.profiles.active}")
    private String env;

    /**
     * 进入controller层之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        String requestUri = httpServletRequest.getRequestURI();
        LogTrace.get().setStartTime(System.currentTimeMillis());
        LogTrace.get().setHttpMethod(httpServletRequest.getMethod());
        LogTrace.get().setUrl(requestUri);
        LogTrace.get().setReqParams(String.valueOf(convertRequestParameters(httpServletRequest.getParameterMap())));

        if (httpServletRequest instanceof RequestWrapper) {
            LogTrace.get().setBody(StringUtils.toEncodedString(
                    ((RequestWrapper) httpServletRequest).getRequestBody(), UTF_8));
        }

        String token = httpServletRequest.getHeader(Constants.HEADER_MEMBER_TOKEN);
        LogTrace.get().setToken(token);

        HandlerMethod handlerMethod = (HandlerMethod) o;
        LogTrace.get().setHandlerMethod(handlerMethod);
        AnonymousSupport anonymous = handlerMethod.getMethod().getAnnotation(AnonymousSupport.class);
        //匿名访问
        if (anonymous != null) {
            if (StringUtils.isNotBlank(token)) {
                String values = cacheService.getVal(String.format(Constants.CACHE_USER_SESSION_LOGIN_PREFIX, token));
                LogTrace.get().setUser(values);
                if (StringUtils.isNotBlank(values)) {
                    StringTokenizer st = new StringTokenizer(values, Constants.DOT);
                    httpServletRequest.setAttribute(Constants.REQUEST_HEADER_USER, st.nextToken());
                    httpServletRequest.setAttribute(Constants.REQUEST_HEADER_ACCOUNT_TYPE, st.nextToken());
                }
            }
            return true;
        }

        //token鉴权
        String values = cacheService.getVal(String.format(Constants.CACHE_USER_SESSION_LOGIN_PREFIX, token));
        LogTrace.get().setUser(values);
        UserContextTool.set(JSON.parseObject(values, User.class));
        if (StringUtils.isBlank(values)) {
            throw new ApplicationException(SysErrorCodeEnum.NEED_LOGIN);
        }
        StringTokenizer st = new StringTokenizer(values, Constants.DOT);
        httpServletRequest.setAttribute(Constants.REQUEST_HEADER_USER, st.nextToken());
        httpServletRequest.setAttribute(Constants.REQUEST_HEADER_ACCOUNT_TYPE, st.nextToken());

        // 处理
        handleUserAgent(httpServletRequest);
        return true;
    }

    /**
     * 环境校验
     * @param envs
     * @return
     */
    private boolean skipEnv(EnvType[] envs) {
        if(envs == null || envs.length == 0){
            return true;
        }
        for(EnvType env : envs){
            if(env.getIdentify().equals(this.env)){
                return false;
            }
        }
        return true;
    }

    private void handleUserAgent(HttpServletRequest httpServletRequest) {
        String userAgent = httpServletRequest.getHeader(Constants.HEADER_USER_AGENT);
        String platform = Platform.parse(userAgent);
        LogTrace.get().setUserAgent(platform);
        httpServletRequest.setAttribute(Constants.REQUEST_HEADER_USER_AGENT, platform);
    }

    private Map<String, String> convertRequestParameters(Map<String, String[]> parameterMap) {
        if (parameterMap == null) {
            return null;
        }
        Map<String, String> converts = new HashMap<>();
        for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
            converts.put(entry.getKey(), entry.getValue()[0]);
        }
        return converts;
    }

    public String getCookieToken(HttpServletRequest request) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(MANAGE_COOKIE_KEY)) {
                    token = cookie.getValue();
                }
            }
        }

        if(cookies == null || token == null){
            throw new ApplicationException(SysErrorCodeEnum.NEED_LOGIN);
        }
        return token;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
        // logger.info("ActionInterceptor.postHandle had been invoked..");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e) {
        // logger.info("ActionInterceptor.afterCompletion had been invoked..");
    }
}