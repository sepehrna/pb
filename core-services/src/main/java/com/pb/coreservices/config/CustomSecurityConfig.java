package com.pb.coreservices.config;

import com.pb.coreservices.controller.api.ApplicationUserApi;
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
import java.util.Objects;

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
        Objects.requireNonNull(applicationUserApi
                        .getApplicationUsers()
                        .getBody()).getApplicationUserDetails()
                .forEach(applicationUserDetailDto -> {
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
                .csrf()
                .disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/coupon/add-coupon").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/test/*").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout();
    }
}
