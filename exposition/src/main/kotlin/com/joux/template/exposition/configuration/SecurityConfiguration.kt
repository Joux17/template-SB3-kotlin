package com.joux.template.exposition.configuration

import com.joux.template.exposition.security.JwtAuthenticationFilter
import jakarta.servlet.DispatcherType.FORWARD
import jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN
import jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.*
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true) // Permet d'utiliser @Authorized ou @Secured
class SecurityConfiguration(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
) {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .csrf(CsrfConfigurer<HttpSecurity>::disable)
            .exceptionHandling { configurer ->
                configurer
                    .authenticationEntryPoint {
                        // Si pas authentifié alors on retourne une 401
                            request,
                            response,
                            authException,
                        ->
                        response.sendError(SC_UNAUTHORIZED)
                    }
//                    .addFilterBefore(jwtAuthenticationFilter)
                    .accessDeniedHandler {
                        // Si pas autorisé à consulter ce endpoint alors on retourne une 403
                            request,
                            response,
                            accessDeniedException,
                        ->
                        response.sendError(SC_FORBIDDEN)
                    }

            }
            .httpBasic(org.springframework.security.config.Customizer.withDefaults())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement { customizer -> customizer.sessionCreationPolicy(STATELESS) }
            .authorizeHttpRequests { requests ->
                requests
                    .dispatcherTypeMatchers(FORWARD).permitAll()
                    .requestMatchers(
                        "/error"
                    ).permitAll()

                    .requestMatchers(
                        POST,
                        "/api/users/login"
                    ).permitAll() // white liste des endpoints en POST autorisés sans être authentifié

                    .requestMatchers(
                        GET,
                        "/api/users/secret"
                    ).hasRole("ADMIN")

                    .requestMatchers(
                        GET,
                        "/api/users/traitementAdministrateur"
                    ).hasRole("ADMIN")

                    .requestMatchers(OPTIONS).permitAll()
                    .anyRequest().authenticated()
            }

        return httpSecurity.build()
    }
}