package com.osyangxin.moji.facade.filter;

import com.osyangxin.moji.common.constants.Constants;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;

/**
 * RequestWrapperFilter
 * 用于对HttpServletRequest进行包装
 */
@Order(1)
@WebFilter(urlPatterns = "/*", filterName = "requestWrapperFilter")
public class RequestWrapperFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 添加traceId
        String traceId = ((HttpServletRequest) servletRequest).getHeader(Constants.REQUEST_HEADER_TRACEID);
        if (StringUtils.isBlank(traceId)) {
            traceId = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
        }
        MDC.put(Constants.REQUEST_HEADER_TRACEID, traceId);

        try {
            // multipart/form-data请求不添加traceId
            if (ServletFileUpload.isMultipartContent((HttpServletRequest) servletRequest)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                filterChain.doFilter(new RequestWrapper((HttpServletRequest) servletRequest), servletResponse);
            }
        } finally {
            // 清除 traceId
            MDC.remove(Constants.REQUEST_HEADER_TRACEID);
        }
    }

    @Override
    public void destroy() {

    }
}