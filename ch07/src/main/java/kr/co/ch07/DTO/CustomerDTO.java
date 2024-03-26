package kr.co.ch07.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor @Builder
public class CustomerDTO {

    private String cuistId;
    private String name;
    private int age;
    private String hp;
    private String addr;
    private LocalDateTime regDate;

    private long orderCount;
}
