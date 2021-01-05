package com.osyangxin.moji.common.bean;


import com.osyangxin.moji.common.enums.SysErrorCodeEnum;
import com.osyangxin.moji.common.exception.ApplicationException;

public class UserContextTool {

    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void set(User userDeptInfo) {
        threadLocal.set(userDeptInfo);
    }

    public static User get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static void setElseSys() {
        if (get() == null) {
            threadLocal.set(getSysInfo());
        }
    }

    public static Long getCheckUnionId() {
        User userDeptInfo = get();
        if (userDeptInfo != null) {
            return userDeptInfo.getUnionId();
        }
        throw new ApplicationException(SysErrorCodeEnum.NEED_LOGIN);
    }

    public static Long getUnionId() {
        User userDeptInfo = get();
        if (userDeptInfo != null) {
            return userDeptInfo.getUnionId();
        }
        return null;
    }

    public static Long getUnionIdOrSys() {
        Long id = getUnionId();
        if (id == null) {
            id = getSysUnionId();
        }
        return id;
    }

    public static Long getUnionIdHandNull() {
        if (getUnionId() != null) {
            return getUnionId();
        }
        return -1L;
    }

    public static String getUserNameOrSys() {
        String name = getUserName();
        if (name == null) {
            name = getSysName();
        }
        return name;
    }

    public static String getUserNumOrSys() {
        String userNum = getUserNum();
        if (userNum == null) {
            userNum = getSysUserNum();
        }
        return userNum;
    }

    public static String getUserName() {
        User userDeptInfo = get();
        if (userDeptInfo != null) {
            return userDeptInfo.getUsername();
        }
        return null;
    }

    public static String getUserNum() {
        User userDeptInfo = get();
        if (userDeptInfo != null) {
            return userDeptInfo.getUserNum();
        }
        return null;
    }

    public static String getUserNameHandNull() {
        if (getUserName() != null) {
            return getUserName();
        }
        return "";
    }

    public static User getSysInfo() {
        User user = new User();
        user.setId(-1L);
        user.setUnionId(-1L);
        user.setUsername("系统");
        user.setUserNum("系统");
        user.setEnable("Y");
        return user;
    }

    public static Long getSysUnionId() {
        return getSysInfo().getUnionId();
    }

    public static String getSysName() {
        return getSysInfo().getUsername();
    }

    public static String getSysUserNum() {
        return getSysInfo().getUserNum();
    }

    public static User getElseSys() {
        User userDeptInfo = get();
        if (userDeptInfo == null) {
            userDeptInfo = getSysInfo();
        }
        return userDeptInfo;
    }
}
