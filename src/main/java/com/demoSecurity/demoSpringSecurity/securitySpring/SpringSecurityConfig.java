package com.demoSecurity.demoSpringSecurity.securitySpring;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

@Log4j2
@Configuration
@EnableWebFluxSecurity
public class SpringSecurityConfig {
    private static final String[] AUTH_WHITELIST = {
            "/login",
            "/logout",
            "/favicon.ico",
            "/images/**",
            "/css/**",
            "/js/**",
            "/resource/**",
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
        return httpSecurity.authorizeExchange()
                .pathMatchers(AUTH_WHITELIST)
                .permitAll()
                .pathMatchers("/**")
                .authenticated()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .loginPage("/login")
                .authenticationManager(authenticationManager())
                .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/"))
                .authenticationFailureHandler(new SelfAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .requestCache()
                .disable()
                .build();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        return new SelfAuthenticationManager();
    }
}
