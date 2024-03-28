package kr.co.oauth.oauthtest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j @Controller @RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/user/login")
    public String login(){
        return "/user/login";
    }
}
