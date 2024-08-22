package com.jimmychiu.artion.controller;

import com.jimmychiu.artion.dto.AdminRequest;
import com.jimmychiu.artion.dto.Result;
import com.jimmychiu.artion.entity.Admin;
import com.jimmychiu.artion.entity.Role;
import com.jimmychiu.artion.service.AdminService;
import com.jimmychiu.artion.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public Result<List<Admin>> list(){
        return Result.success();
    }

    @PostMapping
    public Result createAdmin(@Valid @RequestBody AdminRequest request){
        //將 dto 轉 admin 實體
        Admin admin = convertToEntity(request);
        adminService.createAdmin(admin);
        return Result.success();
    }

    @PutMapping("{adminId}")
    public Result updateAdmin(@PathVariable Integer adminId,
                              @Valid @RequestBody AdminRequest request){
        adminService.updateAdmin(adminId,convertToEntity(request));
        return Result.success();
    }

    @DeleteMapping("{adminId}")
    public Result delete(@PathVariable Integer adminId){
        adminService.deleteMember(adminId);
        return Result.success();
    }

    private Admin convertToEntity(AdminRequest dto){
        Admin admin = new Admin();
        BeanUtils.copyProperties(dto,admin);

        Role role = roleService.findById(dto.getRoleId());
        admin.setRole(role);

        return admin;
    }
}
