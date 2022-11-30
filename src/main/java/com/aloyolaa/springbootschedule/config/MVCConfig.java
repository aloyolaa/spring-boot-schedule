package com.aloyolaa.springbootschedule.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {

    private final HandlerInterceptor schedule;

    public MVCConfig(@Qualifier("schedule") HandlerInterceptor schedule) {
        this.schedule = schedule;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(schedule).excludePathPatterns("/close");
    }
}
