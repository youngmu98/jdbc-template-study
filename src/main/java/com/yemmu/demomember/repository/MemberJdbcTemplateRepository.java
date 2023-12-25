package com.yemmu.demomember.repository;

import com.yemmu.demomember.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
        if(member.getMemberId() == null){
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO member(name, email, phone) VALUES ( ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, member.getName());
                ps.setString(2, member.getEmail());
                ps.setString(3, member.getPhone());
                return ps;
                    }, keyHolder);
            Number key = keyHolder.getKey();

            if(key != null){
                member.setMemberId(key.longValue());
            }else{
                throw new RuntimeException("오류");
            }
        }else {
            jdbcTemplate.update("UPDATE member set name = ?, email = ?, phone = ? WHERE member_id = ?",
                    member.getName(), member.getEmail(), member.getPhone(), member.getMemberId());
        }

        return member;
    }

    @Override
    public void delete(Long memberId) {
        jdbcTemplate.update("DELETE FROM member where member_id = ?",memberId);
    }
}
