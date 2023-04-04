package com.example.GramGram.member.controller;

import com.example.GramGram.base.rsData.RsData.RsData;
import com.example.GramGram.member.entity.Member;
import com.example.GramGram.member.service.MemberService;
import com.example.GramGram.standard.util.Ut.Ut;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Getter
    @AllArgsConstructor
    public static class JoinForm{
        @NotBlank
        @Size(min = 4, max = 30)
        private final String username;
        @NotBlank
        @Size(min = 4, max = 30)
        private final String password;
    }

    @PreAuthorize("isAnonymous()") // 로그인 안한 사용자만 들어올 수 있다
    @GetMapping("/join")
    public String showJoin() {
        return "user/member/join";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/join")
    public String join(@Valid JoinForm joinForm) {
        RsData<Member> joinRs = memberService.join(joinForm.getUsername(), joinForm.getPassword());
        if(joinRs.isFail())
            return "common/js";

        String msg = joinRs.getMsg() + "\n로그인 후 이용해주세요.";
        return "redirect:/member/login?msg="+ Ut.url.encode("회원가입이 완료되었습니다.");
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String showLogin() {
        return "user/member/login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public String showMe() {
        return "user/member/me";
    }
}
