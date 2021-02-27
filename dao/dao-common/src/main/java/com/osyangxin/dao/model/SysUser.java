package com.osyangxin.dao.model;

import com.osyangxin.moji.bean.BaseModel;
import java.util.Date;
import lombok.Data;

@Data
public class SysUser extends BaseModel {
    private Integer id;

    private String name;

    private Integer status;

    private String password;

    private String phoneNum;

    private Date lastResetPasswordTime;

    private Date lastVisitTime;

    private Date createdAt;

    private Date updatedAt;

    private Integer deleted;

    private Date getMsgAt;
    
    private Date bookingOrderGetMsgAt;

    private Integer roleId;

}