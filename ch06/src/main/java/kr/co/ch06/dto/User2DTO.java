package kr.co.ch06.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User2DTO {
    private String uid;
    private String name;
    private String birth;
    private String addr;
}
