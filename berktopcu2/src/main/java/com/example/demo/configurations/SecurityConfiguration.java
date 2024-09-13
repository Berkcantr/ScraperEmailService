package com.example.demo.configurations;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.models.UserRepository;
import com.example.demo.utils.JwtFilterRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private JwtFilterRequest jwtFilterRequest;
	
    private final UserRepository userRepository;
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/subs", "/auth").permitAll() 
                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
        .addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username);
          //  .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    	 return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}


