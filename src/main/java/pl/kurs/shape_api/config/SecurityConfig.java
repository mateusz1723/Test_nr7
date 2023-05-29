package pl.kurs.shape_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsService appUserService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.appUserService = userDetailsService;
    }


    @Primary
    @Bean
    public AuthenticationManagerBuilder configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(appUserService).passwordEncoder(passwordEncoder());
        return auth;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/shapes/**/changes").hasAnyRole("ADMIN", "CREATOR")
                .antMatchers(HttpMethod.PUT, "/api/v1/shapes/**").hasAnyRole("ADMIN", "CREATOR")
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
