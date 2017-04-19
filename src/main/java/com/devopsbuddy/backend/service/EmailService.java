package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Carren.Dsouza on 19/04/2017.
 */
public interface EmailService {
    public void sendFeedbackMail(FeedbackPojo feedbackPojo);
    public void sendGenericMail(SimpleMailMessage simpleMailMessage);
}
