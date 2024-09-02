package com.thexyde.hris.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Bean
    // public WebSecurityCustomizer webSecurityCustomizer() {
    // return (web) -> web.ignoring().anyRequest();
    // }

    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**")
                        .authenticated()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults());

        // http
        // .csrf().disable()
        // .authorizeRequests()
        // .requestMatchers("/api/auth/**").permitAll()
        // .requestMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html",
        // "/webjars/**")
        // .authenticated()
        // .anyRequest().authenticated()
        // .and()
        // .httpBasic();

        http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtRequestFilter() {
        return new JwtAuthenticationFilter();
    }

    // @Bean
    // public PasswordEncoder passwordEncoderz() {
    // return new BCryptPasswordEncoder();
    // }

    // @Bean
    // public UserDetailsService userDetailsServicez() {
    // return new InMemoryUserDetailsManager(
    // User.withUsername("user")
    // .password(passwordEncoderz().encode("password"))
    // .build());
    // }
}
