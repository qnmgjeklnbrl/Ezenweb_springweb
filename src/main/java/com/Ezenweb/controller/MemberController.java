package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.MemberDto;
import com.Ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController //해당 클래스가 RestController 임을 명시
@RequestMapping("/member")
public class MemberController {
   // @GetMapping("/signup")
    // public Resource getsignup() {
    //     return new ClassPathResource("templates/member/signup.html");

    // }
    // @GetMapping("/login")
    // public Resource getlogin() {
    //     return new ClassPathResource("templates/member/login.html");

    // }
    // @GetMapping("/findpassword")
    // public Resource findpassword(){
    //     return new ClassPathResource("templates/member/findpassword.html");
    // }
    // @GetMapping("/delete")
    // public Resource getdelete(){ return new ClassPathResource("templates/member/delete.html");}
    // @GetMapping("/update")
    // public Resource getupdate(){ return new ClassPathResource("templates/member/update.html");}


    @Autowired
    private MemberService memberService;
    @PostMapping("/setmember")
    public int setmember(@RequestBody MemberDto memberDto){

        int result = memberService.setmember(memberDto);

        return result;

    }

    // @PostMapping("/getmember")   [시큐리티 사용시 필요 없음]
    // public int getmember(@RequestBody MemberDto memberDto){

    //     int result = memberService.getmember(memberDto);

    //     return result;

    // }

    @GetMapping("/getpassword")
    public String getpassword( @RequestParam("memail") String memail ){
        String result = memberService.getpassword( memail );
        return result;
    }
    @DeleteMapping("/setdelete")
    public int setdelete( @RequestParam("mpassword") String mpassword ){
        // 1. 서비스처리
        int result = memberService.setdelete( mpassword );
        // 2. 서비스결과 반환
        return result;
    }
    @PutMapping("/setupdate")
    public int setupdate( @RequestParam("mpassword") String mpassword ){
        int result = memberService.setupdate( mpassword );
        return result;
    }

    @GetMapping("/getloginMno") // 6. 로그인 정보 확인
    public String getloginMno(){
        String result = memberService.getloginMno();
        return result;
    }

    // @GetMapping("/logout")
    // public void logout(  ){
    //     memberService.logout( );


    // }

    @GetMapping("/list") //회원 목록123
    public List<MemberDto> list(){
       List<MemberDto> list = memberService.list();

       return list;

    }
    @GetMapping("/getauth")
    public String getauth(@RequestParam("toemail") String toemail ) {
        return memberService.getauth(toemail);
    }


}
