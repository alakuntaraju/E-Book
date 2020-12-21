	package com.onpassive.admin.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SbSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Raaju")
                .password("{noop}12345678")
                .roles("USER")
                .and()
                .withUser("John")
                .password("{noop}87654321")
                .roles("MANAGER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.csrf() 
		.disable()
		.authorizeRequests().antMatchers("/","Book/BookList","swagger-ui.html").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
        .failureUrl("/login?error").permitAll().and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/?logout");
	}
	
}
