package com.jimmychiu.artion.service;

import com.jimmychiu.artion.entity.Member;
import com.jimmychiu.artion.entity.Role;
import com.jimmychiu.artion.enumType.Permission;
import com.jimmychiu.artion.repository.MemberRepository;
import com.jimmychiu.artion.util.PermissionsConverter;
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
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FrontendUserService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //從資料庫查詢 Member 數據
        Member member = memberRepo.findByUsername(username);

        if (member == null){
            throw new UsernameNotFoundException("Member not found for: " + username);
        }

        String dbUsername = member.getUsername();
        String dbPassword = member.getPassword();

        Role role = member.getRole();
        Collection<? extends GrantedAuthority> authorities = convertToAuthorities(role);
        return new User(dbUsername,dbPassword,authorities);

    }

        private Collection<? extends GrantedAuthority> convertToAuthorities(Role role){
            Set<Permission> permissionSet = PermissionsConverter.stringToPermissionSet(role.getPermissions());

            return permissionSet.stream().map(permission -> new SimpleGrantedAuthority(permission.getCode()))
                    .collect(Collectors.toSet());
    }
}
