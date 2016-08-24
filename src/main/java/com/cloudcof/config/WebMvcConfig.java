package com.cloudcof.config;

/**
 * Created by simon on 2016/8/21.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        //ViewName cannot start with "/"
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/starter").setViewName("starter");
        registry.addViewController("/hello/test").setViewName("test");
        registry.addViewController("/account-manage").setViewName("subpages/account-manage");
        registry.addViewController("/binding").setViewName("subpages/binding");
        registry.addViewController("/coffee-type").setViewName("subpages/coffee-type");
        registry.addViewController("/coffee-type-edit").setViewName("subpages/coffee-type-edit");
        registry.addViewController("/my-coffee-maker").setViewName("subpages/my-coffee-maker");
        registry.addViewController("/reports").setViewName("subpages/reports");
    }
}