package kr.co.sboard.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor
public class ArticleDTO {

    private int no;
    private int parent;
    private int comment;
    private String cate;
    private String  title;
    private String content;
    private int file;
    private int hit;
    private String writer;
    private String regip;
    private LocalDateTime rdate;


}
