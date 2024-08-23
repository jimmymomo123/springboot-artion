package com.jimmychiu.artion.component;

import com.jimmychiu.artion.service.BackendUserService;
import com.jimmychiu.artion.service.FrontendUserService;
import com.jimmychiu.artion.util.JwtUitl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private BackendUserService backendUserService;

    @Autowired
    private FrontendUserService frontendUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 忽略 Swagger 相关的请求
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/v3/api-docs") ||
                requestURI.startsWith("/swagger-ui") ||
                requestURI.startsWith("/swagger-resources") ||
                requestURI.startsWith("/configuration")) {
            filterChain.doFilter(request, response);
            return;
        }

        //token驗證
        String token = request.getHeader("Authorization");
        if (token == null || token.equals("null")){
            //終止
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"token is not validate");
            return;
        }

        Map<String, Object> claims = JwtUitl.parseToken(token);
        String role = (String) claims.get("role");
        String username = (String) claims.get("username");

        UserDetails userDetails = role.startsWith("ADMIN_")
                ? backendUserService.loadUserByUsername(username)
                : frontendUserService.loadUserByUsername(username);

        // Once we get the token validate it.
        if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // if token is valid configure Spring Security to manually set
            // authentication
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request,response);

    }
}
