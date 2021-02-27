package com.osyangxin.dao.model;

import java.util.Date;

/**
 * @author yangxin
 * @time 2019/7/23  16:58
 */
public class SysUserInfoView {
    private Integer id;

    private String name;

    private String phoneNum;

    private Integer roleId;

    private String roleName;

    private Short status;

    private Date createdAt;

    private Date updatedAt;

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "SysUserInfoView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNum=" + phoneNum +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
