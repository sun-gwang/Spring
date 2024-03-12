package kr.co.ch06.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User3DTO {
    private String uid;
    private String name;
    private String birth;
    private String hp;
    private String addr;
}
