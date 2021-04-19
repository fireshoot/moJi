package com.osyangxin.dao.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @类描述
 * @time 2021/2/27  14:06
 * @author yangxin
 */
/**
    * 企业用户信息
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@TableName(value = "account_info")
public class AccountInfo {
    /**
     * 唯一标识
     */
//    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 用户标识
     */
//    @TableField(value = "account_num")
    private String accountNum;

    /**
     * 用户姓名
     */
//    @TableField(value = "account_name")
    private String accountName;

    /**
     * 用户状态
     */
//    @TableField(value = "account_status")
    private Integer accountStatus;

    /**
     * 用户手机号
     */
//    @TableField(value = "account_phone")
    private String accountPhone;

    /**
     * 用户手机号
     */
//    @TableField(value = "account_email")
    private String accountEmail;

    /**
     * 密码
     */
//    @TableField(value = "password")
    private String password;

    /**
     * 创建时间
     */
//    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
//    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否删除 0未删除1已删除
     */
//    @TableField(value = "is_delete")
    private String isDelete;
}