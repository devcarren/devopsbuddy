package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domain.backend.PasswordResetToken;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.repositories.PasswordResetTokenRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Carren.Dsouza on 22/04/2017.
 */
@Service
@Transactional(readOnly = true)
public class PasswordResetTokenService {

    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetTokenService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${token.expiration.length.minutes}")
    private int tokeninMinutes;

    public PasswordResetToken findByToken(String token){return passwordResetTokenRepository.findByToken(token);}

    @Transactional
    public PasswordResetToken createPasswordResetTokenForEmail(String email){
        PasswordResetToken passwordResetToken = null;

        User user = userRepository.findByEmail(email);

        if (null != user){
            String token = UUID.randomUUID().toString();
            passwordResetToken = new PasswordResetToken(token,user,new Date(),tokeninMinutes);
            passwordResetToken = passwordResetTokenRepository.save(passwordResetToken);
            LOG.debug(" Sucessfully created Token ");
        } else {
            LOG.debug(" Unable to find user");
        }
        return passwordResetToken;
    }
}
