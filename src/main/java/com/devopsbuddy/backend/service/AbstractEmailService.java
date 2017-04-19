package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Carren.Dsouza on 19/04/2017.
 */
public abstract  class AbstractEmailService implements EmailService {

    @Value("$(default.to.address)")
    private String defaultToAddress;

    protected SimpleMailMessage prepareSimpleMailMessageFromPojo(FeedbackPojo feedbackPojo){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(defaultToAddress);
        simpleMailMessage.setFrom(feedbackPojo.getEmail());
        simpleMailMessage.setSubject(" Test Subject from Devops Buddy");
        simpleMailMessage.setText(feedbackPojo.getFeedback());
        return simpleMailMessage;
    }

    @Override
    public void sendFeedbackMail(FeedbackPojo feedbackPojo){
        sendGenericMail(prepareSimpleMailMessageFromPojo(feedbackPojo));
    }
}
