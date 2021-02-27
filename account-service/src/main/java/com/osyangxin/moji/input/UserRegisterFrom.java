package com.osyangxin.moji.input;

import com.osyangxin.moji.constant.AccountConstants;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author yangxin
 * @类描述
 * @time 2021/2/27  15:07
 */
@Data
public class UserRegisterFrom {

    /**
     * 用户姓名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(min = 1, max = 20, message = "用户名长度在1-20个字符之间")
    private String accountName;

    /**
     * 用户手机号
     */
    @NotBlank(message = "手机号码不能为空")
    @Length(min = 11, max = 11, message = "手机号码为11位")
    @Pattern(regexp = AccountConstants.PHONE_NUM_CHECK_REGEX, message = "手机号格式不正确")
    private String accountPhone;

    /**
     * 用户手机号
     */
    @NotBlank(message = "邮箱不能为空")
    private String accountEmail;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
