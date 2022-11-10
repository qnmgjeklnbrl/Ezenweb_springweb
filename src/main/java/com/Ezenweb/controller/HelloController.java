package com.Ezenweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //현재 클래스를 스프링의 RestController 사용
public class HelloController {
    @RequestMapping("/hello")
    public static  String  hello(){return "Hello world!";}
}
