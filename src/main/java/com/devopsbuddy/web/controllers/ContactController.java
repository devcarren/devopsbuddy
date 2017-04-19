package com.devopsbuddy.web.controllers;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Carren.Dsouza on 19/04/2017.
 */
@Controller
public class ContactController {

    @Autowired
    EmailService emailService;

    private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);

    @RequestMapping(value="/contact",method = RequestMethod.GET)
    public String contactGet(ModelMap modelMap){
        FeedbackPojo feedbackPojo = new FeedbackPojo();
        modelMap.addAttribute("feedback",feedbackPojo);
        return "contact/contact";
    }

    @RequestMapping(value="/contact",method = RequestMethod.POST)
    public String contactPost(@ModelAttribute("feedback") FeedbackPojo feedbackPojo,ModelMap modelMap){
        LOG.debug("Feedback Pojo Content {}",feedbackPojo);
        emailService.sendFeedbackMail(feedbackPojo);
        feedbackPojo = new FeedbackPojo();
        modelMap.addAttribute("feedback",feedbackPojo);
        return "contact/contact";
    }
}
