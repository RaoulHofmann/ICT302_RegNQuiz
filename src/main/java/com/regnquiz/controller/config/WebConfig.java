package com.regnquiz.controller.config;

import com.regnquiz.model.LectureRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Raoul Hofmann
 * @comment Config for WebMVC including beans for booking managment
 */
@Configuration
@Component
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    SessionInterceptor sessionInterceptor;

    @Bean
    public HashMap<Integer, LectureRun> bookings(){
        HashMap<Integer, LectureRun>  bookings = new HashMap<>();
        return bookings;
    }

    @Bean
    public HashMap<String, Integer> runningBookings(){
        HashMap<String, Integer>  runningBookings = new HashMap<>();
        return runningBookings;
    }

    @Bean
    public List<Integer> closedBookings(){
        List<Integer>  closedBookings = new ArrayList<>();
        return closedBookings;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

}
