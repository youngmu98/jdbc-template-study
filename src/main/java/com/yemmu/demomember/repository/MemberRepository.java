package com.yemmu.demomember.repository;

import com.yemmu.demomember.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    List<Member> findAll();

    Optional<Member> findById(Long memberId);

    Member save(Member member);

    void delete(Member member); // 왜 객체를 parameter로 받는지 생각해보기
}
