package com.devopsbuddy.config;

import com.devopsbuddy.backend.service.UserSecurityService;
import com.devopsbuddy.web.controllers.SignupController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Carren.Dsouza on 19/04/2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private UserSecurityService userSecurityService;

    private static final String SALT="sdkfjskfjs232";

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12,new SecureRandom(SALT.getBytes()));
    }

    private static final String[] PUBLIC_URLS={
      "/webjars/**","/forgotpassword/**","/changePassword/**", SignupController.SINGUP_URL_MAPPING+"/**",
            "/css/**","/js/**","/images/**","/","/about/**","/contact/**,/error/**/*",
            "/console/**"

    };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws  Exception {
        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains("dev")){
            httpSecurity.csrf().disable();
            httpSecurity.headers().frameOptions().disable();

        }
        httpSecurity.
                authorizeRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/payload")
                .failureUrl("/login?error").permitAll()
                .and()
                .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(userSecurityService)
         .passwordEncoder(passwordEncoder());
    }

}
