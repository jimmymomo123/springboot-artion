package com.jimmychiu.artion.service.impl;

import com.jimmychiu.artion.dto.LoginAndRegisterRequest;
import com.jimmychiu.artion.dto.MemberRequest;
import com.jimmychiu.artion.entity.Member;
import com.jimmychiu.artion.entity.Role;
import com.jimmychiu.artion.enumType.Permission;
import com.jimmychiu.artion.repository.MemberRepository;
import com.jimmychiu.artion.repository.RoleRepository;
import com.jimmychiu.artion.service.MemberService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void registry(LoginAndRegisterRequest request) {

        //加密密碼
        String hashPassword = passwordEncoder.encode(request.getPassword());

        // 创建 Role 实体
        Role role = new Role();
        role.setName(Permission.MEMBER_LOGIN.getCode());
        role.setPermissions(Permission.MEMBER_LOGIN.name());
        roleRepo.save(role);

        // 创建 Member 实体
        Member member = new Member();
        member.setUsername(request.getUsername());
        member.setPassword(hashPassword);
        member.setCreatedTime(LocalDateTime.now());
        member.setUpdatedTime(LocalDateTime.now());
        member.setRole(role);
        memberRepo.save(member);

    }

    @Transactional
    @Override
    public void updateMember(Long memberId, MemberRequest request) {
        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
        Integer roleId = request.getRoleId();

        //更新時間
        member.setUpdatedTime(LocalDateTime.now());

        // 更新成员信息
        if (request.getName() != null) {
            member.setName(request.getName());
        }
        if (request.getEmail() != null) {
            member.setEmail(request.getEmail());
        }
        if (request.getPhoneNum() != null) {
            member.setPhoneNum(request.getPhoneNum());
        }
        if (request.getGender() != null) {
            member.setGender(request.getGender());
        }
        if (request.getBirth() != null) {
            member.setBirth(request.getBirth());
        }
        if (roleId != null){
            Role role = roleRepo.findById(roleId).orElseThrow();
            member.setRole(role);
        }

        // 保存更新后的成员
        memberRepo.save(member);
    }

    @Transactional
    @Override
    public void deleteMember(Long memberId) {
        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
        memberRepo.delete(member);
    }

    @Override
    public void updateAvatar(Long memberId, String avatarUrl) {
        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
        member.setUpdatedTime(LocalDateTime.now());
        member.setMemberPic(avatarUrl);
        memberRepo.save(member);
    }

}
