// src/main/java/com/jio/callvision/config/WebConfig.java
package com.jio.callvision.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve images from /images/** URL pattern
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/")
                .setCachePeriod(3600);
        
        // Also serve all static resources (fallback)
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        
        // Serve from root for index.html
        registry.addResourceHandler("/index.html")
                .addResourceLocations("classpath:/static/index.html");
    }
}