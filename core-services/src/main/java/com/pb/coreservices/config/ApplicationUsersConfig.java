package com.pb.coreservices.config;

import com.pb.coreservices.controller.api.ApplicationUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationUsersConfig {

    private final ApplicationUserApi applicationUserApi;

    @Autowired
    public ApplicationUsersConfig(ApplicationUserApi applicationUserApi) {
        this.applicationUserApi = applicationUserApi;
    }

    @Bean
    void initiateUsers() throws Exception {
        applicationUserApi.initiateUsers();
    }

}
