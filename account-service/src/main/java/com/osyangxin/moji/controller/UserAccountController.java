package com.osyangxin.moji.controller;


import com.osyangxin.dao.model.AccountInfo;
import com.osyangxin.moji.common.constants.Constants;
import com.osyangxin.moji.facade.annotation.AnonymousSupport;
import com.osyangxin.moji.input.LoginForm;
import com.osyangxin.moji.input.ResetPasswordForm;
import com.osyangxin.moji.input.UserRegisterFrom;
import com.osyangxin.moji.service.LoginService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * @author yangxin666
 */
@RestController
@RequestMapping("/account/user")
public class UserAccountController {

    @Resource
    private LoginService loginService;

    @AnonymousSupport
    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginForm user) {
        return loginService.userLogin(user);
    }

    @AnonymousSupport
    @PostMapping("/register")
    public Boolean register(@RequestBody @Valid UserRegisterFrom userRegisterFrom) {
        return loginService.userRegister(userRegisterFrom);
    }


    @PostMapping("/password-modify")
    public Boolean modifyPassword(@RequestBody @Valid ResetPasswordForm passwordForm,
                                  @SessionAttribute(Constants.SESSION_ADMIN_INFO) AccountInfo user) {
        return loginService.userResetPassword(passwordForm, user);
    }

    @GetMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        return loginService.userLogout(request);
    }


}
