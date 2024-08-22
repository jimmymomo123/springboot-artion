package com.jimmychiu.artion.configuration;

import com.jimmychiu.artion.enumType.Permission;
import com.jimmychiu.artion.service.BackendUserService;
import com.jimmychiu.artion.service.FrontendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ArtionSecurityConfiguration {

    @Autowired
    private FrontendUserService frontendUserService;

    @Autowired
    private BackendUserService backendUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //後台設置
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/admins").hasAuthority(Permission.ADMIN_LOGIN.getCode())

                )
                .userDetailsService(backendUserService)
                .csrf(csrf -> csrf.disable());

        //前台設置
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.POST,"/members").permitAll()
                                .requestMatchers("/members").hasAuthority(Permission.MEMBER_LOGIN.getCode())
                )
                .userDetailsService(frontendUserService)
                .csrf(csrf -> csrf.disable())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
