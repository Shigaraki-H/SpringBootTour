package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
 * EnableWebSecurityはSpring Securityのカスタマイズ用の設定アノテーション
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(
				authorize -> authorize
					.requestMatchers("/", "/staffLogin","/user/tripReserve","/user/reserveReview","/user/inputReserveView/**","/user/reserveCompleted","/css/**","/img/**","/js/**").permitAll()
					.requestMatchers("/search/desc","/search/asc","/search/**").authenticated()
					.anyRequest().authenticated())
			.formLogin(
				login -> login.loginPage("/staffLogin")
				.usernameParameter("loginId")
				.defaultSuccessUrl("/user/list"));
		
		return http.build();
	}

}


