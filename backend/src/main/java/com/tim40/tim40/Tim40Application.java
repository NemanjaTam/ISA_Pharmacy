package com.tim40.tim40;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tim40.tim40.model.LoyaltyProgram;
import com.tim40.tim40.repository.LoyaltyProgramRepository;
@EnableTransactionManagement
@SpringBootApplication
public class Tim40Application {
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:8080");
            }
        };
    }
	
	public static void main(String[] args) {
		LoyaltyProgram lp = new LoyaltyProgram(null, "DSADA", 32, 645.0);
		SpringApplication.run(Tim40Application.class, args);
	}

}
