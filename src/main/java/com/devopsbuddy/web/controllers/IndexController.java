package com.devopsbuddy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Carren.Dsouza on 18/04/2017.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }
}
