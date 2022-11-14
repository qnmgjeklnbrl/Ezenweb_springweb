package com.Ezenweb.controller.test;

import com.Ezenweb.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {
    //1. p.70
    @PutMapping("/member")
    public String putMember(@RequestBody Map<String,Object> putData){
        return putData.toString();


    }
    //2-1 p.71-1
    @PutMapping("/member1")
    public String putMemberDto1(@RequestBody MemberDto memberDto){
        return memberDto.toString();

    }

    //2-2 p.71-2
    @PutMapping("/member2")
    public MemberDto putMemberDto2(@RequestBody MemberDto memberDto){
        return memberDto;

    }






}
