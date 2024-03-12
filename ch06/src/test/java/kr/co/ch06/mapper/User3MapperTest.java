package kr.co.ch06.mapper;

import kr.co.ch06.dto.User2DTO;
import kr.co.ch06.dto.User3DTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class User3MapperTest {

    @Autowired
    private User3Mapper mapper;


    @DisplayName("User3 등록")
    void insertUser3() {
        log.info("insertUser3()...");

        // given
        User3DTO user3DTO = User3DTO.builder()
                                                .uid("J101")
                                                .name("테스트")
                                                .birth("2024-03-12")
                                                .hp("010-4444-4444")
                                                .addr("테스트시").build();

        // when
        mapper.insertUser3(user3DTO);
        log.info(user3DTO.toString());

        // then
        User3DTO resultUser3 = mapper.selectUser3(user3DTO.getUid());
        assertEquals(user3DTO.getUid(), resultUser3.getUid());
    }


    @DisplayName("User3 조회")
    void selectUser3() {
        log.info("selectUser3()...");

        // given
        String uid = "J101";

        // when
        User3DTO user3DTO = mapper.selectUser3(uid);

        // then
        assertEquals(uid, user3DTO.getUid());
    }


    @DisplayName("User3 목록")
    void selectUser3s() {

        log.info("selectUser3s()...");

        // given, when
        List<User3DTO> users = mapper.selectUser3s();

        for(User3DTO user : users){
            log.info(user.toString());
        }

        // then
        assertFalse(users.isEmpty());
    }


    @DisplayName("User3 수정")
    void updateUser3() {
        log.info("updateUser3()...");

        // given
        User3DTO user3DTO = User3DTO.builder()
                .uid("J101")
                .name("테슽흐")
                .birth("2024-04-04")
                .hp("010-1234-4443")
                .addr("우리집").build();

        // when
        mapper.updateUser3(user3DTO);
        log.info(user3DTO.toString());
        // then
        User3DTO resultUser3 = mapper.selectUser3(user3DTO.getUid());
        assertEquals(user3DTO.getName(), resultUser3.getName());
    }

    @Test
    @DisplayName("User3 삭제")
    void deleteUser3() {
        log.info("deleteUser3()...");

        // given
        String uid = "J101";

        // when
        mapper.deleteUser3(uid);

        // then
        User3DTO resultUser3 = mapper.selectUser3(uid);
        assertNull(resultUser3);

    }
}