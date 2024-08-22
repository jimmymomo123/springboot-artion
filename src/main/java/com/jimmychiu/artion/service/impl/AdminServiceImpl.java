package com.jimmychiu.artion.service.impl;

import com.jimmychiu.artion.entity.Admin;
import com.jimmychiu.artion.repository.AdminRepository;
import com.jimmychiu.artion.service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepo;

    @Override
    @Transactional
    public void createAdmin(Admin admin) {
        admin.setCreatedTime(LocalDateTime.now());
        admin.setUpdatedTime(LocalDateTime.now());
        adminRepo.save(admin);
    }

    @Override
    @Transactional
    public void updateAdmin(Integer adminId, Admin adminDetails) {
        Admin admin = adminRepo.findById(adminId).orElseThrow(() -> new IllegalArgumentException("Admin not found with id: " + adminId));

        //更新時間
        admin.setUpdatedTime(LocalDateTime.now());

        //更新管理員信息
        if (adminDetails.getUsername() != null){
            admin.setUsername(adminDetails.getUsername());
        }
        if (adminDetails.getName() != null){
            admin.setName(adminDetails.getName());
        }
        if (adminDetails.getPersonnelCode() != null){
            admin.setPersonnelCode(adminDetails.getPersonnelCode());
        }
        if (adminDetails.getRole() != null){
            admin.setRole(adminDetails.getRole());
        }

        adminRepo.save(admin);
    }

    @Override
    @Transactional
    public void deleteMember(Integer adminId) {
        Admin admin = adminRepo.findById(adminId).orElseThrow(() -> new IllegalArgumentException("Admin not found with id: " + adminId));
        adminRepo.delete(admin);
    }
}
