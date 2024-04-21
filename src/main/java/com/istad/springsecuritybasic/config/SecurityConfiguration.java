package com.istad.springsecuritybasic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("mr.admin")
                .password(passwordEncoder().encode("12345"))
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.builder()
                .username("mr.normaluser")
                .password(passwordEncoder().encode("12345")).roles("USER")
                .build();
        UserDetails user3 = User.builder()
                .username("mr.author")
                .password(passwordEncoder().encode("12345"))
                .roles("AUTHOR")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filter (HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(
                        (auth) -> auth.requestMatchers("/login", "/sign-up")
                                .permitAll()
                                .requestMatchers("api/admins/**")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"api/articles/**","/api/...")
//                                    .hasRole("USER")
                                .hasAnyRole("USER","AUTHOR","ADMIN")
                                .requestMatchers( "api/articles/**")
                                .hasRole("AUTHOR")
                                .anyRequest()
                                .authenticated()

                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
