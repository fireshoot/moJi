package com.osyangxin.moji.facade.log;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 方法实现说明
 * @author      yangxin
 * @date        2020/12/29 18:22
*/
@Data
public class HttpTraceLog {

    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求方式
     */
    private String httpMethod;

    /**
     * 请求token
     */
    private String token;

    /**
     * 用户信息
     */
    private Object user;

    /**
     * 请求参数
     */
    private String reqParams;

    /**
     * 请求body
     */
    private String body;

    /**
     * 响应参数
     */
    private Object respParams;
    /**
     * 开始时间
     */
    private long startTime;

    /**
     * 花费时间
     */
    private long spendTime;

    /**
     * 客户端代理类型
     */
    private String userAgent;

    /**
     * 控制器方法
     */
    @JsonIgnore
    private Object handlerMethod;
}
