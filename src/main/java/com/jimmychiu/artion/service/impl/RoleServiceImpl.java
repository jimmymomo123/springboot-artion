package com.jimmychiu.artion.service.impl;

import com.jimmychiu.artion.entity.Role;
import com.jimmychiu.artion.repository.RoleRepository;
import com.jimmychiu.artion.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public Role findById(Integer roleId) {
        return roleRepo.findById(roleId).orElseThrow();
    }

    @Override
    @Transactional
    public void createRole(Role role) {
        role.setCreatedTime(LocalDateTime.now());
        role.setUpdatedTime(LocalDateTime.now());
        roleRepo.save(role);
    }

    @Override
    @Transactional
    public void updateRole(Integer roleId, Role roleDetails) {
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        role.setUpdatedTime(LocalDateTime.now());
        if (roleDetails.getName() != null){
            role.setName(roleDetails.getName());
        }
        if (roleDetails.getPermissions() != null){
            role.setPermissions(roleDetails.getPermissions());
        }
        roleRepo.save(role);
    }

    @Override
    @Transactional
    public void deleteRole(Integer roleId) {
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        roleRepo.delete(role);
    }
}
