package vegan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditing 활성화
//스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성 자동 설정
@SpringBootApplication
public class Application {
  public static void main(String[] args){
    //내장 WAS 실행
    SpringApplication.run(Application.class, args);
  }
}
