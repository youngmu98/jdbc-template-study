package com.yemmu.demomember.service;

import com.yemmu.demomember.entity.Member;
import com.yemmu.demomember.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Member saveMember(Member member){
        return memberRepository.save(member);
    }
}
