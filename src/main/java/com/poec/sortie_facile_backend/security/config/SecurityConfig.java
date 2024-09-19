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
import static com.poec.sortie_facile_backend.core.constants.RouteConstants.*;

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
                    .requestMatchers(HttpMethod.GET,
                            BASE_REGION + "/**",
                            BASE_DEPARTMENT + "/**",
                            BASE_CITY + "/**",
                            BASE_CATEGORY  +"/**",
                            BASE_ACTIVITY + "/**"
                    ).permitAll()

                    .requestMatchers(HttpMethod.GET,
                            BASE_BOOKING + "**",
                            BASE_PROFILE + "/**"
                    ).hasAnyRole(Role.ADMIN.name(), Role.USER.name())

                    .requestMatchers(HttpMethod.GET,
                            BASE_CONTACT + "/**"
                    ).hasRole(Role.ADMIN.name())

                    .requestMatchers(HttpMethod.POST,
                            "/api/v1/auth/**",
                            BASE_PROFILE + "/**",
                            BASE_CONTACT + "/**")
                    .permitAll()

                    .requestMatchers(HttpMethod.POST,
                            BASE_ACTIVITY + "/**",
                            BASE_BOOKING + "**"
                    ).hasAnyRole(Role.ADMIN.name(), Role.USER.name())

                    .requestMatchers(HttpMethod.POST,
                            BASE_REGION + "/**",
                            BASE_DEPARTMENT + "/**",
                            BASE_CITY + "/**"
                    ).hasRole(Role.ADMIN.name())

                    .requestMatchers(HttpMethod.PUT,
                            BASE_ACTIVITY + "/**",
                            BASE_PROFILE + "/**",
                            BASE_BOOKING + "**"
                    ).hasAnyRole(Role.ADMIN.name(), Role.USER.name())

                    .requestMatchers(HttpMethod.PUT,
                            BASE_CONTACT + "/**",
                            BASE_REGION + "/**",
                            BASE_DEPARTMENT + "/**",
                            BASE_CITY + "/**",
                            BASE_CATEGORY  +"/**"
                    ).hasRole(Role.ADMIN.name())

                    .requestMatchers(HttpMethod.DELETE,
                            BASE_ACTIVITY + "/**",
                            BASE_PROFILE + "/**",
                            BASE_BOOKING + "**"
                    ).hasAnyRole(Role.ADMIN.name(), Role.USER.name())

                    .requestMatchers(HttpMethod.DELETE,
                            BASE_CONTACT + "/**",
                            BASE_REGION + "/**",
                            BASE_DEPARTMENT + "/**",
                            BASE_CITY + "/**",
                            BASE_CATEGORY  +"/**"
                            ).hasRole(Role.ADMIN.name())

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
