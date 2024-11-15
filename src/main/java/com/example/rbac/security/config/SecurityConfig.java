package com.example.rbac.security.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.rbac.security.filters.JWTAuthorizationFilter;

import jakarta.servlet.Filter;

@Configuration
public class SecurityConfig {
	
	 @Bean
	 FilterRegistrationBean<JWTAuthorizationFilter> jwtAuthorizationFilter() {
	        FilterRegistrationBean<JWTAuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
	        registrationBean.setFilter(new JWTAuthorizationFilter());
	        registrationBean.addUrlPatterns("/api/test/secure/*"); // Set URL patterns to apply the filter
	        return registrationBean;
	}
}
