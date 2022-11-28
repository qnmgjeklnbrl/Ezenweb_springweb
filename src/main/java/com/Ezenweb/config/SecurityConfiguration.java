package com.Ezenweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
       // super.configure(http); 모든 HTTP 보안설정
       
    }

   






}
 // 시큐리티 기본값
    //     1. 해당 프로젝트의 모든 URL 잠긴다
    //    2. login html 제공
    //    2. login controller 제공
    //    2. login service 제공
    // --------> 커스텀 작업  SecurityConfiguration : 시큐리티 설정 클래스
        // /WebSecurityConfigurerAdapter :웹 시큐리티 설정 클래스
            //설정 종류 
                // 1.URL 권한