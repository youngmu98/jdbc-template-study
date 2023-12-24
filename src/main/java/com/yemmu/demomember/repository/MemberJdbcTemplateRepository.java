package com.yemmu.demomember.repository;

import com.yemmu.demomember.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    private final RowMapper<Member> rowMapper = (rs, rowNum) -> {
        Member member = new Member();
        member.setMemberId(rs.getLong("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPhone(rs.getString("phone"));
        return member;
    };

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM member", rowMapper);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return jdbcTemplate.query("SELECT * FROM member WHERE member_id = ?",
                new Object[]{memberId},
                rowMapper).stream().findFirst();
    }

    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public void delete(Member member) {

    }
}
