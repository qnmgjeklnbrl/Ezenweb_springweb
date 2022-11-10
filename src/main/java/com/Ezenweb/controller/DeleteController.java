package com.Ezenweb.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {
    //p.75
    //http://localhost:8082/api/v1/delete-api/{String 값}
    @DeleteMapping("/{variable}")
    public String DeleteVariable(@PathVariable String variable) {
        return variable;
    }
    //p.76
    //http://localhost:8082/api/v1/delete-api/request1?email=value
    @DeleteMapping("/request1")
    public String getRequestParam1(@RequestParam String email){
        return email;

    }

}
