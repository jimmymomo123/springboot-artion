package com.jimmychiu.artion.service;

import com.jimmychiu.artion.entity.Admin;
import com.jimmychiu.artion.entity.Role;
import com.jimmychiu.artion.repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BackendUserService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //從資料庫查詢 Member 數據
        Admin admin = adminRepo.findByUsername(username);

        if (admin == null){
            throw new UsernameNotFoundException("Member not found for: " + username);
        }

        String dbUsername = admin.getUsername();
        String dbPassword = admin.getPassword();

        Role role = admin.getRole();
        List<GrantedAuthority> authorities = convertToAuthorities(role);
        return new User(dbUsername,dbPassword,authorities);

    }

    private List<GrantedAuthority> convertToAuthorities(Role role){
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (role != null) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
