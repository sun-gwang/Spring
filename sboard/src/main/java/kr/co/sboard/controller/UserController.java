package kr.co.sboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.sboard.config.AppInfo;
import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.TypeMirror;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final AppInfo appinfo;

    @GetMapping("/user/login")
    public String login(@ModelAttribute("success") String success){
        // 매개변수 success에 ModelAttribute 선언으로 View참조할 수 있음

        return "/user/login";
    }

    @GetMapping("/user/terms")
    public String terms(Model model){

        TermsDTO termsDTO = userService.selectTerms();
        model.addAttribute(termsDTO);

        return "/user/terms";
    }

    @GetMapping("/user/register")
    public String register(){
        return "/user/register";
    }

    @GetMapping("/user/findId")
    public String findId(){
        return "/user/findId";
    }

    @GetMapping("/user/findIdResult")
    public String findIdResult(String name, Model model){
        UserDTO userDTO = userService.selectUser(name);
        model.addAttribute(userDTO);
        return "/user/findIdResult";
    }

    @GetMapping("/user/findPassword")
    public String findPassword(){
        return "/user/findPassword";
    }

    @PostMapping("/user/register")
    public String register(HttpServletRequest req, UserDTO userDTO){

        String rgip = req.getRemoteAddr();
        userDTO.setRgip(rgip);

        log.info(userDTO.toString());

        userService.insertUser(userDTO);

        return "redirect:/user/login?success=200";
    }

    @ResponseBody
    @GetMapping("/user/{type}/{value}")
    public ResponseEntity<?> checkUser(HttpSession session,
                                       @PathVariable("type")  String type,
                                       @PathVariable("value") String value){

        log.info("type : " + type);
        log.info("value : " + value);

        int count = userService.selectCountUser(type, value);
        log.info("count : " + count);

        // 중복 없으면 이메일 인증코드 발송
        if(count == 0 && type.equals("email")){
            log.info("email : " + value);
            userService.sendEmailCode(session, value);
        }

        // Json 생성
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", count);

        return ResponseEntity.ok().body(resultMap);
    }

    // 아이디 찾기 이름 확인
    @ResponseBody
    @GetMapping("/selectName/{type}/{value}")
    public int selectName(@PathVariable("type")  String type,
                           @PathVariable("value") String value){

        return userService.selectCountUser(type, value);
    }

    // 아이디 찾기 이메일 검사, 전송
    @ResponseBody
    @GetMapping("/idEmail/{type}/{value}")
    public ResponseEntity<?> idEmail(HttpSession session,
                                                       @PathVariable("type")  String type,
                                                       @PathVariable("value") String value){

        log.info("type : " + type);
        log.info("value : " + value);

        int count = userService.selectCountUser(type, value);

        log.info("count : " + count);

        // 존재하는 메일이라면 발송
        if(count != 0 && type.equals("email")){
            userService.sendEmailCode(session, value);
        }

        // Json 생성
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", count);

        return ResponseEntity.ok().body(resultMap);
    }


    // 이메일 인증 코드 검사
    @ResponseBody
    @GetMapping("/email/{code}")
    public ResponseEntity<?> checkEmail(HttpSession session, @PathVariable("code") String code){

        String sessionCode = (String) session.getAttribute("code");

        if(sessionCode.equals(code)){
            // Json 생성
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", true);

            return ResponseEntity.ok().body(resultMap);
        }else{
            // Json 생성
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", false);

            return ResponseEntity.ok().body(resultMap);
        }
    }
}