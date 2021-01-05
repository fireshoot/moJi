package com.osyangxin.moji.common.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * 方法实现说明
 *
 * @author yangxin
 * @date 2020/12/29 16:54
 */
public class User implements Serializable {
    private static final long serialVersionUID = -421104393601661200L;

    private Long id;
    private String username;
    private Short type;

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 统一登录标识
     */
    private Long unionId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 启用：Y 禁用：N
     */
    private String enable;

    private Map<String, String> authMap;

    public static long getSerialVersionUid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Map<String, String> getAuthMap() {
        return authMap;
    }

    public void setAuthMap(Map<String, String> authMap) {
        this.authMap = authMap;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Long getUnionId() {
        return unionId;
    }

    public void setUnionId(Long unionId) {
        this.unionId = unionId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
