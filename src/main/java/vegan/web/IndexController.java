package vegan.web;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model; // 서버템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vegan.config.auth.LoginUser;
import vegan.config.auth.dto.SessionUser;
import vegan.service.PostsService;
import vegan.web.dto.PostsResponseDto;

@RequiredArgsConstructor
@Controller
public class IndexController {

  private final PostsService postsService;
  private final HttpSession httpSession;
  //머스테치 스타터 덕에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동 지정됩니다.
  // 앞의 경로는 src/main/resource/templates이고 뒤 확장자는 .mustache가 붙는다.
  @GetMapping("/")
  public String index(Model model, @LoginUser SessionUser user){
    model.addAttribute("posts", postsService.findAllDesc());
    //Custom~service에서 로그인 성공 시 세션에 Session User 저장하도록 구성 - > @LoginUser 사용
    if(user!=null) {// sessiong model에 userName으로 등ㄺ.. 세셩 저장된 ㅏㄱㅄ이 없으면 modelㅇ세ㅓ 아무런 값이 없느 상태이니다.
      model.addAttribute("userName", user.getName());
    }
    return "index"; // index.mustache로 전환되어 View Resolver가 처리하게 된다.
  }

  @GetMapping("/posts/save")
  public String postsSave(){
    return "posts-save";  //posts-save.mustache를 호출한다.
  }
  @GetMapping("/posts/update/{id}")
  public String postsUpdate(@PathVariable Long id, Model model){
    PostsResponseDto dto = postsService.findById(id);
    model.addAttribute("post",dto);

    return "posts-update";
  }
}
