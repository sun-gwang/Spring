package kr.co.sboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j @Controller @RequiredArgsConstructor
public class ArticleController {

    /*
        @ModelAttribute("cate")
         - modelAttribute("cate", cate)와 동일
    */

    private final ArticleService articleService;


    @GetMapping("/article/list")
    public String list(@ModelAttribute("cate") String cate, Model model){

        return "/article/list";
    }


    @GetMapping("/article/write")
    public String write(@ModelAttribute("cate") String cate){
        return "/article/write";
    }

    @PostMapping("/article/write")
    public String write(HttpServletRequest req, ArticleDTO articleDTO){
        /*
            글작성을 테스트할 때는 로그인 해야 하기 때문에
            SecurityConfig 인가 설정 수정할것
        */

        String regip = req.getRemoteAddr();
        articleDTO.setRegip(regip);
        log.info(articleDTO.toString());

        articleService.insertArticle(articleDTO);

        return "redirect:/article/list";
    }

}
