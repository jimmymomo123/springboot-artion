package com.jimmychiu.artion.service;

import com.jimmychiu.artion.dto.LoginAndRegisterRequest;
import com.jimmychiu.artion.dto.MemberRequest;
import com.jimmychiu.artion.enumType.Permission;

import java.util.Set;

public interface MemberService {
    void registry(LoginAndRegisterRequest request);

    void updateMember(Long memberId, MemberRequest request);

    void deleteMember(Long memberId);

    void updateAvatar(Long memberId, String avatarUrl);

    Set<Permission> getPermissionByMemberId(Long memberId);
}
