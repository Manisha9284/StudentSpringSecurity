package org.javapatil.studproject.util.security.configuration;

import java.util.ArrayList;
import java.util.List;

import org.javapatil.studproject.util.security.admin.SessionHandler;
import org.javapatil.studproject.util.security.admin.impl.SessionHandlerImpl;
import org.javapatil.studproject.util.security.interceptor.AuthorizationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Sachin Patil aka JavaPatil on 11 Dec 2019
 */
@EnableWebMvc
@Configuration
public class WebConfigure extends WebMvcConfigurerAdapter {
    private static final Logger log = LoggerFactory.getLogger("WebConfigure");


    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

    @Bean
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("Messages");
        return resourceBundleMessageSource;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
    	        .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        return bCryptPasswordEncoder;
    }

    @Bean
    public SessionHandler sessionHandler() {
        return new SessionHandlerImpl();
    }

    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        AuthorizationInterceptor authorizationInterceptor = new AuthorizationInterceptor();
        List<String> bypassUrlList = new ArrayList<>();
        bypassUrlList.add("/v1/admin/login");
        bypassUrlList.add("/v1/admin/signup");
        authorizationInterceptor.setBypassUrlList(bypassUrlList);
        authorizationInterceptor.setSessionHandler(sessionHandler());
        return authorizationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor());
    }


}
