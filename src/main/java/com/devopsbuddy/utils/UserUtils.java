package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.web.domain.frontend.BasicAccountPayload;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Carren.Dsouza on 20/04/2017.
 */
public class UserUtils {

    private UserUtils(){}

    public static User createBasicUser(){
        User user = new User();
        user.setFirstName("Carren");
        user.setLastName("Dsdouza");
        user.setCountry("India");
        user.setDescription(" IN india");
        user.setEmail("hello@y.com");
        user.setEnabled(true);
        //user.setId(1);
        user.setPassword("Login345");
        user.setPhoneNumber("2323232");
        user.setProfileImageUrl("http://djx.ddd/ddd.jpg");
        user.setStripeCustomerId("234234234");
        user.setUsername("carren");
        return user;

    }

    public static String createPasswordResetUrl(HttpServletRequest httpServletRequest, long id, String token) {
        String passwordResetURL=
                httpServletRequest.getScheme()+"://"+
                        httpServletRequest.getServerName()+":"+
                        httpServletRequest.getServerPort()+
                        httpServletRequest.getContextPath()+
                        "/changePassword"+
                        "?id="+id+
                        "&token="+token;

        return passwordResetURL;


    }

    public static <T extends BasicAccountPayload> User fromWebUsertoDomain(T frontEndPayLoad){
        User user = new User();
        BeanUtils.copyProperties(frontEndPayLoad,user);
        user.setEnabled(true);
        return user;
    }
}
