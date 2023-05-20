package com.caesar.cipher.caesarcipher.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.Arrays;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity){
        try{
            final String policy = getCSPolicy();
            httpSecurity
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().headers().xssProtection().and().contentSecurityPolicy(getCSPolicy());
            httpSecurity.authorizeRequests().antMatchers("/actuator/**").permitAll();
        }catch (Exception e) {
            final String stackTrace = Arrays.toString(e.getStackTrace());
            //log console SLF4J and LOGBACK
        }
    }

    private String getCSPolicy() { return "script-src 'self'";}

}
