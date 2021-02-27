package com.osyangxin.dao.model;

import com.osyangxin.moji.bean.BaseModel;
import java.util.Date;

public class SysRoleAuth extends BaseModel {
    private Integer id;

    private Integer roleId;

    private Integer authId;

    private Date createdAt;

    private Date updatedAt;

    private Short deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Short getDeleted() {
        return deleted;
    }

    public void setDeleted(Short deleted) {
        this.deleted = deleted;
    }
}