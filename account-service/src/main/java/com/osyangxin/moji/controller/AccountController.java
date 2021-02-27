package com.osyangxin.moji.controller;

import com.osyangxin.moji.service.CaptchaService;
import com.osyangxin.moji.facade.annotation.AnonymousSupport;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangxin
 * @类描述
 * @time 2021/2/27  10:35
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private CaptchaService captchaService;

    @AnonymousSupport
    @PostMapping("/captcha")
    public void sendCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        captchaService.createImage(request, response);
    }

}
