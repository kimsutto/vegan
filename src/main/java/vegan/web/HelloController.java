package vegan.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vegan.web.dto.HelloResponseDto;

//컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
@RestController
public class HelloController {
  //HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어줌 (=@RequestMapping(method=RequestMethod.GET))
  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("/hello/dto")
  //@RequestParam : 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
  //외부에서 @RequestParam("name")으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장
  public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
    return new HelloResponseDto(name, amount);
  }
}