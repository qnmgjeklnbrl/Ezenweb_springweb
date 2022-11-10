package com.Ezenweb.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//해당 클래스가 controller 임을 명시 [스프링부트 scan]
@RequestMapping("/test1")//URL 경로 정의
public class Test1Controller {
    /*@GetMapping("")
    public String gettext() {
        return "test.html";
    }*/
    @GetMapping("")
    public Resource gettext() {
        return new ClassPathResource("test1.html") ;
    }
    //반환타입:문자열
    //반환타입: HTML
}
