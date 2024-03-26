package kr.co.onehourproject.member.controller;

import kr.co.onehourproject.member.controller.dto.JoinRequest;
import kr.co.onehourproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/hello")
    public String getHello(){
        return "Hello Around Hub Studio!";
    }

    @PostMapping("/join")
    public String join(@RequestBody JoinRequest joinRequest){
        String id = joinRequest.getId();
        String name = joinRequest.getName();
        String phoneNumber = joinRequest.getPhoneNumber();

        String result = memberService.join(id, name, phoneNumber);
        if (result.equals("success")){
            return "success";
        }else{
            return "fail";
        }
    }
}
