package com.osyangxin.moji.common.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * 方法实现说明
 * @author      yangxin
 * @date        2020/12/29 16:54
*/
public class User implements Serializable {
    private static final long serialVersionUID = -421104393601661200L;

    private int userId;
    private String username;
    private Short type;

    private Map<String, String> authMap;

    public static long getSerialVersionUid() {
        return serialVersionUID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
