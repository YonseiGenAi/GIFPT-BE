package com.gifpt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain security(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/healthz",
                         "/actuator/health",
                         "/actuator/health/**").permitAll()  // 헬스 오픈
        .anyRequest().authenticated()
      )
      .httpBasic(Customizer.withDefaults()); // 나머지는 기본 인증
    return http.build();
  }
}
