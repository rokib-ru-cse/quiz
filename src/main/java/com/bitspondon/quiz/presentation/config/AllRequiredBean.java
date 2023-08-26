package com.bitspondon.quiz.presentation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;

@Configuration
public class AllRequiredBean {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("your-smtp-host");
        mailSender.setPort(587); // Use your SMTP port
        mailSender.setUsername("your-email@example.com");
        mailSender.setPassword("your-email-password");

        return mailSender;
    }

    @Bean
    public UserProvider getUserProvider() {
            return new UserProvider() {
                @Override
                public FeatureUser getCurrentUser() {
                    return new SimpleFeatureUser("rokib@gmail.com", true);
                }
            };

    }
}
