package com.Ezenweb.controller.test;

import com.Ezenweb.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {
    //1. p.68
     @RequestMapping(value ="/domain", method= RequestMethod.POST)
    public String postExample() {
        return "Hello POST API";
    }
    //2. p.69
    @PostMapping("/member")
    public String postMember(@RequestBody Map<String,Object> postData) {
        return postData.toString();
    }
    @PostMapping("/member2")
    public String postMemberDto(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }
}
