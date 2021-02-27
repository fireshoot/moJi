package com.osyangxin.moji.msg;


import com.osyangxin.moji.common.enums.BaseStub;

public enum RetStubDetail implements BaseStub {
    /**
     *
     * */

    EDIT_AUTH_NAME_ERROR(500101, "edit auth name error"),
    EDIT_AUTH_STATUS_ERROR(500102, "edit auth status error"),
    ADD_ROLE_ERROR(500103, "add role error"),
    EDIT_ROLE_ERROR(500104, "edit role error"),
    EDIT_ROLE_STATUS_ERROR(500105, "edit role status error"),
    EDIT_ROLE_AUTH_ERROR(500106, "edit role auth error"),
    ROLE_NAME_DUPLICATE_ERROR(500107, "role name duplicate error"),
    ROLE_STATUS_NO_CHANGE(500108, "role status no change"),
    DEL_AUTH_ERROR(500109, "del auth error"),
    ADD_AUTH_ERROR(500110, "add auth error"),
    UPDATE_AUTH_ORDINAL_ERROR(500111, "update auth ordinal_error"),
    AUTH_ORDINAL_EDIT_ERR(500112, "auth ordinal edit err"),
    AUTH_ORDINAL_NOT_FOUND(500113, "auth ordinal not found"),
    AUTH_PARENT_ID_NOT_FOUND(500114, "auth parent id not found"),
    AUTHID_LIST_IS_BULL(500115, "authId List is null"),
    AUTH_NOT_FOUND(500116, "auth not found"),

    LOGIN_CAPTCHA_ERROR(500201, "login captcha error"),
    USER_NOT_FOUND(500202, "user not found"),
    USER_DISABLE(500203, "user status is disable"),
    USER_PASSWORD_ERROR(500204, "user password error"),
    USER_AUTH_ERROR(500205, "user auth error"),

    VEHICLE_ADD_ERROR(500301, "vehicle add error "),
    VEHICLE_UPDATE_ERROR(500302, "vehicle update error "),
    VEHICLE_INFO_NOT_FOUND(500303, "vehicle info not found"),
    VEHICLE_INFO_EXIST(500304, "vehicle info exist "),
    VEHICLE_ID_ERROR(500305, "vehicle id not valid"),
    VEHICLE_DRIVER_ERROR(500306, "driver always has a vehicle"),
    VEHICLE_FORBID_DISABLE(500307, "vehicle forbid Disable, Because there are still orders travelling"),
    VEHICLE_RELATION_INFO_ERROR(500308, "vehicle relation info error"),

    DRIVER_ADD_ERROR(500401, "driver add error "),
    DRIVER_UPDATE_ERROR(500402, "driver update error "),
    DRIVER_INFO_NOT_FOUND(500403, "driver info not found"),
    DRIVER_INFO_EXIST(500404, "driver info exist "),
    DRIVER_ID_ERROR(500405, "driver_id is  null"),
    DRIVER_CODE_REPEAT_ERROR(500406, "driver code repeat exists"),
    FLEET_HEADMAN_REPEAT_ERROR(500407, "fleet headman always have this code"),
    DRIVER_ID_IS_NULL(500408, "输入id为空"),
    DATA_IS_ERROR(500408, "输入的数据有误"),
    DRIVER_MORE(500409, "driver Too many. Less than 3"),
    DRIVER_HAVE_MORE_VEHICLE(500410, "vehicle Too many. Less than 5"),
    DRIVERS_ID_REPEAT(500411, "driver id repeat"),
    DRIVERS_NOT_BELONG_FLEET(500412, "driver not belong to fleet"),
    DRIVERS_FORBID_DISMISSION(500413, "driver forbid dismission, Because there are still orders travelling"),
    DRIVERS_PHONE_EXIST(500414, "driver PhoneCheck exist"),
    DRIVERS_PENALTY_OPERATION_ERROR(500415, "driver penalty information operation error"),
    PENALTY_ID_IS_NULL(500416, "driver penalty id is null"),
    PENALTY_UPDATE_ERROR(500417, "driver penalty update error"),
    DRIVERS_CREDIT_OPERATION_ERROR(500418, "driver credit information operation error"),
    CREDIT_ID_IS_NULL(500419, "driver credit id is null"),
    CREDIT_UPDATE_ERROR(500420, "driver credit update error"),
    PENALTY_IS_NULL(500421, "driver penalty  is null"),
    CREDIT_IS_NULL(500422, "driver credit  is null"),
    DRIVERS_TRAINING_OPERATION_ERROR(500423, "driver training information operation error"),
    TRAINING_ID_IS_NULL(500424, "driver training id is null"),
    TRAINING_UPDATE_ERROR(500425, "driver training update error"),
    TRAINING_IS_NULL(500426, "driver training  is null"),
    DRIVER_NOT_BOUND_VEHICLE(500427, "driver not bound vehicle"),
    DRIVER_EXTEND_ADD_ERROR(500428, "driver detail add error "),
    DRIVER_EXTEND_UPDATE_ERROR(500429, "driver detail update error "),
    DRIVERS_SF_ACCOUNT_EXIST(500430, "driver sf account  exist"),
    DRIVERS_NO_AVAILABLE_VEHICLE(500431, "driver no available vehicle"),
    DRIVERS_NO_DEATIL_INFORMATION(500431, "driver no detail information"),
    DRIVER_SETTINGS_NOT_FOUND(500432, "driver settings not find"),
    DRIVER_SETTINGS_ADD_ERROR(500433, "driver settings add error"),
    MULTIPLE_DRIVER_SETTINGS_ARE_FOUND(500434, "multiple driver settings are found"),
    DRIVER_CHILDREN_SERVICE_NOT_AVAILABLE(500435, "no available driver exist for chidren service"),
    INTERVAL_ERR(500436, "interval only 30 or 60"),
    REFRESH_TOKEN_ERR(500437, "refresh token err"),
    DATABASE_REFRESHTOKEN_EMPTY(500438, "database refreshtoken empty"),
    INSERT_APIAUTH_ERR(500439, "insert apiauth err"),
    UPDATE_APIAUTH_ERR(500440, "update apiauth err"),


    FLEET_NAME_EXIST(500501, "fleet name exists"),
    FLEET_ADD_ERROR(500502, "fleet add error"),
    FLEET_UPDATE_ERROR(500503, "fleet update error"),
    FLEET_ID_ERROR(500504, "fleet_id not exists"),
    FLEET_NOT_EXIST(500505, "fleet not exists"),
    SYSUSER_ADD_ERROR(500601, "sysUser add error "),
    SYSUSER_UPDATE_ERROR(500602, "sysUser update error "),
    SYSUSER_INFO_NOT_FOUND(500603, "sysUser info not found"),
    SYSUSER_INFO_EXIST(500604, "sysUser info exist "),
    SYSUSER_ID_ERROR(500605, "sysUser_id not exists"),
    SYSUSER_UPDATE_STATUS_ERROR(500606, "Role is disable,sysUser status can not set enable!"),
    SYSUSER_NO_ROLE(500607, "use no role message"),

    FLEETHEADMAN_ADD_ERROR(500701, "fleetHeadman add error"),
    FLEETHEADMAN_CODE_ERROR(500702, "fleetHeadman code exists"),
    FLEETHEADMAN_UPDATE_ERROR(500703, "fleetHeadman update error"),
    FLEETHEADMAN_NAME_ERROR(500704, "fleetHeadman name not found"),
    FLEETHEADMAN_ID_ERROR(500705, "fleetHeadman id not found"),
    FLEETHEADMAN_INPUT_ERROR(500706, "fleetHeadman id/status is null"),
    FLEETHEADMAN_STATUS_ERROR(500707, "fleetHeadman status nerror"),

    USER_ROLE_IS_EXISTS(500901, " user always have a role"),
    USER_ROLE_ADD_ERROR(500902, "user_role add error "),

    ORDER_NOT_EXIST(501001, "order not exist "),
    SHOW_TYPE_ERROR(501002, "show type incorrect or is null"),
    ORDER_STATUS_TRANSFORM_ERROR(501003, "transform error: maybe argument not is digit"),
    ORDER_TYPE_TRANSFORM_ERROR(501004, "order type transform error: maybe argument not is digit"),
    ORDER_STATUS_ILLEGAL(501005, "order status error: operation is not allowed"),
    ORDER_EDIT_UNPAID_STATUS_ILLEGAL(501006, "order unpaid status error: status not is 2 | 5"),
    ORDER_STATUS_UPDATED_ERROR(501007, "order status updated error"),
    ORDER_DATE_ILLEGAL(501101, "date illegal"),
    ILLEGAL_ORDER_STATUS_FOR_CANCEL(501102, "illegal order status for cancel"),
    ORDER_COMPLAIN_ADD_ERROR(501103, "order complain  add error "),
    ORDER_LONGITUDE_LATITUDE_ERROR(501104, "order longitude latitude error"),
    ORDER_TYPE_ERROR(501105, "订单类型不为孩子单 或者 找不到该订单"),
    ORDER_ID_IS_NULL(501106, "order_id is null"),
    FUNCTION_LIMIT(501007, "this interface only user booking order"),
    UNKNOWN_RESPONSIBLE_PARTY(501106,"unknown responsible party"),
    ORDER_ID_ALREADY_EXISTS_IN_HISTORY_ORDER_TABLE(501107,"order id already exists in history order table"),



    MEMBER_UPDATE_ERROR(501301, "用户信息更新失败"),
    MEMBER_NOT_FOUND(501302, "用户信息不存在"),
    MEMBER_IS_EXIST(501303, "该用户已存在"),
    MEMBER_INSERT_ERROR(501304, "用户插入失败"),
    MEMBER_REASON_IS_NULL(501305, "拒绝理由不能为空"),

    UPLOAD_IS_NULL(501201, "file is null"),
    UPLOAD_TYPE_EROOR(501202, "file type error"),

    PLATFORM_BASE_INFO_EMPTY(501301, "platform base info is empty"),
    PLATFORM_OPERATING_INFO_EMPTY(501302, "platform operating info is empty"),
    PLATFORM_PAYMENT_INFO_EMPTY(501303, "platform payment info is empty"),
    PLATFORM_SERVICE_INFO_EMPTY(501304, "platform service info is empty"),
    PLATFORM_LICENSE_INFO_EMPTY(501305, "platform license info is empty"),
    PLATFORM_FARE_INFO_EMPTY(501306, "platform fare info is empty"),
    PLATFORM_OPERATING_INFO_BELONGING_ERROR(501307, "platform operating info belonging error"),
    PLATFORM_PAYMENT_INFO_BELONGING_ERROR(501308, "platform payment info belonging error"),
    PLATFORM_SERVICE_INFO_BELONGING_ERROR(501309, "platform service info belonging error"),
    PLATFORM_LICENSE_INFO_BELONGING_ERROR(501310, "platform license info belonging error"),
    PLATFORM_FARE_INFO_BELONGING_ERROR(501311, "platform fare info belonging error"),
    PLATFORM_BASE_INFO_UPDATE_ERROR(501312, "platform base info update error"),
    PLATFORM_OPERATING_INFO_UPDATE_ERROR(501313, "platform operating info update error"),
    PLATFORM_PAYMENT_INFO_UPDATE_ERROR(501314, "platform payment info update error"),
    PLATFORM_SERVICE_INFO_UPDATE_ERROR(501315, "platform service info update error"),
    PLATFORM_LICENSE_INFO_UPDATE_ERROR(501316, "platform license info update error"),
    PLATFORM_FARE_INFO_UPDATE_ERROR(501317, "platform fare info update error"),
    PLATFORM_FARE_INFO_ADD_ERROR(501318, "platform fare into add error"),
    PLATFORM_PAYMENT_INFO_ADD_ERROR(50319, "platform payment info add error"),

    OPERATION_FLAG_IS_NULL(501401, "operation flag is null or flag not is 1、2、3 "),

    CUSTOMER_EROOR(501501, "customer is empty,need data add"),

    DRIVER_APP_ID_EROOR(501601, "driverApp id error"),

    MEMBERFEEDBACK_PHONUM_RANGE_ERROR(501701, "phoneNumber should be digits range from 1 to 11"),
    MEMBERFEEDBACK_ID_ERROR(501702, "memberFeedback not exists"),
    MEMBERFEEDBACK_UPDATE_ERROR(501703, "deletedStatus update failed"),

    INTERVIEW_INFO_NOT_FOUND(501801, "interview info not found"),
    INTERVIEW_INFO_PHONE_ILLEGALITY(501802, "interview info's phone and login member's phone is inconformity"),
    INTERVIEW_INFO_UPDATE_ERROR(501803, "interview info update error"),
    INTERVIEW_INFO_STARTDATE_GT_ENDDATE(501804, "interview info start date gt end date"),
    NO_INTERVIEW_TIME(501805, "interview time at least one"),
    NO_INTERVIEW_PLACE(501806, "interview place is null"),
    NO_INTERVIEW_CONTACT(501807, "interview contact is null"),

    FAILED_TO_GET_TOKEN(501901, "failed to get token"),

    WALLET_NOT_FOUND(502001, "wallet not found"),

    FILE_FORMAT_NOT_SUPPORTED(502002,"file format not supported"),

    METHOD_HAS_NO_IMPLEMENTATION(502003,"method has no implementation"),

    // 高德msg,
    RESOURCE_UNAVAILABLE(301001, "resource unavalible"),
    QUERY_EMPTY_DATA(301002, "query no data"),
    QUERY_CITY_ERROR(301003, "input data fail"),
    QUERY_AMAP_DATA_ERROR(301004, "query amap data error"),

    /* 内容管理*/
    CONTENT_TYPE_ERROR(301101, "已经存在一个公司介绍"),
    CONTENT_NOT_EXIST(301102, "内容不存在"),
    CONTENT_STATUS_ERROR(301103, "id 或者status为空"),

    PARAMETER_ERROR(3012001, "参数不正确"),
    RECHARGE_AMOUNT_EXIST(3012002, "该金额已经存在"),
    RECHARGE_AMOUNT_SIZE(3012003, "金额配置表最大10条"),
    FORBID_DELETE(3012004, "禁止删除，该金额还有活动未结束"),

    DUP_COUPON_ERR(3013001, "该优惠券已存在，请重新输入"),

    INFORMATION_TIP1(3014001,  "不能上移，该用户已经是最优先"),
    INFORMATION_TIP2(3014002,  "不能下移，该用户已经是最低级"),
    INFORMATION_NOT_FOUND(3014003,  "推广信息没有找到"),


    ACTIVITY_CONFLICT(800907,"活动时间（相同金额下）、名称冲突"),
    ;

    private int code;
    private String msg;

    RetStubDetail(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
