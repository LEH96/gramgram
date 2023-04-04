package com.example.GramGram.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) //@CreatedDate, @LastModifiedDate 작동 허용
@ToString
@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;
    @Column(unique = true)
    private String username;
    private String password;

    public List<? extends GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        //모든 회원에게 member 권한 부여
        grantedAuthorities.add(new SimpleGrantedAuthority("member"));

        //username이 admin이면 admin 권한 추가 부여
        if("admin".equals(username)) {
            grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
        }
        return grantedAuthorities;
    }
}