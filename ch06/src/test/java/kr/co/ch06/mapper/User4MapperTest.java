package kr.co.ch06.mapper;

import kr.co.ch06.dto.User4DTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class User4MapperTest {

    @Autowired
    private User4Mapper mapper;
    

    @DisplayName("user4등록")
    void insertUser4() {
        log.info("insertUser4()...");

        // given
        User4DTO user4DTO = User4DTO.builder()
                                                .uid("j101")
                                                .name("테스트")
                                                .gender("M")
                                                .age(25)
                                                .hp("010-4444-4444")
                                                .addr("테스트시").build();
        // when
        mapper.insertUser4(user4DTO);
        log.info(user4DTO.toString());

        // then
        User4DTO resultUser4 = mapper.selectUser4(user4DTO.getUid());
        assertEquals(user4DTO.getUid(), resultUser4.getUid());
    }


    @DisplayName("user4 조회")
    void selectUser4() {
        log.info("selectUser4()...");

        // given
        String uid = "j101";

        // when
        User4DTO user4DTO = mapper.selectUser4(uid);
        log.info(user4DTO.toString());

        // then
        assertEquals(uid, user4DTO.getUid());
    }


    @DisplayName("user4 목록")
    void selectUser4s() {
        log.info("selectUser4s()...");

        // given / when
        List<User4DTO> users = mapper.selectUser4s();
        for(User4DTO user : users){
            log.info(user.toString());
        }

        // then
        assertFalse(users.isEmpty());
    }


    @DisplayName("user4 수정")
    void updateUser4() {

        log.info("updateUser4()...");

        // given
        User4DTO user4DTO = User4DTO.builder()
                                            .uid("j101")
                                            .name("테슽흐")
                                            .gender("F")
                                            .age(28)
                                            .hp("010-4444-1114")
                                            .addr("테스트시시").build();

        // when
        mapper.updateUser4(user4DTO);
        log.info(user4DTO.toString());

        // then
        User4DTO resultUser4 = mapper.selectUser4(user4DTO.getUid());
        assertEquals(user4DTO.getName(), resultUser4.getName());
    }

    @Test
    @DisplayName("user4 삭제")
    void deleteUser4() {
        log.info("deleteUser4()");

        // given
        String uid = "j101";

        // when
        mapper.deleteUser4(uid);

        // then
        User4DTO resultUser4 = mapper.selectUser4(uid);
        assertNull(resultUser4);
    }
}