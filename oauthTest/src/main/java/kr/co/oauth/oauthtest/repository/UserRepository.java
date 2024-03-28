package kr.co.oauth.oauthtest.repository;

import kr.co.oauth.oauthtest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
