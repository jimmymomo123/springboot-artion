package com.jimmychiu.artion.component;

import com.jimmychiu.artion.service.BackendUserService;
import com.jimmychiu.artion.service.FrontendUserService;
import com.jimmychiu.artion.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ObjectUtils;
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

        final String requestTokenHeader = request.getHeader("Authorization");
        String role = null;
        String username = null;

        //token驗證
        String token = null;

        if (!ObjectUtils.isEmpty(requestTokenHeader)){
            // 去掉 "Bearer " 前缀
            if (requestTokenHeader.startsWith("Bearer ")) {
                token = requestTokenHeader.substring(7);
            } else {
                logger.warn("JWT Token does not begin with Bearer String");
                token = requestTokenHeader;
            }

            if (token != null || !token.equals("null")){

                Map<String, Object> claims = JwtUtil.parseToken(token);
                role = (String) claims.get("role");
                username = (String) claims.get("username");

            }else {
                //終止
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"token is not validate");
                return;
            }
        }
        // else anonymousUser

        UserDetails userDetails = null;

        if (username != null){
            userDetails = role.startsWith("ADMIN_")
                    ? backendUserService.loadUserByUsername(username)
                    : frontendUserService.loadUserByUsername(username);
        }

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
