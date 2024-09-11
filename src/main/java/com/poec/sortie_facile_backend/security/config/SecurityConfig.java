package com.poec.sortie_facile_backend.security.config;

import com.poec.sortie_facile_backend.security.handler.AccessDeniedHandler;
import com.poec.sortie_facile_backend.exceptions.JwtAuthenticationErrors;
import com.poec.sortie_facile_backend.security.filter.JwtAuthenticationFilter;
import com.poec.sortie_facile_backend.core.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationErrors jwtAuthenticationErrors;
    private final AccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //use the CORS configuration of our implementation
            .cors(cors -> cors.configure(http))

            // disable session management
            .sessionManagement(session -> session .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // disable CSRF
            .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringRequestMatchers("/**").disable())

            // Liste des routes protégées / non protégées
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()

                    .requestMatchers(
                            "/api/v1/contact/**",
                            "/api/v1/region/**",
                            "/api/v1/department/**",
                            "/api/v1/city/**",
                            "/api/v1/city/**",
                            "/api/v1/category/**",
                            "/api/v1/activity/**",
                            "/api/v1/profile/**",
                            "api/v1/booking/**")
                    .permitAll()

                    .requestMatchers(HttpMethod.GET, "/api/v1/demo/all").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                    .requestMatchers(HttpMethod.GET, "/api/v1/demo/admins-only").hasRole(Role.ADMIN.name())
                    .requestMatchers(HttpMethod.GET, "/api/v1/demo/users-only").hasRole(Role.USER.name())

                    .requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasRole(Role.ADMIN.name())

                    .anyRequest().authenticated()
            )

            // authentication errors
            .exceptionHandling((exception) ->  exception
                    .authenticationEntryPoint(jwtAuthenticationErrors)
                    .accessDeniedHandler(accessDeniedHandler)
            )

            // specify the authentication provider used
            .authenticationProvider(authenticationProvider)

            // add the JWT authentication filter
            // before the UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();

    }
}
