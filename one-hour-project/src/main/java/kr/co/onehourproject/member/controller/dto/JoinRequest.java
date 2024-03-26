package kr.co.onehourproject.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter @Service @AllArgsConstructor @NoArgsConstructor @Builder
public class JoinRequest {
    private String id;
    private String name;
    private String phoneNumber;
}
