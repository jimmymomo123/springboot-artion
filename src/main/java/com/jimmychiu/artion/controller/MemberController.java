package com.jimmychiu.artion.controller;

import com.jimmychiu.artion.dto.LoginAndRegisterRequest;
import com.jimmychiu.artion.dto.MemberRequest;
import com.jimmychiu.artion.dto.Result;
import com.jimmychiu.artion.entity.Member;
import com.jimmychiu.artion.repository.MemberRepository;
import com.jimmychiu.artion.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepo;

    @GetMapping
    public Result<List<Member>> list(){
        return Result.success();
    }

    @PostMapping
    public Result registry(@Valid @RequestBody LoginAndRegisterRequest request){
        //先查詢有無該使用者
        String username = request.getUsername();
        Member byUsername = memberRepo.findByUsername(username);
        if (byUsername != null){
            return Result.error(username + " 已被註冊！");
        }
        //註冊
        memberService.registry(request);
        return Result.success();
    }

    @PutMapping("/{memberId}")
    public Result update(@PathVariable Long memberId,
                         @Valid MemberRequest request){

        memberService.updateMember(memberId,request);
        return Result.success();
    }

    @DeleteMapping("{memberId}")
    public Result delete(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return Result.success();
    }
}
