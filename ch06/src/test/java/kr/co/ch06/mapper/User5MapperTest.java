package kr.co.ch06.mapper;

import kr.co.ch06.dto.User4DTO;
import kr.co.ch06.dto.User5DTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class User5MapperTest {

    @Autowired
    private User5Mapper mapper;

    @Test
    @DisplayName("User5 등록")
    void insertUser5() {
        log.info("insertUser5()...");

        // given
        User5DTO user5DTO = User5DTO.builder()
                .name("테스트")
                .gender("M")
                .age(25)
                .addr("테스트시").build();

        // when
        mapper.insertUser5(user5DTO);
        log.info(user5DTO.toString());

        // then
        User5DTO resultUser5 = mapper.selectUser5(user5DTO.getSeq());
        assertEquals(user5DTO.getSeq(), resultUser5.getSeq());
    }

    @Test
    @DisplayName("User5 목록")
    void selectUser5s() {
        log.info("selectUser5s()...");

        // given / when
        List<User5DTO> users = mapper.selectUser5s();

        for(User5DTO user : users){
            log.info(user.toString());
        }

        // then
        assertFalse(users.isEmpty());
    }

    @Test
    @DisplayName("User5 조회")
    void selectUser5() {

        log.info("selectUser5");

        // given
        int seq = 11;

        // when
        User5DTO user5DTO = mapper.selectUser5(seq);
        log.info(user5DTO.toString());

        // then
        assertEquals(seq, user5DTO.getSeq());

    }

    @Test
    void updateUser5() {
        log.info("updateUser5");

        // given
        User5DTO user5DTO = User5DTO.builder()
                .seq(11)
                .name("테스트")
                .gender("M")
                .age(25)
                .addr("테스트시").build();

        // when
        mapper.updateUser5(user5DTO);

        // then
        User5DTO resultUser5 = mapper.selectUser5(user5DTO.getSeq());
        assertEquals(user5DTO.getName(),resultUser5.getName());
    }

    @Test
    void deleteUser5() {
        log.info("deleteUser5()...");

        // given
        int seq = 9;

        // when
        mapper.deleteUser5(seq);

        // then
        User5DTO resultUser5 = mapper.selectUser5(seq);
        assertNull(resultUser5);
    }
}