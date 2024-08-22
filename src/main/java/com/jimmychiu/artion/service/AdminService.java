package com.jimmychiu.artion.service;

import com.jimmychiu.artion.entity.Admin;

public interface AdminService {
    void createAdmin(Admin admin);

    void updateAdmin(Integer adminId, Admin adminDetails);

    void deleteMember(Integer adminId);
}
