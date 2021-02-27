package com.osyangxin.moji.input;


import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
public class LoginForm {

    /**
     * 用户名、邮箱、电话号码
     * */
    @NotBlank(message = "用户名不能为空")
    @Length(min = 1, max = 20, message = "用户名长度在1-20个字符之间")
    private String keywords;

    @NotBlank(message = "密码不能为空")
    @Length(min = 32, max = 32, message = "密码长度是32个字符")
    private String password;

    @NotBlank(message = "验证码不能为空")
    private String captcha;

    @NotBlank(message = "序号不能为空")
    private String uuid;

    @Range(min = 0, max = 1, message = "登陆状态(0-打开, 1-关闭)")
    private Short autoLogin;
}
