package com.osyangxin.moji.common.utils;

import java.net.InetAddress;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 方法实现说明
 * @author      yangxin
 * @date        2020/12/29 17:59
*/
public class HttpServletRequestUtils {
    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST = "127.0.0.1";
    private static final String SEPARATOR = ",";

    public static HttpServletRequest currentServletRequest() {
        HttpServletRequest request = null;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            return request;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getIpAddr() {
        HttpServletRequest request =  currentServletRequest();
        if (request == null) {
            return  null;
        }
        return getIpAddr(request);
    }

    public static String getRefererRemoveParams() {
        HttpServletRequest request =  currentServletRequest();
        if (request == null) {
            return  null;
        }
        String referer = request.getHeader("referer");
        if (referer != null && referer.length() > 0) {
            referer = referer.split("\\?")[0];
        }
        return referer;
    }

    public static String getReferer() {
        HttpServletRequest request =  currentServletRequest();
        if (request == null) {
            return  null;
        }
        return request.getHeader("referer");
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress;
        try {
            //X-Forwarded-For：Squid 服务代理
            ipAddress = request.getHeader("x-forwarded-for");
            
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                //Proxy-Client-IP：apache 服务代理
                ipAddress = request.getHeader("Proxy-Client-IP");
            }

            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                //WL-Proxy-Client-IP：weblogic 服务代理
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }

            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                //HTTP_CLIENT_IP：有些代理服务器
                ipAddress = request.getHeader("HTTP_CLIENT_IP");
            }

            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                //X-Real-IP：nginx服务代理
                ipAddress = request.getHeader("X-Real-IP");
            }
            //还是不能获取到，最后再通过request.getRemoteAddr();获取
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (LOCALHOST.equals(ipAddress)) {
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                        ipAddress = inet.getHostAddress();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            // "***.***.***.***".length()
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(SEPARATOR) > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }
}
