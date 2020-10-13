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
//     private static final String[] AUTH_WHITELIST_2 = {

//     }

    @Bean
//     @Order(value = 1)
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
    //---------------------------COMMENTED LINE FOR TWO SPRING SECURITY WITHIN A PROJECT (ORDER IS USED)---------------------------- 
    // ORDER 1 WILL ALLOW THE MATCHING PATH LAST ONE FOR ALL.
//    @Bean
//    @Order(value = 1)
//    public SecurityWebFilterChain securityWebFilterChain2(ServerHttpSecurity http) {
//        http
//                .securityMatcher(ServerWebExchangeMatchers.pathMatchers("/SOME_PATH/**"));
//
//                http
//                        .authorizeExchange()
//                .pathMatchers(AUTH_WHITELIST_FOR_WFM)
//                .permitAll()
//                .and()
//                .csrf()
//                .disable()
//                .formLogin()
//                .loginPage("/{PATH}/login")
//                .authenticationManager(authenticationManager2())
//                .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/wfm/agent/home"))
//                .authenticationFailureHandler(new AuthenticationFailureHandler2());
//
//        final SecurityWebFilterChain build = http.build();
//        build.getWebFilters().collectList().subscribe(
//                webFilters -> {
//                    for (WebFilter filter : webFilters) {
//                        if (filter instanceof AuthenticationWebFilter) {
//                            AuthenticationWebFilter awf = (AuthenticationWebFilter) filter;
//                            awf.setAuthenticationConverter(new SimpleAuthenticationFilter());
//                        }
//                    }
//                }
//        );
//        return build;
//    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        return new SelfAuthenticationManager();
    }
    
//     @Bean
//     public ReactiveAuthenticationManager authenticationManager2() {
//         return new AuthenticationManager_second();
//     }
}
