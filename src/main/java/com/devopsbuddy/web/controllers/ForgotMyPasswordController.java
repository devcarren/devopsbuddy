package com.devopsbuddy.web.controllers;

import com.devopsbuddy.backend.persistence.domain.backend.PasswordResetToken;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.service.PasswordResetTokenService;
import com.devopsbuddy.utils.UserUtils;
import com.devopsbuddy.web.i18n.I18NService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Carren.Dsouza on 22/04/2017.
 */
@Controller
public class ForgotMyPasswordController {
    private static final Logger LOG = LoggerFactory.getLogger(ForgotMyPasswordController.class);


    @Autowired
    private PasswordResetTokenService passwordResetTokenService;


    @Autowired
    private I18NService i18NService;

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public String forgotPasswordGet(){
        return "forgotmypassword/emailForm";
    }

    @RequestMapping(value="/forgotpassword",method = RequestMethod.POST)
    public String forgotPasswordPost(HttpServletRequest httpServletRequest,
                                     @RequestParam("email") String email,
                                     ModelMap modelMap){
        PasswordResetToken passwordResetToken = passwordResetTokenService.createPasswordResetTokenForEmail(email);

        if (null == passwordResetToken){
            LOG.warn(" No email {} ",email);
        } else {
            User user = passwordResetToken.getUser();
            String token = passwordResetToken.getToken();
            String passwordResetUrl= UserUtils.createPasswordResetUrl(httpServletRequest,user.getId(),token);

            String emailText = i18NService.getMessage("fogorgot_password_text",httpServletRequest.getLocale());
            SimpleMailMessage mailMessage  = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Reset your password");
            mailMessage.setText(emailText+"\r\n" +passwordResetUrl);
            mailMessage.setFrom("admin@y.com");
            LOG.info("Token {} ",passwordResetUrl);
        }

        modelMap.addAttribute("mailSent","true");


        return "forgotmypassword/emailForm";
    }
}
