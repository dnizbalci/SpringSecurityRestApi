package com.works.config;

import com.works.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final UserService uService;
    public SecurityConfig(UserService uService) {
        this.uService = uService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uService).passwordEncoder(uService.encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http. httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/fault/list").hasRole("customer")
                .antMatchers("/fault/**").hasRole("mechanic")
                .antMatchers("/user/**").hasRole("mechanic")
                .antMatchers("/**").hasRole("admin")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
