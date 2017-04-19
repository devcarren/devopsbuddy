package com.devopsbuddy.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Carren.Dsouza on 19/04/2017.
 */
public class SmtpEmailService extends AbstractEmailService {
    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendGenericMail(SimpleMailMessage simpleMailMessage) {
        LOG.debug("Sending mail for {}" , simpleMailMessage);
        mailSender.send(simpleMailMessage);
        LOG.debug(" Send Completed");
    }
}
