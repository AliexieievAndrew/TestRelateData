package com.example.demo.config;

import com.example.demo.service.NetFlowService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public NetFlowService getNetFlowService () {
        return new NetFlowService();
    }
}
