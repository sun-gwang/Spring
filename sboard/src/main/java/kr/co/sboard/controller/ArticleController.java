package kr.co.sboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.Config;
import kr.co.sboard.repository.ConfigRepository;
import kr.co.sboard.service.ArticleService;
import kr.co.sboard.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j @Controller @RequiredArgsConstructor
public class ArticleController {

    /*
        @ModelAttribute("cate")
         - modelAttribute("cate", cate)와 동일
    */

    private final ArticleService articleService;
    private final ConfigRepository configRepository;

    @GetMapping("/article/list")
    public String list(Model model, String cate, PageRequestDTO pageRequestDTO){

        PageResponseDTO pageResponseDTO = null;

        if(pageRequestDTO.getKeyword() == null) {
            // 일반 글 목록 조회
            pageResponseDTO = articleService.selectArticles(pageRequestDTO);

        }else{
            // 검색 글 목록 조회
            pageResponseDTO = articleService.searchArticles(pageRequestDTO);
        }

        log.info("pageResponseDTO" + pageResponseDTO);
        model.addAttribute(pageResponseDTO);


        return "/article/list";
    }

    // 여기 바꿈
    @GetMapping("/article/write")
    public String write(Model model, String cate, PageRequestDTO pageRequestDTO){

        PageResponseDTO pageResponseDTO = PageResponseDTO.builder()
                                                        .pageRequestDTO(pageRequestDTO)
                                                        .build();
        model.addAttribute(pageResponseDTO);

        return "/article/write";
    }

    @PostMapping("/article/write")
    public String write(HttpServletRequest req, ArticleDTO articleDTO, @RequestPart String cate, RedirectAttributes redirectAttributes){
        /*
            글작성을 테스트할 때는 로그인 해야 하기 때문에
            SecurityConfig 인가 설정 수정할것
        */

        String regip = req.getRemoteAddr();
        articleDTO.setRegip(regip);
        log.info(articleDTO.toString());
        log.info("cate:" + cate);

        articleService.insertArticle(articleDTO);

        redirectAttributes.addAttribute("cate", cate);
        return "redirect:/article/list";
    }

    // 여기 바꿈
    @GetMapping("/article/view")
    public String view(Model model, String cate, ArticleDTO articleDTO){

        articleDTO.setCate(cate);
        
        articleDTO = articleService.findById(articleDTO.getNo());

        model.addAttribute(articleDTO);

        return "/article/view";
    }


    @GetMapping("/article/modify")
    public String articleModify() {
        return "/article/modify";
    }


    @ResponseBody
    @GetMapping("/article/{no}")
    public ArticleDTO modify(@PathVariable("no") int no){
        ArticleDTO articleDTO = articleService.selectArticle(no);
        return articleDTO;
    }


    @ResponseBody
    @DeleteMapping("/article/{no}")
    public ResponseEntity<?> deleteArticle(@PathVariable("no") int no){
        return articleService.deleteArticle(no);
    }

    @ResponseBody
    @PutMapping("/article")
    public ResponseEntity<?> modify(@RequestBody ArticleDTO articleDTO){

        return articleService.updateArticle(articleDTO);
    }
}
