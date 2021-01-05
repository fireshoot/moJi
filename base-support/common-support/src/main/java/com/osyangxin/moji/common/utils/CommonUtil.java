package com.osyangxin.moji.common.utils;


import cn.hutool.core.util.ReflectUtil;
import com.osyangxin.moji.common.bean.UserContextTool;
import org.apache.commons.lang.StringUtils;

/**
 * 方法实现说明
 * @author      yangxin
 * @date        2021/1/5 15:35
*/
public class CommonUtil {

    //被其他包含的放后面
    private final static String[] ProvinceSuffixArr = {"省", "壮族自治区", "维吾尔自治区", "回族自治区", "自治区", "特别行政区"};
    private final static String[] CitySuffixArr = {"市", "县", "地区", "新区", "区", "自治州", /*"州",*/ "盟"};

    public static String removeProvinceSuffix(String province) {
        return removeSuffix(province, ProvinceSuffixArr);
    }

    public static String removeCitySuffix(String city) {
        return removeSuffix(city, CitySuffixArr);
    }

    public static String removeSuffix(String str, String[] suffixArr) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        String suffix = null;
        for (String s : suffixArr) {
            if (str.endsWith(s)) {
                suffix = s;
                break;
            }
        }
        if (suffix == null) {
            return str;
        }
        return str.substring(0, str.length() - suffix.length());
    }

    public static void setCreateByInfo(Object obj) {
        setCreateByInfo(obj, UserContextTool.getUnionId(), UserContextTool.getUserName());
    }

    public static void setCreateByInfoElseSys(Object obj) {
        setCreateByInfo(obj, UserContextTool.getUnionIdOrSys(), UserContextTool.getUserNameOrSys());
    }

    public static void setCreateByInfo(Object obj, Long userId, String userName) {
        if (obj == null || userId == null) {
            return;
        }
        if (userName == null){
            userName = "";
        }
        ReflectUtil.setFieldValue(obj, "createBy", userId);
        ReflectUtil.setFieldValue(obj, "createName", userName);
    }

    public static void setUpdateByInfoElseSys(Object obj) {
        setUpdateByInfo(obj, UserContextTool.getUnionIdOrSys(), UserContextTool.getUserNameOrSys());
    }

    public static void setUpdateByInfo(Object obj) {
        setUpdateByInfo(obj, UserContextTool.getUnionId(), UserContextTool.getUserName());
    }

    public static void setUpdateByInfo(Object obj, Long userId, String userName) {
        if (obj == null || userId == null) {
            return;
        }
        if (userName == null){
            userName = "";
        }
        ReflectUtil.setFieldValue(obj, "updateBy", userId);
        ReflectUtil.setFieldValue(obj, "updateName", userName);
    }

    public static void setCreateByAndUpdateByInfoElseSys(Object obj) {
        setCreateByAndUpdateByInfo(obj, UserContextTool.getUnionIdOrSys(), UserContextTool.getUserNameOrSys());
    }

    public static void setCreateByAndUpdateByInfo(Object obj) {
        setCreateByAndUpdateByInfo(obj, UserContextTool.getUnionId(), UserContextTool.getUserName());
    }

    public static void setCreateByAndUpdateByInfo(Object obj, Long userId, String userName) {
        setCreateByInfo(obj, userId, userName);
        setUpdateByInfo(obj, userId, userName);
    }
}
