package com.pb.coreservices.config;

import com.pb.coreservices.controller.api.ApplicationUserApi;
import com.pb.coreservices.controller.dto.ApplicationUsersDto;
import com.pb.coreservices.controller.dto.ApplicationUserDetailDto;
import com.pb.coreservices.service.jaxb.mapper.JaxbApplicationUsersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Set;

@Configuration
@EnableWebSecurity
@DependsOn("initiateUsers")
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(CustomSecurityConfig.class);

    private ApplicationUserApi applicationUserApi;

    @Autowired
    public void setApplicationUserApi(ApplicationUserApi applicationUserApi) {
        this.applicationUserApi = applicationUserApi;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        ApplicationUsersDto mappedObjects = applicationUserApi.getApplicationUsersDto();
        Set<ApplicationUserDetailDto> applicationUserDetailDtoSet = mappedObjects.getApplicationUserDetailDtoSet();
        applicationUserDetailDtoSet.forEach(applicationUserDetailDto -> {
            try {
                auth
                        .inMemoryAuthentication()
                        .withUser(applicationUserDetailDto.getUsername())
                        .password(getPasswordEncoder().encode(applicationUserDetailDto.getPassword()))
                        .roles(applicationUserDetailDto.getRole());
            } catch (Exception e) {
                String format = String.format("Initiating the users from the user file has been failed by exception %s", e.getMessage());
                logger.error(format);
                logger.debug(Arrays.toString(e.getStackTrace()));
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/test/*").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout();
    }
}
