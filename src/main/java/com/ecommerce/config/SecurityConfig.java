package com.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.ecommerce.jwt.AuthEntryPoint;
import com.ecommerce.jwt.JwtAuthenticationFilter;


import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	public static final String AUTHENTICATE = "/authenticate";
	public static final String REGISTER = "/register";
	public static final String REFRESH_TOKEN = "/refreshToken";
	public static final String SWAGGER_UI = "/swagger-ui/**";
	public static final String SWAGGER_UI_HTML = "/swagger-ui/index.html";
	public static final String SWAGGER_UI_REDIRECT = "/swagger-ui.html";
	public static final String SWAGGER_UI_REDIRECT_HTML = "/swagger-ui/index.html";
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests(request -> 
			request.requestMatchers(
				AUTHENTICATE, 
				REGISTER, 
				REFRESH_TOKEN, 
				SWAGGER_UI, 
				SWAGGER_UI_HTML, 
				SWAGGER_UI_REDIRECT, 
				SWAGGER_UI_REDIRECT_HTML,
				"/v3/api-docs/**"
			).permitAll()
			.anyRequest()
			.authenticated())
		.exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
