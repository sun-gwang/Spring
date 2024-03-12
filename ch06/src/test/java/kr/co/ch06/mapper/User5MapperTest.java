package kr.co.ch06.mapper;

import kr.co.ch06.dto.User4DTO;
import kr.co.ch06.dto.User5DTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class User5MapperTest {

    @Autowired
    private User5Mapper mapper;

    @Test
    @DisplayName("User5등록")
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
    void selectUser5s() {
    }

    @Test
    void selectUser5() {
    }

    @Test
    void updateUser5() {
    }

    @Test
    void deleteUser5() {
    }
}