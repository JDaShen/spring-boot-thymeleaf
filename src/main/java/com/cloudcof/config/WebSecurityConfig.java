package com.cloudcof.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

/**
 * Created by simon on 2016/8/21.
 */
@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.inMemoryAuthentication().withUser("admin").password("1").roles("ADMIN","USER")
                .and().withUser("user").password("1").roles("USER")
                .and().withUser("simon").password("1").roles("USER")
                .and().withUser("jzz").password("1").roles("USER")
                .and().withUser("feinin").password("1").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().loginPage("/login")
                .failureUrl("/login?error").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }
    @Bean
    public SpringSecurityDialect securityDialect(){
        return new SpringSecurityDialect();
    }
}