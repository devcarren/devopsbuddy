package com.devopsbuddy.web.controllers;

import com.devopsbuddy.web.domain.frontend.ProAccountBasicPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Carren.Dsouza on 23/04/2017.
 */
@Controller
public class SignupController {
    private static final Logger LOG = LoggerFactory.getLogger(SignupController.class);

    public static  final String SINGUP_URL_MAPPING="/signup";

    public static final String PAYLOAD_MODEL_KEY_NAME="payload";
    public static final String SUBSCRIPTION_VIEW_NAME="registration/signup";

    @RequestMapping(value = SINGUP_URL_MAPPING,method = RequestMethod.GET)
    public String singupGet(@RequestParam("planId") int planId,ModelMap modelMap){

        modelMap.addAttribute(PAYLOAD_MODEL_KEY_NAME,new ProAccountBasicPayload());
        return SUBSCRIPTION_VIEW_NAME;
    }

}
