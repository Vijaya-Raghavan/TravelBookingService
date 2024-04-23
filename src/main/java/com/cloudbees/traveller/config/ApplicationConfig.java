package com.cloudbees.traveller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class ApplicationConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring()
				.requestMatchers(new AntPathRequestMatcher("/**"));
	}
}
