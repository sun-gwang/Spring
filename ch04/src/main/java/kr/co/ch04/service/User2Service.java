package kr.co.ch04.service;

import kr.co.ch04.dao.User2DAO;
import kr.co.ch04.dto.User2DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class User2Service {

    @Autowired
    private User2DAO dao;

    public void insertUser2(User2DTO user2DTO){}
    public User2DTO selectUser2(String uid){
        return null;
    }
    public List<User2DTO> selectUser2s(){
        return null;
    }
    public void updateUser2(User2DTO user2DTO){}
    public void deleteUser2(String uid){}

}
