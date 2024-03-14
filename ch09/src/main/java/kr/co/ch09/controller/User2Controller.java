package kr.co.ch09.controller;

import kr.co.ch09.dto.User2DTO;
import kr.co.ch09.service.User2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class User2Controller {

    private final User2Service service;
    @GetMapping("/user2")
    public ResponseEntity<List<User2DTO>> list(){
        return service.selectUser2s();
    }

    @GetMapping("/user2/{uid}")
    public ResponseEntity<?> User2(@PathVariable("uid") String uid){

        return service.selectUser2(uid);
    }

    @PostMapping("/user2")
    public ResponseEntity<?> register(@RequestBody @Validated User2DTO user2DTO){

        return service.insertUser2(user2DTO);
    }

    @PutMapping("/user2")
    public ResponseEntity<?> modify(@RequestBody User2DTO user2DTO) {
        return service.updateUser2(user2DTO);
    }

    @DeleteMapping("/user2/{uid}")
    public ResponseEntity<?> delete(@PathVariable("uid") String uid){
        return service.deleteUser2(uid);
    }
}