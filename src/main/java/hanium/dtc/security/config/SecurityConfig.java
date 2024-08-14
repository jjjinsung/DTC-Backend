package hanium.dtc.security.config;

import hanium.dtc.constant.AuthConstant;
import hanium.dtc.security.filter.JwtAuthenticationFilter;
import hanium.dtc.security.filter.JwtExceptionFilter;
import hanium.dtc.security.handler.JwtAuthenticationEntryPoint;
import hanium.dtc.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(registry ->
                        registry
                                .requestMatchers(AuthConstant.AUTH_WHITELIST).permitAll()
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtUtil),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(
                        new JwtExceptionFilter(),
                        JwtAuthenticationFilter.class)
                .getOrBuild();
    }
}