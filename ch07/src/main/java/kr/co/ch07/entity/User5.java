package kr.co.ch07.entity;

import jakarta.persistence.*;
import kr.co.ch07.DTO.User5DTO;
import lombok.*;

@Getter @ToString @Builder @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="user5")
public class User5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    private String name;
    private String gender;
    private int age;
    private String addr;

    public User5DTO toDTO(){
        return User5DTO.builder()
                .seq(seq)
                .name(name)
                .gender(gender)
                .age(age)
                .addr(addr).build();
    }
}
