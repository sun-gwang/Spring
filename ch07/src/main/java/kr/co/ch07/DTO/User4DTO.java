package kr.co.ch07.DTO;

import kr.co.ch07.entity.User4;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class User4DTO {
    private String uid;
    private String name;
    private String gender;
    private int age;
    private String hp;

    public User4 toEntity(){
        return User4.builder().uid(uid)
                .name(name)
                .gender(gender)
                .age(age)
                .hp(hp).build();
    }
}
