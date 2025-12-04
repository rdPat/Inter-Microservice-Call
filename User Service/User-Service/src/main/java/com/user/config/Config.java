package com.user.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    //@LoanBalanced allow rest template to use eureka
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        return  new RestTemplate();
    }

}
