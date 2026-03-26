package co.com.bancolombia.chocolatinazo.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security configuration for the REST API.
 *
 * - Disables CSRF (REST API with JWT doesn't need it)
 * - Makes the API stateless (no HTTP sessions — JWT replaces sessions)
 * - Defines which routes are public and which require authentication
 * - Adds the JWT filter before Spring's default authentication filter
 * - Enables @PreAuthorize for role-based access control
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity   // Enables @PreAuthorize on controller methods
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF — REST APIs use JWT tokens instead
                .csrf(AbstractHttpConfigurer::disable)

                // Allow H2 console to render in frames
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))

                // Configure route access rules
                .authorizeHttpRequests(auth -> auth
                        // Public routes (no authentication required)
                        .requestMatchers("/auth/**").permitAll()

                        // Swagger / OpenAPI routes
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()

                        // H2 Console
                        .requestMatchers("/h2/**").permitAll()

                        .requestMatchers("/error").permitAll()

                        // Actuator health endpoint
                        .requestMatchers("/actuator/**").permitAll()

                        // All other routes require authentication
                        .anyRequest().authenticated()
                )

                // Stateless sessions — JWT replaces HTTP sessions
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Return explicit status codes for authn/authz errors in REST APIs
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler()))

                // Add our JWT filter before Spring's default username/password filter
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/auth/**");
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) ->
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            Authentication authentication = org.springframework.security.core.context.SecurityContextHolder
                    .getContext()
                    .getAuthentication();

            if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                return;
            }

            response.sendError(HttpStatus.FORBIDDEN.value(), "Forbidden");
        };
    }
}
