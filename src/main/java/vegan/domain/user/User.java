package vegan.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vegan.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable =false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column
  private String picture;

  //JPA로 디비 저장시 Enum값을 어떤 형태로 저장할지 -> 문자열로 저장 (기본값은 숫자)
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @Builder
  public User(String name, String email, String picture, Role role){
    this.name = name;
    this.email = email;
    this.picture = picture;
    this.role = role;
  }

  public User update(String name, String picture) {
    this.name = name;
    this.picture = picture;

    return this;
  }

  public String getRoleKey(){
    return this.role.getKey();
  }
}
