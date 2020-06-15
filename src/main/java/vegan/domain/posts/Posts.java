package vegan.domain.posts;
//domain은 sw요구사항 문제 영역
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import vegan.domain.BaseTimeEntity;

//모든 필드 Getter 메소드 자동 생성
@Getter
//기본 생성자 자동 추가
@NoArgsConstructor
//JPA Annotation - 테이블과 링크될 클래스임을 나타냄 (클래스는 카멜케이스, 테이블이름은 스네이크네이밍으로 매치)
@Entity
public class Posts extends BaseTimeEntity { //실제 DB 테이블과 매칭될 클래스 (= 엔티티 클래스로 DB 데이터 작업시 이 클래스의 수정을통해 작업)
  //해당 테이블의 PK 필드 나타냄
  @Id
  //PK 생성규칙 나타낸다
  @GeneratedValue(strategy = GenerationType.IDENTITY) //다음 옵션은 auto_increment
  private Long id;

  //Column 어노테이션 안써도 해당 클래스의 필드는 모두 칼럼이다 하지만 추가 옵션 설정 위해 사용
  @Column(length = 500, nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT", nullable = false) //타입 텍스트로 변경
  private String content;

  private String author;

  //해당 클래스의 빌더 패턴 클래스를 생성 (생성 시점에 값 채워서 디비에 삽입 역할)
  @Builder
  public Posts(String title, String content, String author) {
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}

// 이 클래스를 기준으로 테이블이 생성되고, 스키마가 변경된다.