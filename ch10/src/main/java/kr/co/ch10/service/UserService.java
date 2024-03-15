package kr.co.ch10.service;

import kr.co.ch10.dto.UserDTO;
import kr.co.ch10.entity.User;
import kr.co.ch10.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j @Service @RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public ResponseEntity<?> insertUser(UserDTO userDTO){

        /*
            JPA save는 삽입, 수정을 동시에 할 수 있는 메서드이기 때문에
            삽입을 수행하고자 할 경우에는 먼저 미리 exitsById()로 존재여부를 확인하고
            save를 수행하면 됨
        */
        if(repository.existsById(userDTO.getUid())){
            // 이미 존재하는 아이디라면
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(userDTO.getUid() + " is already exist");
        }else{
            // 만들 수 있는 아이디라면(중복없는)
            User user = userDTO.toEntity();
            repository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        }

    }

    public ResponseEntity<List<UserDTO>> selectUser2s(){

        List<User> users = repository.findAll();

        List<UserDTO> userDTOS = users.stream()
                .map(entity -> UserDTO.builder()
                        .uid(entity.getUid())
                        .pass(entity.getPass())
                        .name(entity.getName())
                        .age(entity.getAge())
                        .hp(entity.getHp())
                        .regDate(entity.getRegDate())
                        .role(entity.getRole()).build())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(userDTOS);
    }

    public ResponseEntity<?> selectUser2(String uid){
        // orElseThrow() 메서드로 존재하는 Entity를 가져오거나 존재하지 않으면
        // 기본 예외 NoSuchElementException 발생 시켜준다
        try {
            User user = repository.findById(uid).orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(user.toDTO());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

    }

    public ResponseEntity<?> updateUser(UserDTO userDTO){

        // 수정하기 전에 존재여부 확인
        if(repository.existsById(userDTO.getUid())){
            // 아이디가 확인 되면 수정
            repository.save(userDTO.toEntity());

            // 수정 후 수정 데이터 반환
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        }else{
            // 사용자가 존재하지 않으면 NOT_FOUND 응답데이터와 user not found 메세지
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
    }

    public ResponseEntity<?> deleteUser(String uid){
        repository.deleteById(uid);

        Optional<User> optUser = repository.findById(uid);

        if(optUser.isPresent()){
            // 삭제할 사용자가 존재하면 삭제
            repository.deleteById(uid);
            // 삭제 후 삭제한 사용자 정보 ResponseEntity로 반환
            return ResponseEntity.ok().body(optUser.get());
        }else{
            // 삭제할 사용자가 존재하지 않으면 NOT FOUND 응답데이터와 user not found 메시지 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

    }

}
