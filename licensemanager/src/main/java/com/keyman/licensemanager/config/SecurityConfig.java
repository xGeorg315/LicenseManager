package com.keyman.licensemanager.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(req -> {
                    req
                        .antMatchers("/auth/**").permitAll() 
                        .antMatchers("/auth/admin/register").permitAll()
                        .antMatchers("/**").hasAnyAuthority("ADMIN","USER")
                        .antMatchers("/customer/admin/**").hasAuthority("ADMIN")
                        .antMatchers("/user/admin/**").hasAuthority("ADMIN")
                        .antMatchers("/contracts/**").hasAnyAuthority("ADMIN","USER")
                        .antMatchers("/contracts/admin/**").hasAuthority("ADMIN")
                        .antMatchers("/instance/admin/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated();
                    })
                .formLogin(Customizer.withDefaults())
                .build();                             
    }

    @Bean
    @Autowired
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

