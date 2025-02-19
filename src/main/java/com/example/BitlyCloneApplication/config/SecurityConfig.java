package com.example.BitlyCloneApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

@Bean
public SecurityFilterChain getAuthenticationFilter(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.csrf(AbstractHttpConfigurer::disable)
    .authorizeHttpRequests(auth->
             auth
                  .requestMatchers("/api/auth/public/register").permitAll()
                     .requestMatchers("/api/auth/public/login").permitAll()
                    .requestMatchers("/api/url/shorturl").permitAll()
                     .requestMatchers("/css/**","/js/**","/images/**").permitAll()
                     .requestMatchers("/routes").permitAll()
            .anyRequest()
            .authenticated())
          .formLogin(login->login
                  //  .defaultSuccessUrl("/api/auth/public/register",true)
                    .permitAll())
            .build();
}
  @Bean
  public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration configuration) throws Exception {
  return configuration.getAuthenticationManager();
  }

    @Bean
    public PasswordEncoder getNoopPasswordEncoder(){
    return new BCryptPasswordEncoder();
    }

}









//authentication filter
//authentication manager
//authentication provider
//userdetails service and nooppassword encoder
//security context
//
//cretae user details service implements userservice,


// today tasks
//register users settings
//login
//security apply to all endpoints
