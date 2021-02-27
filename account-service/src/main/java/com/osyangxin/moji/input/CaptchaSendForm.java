package com.osyangxin.moji.input;

import com.osyangxin.moji.constant.AccountConstants;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 * @author tongwenwu
 * @since 2019/07/23 11:20
 */
public class CaptchaSendForm {
    @NotBlank(message = "手机号码不能为空")
    @Length(min = 11, max = 11, message = "手机号码为11位")
    @Pattern(regexp = AccountConstants.PHONE_NUM_CHECK_REGEX, message = "手机号格式不正确")
    private String phoneNum;
    private Short tag;

    public Short getTag() {
        return tag;
    }

    public void setTag(Short tag) {
        this.tag = tag;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
