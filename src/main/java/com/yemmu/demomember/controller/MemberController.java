package com.yemmu.demomember.controller;

import com.yemmu.demomember.entity.Member;
import com.yemmu.demomember.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController (MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<Member>> getMembers(){
        List<Member> members = memberService.findMembers();
        if(members.isEmpty()){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (members, HttpStatus.OK);
    }
    @GetMapping("{/memberId}")
    public Optional<Member> getMember(@PathVariable Long memberId){
        return memberService.findMember(memberId);
    }

}
