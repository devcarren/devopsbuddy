package com.devopsbuddy.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Carren.Dsouza on 19/04/2017.
 */
public class MockEmailService extends AbstractEmailService {
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
    @Override
    public void sendGenericMail(SimpleMailMessage simpleMailMessage) {
        LOG.debug(" Message Sending");
        LOG.info(simpleMailMessage.toString());
        LOG.debug(" Finished ");

    }
}
