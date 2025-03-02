package org.szelag.oauth2githubssl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Always use HTTPS @ Secure Headers for external APIs
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                /**
                 *  wymusza HTTPS dla wszystkich żądań - wymaga certyfikatu SSL/TLS
                 */
                .requiresChannel(channel -> channel.anyRequest().requiresSecure())

                /** 
                 * ustawia politykę zarządzania sesjami w Spring Security na IS_REQUIRED (default). 
                 * Tworzy sesję tylko jeśli jest potrzebna. Stosowane w aplikacjach z formularzami logowania MVC + OAuth2.0.
                */
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

                /**
                 *  wyłącza CSRF (dla API REST z JWT/OAuth2.0 jest zbędne!
                 *  dla app MVC z OAuth2.0 (nie REST API) jest potrzebne! 
                 *  Domyślnie CSRF jest w Springu włączone!
                 */
                // .csrf(csrf -> csrf.disable())

                // .headers(headers -> headers
                        // ustawia CSP ograniczając załadowane skrytpy, style, obrazy tylko do tej samej domeny (self)
                        // .contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self' https://localhost:8443"))
                        // blokuje ataki Cross-Site Scripting - metoda przestarzała, bo nowoczesne przeglądarki nie polegają już na X-XSS-Protection
                        //.xssProtection(xss -> xss.xssProtectionEnabled(true))
                        // .xssProtection(xss -> xss.block(true))
                        // .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)) // blokuje osadzanie strony w iframe (Clickjacking Protection)
                        /**
                         * the permissionsPolicy method does not exist in the HttpSecurity class. 
                         * You should remove or replace this line with a valid configuration.
                         */
                       // .permissionsPolicy(policy -> policy.policy("geolocation=(), microphone=(), camera=()")) // Blokowanie dostępu do kamery, mikrofonu

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll() // publiczne strony są dostepne
                        .anyRequest().authenticated()) // pozostałe strony wymagają logowania
                
                .oauth2Login(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true).permitAll())

                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutSuccessUrl("/")); // obsługa wylogowania
        return http.build();
    }
}