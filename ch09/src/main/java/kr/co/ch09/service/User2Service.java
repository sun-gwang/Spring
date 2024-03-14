package kr.co.ch09.service;

import kr.co.ch09.dto.User2DTO;
import kr.co.ch09.entity.User2;
import kr.co.ch09.repository.User2Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j @Service @RequiredArgsConstructor
public class User2Service {

    private final User2Repository repository;

    public ResponseEntity<?> insertUser2(User2DTO user2DTO){
        
        /*
            JPA save는 삽입, 수정을 동시에 할 수 있는 메서드이기 때문에
            삽입을 수행하고자 할 경우에는 먼저 미리 exitsById()로 존재여부를 확인하고
            save를 수행하면 됨
        */
        if(repository.existsById(user2DTO.getUid())){
            // 이미 존재하는 아이디라면
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(user2DTO.getUid() + " is already exist");
        }else{
            // 만들 수 있는 아이디라면(중복없는)
            User2 user2 = user2DTO.toEntity();
            repository.save(user2);

            return ResponseEntity.status(HttpStatus.OK).body(user2DTO);
        }

    }

    public ResponseEntity<List<User2DTO>> selectUser2s(){

        List<User2> user2s = repository.findAll();

        List<User2DTO> user2DTOS = user2s.stream()
                .map(entity -> User2DTO.builder()
                        .uid(entity.getUid())
                        .name(entity.getName())
                        .birth(entity.getBirth())
                        .addr(entity.getAddr()).build())
                        .collect(Collectors.toList());

        return ResponseEntity.ok().body(user2DTOS);
    }

    public ResponseEntity<?> selectUser2(String uid){
        // orElseThrow() 메서드로 존재하는 Entity를 가져오거나 존재하지 않으면
        // 기본 예외 NoSuchElementException 발생 시켜준다
        try {
            User2 user2 = repository.findById(uid).orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(user2.toDTO());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

    }

    public ResponseEntity<?> updateUser2(User2DTO user2DTO){

        // 수정하기 전에 존재여부 확인
        if(repository.existsById(user2DTO.getUid())){
            // 아이디가 확인 되면 수정
            repository.save(user2DTO.toEntity());

            // 수정 후 수정 데이터 반환
            return ResponseEntity.status(HttpStatus.OK).body(user2DTO);
        }else{
            // 사용자가 존재하지 않으면 NOT_FOUND 응답데이터와 user not found 메세지
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
    }

    public ResponseEntity<?> deleteUser2(String uid){
        repository.deleteById(uid);

        Optional<User2> optUser2 = repository.findById(uid);

        if(optUser2.isPresent()){
            // 삭제할 사용자가 존재하면 삭제
            repository.deleteById(uid);
            // 삭제 후 삭제한 사용자 정보 ResponseEntity로 반환
            return ResponseEntity.ok().body(optUser2.get());
        }else{
            // 삭제할 사용자가 존재하지 않으면 NOT FOUND 응답데이터와 user not found 메시지 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

    }

}
