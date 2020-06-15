package vegan.config.auth.dto;

import java.io.Serializable;
import lombok.Getter;
import vegan.domain.user.User;

@Getter
public class SessionUser implements Serializable {
  private String name;
  private String email;
  private String picture;

  public SessionUser(User user){
    this.name = user.getName();
    this.email = user.getEmail();
    this.picture = user.getPicture();
  }
}

//User 클래스를 사용하지 않는 이유는 세션에 저장하기위해 User클래스를 세션에 저장하면 User클래스에
//직렬화를 구현하지 않았다고 에러가 뜬다.. 만약 직렬화를 넣기엔 User는 엔티티이다.
//자식 엔티티까지 고려해 성능 이슈, 부수 효과의 이유로 그냥 따로 직렬화 기능을 가진 세션 Dto를 추가하는 것이 좋다.