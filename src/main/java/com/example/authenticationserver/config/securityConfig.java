package com.example.authenticationserver.config;


import com.example.authenticationserver.security.EmailPasswordAuthenticationFilter;
import com.example.authenticationserver.security.EmailPasswordAuthenticationProvider;
import com.example.authenticationserver.security.jwt.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    EmailPasswordAuthenticationProvider emailPasswordAuthenticationProvider;
    @Autowired
    @Lazy
    EmailPasswordAuthenticationFilter emailPasswordAuthenticationFilter;

    @Autowired
    JwtValidationFilter jwtValidationFilter;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(emailPasswordAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilterAt(emailPasswordAuthenticationFilter, BasicAuthenticationFilter.class);
        http.addFilterAfter(jwtValidationFilter,BasicAuthenticationFilter.class);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager()
            throws Exception {
        return super.authenticationManager();
    }
}
