package vegan.domain.posts; //Entity클래스와 함께 위치해야한다. -> 규모가 커지면 도메인 패키지에서 함께 관리

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//보통 Dao라 불리는 DB레이어 접근자 역할을 한다.
//<Entity class, PK타입>
public interface PostsRepository extends JpaRepository<Posts,Long> { //다음을 상속 받으면 기본적인 CRUD메소드가 자동 생성된다.
  @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
  //SpringDataJpa에서 제공하지 않는 메소드는 위처럼 쿼리로 작성해도 된다. 위에 정도는 jpa 기본 메소드로 가능
  // 규모가 커진 프로젝트에서 Entity 클래스만으로 데이터 조회가 어렵기 때문에 querydsl 프레임워크를 함께 사용한다.
  List<Posts> findAllDesc();
}
