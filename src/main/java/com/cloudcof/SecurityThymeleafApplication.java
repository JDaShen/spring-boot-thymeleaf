package com.cloudcof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SecurityThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityThymeleafApplication.class, args);
	}

	@Configuration
	@EnableWebSecurity
	protected class WebSecurityConfig extends WebSecurityConfigurerAdapter{
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			//super.configure(auth);
			auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN","USER")
					.and().withUser("user").password("user").roles("USER")
					.and().withUser("simon").password("123456").roles("USER");
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
					.csrf().disable()
					.httpBasic().disable();
		}
	}

	@Configuration
	protected class WebMvcConfig extends WebMvcConfigurerAdapter{
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			//super.addViewControllers(registry);
			registry.addViewController("/").setViewName("home");
			registry.addViewController("/home").setViewName("/home");
			registry.addViewController("/login").setViewName("login");
			registry.addViewController("/hello").setViewName("hello");
			registry.addViewController("/starter").setViewName("starter");
			registry.addViewController("/hello/test").setViewName("test");
		}
	}
}
