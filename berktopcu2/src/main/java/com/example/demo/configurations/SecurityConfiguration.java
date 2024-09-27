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
/*v1
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth  // "/signup.html", "/signup",
                .requestMatchers("/auth", "/subs", "/", "/index.html", "/signup.html", "/login.html", "/css/**", "/js/**", "/submit-signup").permitAll() 
                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
        .addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
*/
/*v2
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth  
                .requestMatchers("/auth", "/subs", "/edit", "/", "/edit.html", "/hompepage.html" , "/index.html", "/signup.html", "/login.html", "/css/**", "/js/**", "/submit-signup").permitAll() 
                .anyRequest().authenticated() 
            )
            .formLogin()
                .loginPage("/login.html")
                .permitAll()
            .and()
            .logout()
                .permitAll()
            .and()
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
   */
/* Fix email button to take user to login.html
 * Edit users service works, implement edit.html to appear after homepage.html
 * implement log collector
 * 
 */
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth", "/subs", "/articles/**", "/login.html", "/signup.html", "/index.html", "/homepage.html", "/content.html", "/edit**", "/css/**", "/js/**").permitAll()  // Allow login/signup resources
                //.requestMatchers("/homepage.html").authenticated()  // Restrict homepage to authenticated users
                .anyRequest().authenticated()
            )
            /*.formLogin()
                .loginPage("/homepage.html")
                .permitAll()
            .and()
            .logout()
                .permitAll()
            .and()*/
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

