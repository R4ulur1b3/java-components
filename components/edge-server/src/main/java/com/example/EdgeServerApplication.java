package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.example.filters.SimpleFilter;

@EnableZuulProxy
@SpringBootApplication
public class EdgeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgeServerApplication.class, args);
	}
	
	@Bean
	  public SimpleFilter simpleFilter() {
	    return new SimpleFilter();
	  }
}
