package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.MemberDto;
import com.Ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //해당 클래스가 RestController 임을 명시
public class MemberController {
    @Autowired
    private MemberService memberService;
    @GetMapping("/member")
    public int setmember(){
        MemberDto memberDto = new MemberDto(0,"asd@asd.com","asdasd");
        int result = memberService.setmember(memberDto);

        return result;

    }


}
