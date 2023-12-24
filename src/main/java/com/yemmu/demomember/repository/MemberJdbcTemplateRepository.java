package com.yemmu.demomember.repository;

import com.yemmu.demomember.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberJdbcTemplateRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberJdbcTemplateRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Member> findAll() {
        return null;
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.empty();
    }

    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public void delete(Member member) {

    }
}
