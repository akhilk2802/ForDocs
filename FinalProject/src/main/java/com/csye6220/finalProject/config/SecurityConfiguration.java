package com.csye6220.finalProject.config;

import com.csye6220.finalProject.security.JWTAuthenticationFilter;
import com.csye6220.finalProject.security.JwtAuthenticationEntryPoint;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration{

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfiguration(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
     SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/api/auth/showLogin").permitAll()
                        .requestMatchers("/api/**").permitAll()
//                        .requestMatchers("/api/post/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
//                        .requestMatchers("/api/votes/**").permitAll()
                        .requestMatchers("/api/post/all").permitAll()
//                        .requestMatchers("/api/comment/**").permitAll()
//                        .requestMatchers("/api/comment/bypost/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/api/auth/showLogin").defaultSuccessUrl("/home"))
                .logout(logout -> logout.logoutUrl("/api/auth/logout").logoutSuccessUrl("/api/auth/showLogin"))
                .exceptionHandling(expHandling -> expHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(ssnMgmt -> ssnMgmt.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    JWTAuthenticationFilter JWTAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }
}
