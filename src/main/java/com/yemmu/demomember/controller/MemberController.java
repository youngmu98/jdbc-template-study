package com.yemmu.demomember.controller;

import com.yemmu.demomember.entity.Member;
import com.yemmu.demomember.entity.MemberPostDto;
import com.yemmu.demomember.mapper.MemberMapper;
import com.yemmu.demomember.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
//    private final MemberMapper memberMapper;

    public MemberController (MemberService memberService){
        this.memberService = memberService;
//        this.memberMapper = memberMapper;
    }

    @GetMapping
    public ResponseEntity<List<Member>> getMembers(){
        List<Member> members = memberService.findMembers();
        if(members.isEmpty()){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (members, HttpStatus.OK);
    }
    @GetMapping("/{memberId}")
    public Optional<Member> getMember(@PathVariable Long memberId){
        return memberService.findMember(memberId);
    }

    @PostMapping({"/add"})
    public ResponseEntity<Member> memberAdd(@RequestBody MemberPostDto memberPostDto){
        Member member = memberPostDto.toEntity();
        Member creatMember = memberService.saveMember(member);

        return new ResponseEntity<>(creatMember, HttpStatus.CREATED);
    }
    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
    }


}
