package com.Ezenweb.controller.test;

import com.Ezenweb.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {
    //1.p.57
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String getHello(){
        return "메소드 접근";
    }
    //2. p.58
    @GetMapping(value = "/name")
    public String getName(){
        return "Flature";
    }
    //3. p.59
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }

    //4. p.60
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var){
        return var;
    }
    //5. p.61
    @GetMapping(value = "/request1")
    public String getRequestParam1(@RequestParam String name , @RequestParam String email ,@RequestParam String organization ){
        return name +"  "+email+"  "+organization;
    }
    //6. p.62
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String,String> param){
        StringBuilder sb = new StringBuilder();
        param.entrySet().forEach(map -> {
            sb.append(map.getKey()+"  "+map.getValue()+"\n");
        });
        return sb.toString();
    }
    //http://localhost:8081/api/v1/get-api/request3?name=%EC%9D%B4%EB%A6%84&email=asd@asd.com&organization=asdasd
    //7. p.66
    @GetMapping("/request3")
    public String  getRequestParam3(MemberDto memberDto){
        return memberDto.toString();
    }

}

