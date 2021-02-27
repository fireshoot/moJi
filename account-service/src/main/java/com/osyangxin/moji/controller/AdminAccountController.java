package com.osyangxin.moji.controller;

import com.osyangxin.moji.common.bean.User;
import com.osyangxin.moji.common.constants.Constants;
import com.osyangxin.moji.facade.annotation.AnonymousSupport;
import com.osyangxin.moji.input.LoginForm;
import com.osyangxin.moji.input.ResetPasswordForm;
import com.osyangxin.moji.service.LoginService;
import java.util.Map;
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
@RequestMapping("/account/admin")
public class AdminAccountController {

    @Resource
    private LoginService loginService;

    @AnonymousSupport
    @PostMapping("login")
    public Map<String, Object> login(@RequestBody @Valid LoginForm user) {
        return loginService.login(user);
    }

    @PostMapping("authInfo")
    public Object getAuthInfo(@SessionAttribute(Constants.SESSION_ADMIN_INFO) User user){
        return loginService.getAuthInfo(user.getId().intValue());
    }

    @PostMapping("password-modify")
    public Boolean modifyPassword(@RequestBody @Valid ResetPasswordForm passwordForm,
                                  @SessionAttribute(Constants.SESSION_ADMIN_INFO) User user) {
        return loginService.adminResetPassword(passwordForm, user);
    }

    @GetMapping("logout")
    public boolean logout(HttpServletRequest request) {
        return loginService.adminLogout(request);
    }

}
