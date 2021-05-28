package com.tim40.tim40;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

	@Configuration
	@EnableWebMvc
	public class CorseConfiguration implements WebMvcConfigurer {


	@Override
	public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/**").allowedMethods("PUT", "DELETE", "POST", "GET").allowedOrigins("http://localhost:8081");

	}



	}

