package com.Application.HelloWorld.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityWebConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                request->request.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public UserDetailsService getUserDetailService() {
        UserDetails praveen = User
                .withUsername("Praveen")
                .password("{noop}Shanthi@2709")
                .roles("USER")
                .build();
        UserDetails naveen=User.
                withUsername("Naveen")
                .password("{noop}Kumar@2000")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(praveen,naveen);
    }
}
