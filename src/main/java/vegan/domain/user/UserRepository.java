package vegan.domain.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
  Optional<User> findByEmail(String email); // 소셜 로그인 반환 값 중 이메일 통해 이미 생성된 사용자인지 판단 위한 메소드

}
