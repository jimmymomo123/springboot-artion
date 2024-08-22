package com.jimmychiu.artion.controller;


import com.jimmychiu.artion.dto.Result;
import com.jimmychiu.artion.dto.RoleRequest;
import com.jimmychiu.artion.entity.Role;
import com.jimmychiu.artion.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jimmychiu.artion.enumType.Permission;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public Result<List<Role>> list() {
//        List<Role> roles = roleService.findAll();
//        return Result.success(roles);
        return Result.success();
    }

    @PostMapping
    public Result createRole(@Valid @RequestBody RoleRequest request) {
        Role role = convertToEntity(request);
        roleService.createRole(role);
        return Result.success();
    }

    @PutMapping("{roleId}")
    public Result updateRole(@PathVariable Integer roleId,
                             @Valid @RequestBody RoleRequest request) {
        roleService.updateRole(roleId, convertToEntity(request));
        return Result.success();
    }

    @DeleteMapping("{roleId}")
    public Result delete(@PathVariable Integer roleId) {
        roleService.deleteRole(roleId);
        return Result.success();
    }

    private Role convertToEntity(RoleRequest dto){
        Role role = new Role();
        role.setName(dto.getName());
        if (dto.getPermissions() != null){
            String permissionsString = dto.getPermissions().stream()
                    .map(Permission::name)
                    .collect(Collectors.joining(","));
            role.setPermissions(permissionsString);
        }

        return role;
    }

}
