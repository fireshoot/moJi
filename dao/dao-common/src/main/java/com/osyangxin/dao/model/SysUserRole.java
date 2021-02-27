package com.osyangxin.dao.model;

import com.osyangxin.moji.bean.BaseModel;
import java.util.Date;
import lombok.Data;

@Data
public class SysUserRole extends BaseModel {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private Date createdAt;

    private Date updatedAt;

    private Short deleted;

}