package com.devopsbuddy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Carren.Dsouza on 20/04/2017.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application-common.properties")
public class ApplicationConfig {
}
