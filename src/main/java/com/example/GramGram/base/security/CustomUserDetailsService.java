package com.example.GramGram.base.security;

import com.example.GramGram.member.entity.Member;
import com.example.GramGram.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional //해당 클래스를 상속한 특정 이름의 클래스 선언만 해주면
               // 스프링 시큐리티가 이 클래스를 통해 로그인 해준다
               // 스프링 시큐리티가 독자적으로 관리하는 테이블을 통하는 게 아닌 member 테이블 이용하게 된다
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username(%s) not found".formatted(username)));
        return new User(member.getUsername(), member.getPassword(), member.getGrantedAuthorities());
    }
}
