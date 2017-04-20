package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.backend.User;

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
        user.setEmail(" Hello@y.com");
        user.setEnabled(true);
        //user.setId(1);
        user.setPassword("Login345");
        user.setPhoneNumber("2323232");
        user.setProfileImageUrl("http://djx.ddd/ddd.jpg");
        user.setStripeCustomerId("234234234");
        user.setUsername("carren");
        return user;

    }
}
