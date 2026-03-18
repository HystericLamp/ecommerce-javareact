package com.ecommerce.bcruz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig
{	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
	{

        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            .httpBasic(httpBasic -> httpBasic.disable())
            .formLogin(form -> form.disable());

        return http.build();
    }
	
	// no need for Users in this app
	@Bean
	public UserDetailsService userDetailsService() 
	{
	    return username -> {
	        throw new UsernameNotFoundException("No users");
	    };
	}
	
	@Bean
	public AuthenticationManager authenticationManager() 
	{
	    return authentication -> {
	        throw new UnsupportedOperationException("No authentication");
	    };
	}
}
