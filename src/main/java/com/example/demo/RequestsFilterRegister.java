package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestsFilterRegister {

    @Autowired
    RateLimiter rateLimiter;

    @Bean
    public FilterRegistrationBean<PostCommentRateLimit> registerPostCommentsRateLimiter(){
        FilterRegistrationBean<PostCommentRateLimit> registrationBean  = new FilterRegistrationBean<>();

        registrationBean.setFilter(new PostCommentRateLimit(rateLimiter));
        registrationBean.addUrlPatterns("/comment");

        return registrationBean;
    }
}