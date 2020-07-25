package com.test.wesure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/**")
//                .addResourceLocations("file:/media/siva/Part1/Me/");
        		.addResourceLocations("gs://itlpics_bucket/images/");
//        		.addResourceLocations("file:/home/ftpuser/ftp/upload/");
    }

}
