package gl2.example.PrAR.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService users() {
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("ADMIN")
                        .build(),
                User.withDefaultPasswordEncoder()
                        .username("client")
                        .password("password")
                        .roles("CLIENT")
                        .build()
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //public
                        .requestMatchers(HttpMethod.GET, "/api/voitures").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/voitures/**").permitAll()
                        //admin
                        .requestMatchers(HttpMethod.POST, "/api/voitures/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/voitures/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/voitures/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/voitures/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/locations").hasRole("ADMIN")
                        //users
                        .requestMatchers("/api/users/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {});
        return http.build();
    }
}
