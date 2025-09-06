package com.ram.renew_service.api.renew_service.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfirOAuth2 {
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable()
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/public/**").permitAll()
	                .requestMatchers("/api/**").authenticated()
	            )
	            .oauth2ResourceServer(oauth2 -> oauth2.jwt()); // validates JWT

	        return http.build();
	    }

}
