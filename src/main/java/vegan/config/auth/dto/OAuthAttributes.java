package vegan.config.auth.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import vegan.domain.user.Role;
import vegan.domain.user.User;

@Getter
public class OAuthAttributes {
  private Map<String,Object> attributes;
  private String nameAttributeKey;
  private String name;
  private String email;
  private String picture;

  @Builder
  public OAuthAttributes(Map<String,Object> attributes, String nameAttributeKey, String name, String email, String picture){
    this.attributes = attributes;
    this.nameAttributeKey = nameAttributeKey;
    this.name = name;
    this.email = email;
    this.picture = picture;
  }

  //of를 사용하여 사용자 정보 Map 값 하나하나 변환
  public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String,Object> attributes){
    return ofGoogle(userNameAttributeName, attributes);
  }

  private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){
    return OAuthAttributes.builder()
        .name((String) attributes.get("name"))
        .email((String) attributes.get("email"))
        .picture((String) attributes.get("picture"))
        .attributes(attributes)
        .nameAttributeKey(userNameAttributeName)
        .build();
  }

  //User 엔티티 생성.
  //처음 가입시 기본 권한 GUEST로 줌
  public User toEntity(){
    return User.builder()
        .name(name)
        .email(email)
        .picture(picture)
        .role(Role.GUEST)
        .build();
  }
}
