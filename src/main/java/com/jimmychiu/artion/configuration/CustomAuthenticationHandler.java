package com.jimmychiu.artion.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmychiu.artion.entity.Admin;
import com.jimmychiu.artion.entity.Member;
import com.jimmychiu.artion.enumType.Permission;
import com.jimmychiu.artion.repository.AdminRepository;
import com.jimmychiu.artion.repository.MemberRepository;
import com.jimmychiu.artion.util.JwtUitl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private JwtUitl jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 創建 Token 的載荷
        Map<String, Object> claims = new HashMap<>();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        //如果是管理員
        if (role.startsWith("ADMIN_")){
            Admin admin = adminRepo.findByUsername(username);
            claims.put("role",role);
            claims.put("personnelCode",admin.getPersonnelCode());
        }else {
            Member member = memberRepo.findByUsername(username);
            claims.put("role",role);
            claims.put("username", member.getUsername());
        }
        // 產token
        String token = jwtUtil.generateToken(claims);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        objectMapper.writeValue(response.getWriter(), "{ \"token\": \"" + token + "\" }");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        objectMapper.writeValue(response.getWriter(), "{ \"error\": \"" + exception.getMessage() + "\" }");
    }


}
