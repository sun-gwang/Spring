package kr.co.ch07.DTO;

import kr.co.ch07.entity.User5;
import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor @Builder
public class User5DTO {
    private int seq;
    private String name;
    private String gender;
    private int age;
    private String addr;

    public User5 toEntity(){
        return User5.builder()
                .seq(seq)
                .name(name)
                .gender(gender)
                .age(age)
                .addr(addr).build();
    }
}
