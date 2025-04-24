package di.aittr.pro_sotrudnikov.security.sec_config;

import di.aittr.pro_sotrudnikov.security.sec_filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final String ADMIN_ROLE = "ADMIN";
    private final String USER_ROLE = "USER";
    private final String DEVELOPER_ROLE = "DEVELOPER";

    private final TokenFilter filter;

    public SecurityConfig(TokenFilter filter) {
        this.filter = filter;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(x -> x
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x -> x
                        .requestMatchers(HttpMethod.POST, "/sotrudniki").hasRole(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.GET, "/sotrudniki").permitAll()
                        .requestMatchers(HttpMethod.GET, "/sotrudniki/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/sotrudniki").hasRole(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.DELETE, "/sotrudniki/{id}").hasRole(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.DELETE, "/sotrudniki").hasRole(ADMIN_ROLE)

                        .requestMatchers(HttpMethod.POST, "/proekti").hasRole(DEVELOPER_ROLE)
                        .requestMatchers(HttpMethod.GET, "/proekti").permitAll()
                        .requestMatchers(HttpMethod.GET, "/proekti/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/proekti").hasRole(DEVELOPER_ROLE)
                        .requestMatchers(HttpMethod.DELETE, "/proekti/{id}").hasRole(DEVELOPER_ROLE)
                        .requestMatchers(HttpMethod.DELETE, "/proekti").hasRole(DEVELOPER_ROLE)
                        .requestMatchers(HttpMethod.PUT, "/proekti/").hasAnyRole(DEVELOPER_ROLE, USER_ROLE)
                        .requestMatchers(HttpMethod.DELETE, "/proekti/{proektId}/").hasAnyRole(DEVELOPER_ROLE, USER_ROLE)
                        .requestMatchers(HttpMethod.DELETE, "/proekti/{proektId}").hasRole(DEVELOPER_ROLE)

                        .requestMatchers(HttpMethod.POST, "/zadaci").hasAnyRole(DEVELOPER_ROLE, USER_ROLE)
                        .requestMatchers(HttpMethod.GET, "/zadaci").permitAll()
                        .requestMatchers(HttpMethod.GET, "/zadaci/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/zadaci").hasAnyRole(DEVELOPER_ROLE, USER_ROLE)
                        .requestMatchers(HttpMethod.DELETE, "/zadaci/{id}").hasAnyRole(DEVELOPER_ROLE, USER_ROLE)
                        .requestMatchers(HttpMethod.DELETE, "/zadaci").hasAnyRole(DEVELOPER_ROLE, USER_ROLE)
                        .requestMatchers(HttpMethod.PUT, "/zadaci/").hasRole(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.DELETE, "/zadaci/{zadaciId}/").hasRole(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.DELETE, "/zadaci/{zadaciId}").hasRole(ADMIN_ROLE)

                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/refresh").permitAll()

                        .anyRequest().authenticated()

                )
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}
