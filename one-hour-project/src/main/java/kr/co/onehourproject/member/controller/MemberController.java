package kr.co.onehourproject.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String getHello(){
        return "Hello Around Hub Studio!";
    }

    @PostMapping("/join")
    public String join(){
        return "sting";
    }
}
