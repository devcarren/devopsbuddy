package com.devopsbuddy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Carren.Dsouza on 19/04/2017.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "user/login";
    }
}
