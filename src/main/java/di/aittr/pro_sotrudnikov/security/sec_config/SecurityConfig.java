package di.aittr.pro_sotrudnikov.security.sec_config;

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

    @Bean
    public BCryptPasswordEncoder encoder(){
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
                                .requestMatchers(HttpMethod.GET, "/products/all").permitAll()
                                .requestMatchers(HttpMethod.GET, "/products/").hasAnyRole(ADMIN_ROLE, USER_ROLE)
                                .requestMatchers(HttpMethod.POST, "/products").hasRole(ADMIN_ROLE)
                                .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/refresh").permitAll()
                                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                                .requestMatchers(HttpMethod.GET, "/hello").permitAll()
                                .requestMatchers(HttpMethod.POST, "/files").hasRole(ADMIN_ROLE)
                                .requestMatchers(HttpMethod.POST, "/pokupatel").hasRole(ADMIN_ROLE)
                                .requestMatchers(HttpMethod.PUT, "/pokupatel/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)
                                .anyRequest().authenticated()

                )
//                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
