package com.yemmu.demomember.service;

import com.yemmu.demomember.entity.Member;
import com.yemmu.demomember.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
}
