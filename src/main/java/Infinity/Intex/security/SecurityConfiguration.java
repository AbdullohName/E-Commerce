package Infinity.Intex.security;

import Infinity.Intex.security.jwt.JwtFilter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@OpenAPIDefinition(info = @Info(title = "Intex",
        description = "E-Commerce",
        termsOfService = "url",
        contact = @Contact(name = "Abdulloh Qosimov",
                email = "abdullohqwe@gmail.com",
                url = "@HackersintheWorld")),
        security = @SecurityRequirement(name = "Authorization"))
public class SecurityConfiguration {
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource()).and()
                .requiresChannel()
                .and()
//                .authorizeHttpRequests()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/admin/**").permitAll()
                .antMatchers(HttpMethod.GET,"/product/**").permitAll()
                .antMatchers(HttpMethod.POST,"/order/**").permitAll()
                .antMatchers(HttpMethod.POST,"/consultation").permitAll()
                .antMatchers(HttpMethod.GET,"/category/**").permitAll()
                .antMatchers(HttpMethod.GET,"/info/**").permitAll()
                .antMatchers(
                        "/swagger-ui/",
                        "/api/mail/",
                        "/v2/api-docs",
                        "/swagger-resources/",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/"

                ).permitAll()
                .anyRequest()
                .permitAll()
//                .authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
