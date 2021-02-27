package com.osyangxin.moji.input;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class ResetPasswordForm {

    @NotNull(message = "旧密码不能为空")
    @Length(min = 32, max = 32, message = "旧密码长度应该是32字符")
    private String oldPassword;

    @NotNull(message = "新密码不能为空")
    @Length(min = 32, max = 32, message = "新密码长度应该是32字符")
    private String password;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
