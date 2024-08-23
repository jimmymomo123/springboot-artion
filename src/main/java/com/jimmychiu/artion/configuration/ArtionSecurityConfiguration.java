package com.jimmychiu.artion.configuration;

import com.jimmychiu.artion.component.JwtRequestFilter;
import com.jimmychiu.artion.enumType.Permission;
import com.jimmychiu.artion.service.BackendUserService;
import com.jimmychiu.artion.service.FrontendUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class ArtionSecurityConfiguration {

    @Autowired
    private FrontendUserService frontendUserService;

    @Autowired
    private BackendUserService backendUserService;

    @Autowired
    private CustomAuthenticationHandler customAuthenticationHandler;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 放行 Swagger 和静态资源
                .authorizeHttpRequests(authz ->
                                authz
                                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html"," /configuration/**","/swagger-resources/**","/swagger-resources").permitAll() // 放行 Swagger 相关路径
                                        .requestMatchers(HttpMethod.POST, "/members").permitAll()
                                        .requestMatchers("/members").hasAuthority(Permission.MEMBER_LOGIN.getCode())
                                        .requestMatchers("/admins").hasAuthority(Permission.ADMIN_LOGIN.getCode())
                                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // 如果不使用 CSRF，禁用 CSRF 保护
                .formLogin(formLogin ->
                        formLogin
                                .successHandler(customAuthenticationHandler)
                                .loginPage("/login.html")
                                .permitAll()
                )
                .userDetailsService(frontendUserService)
                .userDetailsService(backendUserService)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
