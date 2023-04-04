package com.example.GramGram.member.service;

import com.example.GramGram.base.rsData.RsData.RsData;
import com.example.GramGram.member.entity.Member;
import com.example.GramGram.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 아래 메서들들이 전부 select 임을 명시
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional
    public RsData<Member> join(String username, String password) {
        if (findByUsername(username).isPresent()) {
            return RsData.of("F-1", "해당 아이디(%s)는 이미 사용중입니다.".formatted(username));
        }

        Member member = Member.builder()
                            .username(username)
                            .password(passwordEncoder.encode(password))
                            .build();

        memberRepository.save(member);
        return RsData.of("S-1","회원가입 완료", member);
    }
}
