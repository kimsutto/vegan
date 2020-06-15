package vegan.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//테스트 진행시 JUnit 내장 실행자 외 다른 실행자 실행 -> SpringRunner
@RunWith(SpringRunner.class)
//Web(Spring MVC)에 집중할 수 있는 어노테이션
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
  //Spring이 관린하는 Bean을 주입받는다
  @Autowired
  //웹 API 테스트시 사용 -> HTTP GET,POST 등 테스트 가능
  private MockMvc mvc;

  @Test
  public void hello가_리턴된다() throws Exception {
    String hello = "hello";

    mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 GET요청한다.
        .andExpect(status().isOk())   //mvc.perform의 결과 검증 (HTTP Header의 Status)
        .andExpect(content().string(hello));  //mvc.perform의 결과 검증 (응답 본문 내용 검증)
  }

  @Test
  public void helloDto가_리턴된다() throws Exception{
    String name = "hello";
    int amount = 1000;
    //API 테스트시 사욜될 요청 파라미터 .param으로 설정 값은 String만 허용되어 바꿔줘야한다.
    mvc.perform(get("/hello/dto").param("name",name).param("amount",String.valueOf(amount)))
        .andExpect(status().isOk())
        //JSON응답값 필드별 검증 할수 있는 메소드로 $를 기준으로 필드명 명시
        .andExpect(jsonPath("$.name",is(name)))
        .andExpect(jsonPath("$.amount",is(amount)));
  }
}
