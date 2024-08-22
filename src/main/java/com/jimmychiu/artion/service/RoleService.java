package com.jimmychiu.artion.service;


import com.jimmychiu.artion.entity.Role;

public interface RoleService {
    Role findById(Integer roleId);

    void createRole(Role role);

    void updateRole(Integer roleId, Role roleDetails);

    void deleteRole(Integer roleId);
}
