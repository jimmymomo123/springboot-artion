package com.jimmychiu.artion.service;

import com.jimmychiu.artion.dto.LoginAndRegisterRequest;
import com.jimmychiu.artion.dto.MemberRequest;

public interface MemberService {
    void registry(LoginAndRegisterRequest request);

    void updateMember(Long memberId, MemberRequest request);

    void deleteMember(Long memberId);
}
