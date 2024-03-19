package kr.co.sboard.service;

import jakarta.persistence.Entity;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j @RequiredArgsConstructor @Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    // RootConfig Bean 생성/등록
    private final ModelMapper modelMapper;

    public void insertArticle(ArticleDTO articleDTO){
        
        // articleDTO를 articleEntity로 변환
        Article article = modelMapper.map(articleDTO, Article.class);
        log.info(article.toString());

        // 저장
        articleRepository.save(article);
    }


/*
    public List<ArticleDTO> selectArticles(){
        List<Article> articles = articleRepository.findAll();

        List<ArticleDTO> articleDTOS = articles.stream()
                .map(entity->ArticleDTO.builder()
                        .no(entity.getNo())
                        .parent(entity.getParent())
                        .comment(entity.getComment())
                        .cate(entity.getCate())
                        .title(entity.getTitle())
                        .content(entity.getContent())
                        .file(entity.getFile())
                        .hit(entity.getHit())
                        .writer(entity.getWriter())
                        .regip(entity.getRegip())
                        .rdate(entity.getRdate()).build())
                .collect(Collectors.toList());

        return articleDTOS;
    }
*/

}