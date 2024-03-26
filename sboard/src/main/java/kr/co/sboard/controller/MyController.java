package kr.co.sboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j @Controller @RequiredArgsConstructor
public class MyController {

    @GetMapping("/my/setting")
    public String setting(){
        return "/my/setting";
    }
}
